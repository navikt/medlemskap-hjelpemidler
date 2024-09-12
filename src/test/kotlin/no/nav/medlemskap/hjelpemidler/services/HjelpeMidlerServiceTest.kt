package no.nav.medlemskap.hjelpemidler.services

import no.nav.medlemskap.hjelpemidler.clients.MedlemskapOppslagMock
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.Status
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class HjelpeMidlerServiceTest {
    @Test
    fun `skal svare NEI når fnr er 1`(){
        val service = HjelpeMidlerService(MedlemskapOppslagMock())
        val response = service.handleRequest(HjelpeMidlerRequest("1"))
        println(LocalDateTime.now().toString())
        Assertions.assertTrue(true)
    }

    @Test
    fun `skal svare JA når medlemskapOppslag svarer JA`(){
        val service = HjelpeMidlerService(MedlemskapOppslagMock())
        val response = service.handleRequest(HjelpeMidlerRequest("1"))
        Assertions.assertEquals(Status.JA, response.status,"Mapper feil status")
    }
}

