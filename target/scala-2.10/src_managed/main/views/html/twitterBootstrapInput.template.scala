
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
object twitterBootstrapInput extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[helper.FieldElements,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(elements: helper.FieldElements):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.34*/("""

"""),format.raw/*5.52*/("""
<div class="clearfix """),_display_(Seq[Any](/*6.23*/if(elements.hasErrors)/*6.45*/ {_display_(Seq[Any](format.raw/*6.47*/("""error""")))})),format.raw/*6.53*/("""">
  <label for=""""),_display_(Seq[Any](/*7.16*/elements/*7.24*/.id)),format.raw/*7.27*/("""">"""),_display_(Seq[Any](/*7.30*/elements/*7.38*/.label)),format.raw/*7.44*/("""</label>
  <div class="input">
    """),_display_(Seq[Any](/*9.6*/elements/*9.14*/.input)),format.raw/*9.20*/("""
    <span class="help-inline">"""),_display_(Seq[Any](/*10.32*/elements/*10.40*/.infos.mkString(", "))),format.raw/*10.61*/("""</span> 
  </div>
</div>
"""))}
    }
    
    def render(elements:helper.FieldElements): play.api.templates.HtmlFormat.Appendable = apply(elements)
    
    def f:((helper.FieldElements) => play.api.templates.HtmlFormat.Appendable) = (elements) => apply(elements)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:39:20 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/twitterBootstrapInput.scala.html
                    HASH: b24383b093ce286d76257a9cad1f4eb9b035e91a
                    MATRIX: 586->1|712->33|741->191|799->214|829->236|868->238|905->244|958->262|974->270|998->273|1036->276|1052->284|1079->290|1149->326|1165->334|1192->340|1260->372|1277->380|1320->401
                    LINES: 19->1|22->1|24->5|25->6|25->6|25->6|25->6|26->7|26->7|26->7|26->7|26->7|26->7|28->9|28->9|28->9|29->10|29->10|29->10
                    -- GENERATED --
                */
            