/** 打开-任务设置
 *  */
app.f_modifyTask_open_window = function ( zindex ) {
	app.zindex = zindex;//nodes、shapes array index
	
	var node = nodes[app.zindex];
	
	//添加子作业的支持
	if( node.isLeaf == 0 ){//是子作业
		var jobId = node.id;
		$('#pselectJob').dialog({
			title : "选择作业",
			content:"<div style='padding:15px 0 0px 15px'><table cellpadding='2'><tr><td>作业:</td><td><input id=subjob_id name='subjob_id' value='"+jobId+"' width='150px' type='text' value=''></td></tr></table></div>",
			iconCls : 'icon-search',
			modal : true,
			closed : true,
			resizable: true,
			minimizable: true,
			maximizable: true,
			cache: false,
			width : 300,
			height : 150,
			buttons: [{
	            text:'确定',
	            iconCls:'icon-ok',
	            handler:function(){
	            	var g = $('#subjob_id').combogrid('grid');	// get datagrid object
	            	var r = g.datagrid('getSelected');	// get the selected row
// 	            	console.log("row:" + $.toJSON(r) );
	            	if(r != null ){
	            		nodes[app.zindex].id = r.id;
	            		nodes[app.zindex].dnodeName = r.name;
	            		nodes[app.zindex].dnodeRemark = r.remark;
	            		txts[app.zindex].attr("text", r.name );
	            	}
	        		$('#pselectJob').dialog('close');
	            	return false;//for IE6 support
	            }
	        },{
	            text:'取消',
	            iconCls:'icon-cancel',
	            handler:function(){
	            	$('#pselectJob').dialog('close');
	            }
	        }]
		});
		createJobCombogrid();
		$('#pselectJob').dialog('open');
		return false;
	}
	
	var comId = node.comId;
	var jdata = {controlId: node.controlId };
	$("#modifyTask").dialog({
		title : "任务",
		href :  getContextPath()+'/migcommon/migTaskInfo/migTaskInfo_add_modify.html',
		iconCls : 'icon-edit',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 840,
		height : 500,
		buttons: [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){
            	app.modifyTask( zindex );
            	return false;//for IE6 support
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#modifyTask').dialog('close');
            }
        }]
	});
	$("#modifyTask").dialog('open');
};

app.modifyTask = function( zindex ){

	if( ! $('#modifyTask_form').form("validate")
			|| app.taskparaloaded==false
			)
	{
		return;
	}
	if( $('#modifyTask_form #utaskRemark').val() == "" )
	{
		alert("任务备注不能为空");
		return;
	}
	//任务基本信息
	var formData = $('#modifyTask_form').form('getData', true);
	nodes[app.zindex].dnodeName = formData.dnodeName;
	nodes[app.zindex].dnodeRemark = formData.dnodeRemark;
	txts[app.zindex].attr("text", formData.dnodeName );
	
	//任务参数
	var rows = $('#taskparam_grid').datagrid( 'getRows');
	nodes[app.zindex].paras = rows;
//	console.log( $.toJSON(rows) );
	
	//控件参数
	var comforms = $('#comparaTable input');
	var cominfos = [];
	for(var i=0, j=comforms.length; i<j; i++ ){
//		console.log("comforms[i].name:"+  comforms[i].name );
		var temp = {comId : "" , paraId : comforms[i].name , paraValue : comforms[i].value };
		cominfos.push(temp);
	}
	nodes[app.zindex].comInfos = cominfos;
		
	$('#modifyTask').dialog('close');
};

/** 打开作业设置界面   **/
app.f_modifyJob_open_window = function( ){

	$("#modifyJob").dialog({
		title : "作业",
		href :  getContextPath()+'/migcommon/migJobInfo/migJobInfo_add_modify.html',
		iconCls : 'icon-edit',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 840,
		height : 500,
		buttons: [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){
            	app.modifyJob( );
            	return false;//for IE6 support
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#modifyJob').dialog('close');
            }
        }]
	});
	$("#modifyJob").dialog('open');
};
/** 更新作业信息，仅更新前台数据  **/
app.modifyJob = function(){
	if( ! $('#modifyJob_form').form("validate")
			|| app.jobparaloaded==false
			)
	{
		return;
	}
	if(  $('#modifyJob_form #ujobRemark').val() == "" )
	{
		alert("作业备注不能为空");
		return;
	}
	var formData = $('#modifyJob_form').form('getData', true);
	var rows = $('#jobparam_grid').datagrid( 'getRows');
	
	$(".tabs-selected .tabs-inner .tabs-title").text( formData.dnodeName );

	job.dnodeName = formData.dnodeName;
	job.dnodeRemark = formData.dnodeRemark;
	job.paras = rows;

	$('#modifyJob').dialog('close');
	
//	$.ajax({
//        type: "POST",
//        url: getContextPath()+'/curd/migJobInfoCURDaddJobinAll.action',
//        data: {
//        	job: encodeURI($.toJSON( job )),
//        	nodes: encodeURI($.toJSON( nodes )) 
//        },
//        dataType: "json",
//        async: false,
//        success: function(data){
//        	if( data.success )
//        	{
//        		$(".tabs-selected .tabs-inner .tabs-title").text( formData.jobName );
//
//        		pojo.jobName = formData.jobName;
//        		pojo.jobRemark = formData.jobRemark;
//            }
//        	else
//        	{
//                $.messager.alert('警告', "系统异常，请联系管理员检查异常原因！详情:<"+data.msg+">", 'warning');
//            }
//        	$('#modifyJob').dialog('close');
//         }
//    });

};
/** 删除任务 **/
app.removeNode = function( ){
//	console.log("app.zindex:"+app.zindex);
//	console.log( "before remove nodes:" + $.toJSON(nodes) );
	
	var zindex = app.zindex;

	removefromConn( nodes[zindex].id );
	
	//删除存在的关系
	/* 因为箭头都是通过后置来画的，所以前台更新被删除任务的前置就行了，更新其前置任务的后置属性  --前台未用此属性，暂时不处理*/
	var prepos = nodes[zindex].prepos;
	if( prepos != "" && prepos != undefined){
		var arr = new String(prepos).split("|");
		for( var i=0,j=arr.length; i<j; i++ ){
//			console.log("prepos app.TaskId2ShapeId."+arr[i]+":"+app.TaskId2ShapeId["'"+arr[i]+"'"]);
//			console.log("nodes[zindex].id:"+nodes[zindex].id);
			unlink2( app.TaskId2ShapeId["'"+arr[i]+"'"], 
					app.TaskId2ShapeId["'"+nodes[zindex].id+"'"] );
		}
	}
	
	/*更新其后置任务的前置属性 --前台未用此属性，暂时不处理  */
	var postpos = nodes[zindex].postpos;
	
	if( postpos != "" && postpos != undefined){
		var arr = new String(postpos).split("|");
		for( var i=0,j=arr.length; i<j; i++ ){
//			console.log("postpos  app.TaskId2ShapeId."+arr[i]+":"+app.TaskId2ShapeId["'"+arr[i]+"'"]);
			unlink2(  app.TaskId2ShapeId["'"+nodes[zindex].id+"'"], 
					app.TaskId2ShapeId["'"+arr[i]+"'"] );
		}
	}
	shapes[zindex].remove();
	txts[zindex].remove();
	
	//删除nodes\shapes\txts中相应的元素
	nodes.splice( zindex, 1);
	shapes.splice( zindex, 1);
	txts.splice( zindex, 1);
	
	updateIndex( nodes, zindex );
	updateIndex( shapes, zindex );
	updateIndex( txts, zindex );
	
//	console.log( "after remove nodes:" + $.toJSON(nodes) );
};
function unlink2( fromTask, toTask ){
	var start = r.getById( fromTask );
	var end = r.getById( toTask );
	//update postpos and prepos
	//start node data postpos
	var postpos= nodes[start.zindex].postpos;
	nodes[start.zindex].postpos = delrel( postpos, end.nodeId );
	
	//end node data prepos
	var prepos = nodes[end.zindex].prepos;
	nodes[end.zindex].prepos = delrel( prepos, start.nodeId );
	
	rel  = rel.replace( start.nodeId + "-" + end.nodeId + "|", "" );
};
/** 复制   **/
app.f_copy = function( ){
	copy = nodes[app.zindex];

	var itemEl = $('#blankpaste')[0];  // the menu item element
	$('#blankContextMenu').menu('enableItem', itemEl ); 
//	console.log(itemEl);
//	alert( $.toJSON( copy.paras ) );
};
/** 粘贴   **/
app.f_paste = function(){
//	alert("rx:"+rx+",ry:"+ry);
	createNode3(rx, ry);
};
/**  paste use  **/
function createNode3(x, y ){
	if( copy.isLeaf == 0 ){//是子作业
		alert("目前不提供子作业的粘贴功能！");
		return;
	}
	
	newNodeNum ++;
	var shape = r.rect(x, y, RECT.w, RECT.h, RECT.r );
	shape.zindex = shapes.length;
	
	var task = copyObj( copy );
	task.id = getNewTaskID();
	   		
	nodes.push( task );
	shapes.push( shape );
	txts.push( r.text( x + RECT.x, y + RECT.yy, task.dnodeName+"-副本" ) );
   
	var color = Raphael.getColor();
	fmt.fmtShape( shape, color, task.id );
};

/** 删除所有后置为nodeId的连接，并且删除所有前置为nodeId的连接  **/
function removefromConn( nodeId ){
//	console.log("removefromConn:"+nodeId);
	for( var i=0; i<connections.length; i++ ){
		if( connections[i].end == nodeId || connections[i].start == nodeId ){
			connections[i].arrPath.remove();
			connections.splice( i, 1 );
			i--;
		}
	}
};

/** 一次性保存作业   **/
app.saveJobInAll = function(){
	var jobName=prompt("请输入作业名称", job.dnodeName );
	if ( jobName != null && jobName != "")
    {
		job.dnodeName = jobName;
		var jdata = {
			rows: nodes, 
			node: job
		};
		$.ajax({
		   type: "POST",
		   url: getContextPath()+"/curd/migJobInfoCURDsaveJobInAll.action",
		   async: false,
		   data: {
			   jdata: encodeURI( $.toJSON( jdata ) )
		   },
			success: function(oResponse){
//				oResponse = eval("("+oResponse+")");
//				alert( oResponse.msg );
				if (oResponse.success){//oResponse.id
					window.location.href = getContextPath()+"/curd/migJobInfoCURDinitModify.action?id="+100;
				}
		   }
		});
    }
};