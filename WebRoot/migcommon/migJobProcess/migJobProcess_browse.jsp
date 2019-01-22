<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<!-- 实例监控是页面间隔发起sendMessage 触发服务端推送-->
	<title>Instance Monitor(Comet)</title>
	<link rel="stylesheet" type="text/css" href="../scripts/jquery-easyui/1.4.1/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../scripts/jquery-easyui/1.4.1/themes/icon.css">
	<link rel="shortcut icon" href="<%=basePath %>images/ico/monitor.ico" type="image/x-icon" />
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
.m_copyright { height:28px; background:url(../images/frame-img/s.png) repeat-x 0 -129px; line-height:28px; color:#89bff0; font-family:verdana; text-align:center;}
.icon-back {
    background: transparent url("../images/virtue/back.png") no-repeat scroll center center;
}
.icon-forward {
    background: transparent url("../images/virtue/forward.png") no-repeat scroll center center;
}
.icon-reload {
    background: transparent url("../scripts/jquery-easyui/1.4.1/themes/icons/reload.png") no-repeat scroll center center;
}
.icon-home {
    background: transparent url("../images/virtue/home.png") no-repeat scroll center center;
}
</style>
	<script type="text/javascript" src="../scripts/jquery-easyui/1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="../scripts/jquery-easyui/1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../scripts/json/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="../scripts/raphael-min.js"></script>
<script type="text/javascript" src="../scripts/virtue/virtue.js"></script>
<script type="text/javascript" src="../scripts/const/const.js"></script>
<script type="text/javascript" src="../scripts/utils/commonUtil.js"></script>

<script type='text/javascript' src='<%=basePath%>dwr/engine.js'> </script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/MigJobInsComet.js'> </script>
<script type='text/javascript' src='<%=basePath%>dwr/util.js'> </script>
<script type="text/javascript" src="../migcommon/migJobProcess/migJobProcess_browse_comet.js"> </script>
<script type="text/javascript">
var topJobId = "${pojo.jobId }";
var currentJobId = "${jid }";
var pojo = ${pojo};

var insId = "${pojo.jobInsId }";
var insName = "${pojo.jobInsName }";
var insAuthor = "${pojo.author }";

// alert("$.toJSON( pojo ):"+ $.toJSON( pojo ) );

var newCtrlNum=100;
var x = 0;//光标绝对坐标
var y = 0;//光标绝对坐标
var px = 251;//工作区的坐标-取工作区左上角的坐标
var py = 30;//工作区的坐标

var rx = 0;//光标相对工作区的坐标
var ry = 0;//光标相对工作区的坐标

var app={};
app.pojo={};
app.currentJobId ="";
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
@var nodes 任务数据集合
@var txts 任务名称文本对象集合

@var tempconn 临时连接对象，连接时用
@var fmt 格式化函数对象集合
@var motask 光标over的节点对象,如果光标没有在任何一个节点上 motask.overFlag = false
*/
var connections=[],dragger,move,up,r,start,end,rel="";
var shapes=[], nodes,txts=[],imges=[];//矩形框,任务json数据,任务文本
var tempconn={};
var fmt={};
/** 作业监控数据结构
 *
	*  */
var dbdata = ${json };//默认认为nodes中与images中的及状态数据中的元素顺序是一样的

nodes = dbdata;
// nodes = eval ("(" + dbdata + ")");
// console.log("nodes:"+$.toJSON(nodes) );

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
        var att = { x: this.ox + dx, y: this.oy + dy };
        this.attr(att);

		var att2 = { x: att.x + RECT.x, y: att.y + RECT.yy };
		txts[this.zindex].attr(att2);
		var att3 = { x: att.x + RECT.x, y: att.y };
		if(nodes[this.zindex].status != 0){
			imges[this.zindex].attr(att3);
		}
		
		//一个conn只有一个起点，一个终点
        for (var i =0, j = connections.length; i<j; i++ ) {
        	//只更新需要更新的关系
        	if( connections[i].start==this.nodeId || connections[i].end==this.nodeId  ){
 				var point = getStartEnd2(connections[i].obj1, connections[i].obj2);
        		r.drawArr3( connections[i], point );
        	}
        }
    };
    //拖动结束后的事件
    up = function () {
        this.animate({ "fill-opacity": 0 }, 500);
    };
    
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
// r.path("M" + x1 + " 20L" + x2 + " 20").attr({ stroke: COLOR.UNEXECUTE,"stroke-width": 16 });
// 	r.text( x3, 20, PSTATENAME.UNEXECUTE );
	
// r.path("M" + x1 + " 40L" + x2 + " 40").attr({ stroke: COLOR.EXECUTING,"stroke-width": 16 });
    r.image("../images/job/"+PSTATEICON[1], x1, 33, 16, 16);
	r.text( x3, 40, PSTATENAME.EXECUTING )
	
// r.path("M" + x1 + " 60L" + x2 + " 60").attr({ stroke: COLOR.FINISH, "stroke-width": 16 });
	r.image("../images/job/"+PSTATEICON[2], x1, 53, 16, 16);
	r.text( x3, 60, PSTATENAME.FINISH )
	
// r.path("M" + x1 + " 80L" + x2 + " 80").attr({ stroke: COLOR.PAUSE, "stroke-width": 16 });
	r.image("../images/job/"+PSTATEICON[3], x1, 73, 16, 16);
	r.text( x3, 80, PSTATENAME.PAUSE )
	
// r.path("M" + x1 + " 100L" + x2 + " 100").attr({ stroke: COLOR.SKIP, "stroke-width": 16 });
	r.image("../images/job/"+PSTATEICON[4], x1, 93, 16, 16);
	r.text( x3, 100, PSTATENAME.SKIP )
	
// r.path("M" + x1 + " 120L" + x2 + " 120").attr({ stroke: COLOR.ERROR, "stroke-width": 16 });
	r.image("../images/job/"+PSTATEICON[5], x1, 113, 16, 16);
	r.text( x3, 120, PSTATENAME.PAUSING )

// r.path("M" + x1 + " 120L" + x2 + " 140").attr({ stroke: COLOR.ERROR, "stroke-width": 16 });
	r.image("../images/job/"+PSTATEICON[-1], x1, 133, 16, 16);
	r.text( x3, 140, PSTATENAME.ERROR )
    shapes = new Array();
 	
    //画图标： @icon status 
    drawIcon = function ( x, y, icon, alt ) {
    	var element = "<img alt='"+alt+"' src='../scripts/jquery-easyui/1.4.1/themes/icons/migcontrol/"+icon+".png'"
    	+" style='position: absolute;left:"+x+"px;top:"+y+"px'/>";
    	$("#holder").append(element); 
    };
    
	for( var i=0, j=nodes.length; i<j; i++ ){

		var remark = nodes[i].nodeRemark;
		var icon = nodes[i].isleaf==1? 
				controlIcon2[nodes[i].controlId] : "job2";
				
		var rr= 0;//区分job的矩形框用
		if(nodes[i].isleaf==1){
			rr = RECT.r;
		}else{
			rr = RECT.r+20;
		}
// 		var shape = r.rect( nodes[i].zb.x, nodes[i].zb.y, RECT.w, RECT.h, rr);
		var shape = r.image("../scripts/jquery-easyui/1.4.1/themes/icons/migcontrol/"+icon+".png", nodes[i].zb.x, nodes[i].zb.y, 32, 32);
		
		shape.zindex = i;
		shape.data("remark", nodes[i].remark);
		shape.data("jobId", nodes[i].jobId);
		shape.data("jobInsId", nodes[i].jobInsId);
		shape.data("nodeId", nodes[i].nodeId);
		shape.data("isleaf", nodes[i].isleaf);
		
		shapes.push( shape );
		
		var txt = r.text( nodes[i].zb.x + RECT.x, nodes[i].zb.y + RECT.yy, nodes[i].nodeName )
		txts.push( txt );
// 		$( txt.node ).tooltip({
// 			position: 'right',
// 			content: nodes[i].nodeRemark
// 		});
	}
	
    //为节点添加样式和事件，并且绘制节点之间的箭头
    for (var i = 0, j = shapes.length; i < j; i++) {
//         var color = Raphael.getColor();
		var color = COLORVALUE[nodes[i].status];
		fmt.fmtShape( shapes[i], color, nodes[i].nodeId );
		
		var img = {};
		
// 		if(nodes[i].status != 0){
			img = r.image("../images/job/"+PSTATEICON[nodes[i].status], nodes[i].zb.x + RECT.x, nodes[i].zb.y, 16, 16);
			img.zindex = i;
			img.dblclick(function () { app.f_viewPara_open_window( this.zindex ) });
// 			console.log(img.id);
			img.id="img"+nodes[i].nodeId
// 			console.log(img.id);
// 		}
		imges.push( img );
    }

    //objects, 连线用
    var os = new Array();
    
    for(var p=0, q = nodes.length; p<q; p++ ){//循环节点
    	
    	var postpos = nodes[p].postpos==null ? [] : nodes[p].postpos.split("|");
        for(var i=0, j=postpos.length; i<j; i++){//循环后置数量
        	rel  = rel + nodes[p].nodeId+"-"+postpos[i]+"|";
        	for( var m=0, n=shapes.length; m<n; m++  ){//循环找后置对应的shape
//         		alert( nodes[m].nodeId+"=="+postpos[i]+" : "+(nodes[m].nodeId == postpos[i]) );
        		if( nodes[m].nodeId == postpos[i] ){
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
	obj.attr({ fill: color, stroke: color, "fill-opacity": 0, "stroke-width": 2, cursor: "curor" });

  //obj有一个默认的ID是数字，这也是绑定事件必须的
    obj.nodeId = nodeId;
  
    obj.drag(move, dragger, up);
    obj.dblclick(function () { app.f_viewPara_open_window( this.zindex ) });
    
// 	$( obj.node ).tooltip({
// 		content: $("<div>"+obj.data("remark")+"</div>"),
// 		showEvent: 'click',
// 		onShow: function(){
// 			var t = $(this);
// 			t.tooltip('tip').unbind().bind('mouseenter', function(){
// 				t.tooltip('show');
// 			}).bind('mouseleave', function(){
// 				t.tooltip('hide');
// 			});
// 		}
// 	});
	
	if( obj.data("isleaf") == NodeType.NOLEAF ){
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
    	obj.arrPath.attr({ path: path1 });
    	
    } else {
    	obj.arrPath = r.path( path1 );
    	obj.arrPath.data("fromTask", obj.obj1==undefined ? "": obj.obj1.nodeId )
    	obj.arrPath.data("toTask", obj.obj2==undefined ? "": obj.obj2.nodeId )
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
<script type="text/javascript" src="../migcommon/migJobProcess/migJobProcess_browse.js"></script>
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
		<div data-options="disabled:false" onclick="javascript: app.f_viewJobIns_open_window( insId, pojo )">Setting</div>
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
	
	<div data-options="region:'center',title:'noheader',iconCls:'icon-add'" noheader="true">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div id="tabPanel1" title="${pojo.jobInsName }" cache="true" style="overflow:hidden;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north'" style="height:25px">
						<div style="border:1px solid #000; border-top:none;">
							<a href="#" class="easyui-linkbutton" data-options="plain:true,disabled: false,iconCls:'icon-run'" onclick="javascript: app.f_startJob( insId )"></a>
							<a href="#" class="easyui-linkbutton" data-options="plain:true,disabled: false,iconCls:'icon-pause'" onclick="javascript: app.f_pauseJob( insId )"></a>
							<a href="#" class="easyui-linkbutton" data-options="plain:true,disabled: false,iconCls:'icon-back'" onclick="javascript: app.f_setJobInit( insId )"></a>
							<a href="#" class="easyui-linkbutton" data-options="plain:true,disabled: false,iconCls:'icon-unlock'" onclick="javascript: app.f_unlockJob( insId )"></a>
							<a href="#" class="easyui-linkbutton" data-options="plain:true,disabled: false,iconCls:'icon-search'" onclick="javascript: app.f_viewJobIns_open_window( insId, pojo )"></a>
					
						</div>
					</div>
					<div id="workspace" noheader="true" class="easyui-droppable targetarea" 
						data-options="region:'center',title:'noheader',iconCls:'icon-add'">
						<div id="holder">
					    </div>
					</div><!-- center   class="easyui-draggable"  -->
					
				</div><!-- layout -->
			</div>
		</div>
	</div>

	<div id="taskContextMenu" class="easyui-menu" style="width:160px;">
		<div data-options="disabled: false,iconCls:'icon-stop'" onclick="javascript: app.f_pauseTask( insId, nodes[app.zindex].nodeId )">Stop Task</div>
		<div>
			<span>Set Task State</span>
			<div style="width:120px;">
				<div data-options="disabled: false,iconCls:'icon-init'" onclick="javascript: app.f_setTaskInit( insId, nodes[app.zindex].nodeId )">Init</div>
				<div data-options="disabled: false,iconCls:'icon-finish'" onclick="javascript: app.f_setTaskFinish( insId, nodes[app.zindex].nodeId )">Finish</div>
				<div data-options="disabled: false,iconCls:'icon-pause'" onclick="javascript: app.f_setTaskPause( insId, nodes[app.zindex].nodeId )">Pause</div>
				<div data-options="disabled: false,iconCls:'icon-skip'" onclick="javascript: app.f_setTaskSkip( insId, nodes[app.zindex].nodeId )">Skip</div>
			</div>
		</div>
		<div data-options="disabled: false,iconCls:'icon-search'" onclick="javascript: app.f_viewPara_open_window( app.zindex )">View Setting</div>
		<div data-options="disabled: false,iconCls:'icon-search'" onclick="javascript: app.showTaskLog(app.zindex)">View Log</div>
	</div>
	
	<div id="jobContextMenu" class="easyui-menu" style="width:160px;">
		<div data-options="disabled: true,iconCls:'icon-stop'" onclick="javascript: app.f_pauseTask( insId, nodes[app.zindex].nodeId )">Stop subJob</div>
		<div data-options="disabled: false,iconCls:'icon-back'" onclick="javascript: app.f_setSubJobInit( insId, nodes[app.zindex].nodeId )">Reset subJob</div>
		<div>
			<span>Set Subjob State</span>
			<div style="width:120px;">
				<div data-options="disabled: false,iconCls:'icon-init'" onclick="javascript: app.f_setTaskInit( insId, nodes[app.zindex].nodeId )">Init</div>
				<div data-options="disabled: false,iconCls:'icon-finish'" onclick="javascript: app.f_setTaskFinish( insId, nodes[app.zindex].nodeId )">Finish</div>
				<div data-options="disabled: false,iconCls:'icon-pause'" onclick="javascript: app.f_setTaskPause( insId, nodes[app.zindex].nodeId )">Pause</div>
				<div data-options="disabled: false,iconCls:'icon-skip'" onclick="javascript: app.f_setTaskSkip( insId, nodes[app.zindex].nodeId )">Skip</div>
			</div>
		</div>
		<div data-options="disabled: false,iconCls:'icon-search'" onclick="javascript: app.f_viewJobIns_open_window( insId, nodes[app.zindex] )">View Subjob Setting</div>
		<div data-options="disabled: false,iconCls:'icon-search'" onclick="javascript: app.showTaskLog(app.zindex)">View Log</div>
	</div>
	
	<div id="blankContextMenu" class="easyui-menu" style="width:120px;">
		<div data-options="disabled: false,iconCls:'icon-run'" onclick="javascript: app.f_startJob( insId )">Start Job</div>
		<div data-options="disabled: false,iconCls:'icon-pause'" onclick="javascript: app.f_pauseJob( insId )">Pause Job</div>
		<div data-options="disabled: false,iconCls:'icon-back'" onclick="javascript: app.f_setJobInit( insId )">Reset Job</div>
		<div class="menu-sep"></div>
		<div data-options="disabled: false,iconCls:'icon-unlock'" onclick="javascript: app.f_unlockJob( insId )">Unlock Job</div>
		<div class="menu-sep"></div>
		<div data-options="disabled: false,iconCls:'icon-search'" onclick="javascript: app.f_viewJobIns_open_window( insId, pojo )">View Setting</div>
	</div>
	
	<!-- 查看参数界面 -->
	<div id="modifyPara"></div>
	<!-- 查看实例配置界面 -->
	<div id="viewJobIns"></div>
	<!-- 任务日志对话框 -->
	<div id="LOGdlg1"></div>

	<div id="tt">
        <a href="javascript:void(0)" class="icon-reload" onclick="javascript:$('#LOGdlg1').panel('refresh')"></a>
        <a href="javascript:void(0)" class="icon-lock" onclick="javascript:app.refreshFlag = ( app.refreshFlag == false); "></a>
    </div>
    
<script type="text/javascript">
jQuery(document).ready(function()
{
	px = $('#workspace').offset().left;
	py = $('#workspace').offset().top;
});
$(function(){
	$("#workspace").bind('contextmenu',function(e){
		e.preventDefault();
		$('#blankContextMenu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	});
});
// var t=setTimeout("reload()",5*1000);
/** 每隔5秒刷新一次页面 ,去掉上面的注释才有效**/
function reload(){
	window.location.reload(true);
};
//sendMessage  -- impl comet
var t=setTimeout("sendMessage()",1*1000);
/** 每隔3秒刷新一次状态 **/
function refreshState(){
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobProcessBUSIrefreshState.action",
	   async: false,
	   data: { id:insId, jid: currentJobId },
	   success: function(oResponse){
		   setTimeout("refreshState()", 1*1000);
		   for(var i=0; i<oResponse.length; i++){
		     imges[i].attr("src","../images/job/"+PSTATEICON[oResponse[i].status])
		   }
	   }
	});
};

/** 1启动作业  **/
app.f_startJob = function( job_ins_id ){
	var jdata = { job_ins_id: job_ins_id };
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobProcessBUSIstartJob.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			alert( oResponse.msg );
			if (oResponse.success){
			
			}
	   }
	});
};
/** 2暂停作业  **/
app.f_pauseJob = function( job_ins_id ){
	var jdata = { job_ins_id: job_ins_id };
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobProcessBUSIpauseJob.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			alert( oResponse.msg );
			if (oResponse.success){
			
			}
	   }
	});
};
/** 3初始化作业  **/
app.f_setJobInit = function( job_ins_id, jobId ){
	var jdata = { job_ins_id: job_ins_id, jobId: jobId};
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobInsBUSImodifyJobStatus2init.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			if (oResponse.success){
				window.location.reload(true);
			}else{
				alert( oResponse.msg );
			}
	   }
	});
};
/** 3.1重置子作业  **/
app.f_setSubJobInit = function( job_ins_id, jobId ){
	var jdata = { job_ins_id: job_ins_id, jobId: jobId};
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobInsBUSImodifySubJobStatus2init.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			if (oResponse.success){
				window.location.reload(true);
			}else{
				alert( oResponse.msg );
			}
	   }
	});
};
/** 4解锁作业  **/
app.f_unlockJob = function( job_ins_id ){
	var jdata = { job_ins_id: job_ins_id };
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobInsBUSIunlock.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			alert( oResponse.msg );
			if (oResponse.success){
			
			}
	   }
	});
};

/** 0 停止C端任务  **/
app.f_pauseTask = function( job_ins_id, taskId ){
	var jdata = { job_ins_id: job_ins_id, taskId: taskId };
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobProcessBUSIstopTaskSignal.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			alert( oResponse.msg );
			if (oResponse.success){
			
			}
	   }
	});
};

/** 1任务设置为初始  **/
app.f_setTaskInit = function( job_ins_id, taskId ){
	var jdata = { job_ins_id: job_ins_id, taskId: taskId };
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobProcessBUSImodifyTaskStatus2Init.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			if (oResponse.success){
				window.location.reload(true);
			}else{
				alert( oResponse.msg );
			}
	   }
	});
};
/** 2任务设置为完成 **/
app.f_setTaskFinish = function( job_ins_id, taskId ){
	var jdata = { job_ins_id: job_ins_id, taskId: taskId };
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobProcessBUSImodifyTaskStatus2Finish.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			if (oResponse.success){
				window.location.reload(true);
			}else{
				alert( oResponse.msg );
			}
	   }
	});
};
/** 3任务设置为暂停  **/
app.f_setTaskPause = function( job_ins_id, taskId ){
	var jdata = { job_ins_id: job_ins_id, taskId: taskId };
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobProcessBUSImodifyTaskStatus2Pause.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			if (oResponse.success){
				window.location.reload(true);
			}else{
				alert( oResponse.msg );
			}
	   }
	});
};
/** 4任务设置为跳过  **/
app.f_setTaskSkip = function( job_ins_id, taskId ){
	var jdata = { job_ins_id: job_ins_id, taskId: taskId };
	$.ajax({
	   type: "POST",
	   url: "../busi/migJobProcessBUSImodifyTaskStatus2Skip.action",
	   async: false,
	   data: {
		   jdata: encodeURI( $.toJSON( jdata ) )
	   },
	   success: function(oResponse){
		   oResponse = eval("("+oResponse+")");
			if (oResponse.success){
				window.location.reload(true);
			}else{
				alert( oResponse.msg );
			}
	   }
	});
};
jQuery(document).ready(function(){
// 	refreshState(); 
}); 
</script>
<!-- <div data-options="region:'south'" class="m_copyright"> -->
<!-- 		<div style="float:left;"> -->
<!-- 			<a href="javascript:void(0)" class="icon-back" onclick="javascript: window.history.go(-1)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> -->
<!-- 			<a href="javascript:void(0)" class="icon-forward" onclick="javascript: window.history.go(1)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> -->
<!-- 			<a href="javascript:void(0)" class="icon-reload" onclick="javascript: window.location.reload()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> -->
<!-- 			<a href="javascript:void(0)" class="icon-home" onclick="javascript: window.location.href='../welcome.html'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> -->
<!-- 		</div> -->
<!-- 	Copyright&copy;2015 Asiainfo.All Rights -->
<!-- 		Reserved -->
<!-- 		<div style="float:right;visibility: visible;margin:0;padding:0;"> -->
<!-- 			<img id="imgid" src="../images/vq/bull-1-min.gif" alt="VQ:Migration Group(1)" -->
<!-- 			 style="padding:1px 18px 0px 1px" height="24px" width="24px" align="right" -->
<!-- 			 onclick="javascript:this.src='../images/vq/bull-1-min.gif';window.open('../chat/chat.jsp', 'chat windows', {}, {});"/> -->
<!-- 		</div> -->
<!-- 	</div> -->
</body>
</html>