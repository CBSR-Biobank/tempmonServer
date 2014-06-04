
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
object editNoteForm extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template8[Long,Long,Double,String,String,Option[String],Form[ContainerNote],User,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(index: Long, readID: Long, temperature: Double, status: String, time: String, note: Option[String], noteForm: Form[ContainerNote], user: User):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import java.util.{Date}

import helper._

def /*8.2*/header/*8.8*/(orderBy: Int, title: String):play.api.templates.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.41*/("""
<th>
  <a>"""),_display_(Seq[Any](/*10.7*/title)),format.raw/*10.12*/("""</a>
</th>
""")))};implicit def /*6.2*/implicitFieldConstructor/*6.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};
Seq[Any](format.raw/*1.145*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*6.75*/("""

"""),format.raw/*12.2*/("""

"""),_display_(Seq[Any](/*14.2*/main(user = user)/*14.19*/ {_display_(Seq[Any](format.raw/*14.21*/("""
<sectionHeader>
  <headerLeft>
    Edit Note
  </headerLeft>
</sectionHeader>
<sectionBody>
  <table class="containers zebra-striped">
    <thead>
      """),_display_(Seq[Any](/*23.8*/header(1, "Temperature"))),format.raw/*23.32*/("""
      """),_display_(Seq[Any](/*24.8*/header(2, "Container Status"))),format.raw/*24.37*/("""
      """),_display_(Seq[Any](/*25.8*/header(3, "Read Time"))),format.raw/*25.30*/("""
      """),_display_(Seq[Any](/*26.8*/header(4, "Notes"))),format.raw/*26.26*/("""
    </thead>
    <tbody>
      <tr>
	<td>
	  """),_display_(Seq[Any](/*31.5*/temperature)),format.raw/*31.16*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*34.5*/status)),format.raw/*34.11*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*37.5*/time)),format.raw/*37.9*/("""
	</td>
	<td>
	  ...
	</td>
      </tr>
    </tbody>
  </table> 
  <fieldset>
  """),_display_(Seq[Any](/*46.4*/helper/*46.10*/.form(action = routes.ContainerController.updateNote(index, readID))/*46.78*/{_display_(Seq[Any](format.raw/*46.79*/("""
    """),_display_(Seq[Any](/*47.6*/if(note.isDefined)/*47.24*/ {_display_(Seq[Any](format.raw/*47.26*/("""
      <div class="clearfix">
	<label>Note:</label>
	<div class="input" style="width:700px;">"""),_display_(Seq[Any](/*50.43*/note)),format.raw/*50.47*/("""</div>
      </div>
    """)))})),format.raw/*52.6*/("""
    """),_display_(Seq[Any](/*53.6*/helper/*53.12*/.inputText(
      noteForm("note"), 
      'style -> "width: 700px;", 
      '_showConstraints -> false,
      '_label -> "Add note:"
    ))),format.raw/*58.6*/("""
    <div style="display:inline; margin-left:58.3%;">
      <input type="submit" value="Save" class="btn primary">
      <a onclick="goBack()" class="btn">Cancel</a>
    </div>
  """)))})),format.raw/*63.4*/("""
  </fieldset>
</sectionBody>
""")))})),format.raw/*66.2*/("""
"""))}
    }
    
    def render(index:Long,readID:Long,temperature:Double,status:String,time:String,note:Option[String],noteForm:Form[ContainerNote],user:User): play.api.templates.HtmlFormat.Appendable = apply(index,readID,temperature,status,time,note,noteForm,user)
    
    def f:((Long,Long,Double,String,String,Option[String],Form[ContainerNote],User) => play.api.templates.HtmlFormat.Appendable) = (index,readID,temperature,status,time,note,noteForm,user) => apply(index,readID,temperature,status,time,note,noteForm,user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:39:20 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/editNoteForm.scala.html
                    HASH: ad0d88cacada3f73ff7e022c53d31b0f5ea10a3d
                    MATRIX: 627->1|888->266|901->272|1014->305|1061->317|1088->322|1131->190|1163->214|1243->144|1271->188|1299->263|1328->334|1366->337|1392->354|1432->356|1622->511|1668->535|1711->543|1762->572|1805->580|1849->602|1892->610|1932->628|2014->675|2047->686|2100->704|2128->710|2181->728|2206->732|2322->813|2337->819|2414->887|2453->888|2494->894|2521->912|2561->914|2691->1008|2717->1012|2773->1037|2814->1043|2829->1049|2989->1188|3200->1368|3262->1399
                    LINES: 19->1|24->8|24->8|26->8|28->10|28->10|30->6|30->6|31->1|33->5|34->6|36->12|38->14|38->14|38->14|47->23|47->23|48->24|48->24|49->25|49->25|50->26|50->26|55->31|55->31|58->34|58->34|61->37|61->37|70->46|70->46|70->46|70->46|71->47|71->47|71->47|74->50|74->50|76->52|77->53|77->53|82->58|87->63|90->66
                    -- GENERATED --
                */
            