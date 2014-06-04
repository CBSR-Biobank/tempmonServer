
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
"""),_display_(Seq[Any](/*2.2*/main(new User("", "", ""))/*2.28*/ {_display_(Seq[Any](format.raw/*2.30*/("""
<html>
  <head>
    <title>CBSR Tempmon</title>
  </head>
  <body>  
    """),_display_(Seq[Any](/*8.6*/helper/*8.12*/.form(routes.Application.authenticate)/*8.50*/ {_display_(Seq[Any](format.raw/*8.52*/("""
    <h1>Sign in</h1>
    
    """),_display_(Seq[Any](/*11.6*/form/*11.10*/.globalError.map/*11.26*/ { error =>_display_(Seq[Any](format.raw/*11.37*/("""
    <p class="error">
      """),_display_(Seq[Any](/*13.8*/error/*13.13*/.message)),format.raw/*13.21*/("""
    </p>
    """)))})),format.raw/*15.6*/("""
    
    """),_display_(Seq[Any](/*17.6*/flash/*17.11*/.get("success").map/*17.30*/ { message =>_display_(Seq[Any](format.raw/*17.43*/("""
    <p class="success">
      """),_display_(Seq[Any](/*19.8*/message)),format.raw/*19.15*/("""
    </p>
    """)))})),format.raw/*21.6*/("""
    
    <p>
      <input type="email" name="email" placeholder="Email" id="email" value=""""),_display_(Seq[Any](/*24.79*/form("email")/*24.92*/.value)),format.raw/*24.98*/("""">
    </p>
    <p>
      <input type="password" name="password" id="password" placeholder="Password">
    </p>
    <p>
      <button type="submit" id="loginbutton" class="btn primary">Login</button>
   </p>
    """)))})),format.raw/*32.6*/("""
    
  </body>
</html>
""")))})),format.raw/*36.2*/("""
"""))}
    }
    
    def render(form:Form[scala.Tuple2[String, String]],flash:Flash): play.api.templates.HtmlFormat.Appendable = apply(form)(flash)
    
    def f:((Form[scala.Tuple2[String, String]]) => (Flash) => play.api.templates.HtmlFormat.Appendable) = (form) => (flash) => apply(form)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:39:21 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/login.scala.html
                    HASH: afcb7611e6dd4be0c941b97175c87922cff632fb
                    MATRIX: 590->1|736->53|772->55|806->81|845->83|954->158|968->164|1014->202|1053->204|1120->236|1133->240|1158->256|1207->267|1272->297|1286->302|1316->310|1362->325|1408->336|1422->341|1450->360|1501->373|1568->405|1597->412|1643->427|1771->519|1793->532|1821->538|2065->751|2121->776
                    LINES: 19->1|22->1|23->2|23->2|23->2|29->8|29->8|29->8|29->8|32->11|32->11|32->11|32->11|34->13|34->13|34->13|36->15|38->17|38->17|38->17|38->17|40->19|40->19|42->21|45->24|45->24|45->24|53->32|57->36
                    -- GENERATED --
                */
            