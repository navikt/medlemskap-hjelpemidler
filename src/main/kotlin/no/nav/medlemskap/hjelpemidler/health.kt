package no.nav.medlemskap.hjelpemidler

data class Componenthealth(val status: Status, val component:String, val url:String, val info:String)
data class Health (val components:List<Componenthealth>)
enum class Status{
    UP,
    DOWN

}