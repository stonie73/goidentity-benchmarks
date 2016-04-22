package objects

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by smue on 22.04.2016.
  */
class DashboardActions {

  val headers_0 = Map(
    "Origin" -> "http://5.9.110.178")

  val pressButtonAddRole =
    exec(http("Dashboard: press button 'Add a Role'")
    .post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:addARole=Add%20a%20Role")
    .headers(headers_0)
    .formParam("aForm0_SUBMIT", "1")
    .formParam("aForm0%3A_link_hidden_", ""))

  val pressButtonRunningWorkflows =
    exec(http("Dashboard: press button 'Running Workflows'")
      .post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:runningTab=Running%20Workflows")
      .headers(headers_0)
      .formParam("aForm0_SUBMIT", "1")
      .formParam("aForm0%3A_link_hidden_", ""))

  val pressButtonFinishedWorkflows =
    exec(http("Dashboard: press button 'Finished Workflows'")
      .post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:finishedTab=Finished%20Workflows")
      .headers(headers_0)
      .formParam("aForm0_SUBMIT", "1")
      .formParam("aForm0%3A_link_hidden_", ""))

  val pressButtonAllWorkflows =
    exec(http("Dashboard: press button 'All Workflows'")
      .post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:allTab=All%20Workflows")
      .headers(headers_0)
      .formParam("aForm0_SUBMIT", "1")
      .formParam("aForm0%3A_link_hidden_", ""))
}
