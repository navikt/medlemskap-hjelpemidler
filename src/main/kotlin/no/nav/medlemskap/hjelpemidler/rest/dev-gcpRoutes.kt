package no.nav.medlemskap.hjelpemidler.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import no.nav.medlemskap.hjelpemidler.clients.AzureAdClient
import no.nav.medlemskap.hjelpemidler.config.Configuration

fun Routing.devgcpRoutes() {
    get("/token") {
        try {
            val client = AzureAdClient(Configuration())

            call.respondText(client.hentTokenScopetMotSelf().token, ContentType.Text.Plain, HttpStatusCode.OK)
        }
        catch (e:Exception){
            call.respond(status = HttpStatusCode.InternalServerError,e.message!!)
        }

    }

}