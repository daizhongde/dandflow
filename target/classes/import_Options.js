/** Default options for import
 *  <br>The pragram default options. Developer Can override this config in program.
 *  <p>
 *  below options are aviliable for all export file type
 *  <br>Encoding				Import file text encoding
 *  <br>EmptyStringReplaceNULL	Use an empty string to replace NULL.
 *  <br>ContinueOnError
 *  The others are only use by import txt file
 *  
 *  Note:
 *   null == ""  --null is the same as space string
 *
 *  **/
var xlsImportOptions = {
	Encoding				: "GB2312",
	UseEmptyStringAsNULL	: false,//or "false"
	ContinueOnError			: true,//or "false"
	
	FieldNameRow			: 1,
	FirstDataRow 			: 2,
	LastDataRow 			: null,//null, "", N+
	
	DateOrder				: "YMD",
	DateDelimiter			: "/",
	TimeDelimiter			: ":",//Whenever time's minute and second is ZeroFilled
	//"DT"-日期时间,"TD"-时间日期,"DTZ"-日期时间时区,"TDZ"-时间日期时区,"TZD"-时间时区日期
	DateTimeOrder			: "DT",
	
	DecimalSymbol			: ".",
	BinaryDataEncoding		: null, //null, "", Base64
	/*
	 * Append: Add records to destination table
	 * Update: Update record in destination with matching record from source
	 * Append|Update: If record exists in destination,update it, Otherwise, add it
	 * Delete: Delete records in destination that match records in source
	 * Copy: Delete all records in destination, repopulate from the source
	 * 
	 * 
	 */
	ImportMode				: "Append"//Append,Update,Append|Update,Delete,Copy
};

var txtImportOptions = {
	Encoding				: "GB2312",
	UseEmptyStringAsNULL	: false,//or "false"
	ContinueOnError			: true,//or "false"
	
	/*fixed-width is a future function   */
	SeparateMode			: "Delimited", //"Delimited" or "Fixed-Width"
	RecordDelimiter			: "\r\n",//delimiter
	FieldDelimiter			: "\t",
	TextQualifier			: null,//or ""
	
	FieldNameRow			: 1,
	FirstDataRow 			: 2,
	LastDataRow 			: null,//null, "", N+
	
	DateOrder				: "YMD",//MDY,DMY,YMD,YDM,DYM,MYD
	DateDelimiter			: "/",
	TimeDelimiter			: ":",//Whenever time's minute and second is ZeroFilled
	//"DT"-日期时间,"TD"-时间日期,"DTZ"-日期时间时区,"TDZ"-时间日期时区,"TZD"-时间时区日期
	DateTimeOrder			: "DT",
	
	DecimalSymbol			: ".",
	BinaryDataEncoding		: null, //null, "", None, Base64
	/*
	 * Append: Add records to destination table
	 * Update: Update record in destination with matching record from source
	 * Append|Update: If record exists in destination,update it, Otherwise, add it
	 * Delete: Delete records in destination that match records in source
	 * Copy: Delete all records in destination, repopulate from the source
	 * 
	 * 
	 */
	ImportMode				: "Append"//Append,Update,Append|Update,Delete,Copy
};