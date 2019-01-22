app.f_modifyPara_open_window = function ( zindex ) {
	app.zindex = zindex;//tasks、shapes array index
//	alert( "jobId:"+jobId+",taskId:"+taskId+",paras"+$.toJSON(paras) );
	$("#modifyPara").dialog({
		title : tasks[zindex].task.taskName,
		href :  getContextPath()+'/migcommon/migComIns/migComIns_modify.html',
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
            	app.modifyPara(  zindex );
            	return false;//for IE6 support
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#modifyPara').dialog('close');
            }
        }]
	});
	$("#modifyPara").dialog('open');
};

app.f_modifyJob_open_window = function(  ){

	$("#modifyJob").dialog({
		title : "Job属性",
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

app.modifyPara = function( zindex ){
	if( ! $('#modifyTask_form').form("validate") ){
		return;
	}
	var formData = $('#modifyPara_form').form('getData', true);

	var jdata  = {
			taskId : $("#taskTable #utaskId").val(),
			taskName : $("#taskTable #utaskName").val(),
			comId : tasks[zindex].task.comId,
			paras : formData
	};
	

	for( var i=0,j=tasks[zindex].paras.length; i<j; i++ ){
		var para = tasks[zindex].paras[i];
		para.paraValue = formData[para.id.paraId];
	}
	tasks[app.zindex].task.taskName = jdata.taskName;
	txts[app.zindex].attr("text", jdata.taskName );

	$('#modifyPara').dialog('close');
};
app.modifyJob = function(){
	if( ! $('#modifyJob_form').form("validate")
			|| $('#modifyJob_form #ujobRemark').val() == "" ){
		alert("return");
		return;
	}
	
	var formData = $('#modifyJob_form').form('getData', true);
		
	$(".tabs-selected .tabs-inner .tabs-title").text( formData.jobName );

	job.jobName = formData.jobName;
	job.jobRemark = formData.jobRemark;
	$('#modifyJob').dialog('close');
};
app.removeTask = function( ){
	var zindex = app.zindex;
	var jdata = { taskId: tasks[zindex].task.taskId };

	removefromConn( tasks[zindex].task.taskId );
	
	var prepos = tasks[zindex].prepos;
	if( prepos != "" && prepos != undefined){
		var arr = prepos.split("|");
		for( var i=0,j=arr.length; i<j; i++ ){
			//从关系链中删除
			rel = rel.replace( arr[i]+"-"+tasks[zindex].task.taskId+"|", "" );
		}
	}
	
	/*更新其后置任务的前置属性 --前台未用此属性，暂时不处理  */
	var postpos = tasks[zindex].postpos;
	if( postpos != "" && postpos != undefined){
		var arr = postpos.split("|");
		for( var i=0,j=arr.length; i<j; i++ ){
			//从关系链中删除
			rel = rel.replace( tasks[zindex].task.taskId + "-" + arr[i] + "|", "" );
		}
	}
	shapes[zindex].remove();
	txts[zindex].remove();

        		//删除tasks\shapes\txts中相应的元素
//        		tasks.splice( zindex, 1);
//        		shapes.splice( zindex, 1);
//        		txts.splice( zindex, 1);
};
/** 删除形状,不需要将shape从数组中删除，只需要从界面上移除图形，如果从数组中删除zindex就需要重置  **/
function removeShape( ){
	var zindex = app.zindex;
	
	shapes[zindex].remove();
	for(var i = zindex, j=shapes.length; i<j; i++){
		shapes[i].zindex = shapes[i].zindex-1;
	}
};
/** 删除所有后置为taskId的连接，并且删除所有前置为taskId的连接  **/
function removefromConn( taskId ){
	for( var i=0; i<connections.length; i++ ){
		if( connections[i].end == taskId || connections[i].start == taskId ){
			connections[i].arrPath.remove();
			connections.splice( i, 1 );
			i--;
		}
	}
};