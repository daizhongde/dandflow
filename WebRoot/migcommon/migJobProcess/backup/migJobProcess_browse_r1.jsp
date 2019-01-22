<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>作业监控</title>
	<link rel="stylesheet" type="text/css" href="../scripts/jquery-easyui/1.4.1/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../scripts/jquery-easyui/1.4.1/themes/icon.css">
	<style type="text/css">

#holder
{
    top: 0px;
    left: 0px;
    right: 0px;
    bottom: 0px;
    position: absolute;
    z-index: 999;
}
</style>
	<script type="text/javascript" src="../scripts/jquery-easyui/1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="../scripts/jquery-easyui/1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../scripts/json/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="../scripts/raphael-min.js"></script>
<script type="text/javascript" src="../scripts/const/const.js"></script>
<script type="text/javascript" src="../scripts/utils/commonUtil.js"></script>
<script type="text/javascript">
var jobId = "${pojo.jobId }";
var jobName = "${pojo.jobName }";

var newCtrlNum=100;
var x = 0;//光标绝对坐标
var y = 0;//光标绝对坐标
var px = 251;//工作区的坐标-取工作区左上角的坐标
var py = 30;//工作区的坐标

var rx = 0;//光标相对工作区的坐标
var ry = 0;//光标相对工作区的坐标


var app={};
app.selectArrId = "";//右键选中的连接箭头raphael id
app.zindex = "";//右键选中的shape的索引
/** 

@var connections 连接对象集合
@var dragger,move,up 三个事件处理函数
@var r 绘图对象
@var start 箭头起始node
@var end 箭头末端node'
@var rel 所有任务存在的关系   数据格式： DA-DC|DB-DC|DC-DE|

@var shapes 矩形对象集合
@var tasks 任务数据集合
@var txts 任务名称文本对象集合

@var tempconn 临时连接对象，连接时用
@var fmt 格式化函数对象集合
@var motask 光标over的节点对象,如果光标没有在任何一个节点上 motask.overFlag = false
*/
var connections=[],dragger,move,up,r,start,end,rel="";
var shapes=[], tasks,txts=[];//矩形框,任务json数据,任务文本
var tempconn={};
var fmt={};
/** 作业监控数据结构
 * task: 
 *  {
			"id" : {
				"jobId" : "100",
				"taskId" : "DA"
			},
			"taskStatus" : "0",
			"coords" : "110,70", "zb" : {"x" : 110,"y" : 70 },
			"postpos" : "DC|DD|DE",
			"prepos" : "TS00000032",
			"t" : {
				"comId" : "com00700",
				"taskAuthor" : null,
				"taskId" : "DA",
				"taskName" : "任务DA",
				"taskNode" : "F",
				"taskRemark" : null,
				"taskUpdate" : null
			},
			processId:"", parent:"", status:""
			]
		}
 *  
 *  */
 
var dbdata = "[{id:{jobId:\"100\", taskId:\"DA\"}, taskStatus:\"0\", prepos:\"\", postpos:\"DC\",coords:\"110,70\",zb:{x:110, y:70}, processId:\"1\", parent:\"100\", status:\"1\","
+"t:{taskId:\"DA\", taskName:\"任务一\", taskNode:\"F\", comId:\"com001\", taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}},"
+"{id:{jobId:\"100\", taskId:\"DB\"}, taskStatus:\"0\", prepos:\"\", postpos:\"DC|DD\",coords:\"110,270\",zb:{x:110, y:270}, processId:\"2\", parent:\"100\", status:\"2\", "
+"t:{taskId:\"DB\", taskName:\"任务二\", taskNode:\"S\", comId:\"com001\",taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}},"
+"{id:{jobId:\"100\", taskId:\"DC\"}, taskStatus:\"0\", prepos:\"DA|DB\", postpos:\"DE\",coords:\"270,70\",zb:{x:270, y:70}, processId:\"3\", parent:\"100\", status:\"0\", "
+"t:{taskId:\"DC\", taskName:\"任务三\", taskNode:\"S\", comId:\"com001\",taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}},"
+"{id:{jobId:\"100\", taskId:\"DD\"}, taskStatus:\"0\", prepos:\"DB\", postpos:\"DE\",coords:\"270,270\",zb:{x:270, y:270}, processId:\"4\", parent:\"100\", status:\"1\", "
+"t:{taskId:\"DD\", taskName:\"任务四\", taskNode:\"F\", comId:\"com001\",taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}},"
+"{id:{jobId:\"100\", taskId:\"DE\"}, taskStatus:\"0\", prepos:\"DC|DD\", postpos:\"\",coords:\"450,170\",zb:{x:450, y:170}, processId:\"5\", parent:\"100\", status:\"0\", "
+"t:{taskId:\"DE\", taskName:\"任务五\", taskNode:\"S\", comId:\"com001\", taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}}]";

// var dbdata = ajaxRequest("/busi/migJobContentBUSIfindTasksByJob.action", 
// 		"{jobId:'"+jobId+"'}" );

tasks = eval ("(" + dbdata + ")");

 //下面的代码需要改为函数，在获取后台数据后回调,
$(function () {
    //用来存储节点的顺序
    connections = [];
    
    //创建绘图对象
    r = Raphael("holder", $(window).width(), $(window).height() );

    /* 
    x1: line start point x coords
    x2: line end point x coords
    x3: line text start point x coords
    */
    var x1 = $(window).width()-100;
    var x2 = $(window).width()-70;
    var x3 = $(window).width()-45;
	
    //绘制颜色说明,六个矩形，六个文本
r.path("M" + x1 + " 20L" + x2 + " 20").attr({ stroke: COLOR.UNEXECUTE,"stroke-width": 16 });
	r.text( x3, 20, PSTATENAME.UNEXECUTE );
r.path("M" + x1 + " 40L" + x2 + " 40").attr({ stroke: COLOR.EXECUTING,"stroke-width": 16 });
	r.text( x3, 40, PSTATENAME.EXECUTING )
r.path("M" + x1 + " 60L" + x2 + " 60").attr({ stroke: COLOR.FINISH, "stroke-width": 16 });
	r.text( x3, 60, PSTATENAME.FINISH )
r.path("M" + x1 + " 80L" + x2 + " 80").attr({ stroke: COLOR.PAUSE, "stroke-width": 16 });
	r.text( x3, 80, PSTATENAME.PAUSE )
r.path("M" + x1 + " 100L" + x2 + " 100").attr({ stroke: COLOR.SKIP, "stroke-width": 16 });
	r.text( x3, 100, PSTATENAME.SKIP )
r.path("M" + x1 + " 120L" + x2 + " 120").attr({ stroke: COLOR.ERROR, "stroke-width": 16 });
	r.text( x3, 120, PSTATENAME.ERROR )
   
    shapes = new Array();
 	
	for( var i=0, j=tasks.length; i<j; i++ ){
		//在shape上加一个索引记录
		var shape = r.rect( tasks[i].zb.x, tasks[i].zb.y, RECT.w, RECT.h, RECT.r);
		
		shape.zindex = i;
		shapes.push( shape );
		
		var txt = r.text( tasks[i].zb.x + RECT.x, tasks[i].zb.y + RECT.yy, tasks[i].t.taskName )
		txts.push( txt );
	}
	
    //为节点添加样式和事件，并且绘制节点之间的箭头
    for (var i = 0, j = shapes.length; i < j; i++) {
//         var color = Raphael.getColor();
		var color = COLORVALUE[tasks[i].status];
		fmt.fmtShape( shapes[i], color, tasks[i].id.taskId );
		
	    
		if( tasks[i].status == PSTATE.EXECUTING ){
// 			alert(shapes[i].attr("x"));
			var c = r.circle( shapes[i].attr("x") + RECT.x, shapes[i].attr("y") + RECT.y, 10)
			function animate() {
			  var ms = 2000;
			  var anim = Raphael.animation({
			    50: {
			      r: 15,
			      stroke: '#00FF00'
			    },
			    100: {
			      r: 10,
			      stroke: '#FFFF00'
			    }
			  }, ms);
			 
			  c.animate(anim.repeat("Infinity")); //Infinity为无限次
			}
			animate();
		}
    }

    //objects, 连线用
    var os = new Array();
    
    for(var p=0, q = tasks.length; p<q; p++ ){//循环节点
    	
    	var postpos = tasks[p].postpos==null ? [] : tasks[p].postpos.split("|");
        for(var i=0, j=postpos.length; i<j; i++){//循环后置数量
        	rel  = rel + tasks[p].id.taskId+"-"+postpos[i]+"|";
        	for( var m=0, n=shapes.length; m<n; m++  ){//循环找后置对应的shape
//         		alert( tasks[m].id.taskId+"=="+postpos[i]+" : "+(tasks[m].id.taskId == postpos[i]) );
        		if( tasks[m].id.taskId == postpos[i] ){
        		 	os.push( { obj1: shapes[p], obj2: shapes[m] } );
        			break;
        		}
        	}
        }
    }
    
    var points = new Array();
    for (var i = 0, j = os.length; i < j; i++) {
    	points.push( getStartEnd2(os[i].obj1, os[i].obj2) );
    }
    //存储节点间的顺序
    for (var i = 0, j = os.length; i < j; i++) {
    	var conn = r.drawArr3(os[i], points[i]);
    	conn.start = os[i].obj1.taskId;
    	conn.end = os[i].obj2.taskId;
    	connections.push( conn );
    }
});
 
/** 格式化子任务图标  **/
fmt.fmtShape = function (obj, color, taskId ){
	obj.attr({ fill: color, stroke: color, "fill-opacity": 0, "stroke-width": 2, cursor: "help" });

	//obj有一个默认的ID是数字，这也是绑定事件必须的
    obj.taskId = taskId;
	
    obj.dblclick(function () { app.f_modifyPara_open_window( this.zindex ) });
};

/** 通用  **/
Raphael.fn.drawArr3 = function (obj, point) {
    var path1 = getArr(point.start.x, point.start.y, point.end.x, point.end.y, 8);
//     var path1 = ["M", point.start.x, point.start.y, "L", point.end.x, point.end.y ];
    if (obj.arrPath) {
    	obj.arrPath.attr({ path: path1 });
    	
    } else {
    	obj.arrPath = r.path( path1 );
    	obj.arrPath.data("fromTask", obj.obj1==undefined ? "": obj.obj1.taskId )
    	obj.arrPath.data("toTask", obj.obj2==undefined ? "": obj.obj2.taskId )
//     	.attr({
//     		'arrow-end':'classic-wide-long',
//     		  stroke: "#f00",
//     		  "stroke-width": 2
//     		});
    	
    	$( obj.arrPath.node ).bind('contextmenu',function(e ){
    		e.preventDefault();
    		app.selectArrId = this.raphaelid;
    		
    		$('#lineContextMenu').menu('show', {
    			left: e.pageX,
    			top: e.pageY
    		});
    	});
    }
    return obj;
};

/** 获取组成箭头的三条线段的路径
@param size 箭头大小
**/
function getArr(x1, y1, x2, y2, size) {
      var angle = Raphael.angle(x1, y1, x2, y2);//得到两点之间的角度
      var a45 = Raphael.rad(angle - 45);//角度转换成弧度
      var a45m = Raphael.rad(angle + 45);
      var x2a = x2 + Math.cos(a45) * size;
      var y2a = y2 + Math.sin(a45) * size;
      var x2b = x2 + Math.cos(a45m) * size;
      var y2b = y2 + Math.sin(a45m) * size;
      var result = ["M", x1, y1, "L", x2, y2, "L", x2a, y2a, "M", x2, y2, "L", x2b, y2b];
      return result;
  }
/** 动态确定起点和终点  **/
function getStartEnd2(obj1, obj2){
// 	Raphael.pathIntersection(path1, path2);
	var p1 = getNodeCenterPoint(obj1);	
	var p2 = getNodeCenterPoint(obj2);
		
	//两矩形中心的连线
	var line = "M"+p1.x+" "+p1.y+"L"+p2.x+" "+p2.y;

	var inters1 = pathIntersection1(line, obj1 );
	var inters2 = pathIntersection2(line, obj2 );
	
	var result = {};
    result.start = {};
    result.end = {};
    result.start.x = inters1.x;
    result.start.y = inters1.y;
    result.end.x = inters2.x;
    result.end.y = inters2.y;
    return result;
};

/** 获取矩形中心坐标
 * @param obj 矩形element
 */
function getNodeCenterPoint(obj){
	var bb = obj.getBBox();
	var p =  { x: bb.x + bb.width / 2, y: bb.y + bb.height / 2 };//中心
	return p;
};
/** 获取交点,前置节点用
 * @param line path字符串
 * @param p 矩形element坐标数组，矩形的四个顶点
 */
function pathIntersection1(line, obj1){
	var bb1 = obj1.getBBox();
	var p = [
	            { x: bb1.x,             y: bb1.y },//左上角
	            { x: bb1.x + bb1.width, y: bb1.y },//右上角
	            { x: bb1.x + bb1.width, y: bb1.y + bb1.height },//右下角
	            { x: bb1.x,             y: bb1.y + bb1.height }//左下角
	        ];

	var right = Raphael.pathIntersection( line, 
			"M"+p[1].x+" "+p[1].y+"L"+p[2].x+" "+p[2].y);
	if(right.length==1){
		return right[0];
	}
	var top = Raphael.pathIntersection( line, 
			"M"+p[0].x+" "+p[0].y+"L"+p[1].x+" "+p[1].y );
	if(top.length==1){
		return top[0];
	}
	var bottom = Raphael.pathIntersection( line, 
			"M"+p[2].x+" "+p[2].y+"L"+p[3].x+" "+p[3].y);
	if(bottom.length==1){
		return bottom[0];
	}
	var left = Raphael.pathIntersection( line, 
			"M"+p[0].x+" "+p[0].y+"L"+p[3].x+" "+p[3].y);
	if(left.length==1){
		return left[0];
	}
	
	return { x: ( p[1].x + p[2].x)/2, y: ( p[1].y + p[2].y)/2 };
};
/** 获取交点,后置节点用
 * @param line path字符串
 * @param p 矩形element坐标数组，矩形的四个顶点
 */
function pathIntersection2(line, obj2){
	var bb2 = obj2.getBBox();
	var p = [
	            { x: bb2.x,             y: bb2.y },//左上角
	            { x: bb2.x + bb2.width, y: bb2.y },//右上角
	            { x: bb2.x + bb2.width, y: bb2.y + bb2.height },//右下角
	            { x: bb2.x,             y: bb2.y + bb2.height }//左下角
	        ];
	var left = Raphael.pathIntersection( line, 
			"M"+p[0].x+" "+p[0].y+"L"+p[3].x+" "+p[3].y);
	if(left.length==1){
		return left[0];
	}

	var top = Raphael.pathIntersection( line, 
			"M"+p[0].x+" "+p[0].y+"L"+p[1].x+" "+p[1].y );
	if(top.length==1){
		return top[0];
	}
	var bottom = Raphael.pathIntersection( line, 
			"M"+p[2].x+" "+p[2].y+"L"+p[3].x+" "+p[3].y);
	if(bottom.length==1){
		return bottom[0];
	}
	
	var right = Raphael.pathIntersection( line, 
			"M"+p[1].x+" "+p[1].y+"L"+p[2].x+" "+p[2].y);
	if(right.length==1){
		return right[0];
	}
	return { x: ( p[1].x + p[2].x)/2, y: ( p[1].y + p[2].y)/2 };
};

</script>
<script type="text/javascript" src="../migcommon/migJobInfo/migJobInfo_browse.js"></script>
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">

	<div data-options="region:'center',title:'noheader',iconCls:'icon-add'" noheader="true">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div id="tabPanel1" title="${pojo.jobName }" cache="true" style="overflow:hidden;">
				<div class="easyui-layout" data-options="fit:true">
				
					<div id="workspace" noheader="true" class="easyui-droppable targetarea" 
						data-options="region:'center',title:'noheader',iconCls:'icon-add'">
						<div id="holder">
					    </div>
					</div><!-- center   class="easyui-draggable"  -->
					
					
				</div><!-- layout -->
			</div>
		</div>
	</div>

	<!-- 控件参数修改界面 -->
	<div id="modifyPara"></div>

	
<script type="text/javascript">
jQuery(document).ready(function()
{
	px = $('#workspace').offset().left;
	py = $('#workspace').offset().top;
});

</script>
</body>
</html>