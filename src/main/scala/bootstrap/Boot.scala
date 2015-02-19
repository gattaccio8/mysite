package bootstrap

import mysite.comet.MyComet
import net.liftweb._
import common._
import http._
import sitemap._
import Loc._

class Boot {
  def boot {
    LiftRules.addToPackages("mysite")

    val entries = Menu(Loc("HomePage", "index" :: Nil, "Home Page", Hidden)) :: Nil

    LiftRules.viewDispatch.append {
      case List("MyComet", "mycomet") => Left(() => Full(<div>hello comet</div>))
    }

    LiftRules.setSiteMap(SiteMap(entries: _*))
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts // Use jQuery 1.4

    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd) //Show the spinny image when an Ajax call starts

    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd) // Make the spinny image go away when it ends

    LiftRules.early.append(_.setCharacterEncoding("UTF-8")) // Force the request to be UTF-8

//    LiftRules.loggedInTest = Full(() => User.loggedIn_?) // What is the function to test if a user is logged in?

    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))               // Use HTML5 for rendering

    LiftRules.xhtmlValidator = Full(StrictXHTML1_0Validator) //validate the xhtml a displays error on browser if something wrong
  }
}
