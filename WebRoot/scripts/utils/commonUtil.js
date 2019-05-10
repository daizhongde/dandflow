/** 获取上下文 @author daizd  */
function getContextPath(){
    var contextPath = document.location.pathname;
    var index =contextPath.substr(1).indexOf("/");
        contextPath = contextPath.substr(0,index+1);
        delete index;
        return contextPath;
};

/**
* 取的url参数
* @author daizd  */
function getUrlParam(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
};

/** 同步发送请求，url以斜扛开头  @author daizd  */
function ajaxReqRespText(url, param){
	return $.ajax({
		type: "POST",
		url: getContextPath() + url,
		async: false,
		data:{
			jdata: param
		}
	}).responseText; 
}
/** 同步发送请求，url以斜扛开头  @author daizd  */
function ajaxReqRespText2(url, param){
	return $.ajax({
		type: "POST",
		url: getContextPath() + url,
		async: false,
		data: param
	}).responseText; 
};

/** 发送请求，弹错,alert error, url以斜扛开头  @author daizd  */
function ajaxReqTC(url, async, param){
	$.ajax({
		type: "POST",
		url: getContextPath()+url,
		async: async,
		data:{
			jdata: param
		},
		success: function(oResponse){
//	 		oResponse = eval("(" + oResponse + ")");
//			alert(oResponse.msg);
			if(oResponse.success == false){
				alert(oResponse.msg);
			}
	   }
	}); 
}
/**
 * 展开.	@author daizd  */
function expandAll(objTree) {
	var node = objTree.tree('getSelected');
	if (node) {
		objTree.tree('expandAll', node.target);
	} else {
		objTree.tree('expandAll');
	}
};

/**
 * 全选. @author daizd  */
function selectAll(objTree) {
	var node = objTree.tree('getRoots');
	for ( var i = 0; i < node.length; i++) {
		var childrenNode =  objTree.tree('getChildren',node[i].target);
		for ( var j = 0; j < childrenNode.length; j++) {
			objTree.tree("check",childrenNode[j].target);
		}
    }
};

/**
 * 获取选中的节点. @author daizd  */
function getNode(objTree) {
	var node = objTree.tree('getChecked');
	var cnodes = '';
	var pnodes = '';
	var pnode = null; //保存上一步所选父节点
	for ( var i = 0; i < node.length; i++) {
		if (objTree.tree('isLeaf', node[i].target)) {
			cnodes += node[i].id + ',';
			pnode = objTree.tree('getParent', node[i].target); //获取当前节点的父节点
			while (pnode!=null) {//添加全部父节点
				pnodes += pnode.id + ',';
				pnode = objTree.tree('getParent', pnode.target); 
			}
		}
	}
	cnodes = cnodes.substring(0, cnodes.length - 1);
	pnodes = pnodes.substring(0, pnodes.length - 1);
	return cnodes + "," + pnodes;
};

/** 对象复制  
 *  	JSON.stringify(obj);	$.toJSON( text )			json2text
 *  	JSON.parse(text);		$.evalJSON( text )
 *  							$.secureEvalJSON( text )
 *                              eval ("(" + text + ")");	text2json obj or array
 *  @author daizd  
 * */
function copyObj( obj ){
	var tempStr = JSON.stringify( obj );
	return eval ("(" + tempStr + ")");
};
/** @author daizd  */
function nvl( value ){
	if(value == null || value=="null" )
		return "";
	else
		return value;
};
/**
 * 
 * typeof(undefined) == 'undefined'  
 typeof(null) == 'object'  
 typeof("") == 'string'  
 typeof(0) == 'number'  
 typeof(false) == 'boolean' 
 
 * @param value
 * @returns {Boolean}
 * @author daizd  
 */
function isNvl( value ){
	if( (value == null || value=="null" || value == "") && typeof(value) != 'number' )
		return true;
	else
		return false;
};
/**  @author daizd  */
function isNotNvl( value ){
	return !isNvl(value);
};
/**
 * @param value 0.3434
 * @returns 34.34%
 * @author daizd  
 */
function fmtRate(value){
	if(isNvl(value)){
		return "";
	}
	var s = String(value);
	var pidx = s.indexOf(".");
	var scale = pidx==-1?4:s.substring(pidx+1).length;
	return new String(new Number(value*100).toFixed( scale>4? scale-2 : 2 ))+"%";
};
/**
 * @param value 0.3434
 * @returns 34.34%
 * @author daizd  
 */
function fmtRateWithScale(value, scale2){
	if(isNvl(value)){
		return "";
	}
	var s = String(value);
	var pidx = s.indexOf(".");
	var scale = pidx==-1?4:s.substring(pidx+1).length;
	if(isNvl(scale)){
		return new String(new Number(value*100).toFixed( scale>4? scale-2 : 2 ))+"%";
	}else{
		return new String(new Number(value*100).toFixed( scale>scale2+2? scale-2 : scale2 ))+"%";
	}
};
/** format level 
 * @author daizd  
 */
function fmtEmail(email){
	return "<a href='mailto:"+email+"'>"+email+"</a>";
};
/** get radio's value <p>Have used by Get export/import file extension name  
 *  var objValue = $("input[name=runFlag]:checked").val();
 * @author daizd  
 */
function getRadioValue(radioName)
{
 	var robj1=document.getElementsByName( radioName );
	var fileType ="";
	// ==javascript读取radio的值==
	for(var i=0; i<robj1.length; i++)
	{
		if(robj1[i].checked)
		{
			fileType = robj1[i].value;
// 			console.log("radio value:"+robj1[i].value);
			break;
		}
	}
	return fileType;
};
/** 可用于计算对象的成员个数 
 * @author daizd  
 */
function count(o){
        var t = typeof o;
        if(t == 'string'){
                return o.length;
        }else if(t == 'object'){
                var n = 0;
                for(var i in o){
                        n++;
                }
                return n;
        }
        return false;
};