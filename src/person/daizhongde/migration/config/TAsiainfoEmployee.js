/** note: 
 * 		Don't support back comment */

/* the globel variable */
var TAsiainfoEmployee = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
TAsiainfoEmployee.Field = {
		"sbu_id" : [ 1, 3, 0, "战略单元ID", "sbu_id", "SBU_ID", 0 ],
		"sbu" : [ 12, 100, 0, "战略单元", "sbu", "SBU", 1 ],
		"company_id" : [ 1, 3, 0, "公司ID", "company_id", "COMPANY_ID", 2 ],
		"company" : [ 12, 100, 0, "公司名称", "company", "COMPANY", 3 ],
		"organization_id" : [ 4, 5, 0, "部门ID", "organization_id", "ORGANIZATION_ID", 4 ],
		"org_name" : [ 12, 100, 0, "部门名称", "org_name", "ORG_NAME", 5 ],
		"office" : [ 12, 100, 0, "座机号", "office", "OFFICE", 6 ],
		"pager" : [ 12, 100, 0, "电话黄页", "pager", "PAGER", 7 ],
		"person_id" : [ 4, 8, 0, "员工ID", "person_id", "PERSON_ID", 8 ],
		"employee_number" : [ 12, 10, 0, "工号", "employee_number", "EMPLOYEE_NUMBER",
				9 ],
		"first_name" : [ 12, 30, 0, "", "first_name", "FIRST_NAME", 10 ],
		"last_name" : [ 12, 30, 0, "", "last_name", "LAST_NAME", 11 ],
		"full_name" : [ 12, 60, 0, "全名", "full_name", "FULL_NAME", 12 ],
		"email_address" : [ 12, 100, 0, "电子邮箱", "email_address", "EMAIL_ADDRESS", 13 ],
		"age" : [ 4, 4, 0, "年龄", "age", "AGE", 14 ],
		"assignment_id" : [ 4, 8, 0, "合同ID", "assignment_id", "ASSIGNMENT_ID", 15 ],
		"birth_date" : [ 93, 19, 0, "生日", "birth_date", "BIRTH_DATE", 16 ],
		"class" : [ 12, 200, 0, "", "class", "CLASS", 17 ],
		"working_location" : [ 12, 100, 0, "工作地点", "working_location",
				"WORKING_LOCATION", 18 ],
		"seat_no" : [ 12, 20, 0, "座位号", "seat_no", "SEAT_NO", 19 ],
		"mobile" : [ 12, 16, 0, "手机号", "mobile", "MOBILE", 20 ],
		"nt_account" : [ 12, 100, 0, "NT账号", "nt_account", "NT_ACCOUNT", 21 ],
		"supervisor_id" : [ 4, 8, 0, "上司ID", "supervisor_id", "SUPERVISOR_ID", 22 ],
		"supervisor_name" : [ 12, 60, 0, "上司姓名工号", "supervisor_name", "SUPERVISOR_NAME",
				23 ],
		"highest_degree" : [ 12, 100, 0, "学历", "highest_degree", "HIGHEST_DEGREE", 24 ],
		"hire_date" : [ 93, 19, 0, "雇佣日期", "hire_date", "HIRE_DATE", 25 ]
	};

TAsiainfoEmployee.Export={};
TAsiainfoEmployee.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
TAsiainfoEmployee.Export.export.ColumnMap = {
		"sbu_id" : [ 1, 3, 0, "", 0 ],
		"sbu" : [ 12, 100, 0, "", 1 ],
		"company_id" : [ 1, 3, 0, "", 2 ],
		"company" : [ 12, 100, 0, "", 3 ],
		"organization_id" : [ 4, 5, 0, "", 4 ],
		"org_name" : [ 12, 100, 0, "", 5 ],
		"office" : [ 12, 100, 0, "", 6 ],
		"pager" : [ 12, 100, 0, "", 7 ],
		"person_id" : [ 4, 8, 0, "", 8 ],
		"employee_number" : [ 12, 10, 0, "", 9 ],
		"first_name" : [ 12, 30, 0, "", 10 ],
		"last_name" : [ 12, 30, 0, "", 11 ],
		"full_name" : [ 12, 60, 0, "", 12 ],
		"email_address" : [ 12, 100, 0, "", 13 ],
		"age" : [ 4, 4, 0, "", 14 ],
		"assignment_id" : [ 4, 8, 0, "", 15 ],
		"birth_date" : [ 93, 19, 0, "", 16 ],
		"class" : [ 12, 200, 0, "", 17 ],
		"working_location" : [ 12, 100, 0, "", 18 ],
		"seat_no" : [ 12, 20, 0, "", 19 ],
		"mobile" : [ 12, 16, 0, "", 20 ],
		"nt_account" : [ 12, 100, 0, "", 21 ],
		"supervisor_id" : [ 4, 8, 0, "", 22 ],
		"supervisor_name" : [ 12, 60, 0, "", 23 ],
		"highest_degree" : [ 12, 100, 0, "", 24 ],
		"hire_date" : [ 93, 19, 0, "", 25 ]
	};

//TAsiainfoEmployee.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//TAsiainfoEmployee.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
TAsiainfoEmployee.Export.export.DefaultColumns = ["sbu_id","sbu","company_id","company","organization_id","org_name","office","pager","person_id","employee_number","first_name","last_name","full_name","email_address","age","assignment_id","birth_date","class","working_location","seat_no","mobile","nt_account","supervisor_id","supervisor_name","highest_degree","hire_date"];

TAsiainfoEmployee.Import={};
TAsiainfoEmployee.Import.import={};

//array's order is import file field order
TAsiainfoEmployee.Import.import.DefaultColumns=
	[
	"PAY_ID",
	"ACC_ID",
	"ACC_CARD_ID",
	"COMMI_INST_ID",
	"MERCH_ID",
	"PREV_CHARGE_AT",
	"PREV_CHARGE_LVL","PREPAY_AT","AMOUNT1"];

