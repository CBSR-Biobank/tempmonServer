
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
<th class="col"""),_display_(Seq[Any](/*22.16*/orderBy)),format.raw/*22.23*/(""" header """),_display_(Seq[Any](/*22.32*/if(scala.math.abs(currentOrderBy) == orderBy){/*22.79*/{if(currentOrderBy < 0) "headerSortDown" else "headerSortUp"}})),format.raw/*22.140*/("""">
  <a href=""""),_display_(Seq[Any](/*23.13*/link(0, Some(orderBy)))),format.raw/*23.35*/("""">"""),_display_(Seq[Any](/*23.38*/title)),format.raw/*23.43*/("""</a>
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
<sectionFooter>
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
	
	"""),_display_(Seq[Any](/*104.3*/currentPage/*104.14*/.next.map/*104.23*/ { page =>_display_(Seq[Any](format.raw/*104.33*/("""
	<li class="next">
          <a href=""""),_display_(Seq[Any](/*106.21*/link(page))),format.raw/*106.31*/("""">Next &rarr;</a>
	</li> 
	""")))}/*108.3*/.getOrElse/*108.13*/ {_display_(Seq[Any](format.raw/*108.15*/("""
	<li class="next disabled">
          <a>Next &rarr;</a>
	</li>          
	""")))})),format.raw/*112.3*/("""
      </ul>
</sectionFooter>
  """)))}/*115.4*/.getOrElse/*115.14*/ {_display_(Seq[Any](format.raw/*115.16*/("""
  <div class="well">
    <em>Nothing to display</em>
  </div>
  """)))})),format.raw/*119.4*/("""  
""")))})),format.raw/*120.2*/("""
      
      
      
"""))}
    }
    
    def render(currentPage:Page[ContainerListView],currentOrderBy:Int,currentFilter:String,user:User,flash:play.api.mvc.Flash): play.api.templates.HtmlFormat.Appendable = apply(currentPage,currentOrderBy,currentFilter,user)(flash)
    
    def f:((Page[ContainerListView],Int,String,User) => (play.api.mvc.Flash) => play.api.templates.HtmlFormat.Appendable) = (currentPage,currentOrderBy,currentFilter,user) => (flash) => apply(currentPage,currentOrderBy,currentFilter,user)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Jan 09 17:31:58 MST 2014
                    SOURCE: /home/connor/workspace/ScalaTempmon/app/views/list.scala.html
                    HASH: 2a9b98e2d66000c4d6f7f14c52285dda1cc34924
                    MATRIX: 607->1|839->674|853->680|967->713|1019->729|1048->736|1093->745|1148->792|1233->853|1284->868|1328->890|1367->893|1394->898|1428->286|1439->290|1742->131|1770->158|1798->284|1826->562|1856->672|1884->910|1922->913|1948->930|1988->932|2060->969|2075->975|2131->1022|2171->1024|2265->1082|2300->1095|2468->1232|2580->1308|2595->1314|2646->1343|2777->1439|2790->1444|2818->1463|2869->1476|2972->1543|3001->1550|3045->1563|3164->1647|3198->1672|3232->1697|3286->1713|3348->1740|3395->1765|3433->1768|3482->1795|3528->1805|3574->1829|3620->1839|3671->1868|3717->1878|3766->1905|3846->1950|3865->1960|3878->1964|3888->1974|3914->1991|3954->1993|4011->2015|4029->2024|4057->2030|4128->2065|4143->2071|4216->2122|4260->2131|4278->2140|4305->2145|4367->2172|4385->2181|4424->2211|4464->2213|4509->2226|4562->2244|4580->2253|4614->2278|4654->2280|4699->2293|4752->2311|4770->2320|4802->2343|4842->2345|4887->2358|4946->2393|5073->2485|5093->2496|5111->2505|5159->2515|5235->2555|5267->2565|5317->2597|5336->2607|5376->2609|5478->2680|5563->2728|5610->2752|5652->2757|5713->2795|5755->2800|5777->2811|5807->2817|5859->2833|5880->2844|5899->2853|5948->2863|6025->2903|6058->2913|6105->2941|6125->2951|6166->2953|6275->3030|6327->3063|6347->3073|6388->3075|6486->3141|6522->3145
                    LINES: 19->1|22->21|22->21|24->21|25->22|25->22|25->22|25->22|25->22|26->23|26->23|26->23|26->23|28->8|28->8|37->1|39->4|40->7|41->16|43->20|44->25|46->27|46->27|46->27|49->30|49->30|49->30|49->30|50->31|50->31|52->33|55->36|55->36|55->36|62->43|62->43|62->43|62->43|64->45|64->45|66->47|71->52|71->52|71->52|71->52|74->55|74->55|75->56|75->56|76->57|76->57|77->58|77->58|78->59|78->59|82->63|82->63|82->63|82->64|82->64|82->64|85->67|85->67|85->67|88->70|88->70|88->70|89->71|89->71|89->71|93->75|93->75|93->75|93->75|93->75|96->78|96->78|96->78|96->78|96->78|99->81|99->81|99->81|99->81|99->81|102->85|108->91|108->91|108->91|108->91|110->93|110->93|112->95|112->95|112->95|116->99|118->101|118->101|118->101|118->101|118->101|118->101|118->101|121->104|121->104|121->104|121->104|123->106|123->106|125->108|125->108|125->108|129->112|132->115|132->115|132->115|136->119|137->120
                    -- GENERATED --
                */
            