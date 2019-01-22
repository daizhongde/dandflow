<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%-- <%@ page import="java.util.*,com.copote.zsgl.sys.*,com.copote.zsgl.entity.TggGgb"%> --%>
<%-- <%@ page import="com.copote.zsgl.entity.Menu,com.copote.zsgl.entity.User"%> --%>
<%!
/**  获取某一个根节点的子数据项-使用了递归  
public String getItemdata(String dwbm,String czybm,com.copote.zsgl.entity.Menu menu){
	com.copote.zsgl.entity.Menu childMenu = new com.copote.zsgl.entity.Menu();
	List list = Global.getSystemServiceLocator().getMenuService().getChildsMenuList(dwbm , czybm ,menu.getName());
	String itemdata = "{ id: \""+menu.getTitle()+"\",itemdata:[";
	for(int i=0; i<list.size(); i++){
		childMenu = (com.copote.zsgl.entity.Menu)list.get(i);
		if(childMenu.getSfmj().intValue()==1){
			itemdata+="{ text: \""+childMenu.getTitle()+"\", url: \""+childMenu.getLocation()+"\", target: \"mainweb\" }";
		}else if(childMenu.getSfmj().intValue()==0){
			//在这里递归调用本函数
			itemdata+="{ text: \""+childMenu.getTitle()+"\",  submenu: "+getItemdata(dwbm , czybm,childMenu)+"}";
		}else{
			System.out.println("ERROR！ 菜单表中sfmj字段出现非法数据！");
		}
		if(i<list.size()-1){
			itemdata+=",";
		}
	}
	itemdata+="]}";
	return itemdata;
}*/
%>
<%
StringBuffer aSubmenuData = new StringBuffer("[");
StringBuffer gg = new StringBuffer();
/*
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 

User user = GlobalEnv.getUser();
List list = Global.getSystemServiceLocator().getMenuService().getParentMenuList(user.getDwbm(), user.getCzybm());
Iterator it = list.iterator();

//组装YUI2中menu示例的aSubmenuData
com.copote.zsgl.entity.Menu menu = new com.copote.zsgl.entity.Menu();

for(int i=0; i<list.size(); i++){
	menu = (com.copote.zsgl.entity.Menu)list.get(i);
	aSubmenuData.append(getItemdata(user.getDwbm(),user.getCzybm(),menu));
	if(i<list.size()-1){
		aSubmenuData.append(",");
	}
}
aSubmenuData.append("]");

Iterator ito = Global.getSystemServiceLocator().getNoticeService().getLatestNotice().iterator();

while (ito.hasNext()) {
	TggGgb o = (TggGgb) ito.next();
	gg.append("<a href='notice.do?method=detail&bh=").append(
	o.getBh()).append("' target='_black'>").append(
	o.getBt()).append("</a>").append("   ");
}
if (!gg.toString().equals("")) {
	gg.append("<a href='notice.do?method=list' target='mainweb'>更多..</a>");
}
*/

%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<title>非税收入执收管理系统</title>
	<meta http-equiv="Cache-Control" content="no-store"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	
	<object id="min" classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11"
		codebase="common/copote.ocx" height=0 width=0>
		<param name="Command" value="Minimize">
	</object>
	
	<link href="styles/main.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="scripts/yui_2.9.0/build/menu/assets/skins/sam/menu.css">
	<link rel="stylesheet" type="text/css" href="scripts/yui_2.9.0/build/resize/assets/skins/sam/resize.css" />
	<link rel="stylesheet" type="text/css" href="scripts/yui_2.9.0/build/layout/assets/skins/sam/layout.css" />
<style type="text/css">
body {
	margin:0;
	padding:0;
}
.yui-skin-sam .yui-dt-liner { white-space:nowrap; } 
#treeDiv1 {background: #fff; margin-top:1em; padding:1em; min-height:7em;}
#examplecontainer {
    padding:10px;
}

#resizablepanel .bd {
    overflow:auto;
    background-color:#fff;
    padding:0px;
    text-align:left; 
}

#resizablepanel .ft {
    height:15px;
    padding:0;
}

#resizablepanel .yui-resize-handle-br {
    right:0;
    bottom:0;
    height: 8px;
    width: 8px;
    position:absolute;
}

#resizablepanel_c.hide-scrollbars .yui-resize .bd {
    overflow: hidden;
}

#resizablepanel_c.show-scrollbars .yui-resize .bd {
    overflow: auto;
}

#resizablepanel_c.show-scrollbars .underlay {
    overflow: visible;
}
</style>

    <script type="text/javascript" src="scripts/yui_2.9.0/build/yahoo-dom-event/yahoo-dom-event.js"></script>
    <script type="text/javascript" src="scripts/yui_2.9.0/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="scripts/yui_2.9.0/build/event/event-min.js"></script>
	<script type="text/javascript" src="scripts/yui_2.9.0/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="scripts/yui_2.9.0/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="scripts/yui_2.9.0/build/element/element-min.js"></script>
	<script type="text/javascript" src="scripts/yui_2.9.0/build/event-delegate/event-delegate-min.js"></script>
	
	<script type="text/javascript" src="scripts/yui_2.9.0/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="scripts/yui_2.9.0/build/animation/animation-min.js"></script>
    <script type="text/javascript" src="scripts/yui_2.9.0/build/layout/layout-min.js"></script>
    <script type="text/javascript" src="scripts/yui_2.9.0/build/container/container_core.js"></script>
    <script type="text/javascript" src="scripts/yui_2.9.0/build/menu/menu.js"></script>
    <script type="text/javascript" src="scripts/yui_2.9.0/build/utilities/utilities.js"></script>
    
    
    <script type="text/javascript">
var app = {};

        YAHOO.util.Event.onContentReady("productsandservices", function () {
            var oMenuBar = new YAHOO.widget.MenuBar("productsandservices", { 
                                                        autosubmenudisplay: true, 
                                                        hidedelay: 750, 
                                                        lazyload: true });
		var aSubmenuData = <%=aSubmenuData.toString()  %>;
            var ua = YAHOO.env.ua,
                oAnim;  // Animation instance
            function onSubmenuBeforeShow(p_sType, p_sArgs) {
                var oBody,
                    oElement,
                    oShadow,
                    oUL;
                if (this.parent) {
                    oElement = this.element;
                    oShadow = oElement.lastChild;
                    oShadow.style.height = "0px";
                    if (oAnim && oAnim.isAnimated()) {
                        oAnim.stop();
                        oAnim = null;
                    }
                    oBody = this.body;

                    if (this.parent && 
                        !(this.parent instanceof YAHOO.widget.MenuBarItem)) {
                        if (ua.gecko || ua.opera) {
                            oBody.style.width = oBody.clientWidth + "px";
                        }
                        if (ua.ie == 7) {
                            oElement.style.width = oElement.clientWidth + "px";
                        }
                    }
                    oBody.style.overflow = "hidden";
                    oUL = oBody.getElementsByTagName("ul")[0];
                    oUL.style.marginTop = ("-" + oUL.offsetHeight + "px");
                }
            }

            function onTween(p_sType, p_aArgs, p_oShadow) {
                if (this.cfg.getProperty("iframe")) {
                    this.syncIframe();
                }
                if (p_oShadow) {
                    p_oShadow.style.height = this.element.offsetHeight + "px";
                }
            }

            function onAnimationComplete(p_sType, p_aArgs, p_oShadow) {
                var oBody = this.body,
                    oUL = oBody.getElementsByTagName("ul")[0];

                if (p_oShadow) {
                    p_oShadow.style.height = this.element.offsetHeight + "px";
                }
                oUL.style.marginTop = "";
                oBody.style.overflow = "";

                if (this.parent && 
                    !(this.parent instanceof YAHOO.widget.MenuBarItem)) {
                    if (ua.gecko || ua.opera) {
                        oBody.style.width = "";
                    }
                    if (ua.ie == 7) {
                        this.element.style.width = "";
                    }
                }
            }

            function onSubmenuShow(p_sType, p_sArgs) {
                var oElement,
                    oShadow,
                    oUL;
            
                if (this.parent) {
                    oElement = this.element;
                    oShadow = oElement.lastChild;
                    oUL = this.body.getElementsByTagName("ul")[0];
                    oAnim = new YAHOO.util.Anim(oUL, 
                        { marginTop: { to: 0 } },
                        .5, YAHOO.util.Easing.easeOut);

                    oAnim.onStart.subscribe(function () {
                        oShadow.style.height = "100%";
                    });
                    oAnim.animate();
                    if (YAHOO.env.ua.ie) {
                        oShadow.style.height = oElement.offsetHeight + "px";
                        oAnim.onTween.subscribe(onTween, oShadow, this);
                    }
                    oAnim.onComplete.subscribe(onAnimationComplete, oShadow, this);
                }
            }
            oMenuBar.subscribe("beforeRender", function () {
		var nSubmenus = aSubmenuData.length,
		i;
                if (this.getRoot() == this) {
		for (i = 0; i < nSubmenus; i++) {
                    	this.getItem(i).cfg.setProperty("submenu", aSubmenuData[i]);
		}
                }
            });
            oMenuBar.subscribe("beforeShow", onSubmenuBeforeShow);
            oMenuBar.subscribe("show", onSubmenuShow);
            oMenuBar.render();     
        });
        
        
YAHOO.example.doLayout = function() {
	var Ex = YAHOO.namespace('example');
	/**   */
	//header: '基础数据&gt;&gt;材料模板',  collapse: true,
	app.layout = new YAHOO.widget.Layout({
        units: [
        	{ position: 'top', height: 110,  resize : false, body: 'top1',  gutter: '0px',  scroll : true },
        	{ position: 'bottom', height: 50, resize: false, body: 'bottom1', gutter: '0px',scroll : true },
            { position: 'center', body: 'center1',scroll: true, gutter: '0 0 0 0' }
        ]
    });
    app.myDataTable = YAHOO.util.Dom.get("mainweb");
    app.layout.on('resize', function() {
        if (app.myDataTable) {
        	//alert("有");
        	YAHOO.util.Dom.setStyle( app.myDataTable , "height" , (this.getSizes().center.h-37) + 'px');
            //app.myDataTable.set('height', (this.getSizes().center.h-74) + 'px');//114没有新增按钮,141:有button不在header,144有button在header,
            //app.myDataTable._syncColWidths();
        }
    }, app.layout, true);
           
    app.layout.on('render', function() {
    	YAHOO.util.Dom.setStyle( app.myDataTable , "height" , (this.getSizes().center.h-37) + 'px');//have reset.css -32  no reset.css -37
    	//app.myDataTable.set('height', (this.getSizes().center.h-74) + 'px');
    }, app.layout, true);  
    
    app.layout.render();
	
    return {
    };
        
};
//YAHOO.example.DynamicData
YAHOO.util.Event.addListener(window, "load", YAHOO.example.doLayout);

function help(file){
	window.open('common/help/'+file,'', 'height=500, width=610, top=0,left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
}
function down(file){
	window.open('common/help/'+file,'', 'height=300, width=450, top=0,left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
}
</script>
</head>
<!-- height="90" -->
<body class="yui-skin-sam" id="yahoo-com">
<!-- north begin -->
<div id="top1">
			<table width="100%" height="34" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td background="images/main/top_01_bg.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30" align="right" valign="bottom"><img
								src="images/main/top_logo.gif" width="23" height="23"></td>
							<td valign="bottom"><img src="images/top_logotitle.gif"
								width="250" height="24"></td>
							<td valign="bottom"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" height="56" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td background="images/main/top_menu_bg.gif">
					<table width="100%" height="56" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="392" align="right" valign="bottom">
							<table width="380" height="40" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="74" height="40"><img
										src="images/main/top_menu01.gif" width="74" height="40"
										border="0"
										onMouseDown="this.src='images/main/top_menu01d.gif'"
										onMouseUp="this.src='images/main/top_menu01o.gif'"
										onMouseOver="this.src='images/main/top_menu01o.gif'"
										onMouseOut="this.src='images/main/top_menu01.gif'"
										onClick="javascript:history.go(-1)" style="cursor:pointer"></td>
									<td width="74"><img src="images/main/top_menu02.gif"
										width="74" height="40" border="0"
										onMouseDown="this.src='images/main/top_menu02d.gif'"
										onMouseUp="this.src='images/main/top_menu02o.gif'"
										onMouseOver="this.src='images/main/top_menu02o.gif'"
										onMouseOut="this.src='images/main/top_menu02.gif'"
										onClick="javascript:history.go(1)" style="cursor:pointer"></td>
									<td width="74"><img src="images/main/top_menu03.gif"
										width="74" height="40" border="0"
										onMouseDown="this.src='images/main/top_menu03d.gif'"
										onMouseUp="this.src='images/main/top_menu03o.gif'"
										onMouseOver="this.src='images/main/top_menu03o.gif'"
										onMouseOut="this.src='images/main/top_menu03.gif'"
										onClick="javascript:mainweb.history.go(0)"
										style="cursor:pointer"></td>
									<td width="74"><a href="#" onclick="help('help.htm')" onFocus="blur()"><img
										src="images/main/top_menu04.gif" width="74" height="40"
										border="0"
										onMouseDown="this.src='images/main/top_menu04d.gif'"
										onMouseUp="this.src='images/main/top_menu04o.gif'"
										onMouseOver="this.src='images/main/top_menu04o.gif'"
										onMouseOut="this.src='images/main/top_menu04.gif'"></a></td>
									<td width="74"><a href="user.do?method=logout"
										onFocus="blur()"><img src="images/main/top_menu05.gif"
										width="74" height="40" border="0"
										onMouseDown="this.src='images/main/top_menu05d.gif'"
										onMouseUp="this.src='images/main/top_menu05o.gif'"
										onMouseOver="this.src='images/main/top_menu05o.gif'"
										onMouseOut="this.src='images/main/top_menu05.gif'"></a></td>
								</tr>
							</table>
							</td>
							<td valign="bottom">
							<table width="100%" border="0" cellspacing="8" cellpadding="0">
								<tr>
									<td><marquee scrollamount=3 onmouseover="this.stop()"
										onMouseOut="this.start()"><%=gg.toString()%></marquee></td>
								</tr>
								<tr>
									<td align="right">
									<a href="#" onclick="down('pjnjbgxz.htm');">票据年检表格下载</a>
									<a href="#" onclick="help('dzsc.htm');">对账手册</a>
									<a
										href="http://10.104.9.18/fc/login.jsp" target="_blank">财政厅国库集中支付系统</a>
									<a
										href="http://10.104.93.229/hnfsbbs/mk1.jsp?zh="
										target="_blank">非税收入征收管理系统维护论坛</a></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
</div>
	<!-- north end -->
			
	<!-- center begin -->
	<div id="center1">
		<!-- #################    top navigate     begin        ######################################     -->

			<div id="productsandservices" class="yuimenubar yuimenubarnav">
	            <div class="bd">
	                <ul class="first-of-type">
	                    <%
// 	                    while(it.hasNext()){
// 	                    	menu = (Menu) it.next();
// 	                    	out.print("<li class='yuimenubaritem'><a class='yuimenubaritemlabel' href='#'>"+menu.getTitle()+"</a></li>");
// 	                    }
	                    %>
	                </ul>
	            </div>
			</div>
<!-- #################   top navigate     end        ######################################     -->
		<div id="mainframe">
			<!-- #################   mainiframe     begin        ######################################     -->
	        <iframe id="mainweb" name="mainweb" src="right.jsp" frameborder="0" scrolling="auto" height="500" width="100%"></iframe>
		    <!-- #################   mainiframe     end        ######################################     -->
	    </div>
	</div>
		<!-- center end -->
		
		<!-- west begin   height="30"-->
		<div id="bottom1">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#E9E8EE">
				<tr>
					<td width="10"><img src="images/main/bottom_l.gif" width="10"
						height="30"></td>
					<td valign="top" background="images/main/bottom_bg.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="476" valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="8">
										</tr>
									</table>
									</td>
									<td valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="right"><span> 
	<script type="text/javascript">
	 <!-- Begin         
	   var enable=0; today=new Date();  
	   new Date();   
	   var day; var date;    
	   var time_start = new Date();   
	   var clock_start = time_start.getTime();   
	   if(today.getDay()==0)  day="星期日"
	   if(today.getDay()==1)  day="星期一"                     
	   if(today.getDay()==2)  day="星期二"    
	   if(today.getDay()==3)  day="星期三" 
	   if(today.getDay()==4)  day="星期四"  
	   if(today.getDay()==5)  day="星期五"  
	   if(today.getDay()==6)  day="星期六"  
	   yr = today.getYear(); 
	   if (yr < 1000)  
	   yr+=1900;          
	   date=yr+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日 ";  
	   document.write(date);                      
	   document.write(day);
	// -->                   
	</SCRIPT> </span></td>
									<td width="6"></td>
									<td width="68"></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
					<td width="10"><img src="images/main/bottom_r.gif" width="10"
						height="30"></td>
				</tr>
			</table>
		</div>
		<!-- west end -->
</body>
</html>