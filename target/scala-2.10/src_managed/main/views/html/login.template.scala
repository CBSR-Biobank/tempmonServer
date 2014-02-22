
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
object login extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[scala.Tuple2[String, String]],Flash,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(form: Form[(String,String)])(implicit flash: Flash):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.54*/("""

<html>
  <head>
    <title>CBSR Tempmon</title>
  </head>
  <body>  
    
    """),_display_(Seq[Any](/*9.6*/helper/*9.12*/.form(routes.Application.authenticate)/*9.50*/ {_display_(Seq[Any](format.raw/*9.52*/("""
    <h1>Sign in</h1>
    
    """),_display_(Seq[Any](/*12.6*/form/*12.10*/.globalError.map/*12.26*/ { error =>_display_(Seq[Any](format.raw/*12.37*/("""
    <p class="error">
      """),_display_(Seq[Any](/*14.8*/error/*14.13*/.message)),format.raw/*14.21*/("""
    </p>
    """)))})),format.raw/*16.6*/("""
    
    """),_display_(Seq[Any](/*18.6*/flash/*18.11*/.get("success").map/*18.30*/ { message =>_display_(Seq[Any](format.raw/*18.43*/("""
    <p class="success">
      """),_display_(Seq[Any](/*20.8*/message)),format.raw/*20.15*/("""
    </p>
    """)))})),format.raw/*22.6*/("""
    
    <p>
      <input type="email" name="email" placeholder="Email" id="email" value=""""),_display_(Seq[Any](/*25.79*/form("email")/*25.92*/.value)),format.raw/*25.98*/("""">
    </p>
    <p>
      <input type="password" name="password" id="password" placeholder="Password">
    </p>
    <p>
      <button type="submit" id="loginbutton">Login</button>
    </p>
    """)))})),format.raw/*33.6*/("""
    
  </body>
</html>
"""))}
    }
    
    def render(form:Form[scala.Tuple2[String, String]],flash:Flash): play.api.templates.HtmlFormat.Appendable = apply(form)(flash)
    
    def f:((Form[scala.Tuple2[String, String]]) => (Flash) => play.api.templates.HtmlFormat.Appendable) = (form) => (flash) => apply(form)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Nov 04 16:43:47 MST 2013
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/login.scala.html
                    HASH: 0fab2b1dda6fbac186ba7e25e41b107532450231
                    MATRIX: 590->1|736->53|851->134|865->140|911->178|950->180|1017->212|1030->216|1055->232|1104->243|1169->273|1183->278|1213->286|1259->301|1305->312|1319->317|1347->336|1398->349|1465->381|1494->388|1540->403|1668->495|1690->508|1718->514|1943->708
                    LINES: 19->1|22->1|30->9|30->9|30->9|30->9|33->12|33->12|33->12|33->12|35->14|35->14|35->14|37->16|39->18|39->18|39->18|39->18|41->20|41->20|43->22|46->25|46->25|46->25|54->33
                    -- GENERATED --
                */
            