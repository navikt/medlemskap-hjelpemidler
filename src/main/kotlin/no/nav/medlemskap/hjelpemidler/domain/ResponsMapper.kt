package no.nav.medlemskap.hjelpemidler.domain

import no.nav.medlemskap.hjelpemidler.config.objectMapper
import kotlin.random.Random

class ResponsMapper {

    fun mapRegelResponsTilHjelpemidlerRespons(jsonString:String):HjelpeMidlerRespons{
        /*
        * Implement logic here to parse the reponse from Lovme and create a correct respons
        * */
        if (Random.nextBoolean()){
            return HjelpeMidlerRespons(Status.NEI)
        }
        return HjelpeMidlerRespons(Status.JA)
    }
}