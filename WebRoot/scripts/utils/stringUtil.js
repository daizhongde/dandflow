/** get file name */

function getFileName(obj)
{
    var pos = obj.value.lastIndexOf("/")*1;
    return obj.value.substring(pos+1);
}
/** get file extension name */
function getFileExt(obj)
{
    return obj.value.replace(/.+./,"");
}
/** convert to new url, to forch refresh page
 *  index.html   						--> index.html?timestamp=1523343656880
 *  index.html?id=123  					--> index.html?id=123&timestamp=1523343656881
 *  index.html?timestamp=1523343656880  --> index.html?timestamp=1523343656880
 *   
 *  with ? && with timestamp parameter(only support timestamp in the end)
 *   */
function convert2newurl(url)
{
	if( !new RegExp("[?=]").test(url) ){//no ? && no parameter
    	url = url+"?timestamp=" + new Date().getTime();
    }else if( url.indexOf("?") != -1 && url.indexOf("timestamp=") != -1 ){//with ? && with timestamp parameter(only support timestamp in the end)
    	var index = url.indexOf("timestamp=");
    	url = url.substring(0,index+10);
    	url = url + new Date().getTime();//index.html&timestamp=
    }else{//with ? && (with other parameter) no timestamp parameter
    	url = url+"&timestamp=" + new Date().getTime();
    }
	return url;
};

/** 校验邮件号码 */
function checkEmail(eleID){
    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
    var obj = document.getElementById(eleID); //要验证的对象
    if(obj.value === ""){ //输入不能为空
    	console.log("验邮件号码输入不能为空!");
        return false;
    }else if(!reg.test(obj.value)){ //正则验证不通过，格式不对
    	console.log("验邮件号码验证不通过!");
        return false;
    }else{
        console.log("验邮件号码通过！");
        return true;
    }
};

/** 校验身份证号码 */
function checkIDCard(eleID){
	//身份证正则表达式(15位)
	var reg1 =/^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$/;
	//身份证正则表达式(18位)
	var reg2 =/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$$/;
	
//    var reg = new RegExp("(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)"); //正则表达式
    var obj = document.getElementById(eleID); //要验证的对象
    if(obj.value === ""){ //输入不能为空
    	console.log("身份证号码输入不能为空!");
        return false;
    }else if(!reg1.test(obj.value) && !reg2.test(obj.value)){ //正则验证不通过，格式不对
    	console.log("身份证号码验证不通过!");
        return false;
    }else{
        console.log("身份证号码验证通过！");
        return true;
    }
};

/** 校验中国人的真实姓名 */
function checkChineseName(eleID){
	var obj = document.getElementById(eleID); //要验证的对象
	var regName =/^[\u4e00-\u9fa5]{2,4}$/;  

	if (!regName.test(obj.value)) {
		console.log('真实姓名填写有误');
		return false;
	}
	return true;
};
