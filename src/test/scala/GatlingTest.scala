import io.gatling.http.Predef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

/**
 * Created by kayipcheung on 03/10/2016.
 */
class GatlingTest extends Simulation {
    val httpProtocol = http.baseURL("http://www.naamnummercontrole.nl").userAgentHeader("Naam Nummer Controle!");

    val myFirstScenario = scenario("Naar de Naam Nummer Control site")
      .exec(
        http("Open the naamnummercontrol start page")
          .get("/")
          .check(status.is(200))
    )

    setUp(myFirstScenario.inject(rampUsers(1500) over (5 seconds)).protocols(httpProtocol))
}
