
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
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[User,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(user: User)(content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.29*/("""

<!DOCTYPE html>
<html>
  <head>
    <script>
      function goBack() """),format.raw/*7.25*/("""{"""),format.raw/*7.26*/("""
      window.history.back()
      """),format.raw/*9.7*/("""}"""),format.raw/*9.8*/("""
    </script>

    <title>CBSR Container Database</title>
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*13.66*/routes/*13.72*/.Assets.at("stylesheets/main.css"))),format.raw/*13.106*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*14.66*/routes/*14.72*/.Assets.at("stylesheets/alert.css"))),format.raw/*14.107*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*15.66*/routes/*15.72*/.Assets.at("stylesheets/button.css"))),format.raw/*15.108*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*16.66*/routes/*16.72*/.Assets.at("stylesheets/pagination.css"))),format.raw/*16.112*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*17.66*/routes/*17.72*/.Assets.at("stylesheets/form.css"))),format.raw/*17.106*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*18.66*/routes/*18.72*/.Assets.at("stylesheets/input.css"))),format.raw/*18.107*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*19.66*/routes/*19.72*/.Assets.at("stylesheets/table.css"))),format.raw/*19.107*/(""""> 
  </head>

  <body> 
    <header>
      <a style= "color:black" id="logo" href=""""),_display_(Seq[Any](/*24.48*/routes/*24.54*/.ContainerController.index)),format.raw/*24.80*/("""">       
	<img src=""""),_display_(Seq[Any](/*25.13*/routes/*25.19*/.Assets.at("images/logo1.png"))),format.raw/*25.49*/("""" align="left">
	Temperature Monitor
      </a>
      
      """),_display_(Seq[Any](/*29.8*/if(user.email!="")/*29.26*/ {_display_(Seq[Any](format.raw/*29.28*/("""
      <dl id="user">
	<dd>
	  <a href=""""),_display_(Seq[Any](/*32.14*/routes/*32.20*/.UserController.editAdmins())),format.raw/*32.48*/("""">Edit admins</a>
	</dd>
      </dl>
      """)))})),format.raw/*35.8*/("""

    </header>
    <section>
      """),_display_(Seq[Any](/*39.8*/content)),format.raw/*39.15*/("""
    </section> 
  </body>
</html>
"""))}
    }
    
    def render(user:User,content:Html): play.api.templates.HtmlFormat.Appendable = apply(user)(content)
    
    def f:((User) => (Html) => play.api.templates.HtmlFormat.Appendable) = (user) => (content) => apply(user)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:39:20 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/main.scala.html
                    HASH: 515189c5eb11fe027d1820022825fe5a626f6dde
                    MATRIX: 558->1|679->28|777->99|805->100|866->135|893->136|1053->260|1068->266|1125->300|1230->369|1245->375|1303->410|1408->479|1423->485|1482->521|1587->590|1602->596|1665->636|1770->705|1785->711|1842->745|1947->814|1962->820|2020->855|2125->924|2140->930|2198->965|2319->1050|2334->1056|2382->1082|2440->1104|2455->1110|2507->1140|2604->1202|2631->1220|2671->1222|2748->1263|2763->1269|2813->1297|2888->1341|2960->1378|2989->1385
                    LINES: 19->1|22->1|28->7|28->7|30->9|30->9|34->13|34->13|34->13|35->14|35->14|35->14|36->15|36->15|36->15|37->16|37->16|37->16|38->17|38->17|38->17|39->18|39->18|39->18|40->19|40->19|40->19|45->24|45->24|45->24|46->25|46->25|46->25|50->29|50->29|50->29|53->32|53->32|53->32|56->35|60->39|60->39
                    -- GENERATED --
                */
            