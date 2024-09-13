package no.nav.medlemskap.hjelpemidler.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ResponsMapperTest {

    @Test
    fun mapJaRespons(){
        val fileContent = this::class.java.classLoader.getResource("sampleVurdering.json").readText(Charsets.UTF_8)
        val respons = ResponsMapper().mapRegelResponsTilHjelpemidlerRespons(fileContent)
        Assertions.assertEquals(Status.JA,respons.status,"status i Lovme Respons mappes feil")
    }
    @Test
    fun mapUavklartRespons(){
        val fileContent = this::class.java.classLoader.getResource("sampleVurdering_uavklart.json").readText(Charsets.UTF_8)
        val respons = ResponsMapper().mapRegelResponsTilHjelpemidlerRespons(fileContent)
        Assertions.assertEquals(Status.UAVKLART,respons.status,"status i Lovme Respons mappes feil")
    }
}