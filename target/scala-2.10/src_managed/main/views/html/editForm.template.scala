
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
object editForm extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Long,Form[ContainerEditData],Seq[scala.Tuple2[String, String]],User,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(index: Long, containerForm: Form[ContainerEditData], monitors : Seq[(String, String)], user: User):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

implicit def /*3.2*/implicitFieldConstructor/*3.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};
Seq[Any](format.raw/*1.101*/("""
"""),format.raw/*3.75*/("""

"""),_display_(Seq[Any](/*5.2*/main(user = user)/*5.19*/ {_display_(Seq[Any](format.raw/*5.21*/("""
<sectionBody>
  <h1>Edit Container Specifications</h1> 
  """),_display_(Seq[Any](/*8.4*/form(routes.ContainerController.update(index))/*8.50*/ {_display_(Seq[Any](format.raw/*8.52*/("""
  <fieldset> 
    """),_display_(Seq[Any](/*10.6*/inputText(containerForm("name"), '_label -> "Container name"))),format.raw/*10.67*/("""
    """),_display_(Seq[Any](/*11.6*/inputText(containerForm("temperatureExpected"), 
    '_label -> "Operating container temperature"))),format.raw/*12.50*/("""

    """),_display_(Seq[Any](/*14.6*/inputText(containerForm("temperatureRange"), 
    '_label -> "Safe temperature range"))),format.raw/*15.41*/("""
    
    """),_display_(Seq[Any](/*17.6*/inputText(containerForm("readFrequency"),
    '_label -> "Read frequency (in seconds)"))),format.raw/*18.46*/("""

    """),_display_(Seq[Any](/*20.6*/select(
    containerForm("monitor"), 
    monitors, 
    '_label -> "Monitor", '_default -> "-- Choose a monitor --",
    '_showConstraints -> false
    ))),format.raw/*25.6*/("""
    
  </fieldset>
</sectionBody>
<sectionFooter>  
  <div class="actions">
    <input type="submit" value="Save this container" class="btn primary"> or 
    <a href=""""),_display_(Seq[Any](/*32.15*/routes/*32.21*/.ContainerController.details(index=index))),format.raw/*32.62*/("""" class="btn">Cancel</a> 
  </div> 
  """)))})),format.raw/*34.4*/("""
  
  """),_display_(Seq[Any](/*36.4*/form(routes.ContainerController.delete(index), 'class -> "topRight")/*36.72*/ {_display_(Seq[Any](format.raw/*36.74*/("""
  <input type="submit" value="Delete this container" class="btn danger">
  """)))})),format.raw/*38.4*/(""" 
</sectionFooter>
""")))})),format.raw/*40.2*/("""
  
"""))}
    }
    
    def render(index:Long,containerForm:Form[ContainerEditData],monitors:Seq[scala.Tuple2[String, String]],user:User): play.api.templates.HtmlFormat.Appendable = apply(index,containerForm,monitors,user)
    
    def f:((Long,Form[ContainerEditData],Seq[scala.Tuple2[String, String]],User) => play.api.templates.HtmlFormat.Appendable) = (index,containerForm,monitors,user) => apply(index,containerForm,monitors,user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Jan 09 17:31:59 MST 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/editForm.scala.html
                    HASH: bb9d247c8dee41b7bb6a11e748f12a8c3ea5a6ce
                    MATRIX: 620->1|821->119|853->143|933->100|961->192|998->195|1023->212|1062->214|1156->274|1210->320|1249->322|1304->342|1387->403|1428->409|1548->507|1590->514|1698->600|1744->611|1853->698|1895->705|2071->860|2276->1029|2291->1035|2354->1076|2424->1115|2466->1122|2543->1190|2583->1192|2691->1269|2742->1289
                    LINES: 19->1|22->3|22->3|23->1|24->3|26->5|26->5|26->5|29->8|29->8|29->8|31->10|31->10|32->11|33->12|35->14|36->15|38->17|39->18|41->20|46->25|53->32|53->32|53->32|55->34|57->36|57->36|57->36|59->38|61->40
                    -- GENERATED --
                */
            