package no.nav.medlemskap.hjelpemidler.services

import no.nav.medlemskap.hjelpemidler.clients.AzureAdClient
import no.nav.medlemskap.hjelpemidler.clients.LovemeAPI
import no.nav.medlemskap.hjelpemidler.clients.MedlemskapOppslagClient
import no.nav.medlemskap.hjelpemidler.clients.medlemskapoppslag.MedlOppslagRequest
import no.nav.medlemskap.hjelpemidler.config.Configuration
import no.nav.medlemskap.hjelpemidler.domain.*

import no.nav.medlemskap.hjelpemidler.http.cioHttpClient
import java.util.*


class HjelpeMidlerService():IJegKanHåndtereHjelpeMidlerRequest {

    var lovmeClient: LovemeAPI
    var configuration = Configuration()
    val azuraADClient = AzureAdClient(configuration)
    val httpClient = cioHttpClient
    init {

        lovmeClient = MedlemskapOppslagClient(
            baseUrl = configuration.register.medlemskapOppslagBaseUrl,
            azureAdClient = azuraADClient,
            httpClient = httpClient
        )
    }

    override suspend fun handleRequest(hjelpeMidlerRequest: HjelpeMidlerRequest,callId: String):HjelpeMidlerRespons {
        /*
        * 0. (pre step)
        * Valider input
        *   - filrer it så vi kun behandlker rett bruker gruppe
        * avklaringer : Hva gjør vi om/når det kommer feil bruker grupper.. skal vi kaste exception ut?
        * */
        /*
        * 1. kall regel motor
        * spørsmål : Bør vi bruke et bedre request objekt mot klient? Vi må ha flere parametere in mot
        * faktisk regel motor, men disse kan teoretisk lages "in the fly".
        * spørsmålet er om det er klienten eller denne tjenesten som skal ha dette ansvaret
        * */
        val lovmeRequest = RequestMapper().mapHjelpemidlerRequestToLovmeRequest(hjelpeMidlerRequest)
        val respons = kallMedlemskapOppslag(lovmeRequest, UUID.randomUUID().toString())
        //TODO: Endre signatur på funsjon til å få med callID fra http endepunktet.
        /*
        * 2. gjøre om respons fra regel motor til noe vi klarer å behandle
        * */

        val hjelpemidlerRespons = ResponsMapper().mapRegelResponsTilHjelpemidlerRespons(respons)

        /*
        * 3. gjør om resultat av pnkt2 til et konkret svar
        *  avklaringer :
        *     - hva gjør vi med timeouts
        *     - hva gjør vi med tekniske feil
        *     - hva gjør vi med kode 6/7
        * */

        return hjelpemidlerRespons
    }

        suspend fun kallMedlemskapOppslag(request: MedlOppslagRequest, callId: String): String {
            runCatching { lovmeClient.vurderMedlemskap(request, callId) }
                .onFailure {
                    if (it.message?.contains("GradertAdresseException") == true) {
                       throw GradertAdresseException("Medlemskapsvurdering kan ikke utføres på personer med kode 6/7")
                    } else {
                        throw Exception("Teknisk feil ved kall mot Lovme. Årsak : ${it.message}")
                    }
                }
                .onSuccess { return it }
            return "" //umulig å komme hit?

        }
}