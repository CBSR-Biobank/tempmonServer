
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
object containerPage extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template5[Page[ContainerReadData],Int,Container,User,Long,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(currentPage: Page[ContainerReadData], currentOrderBy:Int, container: Container, user: User, index: Long):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

import java.util.{Date}

def /*23.2*/header/*23.8*/(orderBy: Int, title: String):play.api.templates.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*23.41*/("""
<th>
  <span>"""),_display_(Seq[Any](/*25.10*/title)),format.raw/*25.15*/("""</span>
</th>
""")))};implicit def /*4.2*/implicitFieldConstructor/*4.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};def /*11.2*/link/*11.6*/(newPage: Int, newOrderBy: Option[Int] = None) = {{
  routes.ContainerController.details(
    index, 
    newPage, 
    newOrderBy.map { orderBy =>
      if(orderBy == scala.math.abs(currentOrderBy)) -currentOrderBy else orderBy
    }.getOrElse(currentOrderBy)) 
}};
Seq[Any](format.raw/*1.107*/("""

"""),format.raw/*4.75*/("""

"""),format.raw/*7.1*/("""
"""),format.raw/*10.42*/("""
"""),format.raw/*18.2*/("""

"""),format.raw/*22.37*/("""
"""),format.raw/*27.2*/("""

"""),_display_(Seq[Any](/*29.2*/main(user = user)/*29.19*/ {_display_(Seq[Any](format.raw/*29.21*/("""
<sectionHeader style="height:20px; margin-bottom:18px;">
  <headerLeft>
    """),_display_(Seq[Any](/*32.6*/container/*32.15*/.name)),format.raw/*32.20*/(""" #"""),_display_(Seq[Any](/*32.23*/index)),format.raw/*32.28*/("""
  </headerLeft>
  <headerRight>
    <a class="btn success" id="add" href=""""),_display_(Seq[Any](/*35.44*/routes/*35.50*/.ContainerController.edit(index))),format.raw/*35.82*/("""" style="margin-bottom:18px">
      Edit container
    </a> 
  </headerRight>
</sectionHeader>

<sectionBody>
  <table class="containers zebra-striped">
    """),_display_(Seq[Any](/*43.6*/Option(currentPage.items)/*43.31*/.filterNot(_.isEmpty).map/*43.56*/ { readings =>_display_(Seq[Any](format.raw/*43.70*/(""" 
    <thead>
      <tr>
        """),_display_(Seq[Any](/*46.10*/header(1, "Temperature"))),format.raw/*46.34*/("""
        """),_display_(Seq[Any](/*47.10*/header(2, "Container Status"))),format.raw/*47.39*/("""
        """),_display_(Seq[Any](/*48.10*/header(3, "Read Time"))),format.raw/*48.32*/("""
	"""),_display_(Seq[Any](/*49.3*/header(4, "Notes"))),format.raw/*49.21*/("""
      </tr>
    </thead>
    <tbody>
      """),_display_(Seq[Any](/*53.8*/readings/*53.16*/.map/*53.20*/ {/*54.7*/case reading =>/*54.22*/ {_display_(Seq[Any](format.raw/*54.24*/("""
      <tr>
	<td>
	  """),_display_(Seq[Any](/*57.5*/reading/*57.12*/.readTemperature)),format.raw/*57.28*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*60.5*/reading/*60.12*/.readStatus)),format.raw/*60.23*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*63.5*/reading/*63.12*/.readTime)),format.raw/*63.21*/("""
	</td>
	<td style="max-width:200px; overflow: hidden; text-overflow:ellipsis;">
	  """),_display_(Seq[Any](/*66.5*/reading/*66.12*/.readNote)),format.raw/*66.21*/("""
	</td>
	<td>
	  <a href=""""),_display_(Seq[Any](/*69.14*/routes/*69.20*/.ContainerController.editNote(index, reading.readID))),format.raw/*69.72*/("""" style="float:right" title="Change or add note">
	    +
	  </a>
	</td>
      </tr>
      """)))}})),format.raw/*75.8*/("""    
    </tbody>
  </table>
</sectionBody>
<sectionFooter>
      <ul class="pagination" style="margin-left:30%; width=25%">
	"""),_display_(Seq[Any](/*81.3*/currentPage/*81.14*/.prev.map/*81.23*/ { page =>_display_(Seq[Any](format.raw/*81.33*/("""
	<li class="prev">
          <a href=""""),_display_(Seq[Any](/*83.21*/link(page))),format.raw/*83.31*/("""">&larr; More recent</a>
	</li> 
	""")))}/*85.3*/.getOrElse/*85.13*/ {_display_(Seq[Any](format.raw/*85.15*/("""
	<li class="prev disabled">
          <a>&larr; More recent</a>
	</li>
	""")))})),format.raw/*89.3*/("""
	<li class="current">
          <a>Displaying """),_display_(Seq[Any](/*91.26*/(currentPage.offset + 1))),format.raw/*91.50*/(""" to """),_display_(Seq[Any](/*91.55*/(currentPage.offset + readings.size))),format.raw/*91.91*/(""" of """),_display_(Seq[Any](/*91.96*/currentPage/*91.107*/.total)),format.raw/*91.113*/("""</a>
	</li>
	
	"""),_display_(Seq[Any](/*94.3*/currentPage/*94.14*/.next.map/*94.23*/ { page =>_display_(Seq[Any](format.raw/*94.33*/("""
	<li class="next">
          <a href=""""),_display_(Seq[Any](/*96.21*/link(page))),format.raw/*96.31*/("""">Less recent &rarr;</a>
	</li> 
	""")))}/*98.3*/.getOrElse/*98.13*/ {_display_(Seq[Any](format.raw/*98.15*/("""
	<li class="next disabled">
          <a>Less recent &rarr;</a>
	</li>          
	""")))})),format.raw/*102.3*/("""
      </ul>
</sectionFooter>
  """)))}/*105.4*/.getOrElse/*105.14*/ {_display_(Seq[Any](format.raw/*105.16*/("""
  <div class="well">
    <em style="margin-left:40%">Nothing to display</em>
  </div>
  """)))})),format.raw/*109.4*/("""  

""")))})),format.raw/*111.2*/("""
"""))}
    }
    
    def render(currentPage:Page[ContainerReadData],currentOrderBy:Int,container:Container,user:User,index:Long): play.api.templates.HtmlFormat.Appendable = apply(currentPage,currentOrderBy,container,user,index)
    
    def f:((Page[ContainerReadData],Int,Container,User,Long) => play.api.templates.HtmlFormat.Appendable) = (currentPage,currentOrderBy,container,user,index) => apply(currentPage,currentOrderBy,container,user,index)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:39:20 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/containerPage.scala.html
                    HASH: ea2b00758deb6d8701b217b380b17ca86c8441df
                    MATRIX: 605->1|829->734|843->740|957->773|1008->788|1035->793|1081->126|1113->150|1176->354|1188->358|1483->106|1512->199|1540->226|1569->352|1597->622|1627->732|1655->808|1693->811|1719->828|1759->830|1872->908|1890->917|1917->922|1956->925|1983->930|2095->1006|2110->1012|2164->1044|2357->1202|2391->1227|2425->1252|2477->1266|2547->1300|2593->1324|2639->1334|2690->1363|2736->1373|2780->1395|2818->1398|2858->1416|2938->1461|2955->1469|2968->1473|2978->1483|3002->1498|3042->1500|3099->1522|3115->1529|3153->1545|3206->1563|3222->1570|3255->1581|3308->1599|3324->1606|3355->1615|3475->1700|3491->1707|3522->1716|3585->1743|3600->1749|3674->1801|3797->1900|3959->2027|3979->2038|3997->2047|4045->2057|4121->2097|4153->2107|4206->2142|4225->2152|4265->2154|4370->2228|4454->2276|4500->2300|4541->2305|4599->2341|4640->2346|4661->2357|4690->2363|4741->2379|4761->2390|4779->2399|4827->2409|4903->2449|4935->2459|4988->2494|5007->2504|5047->2506|5163->2590|5215->2623|5235->2633|5276->2635|5398->2725|5435->2730
                    LINES: 19->1|24->23|24->23|26->23|28->25|28->25|30->4|30->4|30->11|30->11|38->1|40->4|42->7|43->10|44->18|46->22|47->27|49->29|49->29|49->29|52->32|52->32|52->32|52->32|52->32|55->35|55->35|55->35|63->43|63->43|63->43|63->43|66->46|66->46|67->47|67->47|68->48|68->48|69->49|69->49|73->53|73->53|73->53|73->54|73->54|73->54|76->57|76->57|76->57|79->60|79->60|79->60|82->63|82->63|82->63|85->66|85->66|85->66|88->69|88->69|88->69|93->75|99->81|99->81|99->81|99->81|101->83|101->83|103->85|103->85|103->85|107->89|109->91|109->91|109->91|109->91|109->91|109->91|109->91|112->94|112->94|112->94|112->94|114->96|114->96|116->98|116->98|116->98|120->102|123->105|123->105|123->105|127->109|129->111
                    -- GENERATED --
                */
            