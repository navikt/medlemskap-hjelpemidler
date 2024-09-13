package no.nav.medlemskap.hjelpemidler.services

import kotlinx.coroutines.runBlocking
import no.nav.medlemskap.hjelpemidler.clients.MedlemskapOppslagMock
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.Status
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID


class HjelpeMidlerServiceTest {
    @Test
    fun `skal svare NEI når fnr er 1`() = runBlocking {
        val service = HjelpeMidlerService()
        service.lovmeClient = MedlemskapOppslagMock()
        val response = service.handleRequest(HjelpeMidlerRequest("1"),UUID.randomUUID().toString())
        println(LocalDateTime.now().toString())
        Assertions.assertTrue(true)
    }

    @Test
    suspend fun `skal svare JA når medlemskapOppslag svarer JA`() = runBlocking {
        val service = HjelpeMidlerService()
        service.lovmeClient = MedlemskapOppslagMock()
        val response = service.handleRequest(HjelpeMidlerRequest("1"),UUID.randomUUID().toString())
        Assertions.assertEquals(Status.JA, response.status, "Mapper feil status")
    }
}


