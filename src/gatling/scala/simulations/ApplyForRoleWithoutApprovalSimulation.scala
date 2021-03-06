package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import objects._

import scala.concurrent.duration._


class ApplyForRoleWithoutApprovalSimulation extends Simulation {

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


  val auth = new Authentication()
  val dashboard = new Dashboard()

  val scn = scenario("Apply for Role without Approval")
    .exec(
      auth.Login.emooreLogin,
      pause(1 seconds),
      dashboard.pressButtonAddRole,
      pause(1 seconds),
      dashboard.RoleActions.searchForRoleByName,
      pause(1 seconds),
      dashboard.RoleActions.chooseRole,
      pause(1 seconds),
      dashboard.RoleActions.enterDateAndNext,
      pause(4 seconds),
      dashboard.RoleActions.finishApply,
      pause(1 minutes),
      auth.Logout.logout
    )

  setUp(scn.inject(
    atOnceUsers(1)
    ).protocols(httpProtocol)
  )

}