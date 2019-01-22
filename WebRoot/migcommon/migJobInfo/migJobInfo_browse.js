/** 打开-任务设置
 *  */
app.f_viewTask_open_window = function ( zindex ) {
	app.zindex = zindex;//nodes、shapes array index
	var node = nodes[app.zindex];
	
	//添加子作业的支持
	if(node.job != undefined){
		var jobId = node.job.jobId;
//		window.open( getContextPath() + "/curd/migJobInfoCURDinitModify.action?id="+ jobId, "修改作业-"+jobId );
		window.open( getContextPath() + "/curd/migJobInfoCURDbrowse.action?id="+ jobId, "查看作业-"+jobId );
//		window.location.href = "../../curd/migJobInfoCURDbrowse.action?id=" + row.id;
		return false;
	}
	
	var comId = node.task.comId;
	var jdata = {controlId: node.task.controlId };
	
	$("#viewTask").dialog({
		title : "Job",
		href :  getContextPath()+'/curd/migTaskInfoCURDinitModify.action?id='
//		+nodes[app.zindex].task.taskId+"&jdata="+$.toJSON(jdata)+"&comId="+comId,//commented 20170920 to support tomcat8.0.46 
		/* The valid characters are defined in RFC 7230 and RFC 3986
		 * eg:
		 * http://10.158.130.198:8081/migration/curd/migTaskInfoCURDinitModify.action?id=TA00000693&jdata={%22controlId%22:%22con006%22}&comId=CO00000640&_=1505892695596  
		 * 
		 * */
			+nodes[app.zindex].task.taskId+"&controlId="+node.task.controlId+"&comId="+comId,
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
            text:'Close',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#viewTask').dialog('close');
            }
        }]
	});
	$("#viewTask").dialog('open');
};

/** 打开作业设置界面   **/
app.f_viewJob_open_window = function( jobId, temp_pojo ){
	app.pojo = temp_pojo;
	
	$("#viewJob").dialog({
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
            text:'Close',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#viewJob').dialog('close');
            }
        }]
	});
	$("#viewJob").dialog('open');
};