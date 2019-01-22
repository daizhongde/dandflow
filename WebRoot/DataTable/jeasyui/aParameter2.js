//postdata
//act: add del edit read   //create update read delete
var add = {
	"act" : "add",
	"data" : {
		"nmid" : "1",
		"cmname" : "模块名称",
		"nmlevel" : ""
	}
};

var edit = {
	"act" : "edit",
	"data" : {
		"nmid" : "1",
		"cmname" : "模块名称",
		"nmlevel" : ""
	},
	"condition" : {
		"nmid" : "1",
		"cmname" : "模块名称",
		"nmlevel" : ""
	}
};

var read = {
	"act" : "read",
	"condition" : {
		"nmid" : "1",
		"cmname" : "模块名称",
		"nmlevel" : ""
	}
};

var del = {
	"act" : "del",
	"condition" : {
		"nmid" : "1",
		"cmname" : "模块名称",
		"nmlevel" : [ 1, 2, 3 ],
		"cmtarget" : [ 'R', 'B' ]
	},
	"operator" : {//operator 运算符 optional
		"nmid" : 1,  //=1  //key must the same with comdition
		"cmname" : 11,//like '%模块名称%'
		"nmlevel" : 13, //in (1, 2, 3)
		"cmtarget" : 13//in ('R', 'B')
	}
};
// {" - 选择 -
// ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
// {" - 选择 -
// ","等于","不等于","在以下日期之后","在以下日期之后或与之相同","在以下日期之前","在以下日期之前或与之相同","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
// {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于"};
var query = {
	"act" : "query",
	"operator" : {
		"nmid" : 1,
		"cmname" : 11,
		"nmlevel" : 13,
		"cmtarget" : 13
	},
	"condition" : {
		"nmid" : "1",
		"cmname" : "模块名称",
		"nmlevel" : [ 1, 2, 3 ],
		"cmtarget" : [ 'R', 'B' ]
	}
};
// alert(query.condition.nmlevel);

// response data
// 添加成功，修改成功，删除成功
var result = {
	"success" : true,
	"msg" : "添加成功！"
};
// (0) 2 |(1) 12 |(2) 2 |(3) 12 |(4) 2 |(5) 2 |(6) 12 |(7) 12 |(8) 12 |(9) 12
// |(10) 12 |(11) 12 |(12) 93 |(13) 12 |(14) 2 |(15) 93 |(16) 12 |(17) 2 |
// TAuthorityModule_ColumnTypes={N_MID|C_MNAME|N_MLEVEL|C_MLEAF|N_MORDER|N_MPARENT|C_MTARGET|C_MICONCLS|C_MEXPANDED|C_MCHECKED|C_MPATH|C_MNOTE|C_MCTIME|C_MCIP|N_MCUSER|C_MMTIME|C_MMIP|N_MMUSER|}
var TAuthorityModule_ColumnTypes = {
	NMid : 2,
	CMname : 12,
	NMlevel : 2,
	CMleaf : 12,
	NMorder : 2,
	NMparent : 2,
	CMtarget : 12,
	CMiconcls : 12,
	CMexpanded : 12,
	CMchecked : 12,
	CMpath : 12,
	CMnote : 12,
	CMctime : 93,
	CMcip : 12,
	NMcuser : 2,
	CMmtime : 93,
	CMmip : 12,
	NMmuser : 2
}