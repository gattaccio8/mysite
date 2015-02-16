import com.gargoylesoftware.htmlunit.BrowserVersion
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.selenium.HtmlUnit
import org.scalatest.{FunSuite, ShouldMatchers}

class JustATest extends FunSuite with ShouldMatchers with HtmlUnit with WebSpecification {

  val host = "http://localhost:8080/"
  val driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17)

  test("The app home page have the correct title") {
    driver.navigate().to(host + "index.html")
    assert(driver.getTitle === "mysite title")
  }

  test("have a h1 msg") {
    driver.navigate().to(host + "index.html")
    assert(driver.findElementById("helloText").getText === "Hello World!")
  }
}