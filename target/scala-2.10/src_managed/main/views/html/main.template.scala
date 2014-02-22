
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
    <title>CBSR Container Database</title>
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*7.66*/routes/*7.72*/.Assets.at("stylesheets/main.css"))),format.raw/*7.106*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*8.66*/routes/*8.72*/.Assets.at("stylesheets/alert.css"))),format.raw/*8.107*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*9.66*/routes/*9.72*/.Assets.at("stylesheets/button.css"))),format.raw/*9.108*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*10.66*/routes/*10.72*/.Assets.at("stylesheets/pagination.css"))),format.raw/*10.112*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*11.66*/routes/*11.72*/.Assets.at("stylesheets/form.css"))),format.raw/*11.106*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*12.66*/routes/*12.72*/.Assets.at("stylesheets/input.css"))),format.raw/*12.107*/(""""> 
    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*13.66*/routes/*13.72*/.Assets.at("stylesheets/table.css"))),format.raw/*13.107*/(""""> 
  </head>

  <body> 
    <header>
      <a id="logo" href=""""),_display_(Seq[Any](/*18.27*/routes/*18.33*/.ContainerController.index)),format.raw/*18.59*/("""">       
	<img src=""""),_display_(Seq[Any](/*19.13*/routes/*19.19*/.Assets.at("images/logo1.png"))),format.raw/*19.49*/("""" align="left">
	Temperature Monitor
      </a>

      <dl id="user">
	<dt>"""),_display_(Seq[Any](/*24.7*/user/*24.11*/.name)),format.raw/*24.16*/(""" ("""),_display_(Seq[Any](/*24.19*/user/*24.23*/.email)),format.raw/*24.29*/(""")</dt>
	<dd>
	  <a href=""""),_display_(Seq[Any](/*26.14*/routes/*26.20*/.Application.logout())),format.raw/*26.41*/("""">Logout</a>
	</dd>
      </dl>
    </header>

    <section>
      """),_display_(Seq[Any](/*32.8*/content)),format.raw/*32.15*/("""
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
                    DATE: Thu Jan 09 18:12:43 MST 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/main.scala.html
                    HASH: 76db323d38e099d7a8749a8f06333e638c026486
                    MATRIX: 558->1|679->28|856->170|870->176|926->210|1030->279|1044->285|1101->320|1205->389|1219->395|1277->431|1382->500|1397->506|1460->546|1565->615|1580->621|1637->655|1742->724|1757->730|1815->765|1920->834|1935->840|1993->875|2093->939|2108->945|2156->971|2214->993|2229->999|2281->1029|2392->1105|2405->1109|2432->1114|2471->1117|2484->1121|2512->1127|2574->1153|2589->1159|2632->1180|2735->1248|2764->1255
                    LINES: 19->1|22->1|28->7|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|32->11|32->11|32->11|33->12|33->12|33->12|34->13|34->13|34->13|39->18|39->18|39->18|40->19|40->19|40->19|45->24|45->24|45->24|45->24|45->24|45->24|47->26|47->26|47->26|53->32|53->32
                    -- GENERATED --
                */
            