/** 打开-控件参数实例化界面(查看参数--不能修改，保存按钮灰掉) 

 *  
 *  */
app.f_viewPara_open_window = function ( zindex ) {
	app.zindex = zindex;//nodes、shapes array index
	var node = nodes[app.zindex];
	
	//添加子作业的支持
	if(node.isleaf == 0 ){
		var jobId = node.jobId;
		window.open( getContextPath() + "/busi/migJobProcessBUSImonitor1.action?id="+node.jobInsId+"&jid="+node.nodeId, "监控作业-"+jobId );
		return false;
	}
	
	var comId = node.comId;
	var jdata = { controlId: node.controlId };
	
	$("#modifyPara").dialog({
		title : node.nodeName,
		href :  getContextPath()+'/curd/migJobProcessCURDinitBrowse.action?id='
//				+node.processId+"&jdata="+$.toJSON(jdata)+"&comId="+comId+"&controlId="+node.controlId,
				+node.processId+"&controlId="+node.controlId+"&comId="+comId,
		iconCls : 'icon-search',
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
            	$('#modifyPara').dialog('close');
            }
        }]
	});
	$("#modifyPara").dialog('open');
};
/** 打开作业配置查看界面   **/
app.f_viewJobIns_open_window = function( jobInsId, temp_pojo ){
	app.pojo = temp_pojo;
	
	if(isNotNvl( temp_pojo.nodeName )){
		app.currentJobId = temp_pojo.nodeId;
	}else{
		app.currentJobId = temp_pojo.jobId
	}
	
//	console.log("app.currentJobId:"+app.currentJobId);
	
	$("#viewJobIns").dialog({
		title : "Job Instance",
		href :  getContextPath()+'/migcommon/migJobIns/migJobIns_browse.html',
		iconCls : 'icon-search',
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
            	$('#viewJobIns').dialog('close');
            }
        }]
	});
	$("#viewJobIns").dialog('open');
};
app.refreshFlag = false;

function refreshLog(){
	if(app.refreshFlag){
		$('#LOGdlg1').panel('refresh');
	}
};

app.showTaskLog = function (zindex) {
	app.zindex = zindex;
	
	var node = nodes[app.zindex];
	var isleaf =  node.isleaf;
	//子作业没有日志
	if( isleaf == 0 ){
		alert("At present, the console does not provide a log view function to the job node. You can view leaf node's log in subjob");
//		alert("目前控制台对作业节点不提供日志查看功能，您可以进入子作业查看叶子节点的日志！");
		return false;
	}
	
	var jobId =  node.jobId;
	var jobInsId =  node.jobInsId;
	var nodeId =  node.nodeId;

	app.closed = false;
	
	var x = shapes[app.zindex].attr("x")+2*RECT.x;
	var y = shapes[app.zindex].attr("y")+2*RECT.y;
	$('#LOGdlg1').dialog({
	    title: 'My Dialog',
	    tools:'#tt',
	    width: 400,
	    height: 200,
	    left : x,
	    top : y,
	    closed: false,
	    cache: false,
	    resizable: true,
		minimizable: true,
		maximizable: true,
	    href:  "../busi/migJobLogBUSIfetchLog.action",
		queryParams :{
			jobId : jobId,
			jobInsId : jobInsId,
			taskId : nodeId
		},
	    modal: false,
	    onLoad : function(){
	    	if( !app.closed ){
//	    		setTimeout("$('#LOGdlg1').panel('refresh')", 6*1000);
	    		setTimeout("refreshLog();", 6*1000);
	    	}
	    },
	    onClose : function(){
	    	app.closed = true;
	    },
	    buttons:[{
			text:'Close',
			handler:function(){
				$('#LOGdlg1').dialog('close');
			}
		}]
	});
};
