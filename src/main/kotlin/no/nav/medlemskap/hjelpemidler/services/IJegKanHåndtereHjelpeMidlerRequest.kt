package no.nav.medlemskap.hjelpemidler.services

import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRespons

interface IJegKanHåndtereHjelpeMidlerRequest {
    fun handleRequest(hjelpeMidlerRequest: HjelpeMidlerRequest):HjelpeMidlerRespons
}
