package com.eleks.playhttp

import javax.inject.Inject
import play.api.libs.json.{JsValue, Json, OFormat}
import play.api.mvc.{InjectedController, _}

class Controller @Inject() extends InjectedController {

  implicit val userFormat: OFormat[User] = Json.format[User]
  implicit val responseFormat: OFormat[Response] = Json.format[Response]
  implicit val apiErrorFormat: OFormat[ApiError] = Json.format[ApiError]
  implicit val userIdFormat: OFormat[UserIdNamePair] = Json.format[UserIdNamePair]
  implicit val apiResponseFormat: OFormat[ApiResponse] = Json.format[ApiResponse]
  implicit val userSingleIdFormat: OFormat[UserId] = Json.format[UserId]
  implicit val paramsFormat: OFormat[Params] = Json.format[Params]
  implicit val methodDescriptionFormat: OFormat[MethodDescription] = Json.format[MethodDescription]
  implicit val apiMethodsFormat: OFormat[AllApiMethods] = Json.format[AllApiMethods]

  def getUser(id: Long): Action[AnyContent] = Action {
    val userOpt: Option[User] = Service.getUserById(id)
    userOpt match {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound(Json.toJson(ApiError(s"User with id = $id does not exist")))
    }
  }

  def saveUser(): Action[JsValue] = Action(parse.json) { request =>
    val user = request.body.as[User]
    val result = Service.saveUser(user)

    if (result) {
      Created("User was created")
    } else {
      NotAcceptable("User already exists")
    }
  }

  def deleteUser(): Action[JsValue] = Action(parse.json) { request =>
    val user = request.body.as[UserId]
    val result = Service.deleteUserById(user.id)
    if (result) {
      val resp = ApiResponse(s"User ${user.id} has been deleted.")
      Ok(Json.toJson(resp))
    } else {
      NotFound(Json.toJson(ApiError(s"User with id = ${user.id} does not exist")))
    }
  }

  def changeName(): Action[JsValue] = Action(parse.json) { request =>
    val args = request.body.as[UserIdNamePair]
    val result = Service.changeUserName(args)
    if (result) {
      Ok(Json.toJson(ApiResponse("Success")))
    } else {
      NotFound(Json.toJson(ApiError(s"User with id = ${args.id} does not exist")))
    }
  }

  def replaceUser(): Action[JsValue] = Action(parse.json) { request =>
    val user = request.body.as[User]
    val response = Service.replaceUserIfExists(user)
    var apiResponse = "Success"
    if (response) {
      apiResponse = "User replaced"
    }
    Ok(Json.toJson(ApiResponse(apiResponse)))
  }

  def sendAllApiMethods(): Action[AnyContent] = Action {
    Ok(Json.toJson(AllApiMethods(Array(
      MethodDescription(path = "/users", method = "POST",
        description = "add user", params = Array(
          Params(name = "id", description = "user ID (integer)"),
          Params(name = "name", description = "user name"),
          Params(name = "country", description = "user country")
        )),
      MethodDescription(path = "/users", method = "GET",
        description = "get user by id", params = Array(
          Params(name = "id", description = "user ID (integer)")
        )),
      MethodDescription(path = "/change", method = "POST",
        description = "change user name", params = Array(
          Params(name = "id", description = "user ID (integer)"),
          Params(name = "name", description = "new user name")
        )),
      MethodDescription(path = "/delete", method = "POST",
        description = "delete user", params = Array(
          Params(name = "id", description = "user ID (integer)")
        )),
      MethodDescription(path = "/", method = "OPTIONS",
        description = "get all api methods", params = Array())
    ))))
  }
}
