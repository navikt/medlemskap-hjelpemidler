package no.nav.medlemskap.hjelpemidler.services

import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRespons
import no.nav.medlemskap.hjelpemidler.domain.Status
import kotlin.random.Random

class HjelpeMidlerService:IJegKanHåndtereHjelpeMidlerRequest {
    override fun handleRequest(hjelpeMidlerRequest: HjelpeMidlerRequest):HjelpeMidlerRespons {


        if (Random.nextBoolean()){
            return HjelpeMidlerRespons(Status.NEI)
        }
        return HjelpeMidlerRespons(Status.JA)
    }
}