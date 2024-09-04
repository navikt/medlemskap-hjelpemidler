package no.nav.medlemskap.hjelpemidler

import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.PrometheusMeterRegistry
import io.prometheus.client.exporter.common.TextFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.Writer

fun naisLiveness() = embeddedServer(Netty, applicationEngineEnvironment {
    connector { port = 8080 }
    module {

        install(MicrometerMetrics) {
            registry = Metrics.registry
        }

        routing {
            get("/isAlive") {
                    call.respondText("isAlive", ContentType.Text.Plain, HttpStatusCode.OK)
            }

            get("/isReady") {
                call.respondText("Ready!", ContentType.Text.Plain, HttpStatusCode.OK)
            }
            get("/metrics") {
                call.respondTextWriter(ContentType.parse(TextFormat.CONTENT_TYPE_004)) {
                    writeMetrics004(this, Metrics.registry)
                }
            }
        }
    }
})

suspend fun writeMetrics004(writer: Writer, registry: PrometheusMeterRegistry) {
    withContext(Dispatchers.IO) {
        kotlin.runCatching {
            TextFormat.write004(writer, registry.prometheusRegistry.metricFamilySamples())
        }
    }
}

