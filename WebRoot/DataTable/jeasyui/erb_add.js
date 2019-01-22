//var app = {};
//var noquery = VIRTUE.noquery;
//var query = VIRTUE.query;
//
//var add = VIRTUE.add;
//var update = VIRTUE.update;
//var read = VIRTUE.read;
//var del = VIRTUE.del;
//
//var OPE = VIRTUE.operator;


///** 格式化金额，保留两位小数 <br>eg: 1.2 --> 1.20<br>@author daizhongde */
//function fmtAMT(value){
//	return new Number(value).toFixed(2);
//};


/** 对账单明细请求参数 */
app.jsonData_detail="";
/** 增款明细请求参数 */
app.jsonData_add="";

function save_erb() {
	if(!$("#add_form").form('validate')){
		return;
	}
	var data = $('#order_grid').datagrid('getData');
//	alert( $.toJSON(data.rows) );
	var formData = $('#add_form').form('getData', true);
    var jsonData  = JSON.stringify(formData);
    $.ajax({
        type: "POST",
        url: getContextPath()+'/bms_rm/erb_saveErb.action',
        data: {data : jsonData,  detail : $.toJSON(data.rows) },
        dataType: "json",
        success: function(data){
        	data = eval("(" + data + ")");
        	if( data.success ){
        		alert(data.msg);
//        		$.messager.show('提示', data.msg, 'info');
            	$("#erbill_c").dialog('close');
              	$('#grid').datagrid( 'reload', { jdata: encodeURI( $.toJSON( query.jdata ) ) } );
            }else{
                $.messager.alert('警告', "系统异常，请联系管理员检查异常原因！详情:<"+data.msg+">", 'warning');
            }
         }
    });
};
/** validate and assign value **/
function add_form_PackingParameter(){
	query.init();
	var formData = $('#add_form').form('getData', true);
	app.jsonData_detail  = JSON.stringify(formData);
	return true;
};

app.enterKeyDown = function()
{
    if(event.keyCode==13)
    {
       	if(!add_form_PackingParameter())
       	{
       		return;
       	}
    	//查询指定客户本对账周期的订单
        $('#order_grid').datagrid('load', {
        	jdata : encodeURI( $.toJSON( query.jdata )),
        	data : app.jsonData_detail
        });
	}
};


$(function() {
	$.extend($.fn.datagrid.methods, {
		editCell : function(jq, param) {
			return jq.each(function() {
				var opts = $(this).datagrid('options');
				var fields = $(this).datagrid('getColumnFields', true).concat(
						$(this).datagrid('getColumnFields'));
				for ( var i = 0; i < fields.length; i++) {
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field) {
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
				for ( var i = 0; i < fields.length; i++) {
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});
	/** 增款信息表格编辑 */
	var eradd_editIndex = undefined;
	function eradd_endEditing() {
		if (eradd_editIndex == undefined) {
			return true
		}
		if ($('#eradd_grid').datagrid('validateRow', eradd_editIndex)) {
			var ed = $('#eradd_grid').datagrid('getEditor', {
				index : eradd_editIndex,
				field : 'add_type'
			});
			var addtype_name = $(ed.target).combobox('getText');
			$('#eradd_grid').datagrid('getRows')[eradd_editIndex]['addtype_name'] = addtype_name;
			$('#eradd_grid').datagrid('endEdit', eradd_editIndex);
			eradd_editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function eradd_onClickRow(index) {
		if (eradd_editIndex != index) {
			if (eradd_endEditing()) {
				$('#eradd_grid').datagrid('selectRow', index).datagrid('beginEdit', index);
				eradd_editIndex = index;
			} else {
				$('#eradd_grid').datagrid('selectRow', eradd_editIndex);
			}
		}
	}
	
	function eradd_append() {
		if (eradd_endEditing()) {
			$('#eradd_grid').datagrid('appendRow', {});
			eradd_editIndex = $('#eradd_grid').datagrid('getRows').length - 1;
			$('#eradd_grid').datagrid('selectRow', eradd_editIndex).datagrid('beginEdit',
					eradd_editIndex);
		}
	}
	function eradd_removeit() {
		if (eradd_editIndex == undefined) {return}
		$('#eradd_grid').datagrid('cancelEdit', eradd_editIndex).datagrid('deleteRow', eradd_editIndex)
		eradd_editIndex = undefined;
	}
	function eradd_accept() {
		if (eradd_endEditing()) {
			$('#eradd_grid').datagrid('acceptChanges');
		}
	}
	function eradd_reject() {
		$('#eradd_grid').datagrid('rejectChanges');
		eradd_editIndex = undefined;
	}
	function getChanges() {
		var rows = $('#eradd_grid').datagrid('getChanges');
		alert(rows.length + ' rows are changed!');
	}
	
	/** 订单表格编辑 */
	app.order_editIndex = undefined;
	app.order_endEditing = function() {
		if (app.order_editIndex == undefined) {
			return true
		}
		if ($('#order_grid').datagrid('validateRow', app.order_editIndex)) {
			$('#order_grid').datagrid('endEdit', app.order_editIndex);
			app.order_editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	app.order_onClickCell = function(index, field) {
		if (app.order_endEditing()) {
			$('#order_grid').datagrid('selectRow', index).datagrid('editCell', {
				index : index,
				field : field
			});
			app.order_editIndex = index;
		}
	}

	function order_removeit() {
		var row = $('#order_grid').datagrid('getSelected');
		var rowIndex = $('#order_grid').datagrid('getRowIndex', row);
		if (row) {
			$('#order_grid').datagrid('deleteRow', rowIndex)
		}
	}
	
	/** 增款信息 */
	app.eradd_toolbar = [ {
		text : '新增',
		disabled: true,
		iconCls : 'icon-add',
		handler : function() {
			eradd_append();
			return false;
		}
	}, '-', {
		text : '删除',
		disabled: true,
		iconCls : 'icon-remove',
		handler : function() {
			eradd_removeit();
			return false;
		}
	} , '-', {
		text : '保存',
		disabled: true,
		iconCls : 'icon-save',
		handler : function() {
			eradd_accept();
			return false;
		}
	}, '-', {
		text : '撤销',
		disabled: true,
		iconCls : 'icon-undo',
		handler : function() {
			eradd_reject();
			return false;
		}
	}];
	
	/** 订单 */
	app.order_toolbar = [ {
		text : '',
		iconCls : 'icon-add',
		disabled: false,
		handler : function() {
			alert('添加');
		}
	}, '-', {
		text : '',
		iconCls : 'icon-remove',
		disabled: false,
		handler : function() {
			order_removeit();
		}
	} ];

	// 外部对账单增款信息-表格
	$('#eradd_grid').datagrid({
		title : '',// 增款信息
		// autoRowHeight : false,
		toolbar : app.eradd_toolbar,
		width : 938,
		height : 720,
		nowrap : true,
		striped : true,
		fit : true,
		collapsible : true,
		url:'',
		// url: "../../tAuthorityModuleJEasyUIQUERY.do?method=dfind",//struts1
//		url : "simdata/erbadd_query.txt",// struts2
//		url: getContextPath()+"/bms_rm/erb_query.action",
		idField : "eraddId",
//		remoteSort : true,
//		sortName : "eraddId",
//		sortOrder : "asc",
		multiSort : true,
		singleSelect : true,
//		checkOnSelect : true,
//		selectOnCheck: true,
//		onClickCell : eradd_onClickRow,
		frozenColumns : [ [ 
//		                    {
//			field : 'ck',
//			rowspan : 2,
//			checkbox : true
//		}, 
			{
			title : 'Item ID',
			field : 'eraddId',
			hidden:true,
			width : 80
			},{
			title : '增款类型',
			field : 'addType',
			formatter : function(value, row) {
				return row.addtype_name;
			},
			editor : {
				type : 'combobox',
				options : {
//					valueField : 'add_type',
//					textField : 'addtype_name',
//					url : 'simdata/erb_addtype.txt',
					valueField:'code',
					textField:'value',
					url:'../../bms_rm/dic_getDicByType.action?dicType=ADD_TYPE',
					method : 'post',
					required : true
				}
			},
			width : 64
		} ] ],
		columns : [ [ {
			title : '增款金额',
			field : 'addAmt',
			align:'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2,
					required : true
				}
			},
			width : 64,
			formatter: fmtAMT
		}, {
			title : '增款备注',
			field : 'addNote',
			editor : {
				type : 'text',
			},
			width : 90
		} ] ],
		pagination : false,
		rownumbers : false,
//		pageNumber : 1,
//		pageSize : query.pageSize,
		queryParams : {
			jdata : encodeURI($.toJSON(noquery.jdata)),
			data : app.jsonData 
		},
		onBeforeLoad : function(param) {
			// $('#btnSearch').linkbutton('disable');
			// document.getElementById('btnSearch').disabled=true;
		},
		onLoadSuccess : function() {
			// $('#btnSearch').linkbutton('enable');
			// document.getElementById('btnSearch').disabled=false;
		}
	});
	
	// 初始化-订单信息-表格
	$('#order_grid').datagrid({
		title : '',// 订单信息
		// autoRowHeight : false,
		toolbar : app.order_toolbar,
		width : 938,
		height : 720,
		nowrap : true,
		striped : true,
		fit : true,
		collapsible : true,
		// url:getContextPath()+'/order/order_queryOrerList.action',
		// url: "../../tAuthorityModuleJEasyUIQUERY.do?method=dfind",//struts1
		//url : "simdata/order_query.txt",// struts2
		url: getContextPath()+"/bms_rm/erb_queryCustCurArb.action",
		idField : "arbNo",
		remoteSort : true,
		sortName : "arbNo",
		sortOrder : "asc",
		multiSort : true,
		singleSelect : true,
		checkOnSelect : false,
		// selectOnCheck: true,
//		onClickCell : app.order_onClickCell,
		frozenColumns : [ [ {
			field : 'ck',
			rowspan : 1,
			checkbox : true
		}, {
			title : '明细ID',
			field : 'erdetailId',
			rowspan : 2,
			width : 90,
			hidden: true
		}, {
			title : '应收单ID',
			field : 'arbId',
			rowspan : 2,
			width : 90,
			hidden: true
		}, {
			title : '应收单编号',
			field : 'arbNo',
			rowspan : 2,
			width : 90,
			sortable : true
		}, {
			title : '订单ID',
			field : 'orderId',
			rowspan : 2,
			width : 90,
			hidden: true
		}, {
			title : '订单编号',
			field : 'orderNo',
			rowspan : 2,
			width : 90,
			sortable : true
		}, {
			title : '回单号',
			field : 'billNo',
			rowspan : 2,
			width : 90,
			sortable : true
		} ] ],
		columns : [ [ {
			title : '结算单位',
			colspan : 2
		}, {
			title : '结算量',
			colspan : 2
		}, {
			title : '单价',
			colspan : 2
		}, {
			title : '合计费用',
			colspan : 2
		}, {
			title : '扣款信息',
			colspan : 4
		} ], [ {
			title : '对账前',//结算单位
			field : 'settunit',
			align:'right',
			width : 80
		}, {
			title : '对账后',
			field : 'arSettunit',
			align:'right',
			editor : 'text',
			width : 80
		}, {
			title : '对账前',//结算量
			field : 'settamt',
			align:'right',
			width : 80
		}, {
			title : '对账后',
			field : 'arSettamt',
			align:'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 0
				}
			},
			width : 80
		}, {
			title : '对账前',//单价
			field : 'uamt',
			align:'right',
			width : 80,
			formatter: fmtAMT
		}, {
			title : '对账后',
			field : 'arUamt',
			align:'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2
				}
			},
			width : 80,
			formatter: fmtAMT
		}, {
			title : '对账前',
			field : 'totalamt',//合计费用
			align:'right',
			width : 80,
			formatter: fmtAMT
		}, {
			title : '对账后',
			field : 'arTotalamt',
			align:'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2
				}
			},
			width : 80,
			formatter: fmtAMT
		}, 
//		{
//			title : '货损',
//			field : 'de_dam',
//			align:'right',
//			editor : {
//				type : 'numberbox',
//				options : {
//					precision : 2
//				}
//			},
//			width : 80,
//			formatter: fmtAMT
//		}, 
		{
			title : '晚到车',
			field : 'deLcar',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2
				}
			},
			width : 80,
			align:'right',
			formatter: fmtAMT
		}, {
			title : '晚到货',
			field : 'deLgoods',
			align:'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2
				}
			},
			width : 80,
			formatter: fmtAMT
		}, {
			title : '回单考核',
			field : 'deCheckb',
			align:'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2
				}
			},
			width : 80,
			formatter: fmtAMT
		}, {
			title : '其它费用',
			field : 'deOther',
			align:'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2
				}
			},
			width : 80,
			formatter: fmtAMT
		} ] ],
		rownumbers : true,
//		pagination : false,
//		pageNumber : 1,
//		pageSize : query.pageSize,
		queryParams : {
			jdata : encodeURI($.toJSON(noquery.jdata)),
			data : app.jsonData_detail
		},
		onBeforeLoad : function(param) {
			// $('#btnSearch').linkbutton('disable');
			// document.getElementById('btnSearch').disabled=true;
		},
		onLoadSuccess : function() {
			// $('#btnSearch').linkbutton('enable');
			// document.getElementById('btnSearch').disabled=false;
		}
	});
});

jQuery(document).ready(function(){
//	$("#reco_date").datebox('setValue', dateUtil.jsDateToString( new Date() ) );	// set datebox value
//	$("#reco_date").datebox('setValue', '6/1/2012' );
	document.getElementById("reco_date").value=dateUtil.jsDateToString( new Date() );
//	$("#cust_id").keydown(function(){
//		  alert( $("#cust_id").val() );
//		});
});