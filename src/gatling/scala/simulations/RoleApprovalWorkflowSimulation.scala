package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import objects._

import scala.concurrent.duration._


class RoleApprovalWorkflowSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://5.9.110.178")
    .inferHtmlResources(
      BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\.xsl"""),
      WhiteList()
    )
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en,en-US;q=0.8,de-DE;q=0.6,de;q=0.4")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36")


  val auth = new Authentication()
  val workflow = new ApplyForRoleWithApprovalWorkflow()

  val applyForRole = scenario("User Applies for Role")
    .exec(
      auth.Login.userLogin,
      pause(2 seconds, 5 seconds),

      workflow.applyForRole,
      pause(2 seconds, 5 seconds),

      auth.Logout.logout
    )

  val approveRole = scenario("Approver approves Role")
    .exec(
      auth.Login.approverLogin,
      pause(2 seconds, 5 seconds),


      //workflow.approveRole,
      pause(2 seconds, 5 seconds),

      auth.Logout.logout
    )

  setUp(
    applyForRole.inject(
      //atOnceUsers(1)
      constantUsersPerSec(0.1) during(10 minutes)
//    ),
//    approveRole.inject(
    //  atOnceUsers(1)
      //constantUsersPerSec(0.1) during(1 hour)
    )

  ).protocols(httpProtocol)

}
