// @SOURCE:/home/connor/workspace/ScalaTempmon/conf/routes
// @HASH:dcc9c7cfc4915a8ca0077edec038e3449a1a0313
// @DATE:Wed Jun 04 10:39:15 MDT 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:5
private[this] lazy val controllers_UserController_create0 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("user/create"))))
        

// @LINE:7
private[this] lazy val controllers_UserController_editAdmins1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/edit"))))
        

// @LINE:8
private[this] lazy val controllers_UserController_submitAdmin2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/add"))))
        

// @LINE:10
private[this] lazy val controllers_UserController_deleteAdmin3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/"),DynamicPart("email", """[^/]+""",true),StaticPart("/delete"))))
        

// @LINE:12
private[this] lazy val controllers_ContainerController_index4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:14
private[this] lazy val controllers_Application_login5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:15
private[this] lazy val controllers_Application_authenticate6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("authenticate"))))
        

// @LINE:16
private[this] lazy val controllers_Application_logout7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
        

// @LINE:19
private[this] lazy val controllers_ContainerController_list8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers"))))
        

// @LINE:22
private[this] lazy val controllers_ContainerController_create9 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/new"))))
        

// @LINE:23
private[this] lazy val controllers_ContainerController_save10 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers"))))
        

// @LINE:26
private[this] lazy val controllers_ContainerController_details11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("index", """[^/]+""",true),StaticPart("/readings"))))
        

// @LINE:29
private[this] lazy val controllers_ContainerController_edit12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("index", """[^/]+""",true),StaticPart("/specifications/edit"))))
        

// @LINE:30
private[this] lazy val controllers_ContainerController_update13 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/edit"))))
        

// @LINE:33
private[this] lazy val controllers_ContainerController_editNote14 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("index", """[^/]+""",true),StaticPart("/readings/"),DynamicPart("readID", """[^/]+""",true),StaticPart("/note/edit"))))
        

// @LINE:34
private[this] lazy val controllers_ContainerController_updateNote15 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("index", """[^/]+""",true),StaticPart("/readings/"),DynamicPart("readID", """[^/]+""",true),StaticPart("/note/edit"))))
        

// @LINE:37
private[this] lazy val controllers_ContainerController_clientPackage16 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("id", """[^/]+""",true),StaticPart("/specifications/clientpkg.json"))))
        

// @LINE:38
private[this] lazy val controllers_ContainerController_handleUpload17 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("id", """[^/]+""",true),StaticPart("/uploadpkg.json"))))
        

// @LINE:41
private[this] lazy val controllers_ContainerController_delete18 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
        

// @LINE:44
private[this] lazy val controllers_Assets_at19 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:47
private[this] lazy val controllers_Application_untrail20 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),DynamicPart("path", """.+""",false),StaticPart("/"))))
        
def documentation = List(("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """user/create""","""controllers.UserController.create"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/edit""","""controllers.UserController.editAdmins"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/add""","""controllers.UserController.submitAdmin"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/$email<[^/]+>/delete""","""controllers.UserController.deleteAdmin(email:String)"""),("""GET""", prefix,"""controllers.ContainerController.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.login"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """authenticate""","""controllers.Application.authenticate"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers""","""controllers.ContainerController.list(p:Int ?= 0, s:Int ?= 1, f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/new""","""controllers.ContainerController.create"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers""","""controllers.ContainerController.save"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$index<[^/]+>/readings""","""controllers.ContainerController.details(index:Long, p:Int ?= 0, s:Int ?= -3)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$index<[^/]+>/specifications/edit""","""controllers.ContainerController.edit(index:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/edit""","""controllers.ContainerController.update(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note/edit""","""controllers.ContainerController.editNote(index:Long, readID:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note/edit""","""controllers.ContainerController.updateNote(index:Long, readID:Long)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$id<[^/]+>/specifications/clientpkg.json""","""controllers.ContainerController.clientPackage(id:Long)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$id<[^/]+>/uploadpkg.json""","""controllers.ContainerController.handleUpload(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$id<[^/]+>/delete""","""controllers.ContainerController.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """$path<.+>/""","""controllers.Application.untrail(path:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:5
case controllers_UserController_create0(params) => {
   call { 
        invokeHandler(controllers.UserController.create, HandlerDef(this, "controllers.UserController", "create", Nil,"POST", """ Routes
 This file defines all application routes (Higher priority routes first)
 ~~~~
 User stuff""", Routes.prefix + """user/create"""))
   }
}
        

// @LINE:7
case controllers_UserController_editAdmins1(params) => {
   call { 
        invokeHandler(controllers.UserController.editAdmins, HandlerDef(this, "controllers.UserController", "editAdmins", Nil,"GET", """""", Routes.prefix + """admin/edit"""))
   }
}
        

// @LINE:8
case controllers_UserController_submitAdmin2(params) => {
   call { 
        invokeHandler(controllers.UserController.submitAdmin, HandlerDef(this, "controllers.UserController", "submitAdmin", Nil,"POST", """""", Routes.prefix + """admin/add"""))
   }
}
        

// @LINE:10
case controllers_UserController_deleteAdmin3(params) => {
   call(params.fromPath[String]("email", None)) { (email) =>
        invokeHandler(controllers.UserController.deleteAdmin(email), HandlerDef(this, "controllers.UserController", "deleteAdmin", Seq(classOf[String]),"POST", """""", Routes.prefix + """admin/$email<[^/]+>/delete"""))
   }
}
        

// @LINE:12
case controllers_ContainerController_index4(params) => {
   call { 
        invokeHandler(controllers.ContainerController.index, HandlerDef(this, "controllers.ContainerController", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:14
case controllers_Application_login5(params) => {
   call { 
        invokeHandler(controllers.Application.login, HandlerDef(this, "controllers.Application", "login", Nil,"GET", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:15
case controllers_Application_authenticate6(params) => {
   call { 
        invokeHandler(controllers.Application.authenticate, HandlerDef(this, "controllers.Application", "authenticate", Nil,"GET", """""", Routes.prefix + """authenticate"""))
   }
}
        

// @LINE:16
case controllers_Application_logout7(params) => {
   call { 
        invokeHandler(controllers.Application.logout, HandlerDef(this, "controllers.Application", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
   }
}
        

// @LINE:19
case controllers_ContainerController_list8(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[Int]("s", Some(1)), params.fromQuery[String]("f", Some(""))) { (p, s, f) =>
        invokeHandler(controllers.ContainerController.list(p, s, f), HandlerDef(this, "controllers.ContainerController", "list", Seq(classOf[Int], classOf[Int], classOf[String]),"GET", """ Container list (look at the default values for pagination parameters)""", Routes.prefix + """containers"""))
   }
}
        

// @LINE:22
case controllers_ContainerController_create9(params) => {
   call { 
        invokeHandler(controllers.ContainerController.create, HandlerDef(this, "controllers.ContainerController", "create", Nil,"GET", """ Add container""", Routes.prefix + """containers/new"""))
   }
}
        

// @LINE:23
case controllers_ContainerController_save10(params) => {
   call { 
        invokeHandler(controllers.ContainerController.save, HandlerDef(this, "controllers.ContainerController", "save", Nil,"POST", """""", Routes.prefix + """containers"""))
   }
}
        

// @LINE:26
case controllers_ContainerController_details11(params) => {
   call(params.fromPath[Long]("index", None), params.fromQuery[Int]("p", Some(0)), params.fromQuery[Int]("s", Some(-3))) { (index, p, s) =>
        invokeHandler(controllers.ContainerController.details(index, p, s), HandlerDef(this, "controllers.ContainerController", "details", Seq(classOf[Long], classOf[Int], classOf[Int]),"GET", """ View container""", Routes.prefix + """containers/$index<[^/]+>/readings"""))
   }
}
        

// @LINE:29
case controllers_ContainerController_edit12(params) => {
   call(params.fromPath[Long]("index", None)) { (index) =>
        invokeHandler(controllers.ContainerController.edit(index), HandlerDef(this, "controllers.ContainerController", "edit", Seq(classOf[Long]),"GET", """ Edit existing container""", Routes.prefix + """containers/$index<[^/]+>/specifications/edit"""))
   }
}
        

// @LINE:30
case controllers_ContainerController_update13(params) => {
   call(params.fromQuery[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ContainerController.update(id), HandlerDef(this, "controllers.ContainerController", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """containers/edit"""))
   }
}
        

// @LINE:33
case controllers_ContainerController_editNote14(params) => {
   call(params.fromPath[Long]("index", None), params.fromPath[Long]("readID", None)) { (index, readID) =>
        invokeHandler(controllers.ContainerController.editNote(index, readID), HandlerDef(this, "controllers.ContainerController", "editNote", Seq(classOf[Long], classOf[Long]),"GET", """ Add note to reading""", Routes.prefix + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note/edit"""))
   }
}
        

// @LINE:34
case controllers_ContainerController_updateNote15(params) => {
   call(params.fromPath[Long]("index", None), params.fromPath[Long]("readID", None)) { (index, readID) =>
        invokeHandler(controllers.ContainerController.updateNote(index, readID), HandlerDef(this, "controllers.ContainerController", "updateNote", Seq(classOf[Long], classOf[Long]),"POST", """""", Routes.prefix + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note/edit"""))
   }
}
        

// @LINE:37
case controllers_ContainerController_clientPackage16(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ContainerController.clientPackage(id), HandlerDef(this, "controllers.ContainerController", "clientPackage", Seq(classOf[Long]),"PUT", """ Get the JSON representation of the monitor requirements for this container""", Routes.prefix + """containers/$id<[^/]+>/specifications/clientpkg.json"""))
   }
}
        

// @LINE:38
case controllers_ContainerController_handleUpload17(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ContainerController.handleUpload(id), HandlerDef(this, "controllers.ContainerController", "handleUpload", Seq(classOf[Long]),"PUT", """""", Routes.prefix + """containers/$id<[^/]+>/uploadpkg.json"""))
   }
}
        

// @LINE:41
case controllers_ContainerController_delete18(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ContainerController.delete(id), HandlerDef(this, "controllers.ContainerController", "delete", Seq(classOf[Long]),"POST", """ Delete a container""", Routes.prefix + """containers/$id<[^/]+>/delete"""))
   }
}
        

// @LINE:44
case controllers_Assets_at19(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:47
case controllers_Application_untrail20(params) => {
   call(params.fromPath[String]("path", None)) { (path) =>
        invokeHandler(controllers.Application.untrail(path), HandlerDef(this, "controllers.Application", "untrail", Seq(classOf[String]),"GET", """ Remove trailing / from GETs""", Routes.prefix + """$path<.+>/"""))
   }
}
        
}

}
     