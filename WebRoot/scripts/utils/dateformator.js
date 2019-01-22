//java.sql.Timestamp->String
//2011-07-02 22:19:06.015 -> 2011年07月02日 22:45
timestampToString1=function(s){
	s = new String(s);
	if(s != "" && s != "null"){
		s = str.split(/[- :]/, 5);
		return s[0]+'年'+s[1]+'月'+s[2]+'日 '+s[3]+':'+s[4];
	}else{
		return "";
	}
};

//java.sql.Timestamp->String
//2011-07-02 22:19:06.015  -> 2011-07-02 23:20:53
timestampToString2=function(s){
	s = new String(s);
	if(s != "" && s != "null"){
		s = new String(s);
		s = s.split('.', 1);
		return s[0];
	}else{
		return "";
	}
};
//2012-11-19T10:07:56 -> 2011年07月02日 22:45
timestamp2ToString1=function(s){
	s = new String(s);
	if(s != "" && s != "null"){
		s = new String(s);
		s = s.split(/[-T:]/, 5);
		return s[0]+'年'+s[1]+'月'+s[2]+'日 '+s[3]+':'+s[4];
	}else{
		return "";
	}
};
//js Date->String
//Sat Jul 2 23:22:28 UTC+0800 2011->String
//Sat Jul 2 23:22:28 UTC+0800 2011->2011-07-02 23:22:09 
dateToString1=function(date){
	//var date = new Date();
	var s = new String(date);
	if(s != "" && s != "null"){
		var Y=date.getFullYear();
		var M=date.getMonth()+1;
		var D=date.getDate();
		var h=date.getHours();
		var m=date.getMinutes();
		var s=date.getSeconds();
		//return Y+'-'+M+'-'+D+' '+h+':'+m+':'+s;
		return Y+'-'+getTwoChar(M)+'-'+getTwoChar(D)+' '+getTwoChar(h)+':'+getTwoChar(m)+':'+getTwoChar(s);
	}else{
		return "";
	}
};
//js Date->String
//Sat Jul 2 23:22:28 UTC+0800 2011->String
//Sat Jul 2 23:22:28 UTC+0800 2011->2011-07-02
dateToString2=function(date){
	//var date = new Date();
	var s = new String(date);
	if(s != "" && s != "null"){
		var Y=date.getFullYear();
		var M=date.getMonth()+1;
		var D=date.getDate();
		//return Y+'-'+M+'-'+D+' '+h+':'+m+':'+s;
		return Y+'-'+getTwoChar(M)+'-'+getTwoChar(D);
	}else{
		return "";
	}
};
//js Date->String
//Sat Jul 2 23:22:28 UTC+0800 2011->String
//Sat Jul 2 23:22:28 UTC+0800 2011->2011-07-02
dateToString3=function(date){
	//var date = new Date();
	var s = new String(date);
	if(s != "" && s != "null"){
		var Y=date.getFullYear();
		var M=date.getMonth()+1;
		var D=date.getDate();
		//return Y+'-'+M+'-'+D+' '+h+':'+m+':'+s;
		return getTwoChar(M)+'/'+getTwoChar(D) + '/' + Y;
	}else{
		return "";
	}
};
//日期格式转换 20121105->2012-11-05
formatS2date=function(s){
	s = new String(s);
	if(s != "" && s != "null"){
		var Y=s.substr(0,4);
		var M=s.substr(4,2);
		var D=s.substr(6,2);
		return Y+'-'+M+'-'+D;
	}else{
		return "";
	}
};
//日期格式转换 20121105->2012年11月05日
formatS2date2=function(s){
	s = new String(s);
	if(s != "" && s != "null"){
		var Y=s.substr(0,4);
		var M=s.substr(4,2);
		var D=s.substr(6,2);
		return Y+'年'+M+'月'+D+'日';
	}else{
		return "";
	}
};
getTwoChar=function(str){
	str = new String(str);
	if(str.length==1){
		str="0"+str;
	}
	return str;
};
parse22=function(x){
	return (x<10?('0'+x):x)
};
getWeek=function(day){
	var week = new Array();
	week[0] = "星期日";
	week[1] = "星期一";
	week[2] = "星期二";
	week[3] = "星期三";
	week[4] = "星期四";
	week[5] = "星期五";
	week[6] = "星期六";
	return week[day];
};

/**
 * format date  
 * 2012-11-19T10:07:56 to 2012-11-19 10:07
 */
function getCMctime(s){
	s = new String(s);
	if(s == "null"){
		return "";
	}
	if(s.length < 6){
		return s;
	}
	s = s.substring(0,10) + ' ' + s.substring(11,16);
	return s;
};

test = function(){
	var arr = new Array(6);
	arr.push('end');
};
/** 获取今天的最早时间 
 * @return Date 
 * Sat Jul 2 23:22:28 UTC+0800 2011->2011-07-02 23:22:09  **/
function getDateTime1(){
	//js Date->String
	var date = new Date();
//	var Y = date.getFullYear();
//	var M = date.getMonth()+1;
//	var D = date.getDate();
//	var h = date.getHours();
//	var m=date.getMinutes();
//	var s=date.getSeconds();
	
	date.setHours(0, 0, 0, 0);
	return date;
};
/** 获取今天的最迟时间
 * @return Date   */
function getDateTime2(){
	//js Date->String
	var date = new Date();
	date.setDate( date.getDate() + 1 );
	date.setHours(0, 0, 0, 0);
	return date;
};
/** 获取今天的最早时间 
 * @return String  
 * Sat Jul 2 23:22:28 UTC+0800 2011->2011-07-02 23:22:09  */
function getDateTimeS1(){
	//js Date->String
	var today = new Date();
	return dateToString3(today)+" 00:00:00";
};
/** 获取今天的最迟时间 
 * @return String  */
function getDateTimeS2(){
	//js Date->String
	var today = new Date();
	today.setDate( today.getDate() + 1 );
	return dateToString3(today)+" 00:00:00";
};

function formatString2Date(s){
//	var reg=/[\u4e00-\u9fa5]/  //利用正则表达式分隔
	var reg=/[-/ :]/
	var ss = (s.split(reg));
	var Y = parseInt(ss[0],10);
	var M = parseInt(ss[1],10);
	var D = parseInt(ss[2],10);
	var h = parseInt(ss[3],10);
	var m = parseInt(ss[4],10);
	var s = parseInt(ss[5],10);
	if (!isNaN(Y) && !isNaN(M) && !isNaN(D) && !isNaN(h) && !isNaN(m) && !isNaN(s)){
//			var date = new Date();
//			date.setFullYear(Y, M-1, D);
//			date.setHours(h, m, s)
//			return date;
		return new Date(Y,M-1,D,h,m,s);
	} else {
		return new Date();
	}
};
/** format date */
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
};
/** parser date */
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
};

/** format datetime */
function fmtDT(date){
	var Y = date.getFullYear();
	var M = date.getMonth()+1;
	var D = date.getDate();
	
	var h=date.getHours();
	var m=date.getMinutes();
	var s=date.getSeconds();
	return Y+'-'+ parse22(M) +'-'+parse22(D)+" "+parse22(h)+':'+parse22(m)+':'+ parse22(s);
};
/** parse datetime */
function psrDT(s){
	if (!s) return new Date();
	var ss = (s.split(/[-T :]/, 6));
	var Y = parseInt(ss[0],10);
	var M = parseInt(ss[1],10);
	var D = parseInt(ss[2],10);
	var h = parseInt(ss[3],10);
	var m = parseInt(ss[4],10);
	var s = parseInt(ss[5],10);
	
	if (!isNaN(Y) && !isNaN(M) && !isNaN(D)
			&&!isNaN(h) && !isNaN(m) && !isNaN(s)){
		return new Date(Y,M-1,D,h,m,s);
	} else {
		return new Date();
	}
};
/** validate date, limit later than now  */
function vlrDTbtNow(date){
    var now = new Date();
	var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    console.log(d1);
    return d1<=date;
};