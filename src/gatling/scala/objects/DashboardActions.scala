package objects

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by smue on 22.04.2016.
  */
class DashboardActions {

  val RoleNameSearchCriteria = "IDM+Identity+Activation+Approver"

  val sucessfullApply = "The data of the process were saved successfully"

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

  object RoleActions {

    val searchForRoleByName =
      exec(http("Dashboard - Apply for Role: Search Role by Name")
      .post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:searchRoles=Search")
      .headers(headers_0)
      .formParam("form%3AcnRoleCriteria", RoleNameSearchCriteria)
      .formParam("form%3AdescriptionRoleCriteria", "")
      .formParam("form%3AitcApprovalRequiredRoleCriteria", "")
      .formParam("form%3AcriteriaRoleCategory", "")
      .formParam("form_SUBMIT", "1")
      .formParam("form%3A_link_hidden_", ""))

     val chooseRole =
      exec(http("Dashboard - Apply for Role: Choose Role")
          .post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:dt02_0:choose2=Choose")
          .headers(headers_0)
          .formParam("form%3AcnRoleCriteria", RoleNameSearchCriteria)
          .formParam("form%3AdescriptionRoleCriteria", "")
          .formParam("form%3AitcApprovalRequiredRoleCriteria", "")
          .formParam("form%3AcriteriaRoleCategory", "")
          .formParam("form%3APageChooserRole", "")
          .formParam("form%3Adt02%3AsheaderCategory", "")
          .formParam("form%3Adt02%3Ascn", "")
          .formParam("form%3Adt02%3Asdescription", "")
          .formParam("form%3Adt02%3AsitcApprovalRequired", "")
          .formParam("form%3Adt02%3AsitcRoleResponsible", "")
          .formParam("form%3AexpireDate", "")
          .formParam("form_SUBMIT", "1")
          .formParam("form%3A_link_hidden_", ""))

     val enterDateAndNext =
      exec(http("Dashboard - Apply for Role: Enter Date and Next")
      .post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:wizardApplyForRoleSelfService_next=Next")
      .headers(headers_0)
      .formParam("form%3AcnRoleCriteria", RoleNameSearchCriteria)
      .formParam("form%3AdescriptionRoleCriteria", "")
      .formParam("form%3AitcApprovalRequiredRoleCriteria", "")
      .formParam("form%3AcriteriaRoleCategory", "")
      .formParam("form%3APageChooserRole", "")
      .formParam("form%3Adt02%3AsheaderCategory", "")
      .formParam("form%3Adt02%3Ascn", "")
      .formParam("form%3Adt02%3Asdescription", "")
      .formParam("form%3Adt02%3AsitcApprovalRequired", "")
      .formParam("form%3Adt02%3AsitcRoleResponsible", "")
      .formParam("form%3AexpireDate", "4%2F22%2F17")
      .formParam("form_SUBMIT", "1")
      .formParam("form%3A_link_hidden_", ""))

    val finishApply =
    exec(http("Dashboard - Apply for Role: Finish apply for Role without Approval")
      .post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:wizardApplyForRoleSelfService_finish=Apply")
      .headers(headers_0)
      .formParam("form_SUBMIT", "1")
      .formParam("form%3A_link_hidden_", ""))
  }
}
