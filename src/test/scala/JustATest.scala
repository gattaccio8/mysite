import org.scalatest.selenium.HtmlUnit
import org.scalatest.{ShouldMatchers, FlatSpec}
import webserver.WebServer

class JustATest extends FlatSpec with ShouldMatchers with HtmlUnit {

  val host = "http://localhost:8080/"

  "The app home page" should "have the correct title" in {
      WebServer
      go to (host + "index.html")
      pageTitle should be ("mysite title")
  }

  "it" should "have a h1 msg" in {
    WebServer
    go to (host + "index.html")
    id("helloText").element.isDisplayed
  }
}