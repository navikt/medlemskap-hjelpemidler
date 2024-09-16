package no.nav.medlemskap.hjelpemidler.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mu.KotlinLogging
import net.logstash.logback.argument.StructuredArguments
import no.nav.medlemskap.hjelpemidler.domain.GradertAdresseException
import no.nav.medlemskap.hjelpemidler.domain.HjelpeMidlerRequest
import no.nav.medlemskap.hjelpemidler.services.HjelpeMidlerService

import java.util.*
private val logger = KotlinLogging.logger { }
private val secureLogger = KotlinLogging.logger("tjenestekall")
fun Routing.hjelpemidlerRoutes(hjelpeMidlerService: HjelpeMidlerService) {
    authenticate("azureAuth") {
        post("/vurdering") {
            val callerPrincipal: JWTPrincipal = call.authentication.principal()!!
            val azp = callerPrincipal.payload.getClaim("azp").asString()
            secureLogger.info("EvalueringRoute: azp-claim i principal-token: {} ", azp)
            val callId = call.callId ?: UUID.randomUUID().toString()
            logger.info(
                "kall autentisert, url : /vurdering",
                StructuredArguments.kv("callId", callId),
                StructuredArguments.kv("endpoint", "vurdering")
            )
            val request = call.receive<HjelpeMidlerRequest>()
            try {

                val respons = hjelpeMidlerService.handleRequest(request,callId)
                call.respond(HttpStatusCode.OK,respons)
            }
            catch (g:GradertAdresseException){
                call.respond(HttpStatusCode.Forbidden, g.message!!)
            }
            catch (t: Throwable) {
                call.respond(HttpStatusCode.InternalServerError, t.message!!)
            }
        }
    }
}
