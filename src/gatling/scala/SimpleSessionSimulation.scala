
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimpleSessionSimulation extends Simulation {

  val password = "Start123"

	val httpProtocol = http
		.baseURL("http://5.9.110.178")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("en,en-US;q=0.8,de-DE;q=0.6,de;q=0.4")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36")



  object LoginScreen {

    val headers = Map(
      "Cache-Control" -> "max-age=0",
      "Upgrade-Insecure-Requests" -> "1")

    val loginScreen =
      exec(
        http("Login Page")
        .get("/goIdentity/jsf/stdportal/startlogin.jsp")
        .headers(headers)
        .check(status.is(200))
      )
  }

  object LoginAction {

    val feeder = csv("simpleUsers.csv").random

    val headers = Map(
      "Cache-Control" -> "max-age=0",
      "Origin" -> "http://5.9.110.178",
      "Upgrade-Insecure-Requests" -> "1")

    val loginAction =
      feed(feeder).exec(
        http("User Login")
        .post("/goIdentity/jsf/1/stdportal/login.jsp?form:login=Log%20On")
        .formParam("form:username", "${userId}")
        .formParam("form:password", "Start123")
        .formParam("form_SUBMIT", "1")
        .formParam("form:_link_hidden_", "")
        .headers(headers)
        .check(status.is(200))
        .check(regex("Welcome ${userId}").exists) // check if userId is contained in header
      )
  }


	val scn = scenario("Login Simulation")
		.exec(
      LoginScreen.loginScreen,
      LoginAction.loginAction
    )

	setUp(scn.inject(rampUsersPerSec(1) to (5) during(3 minutes))).protocols(httpProtocol)
}