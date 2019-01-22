//过程状态
var PSTATE = {
	UNEXECUTE : 0,
	EXECUTING : 1,
	FINISH : 2,
	PAUSE : 3,
	SKIP : 4,
	PAUSING : 5,
	ERROR : -1
};
//任务状态名称
var PSTATENAME = {
	UNEXECUTE : "UNEXECUTE",
	EXECUTING : "EXECUTING",
	FINISH : "FINISH",
	PAUSE : "PAUSED",
	SKIP : "SKIP",
	PAUSING : "PAUSING",
	ERROR : "ERROR"
};
//作业状态名称
var PSTATEVALUE = {
	'0' : "UNEXECUTE",
	'1' : "EXECUTING",
	'2' : "FINISH",
	'3' : "PAUSED",
	'4' : "SKIP",
	'5' : "PAUSING",
	'-1' : "ERROR"
};

//任务颜色
var COLOR = {
	UNEXECUTE : "gray",
	EXECUTING : "green",
	FINISH : "blue",
	PAUSE : "yellow",
	SKIP : "black",
	ERROR : "red"
};
//任务颜色
var COLORVALUE = {
	'0' : "gray",
	'1' : "green",
	'2' : "blue",
	'3' : "yellow",
	'4' : "black",
	'-1' : "red"
};

/**
任务状态
0未执行     --灰色  gray   #808080
1正在执行   --绿色  green  #00FF00 
2执行完成   --蓝色  blue   #0000FF 
3暂停执行   --黄色  yellow #FFFF00
4跳过 black #000000  黑
-1执行出错  --红色  red    #FF0000

aqua(cyan)     #00FFFF 
**/
//作业状态
var JSTATE = {
	UNEXECUTE : 0,
	EXECUTING : 1,
	FINISH : 2,
	PAUSE : 3,
	PAUSING_MANUAL : 5,
	ERROR : -1
};
//作业状态名称
var INS_STATENAME = {
	UNEXECUTE : "UNEXECUTE",
	EXECUTING : "EXECUTING",
	FINISH : "FINISH",
	PAUSE : "PAUSED",
	PAUSING_MANUAL : "PAUSING",
	ERROR : "ERROR"
};
//作业状态名称
var JSTATENAME = {
	'0' : "UNEXECUTE",
	'1' : "EXECUTING",
	'2' : "FINISH",
	'3' : "PAUSED",
	'5' : "PAUSING",
	'-1' : "ERROR"
};

//任务属性
var RECT={
	w: 60,
	h: 40,
	r: 4,
	x: 32/2,//矩形中心相对于矩形坐标的偏移量x，文本相对任务坐标的x偏移量
	y: 32/2,//矩形中心相对于矩形坐标的偏移量y
	yy: 40-2//文本相对任务坐标的y偏移量
};
/** 作业编译状态 **/
var COMPILESTATE = {
		COMPILED:'1',
		UNCOMPILED: '0'
};
/** 任务图标（流程图） **/
var controlIcon2 = {
		"con001" : "split2",
		"con002" : "loaddata2",
		"con003" : "outdf2",
		"con004" : "outdb2",
		"con005" : "sql2",
		"con006" : "shell2",
		"con007" : "auditv2",
		"con008" : "control2",
		"con009" : "auditc2",
		"con010" : "auditf2"
};
/** 任务样式（表格） **/
var controlIcon3 = {
		"con001" : "icon-shell",
		"con002" : "icon-loaddata",
		"con003" : "icon-outdf",
		"con004" : "icon-outdb",
		"con005" : "icon-sql",
		"con006" : "icon-shell",
		"con007" : "icon-auditv",
		"con008" : "icon-control",
		"con009" : "icon-auditc",
		"con010" : "icon-auditf"
};
//过程状态
var PSTATEICON = {
		'0' : "",
		'1' : "executing.gif",
		'2' : "true.png",
		'3' : "pause.png",
		'4' : "skip.png",
		'5' : "pausing.png",
		'-1' : "false.png"
};
var control = {
	"SPLIT_FILE" : "con001",
	"LOAD_DATA" : "con002",
	"OUT_DATA_FILE" : "con003",
	"OUT_DATA_BASE" : "con004",
	"EXCUTE_SQL" : "con005",
	"EXCUTE_BIN" : "con006",
	"LEGALITY_AUDIT" : "con007",
	"CONTROL" : "con008",
	"CONSISTENCY_AUDIT" : "con009",
	"QUALITY_CONTROL" : "con010"
};
var NodeType = {
	"NOLEAF" : 0,
	"LEAF" : 1
};