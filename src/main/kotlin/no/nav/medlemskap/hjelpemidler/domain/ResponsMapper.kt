package no.nav.medlemskap.hjelpemidler.domain

import no.nav.medlemskap.hjelpemidler.json.JacksonParser
import java.lang.UnsupportedOperationException


class ResponsMapper {

    fun mapRegelResponsTilHjelpemidlerRespons(jsonString:String):HjelpeMidlerRespons{
        /*
        * Implement logic here to parse the reponse from Lovme and create a correct respons
        * */
        return mapToHjelpemidlerRespons(jsonString)
    }

    private fun mapToHjelpemidlerRespons(jsonString:String):HjelpeMidlerRespons{
        val medlemskap = JacksonParser().parseMedlemskap(jsonString)
        when (medlemskap.resultat.svar){
            "JA" -> return HjelpeMidlerRespons(Status.JA)
            "NEI"->return HjelpeMidlerRespons(Status.NEI)
            "UAVKLART"->return HjelpeMidlerRespons(Status.UAVKLART)
        }
        throw UnsupportedOperationException()
    }
}