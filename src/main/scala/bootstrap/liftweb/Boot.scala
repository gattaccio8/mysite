package bootstrap.liftweb

import mysite.comet._
import net.liftweb.common._
import net.liftweb.http._
import net.liftweb.sitemap.Loc._
import net.liftweb.sitemap._

trait LiftPage {
  val title: String
  val url: String
  lazy val dispatch = url.split("/").toList
  def menu: Menu.Menuable
}

case class Page(title: String, pageName: String) extends LiftPage {
  val url = pageName
  def menu = Menu(S ? title) / pageName
}

object Pages {
  val mycomet = Page("title", "mycomet")
}

class Boot {
  import Pages._
  import view.CometActorView._

  def boot {
    LiftRules.addToPackages("mysite")

    val entries = SiteMap(
      Menu.i("Home") / "index",
      Menu.i("MyComet") / mycomet.pageName >> Hidden)

//      mycomet.menu >> LocGroup("main")
//      Menu(Loc("HomePage", "index" :: Nil, "Home Page", Hidden)) :: Nil

    LiftRules.viewDispatch.append {
      case mycomet.dispatch => Left(() => Full(cometActorView[MyComet]))
    }

    LiftRules.setSiteMap(entries)

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
