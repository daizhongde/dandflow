/** Default options for export
 *  <br>The pragram default options. Developer Can override this config in program.
 *  <p>
 *  below options are aviliable for all export file type
 *  <br>IncludeColumnTitles
 *  <br>ContinueOnError
 *  <br>Encoding		Export file text encoding
 *  <br>AddTimestamp	Wethether add timestamp to export filename
 *  <br>Timestamp		timestamp format
 *  The others are only use by export txt file
 * 
 *  Note:
 *   null == ""  --null is the same as space string
 *  **/
var xlsExportOptions = {
	Encoding				: "GB2312",
	AddTimestamp 			: "false",//or "true"
	TimestampFormat			: "",////or "YYYYMMDD","YYYY-MM-DD-HHNNSS"
	IncludeColumnTitles		: true,//or "false"
	Append					: false, // It is invaild in web application
	ContinueOnError			: false////or "false"
};

var txtExportOptions = {
	Encoding				: "GB2312",
	AddTimestamp 			: "false",//or "true"
	TimestampFormat			: "",////or "YYYYMMDD","YYYY-MM-DD-HHNNSS"
	IncludeColumnTitles		: true,//or "false"
	Append					: false, // It is invaild in web application
	ContinueOnError			: false,////or "false"
	
	RecordDelimiter			: "\r\n",//delimiter
	FieldDelimiter			: "\t",
	TextQualifier			: "",//or ""
	
	DateOrder				: "YMD",//MDY,DMY,YMD,YDM,DYM,MYD
	DateDelimiter			: "/",
	ZeroPaddingDate			: true,//true or "false"
	TimeDelimiter			: ":",//Whenever time's minute and second is ZeroFilled
	
	DecimalSymbol			: ".",
	BinaryDataEncoding		: null //null, "", None, Base64
};