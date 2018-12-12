package com.eleks.playhttp

object Service {

  def saveUser(user: User): Boolean = Database.saveUser(user)

  def getUserById(id: Long): Option[User] = Database.getUserById(id)

  def deleteUserById(id: Long): Boolean = {
    val user = getUserById(id)
    if (user.nonEmpty) {
      Database.deleteUser(user.get)
      return true
    }
    false
  }

  def changeUserName(args: UserIdNamePair): Boolean = {
    val user = getUserById(args.id)
    if (user.nonEmpty) {
      Database.changeName(user.get, args.name)
      return true
    }
    false
  }

  def replaceUserIfExists(user: User): Boolean = {
    Database.replaceUser(user)
  }
}
