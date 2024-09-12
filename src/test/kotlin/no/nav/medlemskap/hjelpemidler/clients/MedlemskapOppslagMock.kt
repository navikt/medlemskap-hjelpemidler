package no.nav.medlemskap.hjelpemidler.clients

class MedlemskapOppslagMock():LovemeAPI {
    override fun utforVurderingForFnr(fnr: String): String {
        val fileContent = this::class.java.classLoader.getResource("sampleVurdering.json").readText(Charsets.UTF_8)
        return fileContent
    }
}