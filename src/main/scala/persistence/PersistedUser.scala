package persistence

import com.mongodb.casbah.Imports._

class PersistedUser {
  private val mongoClient = MongoClient("81.4.100.93", 27017)
  private val db = mongoClient("test")
  private val name = MongoDBObject("name" -> "Paolo")
  private val lastname = MongoDBObject("surname" -> "Rossi")
  val mongoCollection = db("test")

  def addUser = {
    mongoCollection.insert(name)
    mongoCollection.insert(lastname)
  }

  def findUserName(dbkey: String) = {
    mongoCollection.findOne(name) foreach println
    mongoCollection.findOne(name).map(_.get("name")).getOrElse("")
  }
}
