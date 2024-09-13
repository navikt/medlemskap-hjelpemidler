package no.nav.medlemskap.hjelpemidler.services

import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRespons

interface IJegKanHåndtereHjelpeMidlerRequest {
    suspend fun handleRequest(hjelpeMidlerRequest: HjelpeMidlerRequest,callId:String):HjelpeMidlerRespons
}
