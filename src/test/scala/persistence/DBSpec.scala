package persistence

import com.gargoylesoftware.htmlunit.BrowserVersion
import infrastructure.WebSpecification
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.selenium.HtmlUnit
import org.scalatest._

class DBSpec extends FunSuite with MustMatchers with HtmlUnit with WebSpecification {

  val host = "http://localhost:8080/"
  val driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17)

  test("The app home page have the user name from db") {
    (pending)
    new PersistedUser().addUser
    driver.navigate().to(host + "index.html")
    assert(driver.findElementById("name").getText === "Paolo")
  }
}
