package no.nav.medlemskap.hjelpemidler.domain

import no.nav.medlemskap.hjelpemidler.json.JacksonParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RequestMapperTest {

@Test
fun mappiungAvRequestMappesKorrekt(){
    val request = HjelpeMidlerRequest(fnr = "12345678901")
    val respons = RequestMapper().mapHjelpemidlerRequestToLovmeRequest(request)
    println(JacksonParser().parse(respons))
    Assertions.assertEquals(request.fnr,respons.fnr,"fnr mappet feil")
    Assertions.assertEquals(LocalDate.now().toString(),respons.førsteDagForYtelse,"fnr mappet feil")

}

}