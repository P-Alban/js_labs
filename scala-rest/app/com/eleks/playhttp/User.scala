package com.eleks.playhttp

case class User(id: Long, name: String, country: String)
case class Response(response: String, user: User)
case class ApiError(error: String)
case class UserIdNamePair(id: Long, name: String)
case class ApiResponse(status: String)
case class UserId(id: Long)
case class Params(name: String, description: String)
case class MethodDescription(path: String, method: String, description: String, params: Array[Params])
case class AllApiMethods(methods: Array[MethodDescription])