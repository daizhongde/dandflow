//postdata
//act: add\del\edit\read\query\noquery   //create update read delete
/** 
 * insert into T_AUTHORITY_MODULE (n_mid, c_mname, n_mlevel, c_mleaf, n_morder, n_mparent, c_mtarget, c_miconcls, c_mexpanded, c_mchecked, c_mpath, c_mnote, c_mctime, c_mcip, n_mcuser, c_mmtime, c_mmip, n_mmuser)
   values (1, '湖南社保金融卡省内前置系统', 0, 'false', 1, null, 'R', 'icon-web', 'true', 'false', null, '系统名称', to_date('22-07-2013 11:23:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null);

 * 
 * min
var add = {};
add.jdata = { act: "add", data : {} }; 

 **/
var add = {
	act : "add",
	data : {
		nmid : 1,
		cmname : "模块名称",
		nmlevel : ""
	}
};

/** 
 * update t_authority_module t 
 *    set t.c_mleaf='false', n_mlevel=2 
 *  where n_mid=100 and n_mlevel=2 
 * 
 * min
var update = {};
update.jdata = { act: "update", data : {}, algorithm:{}, condition: {},operator : {}}; 

**/
var update = {
	act : "update",
	data : {//pojo value
		nmid : 1,
		cmname : "模块名称",
		nmorder: 3,
		nmlevel : ""
	},
	algorithm:{
		nmorder: 2
	},
	condition : {
		nmid : 1,
		cmname : "模块名称",
		nmlevel : ""
	},
	operator : {//operator 运算符  optional 
		nmid : 1,  //=1  //key must the same with comdition
		cmname : 11,//like '%模块名称%'
		nmlevel : 13, //in (1, 2, 3)
		cmtarget : 13//in ('R', 'B')
	}
};

/** 
 * select * 
 *   from t_authority_module
 *  where n_mid=1
 * 
 * 
 * min
var read = {};
read.jdata = {act : "read",condition : {nmid : 1}}; */

var read = {
	act : "read",
	condition : {
		nmid : 1,
		cmname : "模块名称",
		nmlevel : ""
	}
};

/** 
 * delete from t_authority_module 
 *  where n_mid = 1000
 * 
 * min
var del = {};
del.jdata = { act: "del",condition: {}, operator : {} };**/
var del = {
	act : "del",
	condition : {
		nmid : 1,
		cmname : "模块名称",
		nmlevel : [ 1, 2, 3 ],
		cmtarget : [ 'R', 'B' ]
	},
	operator : {//operator 运算符 optional
		nmid : 1,  //=1  //key must the same with comdition
		cmname : 11,//like '%模块名称%'
		nmlevel : 13, //in (1, 2, 3)
		cmtarget : 13//in ('R', 'B')
	}
};

//       0      1=   2!=    3>    4>=     5<     6<=   7like s% 8     9like %s  10  11like %s%  12    
// {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};   
// {" - 选择 - ","等于","不等于","在以下日期之后","在以下日期之后或与之相同","在以下日期之前","在以下日期之前或与之相同","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
// {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于"};
var query = {
	act : "query",
	condition : {// search condition
		nmid : 1,
		cmname : "模块名称",
		nmlevel : [ 1, 2, 3 ],
		nmparent : [ 'nest1_SQL'],//exists,exists1,exists2.....(nestSQL现在假定没有参数)
		nmexpanded : [ 'nest2_SQL'],//nest_SQL,nest1_SQL,nest2_SQL...
		cmtarget : [ 'R', 'B' ]
	},
	operator : {//operator 运算符   optional
		nmid : 1,  //=1  //key must the same with comdition
		cmname : 11,//like '%模块名称%'
		nmlevel : 15, //in (1, 2, 3)
		nmparent : 17, //exists
		nmexpanded : 15,//nest_SQL,nest1_SQL,nest2_SQL...
		cmtarget : 15//in ('R', 'B')
	}
};
//don's query database, return '{"rows":[],"total":0}'
var noquery = {
	act : "noquery",
};
// alert(query.condition.nmlevel);
/** min
var query = {};**/
query.jdata = { act: "noquery", condition: {}, operator : {} };


var vvexport = {
	act : "export",
	condition : {
		nmid : 1,
		cmname : "模块名称",
		nmlevel : [ 1, 2, 3 ],
		cmtarget : [ 'R', 'B' ]
	},
	operator : {//operator 运算符
		nmid : 1,  //=1  //key must the same with comdition
		cmname : 11,//like '%模块名称%'
		nmlevel : 13, //in (1, 2, 3)
		cmtarget : 13//in ('R', 'B')
	}
};

var cud = {
	act : "cud",
	add : [{data : {
			nmid : 1,
			cmname : "模块名称1",
			nmlevel : "2"
			}
		},
		{data : {
			nmid : 2,
			cmname : "模块名称2",
			nmlevel : "2"
		}}
	],
	update : [{
			data : {//pojo value
				nmid : 1,
				cmname : "模块名称1new",
				nmlevel : "2"
			},
			condition : {
				nmid : 1,
				cmname : "模块名称1old",
				nmlevel : "2"
			},
			operator : {//operator 运算符  optional 
				nmid : 1,  //=1  //key must the same with comdition
				cmname : 11,//like '%模块名称%'
				nmlevel : 13, //in (1, 2, 3)
				cmtarget : 13//in ('R', 'B')
		}},
		{
			data : {//pojo value
				nmid : 2,
				cmname : "模块名称2new",
				nmlevel : "2"
			},
			condition : {
				nmid : 1,
				cmname : "模块名称2old",
				nmlevel : "2"
			},
			operator : {//operator 运算符  optional 
				nmid : 1,  //=1  //key must the same with comdition
				cmname : 11,//like '%模块名称%'
				nmlevel : 13, //in (1, 2, 3)
				cmtarget : 13//in ('R', 'B')
		}}
	],
	del : [{
			condition : {
				nmid : 1,
				cmname : "模块名称1",
				nmlevel : [ 1, 2, 3 ],
				cmtarget : [ 'R', 'B' ]
			},
			operator : {//operator 运算符 optional
				nmid : 1,  //=1  //key must the same with comdition
				cmname : 11,//like '%模块名称%'
				nmlevel : 13, //in (1, 2, 3)
				cmtarget : 13//in ('R', 'B')
			}
		},
		{
			condition : {
				nmid : 2,
				cmname : "模块名称2",
				nmlevel : [ 1, 2, 3 ],
				cmtarget : [ 'R', 'B' ]
			},
			operator : {//operator 运算符 optional
				nmid : 1,  //=1  //key must the same with comdition
				cmname : 11,//like '%模块名称%'
				nmlevel : 13, //in (1, 2, 3)
				cmtarget : 13//in ('R', 'B')
			}
	}]
	/* NO rows key
	 * del : {
		condition : {
			nmid : 1,
			cmname : "模块名称1",
			nmlevel : [ 1, 2, 3 ],
			cmtarget : [ 'R', 'B' ]
		},
		operator : {//operator 运算符 optional
			nmid : 1,  //=1  //key must the same with comdition
			cmname : 11,//like '%模块名称%'
			nmlevel : 13, //in (1, 2, 3)
			cmtarget : 13//in ('R', 'B')
		}
	}*/
};
/** min
var cud = {};
cud.jdata = {act : "cud", add : [], update : [], del : [] };
cud.jdata = {act : "cud", add : {rows:[]}, update : {rows:[]}, del : {rows:[]} };
**/



// response data
// 添加成功，修改成功，删除成功
var result = {
//	flag : true,
	success : true,
	msg : "添加成功！"
};



// (0) 2 |(1) 12 |(2) 2 |(3) 12 |(4) 2 |(5) 2 |(6) 12 |(7) 12 |(8) 12 |(9) 12
// |(10) 12 |(11) 12 |(12) 93 |(13) 12 |(14) 2 |(15) 93 |(16) 12 |(17) 2 |
// TAuthorityModule_ColumnTypes={N_MID|C_MNAME|N_MLEVEL|C_MLEAF|N_MORDER|N_MPARENT|C_MTARGET|C_MICONCLS|C_MEXPANDED|C_MCHECKED|C_MPATH|C_MNOTE|C_MCTIME|C_MCIP|N_MCUSER|C_MMTIME|C_MMIP|N_MMUSER|}
var TAuthorityModule_ColumnTypes2 = {
	N_MID : 2,
	C_MNAME : 12,
	N_MLEVEL : 2,
	C_MLEAF : 12,
	N_MORDER : 2,
	N_MPARENT : 2,
	C_MTARGET : 12,
	C_MICONCLS : 12,
	C_MEXPANDED : 12,
	C_MCHECKED : 12,
	C_MPATH : 12,
	C_MNOTE : 12,
	C_MCTIME : 93,
	C_MCIP : 12,
	N_MCUSER : 2,
	C_MMTIME : 93,
	C_MMIP : 12,
	N_MMUSER : 2
};