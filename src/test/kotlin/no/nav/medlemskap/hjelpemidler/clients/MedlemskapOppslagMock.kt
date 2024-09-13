package no.nav.medlemskap.hjelpemidler.clients

import no.nav.medlemskap.hjelpemidler.clients.medlemskapoppslag.MedlOppslagRequest

class MedlemskapOppslagMock():LovemeAPI {

    override suspend fun vurderMedlemskap(medlOppslagRequest: MedlOppslagRequest, callId: String): String {
        val fileContent = this::class.java.classLoader.getResource("sampleVurdering.json").readText(Charsets.UTF_8)
        return fileContent
    }
}