package objects

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by bste on 22.04.2016.
  */
class SelfService {

  val headers = Map("Upgrade-Insecure-Requests" -> "1")

  object Tab {
    val open = exec(
      http("Self Service: Open Tab")
        .get("/goIdentity/jsf/2/DashBoardRead.jsp?formviews_SUBMIT=1&formviews:_link_hidden_=formviews:views_1:gotoview&jsf_sequence=1")
        .headers(headers)
        .check(substring("My Data"))
    )
  }

  object MenuMyData {
    val open = exec(
      http("Self Service: Open My Data")
        .get("/goIdentity/jsf/3/waforms/MyData/other/customMyDataRead.jsp?formforms_SUBMIT=1&formforms:_link_hidden_=formforms:f0_0:f1_0:formLink&jsf_sequence=1")
        .headers(headers)
        .check(substring("Personal Information"))
    )
  }

  object MenuSearchDirectory {
    val open = exec(
      http("Self Service: Open Search Directory")
        .get("/goIdentity/jsf/3/waforms/MyData/other/customMyDataRead.jsp?formforms_SUBMIT=1&formforms:_link_hidden_=formforms:f0_0:f1_1:formLink&jsf_sequence=1")
        .headers(headers)
        .check(substring("Directory Search"))
    )
  }

  object MenuMyRoles {
    val open = exec(
      http("Self Service: Open My Roles")
        .get("/goIdentity/jsf/3/waforms/ActiveUsers/other/customSearchActiveUsersSelfService.jsp?formforms_SUBMIT=1&formforms:_link_hidden_=formforms:f0_0:f1_2:formLink&jsf_sequence=1")
        .headers(headers)
        .check(substring("<title>My Roles</title>"))
    )
  }

  object MenuMyDelegations {
    val open = exec(
      http("Self Service: Open My Delegations")
        .get("/goIdentity/jsf/3/waforms/MyRoles/other/customMyRolesSearch.jsp?formforms_SUBMIT=1&formforms:_link_hidden_=formforms:f0_0:f1_3:formLink&jsf_sequence=1")
        .headers(headers)
        .check(substring("<title>My Delegations</title>"))
    )
  }

}
