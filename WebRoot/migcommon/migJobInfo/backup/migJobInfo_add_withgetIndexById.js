/** 打开-任务设置
 *  */
app.f_modifyTask_open_window = function ( zindex ) {
	app.zindex = zindex;//nodes、shapes array index
	
	var node = nodes[app.zindex];
	
	//添加子作业的支持
	if(node.job != undefined){
		var jobId = node.job.jobId;
		window.open( getContextPath() + "/curd/migJobInfoCURDinitModify.action?id="+ jobId, "修改作业-"+jobId );
		return false;
	}
	
	var comId = node.task.comId;
	var jdata = {controlId: node.task.controlId };
	
	$("#modifyTask").dialog({
		title : "任务",
		href :  getContextPath()+'/curd/migTaskInfoCURDinitModify.action?id='
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
			|| $('#modifyTask_form #utaskRemark').val() == ""
			|| app.taskparaloaded==false
			){
		return;
	}
	var formData = $('#modifyTask_form').form('getData', true);
	var rows = $('#taskparam_grid').datagrid( 'getRows');
	var comforms = $('#comparaTable input');

	var cominfos = [];
	var comId = nodes[zindex].task.comId;

	for(var i=0, j=comforms.length; i<j; i++ ){
		var temp = {comId : comId , paraId : comforms[i].name , paraValue : comforms[i].value };
		cominfos.push(temp);
	}
	
	var jdata  = {
		taskId : formData.taskId,
		taskName : formData.taskName,
		taskRemark : formData.taskRemark
	};
	$.ajax({
        type: "POST",
        url: getContextPath()+'/curd/migTaskInfoCURDmodifyWithTaskParamandComInfo.action',
        data: { 
        	jdata :  encodeURI($.toJSON(jdata)), 
        	rows: encodeURI($.toJSON( rows )),
        	cominfos: encodeURI($.toJSON( cominfos )),
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
                $.messager.alert('警告', "系统异常，请联系管理员检查异常原因！详情:<"+data.msg+">", 'warning');
            }
        	$('#modifyTask').dialog('close');
         }
    });
};
/** 打开作业设置界面   **/
app.f_modifyJob_open_window = function( jobId ){

	$("#modifyJob").dialog({
		title : "作业",
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
/** 更新作业信息，仅更新前台数据  **/
app.modifyJob = function(){
	if( ! $('#modifyJob_form').form("validate")
			|| $('#modifyJob_form #ujobRemark').val() == ""
			|| app.jobparaloaded==false
			){
		return;
	}
	
	var formData = $('#modifyJob_form').form('getData', true);
	var rows = $('#jobparam_grid').datagrid( 'getRows');
	
	update.init();
	update.jdata.condition.id = formData.jobId;
	
	update.jdata.data.name = formData.jobName;
	update.jdata.data.remark = formData.jobRemark;
		
	$.ajax({
        type: "POST",
        url: getContextPath()+'/curd/migJobInfoCURDmodifyWithJobParam.action',
        data: { jdata: encodeURI($.toJSON( update.jdata )), rows: encodeURI($.toJSON( rows ))  },
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
                $.messager.alert('警告', "系统异常，请联系管理员检查异常原因！详情:<"+data.msg+">", 'warning');
            }
        	$('#modifyJob').dialog('close');
         }
    });
};
/** 删除任务 **/
app.removeNode = function( ){
	alert(app.zindex);
	console.log("app.zindex:"+app.zindex);
	console.log( "before remove nodes:" + $.toJSON(nodes) );
	
	var zindex = app.zindex;

	removefromConn( nodes[zindex].id );
	
	//删除存在的关系
	/* 因为箭头都是通过后置来画的，所以前台更新被删除任务的前置就行了，更新其前置任务的后置属性  --前台未用此属性，暂时不处理*/
	var prepos = nodes[zindex].prepos;
	if( prepos != "" && prepos != undefined){
		var arr = prepos.split("|");
		for( var i=0,j=arr.length; i<j; i++ ){
			unlink2( arr[i], nodes[zindex].id );
		}
	}
	
	/*更新其后置任务的前置属性 --前台未用此属性，暂时不处理  */
	var postpos = nodes[zindex].postpos;
	if( postpos != "" && postpos != undefined){
		var arr = postpos.split("|");
		for( var i=0,j=arr.length; i<j; i++ ){

			unlink2(  nodes[zindex].id, arr[i] );
		}
	}
	shapes[zindex].remove();
	txts[zindex].remove();
	
	//删除nodes\shapes\txts中相应的元素
	nodes.splice( zindex, 1);
	shapes.splice( zindex, 1);
	txts.splice( zindex, 1);
	
	console.log( "after remove nodes:" + $.toJSON(nodes) );
};
function unlink2( fromTask, toTask ){
	var start = r.getById( fromTask );
	var end = r.getById( toTask );
	//update postpos and prepos
	//start node data postpos
	var postpos= nodes[start.zindex].postpos;
	nodes[start.zindex].postpos = delrel( postpos, end.id );
	
	//end node data prepos
	var prepos = nodes[end.zindex].prepos;
	nodes[end.zindex].prepos = delrel( prepos, start.id );
	
	rel  = rel.replace( start.id + "-" + end.id + "|", "" );
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
	newCtrlNum ++;
	
	var shape = r.rect(x, y, RECT.w, RECT.h, RECT.r );
	shape.zindex = shapes.length;
	
	//将copy中的paras复制到paras变量，*非地址复制
	var paras= new Array(0);
	var temps = $.toJSON(copy.paras);
	paras = $.evalJSON(temps);
	
    //将任务保存到数据库
    var jdata = {
    		taskName : copy.task.taskName + newCtrlNum,
    		taskRemark : copy.task.taskRemark,
    		taskNode : copy.task.taskNode,
    		coords : x+","+y,
    		jobId : jobId,
    		controlId: copy.paras[0].controlId,
    		paras: paras
    };
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
//		   	alert("copy.task.taskRemark:"+copy.task.taskRemark);
		    var task = {
		    		id : {
		    				jobId : jobId, 
		    				nodeId : oResponse.id 
		    		},
					nodeStatus : "0", prepos : "", postpos:"",
					coords : x+","+y, zb : { x: x, y : y },
		  			task : {
		  				nodeId : oResponse.id, 
		  				taskName : jdata.taskName,
		  				taskNode : jdata.taskNode, 
		  				comId: oResponse.comId,
		  				taskAuthor : "", 
		  				taskRemark : copy.task.taskRemark,
		  				taskUpdate : ""
		  			},
		  			paras: paras
			};
	   		
			nodes.push( task );
			shapes.push( shape );
			txts.push( r.text( x + RECT.x, y + RECT.yy, jdata.taskName ) );
		   
			var color = Raphael.getColor();
			fmt.fmtShape( shape, color, oResponse.id );
	   }
	});
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