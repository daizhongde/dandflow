/** 打开-任务设置
 *  */
app.f_modifyTask_open_window = function ( zindex ) {
	app.zindex = zindex;//nodes、shapes array index
	var node = nodes[app.zindex];
	
	//添加子作业的支持
	if(node.job != undefined){
		var jobId = node.job.jobId;
		window.open( getContextPath() + "/curd/migJobInfoCURDinitModify.action?id="+ jobId, "修改作业-"+jobId );
//		window.open( "../../curd/migJobInfoCURDinitModify.action?id="+ jobId, "修改作业-"+jobId );
		return false;
	}
	
	var comId = node.task.comId;
	var jdata = {controlId: node.task.controlId };
	
	$("#modifyTask").dialog({
		title : "Task",
		href :  getContextPath()+'/curd/migTaskInfoCURDinitModify.action?id='
//			+node.task.taskId+"&jdata="+$.toJSON(jdata)+"&comId="+comId,//commented 20170920 to support tomcat8.0.46 
		/* The valid characters are defined in RFC 7230 and RFC 3986
		 * eg:
		 * http://10.158.130.198:8081/migration/curd/migTaskInfoCURDinitModify.action?id=TA00000693&jdata={%22controlId%22:%22con006%22}&comId=CO00000640&_=1505892695596  
		 * 
		 * */
			+node.task.taskId+"&controlId="+node.task.controlId+"&comId="+comId,
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
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	app.modifyTask( zindex );
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
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
		alert("Control Parameter is Required!");
		return;
	}
//	if( $('#modifyTask_form #utaskRemark').val() == "" )
//	{
//		alert("Task remark is Required!");
//		return;
//	}
	var formData = $('#modifyTask_form').form('getData', true);
//	console.log( $.toJSON(formData) );
//	console.log( $('#modifyTask_form textarea')[0].val() );
	var rows = $('#taskparam_grid').datagrid( 'getRows');
	var comforms = $('#comparaTable input,#comparaTable textarea');

	var cominfos = [];
	var comId = nodes[zindex].task.comId;

	for(var i=0, j=comforms.length; i<j; i++ ){
		if(comforms[i].name !=0 ){
			var temp = {comId : comId , paraId : comforms[i].name , paraValue : comforms[i].value };
			cominfos.push(temp);
		}
	}
	
	var jdata  = {
		taskId : formData.taskId,
		taskName : formData.taskName,
		taskRemark : formData.taskRemark
	};
	console.log($.toJSON( rows ));
	$.ajax({
        type: "POST",
        url: getContextPath()+'/curd/migTaskInfoCURDmodifyWithTaskParamandComInfo.action',
        data: { 
        	jdata :  encodeURI($.toJSON(jdata)), 
        	rows: $.toJSON( rows ),
        	cominfos: $.toJSON( cominfos ),
        	comId: comId
        },
        dataType: "json",
        async: false,
        success: function(data){
//        	data = eval("(" + data + ")");
        	if( data.success ){
        		nodes[app.zindex].task.taskName = jdata.taskName;
        		nodes[app.zindex].task.taskRemark = jdata.taskRemark;
        		nodes[zindex].task.comId = data.comId;
        		txts[app.zindex].attr("text", jdata.taskName );
            }else{
            	alert(data.msg);
//                $.messager.alert('Warn', "System error,Please contract admin check error cause！Detail:<"+data.msg+">", 'warning');
            }
        	$('#modifyTask').dialog('close');
         }
    });
};
/** 打开作业设置界面   **/
app.f_modifyJob_open_window = function( jobId, temp_pojo ){
	app.pojo = temp_pojo;

	$("#modifyJob").dialog({
		title : "Job",
		href :  getContextPath()+'/migcommon/migJobInfo/migJobInfo_modify.html',
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
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	app.modifyJob( jobId );
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#modifyJob').dialog('close');
            }
        }]
	});
	$("#modifyJob").dialog('open');
};

app.modifyJob = function( jobId ){
	if( ! $('#modifyJob_form').form("validate")
			|| app.jobparaloaded==false
			)
	{
		return;
	}
	if(  $('#modifyJob_form #ujobRemark').val() == "" )
	{
		alert("Job remark is required!");
		return;
	}
	var formData = $('#modifyJob_form').form('getData', true);
	var rows = $('#ujobparam_grid').datagrid( 'getRows');
	
	update.init();
	update.jdata.condition.id = formData.jobId;
	
	update.jdata.data.name = formData.jobName;
	update.jdata.data.remark = formData.jobRemark;
		
	$.ajax({
        type: "POST",
        url: getContextPath()+'/curd/migJobInfoCURDmodifyWithJobParam.action',
        data: { jdata: $.toJSON( update.jdata ), rows: $.toJSON( rows ) },
        dataType: "json",
        async: false,
        success: function(data){
//        	data = eval("(" + data + ")");
        	if( data.success )
        	{
        		$(".tabs-selected .tabs-inner .tabs-title").text( formData.jobName );

        		pojo.jobName = formData.jobName;
        		pojo.jobRemark = formData.jobRemark;
            }
        	else
        	{
        		alert(data.msg);
//                $.messager.alert('Warn', "System error,Please contract admin check error cause！Detail:<"+data.msg+">", 'warning');
            }
        	$('#modifyJob').dialog('close');
         }
    });
};

app.removeTask = function( ){
	var zindex = app.zindex;
	var jdata = {
		comId : nodes[zindex].task.comId,
		taskId: nodes[zindex].id.nodeId, 
		jobId : jobId
	};
	$.ajax({
        type: "POST",
        url: getContextPath()+"/busi/migJobContentBUSIremoveTask.action",
        data: { jdata : $.toJSON(jdata)  },
        dataType: "json",
        async: false,
        success: function(data){
        	if( data.success ){
        		removefromConn( nodes[zindex].id.nodeId );
        		
        		//删除存在的关系
//        		"postpos" : "DC|DD|DE",
//    			"prepos" : "TS00000032",
//				rel 所有任务存在的关系   数据格式： DA-DC|DB-DC|DC-DE|
        		/* 因为箭头都是通过后置来画的，所以前台更新被删除任务的前置就行了，更新其前置任务的后置属性  --前台未用此属性，暂时不处理*/
        		var prepos = nodes[zindex].prepos;
        		if( prepos != "" && prepos != undefined){
        			var arr = prepos.split("|");
        			for( var i=0,j=arr.length; i<j; i++ ){
        				//从关系链中删除
        				rel = rel.replace( arr[i]+"-"+nodes[zindex].id.nodeId+"|", "" );
            		}
        		}
        		
        		/*更新其后置任务的前置属性 --前台未用此属性，暂时不处理  */
        		var postpos = nodes[zindex].postpos;
        		if( postpos != "" && postpos != undefined){
        			var arr = postpos.split("|");
        			for( var i=0,j=arr.length; i<j; i++ ){
        				//从关系链中删除
        				rel = rel.replace( nodes[zindex].id.nodeId + "-" + arr[i] + "|", "" );
            		}
        		}
        		shapes[zindex].remove();
//        		removeShape();
        		txts[zindex].remove();

        		//删除nodes\shapes\txts中相应的元素
//        		nodes.splice( zindex, 1);
//        		shapes.splice( zindex, 1);
//        		txts.splice( zindex, 1);
            }else{
                $.messager.alert('Warn', "System error,Please contract admin check error cause！Detail:<"+data.msg+">", 'warning');
            }
         }
    });
};
app.unlinkJob = function( ){
	var zindex = app.zindex;
	var jdata = {
		isleaf : nodes[zindex].isLeaf,
		jobId: nodes[zindex].id.nodeId, 
		parentJobId : jobId
	};
	$.ajax({
        type: "POST",
        url: getContextPath()+"/busi/migJobContentBUSIunhangJob.action",
        data: { jdata : $.toJSON(jdata)  },
        dataType: "json",
        async: false,
        success: function(data){
        	if( data.success ){
        		removefromConn( nodes[zindex].id.nodeId );
        		
        		//删除存在的关系
//        		"postpos" : "DC|DD|DE",
//    			"prepos" : "TS00000032",
//				rel 所有任务存在的关系   数据格式： DA-DC|DB-DC|DC-DE|
        		/* 因为箭头都是通过后置来画的，所以前台更新被删除任务的前置就行了，更新其前置任务的后置属性  --前台未用此属性，暂时不处理*/
        		var prepos = nodes[zindex].prepos;
        		if( prepos != "" && prepos != undefined){
        			var arr = prepos.split("|");
        			for( var i=0,j=arr.length; i<j; i++ ){
        				//从关系链中删除
        				rel = rel.replace( arr[i]+"-"+nodes[zindex].id.nodeId+"|", "" );
            		}
        		}
        		
        		/*更新其后置任务的前置属性 --前台未用此属性，暂时不处理  */
        		var postpos = nodes[zindex].postpos;
        		if( postpos != "" && postpos != undefined){
        			var arr = postpos.split("|");
        			for( var i=0,j=arr.length; i<j; i++ ){
        				//从关系链中删除
        				rel = rel.replace( nodes[zindex].id.nodeId + "-" + arr[i] + "|", "" );
            		}
        		}
        		shapes[zindex].remove();
        		txts[zindex].remove();
            }else{
                $.messager.alert('Warn', "System error,Please contract admin check error cause！Detail:<"+data.msg+">", 'warning');
            }
         }
    });
};
app.deleteJob = function(){
	var zindex = app.zindex;
	var jdata_D = {act: "del",condition: { id: "",curJobId: jobId },operator : {nmid : 1} };
	jdata_D.condition.id = nodes[zindex].id.nodeId;
	
	$.ajax({
        type: "POST",
        url: getContextPath()+"/busi/migJobInfoBUSIdeleteInJob.action",
        data: { jdata : $.toJSON(jdata_D)  },
        dataType: "json",
        async: false,
        success: function(data){
        	data = eval("(" + data + ")");
        	if( data.success ){
        		removefromConn( nodes[zindex].id.nodeId );
        		
        		var prepos = nodes[zindex].prepos;
        		if( prepos != "" && prepos != undefined){
        			var arr = prepos.split("|");
        			for( var i=0,j=arr.length; i<j; i++ ){
        				//从关系链中删除
        				rel = rel.replace( arr[i]+"-"+nodes[zindex].id.nodeId+"|", "" );
            		}
        		}
        		
        		/*更新其后置任务的前置属性 --前台未用此属性，暂时不处理  */
        		var postpos = nodes[zindex].postpos;
        		if( postpos != "" && postpos != undefined){
        			var arr = postpos.split("|");
        			for( var i=0,j=arr.length; i<j; i++ ){
        				//从关系链中删除
        				rel = rel.replace( nodes[zindex].id.nodeId + "-" + arr[i] + "|", "" );
            		}
        		}
        		shapes[zindex].remove();
        		txts[zindex].remove();
            }else{
                alert(data.msg);
            }
         }
    });
};
/** 复制   **/
app.f_copy = function( ){
	copy = nodes[app.zindex];

	//添加子作业的支持
	if(copy.job != undefined){
		alert("Job no copy function！");
		return false;
	}
	
	var itemEl = $('#blankpaste')[0];  // the menu item element
	$('#blankContextMenu').menu('enableItem', itemEl ); 
//	console.log(itemEl);
//	alert( $.toJSON( copy.paras ) );
};
/** 粘贴   **/
app.f_pasteTask = function(){
//	alert("rx:"+rx+",ry:"+ry);
	createTaskNode2(rx, ry);
};
/**  paste use  **/
function createTaskNode2(x, y ){
	newNodeNum ++;
	
    //将任务保存到数据库
    var jdata = {
    		taskName : copy.task.taskName + newNodeNum,
    		taskRemark : copy.task.taskRemark,
    		coords : x+","+y,
    		jobId : jobId,
    		controlId: copy.task.controlId
    };
    //copy
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
			var shape = r.rect(x, y, RECT.w, RECT.h, RECT.r );
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
		  				"controlId" : jdata.controlId,
		  				"comId" : "",
		  				"taskAuthor" : "", 
		  				"taskRemark" : jdata.taskRemark,
		  				"taskUpdate" : ""
		  			},
		  			job:null
			};
		    
			nodes.push( task );
			shapes.push( shape );
			txts.push( r.text( x + RECT.x, y + RECT.yy, jdata.taskName ) );
		   
			var color = Raphael.getColor();
			fmt.fmtShape( shape, color, oResponse.id );
	   }
	});
};

/** 删除形状,不需要将shape从数组中删除，只需要从界面上移除图形，如果从数组中删除zindex就需要重置  **/
function removeShape( ){
	var zindex = app.zindex;
	
	shapes[zindex].remove();
	for(var i = zindex, j=shapes.length; i<j; i++){
		shapes[i].zindex = shapes[i].zindex-1;
	}
};
/** 删除所有后置为nodeId的连接，并且删除所有前置为nodeId的连接  **/
function removefromConn( nodeId ){
	for( var i=0; i<connections.length; i++ ){
		if( connections[i].end == nodeId || connections[i].start == nodeId ){
			connections[i].arrPath.remove();
			connections.splice( i, 1 );
			i--;
		}
	}
};
