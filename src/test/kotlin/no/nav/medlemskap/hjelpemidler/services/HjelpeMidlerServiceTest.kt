package no.nav.medlemskap.hjelpemidler.services

import kotlinx.coroutines.runBlocking
import no.nav.medlemskap.hjelpemidler.clients.MedlemskapOppslagGradertAdresseException
import no.nav.medlemskap.hjelpemidler.clients.MedlemskapOppslagMock
import no.nav.medlemskap.hjelpemidler.clients.MedlemskapOppslagTimeOutMock
import no.nav.medlemskap.hjelpemidler.domain.GradertAdresseException
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.domain.Status
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.time.LocalDateTime
import java.util.*


class HjelpeMidlerServiceTest {
    @Test()
    fun `skal kaste GradertAdresseException når vi regelmotor kaster graderty Adresse`() = runBlocking {
        val service = HjelpeMidlerService()
        service.lovmeClient = MedlemskapOppslagGradertAdresseException()
        try{
            val response = service.handleRequest(HjelpeMidlerRequest("1"),UUID.randomUUID().toString())
            fail("Exception skal kastes i dette scenario")
        }
        catch (g:GradertAdresseException){

        }
        catch (t:Throwable){
            fail("feil exception bli kastet")
        }


        println(LocalDateTime.now().toString())
        Assertions.assertTrue(true)
    }
    @Test()
    fun `skal kaste Exception når vi regelmotor kaster timeout`() = runBlocking {
        val service = HjelpeMidlerService()
        service.lovmeClient = MedlemskapOppslagTimeOutMock()
        try{
            val response = service.handleRequest(HjelpeMidlerRequest("1"),UUID.randomUUID().toString())
            fail("Exception skal kastes i dette scenario")
        }
        catch (g:GradertAdresseException){
            fail("feil exception bli kastet")
        }
        catch (e:Exception){
        Assertions.assertTrue(e.message!!.contains("Teknisk feil ved kall mot Lovme"))
        }

    }

    @Test
    suspend fun `skal svare JA når medlemskapOppslag svarer JA`() = runBlocking {
        val service = HjelpeMidlerService()
        service.lovmeClient = MedlemskapOppslagMock()
        val response = service.handleRequest(HjelpeMidlerRequest("1"),UUID.randomUUID().toString())
        Assertions.assertEquals(Status.JA, response.status, "Mapper feil status")
    }
}


