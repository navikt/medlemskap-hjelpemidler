package no.nav.medlemskap.hjelpemidler.services

import no.nav.medlemskap.hjelpemidler.clients.LovemeAPI
import no.nav.medlemskap.hjelpemidler.clients.MedlemskapOppslagClient
import no.nav.medlemskap.hjelpemidler.config.objectMapper
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRespons
import no.nav.medlemskap.hjelpemidler.domain.ResponsMapper
import no.nav.medlemskap.hjelpemidler.domain.Status
import kotlin.random.Random

class HjelpeMidlerService(val client: LovemeAPI):IJegKanHåndtereHjelpeMidlerRequest {
    override fun handleRequest(hjelpeMidlerRequest: HjelpeMidlerRequest):HjelpeMidlerRespons {
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
        val respons = client.utforVurderingForFnr(hjelpeMidlerRequest.fnr)
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
}