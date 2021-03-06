package objects

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by smue on 22.04.2016.
  */
class Dashboard {

  val RoleNameSearchCriteria = "IDM+Identity+Activation+Approver"

  val sucessfullApply = "The data of the process were saved successfully"

  val headers_0 = Map(
    "Origin" -> "http://5.9.110.178")

  object Tab {
    val open = exec(
      http("Dashboard: Open Tab")
        .get("/goIdentity/jsf/2/DashBoardRead.jsp?formviews_SUBMIT=1&formviews:_link_hidden_=formviews:views_0:gotoview&jsf_sequence=1")
        .headers(headers_0)
        .check(substring("Overview"))
    )
  }

  val pressButtonAddRole =
    exec(http("Dashboard: press button 'Add a Role'")
    .post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:addARole=Add%20a%20Role")
    .headers(headers_0)
    .formParam("aForm0_SUBMIT", "1")
    .formParam("aForm0:_link_hidden_", ""))

  val pressButtonRunningWorkflows =
    exec(http("Dashboard: press button 'Running Workflows'")
      .post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:runningTab=Running%20Workflows")
      .headers(headers_0)
      .formParam("aForm0_SUBMIT", "1")
      .formParam("aForm0:_link_hidden_", ""))

  val pressButtonFinishedWorkflows =
    exec(http("Dashboard: press button 'Finished Workflows'")
      .post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:finishedTab=Finished%20Workflows")
      .headers(headers_0)
      .formParam("aForm0_SUBMIT", "1")
      .formParam("aForm0:_link_hidden_", ""))

  val pressButtonAllWorkflows =
    exec(http("Dashboard: press button 'All Workflows'")
      .post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:allTab=All%20Workflows")
      .headers(headers_0)
      .formParam("aForm0_SUBMIT", "1")
      .formParam("aForm0:_link_hidden_", ""))

  object RoleActions {

    val searchForRoleByName =
      exec(http("Dashboard - Apply for Role: Search Role by Name")
      .post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:searchRoles=Search")
      .headers(headers_0)
      .formParam("form:cnRoleCriteria", RoleNameSearchCriteria)
      .formParam("form:descriptionRoleCriteria", "")
      .formParam("form:itcApprovalRequiredRoleCriteria", "")
      .formParam("form:criteriaRoleCategory", "")
      .formParam("form_SUBMIT", "1")
      .formParam("form:_link_hidden_", ""))

     val chooseRole =
      exec(http("Dashboard - Apply for Role: Choose Role")
          .post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:dt02_0:choose2=Choose")
          .headers(headers_0)
          .formParam("form:cnRoleCriteria", RoleNameSearchCriteria)
          .formParam("form:descriptionRoleCriteria", "")
          .formParam("form:itcApprovalRequiredRoleCriteria", "")
          .formParam("form:criteriaRoleCategory", "")
          .formParam("form:PageChooserRole", "")
          .formParam("form:dt02:sheaderCategory", "")
          .formParam("form:dt02:scn", "")
          .formParam("form:dt02:sdescription", "")
          .formParam("form:dt02:sitcApprovalRequired", "")
          .formParam("form:dt02:sitcRoleResponsible", "")
          .formParam("form:expireDate", "")
          .formParam("form_SUBMIT", "1")
          .formParam("form:_link_hidden_", ""))

     val enterDateAndNext =
      exec(http("Dashboard - Apply for Role: Enter Date and Next")
      .post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:wizardApplyForRoleSelfService_next=Next")
      .headers(headers_0)
      .formParam("form:cnRoleCriteria", RoleNameSearchCriteria)
      .formParam("form:descriptionRoleCriteria", "")
      .formParam("form:itcApprovalRequiredRoleCriteria", "")
      .formParam("form:criteriaRoleCategory", "")
      .formParam("form:PageChooserRole", "")
      .formParam("form:dt02:sheaderCategory", "")
      .formParam("form:dt02:scn", "")
      .formParam("form:dt02:sdescription", "")
      .formParam("form:dt02:sitcApprovalRequired", "")
      .formParam("form:dt02:sitcRoleResponsible", "")
      .formParam("form:expireDate", "4%2F22%2F17")
      .formParam("form_SUBMIT", "1")
      .formParam("form:_link_hidden_", ""))

    val finishApply =
    exec(http("Dashboard - Apply for Role: Finish apply for Role without Approval")
      .post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:wizardApplyForRoleSelfService_finish=Apply")
      .headers(headers_0)
      .formParam("form_SUBMIT", "1")
      .formParam("form:_link_hidden_", "")
      .check(substring("The data of the process were saved successfully"))
    )
  }
}
