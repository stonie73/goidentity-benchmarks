package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import objects._

import scala.concurrent.duration._


class SingleLongRunningSessionSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://5.9.110.178")
    .inferHtmlResources(
      BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""),
      WhiteList()
    )
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, sdch")
    .acceptLanguageHeader("en,en-US;q=0.8,de-DE;q=0.6,de;q=0.4")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36")

// https://github.com/major/MySQLTuner-perl/archive/master.zip

  val auth = new Authentication()
  val selfService = new SelfService()
  val dashboard = new Dashboard()

  val scn = scenario("Long Running Session")
    .exec(
      auth.Login.userLogin,
      pause(2 seconds),
      selfService.Tab.open,
      pause(2 seconds)
    )
    .during(10 minutes) {
      exec(
        selfService.MenuMyData.open,
        pause(2 seconds),
        selfService.MenuSearchDirectory.open,
        pause(2 seconds),
        selfService.MenuMyRoles.open,
        pause(2 seconds)
      )
    }
    .exec(
      auth.Logout.logout
    )

  setUp(
    scn.inject(
      atOnceUsers(1)
      //constantUsersPerSec(0.5) during(1 minutes)
    )
    .protocols(httpProtocol)
  )

}