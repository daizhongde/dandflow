<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>作业编辑(2代)</title>
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
	<script type="text/javascript" src="../scripts/jquery-easyui/1.4.1/plugins/easyuiExtends.js"></script>
<script type="text/javascript" src="../scripts/raphael-min.js"></script>
<script type="text/javascript" src="../scripts/virtue/virtue.js"></script>
<script type="text/javascript" src="../scripts/const/const.js"></script>
<script type="text/javascript" src="../scripts/utils/commonUtil.js"></script>
<script type="text/javascript">
Array.prototype.clone=function(){ return [].concat(this); } //或者 Array.prototype.clone=function(){ return this.concat(); } 
var update = VIRTUE.update;

var jobId = "${pojo.jobId }";
var jobName = "${pojo.jobName }";
var status = "${pojo.status }";

var pojo = "${pojo }";
pojo = eval("(" + pojo + ")");
// alert("pojo:"+pojo);
// alert("$.toJSON( pojo ):"+ $.toJSON( pojo ) );

var newCtrlNum=100;
var x = 0;//光标绝对坐标
var y = 0;//光标绝对坐标
var px = 251;//工作区的坐标-取工作区左上角的坐标
var py = 30;//工作区的坐标

var rx = 0;//光标相对工作区的坐标
var ry = 0;//光标相对工作区的坐标

/** 画线开关,单击节点时如果shift键被按下就将drawflag设置为true */
var drawflag = false;

var app={};
app.selectArrId = "";//右键选中的连接箭头raphael id
app.zindex = "";//右键选中的shape的索引
var copy="";//复制的任务
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
var motask={};//光标over的节点对象
motask.overFlag = false;//光标over标志

window.onkeydown = keydown;
//只有在工作区的div中才需要触发此类事件
// window.onmousemove = mousemove;
// window.onmouseup = mouseup;

/** 显示按键代码 ,不需要记录shift键状态，因为任何事件中都可以判断shift键状态 **/
function keydown(e){
// 	document.getElementById("dcode").value=e.keyCode;
};
/** 当鼠标单击节点时shift状态为true鼠标移动时实时更新箭头
 * 箭头三种状态:
	 1：终点不在节点'上，浅灰，起点需要计算，终点为当前坐标
	 2：终点在节点'上，蓝，起点终点都需要计算
	 3：鼠标在节点'上松开，黑（正常），起点终点都需要计算
操作处理步骤:
	 1：左键单击节点时如果shift==true,置画线标志flag==true
	 2: 移动鼠标如果flag==true
	 	2.1光标不在node'上，更新箭头，起点需要计算，终点为当前坐标，并设置颜色为灰色
	 	2.2光标在node'上，更新箭头，起点终点都需要计算，并设置颜色为蓝色
	 3：松开鼠标，
	 	3.1如果flag==true，且光标不在node'上，删除箭头，置flag==false
	 	3.2如果flag==true，且光标在node'上，更新箭头颜色(写入数据库)，置flag==false
 */
function mousemove(e){
	showPosition(e);
	
	if(drawflag){
		//如果光标不在node'上
		if( !motask.overFlag || motask.taskId == start.taskId){
			var s = getNodeCenterPoint(start);
			var line = "M"+s.x+" "+s.y+"L"+rx+" "+ry;
			
			var point = {};
			point.start = pathIntersection1(line, start);
			
			point.end = {};
			point.end.x = rx;
			point.end.y = ry;
			r.drawArr3( tempconn, point );
			
		}else{
// 			alert("鼠标在任务上，且不是前置任务!");
// 			tempconn = { obj1: start, obj2: motask };//如果这样tempconn就不再是原来的对象，主会多出一条箭头
			tempconn.obj1 = start;
			tempconn.obj2 = motask;
			var point1 = getStartEnd2(tempconn.obj1, tempconn.obj2);
			r.drawArr3(tempconn, point1);
		}
	}
};

/** 当左键松开的时候，判断光标是否在节点'上，如果是就画线，否则什么也不做  **/
function mouseup(e){
	if(drawflag){
		drawflag = false;
		//光标不在node'上,删除箭头
		if( !motask.overFlag || motask.taskId == start.taskId ){

// 			connections.pop();
			tempconn.arrPath.remove();
			tempconn = { };
		}else{
			//将关系写入数据库
			tempconn.obj1 = start;
			tempconn.obj2 = motask;
			var searchStr = start.taskId +"-"+motask.taskId+"|";
			var searchStr2 = motask.taskId +"-"+start.taskId+"|";
			if( rel.indexOf( searchStr ) == -1 
					&& rel.indexOf( searchStr2 ) == -1 ){//如果两节点还不存在关系

				//write to db
				ajaxReqConn("/busi/migJobContentBUSIlink.action", 
					"{jobId:'"+jobId+"',fromTask:'"+start.taskId+"',toTask:'"+motask.taskId+"'}" );
				
				var point1 = getStartEnd2(tempconn.obj1, tempconn.obj2);
				var conn = r.drawArr3(tempconn, point1);
				//因为只有画的箭头保存时已经存在但又没有fromTask和toTask属性
				tempconn.arrPath.data("fromTask", tempconn.obj1.taskId )
		    	tempconn.arrPath.data("toTask", tempconn.obj2.taskId )
		    	
		    	conn.start = start.taskId;
		    	conn.end = motask.taskId;
				connections.push( conn );
				tempconn = { };//初始化。如果这样tempconn就不再是原来的对象
				
				rel  = rel + start.taskId+"-"+motask.taskId+"|";
				
			}else{
// 				connections.pop();
				tempconn.arrPath.remove();
				tempconn = { };
			}
		}
	}
};

/** 同步发送请求，url以斜扛开头  **/
function ajaxReqConn(url, param){
	$.ajax({
		type: "POST",
		url: getContextPath()+url,
		async: true,
		data:{
			jdata: param
		},
		success: function(oResponse){
// 	 		oResponse = eval("(" + oResponse + ")");
//			alert(oResponse.msg);
			if(oResponse.success == false){
				alert(oResponse.msg);
			}else{

			}
	   }
	}); 
};

/** 计算相对坐标（工作区中的坐标）   **/
function calcPosition(obj){
	obj.top = obj.top + py;
	obj.left = obj.left + px;
// 	alert( $.toJSON(obj) );
	return obj;
};
/** 专门记录鼠标的坐标   **/
function showPosition(e) {
	var e = e || window.event;

	x = e.clientX
			+ document.body.scrollLeft
			+ document.documentElement.scrollLeft;
	y = e.clientY
			+ document.body.scrollTop + document.documentElement.scrollTop;
	rx = x-px;
	ry = y-py;
	
// 	document.getElementById("x").value = rx;
// 	document.getElementById("y").value = ry;

};

function onStopDragNode(node){

};

var dbdata = ajaxRequest("/busi/migJobContentBUSIfindTasksByJob.action", 
		"{jobId:'"+jobId+"'}" );

// var dbdata = "[{id:{jobId:\"100\", taskId:\"DA\"}, taskStatus:\"0\", prepos:\"\", postpos:\"DC\",coords:\"110,70\",zb:{x:110, y:70}, "
//     +"t:{taskId:\"DA\", taskName:\"任务一\", taskNode:\"F\", comId:\"com001\", taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}},"
//   +"{id:{jobId:\"100\", taskId:\"DB\"}, taskStatus:\"0\", prepos:\"\", postpos:\"DC|DD\",coords:\"110,270\",zb:{x:110, y:270}, "
//  	+"t:{taskId:\"DB\", taskName:\"任务二\", taskNode:\"S\", comId:\"com001\",taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}},"
//   +"{id:{jobId:\"100\", taskId:\"DC\"}, taskStatus:\"0\", prepos:\"DA|DB\", postpos:\"DE\",coords:\"270,70\",zb:{x:270, y:70}, "
//  	+"t:{taskId:\"DC\", taskName:\"任务三\", taskNode:\"S\", comId:\"com001\",taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}},"
//   +"{id:{jobId:\"100\", taskId:\"DD\"}, taskStatus:\"0\", prepos:\"DB\", postpos:\"DE\",coords:\"270,270\",zb:{x:270, y:270}, "
//  	+"t:{taskId:\"DD\", taskName:\"任务三\", taskNode:\"F\", comId:\"com001\",taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}},"
//   +"{id:{jobId:\"100\", taskId:\"DE\"}, taskStatus:\"0\", prepos:\"DC|DD\", postpos:\"\",coords:\"450,170\",zb:{x:450, y:170}, "
//  	+"t:{taskId:\"DE\", taskName:\"任务四\", taskNode:\"S\", comId:\"com001\", taskAuthor:\"\",taskRemark:\"\",taskUpdate:\"\"}}]";

tasks = eval ("(" + dbdata + ")");

 //下面的代码需要改为函数，在获取后台数据后回调,
$(function () {
    //用来存储节点的顺序
    connections = [];
    //拖动节点开始时的事件
    dragger = function () {
    	
        this.ox = this.attr("x");
        this.oy = this.attr("y");
        this.animate({ "fill-opacity": .2 }, 500);
    };
    //拖动事件
    move = function (dx, dy) {
    	if(drawflag){
    		return;
    	}
        var att = { x: this.ox + dx, y: this.oy + dy };
        this.attr(att);

		var att2 = { x: att.x + RECT.x, y: att.y + RECT.yy };
		txts[this.zindex].attr(att2);
		
		//一个conn只有一个起点，一个终点
        for (var i = connections.length; i--; ) {
        	//只更新需要更新的关系
        	if( connections[i].start==this.taskId || connections[i].end==this.taskId  ){
        		
//         		alert("this.taskId:"+this.taskId+","+connections[i].start+"--->"+connections[i].end);
 				var point = getStartEnd2(connections[i].obj1, connections[i].obj2);
        		r.drawArr3( connections[i], point );
        	}
        }
    };
    //拖动结束后的事件
    up = function () {
        this.animate({ "fill-opacity": 0 }, 500);
        
//         alert( "up x:"+ this.ox + ", y: "+ this.oy );
//         alert( "up x:"+ this.attr("x") + ", y: "+ this.attr("y") );
		if( this.ox != this.attr("x") || this.oy != this.attr("y") ){
			ajaxReq("/curd/migJobContentCURDmodifyCoords.action", 
        		"{jobId:'"+jobId+"',taskId:'" + this.taskId + "',coords:'"+this.attr("x") + ","+ this.attr("y")+"'}" );
		}
    };
    //创建绘图对象
    r = Raphael("holder", $(window).width(), $(window).height());
//     var r = Raphael("holder", $('#workspace').width()-5, $('#workspace').height()-5 );

 /**
  * 绘制节点,将数据库中已经存在的任务绘制出来
  参数x数字左上角的x坐标
  y数字左上角的y坐标
  width数字宽度
  height数字高度
  r数字圆角半径，默认为​​0
  返回：对象Raphael元素对象，类型为“矩形
  矩形中心坐标：(x+width/2, y+height/2)
 	
  */
    shapes = new Array();
 	
	for( var i=0, j=tasks.length; i<j; i++ ){
		//在shape上加一个索引记录
		var shape = r.rect( tasks[i].zb.x, tasks[i].zb.y, RECT.w, RECT.h, RECT.r);
		shape.zindex = i;
		shapes.push( shape );
		
		var txt = r.text( tasks[i].zb.x + RECT.x, tasks[i].zb.y + RECT.yy, tasks[i].task.taskName )
// 		txt.dblclick(function () {  alert("shape.zindex:"+shape.zindex); });
		txts.push( txt );
	}
	
    //为节点添加样式和事件，并且绘制节点之间的箭头
    for (var i = 0, j = shapes.length; i < j; i++) {
        var color = Raphael.getColor();
		fmt.fmtShape( shapes[i], color, tasks[i].id.taskId );
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
	obj.attr({ fill: color, stroke: color, "fill-opacity": 0, "stroke-width": 2, cursor: "move" });

	//obj有一个默认的ID是数字，这也是绑定事件必须的
    obj.taskId = taskId;

    obj.drag(move, dragger, up);
    obj.dblclick(function () { app.f_modifyPara_open_window( this.zindex ) });
// 	obj.click( clickTask );
	obj.mousedown( mousedownTask );
	obj.mouseover( mouseoverTask );
	obj.mouseout( mouseoutTask );
			
	$( obj.node ).bind('contextmenu',function(e ){
		e.preventDefault();
		e.stopPropagation();
		app.zindex = r.getById( this.raphaelid ).zindex;
		
		$('#taskContextMenu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	});
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
    		e.stopPropagation();
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

/** 在工作区指定位置创建一个可拖放的元素   
 * x 相对于工作区的X坐标
 * y 相对于工作区的Y坐标
 */
function createNode2(x, y, node ){
	newCtrlNum ++;
	
	var shape = r.rect(x, y, RECT.w, RECT.h, RECT.r );
	shape.zindex = shapes.length;
	
	var paras=[],para;
	var tpl = node.attributes.tpl;
	for(var i = 0,j = tpl.length; i < j; i++ ){
		var param = {};
		param.id = {};
		param.id.comId = "";
		param.id.paraId = tpl[i].id.paraId;
		param.paraName = tpl[i].paraName;
		param.paraValue = (tpl[i].defValue==undefined?"":tpl[i].defValue);
		paras.push(param);
	}
	
    //将任务保存到数据库
    var jdata = {
    		taskName : node.text+"-任务"+newCtrlNum,
    		taskRemark : "",
    		taskNode : node.attributes.type,
    		coords : x+","+y,
    		jobId : jobId,
    		controlId: node.id,
    		paras: paras
    };
// 	JSON.stringify(obj);	$.toJSON( text )			json2text
// 	JSON.parse(text);		eval ("(" + text + ")");	text2json obj or array
   	$.ajax({
	   type: "POST",
	   url: getContextPath()+"/curd/migTaskInfoCURDaddRetId2.action",
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   async: false,//default:true
	   success: function( oResponse ){// oResponse.id is task id
			for(var i = 0,j = paras.length; i < j; i++ ){
				paras[i].id.comId = oResponse.comId;
			}
	   
		    var task = {
		    		id : {
		    				jobId : jobId, 
		    				taskId : oResponse.id 
		    		},
					taskStatus : "0", prepos : "", postpos:"",
					coords : x+","+y, zb : { x: x, y : y },
		  			task : {
		  				taskId : oResponse.id, 
		  				taskName : node.text+"-任务"+newCtrlNum, 
		  				taskNode : node.attributes.type, 
		  				comId: oResponse.comId, 
		  				taskAuthor : "", 
		  				taskRemark : "",
		  				taskUpdate : ""
		  			},
		  			paras: paras
			};
	   		
			tasks.push( task );
			shapes.push( shape );
			txts.push( r.text( x + RECT.x, y + RECT.yy, node.text+"-任务"+newCtrlNum ) );
		   
			var color = Raphael.getColor();
			fmt.fmtShape( shape, color, oResponse.id );
	   }
	});
};

function mousedownTask( event ){
	if(event.shiftKey){
		//起点是shape
		start = this;
		drawflag = true;
	}
};
function mouseoverTask( event ){
	motask = this;
	motask.overFlag = true;
};
function mouseoutTask( event ){
	
// 	alert("yes i am over");
	motask={};
	motask.overFlag = false;
};
function onBeforeDragTask(e){
	try{
// 		alert( "ce.data:"+$.toJSON(e.data) );
	}catch(ex){
		alert("ex:"+ex);
	}
	if (e.shiftKey==1)
    {
//     alert("The shift key was pressed!")
//     e.stopPropagation();//阻止事件冒泡。不再派发事件。
//     e.preventDefault(); //阻止默认行为 ( 表单提交 ),通知浏览器不要执行与事件关联的默认动作。
    }
  else
    {
//     alert("The shift key was NOT pressed!")
    }
};

function onStartDragTask(e){
	
};

function onDragTask(e){
   var d = e.data;
    if (d.left < 0){d.left = 0}
    if (d.top < 0){d.top = 0}
    if (d.left + $(d.target).outerWidth() > $(d.parent).width()){
        d.left = $(d.parent).width() - $(d.target).outerWidth();
    }
    if (d.top + $(d.target).outerHeight() > $(d.parent).height()){
        d.top = $(d.parent).height() - $(d.target).outerHeight();
    }
};

function onStopDragTask(e){
   var d = e.data;
};
</script>
<script type="text/javascript" src="../migcommon/migJobInfo/migJobInfo_modify.js"></script>
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
	
	<div data-options="region:'west',split:true" title="控件" style="width:250px;">
		<ul id="tt" class="easyui-tree" 
			data-options="url:'../tree/migControlInfoTREEquery_JEasyUI_Tree.action',
			animate:true, dnd:true, lines:true,mode: 'remote',revert: false,
			onLoadSuccess: function(node, data){
				$(this).tree('expandAll');
			}
			">
		</ul>
	</div>
	<div data-options="region:'center',title:'noheader',iconCls:'icon-add'" noheader="true">
		<div id="jobTabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div id="tabPanel1" title="${pojo.jobName }" cache="true" style="overflow:hidden;">
				<div class="easyui-layout" data-options="fit:true">
				
					<div id="workspace" noheader="true" class="easyui-droppable targetarea" 
						onmousemove="mousemove(event)" onmouseup = "mouseup(event)"
						data-options="region:'center',title:'noheader',iconCls:'icon-add',
							accept: '.tree-node',
		                    onDrop: function(e, source ){
		                    
		                    	var node = $('#tt').tree( 'getNode', source );
		                    	if(node.id.length !=1 ){
		                    		createNode2(x - px , source.pageY - py, node);
		                    	}
		                    }">
						<div id="holder">
					    </div>
					</div><!-- center   class="easyui-draggable"  -->
					
					
				</div><!-- layout -->
			</div>
		</div>
	</div>

	<div id="lineContextMenu" class="easyui-menu" style="width:120px;">
		<div>
			<span>评价</span>
			<div style="width:200px;">
				<div><b>无条件的</b></div>
				<div>当结果为真的时候进入下一步</div>
				<div>当结果为假的时候进入下一步</div>
			</div>
		</div>
		<div data-options="disabled:true">改变方向</div>
		<div data-options="disabled:true">使节点连接失效</div>
		<div data-options="disabled:false" onclick="javascript: unlink()">删除节点连接</div>
	</div>
	
	<div id="taskContextMenu" class="easyui-menu" style="width:120px;">
		<div data-options="disabled:false,iconCls:'icon-edit'" onclick="javascript: app.f_modifyPara_open_window( app.zindex )">编辑该任务</div>
		<div data-options="disabled:false,iconCls:'icon-remove'" onclick="javascript: app.removeTask( )">删除该任务</div>
		<div data-options="disabled:false" onclick="javascript: app.f_copy( )">复制</div>
	</div>
	
	<div id="blankContextMenu" class="easyui-menu" style="width:120px;">
		<div data-options="disabled:true,iconCls:'icon-add'" onclick="javascript: app.f_modifyPara_open_window( app.zindex )">新建记录</div>
		<div id = "blankpaste" data-options="disabled:true" onclick="javascript: app.f_paste()">从剪贴板粘贴</div>
		<div class="menu-sep"></div>
		<div data-options="disabled:false" onclick="javascript: app.f_modifyJob_open_window( jobId )">作业设置</div>
	</div>
	
	<!-- 控件参数修改界面 -->
	<div id="modifyPara"></div>
	
	<div id="modifyJob"></div>
	
<script type="text/javascript">
jQuery(document).ready(function()
{
	px = $('#workspace').offset().left;
	py = $('#workspace').offset().top;
// 	alert("px:"+px+" py:"+py);
// 	document.getElementById("myCanvas").width = $('#workspace').width()-5;
// 	document.getElementById("myCanvas").height = $('#workspace').height()-5;
});
/*  */
$(function(){
	$("#workspace").bind('contextmenu',function(e){
		e.preventDefault();
		$('#blankContextMenu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	});
});

function unlink(){
	var arrow = r.getById( app.selectArrId );
	var fromTask = arrow.data("fromTask");
	var toTask = arrow.data("toTask");
	
	arrow.remove();
	ajaxReq("/busi/migJobContentBUSIunLink.action", 
			"{jobId:'"+jobId+"',fromTask:'"+fromTask+"',toTask:'"+toTask+"'}" );
};
</script>
</body>
</html>