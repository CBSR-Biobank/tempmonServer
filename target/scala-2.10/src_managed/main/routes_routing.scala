// @SOURCE:/home/connor/workspace/ScalaTempmon/conf/routes
// @HASH:98e44e0c6c5e84ff2ccf07de762eb17bd4f60be5
// @DATE:Thu May 22 15:30:35 MDT 2014


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


// @LINE:6
private[this] lazy val controllers_ContainerController_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:8
private[this] lazy val controllers_Application_login1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:9
private[this] lazy val controllers_Application_authenticate2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("authenticate"))))
        

// @LINE:10
private[this] lazy val controllers_Application_logout3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
        

// @LINE:13
private[this] lazy val controllers_ContainerController_list4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers"))))
        

// @LINE:16
private[this] lazy val controllers_ContainerController_create5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/new"))))
        

// @LINE:17
private[this] lazy val controllers_ContainerController_save6 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers"))))
        

// @LINE:20
private[this] lazy val controllers_ContainerController_details7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("index", """[^/]+""",true),StaticPart("/readings"))))
        

// @LINE:23
private[this] lazy val controllers_ContainerController_edit8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("index", """[^/]+""",true),StaticPart("/specifications/edit"))))
        

// @LINE:24
private[this] lazy val controllers_ContainerController_update9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/edit"))))
        

// @LINE:27
private[this] lazy val controllers_ContainerController_editNote10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("index", """[^/]+""",true),StaticPart("/readings/"),DynamicPart("readID", """[^/]+""",true),StaticPart("/note"))))
        

// @LINE:28
private[this] lazy val controllers_ContainerController_updateNote11 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("index", """[^/]+""",true),StaticPart("/readings/"),DynamicPart("readID", """[^/]+""",true),StaticPart("/note"))))
        

// @LINE:31
private[this] lazy val controllers_ContainerController_clientPackage12 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("id", """[^/]+""",true),StaticPart("/specifications/clientpkg.json"))))
        

// @LINE:32
private[this] lazy val controllers_ContainerController_handleUpload13 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("id", """[^/]+""",true),StaticPart("/uploadpkg.json"))))
        

// @LINE:35
private[this] lazy val controllers_ContainerController_delete14 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("containers/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
        

// @LINE:38
private[this] lazy val controllers_Assets_at15 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:41
private[this] lazy val controllers_Application_untrail16 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),DynamicPart("path", """.+""",false),StaticPart("/"))))
        
def documentation = List(("""GET""", prefix,"""controllers.ContainerController.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.login"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """authenticate""","""controllers.Application.authenticate"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers""","""controllers.ContainerController.list(p:Int ?= 0, s:Int ?= 1, f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/new""","""controllers.ContainerController.create"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers""","""controllers.ContainerController.save"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$index<[^/]+>/readings""","""controllers.ContainerController.details(index:Long, p:Int ?= 0, s:Int ?= -3)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$index<[^/]+>/specifications/edit""","""controllers.ContainerController.edit(index:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/edit""","""controllers.ContainerController.update(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note""","""controllers.ContainerController.editNote(index:Long, readID:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note""","""controllers.ContainerController.updateNote(index:Long, readID:Long)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$id<[^/]+>/specifications/clientpkg.json""","""controllers.ContainerController.clientPackage(id:Long)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$id<[^/]+>/uploadpkg.json""","""controllers.ContainerController.handleUpload(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """containers/$id<[^/]+>/delete""","""controllers.ContainerController.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """$path<.+>/""","""controllers.Application.untrail(path:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_ContainerController_index0(params) => {
   call { 
        invokeHandler(controllers.ContainerController.index, HandlerDef(this, "controllers.ContainerController", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:8
case controllers_Application_login1(params) => {
   call { 
        invokeHandler(controllers.Application.login, HandlerDef(this, "controllers.Application", "login", Nil,"GET", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:9
case controllers_Application_authenticate2(params) => {
   call { 
        invokeHandler(controllers.Application.authenticate, HandlerDef(this, "controllers.Application", "authenticate", Nil,"GET", """""", Routes.prefix + """authenticate"""))
   }
}
        

// @LINE:10
case controllers_Application_logout3(params) => {
   call { 
        invokeHandler(controllers.Application.logout, HandlerDef(this, "controllers.Application", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
   }
}
        

// @LINE:13
case controllers_ContainerController_list4(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[Int]("s", Some(1)), params.fromQuery[String]("f", Some(""))) { (p, s, f) =>
        invokeHandler(controllers.ContainerController.list(p, s, f), HandlerDef(this, "controllers.ContainerController", "list", Seq(classOf[Int], classOf[Int], classOf[String]),"GET", """ Container list (look at the default values for pagination parameters)""", Routes.prefix + """containers"""))
   }
}
        

// @LINE:16
case controllers_ContainerController_create5(params) => {
   call { 
        invokeHandler(controllers.ContainerController.create, HandlerDef(this, "controllers.ContainerController", "create", Nil,"GET", """ Add container""", Routes.prefix + """containers/new"""))
   }
}
        

// @LINE:17
case controllers_ContainerController_save6(params) => {
   call { 
        invokeHandler(controllers.ContainerController.save, HandlerDef(this, "controllers.ContainerController", "save", Nil,"POST", """""", Routes.prefix + """containers"""))
   }
}
        

// @LINE:20
case controllers_ContainerController_details7(params) => {
   call(params.fromPath[Long]("index", None), params.fromQuery[Int]("p", Some(0)), params.fromQuery[Int]("s", Some(-3))) { (index, p, s) =>
        invokeHandler(controllers.ContainerController.details(index, p, s), HandlerDef(this, "controllers.ContainerController", "details", Seq(classOf[Long], classOf[Int], classOf[Int]),"GET", """ View container""", Routes.prefix + """containers/$index<[^/]+>/readings"""))
   }
}
        

// @LINE:23
case controllers_ContainerController_edit8(params) => {
   call(params.fromPath[Long]("index", None)) { (index) =>
        invokeHandler(controllers.ContainerController.edit(index), HandlerDef(this, "controllers.ContainerController", "edit", Seq(classOf[Long]),"GET", """ Edit existing container""", Routes.prefix + """containers/$index<[^/]+>/specifications/edit"""))
   }
}
        

// @LINE:24
case controllers_ContainerController_update9(params) => {
   call(params.fromQuery[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ContainerController.update(id), HandlerDef(this, "controllers.ContainerController", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """containers/edit"""))
   }
}
        

// @LINE:27
case controllers_ContainerController_editNote10(params) => {
   call(params.fromPath[Long]("index", None), params.fromPath[Long]("readID", None)) { (index, readID) =>
        invokeHandler(controllers.ContainerController.editNote(index, readID), HandlerDef(this, "controllers.ContainerController", "editNote", Seq(classOf[Long], classOf[Long]),"GET", """ Add note to reading""", Routes.prefix + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note"""))
   }
}
        

// @LINE:28
case controllers_ContainerController_updateNote11(params) => {
   call(params.fromPath[Long]("index", None), params.fromPath[Long]("readID", None)) { (index, readID) =>
        invokeHandler(controllers.ContainerController.updateNote(index, readID), HandlerDef(this, "controllers.ContainerController", "updateNote", Seq(classOf[Long], classOf[Long]),"POST", """""", Routes.prefix + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note"""))
   }
}
        

// @LINE:31
case controllers_ContainerController_clientPackage12(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ContainerController.clientPackage(id), HandlerDef(this, "controllers.ContainerController", "clientPackage", Seq(classOf[Long]),"PUT", """ Get the JSON representation of the monitor requirements for this container""", Routes.prefix + """containers/$id<[^/]+>/specifications/clientpkg.json"""))
   }
}
        

// @LINE:32
case controllers_ContainerController_handleUpload13(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ContainerController.handleUpload(id), HandlerDef(this, "controllers.ContainerController", "handleUpload", Seq(classOf[Long]),"PUT", """""", Routes.prefix + """containers/$id<[^/]+>/uploadpkg.json"""))
   }
}
        

// @LINE:35
case controllers_ContainerController_delete14(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ContainerController.delete(id), HandlerDef(this, "controllers.ContainerController", "delete", Seq(classOf[Long]),"POST", """ Delete a container""", Routes.prefix + """containers/$id<[^/]+>/delete"""))
   }
}
        

// @LINE:38
case controllers_Assets_at15(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:41
case controllers_Application_untrail16(params) => {
   call(params.fromPath[String]("path", None)) { (path) =>
        invokeHandler(controllers.Application.untrail(path), HandlerDef(this, "controllers.Application", "untrail", Seq(classOf[String]),"GET", """ Remove trailing / from GETs""", Routes.prefix + """$path<.+>/"""))
   }
}
        
}

}
     