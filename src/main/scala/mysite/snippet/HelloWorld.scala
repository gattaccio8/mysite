package mysite.snippet

import persistence.PersistedUser
import net.liftweb.util.BindHelpers._

class HelloWorld {
  def render =
    "#time" #> <span id="time">{new java.util.Date().toString}</span> &
//      "#firstname" #> <span id="firstname">{new PersistedUser().findUserName("name")}</span>
      "#firstname" #> <span id="firstname">Some data</span>
}
