package webserver

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.webapp.WebAppContext
import java.io.File

object WebServer extends App {

  val serverPort = 8080
  val productionMode = "production"
  private val server = createServer
  private val context = createContext
  server.setHandler(context)


  private def createServer = {
    val server = new Server
    val selectChannelConnector = new SelectChannelConnector
    selectChannelConnector.setPort(serverPort)
    server.setConnectors(Array(selectChannelConnector))
    server
  }

  def startServer = {
    try {
      println(">>> STARTING EMBEDDED JETTY SERVER")
      server.start()
      println(">>> JETTY SERVER STARTED")
      while (!server.isRunning) Thread.sleep(100)
    } catch {
      case exception: Exception => {
        println("FAILED TO START JETTY SERVER")
        exception.printStackTrace()
        throw exception
      }
    }
  }


  private def createContext = {
    val context = new WebAppContext()
    context.setServer(server)
    context.setContextPath("/")
    context.setWar(context.getClass.getClassLoader.getResource("webapp").toExternalForm)
    context.setExtractWAR(false)
    context
  }

   startServer
 }