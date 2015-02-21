package view

import scala.reflect.ClassTag

object CometActorView {
  def cometActorView[T: ClassTag] = {
    val name = implicitly[ClassTag[T]].runtimeClass.getSimpleName
    val pageName = name.replace("CometActor", "")
//    DurationTracker.trackView(pageName, () => <div><lift:comet type={name}></lift:comet><div style="clear:both"></div></div>)
    <div><lift:comet type={name}/><div style="clear:both"/></div>
  }
    }
