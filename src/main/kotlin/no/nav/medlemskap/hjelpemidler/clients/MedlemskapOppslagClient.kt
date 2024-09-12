package no.nav.medlemskap.hjelpemidler.clients

import io.github.resilience4j.retry.Retry
import io.ktor.client.*

class MedlemskapOppslagClient():LovemeAPI{

    override fun utforVurderingForFnr(fnr:String):String{
        return ""
    }

}

interface LovemeAPI{
    fun utforVurderingForFnr(fnr:String):String
}