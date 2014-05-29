
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
object createForm extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Form[ContainerCreateData],Seq[scala.Tuple2[String, String]],User,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(containerForm: Form[ContainerCreateData], monitors: Seq[(String, String)], user: User):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};
Seq[Any](format.raw/*1.89*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.75*/(""" 

"""),_display_(Seq[Any](/*7.2*/main(user = user)/*7.19*/ {_display_(Seq[Any](format.raw/*7.21*/(""" 
<sectionBody>
  <h1>New Container</h1>
  
  """),_display_(Seq[Any](/*11.4*/form(routes.ContainerController.save())/*11.43*/ {_display_(Seq[Any](format.raw/*11.45*/("""
  <fieldset>
    """),_display_(Seq[Any](/*13.6*/inputText(containerForm("name"), '_label -> "Container name"))),format.raw/*13.67*/(""" 
    """),_display_(Seq[Any](/*14.6*/inputText(containerForm("index"), '_label -> "Container number (Unique)"))),format.raw/*14.79*/("""
    """),_display_(Seq[Any](/*15.6*/inputText(containerForm("temperatureExpected"), 
    '_label -> "Operating container temperature"))),format.raw/*16.50*/("""
    
    """),_display_(Seq[Any](/*18.6*/inputText(containerForm("temperatureRange"), 
    '_label -> "Safe temperature range"))),format.raw/*19.41*/("""

    """),_display_(Seq[Any](/*21.6*/inputText(containerForm("readFrequency"),
    '_label -> "Read frequency (in seconds)"))),format.raw/*22.46*/("""

    """),_display_(Seq[Any](/*24.6*/select(
      containerForm("monitor"), 
      monitors, 
      '_label -> "Monitor", '_default -> "-- Choose a monitor --",
      '_showConstraints -> false
    ))),format.raw/*29.6*/("""
  </fieldset>

  <div class="actions">
    <input type="submit" value="Create this container" class="btn primary"> or 
    <a href=""""),_display_(Seq[Any](/*34.15*/routes/*34.21*/.ContainerController.list())),format.raw/*34.48*/("""" class="btn">Cancel</a> 
  </div>
  """)))})),format.raw/*36.4*/("""
</sectionBody>

""")))})),format.raw/*39.2*/("""
"""))}
    }
    
    def render(containerForm:Form[ContainerCreateData],monitors:Seq[scala.Tuple2[String, String]],user:User): play.api.templates.HtmlFormat.Appendable = apply(containerForm,monitors,user)
    
    def f:((Form[ContainerCreateData],Seq[scala.Tuple2[String, String]],User) => play.api.templates.HtmlFormat.Appendable) = (containerForm,monitors,user) => apply(containerForm,monitors,user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu May 22 11:46:30 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/createForm.scala.html
                    HASH: f82f824fa5a37859f7ea1030433824fe7d5072ae
                    MATRIX: 619->1|808->109|840->133|919->88|947->107|975->182|1013->186|1038->203|1077->205|1159->252|1207->291|1247->293|1301->312|1384->373|1426->380|1521->453|1562->459|1682->557|1728->568|1836->654|1878->661|1987->748|2029->755|2213->918|2383->1052|2398->1058|2447->1085|2516->1123|2565->1141
                    LINES: 19->1|22->5|22->5|23->1|25->4|26->5|28->7|28->7|28->7|32->11|32->11|32->11|34->13|34->13|35->14|35->14|36->15|37->16|39->18|40->19|42->21|43->22|45->24|50->29|55->34|55->34|55->34|57->36|60->39
                    -- GENERATED --
                */
            