/** note: 
 * 		Don't support back comment */

/* the globel variable */
var TCopoteEmployee = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
TCopoteEmployee.Field = {
		"id" : [ 4, 8, 0, "", "id", "ID", 0 ],
		"uin" : [ 4, 10, 0, "用户id", "uin", "UIN", 1 ],
		"pid" : [ 4, 10, 0, "", "pid", "PID", 2 ],
		"name" : [ 12, 60, 0, "登陆名", "name", "NAME", 3 ],
		"alias" : [ 12, 60, 0, "", "alias", "ALIAS", 4 ],
		"sex" : [ 1, 1, 0, "性别", "sex", "SEX", 5 ],
		"pos" : [ 12, 30, 0, "", "pos", "POS", 6 ],
		"tel" : [ 12, 20, 0, "座机", "tel", "TEL", 7 ],
		"birth" : [ 12, 30, 0, "生日", "birth", "BIRTH", 8 ],
		"slave_alias" : [ 12, 30, 0, "", "slave_alias", "SLAVE_ALIAS", 9 ],
		"department" : [ 12, 60, 0, "部门", "department", "DEPARTMENT", 10 ],
		"mobile" : [ 12, 20, 0, "手机号", "mobile", "MOBILE", 11 ],
		"employee_no" : [ 1, 4, 0, "员工编号", "employee_no", "EMPLOYEE_NO", 12 ],
		"employee_cardno" : [ 1, 4, 0, "员工工牌号", "employee_cardno",
				"EMPLOYEE_CARDNO", 13 ],
		"employee_idcard" : [ 12, 20, 0, "身份证号码", "employee_idcard",
				"EMPLOYEE_IDCARD", 14 ],
		"logname" : [ 12, 30, 0, "登陆名", "logname", "LOGNAME", 15 ]
	};

TCopoteEmployee.Export={};
TCopoteEmployee.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
TCopoteEmployee.Export.export.ColumnMap =  {
		"id" : [ 4, 8, 0, "", 0 ],
		"uin" : [ 4, 10, 0, "用户id", 1 ],
		"pid" : [ 4, 10, 0, "", 2 ],
		"name" : [ 12, 60, 0, "登陆名", 3 ],
		"alias" : [ 12, 60, 0, "", 4 ],
		"sex" : [ 1, 1, 0, "性别", 5 ],
		"pos" : [ 12, 30, 0, "", 6 ],
		"tel" : [ 12, 20, 0, "座机", 7 ],
		"birth" : [ 12, 30, 0, "生日", 8 ],
		"slave_alias" : [ 12, 30, 0, "", 9 ],
		"department" : [ 12, 60, 0, "部门", 10 ],
		"mobile" : [ 12, 20, 0, "手机号", 11 ],
		"employee_no" : [ 1, 4, 0, "员工编号", 12 ],
		"employee_cardno" : [ 1, 4, 0, "员工工牌号", 13 ],
		"employee_idcard" : [ 12, 20, 0, "身份证号码", 14 ],
		"logname" : [ 12, 30, 0, "登陆名", 15 ]
	};


//TCopoteEmployee.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//TCopoteEmployee.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
TCopoteEmployee.Export.export.DefaultColumns = ["sbu_id","sbu","company_id","company","organization_id","org_name","office","pager","person_id","employee_number","first_name","last_name","full_name","email_address","age","assignment_id","birth_date","class","working_location","seat_no","mobile","nt_account","supervisor_id","supervisor_name","highest_degree","hire_date"];

TCopoteEmployee.Import={};
TCopoteEmployee.Import.import={};

//array's order is import file field order
TCopoteEmployee.Import.import.DefaultColumns=
	[
	"PAY_ID",
	"ACC_ID",
	"ACC_CARD_ID",
	"COMMI_INST_ID",
	"MERCH_ID",
	"PREV_CHARGE_AT",
	"PREV_CHARGE_LVL","PREPAY_AT","AMOUNT1"];

