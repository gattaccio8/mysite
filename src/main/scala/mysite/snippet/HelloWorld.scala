package mysite.snippet

import net.liftweb.util._
import Helpers._

class HelloWorld {
  // replace the contents of the element with id "time" with the date
  def render = "#time *" #> new java.util.Date().toString
}
