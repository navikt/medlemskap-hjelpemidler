package no.nav.medlemskap.hjelpemidler.domain

import no.nav.medlemskap.hjelpemidler.clients.medlemskapoppslag.Brukerinput
import no.nav.medlemskap.hjelpemidler.clients.medlemskapoppslag.MedlOppslagRequest
import no.nav.medlemskap.hjelpemidler.clients.medlemskapoppslag.Periode
import java.time.LocalDate

class RequestMapper {
    fun mapHjelpemidlerRequestToLovmeRequest(hjelpeMidlerRequest: HjelpeMidlerRequest):MedlOppslagRequest {
        //TODO: Dette må avklares og endres i fremtiden. Ikke gyldig logikk på lang sikt
        val førsteDagForYtelse = LocalDate.now().toString()
        return MedlOppslagRequest(
            fnr = hjelpeMidlerRequest.fnr,
            førsteDagForYtelse = førsteDagForYtelse,
            periode = Periode(førsteDagForYtelse),
            brukerinput = Brukerinput(false)
        )
    }
}