// @SOURCE:/home/connor/workspace/ScalaTempmon/conf/routes
// @HASH:98e44e0c6c5e84ff2ccf07de762eb17bd4f60be5
// @DATE:Thu May 22 15:30:35 MDT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:41
// @LINE:38
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers {

// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:6
class ReverseContainerController {
    

// @LINE:35
def delete(id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "containers/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                                                

// @LINE:31
def clientPackage(id:Long): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "containers/" + implicitly[PathBindable[Long]].unbind("id", id) + "/specifications/clientpkg.json")
}
                                                

// @LINE:16
def create(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "containers/new")
}
                                                

// @LINE:23
def edit(index:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "containers/" + implicitly[PathBindable[Long]].unbind("index", index) + "/specifications/edit")
}
                                                

// @LINE:27
def editNote(index:Long, readID:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "containers/" + implicitly[PathBindable[Long]].unbind("index", index) + "/readings/" + implicitly[PathBindable[Long]].unbind("readID", readID) + "/note")
}
                                                

// @LINE:24
def update(id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "containers/edit" + queryString(List(Some(implicitly[QueryStringBindable[Long]].unbind("id", id)))))
}
                                                

// @LINE:32
def handleUpload(id:Long): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "containers/" + implicitly[PathBindable[Long]].unbind("id", id) + "/uploadpkg.json")
}
                                                

// @LINE:13
def list(p:Int = 0, s:Int = 1, f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "containers" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == 1) None else Some(implicitly[QueryStringBindable[Int]].unbind("s", s)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:17
def save(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "containers")
}
                                                

// @LINE:28
def updateNote(index:Long, readID:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "containers/" + implicitly[PathBindable[Long]].unbind("index", index) + "/readings/" + implicitly[PathBindable[Long]].unbind("readID", readID) + "/note")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:20
def details(index:Long, p:Int = 0, s:Int = -3): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "containers/" + implicitly[PathBindable[Long]].unbind("index", index) + "/readings" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == -3) None else Some(implicitly[QueryStringBindable[Int]].unbind("s", s)))))
}
                                                
    
}
                          

// @LINE:38
class ReverseAssets {
    

// @LINE:38
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:41
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseApplication {
    

// @LINE:10
def logout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                                                

// @LINE:9
def authenticate(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "authenticate")
}
                                                

// @LINE:41
def untrail(path:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + implicitly[PathBindable[String]].unbind("path", path) + "/")
}
                                                

// @LINE:8
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                                                
    
}
                          
}
                  


// @LINE:41
// @LINE:38
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers.javascript {

// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:6
class ReverseContainerController {
    

// @LINE:35
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:31
def clientPackage : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.clientPackage",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/specifications/clientpkg.json"})
      }
   """
)
                        

// @LINE:16
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.create",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/new"})
      }
   """
)
                        

// @LINE:23
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.edit",
   """
      function(index) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("index", index) + "/specifications/edit"})
      }
   """
)
                        

// @LINE:27
def editNote : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.editNote",
   """
      function(index,readID) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("index", index) + "/readings/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("readID", readID) + "/note"})
      }
   """
)
                        

// @LINE:24
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/edit" + _qS([(""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("id", id)])})
      }
   """
)
                        

// @LINE:32
def handleUpload : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.handleUpload",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/uploadpkg.json"})
      }
   """
)
                        

// @LINE:13
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.list",
   """
      function(p,s,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "containers" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("s", s)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:17
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "containers"})
      }
   """
)
                        

// @LINE:28
def updateNote : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.updateNote",
   """
      function(index,readID) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("index", index) + "/readings/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("readID", readID) + "/note"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:20
def details : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ContainerController.details",
   """
      function(index,p,s) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("index", index) + "/readings" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("s", s))])})
      }
   """
)
                        
    
}
              

// @LINE:38
class ReverseAssets {
    

// @LINE:38
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:41
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseApplication {
    

// @LINE:10
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:9
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticate",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "authenticate"})
      }
   """
)
                        

// @LINE:41
def untrail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.untrail",
   """
      function(path) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("path", path) + "/"})
      }
   """
)
                        

// @LINE:8
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:41
// @LINE:38
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers.ref {


// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:6
class ReverseContainerController {
    

// @LINE:35
def delete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.delete(id), HandlerDef(this, "controllers.ContainerController", "delete", Seq(classOf[Long]), "POST", """ Delete a container""", _prefix + """containers/$id<[^/]+>/delete""")
)
                      

// @LINE:31
def clientPackage(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.clientPackage(id), HandlerDef(this, "controllers.ContainerController", "clientPackage", Seq(classOf[Long]), "PUT", """ Get the JSON representation of the monitor requirements for this container""", _prefix + """containers/$id<[^/]+>/specifications/clientpkg.json""")
)
                      

// @LINE:16
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.create(), HandlerDef(this, "controllers.ContainerController", "create", Seq(), "GET", """ Add container""", _prefix + """containers/new""")
)
                      

// @LINE:23
def edit(index:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.edit(index), HandlerDef(this, "controllers.ContainerController", "edit", Seq(classOf[Long]), "GET", """ Edit existing container""", _prefix + """containers/$index<[^/]+>/specifications/edit""")
)
                      

// @LINE:27
def editNote(index:Long, readID:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.editNote(index, readID), HandlerDef(this, "controllers.ContainerController", "editNote", Seq(classOf[Long], classOf[Long]), "GET", """ Add note to reading""", _prefix + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note""")
)
                      

// @LINE:24
def update(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.update(id), HandlerDef(this, "controllers.ContainerController", "update", Seq(classOf[Long]), "POST", """""", _prefix + """containers/edit""")
)
                      

// @LINE:32
def handleUpload(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.handleUpload(id), HandlerDef(this, "controllers.ContainerController", "handleUpload", Seq(classOf[Long]), "PUT", """""", _prefix + """containers/$id<[^/]+>/uploadpkg.json""")
)
                      

// @LINE:13
def list(p:Int, s:Int, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.list(p, s, f), HandlerDef(this, "controllers.ContainerController", "list", Seq(classOf[Int], classOf[Int], classOf[String]), "GET", """ Container list (look at the default values for pagination parameters)""", _prefix + """containers""")
)
                      

// @LINE:17
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.save(), HandlerDef(this, "controllers.ContainerController", "save", Seq(), "POST", """""", _prefix + """containers""")
)
                      

// @LINE:28
def updateNote(index:Long, readID:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.updateNote(index, readID), HandlerDef(this, "controllers.ContainerController", "updateNote", Seq(classOf[Long], classOf[Long]), "POST", """""", _prefix + """containers/$index<[^/]+>/readings/$readID<[^/]+>/note""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.index(), HandlerDef(this, "controllers.ContainerController", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:20
def details(index:Long, p:Int, s:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ContainerController.details(index, p, s), HandlerDef(this, "controllers.ContainerController", "details", Seq(classOf[Long], classOf[Int], classOf[Int]), "GET", """ View container""", _prefix + """containers/$index<[^/]+>/readings""")
)
                      
    
}
                          

// @LINE:38
class ReverseAssets {
    

// @LINE:38
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:41
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseApplication {
    

// @LINE:10
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.logout(), HandlerDef(this, "controllers.Application", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

// @LINE:9
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Seq(), "GET", """""", _prefix + """authenticate""")
)
                      

// @LINE:41
def untrail(path:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.untrail(path), HandlerDef(this, "controllers.Application", "untrail", Seq(classOf[String]), "GET", """ Remove trailing / from GETs""", _prefix + """$path<.+>/""")
)
                      

// @LINE:8
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq(), "GET", """""", _prefix + """login""")
)
                      
    
}
                          
}
        
    