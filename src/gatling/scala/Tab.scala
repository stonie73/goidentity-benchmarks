import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by bste on 22.04.2016.
  */
class Tab {

  val headers = Map("Upgrade-Insecure-Requests" -> "1")

  object SelfService {
    val visit = exec(
      http("Visit Tab: Self Service")
        .get("/goIdentity/jsf/2/DashBoardRead.jsp?formviews_SUBMIT=1&formviews:_link_hidden_=formviews:views_1:gotoview&jsf_sequence=1")
        .headers(headers)
    )
  }

  object DashboardService {
    val visit = exec(
      http("Visit Tab: Dashboard")
        .get("/goIdentity/jsf/2/DashBoardRead.jsp?formviews_SUBMIT=1&formviews:_link_hidden_=formviews:views_0:gotoview&jsf_sequence=1")
        .headers(headers)
    )
  }

  object WorkList {
    val visit = exec(
      http("Visit Tab: Work List")
        .get("/goIdentity/jsf/2/DashBoardRead.jsp?formviews_SUBMIT=1&formviews:_link_hidden_=formviews:views_2:gotoview&jsf_sequence=1")
        .headers(headers)
    )
  }
}
