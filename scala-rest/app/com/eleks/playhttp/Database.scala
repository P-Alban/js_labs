package com.eleks.playhttp

object Database {

  var db: Set[User] = Set(
    User(1, "Tommy", "NY"),
    User(2, "John", "LA"),
    User(3, "Nick", "NY"),
    User(4, "Rob", "Detroit"),
    User(5, "Alice", "Boston"),
    User(6, "Alan", "LA")
  )

  def saveUser(user: User): Boolean = {
    val sizeBefore = db.size
    db += user
    val sizeAfter = db.size

    if (sizeBefore == sizeAfter) false
    else true
  }

  def replaceUser(user: User): Boolean = {
    val current_user = getUserById(user.id)
    var result = false
    if (current_user.nonEmpty) {
      db -= current_user.get
      result = true
    }
    db += user
    result
  }

  def getUserById(id: Long): Option[User] = db.find(_.id == id)

  def deleteUser(user: User): User = {
    db -= user
    user
  }

  def changeName(user: User, name: String): Unit = {
    db -= user
    val new_user = User(id = user.id, name = name, country = user.country)
    db += new_user
  }
}
