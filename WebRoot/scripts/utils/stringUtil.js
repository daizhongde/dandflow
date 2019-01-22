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
