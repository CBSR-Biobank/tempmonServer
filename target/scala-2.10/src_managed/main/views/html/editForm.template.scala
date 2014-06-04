
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
<sectionHeader>
  <headerLeft>Edit Container Specifications</headerLeft> 
</sectionHeader>
<sectionBody>
  
  
  """),_display_(Seq[Any](/*12.4*/form(routes.ContainerController.update(index))/*12.50*/ {_display_(Seq[Any](format.raw/*12.52*/("""
  <fieldset> 
    """),_display_(Seq[Any](/*14.6*/inputText(
      containerForm("name"), 
      '_label -> "Container name",
      '_showConstraints -> containerForm.error("name").isDefined
    ))),format.raw/*18.6*/("""
    """),_display_(Seq[Any](/*19.6*/inputText(
      containerForm("temperatureExpected"), 
      '_label -> "Operating container temperature",
      '_showConstraints -> false
    ))),format.raw/*23.6*/("""
    
    """),_display_(Seq[Any](/*25.6*/inputText(
      containerForm("temperatureRange"), 
      '_label -> "Safe temperature range",
      '_showConstraints -> false
    ))),format.raw/*29.6*/("""
    
    """),_display_(Seq[Any](/*31.6*/inputText(
      containerForm("readFrequency"),
      '_label -> "Read frequency (in seconds)",
      '_showConstraints -> false
    ))),format.raw/*35.6*/("""
    
    """),_display_(Seq[Any](/*37.6*/select(
    containerForm("monitor"), 
    monitors, 
    '_label -> "Monitor", '_default -> "-- Choose a monitor --",
    '_showConstraints -> false
    ))),format.raw/*42.6*/("""
    
  </fieldset>
</sectionBody>
<sectionFooter>  
  <div class="actions">
    <input type="submit" value="Save this container" class="btn primary"> or 
    <a href=""""),_display_(Seq[Any](/*49.15*/routes/*49.21*/.ContainerController.details(index=index))),format.raw/*49.62*/("""" class="btn">Cancel</a> 
  </div> 
  """)))})),format.raw/*51.4*/("""
  <span style="float:right">
    """),_display_(Seq[Any](/*53.6*/form(routes.ContainerController.delete(index), 'class -> "topRight")/*53.74*/ {_display_(Seq[Any](format.raw/*53.76*/("""
    <input type="submit" value="Delete this container" class="btn danger">
    """)))})),format.raw/*55.6*/(""" 
  </span>
  
</sectionFooter>
""")))})),format.raw/*59.2*/("""
  
"""))}
    }
    
    def render(index:Long,containerForm:Form[ContainerEditData],monitors:Seq[scala.Tuple2[String, String]],user:User): play.api.templates.HtmlFormat.Appendable = apply(index,containerForm,monitors,user)
    
    def f:((Long,Form[ContainerEditData],Seq[scala.Tuple2[String, String]],User) => play.api.templates.HtmlFormat.Appendable) = (index,containerForm,monitors,user) => apply(index,containerForm,monitors,user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:51:53 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/editForm.scala.html
                    HASH: 703985d1edbe38e895c5676fff1b17c83a4a1b72
                    MATRIX: 620->1|821->119|853->143|933->100|961->192|998->195|1023->212|1062->214|1212->329|1267->375|1307->377|1362->397|1529->543|1570->549|1737->695|1783->706|1938->840|1984->851|2140->986|2186->997|2362->1152|2567->1321|2582->1327|2645->1368|2715->1407|2785->1442|2862->1510|2902->1512|3014->1593|3078->1626
                    LINES: 19->1|22->3|22->3|23->1|24->3|26->5|26->5|26->5|33->12|33->12|33->12|35->14|39->18|40->19|44->23|46->25|50->29|52->31|56->35|58->37|63->42|70->49|70->49|70->49|72->51|74->53|74->53|74->53|76->55|80->59
                    -- GENERATED --
                */
            