package objects


import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ApplyForRoleWithApprovalWorkflow extends Simulation {

	val headers = Map(
		"Cache-Control" -> "max-age=0",
		"Origin" -> "http://5.9.110.178",
		"Upgrade-Insecure-Requests" -> "1")

  val uri1 = "http://5.9.110.178/goIdentity/jsf/2"

	val applyForRole =
		exec(http("Dashboard: Add a Role")
			.post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:addARole=Add%20a%20Role")
			.headers(headers)
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
			.headers(headers)
			.formParam("form:cnRoleCriteria", "role")
			.formParam("form:descriptionRoleCriteria", "")
			.formParam("form:itcApprovalRequiredRoleCriteria", "TRUE")
			.formParam("form:criteriaRoleCategory", "")
			.formParam("form_SUBMIT", "1")
			.formParam("form:_link_hidden_", "")
      .check(substring(" Result(s)")))
		.pause(2)
		.exec(http("Workflow: Apply for Role (choose)")
			.post("/goIdentity/jsf/2/workflow-custom/Dashboard/ApplyForRoleSelfService_3501_1/ApplyForRoleSelfService_3501_1Init.jsp?form:dt02_0:choose2=Choose")
			.headers(headers)
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
			.headers(headers)
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
			.headers(headers)
			.formParam("form_SUBMIT", "1")
			.formParam("form:_link_hidden_", "")
      .check(substring("The data of the process were saved successfully"))
    )

	val approveRole =
		exec(http("Select Task for Approval")
			.post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:dt02_0:workOnButton=Work%20On")
			.headers(headers)
			.formParam("aForm0:tasksResultPageChooser", "")
			.formParam("aForm0:dt02:s00", "")
			.formParam("aForm0:dt02:s01", "")
			.formParam("aForm0:dt02:s02", "")
			.formParam("aForm0:dt02:s03", "")
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
      .check(substring("Grant role"))
      .check(substring("to user"))
    )
    .pause(2)
    .exec(http("Approver: Approve Role (perform)")
      .post("/goIdentity/jsf/2/workflow-custom/ApplyForRoleSelfService_3501_1/DashboardApplyForRoleSelfService_3501_1_38Sumup.jsp?form:execute=Perform")
      .headers(headers)
      .formParam("form_SUBMIT", "1")
      .formParam("form:_link_hidden_", "")
      .check(substring("Explain your decision"))
    )
    .pause(2)
    .exec(http("Approver: Approve Role (approve)")
      .post("/goIdentity/jsf/2/workflow-custom/ApplyForRoleSelfService_3501_1/DashboardApplyForRoleSelfService_3501_1_38Act.jsp?form:terminate=Approve")
      .headers(headers)
      .formParam("form:expireDate", "")
      .formParam("form:dt0:s00", "")
      .formParam("form:terminateCommentArea", "")
      .formParam("form_SUBMIT", "1")
      .formParam("form:_link_hidden_", "")
      .check(substring("The data of the task were saved successfully"))
    )



	val approverReservesTask =
		exec(http("Approver: Select Task for Reservation")
			.post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:dt02_0:workOnButton=Work%20On")
			.headers(headers)
			.formParam("aForm0:tasksResultPageChooser", "")
			.formParam("aForm0:dt02:s00", "")
			.formParam("aForm0:dt02:s01", "")
			.formParam("aForm0:dt02:s02", "")
			.formParam("aForm0:dt02:s03", "")
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
		  .check(substring("form:reserve")))
		.pause(2)
		.exec(http("Approver: Reserve Task (reserve)")
			.post("/goIdentity/jsf/2/workflow-custom/ApplyForRoleSelfService_3501_1/DashboardApplyForRoleSelfService_3501_1_38Sumup.jsp?form:reserve=Reserve")
			.headers(headers)
			.formParam("form_SUBMIT", "1")
			.formParam("form:_link_hidden_", "")
		  .check(substring("You are about to reserve a task")))
		.pause(2)
		.exec(http("Approver: Reserve Task (confirm)")
			.post("/goIdentity/jsf/2/workflow-custom/ApplyForRoleSelfService_3501_1/DashboardApplyForRoleSelfService_3501_1_38Act.jsp?form:reserveConfirm=Confirm%20Reservation")
			.headers(headers)
			.formParam("form:reserveCommentArea", "")
			.formParam("form_SUBMIT", "1")
			.formParam("form:_link_hidden_", "")
		  .check(substring("The assignment of the task was modified successfully")))



	val approveReservedRole =
		exec(http("Approver: Select Reserved Task")
			.post("/goIdentity/jsf/2/DashBoardRead.jsp?aForm0:dt02_0:workOnButton=Work%20On")
			.headers(headers)
			.formParam("aForm0:tasksResultPageChooser", "")
			.formParam("aForm0:dt02:s00", "")
			.formParam("aForm0:dt02:s01", "")
			.formParam("aForm0:dt02:s02", "")
			.formParam("aForm0:dt02:s03", "")
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
		  .check(substring("form:execute")))
			.pause(2)
			.exec(http("Approver: Approve Role (perform)")
				.post("/goIdentity/jsf/2/workflow-custom/ApplyForRoleSelfService_3501_1/DashboardApplyForRoleSelfService_3501_1_38Sumup.jsp?form:execute=Perform")
				.headers(headers)
				.formParam("form:dt0:s00", "")
				.formParam("form_SUBMIT", "1")
				.formParam("form:_link_hidden_", "")
			  .check(substring("form:terminate")))
			.pause(2)
			.exec(http("Approver: Approve Role (approve)")
				.post("/goIdentity/jsf/2/workflow-custom/ApplyForRoleSelfService_3501_1/DashboardApplyForRoleSelfService_3501_1_38Act.jsp?form:terminate=Approve")
				.headers(headers)
				.formParam("form:expireDate", "")
				.formParam("form:dt0:s00", "")
				.formParam("form:terminateCommentArea", "")
				.formParam("form_SUBMIT", "1")
				.formParam("form:_link_hidden_", "")
			  .check(substring("The data of the task were saved successfully")))
}