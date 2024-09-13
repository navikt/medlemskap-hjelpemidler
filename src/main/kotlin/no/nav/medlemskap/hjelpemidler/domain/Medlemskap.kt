package no.nav.medlemskap.hjelpemidler.domain

data class Medlemskap(
    val resultat:Resultat
)

data class Resultat(
    val svar:String,
    val årsaker :List<Arsak> = emptyList()
)
data class Arsak(
    val regelId : String,
    val avklaring : String,
    val svar: String,
    val begrunnelse:String
)