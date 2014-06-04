
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
<sectionHeader>
  <headerLeft>New Container</headerLeft>
</sectionHeader>
<sectionBody>
  """),_display_(Seq[Any](/*12.4*/form(routes.ContainerController.save())/*12.43*/ {_display_(Seq[Any](format.raw/*12.45*/("""
  <fieldset>
    """),_display_(Seq[Any](/*14.6*/inputText(
      containerForm("name"), 
      '_label -> "Container name", 
      '_showConstraints -> containerForm.error("name").isDefined
    ))),format.raw/*18.6*/(""" 
    """),_display_(Seq[Any](/*19.6*/inputText(
      containerForm("index"), 
      '_label -> "Container number (Unique)",
      '_showConstraints -> containerForm.error("index").isDefined
    ))),format.raw/*23.6*/("""
    """),_display_(Seq[Any](/*24.6*/inputText(
      containerForm("temperatureExpected"), 
      '_label -> "Operating container temperature",
      '_showConstraints -> false
    ))),format.raw/*28.6*/(""" 
    
    """),_display_(Seq[Any](/*30.6*/inputText(
      containerForm("temperatureRange"), 
      '_label -> "Safe temperature range",
      '_showConstraints -> false
    ))),format.raw/*34.6*/("""

    """),_display_(Seq[Any](/*36.6*/inputText(
      containerForm("readFrequency"),
      '_label -> "Read frequency (in seconds)",
      '_showConstraints -> false
    ))),format.raw/*40.6*/("""

    """),_display_(Seq[Any](/*42.6*/select(
      containerForm("monitor"), 
      monitors, 
      '_label -> "Monitor", '_default -> "-- Choose a monitor --",
      '_showConstraints -> false
    ))),format.raw/*47.6*/("""
  </fieldset>

  <div class="actions">
    <input type="submit" value="Create this container" class="btn primary"> or 
    <a href=""""),_display_(Seq[Any](/*52.15*/routes/*52.21*/.ContainerController.list())),format.raw/*52.48*/("""" class="btn">Cancel</a> 
  </div>
  """)))})),format.raw/*54.4*/("""
</sectionBody>

""")))})),format.raw/*57.2*/("""
"""))}
    }
    
    def render(containerForm:Form[ContainerCreateData],monitors:Seq[scala.Tuple2[String, String]],user:User): play.api.templates.HtmlFormat.Appendable = apply(containerForm,monitors,user)
    
    def f:((Form[ContainerCreateData],Seq[scala.Tuple2[String, String]],User) => play.api.templates.HtmlFormat.Appendable) = (containerForm,monitors,user) => apply(containerForm,monitors,user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:39:21 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/createForm.scala.html
                    HASH: 02ddfa3582f2b6e11c57aa81cee67d67f61584b8
                    MATRIX: 619->1|808->109|840->133|919->88|947->107|975->182|1013->186|1038->203|1077->205|1205->298|1253->337|1293->339|1347->358|1515->505|1557->512|1737->671|1778->677|1945->823|1992->835|2147->969|2189->976|2345->1111|2387->1118|2571->1281|2741->1415|2756->1421|2805->1448|2874->1486|2923->1504
                    LINES: 19->1|22->5|22->5|23->1|25->4|26->5|28->7|28->7|28->7|33->12|33->12|33->12|35->14|39->18|40->19|44->23|45->24|49->28|51->30|55->34|57->36|61->40|63->42|68->47|73->52|73->52|73->52|75->54|78->57
                    -- GENERATED --
                */
            