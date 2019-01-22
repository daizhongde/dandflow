/** note: 
 * 		Don't support back comment */

/* the globel variable */
var TChatMsg = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
TChatMsg.Field = {
		N_MID : [ 4, 10, 0, "", "n_mid", "N_MID", 0 ],
		N_UID : [ 4, 8, 0, "", "n_uid", "N_UID", 1 ],
		C_MSG : [ 12, 3072, 0, "", "c_msg", "C_MSG", 2 ],
		"D_MSTIME" : [ 93, 19, 0, "", "d_mstime", "D_MSTIME", 3 ]
	};

TChatMsg.Export={};
TChatMsg.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
TChatMsg.Export.export.ColumnMap = {
		N_MID : [ 4, 10, 0, "", 0 ],
		N_UID : [ 4, 8, 0, "", 1 ],
		C_MSG : [ 12, 3072, 0, "", 2 ],
		"D_MSTIME" : [ 93, 19, 0, "", 3 ]
	};

//TChatMsg.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//TChatMsg.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
TChatMsg.Export.export.DefaultColumns = ["N_MID","N_UID","C_MSG","D_MSTIME"];

