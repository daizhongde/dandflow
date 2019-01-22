/** global obejct **/
var VIRTUE={};

/** act **/
VIRTUE.act = {
	NOQUERY    : "noquery",
	QUERY      : "query",
	//** query combobox data **/
	QUERYCBB   : "querycbb",
	//** export **/
	EXP        : "exp",
	ADD        : "add",
	UPDATE     : "update",
	READ       : "read",
	//** delete **/
	DEL        : "del",
	//** create update delete **/
	CUD        : "cud"
};

/** operator
 *  <p>
 *      0        1=     2!=     3>     4>=         5<     6<=         7like s%  8        9like %s  10        11like %s%  12     13      14          15         16              
 * {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};   between not between in (1,2,3) not in (1,2,3)  
 * {" - 选择 - ","等于","不等于","在以下日期之后","在以下日期之后或与之相同","在以下日期之前","在以下日期之前或与之相同","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
 * {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于"};
 *           1    equal  
 *           2    notEqual 
 *           3    greater then (greaterThen, gt)
 *           4    greater then or equal (greaterThenOrEqual, gtE)
 *           5    less then (lessThen, lt)
 *           6    less then or equal (lessThenOrEqual, ltE)
 *           7    startWith
 *           8    not startWith (notStartWith)
 *           9    endWith
 *          10    not endWith (notEndWith)
 *          11    contain
 *          12    notContain
 *          13    between 
 *          14    notBetween 
 *          15    inList  --join list(['nest_SQL','',...]) or Nested Subqueries B表较小
 *          16    notIn
 *          17    exists  --join Nested Subqueries B表较大
 *          18    notExists
 *  **/
VIRTUE.operator = {
	EQUAL        : 1,
	NOTEQUAL     : 2,
	GT           : 3,
	GE           : 4,
	LT           : 5,
	LE           : 6,
	STARTWITH    : 7,
	NOTSTARTWITH : 8,
	ENDWITH      : 9,
	NOTENDWITH   : 10,
	CONTAIN      : 11,
	NOTCONTAIN   : 12,
	BETWEEN      : 13,
	NOTBETWEEN   : 14,
	IN           : 15,
	NOTIN        : 16,
	EXISTS       : 17,
	NOTEXISTS    : 18
};
/** combobox data **/
VIRTUE.OPECBBDATA =  [
	{ label: 'STARTWITH', value: 7 },
	{ label: 'NOTSTARTWITH', value: 8 },
	{ label: 'ENDWITH', value: 9 },
	{ label: 'NOTENDWITH', value: 10 },
	{ label: 'CONTAIN', value: 11 },
	{ label: 'NOTCONTAIN', value: 12 }
];
				
/** algorithm
 *  <p>
 *       0       1(+) 2(-) 3(*) 4(/) 
 * {" - 选择 - ","加","减","乘","除"}; add, subtract, multiply and divide 
 *           1    plus  
 *           2    minus 
 *           3    multiply
 *           4    divide
 *  **/
VIRTUE.algorithm = {
	PLUS     : 1,
	MINUS    : 2,
	MULTIPLY : 3,
	DIVIDE   : 4
};

/** options
 *  <p>
 *  below options are aviliable for all export file type
 *  <br>IncludeColumnTitles
 *  <br>ContinueOnError
 *  The others are only use by export txt file
 *
 *  **/
VIRTUE.options = {
	IncludeColumnTitles		: true,//or "false"
	ContinueOnError	: false,//or "false"
	RecordDelimiter			: "\r\n",
	FieldDelimiter			: "\t",
	TextQualifier			: null,//or ""
	DateOrder				: "YMD",
	DateDelimiter			: "/",
	ZeroPaddingDate			: false,//or "false"
	TimeDelimiter			: ":",//Whenever time's minute and second is ZeroFilled
	DecimalSymbol			: ".",
	BinaryDataEncoding				: null //or ""
};

/** noquery tell server don't search db **/
VIRTUE.noquery = {};
VIRTUE.noquery.jdata = { act: VIRTUE.act.NOQUERY };

/** query db, datatable search **/
VIRTUE.query = {};

/* jQueryEasyUI, YUI3 */
VIRTUE.query.pageNumber = 1;
VIRTUE.query.pageSize = 20;

/* Dojo, Ext3, Ext4, YUI2 **/
VIRTUE.query.start = 0;
VIRTUE.query.count = VIRTUE.query.pageSize;

VIRTUE.query.jdata = { act: VIRTUE.act.QUERY, condition: {}, operator : {} };
VIRTUE.query.init = function(){
	VIRTUE.query.jdata = { act: VIRTUE.act.QUERY, condition: {}, operator : {} };
};

/** querycbb, query combobox data  **/
VIRTUE.querycbb = {};
VIRTUE.querycbb.jdata = { act: VIRTUE.act.QUERYCBB, condition: {}, operator : {} };
VIRTUE.querycbb.init = function(){
	VIRTUE.querycbb.jdata = { act: VIRTUE.act.QUERYCBB, condition: {}, operator : {} };
};

/** export data  **/
VIRTUE.exp = {};
VIRTUE.exp.jdata = { act: VIRTUE.act.EXP, condition: {}, operator : {} };
VIRTUE.exp.DefaultColumns = [];
VIRTUE.exp.Options = {};
VIRTUE.exp.init = function(){
	VIRTUE.exp.jdata = { act: VIRTUE.act.EXP, condition: {}, operator : {} };
	VIRTUE.exp.DefaultColumns=[];
	VIRTUE.exp.Options = {};
};

/** import data  **/
VIRTUE.imp = {};
VIRTUE.imp.DefaultColumns = [];
VIRTUE.imp.Options = {};
VIRTUE.imp.init = function(){
	VIRTUE.imp.DefaultColumns=[];
	VIRTUE.imp.Options = {};
};

/** CURD data  **/
/* create  **/
VIRTUE.add = {};
VIRTUE.add.jdata = { act: VIRTUE.act.ADD, data : {} };
VIRTUE.add.init = function(){
	VIRTUE.add.jdata = { act: VIRTUE.act.ADD, data : {} };
};

/* update  **/
VIRTUE.update = {};
VIRTUE.update.jdata = { act: VIRTUE.act.UPDATE, data : {}, algorithm:{}, condition: {}, operator : {} };
VIRTUE.update.init = function(){
	VIRTUE.update.jdata = { act: VIRTUE.act.UPDATE, data : {}, algorithm:{}, condition: {}, operator : {} };
};

/* read  **/
VIRTUE.read = {};
VIRTUE.read.jdata = { act: VIRTUE.act.READ, condition: {}, operator : {} };
VIRTUE.read.init = function(){
	VIRTUE.read.jdata = { act: VIRTUE.act.READ, condition: {}, operator : {} };
};

/* delete  **/
VIRTUE.del = {};
VIRTUE.del.jdata = { act: VIRTUE.act.DEL, condition: {}, operator : {} };
VIRTUE.del.init = function(){
	VIRTUE.del.jdata = { act: VIRTUE.act.DEL, condition: {}, operator : {} };
};

/** data batch CUD **/
VIRTUE.cud = {};
//VIRTUE.cud.jdata = {act : VIRTUE.act.CUD, add : [], update : [], del : [] };
//VIRTUE.cud.init = function(){
//	VIRTUE.cud.jdata = { act : VIRTUE.act.CUD, add : [], update : [], del : [] };
//};
VIRTUE.cud.jdata = {act : VIRTUE.act.CUD, data : [] };
VIRTUE.cud.init = function(){
	VIRTUE.cud.jdata = { act : VIRTUE.act.CUD,  data : [] };
};