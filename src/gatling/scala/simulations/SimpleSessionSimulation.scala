package simulations


import objects._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._


class SimpleSessionSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://5.9.110.178")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, sdch")
    .acceptLanguageHeader("en,en-US;q=0.8,de-DE;q=0.6,de;q=0.4")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36")



  val auth = new Authentication()
  val tab = new Tab()

  val scn = scenario("Login Simulation")
    .exec(
      auth.Login.login,
      pause(4 seconds),
      tab.SelfService.visit,
      pause(4 seconds),
      tab.WorkList.visit,
      pause(4 seconds),
      tab.DashboardService.visit,
      pause(5 seconds),
      auth.Logout.logout
    )

  setUp(scn.inject(atOnceUsers(5)).protocols(httpProtocol))
/*  setUp(scn.inject(
    rampUsers(100) over(1 minutes),
    constantUsersPerSec(1) during(9 minutes)
    ).protocols(httpProtocol)
  )
*/
}