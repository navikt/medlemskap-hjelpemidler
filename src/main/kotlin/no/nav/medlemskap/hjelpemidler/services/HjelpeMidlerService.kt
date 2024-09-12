package no.nav.medlemskap.hjelpemidler.services

import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRespons
import no.nav.medlemskap.hjelpemidler.domain.Status

class HjelpeMidlerService:IJegKanHåndtereHjelpeMidlerRequest {
    override fun handleRequest(hjelpeMidlerRequest: HjelpeMidlerRequest):HjelpeMidlerRespons {
        if (hjelpeMidlerRequest.fnr=="1"){
            return HjelpeMidlerRespons(hjelpeMidlerRequest.fnr,Status.NEI)
        }
        return HjelpeMidlerRespons(hjelpeMidlerRequest.fnr,Status.JA)
    }
}