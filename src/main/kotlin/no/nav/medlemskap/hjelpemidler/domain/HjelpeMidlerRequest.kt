package no.nav.medlemskap.hjelpemidler.domain

data class HjelpeMidlerRequest (val fnr:String)

data class HjelpeMidlerRespons (val fnr:String,val status: Status)

enum class Status{
    JA,
    NEI,
    UAVKLART
}