
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
object list extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template5[Page[ContainerListView],Int,String,User,play.api.mvc.Flash,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(currentPage: Page[ContainerListView], currentOrderBy: Int, currentFilter: String, user: User)(implicit flash: play.api.mvc.Flash):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import java.util.{Date}

def /*21.2*/header/*21.8*/(orderBy: Int, title: String):play.api.templates.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*21.41*/("""
<th>
  <span>"""),_display_(Seq[Any](/*23.10*/title)),format.raw/*23.15*/("""</span>
</th>
""")))};def /*8.2*/link/*8.6*/(newPage: Int, newOrderBy: Option[Int] = None) = {{
  routes.ContainerController.list(
    newPage, 
    newOrderBy.map { orderBy =>
      if(orderBy == scala.math.abs(currentOrderBy)) -currentOrderBy else orderBy
    }.getOrElse(currentOrderBy), 
    currentFilter
  ) 
}};
Seq[Any](format.raw/*1.132*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*7.42*/("""
"""),format.raw/*16.2*/("""

"""),format.raw/*20.37*/("""
"""),format.raw/*25.2*/("""

"""),_display_(Seq[Any](/*27.2*/main(user = user)/*27.19*/ {_display_(Seq[Any](format.raw/*27.21*/("""
<sectionHeader>
  <headerLeft>
    """),_display_(Seq[Any](/*30.6*/helper/*30.12*/.form(action=routes.ContainerController.list())/*30.59*/ {_display_(Seq[Any](format.raw/*30.61*/("""
    <input type="search" id="searchbox" name="f" value=""""),_display_(Seq[Any](/*31.58*/currentFilter)),format.raw/*31.71*/("""" placeholder="Filter by container name...">
    <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
    """)))})),format.raw/*33.6*/("""
  </headerLeft>
  <headerRight>
    <a class="btn success" id="add" href=""""),_display_(Seq[Any](/*36.44*/routes/*36.50*/.ContainerController.create())),format.raw/*36.79*/("""">
      Add a new container
    </a> 
  </headerRight>
</sectionHeader>

<sectionSubHeader>
  """),_display_(Seq[Any](/*43.4*/flash/*43.9*/.get("success").map/*43.28*/ { message =>_display_(Seq[Any](format.raw/*43.41*/("""
  <div class="alert-message warning">
    <strong>Done!</strong> """),_display_(Seq[Any](/*45.29*/message)),format.raw/*45.36*/("""
  </div>
  """)))})),format.raw/*47.4*/("""
</sectionSubHeader>

<sectionBody>
  <table class="containers zebra-striped">
    """),_display_(Seq[Any](/*52.6*/Option(currentPage.items)/*52.31*/.filterNot(_.isEmpty).map/*52.56*/ { containers =>_display_(Seq[Any](format.raw/*52.72*/(""" 
    <thead>
      <tr>
	"""),_display_(Seq[Any](/*55.3*/header(1, "Container ID"))),format.raw/*55.28*/("""
	"""),_display_(Seq[Any](/*56.3*/header(2, "Container Name"))),format.raw/*56.30*/("""
        """),_display_(Seq[Any](/*57.10*/header(3, "Temperature"))),format.raw/*57.34*/("""
        """),_display_(Seq[Any](/*58.10*/header(4, "Container Status"))),format.raw/*58.39*/("""
        """),_display_(Seq[Any](/*59.10*/header(5, "Last Read Time"))),format.raw/*59.37*/("""
      </tr>
    </thead>
    <tbody>
      """),_display_(Seq[Any](/*63.8*/containers/*63.18*/.map/*63.22*/ {/*64.7*/case container =>/*64.24*/ {_display_(Seq[Any](format.raw/*64.26*/("""
      <tr>
	<td>
	  """),_display_(Seq[Any](/*67.5*/container/*67.14*/.index)),format.raw/*67.20*/("""
	</td>
        <td> 
	  <a href=""""),_display_(Seq[Any](/*70.14*/routes/*70.20*/.ContainerController.details(index=container.index))),format.raw/*70.71*/("""">
	    """),_display_(Seq[Any](/*71.7*/container/*71.16*/.name)),format.raw/*71.21*/("""
	  </a> 
	</td>
	<td>
	  """),_display_(Seq[Any](/*75.5*/container/*75.14*/.lastReadTemperature.getOrElse/*75.44*/ {_display_(Seq[Any](format.raw/*75.46*/(""" <em>-</em> """)))})),format.raw/*75.59*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*78.5*/container/*78.14*/.lastReadStatus.getOrElse/*78.39*/ {_display_(Seq[Any](format.raw/*78.41*/(""" <em>-</em> """)))})),format.raw/*78.54*/("""
	</td>
	<td>
	  """),_display_(Seq[Any](/*81.5*/container/*81.14*/.lastReadTime.getOrElse/*81.37*/ {_display_(Seq[Any](format.raw/*81.39*/(""" <em>-</em> """)))})),format.raw/*81.52*/("""
	</td>
      </tr>
      """)))}})),format.raw/*85.8*/("""    
    </tbody>
  </table>
</sectionBody>
<sectionFooter style="margin-left: 35%">
      <ul class="pagination">
	"""),_display_(Seq[Any](/*91.3*/currentPage/*91.14*/.prev.map/*91.23*/ { page =>_display_(Seq[Any](format.raw/*91.33*/("""
	<li class="prev">
          <a href=""""),_display_(Seq[Any](/*93.21*/link(page))),format.raw/*93.31*/("""">&larr; Previous</a>
	</li> 
	""")))}/*95.3*/.getOrElse/*95.13*/ {_display_(Seq[Any](format.raw/*95.15*/("""
	<li class="prev disabled">
          <a>&larr; Previous</a>
	</li>
	""")))})),format.raw/*99.3*/("""
	<li class="current">
          <a>Displaying """),_display_(Seq[Any](/*101.26*/(currentPage.offset + 1))),format.raw/*101.50*/(""" to """),_display_(Seq[Any](/*101.55*/(currentPage.offset + containers.size))),format.raw/*101.93*/(""" of """),_display_(Seq[Any](/*101.98*/currentPage/*101.109*/.total)),format.raw/*101.115*/("""</a>
	</li>	
	"""),_display_(Seq[Any](/*103.3*/currentPage/*103.14*/.next.map/*103.23*/ { page =>_display_(Seq[Any](format.raw/*103.33*/("""
	<li class="next">
          <a href=""""),_display_(Seq[Any](/*105.21*/link(page))),format.raw/*105.31*/("""">Next &rarr;</a>
	</li> 
	""")))}/*107.3*/.getOrElse/*107.13*/ {_display_(Seq[Any](format.raw/*107.15*/("""
	<li class="next disabled">
          <a>Next &rarr;</a>
	</li>          
	""")))})),format.raw/*111.3*/("""
      </ul>
</sectionFooter>
  """)))}/*114.4*/.getOrElse/*114.14*/ {_display_(Seq[Any](format.raw/*114.16*/("""
  <div class="well">
    <em style="margin-left:45%">Nothing to display</em>
  </div>
  """)))})),format.raw/*118.4*/("""  
""")))})),format.raw/*119.2*/("""
      
      
      
"""))}
    }
    
    def render(currentPage:Page[ContainerListView],currentOrderBy:Int,currentFilter:String,user:User,flash:play.api.mvc.Flash): play.api.templates.HtmlFormat.Appendable = apply(currentPage,currentOrderBy,currentFilter,user)(flash)
    
    def f:((Page[ContainerListView],Int,String,User) => (play.api.mvc.Flash) => play.api.templates.HtmlFormat.Appendable) = (currentPage,currentOrderBy,currentFilter,user) => (flash) => apply(currentPage,currentOrderBy,currentFilter,user)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 04 10:50:19 MDT 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/list.scala.html
                    HASH: 8198a451abe30b0f784a6cc902ad51c6949a54a6
                    MATRIX: 607->1|839->674|853->680|967->713|1018->728|1045->733|1082->286|1093->290|1396->131|1424->158|1452->284|1480->562|1510->672|1538->748|1576->751|1602->768|1642->770|1714->807|1729->813|1785->860|1825->862|1919->920|1954->933|2122->1070|2234->1146|2249->1152|2300->1181|2431->1277|2444->1282|2472->1301|2523->1314|2626->1381|2655->1388|2699->1401|2818->1485|2852->1510|2886->1535|2940->1551|3002->1578|3049->1603|3087->1606|3136->1633|3182->1643|3228->1667|3274->1677|3325->1706|3371->1716|3420->1743|3500->1788|3519->1798|3532->1802|3542->1812|3568->1829|3608->1831|3665->1853|3683->1862|3711->1868|3782->1903|3797->1909|3870->1960|3914->1969|3932->1978|3959->1983|4021->2010|4039->2019|4078->2049|4118->2051|4163->2064|4216->2082|4234->2091|4268->2116|4308->2118|4353->2131|4406->2149|4424->2158|4456->2181|4496->2183|4541->2196|4600->2231|4752->2348|4772->2359|4790->2368|4838->2378|4914->2418|4946->2428|4996->2460|5015->2470|5055->2472|5157->2543|5242->2591|5289->2615|5331->2620|5392->2658|5434->2663|5456->2674|5486->2680|5537->2695|5558->2706|5577->2715|5626->2725|5703->2765|5736->2775|5783->2803|5803->2813|5844->2815|5953->2892|6005->2925|6025->2935|6066->2937|6188->3027|6224->3031
                    LINES: 19->1|22->21|22->21|24->21|26->23|26->23|28->8|28->8|37->1|39->4|40->7|41->16|43->20|44->25|46->27|46->27|46->27|49->30|49->30|49->30|49->30|50->31|50->31|52->33|55->36|55->36|55->36|62->43|62->43|62->43|62->43|64->45|64->45|66->47|71->52|71->52|71->52|71->52|74->55|74->55|75->56|75->56|76->57|76->57|77->58|77->58|78->59|78->59|82->63|82->63|82->63|82->64|82->64|82->64|85->67|85->67|85->67|88->70|88->70|88->70|89->71|89->71|89->71|93->75|93->75|93->75|93->75|93->75|96->78|96->78|96->78|96->78|96->78|99->81|99->81|99->81|99->81|99->81|102->85|108->91|108->91|108->91|108->91|110->93|110->93|112->95|112->95|112->95|116->99|118->101|118->101|118->101|118->101|118->101|118->101|118->101|120->103|120->103|120->103|120->103|122->105|122->105|124->107|124->107|124->107|128->111|131->114|131->114|131->114|135->118|136->119
                    -- GENERATED --
                */
            