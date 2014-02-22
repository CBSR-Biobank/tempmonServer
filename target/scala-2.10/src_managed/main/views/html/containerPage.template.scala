
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
  <a>"""),_display_(Seq[Any](/*25.7*/title)),format.raw/*25.12*/("""</a>
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
<sectionHeader style="height:20px">
  <div class="page-header" style="float:left;">
    """),_display_(Seq[Any](/*32.6*/container/*32.15*/.name)),format.raw/*32.20*/(""" #"""),_display_(Seq[Any](/*32.23*/index)),format.raw/*32.28*/("""'s temperature readings:
  </div>
  <a href=""""),_display_(Seq[Any](/*34.13*/routes/*34.19*/.ContainerController.edit(index))),format.raw/*34.51*/("""" class="page-header" style="float:right; margin-right:25%">
    Change this container's specifications 
  </a>
</sectionHeader>

<sectionBody style="width:75%;">
  <table class="containers zebra-striped">
    """),_display_(Seq[Any](/*41.6*/Option(currentPage.items)/*41.31*/.filterNot(_.isEmpty).map/*41.56*/ { readings =>_display_(Seq[Any](format.raw/*41.70*/(""" 
    <thead>
      <tr>
        """),_display_(Seq[Any](/*44.10*/header(1, "Temperature"))),format.raw/*44.34*/("""
        """),_display_(Seq[Any](/*45.10*/header(2, "Container Status"))),format.raw/*45.39*/("""
        """),_display_(Seq[Any](/*46.10*/header(3, "Read Time"))),format.raw/*46.32*/("""
      </tr>
    </thead>
    <tbody>
      """),_display_(Seq[Any](/*50.8*/readings/*50.16*/.map/*50.20*/ {/*51.7*/case reading =>/*51.22*/ {_display_(Seq[Any](format.raw/*51.24*/("""
      <tr>
	<td>
	  """),_display_(Seq[Any](/*54.5*/reading/*54.12*/.readTemperature)),format.raw/*54.28*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*57.5*/reading/*57.12*/.readStatus)),format.raw/*57.23*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*60.5*/reading/*60.12*/.readTime)),format.raw/*60.21*/("""
	</td>
      </tr>
      """)))}})),format.raw/*64.8*/("""    
    </tbody>
  </table>
</sectionBody>
<sectionFooter>
      <ul class="pagination" style="margin-left:20%; width=25%">
	"""),_display_(Seq[Any](/*70.3*/currentPage/*70.14*/.prev.map/*70.23*/ { page =>_display_(Seq[Any](format.raw/*70.33*/("""
	<li class="prev">
          <a href=""""),_display_(Seq[Any](/*72.21*/link(page))),format.raw/*72.31*/("""">&larr; More recent</a>
	</li> 
	""")))}/*74.3*/.getOrElse/*74.13*/ {_display_(Seq[Any](format.raw/*74.15*/("""
	<li class="prev disabled">
          <a>&larr; More recent</a>
	</li>
	""")))})),format.raw/*78.3*/("""
	<li class="current">
          <a>Displaying """),_display_(Seq[Any](/*80.26*/(currentPage.offset + 1))),format.raw/*80.50*/(""" to """),_display_(Seq[Any](/*80.55*/(currentPage.offset + readings.size))),format.raw/*80.91*/(""" of """),_display_(Seq[Any](/*80.96*/currentPage/*80.107*/.total)),format.raw/*80.113*/("""</a>
	</li>
	
	"""),_display_(Seq[Any](/*83.3*/currentPage/*83.14*/.next.map/*83.23*/ { page =>_display_(Seq[Any](format.raw/*83.33*/("""
	<li class="next">
          <a href=""""),_display_(Seq[Any](/*85.21*/link(page))),format.raw/*85.31*/("""">Less recent &rarr;</a>
	</li> 
	""")))}/*87.3*/.getOrElse/*87.13*/ {_display_(Seq[Any](format.raw/*87.15*/("""
	<li class="next disabled">
          <a>Less recent &rarr;</a>
	</li>          
	""")))})),format.raw/*91.3*/("""
      </ul>
</sectionFooter>
  """)))}/*94.4*/.getOrElse/*94.14*/ {_display_(Seq[Any](format.raw/*94.16*/("""
  <div class="well">
    <em style="margin-left:40%">Nothing to display</em>
  </div>
  """)))})),format.raw/*98.4*/("""  

""")))})),format.raw/*100.2*/("""
"""))}
    }
    
    def render(currentPage:Page[ContainerReadData],currentOrderBy:Int,container:Container,user:User,index:Long): play.api.templates.HtmlFormat.Appendable = apply(currentPage,currentOrderBy,container,user,index)
    
    def f:((Page[ContainerReadData],Int,Container,User,Long) => play.api.templates.HtmlFormat.Appendable) = (currentPage,currentOrderBy,container,user,index) => apply(currentPage,currentOrderBy,container,user,index)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Jan 09 17:43:16 MST 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/containerPage.scala.html
                    HASH: be778d1d31f272596ea5e272e6584ea53c24e759
                    MATRIX: 605->1|829->734|843->740|957->773|1004->785|1031->790|1074->126|1106->150|1169->354|1181->358|1476->106|1505->199|1533->226|1562->352|1590->622|1620->732|1648->802|1686->805|1712->822|1752->824|1877->914|1895->923|1922->928|1961->931|1988->936|2070->982|2085->988|2139->1020|2385->1231|2419->1256|2453->1281|2505->1295|2575->1329|2621->1353|2667->1363|2718->1392|2764->1402|2808->1424|2888->1469|2905->1477|2918->1481|2928->1491|2952->1506|2992->1508|3049->1530|3065->1537|3103->1553|3156->1571|3172->1578|3205->1589|3258->1607|3274->1614|3305->1623|3364->1658|3526->1785|3546->1796|3564->1805|3612->1815|3688->1855|3720->1865|3773->1900|3792->1910|3832->1912|3937->1986|4021->2034|4067->2058|4108->2063|4166->2099|4207->2104|4228->2115|4257->2121|4308->2137|4328->2148|4346->2157|4394->2167|4470->2207|4502->2217|4555->2252|4574->2262|4614->2264|4729->2348|4780->2381|4799->2391|4839->2393|4960->2483|4997->2488
                    LINES: 19->1|24->23|24->23|26->23|28->25|28->25|30->4|30->4|30->11|30->11|38->1|40->4|42->7|43->10|44->18|46->22|47->27|49->29|49->29|49->29|52->32|52->32|52->32|52->32|52->32|54->34|54->34|54->34|61->41|61->41|61->41|61->41|64->44|64->44|65->45|65->45|66->46|66->46|70->50|70->50|70->50|70->51|70->51|70->51|73->54|73->54|73->54|76->57|76->57|76->57|79->60|79->60|79->60|82->64|88->70|88->70|88->70|88->70|90->72|90->72|92->74|92->74|92->74|96->78|98->80|98->80|98->80|98->80|98->80|98->80|98->80|101->83|101->83|101->83|101->83|103->85|103->85|105->87|105->87|105->87|109->91|112->94|112->94|112->94|116->98|118->100
                    -- GENERATED --
                */
            