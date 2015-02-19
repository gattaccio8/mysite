package mysite.comet

import net.liftweb.http.CometActor


class MyComet extends CometActor {
  override def render =
    <div class="row-fluid">
      Mycomet
    </div>

}


