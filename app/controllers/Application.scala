package controllers

import play.api.Play
import play.api.Play.current
import play.api.mvc._
import play.api.libs.ws._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

  def callback(code: String) = Action.async { req =>
    def getConfigString: String => String = Play.application.configuration.getString(_).getOrElse("")

    val clientId = getConfigString("untappd.client_id")
    val clientSecret = getConfigString("untappd.client_secret")
    val redirectUrl = getConfigString("application.hostname") + "/callback"

    // Exchange code for token
    val resp: Future[WSResponse] = WS.url("http://untappd.com/oauth/authorize/")
      .withQueryString(
        "client_id" -> clientId,
        "client_secret" -> clientSecret,
        "response_type" -> "code",
        "redirect_url" -> redirectUrl,
        "code" -> code
      )
      .get()

    resp.map { response =>
      val token = ( response.json \ "response" \ "access_token" ).as[String]

      Redirect("http://untappdforhootsuite.com", Map("receivedCode" -> Seq(code), "token" -> Seq(token)))
    }
  }
}