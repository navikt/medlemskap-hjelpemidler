package no.nav.medlemskap.hjelpemidler.domain

data class HjelpeMidlerRequest (val fnr:String)

data class HjelpeMidlerRespons (val status: Status)

enum class Status{
    JA,
    NEI,
    UAVKLART
}