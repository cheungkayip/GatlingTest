package test.scala

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

/**
  * Created by kayipcheung on 03/10/2016.
  */
class GatlingTest extends Simulation {
  val httpProtocol = http.baseURL("https://www.naamnummercontrole.nl").userAgentHeader("Naam Nummer Controle")

  val myFirstScenario = scenario("Check bank account")
    .exec(
      http("Open the Naam Nummer controle")
        .get("/")
        .check(status.is(200))
    )
    .pause(1)
    .exec(
      http("Perform a iban check action'")
        .post("/api/iban/check/NAAMNUMMERCONTROLE%20BV/NL25INGB0007251387/z9qCFQ05ii")
        .check(status.is(200))
    )

  setUp(
    myFirstScenario.inject(rampUsers(10000) over (10 seconds)).protocols(httpProtocol))
}
