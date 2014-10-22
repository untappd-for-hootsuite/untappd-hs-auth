package controllers

import play.api.mvc._

object Application extends Controller {

  def callback(code: String) = Action { req =>
    Redirect("http://untappdforhootsuite.com", Map("receivedCode" -> Seq(code)))
  }
}