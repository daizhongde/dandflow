<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看</title>
</head>
  <body>
   	<script type="text/javascript">
   	var action = "view";//edit,add,view   , mysoftstone zddaia employeeNo98974,author 戴忠戴 ,author daizhongde QQ:413881461
	/*
	 * 
	 * 1、一次性，并且指定了时间 ("13 12 11 1 11 ? 2017")； 2、周期性；3、自定义,并指定了定时表达式
	 *  2再分5种情况 ：
	 *   2.1、按小时; 2.2、按天; 2.3、按周; 2.4、按月（日）; 2.5、按月（星期）;
	 *   "0 0 0/5 * * ?"  每5小时一次触发  "0 0 * /5 * * ?"  --?
	 *   "0 15 8 * * ?"  每天早上8：15一次触发
	 *   "0 10 8 ? * WED"  每周三早上8：15一次触发,或者写成"0 10 8 ? * 4"
	 *   "0 15 10 15 * ?"  每月15号的10：15触发    "4 20 1 L * ?"
	 *   "0 15 10 ? * 6#3"  每月的第三个星期五上午10:15触发 "0 15 10 ? * SUN#3",  "0 15 10 ? * SUNL",  "0 15 10 ? * 1L"
	 */
	var cronExpression = "0 15 10 ? * 6#3";//when action is edit or view this value is useful
	var activeTabIndex = 0;//选定的选项卡，新增界面默认是0:一次性
	var ckRunFlagRadioIndex = 1;//选定的定时方式，新增界面默认是1:按天
	
	//任务状态名称
// 	var MONTH = ['JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEPT','OCT','NOV','DEC'];
// 	var WEEK = ['SUN','MON','TUE','WED','THU','FRI','SAT'];
// 	var MONTH = {"1":"JAN","2":"FEB","3":"MAR","4":"APR","5":"MAY","6":"JUN","7":"JUL","8":"AUG","9":"SEPT","10":"OCT","11":"NOV","12":"DEC"};
// 	var WEEK = {"1":"SUN","2":"MON","3":"TUE","4":"WED","5":"THU","6":"FRI","7":"SAT"};
	var MONTH = {"JAN":"1","FEB":"2","MAR":"3","APR":"4","MAY":"5","JUN":"6","JUL":"7","AUG":"8","SEPT":"9","OCT":"10","NOV":"11","DEC":"12"};
	var WEEK = {"SUN":"1","MON":"2","TUE":"3","WED":"4","THU":"5","FRI":"6","SAT":"7"};
	
    /** 格式化字符	05——>5   */
	function format2int(str){
		return str.replace(/^0/,"");
	};
 
   	/** format char 
	 * @author daizd 
	 */
   	function parse22c(x){
   		return (x<10?('0'+x):x)
   	};
   	/** format datetime -->String
	 * @author daizd 
	 */
	function fmtDT(date){
		var Y = date.getFullYear();
		var M = date.getMonth()+1;
		var D = date.getDate();
		
		var h=date.getHours();
		var m=date.getMinutes();
		var s=date.getSeconds();
		return Y+'-'+ parse22c(M) +'-'+parse22c(D)+" "+parse22c(h)+':'+parse22c(m)+':'+ parse22c(s);
	};
	/** parse datetime, string -->date
	 * @author daizd 
	 */
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
	/** validate date, limit later than now
	 * @author daizd 
	 */
	function vlrDTbtNow(date){
		var now = new Date();
		var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
		return d1<=date;
	};
	
   	/** 生成表达式 
   	       分三种情况：1、一次性，并且指定了时间 ； 2、周期性；3、自定义,并指定了定时表达式
          2再分5种情况 ：
     	     2.1、按小时; 2.2、按天; 2.3、按周; 2.4、按月（日）; 2.5、按月（星期）;
              如果返回-1就不提交表单, 返回空或者非-1的值
	 * @author daizd  
	 */
   	function count(){
		var result = "-1";
		activeTabIndex = Number(activeTabIndex);
   		switch(activeTabIndex)
   		{
   		case 0://#disposable 一次性 "1 1 1 1 1 ? 2017" 
   			if($("#once").prop("checked")){
   				if($('#once_date').datetimebox("isValid")){
   					var datetime = $('#once_date').textbox('getValue');
   	   				var date = psrDT(datetime);
	   	   			var Y = date.getFullYear();
		   	 		var M = date.getMonth()+1;
		   	 		var D = date.getDate();
		   	 		var h=date.getHours();
		   	 		var m=date.getMinutes();
		   	 		var s=date.getSeconds();
		   	 		result = s+" "+m+" "+h+" "+D+" "+M+" ? "+Y;
   				}else{
   					alert("请选择时间！");
   				}
   			}else{
   				result="";
   			};
   		 	break;
   		case 1://#periodicity 周期性
   			result = count2();
   		  	break;
   		case 2://#custom 自定义
   			var form_schedule = $("#form_schedule").val();
//    			秒0-59 , - * /
//    			分0-59 , - * /
//    			小时0-23 , - * /
//    			日1-31 , - * ? / L W C
//    			月1-12 or JAN-DEC , - * /
//    			周几1-7 or SUN-SAT , - * ? / L C #
//    			年(可选字段) empty, 1970-2099 , - * /
   			var patt0=/^([0-9]|[,\-*/])+ ([0-9]|[,\-*/])+ ([0-9]|[,\-*/])+ ([0-9]|[,\-*?/LWC])+ ([0-9]|[,\-*/]|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEPT|OCT|NOV|DEC)+ ([1-7]|[,\-*?/LC#]|SUN|MON|TUE|WED|THU|FRI|SAT)+ ?([0-9]{4}|[,\-*/])*$/i;
//    			if(form_schedule.split(" ").length < 6 ){
			if( !patt0.test(form_schedule) ){
   				alert("请输入正确的表达式！");
   				break;
   			}
   			result = form_schedule;
 			break;
   		default:console.log("page error: no tab is actived!");
   		}
   		return result;
   	};
   	/** 针对选择2：周期性时，生成表达式, 
   	 *    *每     -至   ,和    ?缺省   /到       */
   	function count2(){
   		var result = "-1";
   		var objValue = $("input[name=runFlag]:checked").val();
   		console.log("checked radio:"+objValue);
   		objValue = Number(objValue);
   		switch(objValue)
   		{
   		case 0://按小时：每[1]小时   "0 0 0/5 * * ?"  每5小时一次触发
   			var someHours = $("#someHours").val();
			result = "0 0 0/"+someHours+" * * ?";
     		break;
   		case 1://按天：每天   "0 15 8 * * ?"  每天早上8：15一次触发
   			var time = $('#day_tsp').spinner('getValue');
//    			var arr = (time.split(/[:]/,2))
//    			result = "0 "+format2int(arr[1])+" "+format2int(arr[0])+" * * ?";
			var arr = (time.split(/[:]/,3))
   			result = format2int(arr[2]) + " " + format2int(arr[1])+" "+format2int(arr[0])+" * * ?";
   			break;
   		case 2://按周:每周[一]  "0 10 8 ? * WED"  每周三早上8：15一次触发
   			var week = $("#week").val();
   			var time = $('#week_tsp').textbox('getValue');
   			var arr = (time.split(/[:]/,3))
   			result = format2int(arr[2]) + " " + format2int(arr[1])+" "+format2int(arr[0])+" ? * " + week;
 			break;
   		case 3://按月（日）:每月[1]日   "0 15 10 15 * ?"  每月15号的10：15触发  
   				// "0 15 10 L * ?" 每月的最后一天的10：15触发
   			var D = $("#slt_day").val();
   			var time = $('#month1_tsp').textbox('getValue');
   			var arr = (time.split(/[:]/,3))
   			result = format2int(arr[2]) + " " + format2int(arr[1])+" "+format2int(arr[0])+" "+ D +" * ?";
 			break;
   		case 4://按月（星期）:每月第[一]个星期[一]  "0 15 10 ? * 6#3"  每月的第三个星期五上午10:15触发
   			//"0 15 10 ? * 6L"每月最后一个周五的10：15
			var order = $("#slt_order").val();
			var week = $("#month2_week").val();
			var time = $('#month2_tsp').textbox('getValue');
			var arr = (time.split(/[:]/,3))
   			result = format2int(arr[2]) + " " + format2int(arr[1])+" "+format2int(arr[0])+" ? * "+ week + order;
 			break;
   		default:console.log("page error: no radio is checked!");
   		}
   		return result;
   	};

   	/** 计算activeTabIndex的值, eg: 0,1,2 */
   	function analysisActiveTab( cronExpression ){
   		activeTabIndex = 0
   		if( cronExpression == "" || cronExpression == undefined ){
   			return activeTabIndex;
   		}
   		var arr = cronExpression.split(/[ ]/,7);// timeArr = ["0", "0", "12", "14", "7", "?", "2016-2017"]
   		var Y = arr[6];
		var W = arr[5];
		var M = arr[4];
		var D = arr[3];
		var h = arr[2];
		var m = arr[1];
		var s = arr[0];
		
		var rIndex = analysisRunFlagRadio( cronExpression );
		if( rIndex== -1 ){//一次性、自定义
			//"0 10 18 30 11 ? 2016-2017" false "0 10 18 30 11 ? 2016" true
			var patt0=/^\d{1,2} \d{1,2} \d{1,2} \d{1,2} \d{1,2} \? \d{4}$/;
			if(patt0.test(cronExpression)){// 一次性（指定时间） arr.length == 7 
	   			activeTabIndex = 0;
	   		}else{// 自定义
	   			activeTabIndex = 2;
	   		}
		}else{//周期性
			activeTabIndex = 1;
			ckRunFlagRadioIndex = rIndex;
		}
   		
   		return activeTabIndex;
   	};

   	/** 计算ckRunFlagRadioIndex的值, eg: 0,1,2,3,4 */
   	function analysisRunFlagRadio( cronExpression ){
   		var ret = -1;
   		
		var patt0=/^\d{1,2} \d{1,2} \d{1,2}\/\d{1,2} \* \* \?( \*)?$/;
		var patt1=/^\d{1,2} \d{1,2} \d{1,2} \* \* \?( \*)?$/;
		var patt2=/^\d{1,2} \d{1,2} \d{1,2} \? \* (\d{1}|SUN|MON|TUE|WED|THU|FRI|SAT)( \*)?$/i;
		var patt3=/^\d{1,2} \d{1,2} \d{1,2} (\d{1,2}|L) \* \?( \*)?$/
		var patt4=/^\d{1,2} \d{1,2} \d{1,2} \? \* (\d{1}|SUN|MON|TUE|WED|THU|FRI|SAT)(L|#1|#2|#3|#4)( \*)?$/i;
		
		if( patt0.test(cronExpression) ){
			ret =0;
		}else if( patt1.test(cronExpression) ){
			ret =1;
		}else if( patt2.test(cronExpression) ){
			ret =2;
		}else if( patt3.test(cronExpression) ){
			ret =3;
		}else if( patt4.test(cronExpression) ){
			ret =4;
		}
   		return ret;
   	};
   	
   	/** initial timer form value. according activeTabIndex and  ckRunFlagRadioIndex */
   	function initTimerForm( cronExpression ){
   		console.log("begin initTimerForm...");
   		
   		if( cronExpression == "" || cronExpression == undefined ){
   			document.getElementById('once').checked = false;
   			$("#setOnceDiv").css("display","none");
   			return;
   		}
   		
   		var arr = cronExpression.split(/[ ]/,7);// timeArr = ["0", "0", "12", "14", "7", "?", "2016-2017"]
   		var Y = arr[6];
		var W = arr[5];
		var M = arr[4];
		var D = arr[3];
		var h = arr[2];
		var m = arr[1];
		var s = arr[0];
		
   		activeTabIndex = Number(activeTabIndex);
   		switch(activeTabIndex)
   		{
   		case 0://#disposable 一次性 "1 1 1 1 1 ? 2017" 
   			var time = Y+'-'+ parse22c(M) +'-'+parse22c(D)+" "+parse22c(h)+':'+parse22c(m)+':'+ parse22c(s);
// 			console.log(time);
   			$('#once_date').datetimebox('setValue',  time );
   			document.getElementById('once').checked = true;
   			$("#setOnceDiv").css("display","inline");
   		 	break;
   		case 1://#periodicity 周期性
   			initTimerForm2(cronExpression);
   		  	break;
   		case 2://#custom 自定义
   			$("#form_schedule").val(cronExpression);
 			break;
   		default:console.log("page error: no tab is actived!");
   		}
   	};
   	/** initial timer form value when activeTabIndex equal 1 - per day. according ckRunFlagRadioIndex */
   	function initTimerForm2(cronExpression){
   		var arr = cronExpression.split(/[ ]/,7);// timeArr = ["0", "0", "12", "14", "7", "?", "2016-2017"]
   		var Y = arr[6];
		var W = arr[5];
		var M = arr[4];
		var D = arr[3];
		var h = arr[2];
		var m = arr[1];
		var s = arr[0];
		
   		ckRunFlagRadioIndex = Number(ckRunFlagRadioIndex);
   		switch(ckRunFlagRadioIndex)
   		{
   		case 0://按小时：每[1]小时   "0 0 0/5 * * ?"  每5小时一次触发
   			swithRadioTab(0);
   			var hourArr = h.split("/")	// 0/9
			hour = format2int(hourArr[1]);
   			$("#someHours option[value='"+ hour +"']").attr("selected", true);
     		break;
   		case 1://按天：每天   "0 15 8 * * ?"  每天早上8：15一次触发
   			swithRadioTab(1);
   			$('#day_tsp').timespinner('setValue', parse22c(h)+':'+parse22c(m)+':'+ parse22c(s) );
   			break;
   		case 2://按周:每周[一]  "0 10 8 ? * WED"  每周三早上8：15一次触发 "0 10 8 ? * 4"
   			swithRadioTab(2);
   			if(W.length>2){
   				W = WEEK[W.toUpperCase()];
   				if( W == undefined )alert("js error!");
   			}
   			$("#week option[value='"+W+"']").attr("selected", true);
   			$('#week_tsp').timespinner('setValue', parse22c(h)+':'+parse22c(m)+':'+ parse22c(s) );
 			break;
   		case 3://按月（日）:每月[1]日   "0 15 10 15 * ?"  每月15号的10：15触发    "4 20 1 L * ?"
   			swithRadioTab(3);
   			$("#slt_day option[value='"+D+"']").attr("selected", true);
   			$('#month1_tsp').timespinner('setValue', parse22c(h)+':'+parse22c(m)+':'+ parse22c(s) );
 			break;
   		case 4://按月（星期）:每月第[一]个星期[一]  "0 15 10 ? * 6#3"  每月的第三个星期五上午10:15触发 "5 20 1 ? * 1L"
   			swithRadioTab(4);
   			var order = "";
   			var jindex = W.indexOf("#")
   			if( jindex == -1 ){//1L,SUNL
   				order = W.substr(W.length-1);
   				W = W.substring(0,W.length-1)
   			}else{//6#3 SUM#3
   				order = W.substr( jindex );
   				W = W.substring(0,jindex)
   			}
   			if(W.length>2){
   				W = WEEK[W.toUpperCase()];
   				if( W == undefined )alert("js error!");
   			}
   			$("#slt_order option[value='"+order+"']").attr("selected", true);
   			$("#month2_week option[value='"+W+"']").attr("selected", true);
   			$('#month2_tsp').timespinner('setValue', parse22c(h)+':'+parse22c(m)+':'+ parse22c(s) );
 			break;
   		default:console.log("page error: no radio is checked!");
   		}
   	};
   	
   	function swithRadioTab(value){
   		if(value==0){// 周期性：按小时
			$("#setHourDiv").css("display","inline");
			$("#setDayDiv").css("display","none");
			$("#setWeekDiv").css("display","none");
			$("#setMonth1Div").css("display","none");
			$("#setMonth2Div").css("display","none");
		}else if(value==1){// 周期性：按天
			$("#setHourDiv").css("display","none");
			$("#setDayDiv").css("display","inline");
			$("#setWeekDiv").css("display","none");
			$("#setMonth1Div").css("display","none");
			$("#setMonth2Div").css("display","none");
		}else if(value==2){//按周
			$("#setHourDiv").css("display","none");
			$("#setDayDiv").css("display","none");
			$("#setWeekDiv").css("display","inline");
			$("#setMonth1Div").css("display","none");
			$("#setMonth2Div").css("display","none");
		}else if(value==3){// 按月
			$("#setHourDiv").css("display","none");
			$("#setDayDiv").css("display","none");
			$("#setWeekDiv").css("display","none");
			$("#setMonth1Div").css("display","inline");
			$("#setMonth2Div").css("display","none");
		}else if(value==4){// 按月
			$("#setHourDiv").css("display","none");
			$("#setDayDiv").css("display","none");
			$("#setWeekDiv").css("display","none");
			$("#setMonth1Div").css("display","none");
			$("#setMonth2Div").css("display","inline");
		}
   	};
	</script>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" title="" noheader="true">
			<div style="padding:10px 0 10px 30px">
		    	<table align="center" width="100%">
		    		<tr>
		    			<td>Job Group:</td>
		    			<td>
		    				<input class="easyui-combobox" 
			                        id="jobgroup_R" name="jobgroup" 
			                        data-options="valueField:'code', textField:'value',width:150,
			                        editable:false,
			                        required: true,
			                        value:'${map.jobgroup}',
									data: domains" />
		    			</td>
		    		</tr>
					<tr>
		    			<td>Job Instance:</td>
		    			<td>
							<input id="case_id_R" name='case_id' />
						</td>
		    		</tr>
		    		<tr>
		    			<td>Job Name:</td>
		    			<td><input class="easyui-validatebox" type="text" id="jobname_R" name="jobname" value="${map.jobname}"
		    				data-options="required:true" style="width:300px"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Description:</td>
		    			<td><textarea id="remark_R" name="remark" value="${map.remark}"
		    				style="height:60px;width:500px;font-size: 12px;"></textarea></td>
		    		</tr>
		    	</table>
  	<!-- pills navigater -->
	<ul class="nav nav-pills" id="myTabHead">
		<li id="disposableTab" value="0" class="active"><a href="#disposable" data-toggle="tab">一次性</a></li>
		<li id="periodicityTab" value="1"><a href="#periodicity" data-toggle="tab">周期性</a></li>
		<li id="customTab" value="2"><a href="#custom" data-toggle="tab">自定义</a></li>
	</ul>
	
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade active in" id=disposable>
			<div style="margin-top: 20px;">
				<label class="radio-line" style="margin-top: 5px;"><input type="checkbox" id = "once" name ="timeFlag" value="3" >指定时间</label>
			</div>
			<!-- datetimebox -->
			<div class="form-group" id="setOnceDiv" style="display: none;">
				<div class="col-md-2 hivecloud-date">
					<input class="easyui-datetimebox" id="once_date" labelPosition="top" 
						data-options="formatter:fmtDT,parser:psrDT,required:true" 
						style="width:170px;height:34px;">
		        </div>
			</div>
		</div>
		
		<div class="tab-pane fade" id=periodicity>
			<div style="margin-top: 20px;" class="form-group">
				<!-- 每[1]小时、每天、每周[一]、每月[1]日、每月第[一]个星期[一]-->
				<label class="radio-line" style="margin-top: 5px;"><input type="radio" name ="runFlag" value="0"/>按小时</label>
				<label class="radio-line" style="margin-top: 5px;"><input type="radio" name ="runFlag" value="1" checked="checked"/>按天</label>
				<label class="radio-line" style="margin-top: 5px;"><input type="radio" name ="runFlag" value="2"/>按周</label>
				<label class="radio-line" style="margin-top: 5px;"><input type="radio" name ="runFlag" value="3"/>按月（日）</label>
				<label class="radio-line" style="margin-top: 5px;"><input type="radio" name ="runFlag" value="4"/>按月（星期）</label>
			</div>
			
			<!-- 每[1]小时 -->
			<div class="form-group" id="setHourDiv" style="display: none;">
				<label class="col-md-2 control-label hivecloud-input-label">每</label>
				<div class="col-md-4">
					<select class="form-control" id="someHours">
						<option value="1">1</option><option value="2">2</option><option value="3">3</option>
						<option value="4">4</option><option value="5">5</option><option value="6">6</option>
						<option value="7">7</option><option value="8">8</option><option value="9">9</option>
						<option value="10">10</option><option value="11">11</option><option value="12">12</option>
						<option value="13">13</option><option value="14">14</option><option value="15">15</option>
						<option value="16">16</option><option value="17">17</option><option value="18">18</option>
						<option value="19">19</option><option value="20">20</option><option value="21">21</option>
						<option value="22">22</option><option value="23">23</option>
					</select>
				</div>
				<label class="col-md-6 control-label hivecloud-input-suffixlabel">小时执行一次</label>
			</div>
			
			<!-- 每天 -->
			<div class="form-group" id="setDayDiv" style="display: inline;">
				<label class="col-md-2 control-label hivecloud-input-label">每天</label>
				<!-- timespinner -->
				<div class="col-md-4 hivecloud-date">
					<input id="day_tsp" class="easyui-timespinner" label="" labelPosition="left" value="01:20:02" 
						style="width:100px;height:34px;" data-options="showSeconds:true">
		        </div>
		        <label class="col-md-6 control-label hivecloud-input-suffixlabel">执行</label>
			</div>
			
			<!-- 每周[一] -->
			<div class="form-group" id="setWeekDiv" style="display: none;">
				<label class="col-md-2 control-label hivecloud-input-label">每周</label>
				<div class="col-md-4">
					<select class="form-control" id="week">
						<option value="1">日</option>
						<option value="2">一</option>
						<option value="3">二</option>
						<option value="4">三</option>
						<option value="5">四</option>
						<option value="6">五</option>
						<option value="7">六</option>
					</select>
				</div>
				<!-- timespinner -->
				<div class="col-md-3 hivecloud-date">
					<input id="week_tsp" class="easyui-timespinner" label="" labelPosition="left" value="01:20:03" 
						style="width:100px;height:34px;" data-options="showSeconds:true">
		        </div>
		        <label class="col-md-3 control-label hivecloud-input-suffixlabel">执行</label>
			</div>
			
			<!-- 每月[1]日 -->
			<div class="form-group" id="setMonth1Div" style="display: none;">
				<label class="col-md-2 control-label hivecloud-input-label">每月</label>
				<div class="col-md-4">
					<select class="form-control" id="slt_day">
						<option value="L">最后一天</option>
						<option value="1">1</option><option value="2">2</option><option value="3">3</option>
						<option value="4">4</option><option value="5">5</option><option value="6">6</option>
						<option value="7">7</option><option value="8">8</option><option value="9">9</option>
						<option value="10">10</option><option value="11">11</option><option value="12">12</option>
						<option value="13">13</option><option value="14">14</option><option value="15">15</option>
						<option value="16">16</option><option value="17">17</option><option value="18">18</option>
						<option value="19">19</option><option value="20">20</option><option value="21">21</option>
						<option value="22">22</option><option value="23">23</option><option value="24">24</option>
						<option value="25">25</option><option value="26">26</option><option value="27">27</option>
						<option value="28">28</option>
					</select>
				</div>
				<!-- timespinner -->
				<div class="col-md-3 hivecloud-date">
					<input id="month1_tsp" class="easyui-timespinner" label="" labelPosition="left" value="01:20:04" 
						style="width:100px;height:34px;" data-options="showSeconds:true">
		        </div>
		        <label class="col-md-3 control-label hivecloud-input-suffixlabel">执行</label>
			</div>
			
			<!-- 每月第[一]个星期[一] -->
			<div class="form-group" id="setMonth2Div" style="display: none;">
				<label class="col-md-2 control-label hivecloud-input-label">每月</label>
				<div class="col-md-2">
					<select class="form-control" id="slt_order">
						<option value="L">最后一个</option>
						<option value="#1">第1个</option><option value="#2">第2个</option><option value="#3">第3个</option>
						<option value="#4">第4个</option>
					</select>
				</div>
				<label class="col-md-2 control-label hivecloud-input-label">星期</label>
				<div class="col-md-2">
					<select class="form-control" id="month2_week">
						<option value="1">日</option>
						<option value="2">一</option>
						<option value="3">二</option>
						<option value="4">三</option>
						<option value="5">四</option>
						<option value="6">五</option>
						<option value="7">六</option>
					</select>
				</div>
				<!-- timespinner -->
				<div class="col-md-3 hivecloud-date">
					<input id="month2_tsp" class="easyui-timespinner" label="" labelPosition="left" value="01:20:05" 
						style="width:100px;height:34px;" data-options="showSeconds:true">
		        </div>
		        <label class="control-label hivecloud-input-suffixlabel">执行</label>
			</div>
		</div>
		
		<div class="tab-pane fade" id=custom>
			<div style="margin-top: 20px;">
				<input type="text" id ="form_schedule" name="schedule" class="form-control" placeholder="spring schedule cronExpression" 
				aria-describedby="basic-addon1" maxlength="100" style="width:228px;">
			</div>
		</div>
	</div>
<script type="text/javascript">
read.close = function (){
	$('#win').window('close');
}
$(function(){
	$('#case_id_R').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		width : 300,
		panelWidth : 500,
		panelHeight : 200,
		idField : 'job_ins_id',
		textField : 'job_ins_name',
		value:'${map.case_id}',
		editable: false,
		pagination : true,
		fitColumns : false,
		required : true,
		rownumbers : true,
		mode : 'remote',
		delay : 500,
		sortName : 'job_ins_name',
		sortOrder : 'asc',
		pageSize : 5,
		pageList : [ 5, 10 ],
		queryParams : {
			tableName : "mig_job_ins",
			columns: "job_ins_name"
		},
		columns : [ [ {
			field : 'dryrun_id',
			title : 'dryrun_id',
			width : 60,
			sortable : true
		},{
			field : 'job_ins_name',
			title : 'job_ins_name',
			width : 200,
			sortable : true
		},{
			field : 'type',
			title : 'type',
			width : 50,
			sortable : true
		}, {
			field : 'author',
			title : 'author',
			width : 60,
			sortable : true
		}, {
			field : 'remark',
			title : 'remark',
			width : 220,
			sortable : false
		}, {
			field : 'mtime',
			title : 'Create Time',
			width : 130,
			sortable : true
		} ] ]
	});

});

jQuery(document).ready(function(){
	/* record active tab */
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		activeTabIndex = e.target.parentNode.value;
		console.log("active tab:"+activeTabIndex);
	});

	/* visibility: visible|hidden   display: none|inline */
	$("#once").on("click",function(){
		if($("#once").prop("checked")){
			$("#setOnceDiv").css("display","inline");
		}else {
			$("#setOnceDiv").css("display","none");
		}
	});

	/* like tab  */
	$("input:radio[name='runFlag']").on("change",function(){
		//每[1]小时、每天、每周[一]、每月,old:setTimeDiv<->按天 , old:frequencyDiv<->按小时
		var thisObj = this;
		swithRadioTab( thisObj.value );
	});
	//id: once_date datetimebox
	$('#once_date').datetimebox({
	    required: true,
	    showSeconds: true
	});
	//id: day_tsp  timespinner
	$('#day_tsp').timespinner({
	    required: true,
	    showSeconds: true
	});
	//week_tsp
	$('#week_tsp').timespinner({
	    required: true,
	    showSeconds: true
	});
	//month1_tsp
	$('#month1_tsp').timespinner({
	    required: true,
	    showSeconds: true
	});
	//month2_tsp
	$('#month2_tsp').timespinner({
	    required: true,
	    showSeconds:true 
	});
	/* restrict datetime input , the value must later than now */
	$('#once_date').datetimebox('calendar').calendar({
        validator: vlrDTbtNow
    });
	
	if(action=="edit" || action=="view"){
		//先计算activeTabIndex的值，如果activeTabIndex==2 再计算ckRunFlagRadioIndex的值
		activeTabIndex = analysisActiveTab(cronExpression);
		console.log("activeTabIndex:"+activeTabIndex);
		if(activeTabIndex==1){
			ckRunFlagRadioIndex = analysisRunFlagRadio(cronExpression);
			//选定对应的radio
			var freqRadios = document.getElementsByName('runFlag');
			freqRadios[ckRunFlagRadioIndex].checked=true;
		}
		//激活对应的tab
		$('#myTabHead li:eq('+activeTabIndex+') a').tab('show'); // Select db activeTabIndex (0-indexed)
		
		//init form
		initTimerForm(cronExpression);
	}else{//add
		activeTabIndex=0;//
		/* init once checkbox */
		var oncecb = document.getElementById('once');//checkbox dom
		if(oncecb.checked) {
			oncecb.checked=false;
		};
		/* init runFlag checkbox, default:每天 */
		var freqRadios = document.getElementsByName('runFlag');//checkbox dom
		if(!freqRadios[1].checked) {
			freqRadios[1].checked=true;
		};
	}
}); 
</script>
			    </div>
	    </div>
		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Close</a>
		</div>
	</div>
</body>
</html>