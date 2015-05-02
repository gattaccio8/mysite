import org.scalatest.{MustMatchers, FunSuite}
import persistence.PersistedUser


class DBConnectionTest extends FunSuite with MustMatchers {

  test("must connect to mongo") {
    (pending)
    val db = new PersistedUser()
//    db.addUser
    assert(db.findUserName("name") === "Paolo")
  }
}
