import org.scalatest.selenium.HtmlUnit
import org.scalatest.{FunSuite, ShouldMatchers}

class JustATest extends FunSuite with ShouldMatchers with HtmlUnit with WebSpecification {

  val host = "http://localhost:8080/"

  test("The app home page have the correct title") {
    go to (host + "index.html")
    assert(pageTitle === "mysite title")
  }

  test("have a h1 msg") {
    go to (host + "index.html")
    assert(id("helloText").element.isDisplayed)
  }
}