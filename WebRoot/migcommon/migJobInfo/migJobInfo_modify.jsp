<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>Job Edit</title>
	<link rel="stylesheet" type="text/css" href="../scripts/jquery-easyui/1.4.1/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../scripts/jquery-easyui/1.4.1/themes/icon.css">
	<link rel="shortcut icon" href="<%=basePath %>images/ico/edit.ico" type="image/x-icon" />
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
<!-- <script type="text/javascript" src="../scripts/jquery-easyui/1.4.1/locale/easyui-lang-zh_CN.js"></script> -->

<script type="text/javascript" src="../scripts/jquery-easyui/1.4.1/plugins/easyuiExtends.js"></script>
<script type="text/javascript" src="../scripts/raphael-min.js"></script>
<script type="text/javascript" src="../scripts/virtue/virtue.js"></script>
<script type="text/javascript" src="../scripts/const/const.js"></script>
<script type="text/javascript" src="../scripts/utils/commonUtil.js"></script>
<script type="text/javascript">
//	JSON.stringify(obj);	$.toJSON( text )			json2text
//	JSON.parse(text);		eval ("(" + text + ")");	text2json obj or array

var noquery = VIRTUE.noquery;
var query = VIRTUE.query;
var OPE = VIRTUE.operator;
var update = VIRTUE.update;
var del = VIRTUE.del;
var jobType = ajaxReqRespText("/cbb/dicCBBgetDicByType.action?dicType=job_type" );
jobType = eval ("(" + jobType + ")");

var controlData = ajaxReqRespText("/cbb/controlCBBgetDicByType.action" );
controlData = eval ("(" + controlData + ")");

/** validate and assign value **/
function pubPackingParameter(formId){
	var formData = $('#'+formId).form( 'getData');

	query.init();
	for (var field in formData) {
		if( formData[field] !== "" ){
			query.jdata.condition[field] = formData[field];
			query.jdata.operator[field] = OPE.CONTAIN;
		}
	}
	return true;
};

/** search data ,reload data @formPrefix business **/
function pubSubmitForm(formPrefix){
   	if( !pubPackingParameter( formPrefix+"_sform" ) ){
   		return;
   	}
   	$('#'+formPrefix+'_grid').datagrid( 'reload', {jdata: encodeURI( $.toJSON( query.jdata ) ) } );
};



var jobId = "${pojo.jobId }";
var jobName = "${pojo.jobName }";
// console.log("jobid:"+jobId);

var pojo = ${pojo };

var newNodeNum=100;
var x = 0;//光标绝对坐标
var y = 0;//光标绝对坐标
var px = 251;//工作区的坐标-取工作区左上角的坐标
var py = 30;//工作区的坐标

var rx = 0;//光标相对工作区的坐标
var ry = 0;//光标相对工作区的坐标

/** 画线开关,单击节点时如果shift键被按下就将drawflag设置为true */
var drawflag = false;

var app={};
app.pojo = {};
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
@var nodes 任务数据集合
@var txts 任务名称文本对象集合

@var tempconn 临时连接对象，连接时用
@var fmt 格式化函数对象集合
@var moNode 光标over的节点对象,如果光标没有在任何一个节点上 moNode.overFlag = false
*/
var connections=[],dragger,move,up,r,start,end,rel="",singleTimer;
var shapes=[], nodes,txts=[];//矩形框,任务json数据,任务文本
var tempconn={};
var fmt={};
var moNode={};//光标over的节点对象
var operateimg; //显示用于删除箭头的图标   relArrId 属性用来记录相关箭头的raphelid
moNode.overFlag = false;//光标over标志

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
		if( !moNode.overFlag || moNode.nodeId == start.nodeId){
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
// 			tempconn = { obj1: start, obj2: moNode };//如果这样tempconn就不再是原来的对象，主会多出一条箭头
			tempconn.obj1 = start;
			tempconn.obj2 = moNode;
			var point1 = getStartEnd2( tempconn.obj1, tempconn.obj2 );
			r.drawArr3( tempconn, point1 );
		}
	}
};

/** 当左键松开的时候，判断光标是否在节点'上，如果是就画线，否则什么也不做  **/
function mouseup(e){
	if(drawflag){
		drawflag = false;
		//光标不在node'上,删除箭头
		if( !moNode.overFlag || moNode.nodeId == start.nodeId ){

// 			connections.pop();
			tempconn.arrPath.remove();
			tempconn = { };
		}else{
			//将关系写入数据库
			tempconn.obj1 = start;
			tempconn.obj2 = moNode;
			var searchStr = start.nodeId +"-"+moNode.nodeId+"|";
			var searchStr2 = moNode.nodeId +"-"+start.nodeId+"|";
			if( rel.indexOf( searchStr ) == -1 
					&& rel.indexOf( searchStr2 ) == -1 ){//如果两节点还不存在关系

				//write to db
				ajaxReqTC("/busi/migJobContentBUSIlink.action", false,
					"{jobId:'"+jobId+"',fromTask:'"+start.nodeId+"',toTask:'"+moNode.nodeId+"'}" );
				
				var point1 = getStartEnd2(tempconn.obj1, tempconn.obj2);
				var conn = r.drawArr3(tempconn, point1);
				//因为只有画的箭头保存时已经存在但又没有fromTask和toTask属性
				tempconn.arrPath.data("fromTask", tempconn.obj1.nodeId )
		    	tempconn.arrPath.data("toTask", tempconn.obj2.nodeId )
		    	
		    	conn.start = start.nodeId;
		    	conn.end = moNode.nodeId;
				connections.push( conn );
				tempconn = { };//初始化。如果这样tempconn就不再是原来的对象
				
				rel  = rel + start.nodeId+"-"+moNode.nodeId+"|";
				
			}else{
// 				connections.pop();
				tempconn.arrPath.remove();
				tempconn = { };
			}
		}
	}
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

var dbdata = ${json };
// var dbdata = ajaxReqRespText("/migcommon/migJobInfo/jobcontents.json", 
// 		"{jobId:'"+jobId+"'}" );
		
nodes = dbdata;
//nodes = eval ("(" + dbdata + ")");

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
        for (var i =0, j = connections.length; i<j; i++ ) {
        	//只更新需要更新的关系
        	if( connections[i].start==this.nodeId || connections[i].end==this.nodeId  ){
        		
//         		alert("this.nodeId:"+this.nodeId+","+connections[i].start+"--->"+connections[i].end);
 				var point = getStartEnd2(connections[i].obj1, connections[i].obj2);
        		r.drawArr3( connections[i], point );
        	}
        }
    };
    //拖动结束后的事件
    up = function () {
        this.animate({ "fill-opacity": 0 }, 500);
        
		if( this.ox != this.attr("x") || this.oy != this.attr("y") ){

// 			console.log("this.attr(x):"+this.attr("x")+",this.attr(y):"+this.attr("y"));
			var x = new Number(this.attr("x"));
			var y = new Number(this.attr("y"));
// 			console.log("x.toFixed(0):"+x.toFixed(0)+",y.toFixed(0):"+y.toFixed(0));
			
			ajaxReqTC("/curd/migJobContentCURDmodifyCoords.action", false,
        		"{jobId:'"+jobId+"',nodeId:'" + this.nodeId + "',coords:'"+ x.toFixed(0) + ","+ y.toFixed(0)+"'}" );
		}
    };
    //画status图标
    drawIcon = function ( x, y, icon, alt ) {
    	var element = "<img alt='"+alt+"' src='../scripts/jquery-easyui/1.4.1/themes/icons/migcontrol/"+icon+".png'"
    	+" style='position: absolute;left:"+x+"px;top:"+y+"px'/>";
    	$("#holder").append(element); 
    };
    //创建绘图对象
    r = Raphael("holder", $(window).width(), $(window).height());
//     var r = Raphael("holder", $('#workspace').width()-5, $('#workspace').height()-5 );

	operateimg = r.image("../scripts/jquery-easyui/1.4.1/themes/icons/cancel.png", 
				100, 100, 16, 16);
	$( operateimg.node ).hide(); // 表示为display:none;
	
    	    //显示箭头中点的元素
//     		$("#operatediv").show(); //表示为display：block;
//     		$("#operatediv").hide(); // 表示为display:none;

	// node属性是raphel图形元素，不是elementId, raphaelid是元素的属性
	$( operateimg.node ).click(function(){
		console.log("operateimg clicked!");
		app.selectArrId = operateimg.relArrId;
		unlink();
		$( operateimg.node ).hide(); // 表示为display:none;
	});
	
// 	operateimg.attr({
// 		border:2
// 	});

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
 	
// 	alert( nodes[0].job );
	for( var i=0, j=nodes.length; i<j; i++ ){
		var nodeName = nodes[i].job==undefined? nodes[i].task.taskName : nodes[i].job.jobName;
		var remark = nodes[i].job==undefined? nodes[i].task.taskRemark : nodes[i].job.jobRemark;
		var icon = nodes[i].job==undefined? 
				controlIcon2[nodes[i].task.controlId] : "job2";
		
// 		drawIcon( nodes[i].zb.x + 14, nodes[i].zb.y + 4, icon, remark );
		var rr= 0;//区分job的矩形框用
		if(nodes[i].job==undefined){
			rr = RECT.r;
		}else{
			rr = RECT.r+20;
		}
		//在shape上加一个索引记录
// 		var shape = r.rect( nodes[i].zb.x, nodes[i].zb.y, RECT.w, RECT.h, rr);
// 		var pathstr = "M17.078,22.004l-1.758-4.129l-2.007,4.752l-7.519-3.289l0.174,3.905l9.437,4.374l10.909-5.365l-0.149-4.989L17.078,22.004zM29.454,6.619L18.521,3.383l-3.006,2.671l-3.091-2.359L1.546,8.199l3.795,3.048l-3.433,5.302l10.879,4.757l2.53-5.998l2.257,5.308l11.393-5.942l-3.105-4.709L29.454,6.619zM15.277,14.579l-9.059-3.83l9.275-4.101l9.608,3.255L15.277,14.579z";
// 		var shape = r.path(pathstr).attr({fill: "#000", stroke: "none" });
		var shape = r.image("../scripts/jquery-easyui/1.4.1/themes/icons/migcontrol/"+icon+".png", 
				nodes[i].zb.x, nodes[i].zb.y, 32, 32);
		
		shape.zindex = i;
		shape.data("remark", remark );
		shape.data("migNode", nodes[i] );
		shapes.push( shape );
		
		var txt = r.text( nodes[i].zb.x + RECT.x, nodes[i].zb.y + RECT.yy, nodeName )
// 		txt.dblclick(function () {  alert("shape.zindex:"+shape.zindex); });
		txts.push( txt );
		
// 		$("body").append("<b>Hello</b>"); 
	}
	
    //为节点添加样式和事件，并且绘制节点之间的箭头
    for (var i = 0, j = shapes.length; i < j; i++) {
        var color = Raphael.getColor();
		fmt.fmtShape( shapes[i], color, nodes[i].id.nodeId );
    }

    //objects, 连线用
    var os = new Array();
    
    for(var p=0, q = nodes.length; p<q; p++ ){//循环节点
    	
    	var postpos = nodes[p].postpos==null ? [] : nodes[p].postpos.split("|");
        for(var i=0, j=postpos.length; i<j; i++){//循环后置数量
        	rel  = rel + nodes[p].id.nodeId+"-"+postpos[i]+"|";
        	for( var m=0, n=shapes.length; m<n; m++  ){//循环找后置对应的shape
//         		alert( nodes[m].id.nodeId+"=="+postpos[i]+" : "+(nodes[m].id.nodeId == postpos[i]) );
        		if( nodes[m].id.nodeId == postpos[i] ){
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
    	conn.start = os[i].obj1.nodeId;
    	conn.end = os[i].obj2.nodeId;
    	connections.push( conn );
    }
});
 
/** 格式化子任务图标  **/
fmt.fmtShape = function (obj, color, nodeId ){
	obj.attr({ fill: color, stroke: "#990000", "fill-opacity": 0, "stroke-width": 2, cursor: "move" });

	//obj有一个默认的ID是数字，这也是绑定事件必须的
    obj.nodeId = nodeId;

    obj.drag(move, dragger, up);
    obj.dblclick(function () { app.f_modifyTask_open_window( this.zindex ) });
// 	obj.click( clickTask );
	obj.mousedown( mousedownTask );
	obj.mouseover( mouseoverTask );
	obj.mouseout( mouseoutTask );
			
	$( obj.node ).tooltip({
		position: 'right',
		content: obj.data("remark")
	});
	
	if( obj.data("migNode").job != undefined ){
		$( obj.node ).bind('contextmenu',function(e ){
			e.preventDefault();
			e.stopPropagation();
			app.zindex = r.getById( this.raphaelid ).zindex;
			
			$('#jobContextMenu').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		});
	}else{
		$( obj.node ).bind('contextmenu',function(e ){
			e.preventDefault();
			e.stopPropagation();
			app.zindex = r.getById( this.raphaelid ).zindex;
			
			$('#taskContextMenu').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		});
	}
};

/** 通用  **/
Raphael.fn.drawArr3 = function (obj, point) {

    var path1 = getArr(point.start.x, point.start.y, point.end.x, point.end.y, 8);
//     var path1 = ["M", point.start.x, point.start.y, "L", point.end.x, point.end.y ];

    if (obj.arrPath) {
    	obj.arrPath.attr({ path: path1,
//             'arrow-end': 'oval-wide-long',
//             stroke: "#000", 
            "stroke-width": 2 });
    	
    } else {
    	obj.arrPath = r.path( path1 );
    	
    	obj.arrPath.data("fromTask", obj.obj1==undefined ? "": obj.obj1.nodeId );
    	obj.arrPath.data("toTask", obj.obj2==undefined ? "": obj.obj2.nodeId )
//     	.attr({
//     		'arrow-end':'classic-wide-long'
//     		  stroke: "#f00",
//     		  "stroke-width": 3
//     		});
    	.attr({
//             'arrow-end': 'oval-wide-long',
//             stroke: "#000", 
            "stroke-width": 2
    		});
    	
    	$( obj.arrPath.node ).bind('contextmenu',function(e ){
    		e.preventDefault();
    		e.stopPropagation();
    		app.selectArrId = this.raphaelid;
    		
    		$('#lineContextMenu').menu('show', {
    			left: e.pageX,
    			top: e.pageY
    		});
    	});
    	/** 鼠标进入箭头事件  */
    	$( obj.arrPath.node ).mouseenter(function(e){
//     		console.log("show");
    		app.selectArrId = this.raphaelid;
			/** 用于单击删除图标时用, obj.arrPath.node.raphaelid 与this.raphaelid是价的 */
// 			console.log("raphaelid_1:"+obj.arrPath.node.raphaelid);
// 			console.log("raphaelid_2:"+this.raphaelid);

        	operateimg.relArrId = this.raphaelid;

    	    //显示箭头中点的元素
//     		$("#operatediv").show(); //表示为display：block;
//     		$("#operatediv").hide(); // 表示为display:none;

    		var x1 = point.start.x;
    		var y1 = point.start.y;
    		var x2 = point.end.x;
    		var y2 = point.end.y;
    	    var centerx = x1+(x2-x1)/2;
    	    var centery = y1+(y2-y1)/2;
    	            	
        	$( operateimg.node ).attr({x: e.pageX-251,y: e.pageY-42});
        	$( operateimg.node ).show(); // 表示为display:none;
        	
    	});
    	/** 鼠标进入箭头事件  */
    	$( obj.arrPath.node ).mouseout(function(e){
    		clearTimeout(singleTimer);
//     		console.log("if('"+operateimg.relArrId+"'=='"+this.raphaelid+"')$( operateimg.node ).hide();");
//     		console.log("$( operateimg.node ).hide();");
    		singleTimer = setTimeout("$( operateimg.node ).hide();", 2000);
//         	$( operateimg.node ).hide(); // 表示为display:none;
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
      
      var centerx = x1+(x2-x1)/2;
      var centery = y1+(y2-y1)/2;
      
//       var result = ["M", x1, y1, "L", x2, y2, "L", x2a, y2a, "M", x2, y2, "L", x2b, y2b]
var result = "M"+ x1 +","+ y1 + "L"+ x2 +","+ y2 
	+ "L"+ x2a +","+ y2a 
	+ "M"+ x2+","+ y2 +"L"+ x2b +","+ y2b;
//  	+ "M"+centerx+","+centery+"L"+(centerx+1)+","+(centery+1);
//       var result = "M"+ x1 +","+ y1 + "L"+ x2 +","+ y2 + "L"+ x2a +","+ y2a + "M"+ x2+","+ y2 +"L"+ x2b +","+ y2b +"m47.446122,148.46699l47.463496,149.0968l47.430196,149.09969c47.363594,148.8854247.278172,148.7259147.173az931,148.62119c47.069686,148.5164646.943725,148.464146.796048,148.4641c46.681186,148.464146.582493,148.4875146.499968,148.53432c46.417441,148.5811346.340465,148.6571446.269039,148.76235c46.124256,148.9775946.051865,149.2237246.051865,149.50074c46.051865,149.6329846.069239,149.7577346.103987,149.875c46.138734,149.9922846.189408,150.095846.256009,150.18556c46.385347,150.359346.54316,150.4461746.729448,150.44617c46.802803,150.4461746.871816,150.4307346.936487,150.39984c47.001155,150.3689547.054242,150.3264847.095748,150.27243c47.152694,150.1971547.181168,150.1078647.18117,150.00458c47.181168,149.9090347.155349,149.8400247.103711,149.79755c47.05207,149.7550846.96689,149.7338446.84817,149.73384l46.84817,149.70054l47.771883,149.70054l47.771883,149.73384c47.686942,149.7415647.62734,149.7533947.593076,149.76931c47.558809,149.7852447.533472,149.8125147.517065,149.85111c47.498724,149.8935947.489555,149.9780447.489557,150.10448c47.489555,150.1392347.491485,150.1932847.495348,150.26664l47.498244,150.29994c47.461564,150.2864347.434538,150.2796747.417165,150.27967c47.395929,150.2796747.368903,150.2907747.336087,150.31297c47.252112,150.3708847.154625,150.4152847.043626,150.44617c46.932625,150.4770646.815351,150.492546.691804,150.4925c46.521925,150.492546.368697,150.4635546.23212,150.40563c46.095541,150.3477245.984782,150.2647145.899844,150.15661c45.836139,150.0755345.787154,149.9790145.752889,149.86704c45.718624,149.7550845.701491,149.6363545.701491,149.51088c45.701491,149.338145.732378,149.1773945.794152,149.02875c45.855926,148.8801145.942795,148.7575346.054761,148.661c46.146456,148.5828246.252147,148.5224946.371835,148.48002c46.491521,148.4375646.617964,148.4163246.751165,148.41632c46.828382,148.4163246.900049,148.4235646.966168,148.43804c47.032284,148.4525247.102503,148.4761647.176826,148.50898l47.262248,148.54807c47.283481,148.5567647.302785,148.561147.320161,148.5611c47.358768,148.561147.388207,148.5297347.408479,148.46699l47.446122,148.46699z";

// 	var circle = r.circle(centerx, centery, 2);
// 	// 给绘制的圆圈填充红色 (#f00)
// 	circle.attr("fill", "#f00");
	
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

app.openJobChooseDialog = function(x, y){
	$("#chooseJob-dlg").dialog({
		title : "选择作业",
		href :   getContextPath()+"/migcommon/migJobInfo/migJobInfo_main3.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 600,
		height : 400,
		buttons: [{
	        text:'添加',
	        iconCls:'icon-add',
	        handler:function(){
	        	var row = $('#job_grid').datagrid("getSelected");
	        	if(row == null ){
	        		alert("请选择作业！");
	        		return;
	        	}
	        	app.f_pasteJob(x, y, row.id, row.name, jobId );
	        	x=x+60;
	        	//作业目前后台不支持递归复制
	        	return false;//for IE6 support
	        }
	    },{
	        text:'确定',
	        iconCls:'icon-ok',
	        handler:function(){
	        	var row = $('#job_grid').datagrid("getSelected");
	        	if(row == null ){
	        		alert("请选择作业！");
	        		return;
	        	}
	        	app.f_pasteJob(x, y, row.id, row.name, jobId )
	        	//作业目前后台不支持递归复制
	        	$('#chooseJob-dlg').dialog('close');
	        	return false;//for IE6 support
	        }
	    },{
	        text:'取消',
	        iconCls:'icon-cancel',
	        handler:function(){
	        	$('#chooseJob-dlg').dialog('close');
	        }
	    }]
	});
	$('#chooseJob-dlg' ).dialog('open');
};
app.openTaskChooseDialog = function(jobId){
	$("#chooseTask-dlg").dialog({
		title : "选择任务",
		href :   getContextPath()+"/migcommon/migTaskInfo/migTaskInfo_main3.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 650,
		height : 430,
		buttons: [{
	        text:'确定',
	        iconCls:'icon-ok',
	        handler:function(){
	        	var rows = $('#task_grid').datagrid("getSelections");
	        	
// 	        	console.log("1 rows.length:"+ $.toJSON($('#task_grid').datagrid("getSelections")) );
	        	if( rows.length == 0 ){
	        		alert("请选择任务！");
	        		return;
	        	}
	        	app.f_addTasks2JobRequest( jobId, rows );
	        	return false;//for IE6 support
	        }
	    },{
	        text:'取消',
	        iconCls:'icon-cancel',
	        handler:function(){
	        	$('#chooseTask-dlg').dialog('close');
	        }
	    }]
	});
	$('#chooseTask-dlg' ).dialog('open');
};

app.f_addTasks2JobRequest = function( jobId, rows ){
	var ss = [];
// 	console.log("2 rows.length:"+rows.length);
	for(var i=0; i<rows.length; i++){
        var row = rows[i];
        ss.push( row.task_id );
    }
	var jdata={
			jobId: jobId,
			taskIds: ss
	};
	
   	$.ajax({
 	   type: "POST",
 	   url: getContextPath()+"/busi/migJobContentBUSImoveTasks.action",
 	   data: {
 		   jdata: $.toJSON( jdata )
 	   },
 	   async: false,//default:true
 	   success: function( oResponse ){// oResponse.id is task id
 			if( !oResponse.success ){
 				alert(oResponse.msg);
 				return;
 			}else{
 				$('#chooseTask-dlg').dialog('close');
	        	window.location.reload();
 			}
 	   }
 	});
};

/** 粘贴
@parentJobId   mainjob id
@subJobId subjob name
**/
app.f_pasteJob = function(x, y, subJobId, subJobName, parentJobId){

	//将任务保存到数据库
    var jdata = {
    	jobId : subJobId,
    	jobName : subJobName,
		type : 5,
		jobRemark : "",
		coords : x+","+y,
		parentJobId : parentJobId
    };

   	$.ajax({
	   type: "POST",
// 	   url: getContextPath()+"/busi/migJobContentBUSIcopyJob.action",
	   url: getContextPath()+"/busi/migJobContentBUSIhangJob.action",
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   async: false,//default:true
	   success: function( oResponse ){// oResponse.id is task id
			if( !oResponse.success ){
				alert(oResponse.msg);
				return;
			}
			$('#job_grid').datagrid("unselectAll");
// 			var shape = r.rect(x, y, RECT.w, RECT.h, RECT.r+20 );
			var shape = r.image("../scripts/jquery-easyui/1.4.1/themes/icons/migcontrol/job2.png", x, y, 32, 32);
			
			shape.zindex = shapes.length;
		    var job = {
		    		id : {
		    				jobId : parentJobId, //this is current main jobid
		    				nodeId : oResponse.id //this is copy's jobid
		    		},
		    		"isLeaf" : 0,
					nodeStatus : "0", prepos : "", postpos:"",
					coords : x+","+y, zb : { x: x, y : y },
					task : null,
		  			job : {
		  				jobId : oResponse.id,
		  				jobName : jdata.jobName,
		  				type : 5,
		  				"jobAuthor" : "", 
		  				"jobRemark" : "", 
		  				"jobUpdate" : ""
		  			}
			};
	   		
			nodes.push( job );
			shape.data("migNode", job );
			shapes.push( shape );
			txts.push( r.text( x + RECT.x, y + RECT.yy, jdata.jobName ) );
		   
			var color = Raphael.getColor();
			fmt.fmtShape( shape, color, oResponse.id );
	   }
	});
};

/** 在工作区指定位置创建一个可拖放的子任务图标  
 * @x 相对于工作区的X坐标
 * @y 相对于工作区的Y坐标
 * @node control tree's node
 */
function createNode( x, y, node ){
	newNodeNum ++;
	
	if( node.attributes.type == "T" ){
		createTaskNode( x, y,node );
	}else{
			$.messager.confirm('Confirm', "Wether choose from exist's jobs?", function(r){
				if (r==true)
				  {
					app.openJobChooseDialog(x, y);
				  }
				else
				  {
					createJobNode( x, y,node );
				  }
			});
	}
};
/** 在工作区指定位置创建一个可拖放的元素   
 * @x 相对于工作区的X坐标
 * @y 相对于工作区的Y坐标
 * @node control tree's node
 */
function createTaskNode(x, y, node ){

	//将任务保存到数据库
    var jdata = {
    		taskName : node.text+"-node-"+newNodeNum,
    		taskRemark : "",
    		coords : x+","+y,
    		jobId : jobId,
    		controlId: node.id
    };

   	$.ajax({
	   type: "POST",
	   url: getContextPath()+"/curd/migJobContentCURDaddTaskNodeRetId.action",
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   async: false,//default:true
	   success: function( oResponse ){// oResponse.id is task id
			if( !oResponse.success ){
				alert(oResponse.msg);
				return;
			}
// 			var shape = r.rect(x, y, RECT.w, RECT.h, RECT.r );
			var icon = controlIcon2[node.id];
			var shape = r.image("../scripts/jquery-easyui/1.4.1/themes/icons/migcontrol/"+icon+".png", x, y, 32, 32);
			
			shape.zindex = shapes.length;
		    var task = {
		    		id : {
		    				jobId : jobId, 
		    				nodeId : oResponse.id 
		    		},
		    		"isLeaf" : 1,
					nodeStatus : "0", prepos : "", postpos:"",
					coords : x+","+y, zb : { x: x, y : y },
		  			task : {
		  				taskId : oResponse.id, 
		  				taskName : jdata.taskName,
		  				"controlId" : node.id,
		  				"comId" : "", 
		  				"taskAuthor" : "", 
		  				"taskRemark" : "", 
		  				"taskUpdate" : ""
		  			},
		  			job:null
			};
	   		
			nodes.push( task );
			shape.data("migNode", task );
			shapes.push( shape );
			txts.push( r.text( x + RECT.x, y + RECT.yy, jdata.taskName ) );
		   
			var color = Raphael.getColor();
			fmt.fmtShape( shape, color, oResponse.id );
	   }
	});
};
/** 在工作区指定位置创建一个可拖放的作业图标  
 * x 相对于工作区的X坐标
 * y 相对于工作区的Y坐标
 */
function createJobNode(x, y, node ){
	
	//将任务保存到数据库
    var jdata = {
    		jobName : node.text+"-subjob-"+newNodeNum,
    		type : 5,
    		jobRemark : "",
    		coords : x+","+y,
    		jobId : jobId
    };

   	$.ajax({
	   type: "POST",
	   url: getContextPath()+"/curd/migJobContentCURDaddJobNodeRetId.action",
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   async: false,//default:true
	   success: function( oResponse ){// oResponse.id is task id
			if( !oResponse.success ){
				alert(oResponse.msg);
				return;
			}
// 			var shape = r.rect(x, y, RECT.w, RECT.h, RECT.r+20 );
			var shape = r.image("../scripts/jquery-easyui/1.4.1/themes/icons/migcontrol/job2.png", x, y, 32, 32);
			shape.zindex = shapes.length;
		    var job = {
		    		id : {
		    				jobId : jobId, 
		    				nodeId : oResponse.id 
		    		},
		    		"isLeaf" : 0,
					nodeStatus : "0", prepos : "", postpos:"",
					coords : x+","+y, zb : { x: x, y : y },
					task : null,
		  			job : {
		  				jobId : oResponse.id,
		  				jobName : jdata.jobName,
		  				type : 5,
		  				"jobAuthor" : "", 
		  				"jobRemark" : "", 
		  				"jobUpdate" : ""
		  			}
			};
	   		
			nodes.push( job );
			shape.data("migNode", job );
			shapes.push( shape );
			txts.push( r.text( x + RECT.x, y + RECT.yy, jdata.jobName ) );
		   
			var color = Raphael.getColor();
			fmt.fmtShape( shape, color, oResponse.id );
	   }
	});
};
app.addTasks2Job = function(){
	var zindex = app.zindex;
	var jobId = nodes[zindex].id.nodeId;
	app.openTaskChooseDialog(jobId);
};

function mousedownTask( event ){
	if(event.shiftKey){
		//起点是shape
		start = this;
		drawflag = true;
	}
};
function mouseoverTask( event ){
	moNode = this;
	moNode.overFlag = true;
};
function mouseoutTask( event ){
	
// 	alert("yes i am over");
	moNode={};
	moNode.overFlag = false;
};

/** 实例化作业   **/
function instanceJob(){
// 	var rows = $('#grid').datagrid('getSelections');
// 	if(rows.length!=1){
// 		alert("Please select one row!");
// 		return false;
// 	};
// 	var row = $('#grid').datagrid('getSelected');
	var row = {};
	row.id= jobId;
	row.type = pojo.type;
	row.name = jobName;
	
	console.log($.toJSON(row));
	
	if (row ) {
		app.selectedRow = row;

		$("#instanceJobParam").dialog({
			title : "Parameter Setting",
			href :  getContextPath()+'/migcommon/migJobPara/migJobPara_instance.html',
			iconCls : 'icon-edit',
			modal : true,
			closed : true,
			resizable: true,
			minimizable: true,
			maximizable: true,
			cache: false,
			width : 550,
			height : 350,
			buttons: [{
	            text:'OK',
	            iconCls:'icon-ok',
	            handler:function(){
	            	app.instanceJobParam(  jobId, pojo.type );
	            	return false;//for IE6 support
	            }
	        },{
	            text:'Cancel',
	            iconCls:'icon-cancel',
	            handler:function(){
	            	$("#instanceJobParam").dialog('close');
	            }
	        }]
		});
		$("#instanceJobParam").dialog('open');
	}
};
app.instanceJobParam = function(  ){
	if( ! $('#instanceJob_form').form("validate")
			|| app.jobparaloaded==false
			){
		return;
	}
	
	var formData = $('#instanceJob_form').form('getData', true);
	var rows = $('#jobparam_grid').datagrid( 'getRows');
	
	var jdata = {
		jobId: app.selectedRow.id, 
		type: app.selectedRow.type,
		jobInsName: formData.jobInsName,
		dryrunId: formData.dryrunId,
		rows: rows
	};

	$.ajax({
	   type: "POST",
	   url: getContextPath()+"/busi/migJobInfoBUSIinstanceJob.action",
	   async: false,
	   data: {
		   jdata:  $.toJSON( jdata )
	   },
		success: function(oResponse){
			oResponse = eval("("+oResponse+")");
			alert( oResponse.msg );
// 			console.log(oResponse.success);
// 			$("#instanceJobParam").dialog('close');
			if (oResponse.success){
// 				$('#grid').datagrid('reload',{jdata: encodeURI( $.toJSON( query.jdata ) ) } );	// reload data
				$("#instanceJobParam").dialog('close');
			}
	   }
	});
};

</script>
<script type="text/javascript" src="../migcommon/migJobInfo/migJobInfo_modify.js"></script>
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
	<div data-options="region:'north'" style="height:25px">
		<a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true">Home</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-edit'">Edit</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-help',disabled:true">Help</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm3'">About</a>
	</div>
	<div id="mm1" style="width:150px;">
		<div data-options="iconCls:'icon-undo',disabled:true">Undo</div>
		<div data-options="iconCls:'icon-redo',disabled:true">Redo</div>
		<div class="menu-sep"></div>
		<div data-options="disabled:false" onclick="javascript: app.f_modifyJob_open_window( jobId, pojo )">Setting</div>
		<div data-options="disabled:false" onclick="javascript: instanceJob()">Instance Job</div>
	</div>
	<div id="mm2" style="width:100px;">
		<div>Help</div>
		<div>Update</div>
		<div>About</div>
	</div>
	<div id="mm3" class="menu-content" style="background:#f0f0f0;padding:10px;text-align:left">
		<img src="../images/job/asiainfo.jpg">
		<p style="font-size:14px;color:#444;">AsiaInfo is an innovative provider of software and IT services to the telecommunications industry. </p>
	</div>
	<div data-options="region:'west', split:true, onCollapse :function(){
		px = 30;
	}, onExpand :function(){
		px = 251;
	}" title="control tree" style="width:250px;">
		<ul id="tt" class="easyui-tree" 
			data-options="url:'../tree/migControlInfoTREEquery_JEasyUI_Tree.action',
			animate:true, dnd:true, lines:true,mode: 'remote',revert: false,
			onLoadSuccess: function( node, data ){
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
		                    		createNode(x - px , source.pageY - py, node);
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
		<div data-options="disabled:false" onclick="javascript: unlink()">remove link</div>
	</div>
	
	<div id="taskContextMenu" class="easyui-menu" style="width:140px;">
		<div data-options="disabled:false,iconCls:'icon-edit'" onclick="javascript: app.f_modifyTask_open_window( app.zindex )">edit task</div>
		<div data-options="disabled:false,iconCls:'icon-remove'" onclick="javascript: app.removeTask( )">remove task</div>
		<div data-options="disabled:false" onclick="javascript: app.f_copy( )">copy</div>
	</div>
	
	<div id="jobContextMenu" class="easyui-menu" style="width:140px;">
		<div data-options="disabled:false,iconCls:'icon-edit'" onclick="javascript: app.f_modifyTask_open_window( app.zindex )">edit subjob</div>
		<div data-options="disabled:false,iconCls:'icon-unlink'" onclick="javascript: app.unlinkJob( )">unlink job</div>
		<div data-options="disabled:false,iconCls:'icon-remove'" onclick="javascript: app.deleteJob( )">delete job</div>
		<div data-options="disabled:false" onclick="javascript: app.f_copy( )">copy</div>
		<div data-options="disabled:false,iconCls:'icon-add'" onclick="javascript: app.addTasks2Job( )">add tasks</div>
		<div data-options="disabled:false" onclick="javascript: app.f_modifyJob_open_window( nodes[app.zindex].job.jobId, nodes[app.zindex].job )">setting</div>
	</div>
	
<!-- 	<div id="operatediv" style="display:none"> -->
<!--   		<img id="operateimg" src="../scripts/jquery-easyui/1.4.1/themes/icons/pencil.png" -->
<!-- 		 onclick="javascript:window.open('cookie_get.png', 'cookies获取', {}, {});"> -->
<!-- 	</div> -->
				 
	<div id="blankContextMenu" class="easyui-menu" style="width:140px;">
		<div data-options="disabled:true,iconCls:'icon-add'" onclick="javascript: app.f_modifyTask_open_window( app.zindex )">New</div>
		<div id = "blankpaste" data-options="disabled:true" onclick="javascript: app.f_pasteTask()">paste</div>
		<div class="menu-sep"></div>
		<div data-options="disabled:false" onclick="javascript: app.f_modifyJob_open_window( jobId, pojo )">job setting</div>
	</div>
	
	<!-- 控件参数修改界面,任务基本信息、任务参数信息、控件参数信息 -->
	<div id="modifyTask"></div>
	
	<div id="modifyJob"></div>

	<div id="chooseJob-dlg"></div>

	<div id="chooseTask-dlg"></div>

	<div id="instanceJobParam"></div>
	
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
	unlinkCommon(arrow);
};
function unlinkCommon(arrow){
	var fromTask = arrow.data("fromTask");
	var toTask = arrow.data("toTask");
	
	arrow.remove();
	rel  = rel.replace( fromTask + "-" + toTask + "|", "" );
	
	ajaxReqTC("/busi/migJobContentBUSIunLink.action", false,
			"{jobId:'"+jobId+"',fromTask:'"+fromTask+"',toTask:'"+toTask+"'}" );
};
</script>
</body>
</html>