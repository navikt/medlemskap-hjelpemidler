package no.nav.medlemskap.hjelpemidler

import no.nav.medlemskap.hjelpemidler.config.Environment
import no.nav.medlemskap.hjelpemidler.rest.createHttpServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory


fun main() {
    Application().start()
}

class Application(private val env: Environment = System.getenv()) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(Application::class.java)
    }

    fun start() {
        log.info("Start application")

        createHttpServer().start(wait = true)
    }
}