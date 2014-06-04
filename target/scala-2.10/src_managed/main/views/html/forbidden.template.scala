
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object forbidden extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""<html>
  <header>
    <title>Unauthorized User</title>
  </header>

  <body>
    <h1> Unauthorized User </h1>
    <h2> Valid Authentication is required </h2>
  </body>
</html>
"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:39:20 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/forbidden.scala.html
                    HASH: 974f9efa09dc8fcaae33cd930f0f9ef7cb2b09c5
                    MATRIX: 641->0
                    LINES: 22->1
                    -- GENERATED --
                */
            