package no.nav.medlemskap.hjelpemidler.services

import no.nav.medlemskap.hjelpemidler.clients.LovemeAPI
import no.nav.medlemskap.hjelpemidler.clients.MedlemskapOppslagClient
import no.nav.medlemskap.hjelpemidler.config.objectMapper
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRespons
import no.nav.medlemskap.hjelpemidler.domain.Status
import kotlin.random.Random

class HjelpeMidlerService(val client: LovemeAPI):IJegKanHåndtereHjelpeMidlerRequest {
    override fun handleRequest(hjelpeMidlerRequest: HjelpeMidlerRequest):HjelpeMidlerRespons {

    val respons = client.utforVurderingForFnr(hjelpeMidlerRequest.fnr)
        val json = objectMapper.readTree(respons)
        if (Random.nextBoolean()){
            return HjelpeMidlerRespons(Status.NEI)
        }
        return HjelpeMidlerRespons(Status.JA)
    }
}