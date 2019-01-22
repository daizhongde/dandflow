<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%-- <%@ page import="java.util.*,com.copote.zsgl.sys.*,com.copote.zsgl.entity.Menu,com.copote.zsgl.entity.User"%> --%>
<%
/*
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 

User user = GlobalEnv.getUser(); 
List list = Global.getSystemServiceLocator().getMenuService().getParentMenuList(user.getDwbm(), user.getCzybm());
Iterator it = list.iterator();
*/
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=GBK">
        <title>票据菜单</title>
        
	<link rel="stylesheet" type="text/css" href="scripts/yui_2.9.0/build/reset-fonts-grids/reset-fonts-grids.css">
	<link rel="stylesheet" type="text/css" href="scripts/yui_2.9.0/build/menu/assets/skins/sam/menu.css"> 

        <script type="text/javascript" src="scripts/yui_2.9.0/build/yahoo-dom-event/yahoo-dom-event.js"></script>
        <script type="text/javascript" src="scripts/yui_2.9.0/build/animation/animation.js"></script>
        <script type="text/javascript" src="scripts/yui_2.9.0/build/container/container_core.js"></script>

        <script type="text/javascript" src="scripts/yui_2.9.0/build/menu/menu.js"></script>

        <script type="text/javascript">

            YAHOO.util.Event.onContentReady("productsandservices", function () {

                var oMenuBar = new YAHOO.widget.MenuBar("productsandservices", { 
                                                            autosubmenudisplay: true, 
                                                            hidedelay: 750, 
                                                            lazyload: true });
<%!
/**  获取某一个根节点的子数据项  
public String getItemdata(String dwbm,String czybm,com.copote.zsgl.entity.Menu menu){
	com.copote.zsgl.entity.Menu childMenu = new com.copote.zsgl.entity.Menu();
	List list = Global.getSystemServiceLocator().getMenuService().getChildsMenuList(dwbm , czybm ,menu.getName());
	String itemdata = "{ id: \""+menu.getTitle()+"\",itemdata:[";
	for(int i=0; i<list.size(); i++){
		childMenu = (com.copote.zsgl.entity.Menu)list.get(i);
		//System.out.println("----|"+childMenu.getSfmj()+"|"+(childMenu.getSfmj().intValue()==1)+"|");
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
//组装YUI2中menu示例的aSubmenuData
// com.copote.zsgl.entity.Menu menu = new com.copote.zsgl.entity.Menu();
StringBuffer aSubmenuData = new StringBuffer("[");
/*
for(int i=0; i<list.size(); i++){
	menu = (com.copote.zsgl.entity.Menu)list.get(i);
	aSubmenuData.append(getItemdata(user.getDwbm(),user.getCzybm(),menu));
	if(i<list.size()-1){
		aSubmenuData.append(",");
	}
}
aSubmenuData.append("]");
*/
%>
var aSubmenuData = <%=aSubmenuData.toString()  %>

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
        </script>
    </head>
    <body class="yui-skin-sam" id="yahoo-com">
        <div id="productsandservices" class="yuimenubar yuimenubarnav">
            <div class="bd">
                <ul class="first-of-type">
                    <%
//                     while(it.hasNext()){
//                     	menu = (Menu) it.next();
//                     	out.print("<li class='yuimenubaritem'><a class='yuimenubaritemlabel' href='#'>"+menu.getTitle()+"</a></li>");
//                     }
                    %>
                </ul>
            </div>
        </div>
    </body>
</html>