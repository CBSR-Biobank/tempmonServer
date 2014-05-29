
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
  <div class="page-header" style="float:left;">
    """),_display_(Seq[Any](/*32.6*/container/*32.15*/.name)),format.raw/*32.20*/(""" #"""),_display_(Seq[Any](/*32.23*/index)),format.raw/*32.28*/("""'s temperature readings:
  </div>
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
                    DATE: Fri May 23 15:47:16 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/containerPage.scala.html
                    HASH: 639faae922b5eca34857dba2820dc512ea6fe6e3
                    MATRIX: 605->1|829->734|843->740|957->773|1008->788|1035->793|1081->126|1113->150|1176->354|1188->358|1483->106|1512->199|1540->226|1569->352|1597->622|1627->732|1655->808|1693->811|1719->828|1759->830|1905->941|1923->950|1950->955|1989->958|2016->963|2145->1056|2160->1062|2214->1094|2407->1252|2441->1277|2475->1302|2527->1316|2597->1350|2643->1374|2689->1384|2740->1413|2786->1423|2830->1445|2868->1448|2908->1466|2988->1511|3005->1519|3018->1523|3028->1533|3052->1548|3092->1550|3149->1572|3165->1579|3203->1595|3256->1613|3272->1620|3305->1631|3358->1649|3374->1656|3405->1665|3525->1750|3541->1757|3572->1766|3635->1793|3650->1799|3724->1851|3847->1950|4009->2077|4029->2088|4047->2097|4095->2107|4171->2147|4203->2157|4256->2192|4275->2202|4315->2204|4420->2278|4504->2326|4550->2350|4591->2355|4649->2391|4690->2396|4711->2407|4740->2413|4791->2429|4811->2440|4829->2449|4877->2459|4953->2499|4985->2509|5038->2544|5057->2554|5097->2556|5213->2640|5265->2673|5285->2683|5326->2685|5448->2775|5485->2780
                    LINES: 19->1|24->23|24->23|26->23|28->25|28->25|30->4|30->4|30->11|30->11|38->1|40->4|42->7|43->10|44->18|46->22|47->27|49->29|49->29|49->29|52->32|52->32|52->32|52->32|52->32|55->35|55->35|55->35|63->43|63->43|63->43|63->43|66->46|66->46|67->47|67->47|68->48|68->48|69->49|69->49|73->53|73->53|73->53|73->54|73->54|73->54|76->57|76->57|76->57|79->60|79->60|79->60|82->63|82->63|82->63|85->66|85->66|85->66|88->69|88->69|88->69|93->75|99->81|99->81|99->81|99->81|101->83|101->83|103->85|103->85|103->85|107->89|109->91|109->91|109->91|109->91|109->91|109->91|109->91|112->94|112->94|112->94|112->94|114->96|114->96|116->98|116->98|116->98|120->102|123->105|123->105|123->105|127->109|129->111
                    -- GENERATED --
                */
            