package objects

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

/**
  * Created by bste on 22.04.2016.
  */
class Authentication {

  val DEFAULT_PASSWORD = "Start123"

  object Login {

    val feeder = csv("simpleUsers.csv").random

    val headers = Map(
      "Cache-Control" -> "max-age=0",
      "Origin" -> "http://5.9.110.178",
      "Upgrade-Insecure-Requests" -> "1")

    val login =
      feed(feeder)
        .exec(
          http("Login Page")
            .get("/goIdentity/jsf/stdportal/startlogin.jsp")
            .headers(headers)
            .check(status.is(200))
        )
        .pause(1 second)
        .exec(
          http("User Login")
            .post("/goIdentity/jsf/1/stdportal/login.jsp?form:login=Log%20On")
            .formParam("form:username", "${userId}")
            .formParam("form:password", DEFAULT_PASSWORD)
            .formParam("form_SUBMIT", "1")
            .formParam("form:_link_hidden_", "")
            .headers(headers)
            .check(status.is(200))
            .check(regex("Welcome ${userId}").exists) // check if userId is contained in header
        )
  }

  object Logout {

    val headers = Map(
      "Connection" -> "keep-alive",
      "Upgrade-Insecure-Requests" -> "1")

    val logout =
      exec(http("Logout Request")
        .get("/goIdentity/jsf/2/DashBoardRead.jsp?formviews_SUBMIT=1&formviews:_link_hidden_=formviews:logout&jsf_sequence=1")
        .headers(headers)
        .check(status.is(200))
        .check(regex("Log On").exists)
      )
  }

}
