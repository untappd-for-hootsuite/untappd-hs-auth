package controllers

import play.api.mvc._

object Application extends Controller {

  def callback = Action { req =>
    val authToken = "not-set"

    Redirect("http://untappdforhootsuite.com", Map("authToken" -> Seq(authToken)))
  }
}