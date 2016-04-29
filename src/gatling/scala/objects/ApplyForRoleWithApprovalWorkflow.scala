package objects


import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ApplyForRoleWithApprovalWorkflow extends Simulation {

	val headers_1 = Map(
		"Cache-Control" -> "max-age=0",
		"Origin" -> "http://5.9.110.178",
		"Upgrade-Insecure-Requests" -> "1")

  val uri1 = "http://5.9.110.178/goIdentity/jsf/2"

	val applyForRole =
		exec(http("Dashboard: Add a Role")
			.post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:addARole=Add%20a%20Role")
			.headers(headers_1)
			.formParam("aForm0:aPageChooser", "")
			.formParam("aForm0:dt0:scn", "")
			.formParam("aForm0:dt0:sdescription", "")
			.formParam("aForm0:dt0:sitcRoleResponsible", "")
			.formParam("aForm0:runningPageChooser", "")
			.formParam("aForm0:runningWorkflowsTable:srunningprocessinstancefriendlyName", "")
			.formParam("aForm0:runningWorkflowsTable:sprocessinstanceid22", "")
			.formParam("aForm0:runningWorkflowsTable:sprocessinstanceid223", "")
			.formParam("aForm0_SUBMIT", "1")
			.formParam("aForm0:_link_hidden_", "")
      .check(substring("Please select the role you would like to apply for")))
		.pause(2)
		.exec(http("Workflow: Search Role (approval required)")
			.post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:searchRoles=Search")
			.headers(headers_1)
			.formParam("form:cnRoleCriteria", "role")
			.formParam("form:descriptionRoleCriteria", "")
			.formParam("form:itcApprovalRequiredRoleCriteria", "TRUE")
			.formParam("form:criteriaRoleCategory", "")
			.formParam("form_SUBMIT", "1")
			.formParam("form:_link_hidden_", "")
      .check(substring("1000 Result(s)")))
		.pause(2)
		.exec(http("Workflow: Apply for Role (choose)")
			.post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:dt02_0:choose2=Choose")
			.headers(headers_1)
			.formParam("form:cnRoleCriteria", "role")
			.formParam("form:descriptionRoleCriteria", "")
			.formParam("form:itcApprovalRequiredRoleCriteria", "TRUE")
			.formParam("form:criteriaRoleCategory", "")
			.formParam("form:PageChooserRole", "")
			.formParam("form:dt02:sheaderCategory", "")
			.formParam("form:dt02:scn", "")
			.formParam("form:dt02:sdescription", "")
			.formParam("form:dt02:sitcApprovalRequired", "")
			.formParam("form:dt02:sitcRoleResponsible", "")
			.formParam("form_SUBMIT", "1")
			.formParam("form:_link_hidden_", "")
      .check(substring("Selected Role")))
		.pause(2)
		.exec(http("Workflow: Apply for Role (next)")
			.post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:wizardApplyForRoleSelfService_next=Next")
			.headers(headers_1)
			.formParam("form:cnRoleCriteria", "role")
			.formParam("form:descriptionRoleCriteria", "")
			.formParam("form:itcApprovalRequiredRoleCriteria", "TRUE")
			.formParam("form:criteriaRoleCategory", "")
			.formParam("form:PageChooserRole", "")
			.formParam("form:dt02:sheaderCategory", "")
			.formParam("form:dt02:scn", "")
			.formParam("form:dt02:sdescription", "")
			.formParam("form:dt02:sitcApprovalRequired", "")
			.formParam("form:dt02:sitcRoleResponsible", "")
			.formParam("form:expireDate", "")
			.formParam("form_SUBMIT", "1")
			.formParam("form:_link_hidden_", "")
      .check(substring("Apply for the role '")))
		.pause(5)
		.exec(http("Workflow: Apply for Role (finish)")
			.post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:wizardApplyForRoleSelfService_finish=Apply")
			.headers(headers_1)
			.formParam("form_SUBMIT", "1")
			.formParam("form:_link_hidden_", "")
      .check(substring("The data of the process were saved successfully"))
    )
}