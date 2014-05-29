
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
object editNoteForm extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template7[Long,Long,Double,String,String,Form[ContainerNote],User,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(index: Long, readID: Long, temperature: Double, status: String, time: String, noteForm: Form[ContainerNote], user: User):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import java.util.{Date}

import helper._

def /*8.2*/header/*8.8*/(orderBy: Int, title: String):play.api.templates.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.41*/("""
<th>
  <a>"""),_display_(Seq[Any](/*10.7*/title)),format.raw/*10.12*/("""</a>
</th>
""")))};implicit def /*6.2*/implicitFieldConstructor/*6.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};
Seq[Any](format.raw/*1.123*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*6.75*/("""

"""),format.raw/*12.2*/("""

"""),_display_(Seq[Any](/*14.2*/main(user = user)/*14.19*/ {_display_(Seq[Any](format.raw/*14.21*/("""
<sectionBody>
  <table class="containers zebra-striped">
    <thead>
      """),_display_(Seq[Any](/*18.8*/header(1, "Temperature"))),format.raw/*18.32*/("""
      """),_display_(Seq[Any](/*19.8*/header(2, "Container Status"))),format.raw/*19.37*/("""
      """),_display_(Seq[Any](/*20.8*/header(3, "Read Time"))),format.raw/*20.30*/("""
      """),_display_(Seq[Any](/*21.8*/header(4, "Notes"))),format.raw/*21.26*/("""
    </thead>
    <tbody>
      <tr>
	<td>
	  """),_display_(Seq[Any](/*26.5*/temperature)),format.raw/*26.16*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*29.5*/status)),format.raw/*29.11*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*32.5*/time)),format.raw/*32.9*/("""
	</td>
	<td>
	  ...
	</td>
      </tr>
    </tbody>
  </table>
  """),_display_(Seq[Any](/*40.4*/helper/*40.10*/.form(action = routes.ContainerController.updateNote(index, readID))/*40.78*/ {_display_(Seq[Any](format.raw/*40.80*/("""
    """),_display_(Seq[Any](/*41.6*/helper/*41.12*/.inputText(
      noteForm("note"), 
      'style -> "width: 700px; margin-left:12.5%;", 
      'placeholder -> "Add note here",
      '_label -> null
     ))),format.raw/*46.7*/("""
  <div style="margin-left:68%">
    <a onclick="goBack()" class="btn">Cancel</a>
    <input type="submit" value="Save" class="btn primary">
  </div>
   """)))})),format.raw/*51.5*/("""
</sectionBody>
""")))})),format.raw/*53.2*/("""
"""))}
    }
    
    def render(index:Long,readID:Long,temperature:Double,status:String,time:String,noteForm:Form[ContainerNote],user:User): play.api.templates.HtmlFormat.Appendable = apply(index,readID,temperature,status,time,noteForm,user)
    
    def f:((Long,Long,Double,String,String,Form[ContainerNote],User) => play.api.templates.HtmlFormat.Appendable) = (index,readID,temperature,status,time,noteForm,user) => apply(index,readID,temperature,status,time,noteForm,user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon May 26 17:39:13 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/editNoteForm.scala.html
                    HASH: 0622cbe56a2422c1e2d10b7bc7dc1322cee4f89d
                    MATRIX: 612->1|851->244|864->250|977->283|1024->295|1051->300|1094->168|1126->192|1206->122|1234->166|1262->241|1291->312|1329->315|1355->332|1395->334|1507->411|1553->435|1596->443|1647->472|1690->480|1734->502|1777->510|1817->528|1899->575|1932->586|1985->604|2013->610|2066->628|2091->632|2193->699|2208->705|2285->773|2325->775|2366->781|2381->787|2559->944|2744->1098|2792->1115
                    LINES: 19->1|24->8|24->8|26->8|28->10|28->10|30->6|30->6|31->1|33->5|34->6|36->12|38->14|38->14|38->14|42->18|42->18|43->19|43->19|44->20|44->20|45->21|45->21|50->26|50->26|53->29|53->29|56->32|56->32|64->40|64->40|64->40|64->40|65->41|65->41|70->46|75->51|77->53
                    -- GENERATED --
                */
            