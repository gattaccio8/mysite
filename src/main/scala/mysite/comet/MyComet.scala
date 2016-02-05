package mysite.comet

import net.liftweb.http.CometActor

class MyComet extends CometActor {
  def render =
    <div class="row-fluid">
      Mycomet
    </div>

  override def lowPriority = {
    case _ => reRender()
  }

  override def localSetup {
    super.localSetup()
  }

  override def localShutdown {
    super.localShutdown()
  }
}


