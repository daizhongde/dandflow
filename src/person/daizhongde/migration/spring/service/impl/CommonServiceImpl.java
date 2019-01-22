package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import person.daizhongde.migration.spring.service.CommonService;

import person.daizhongde.virtue.dao.SpringHibernateDao;
import person.daizhongde.virtue.sql.SQLManySwitch;
import person.daizhongde.virtue.util.collection.List2Map;
import person.daizhongde.virtue.util.test.Printer;
import net.sf.json.JSONObject;

public class CommonServiceImpl implements CommonService {
	
	private SpringHibernateDao virtueDAO;

	private static String TableNames;
	private static Map TableComment = new HashMap();//key:tablename, value: comment
	private static Map TableColumns = new HashMap();//key:tablename, value: col1,col2...
	/** key:tablename, value: col1,col2... different from TableColumns datetime timestamp type column format  */
	private static Map SelectSQLColumns = new HashMap();
	
	public void init(){
//		System.out.println("init CommonService..");
		Map map = new HashMap(2);
		long total = 0;
		List rows = new ArrayList();
				
		total = Long.valueOf(
				virtueDAO.sqlQueryfindaValue(
//				"select count(*) from all_tables where OWNER='CPAB'"
				"SELECT count(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'tool'"
			).toString()
		);
		rows = virtueDAO.sqlQuerylistAll(
//			"select TABLE_NAME from all_tables where OWNER='CPAB'"
//				"select a.TABLE_NAME \"tname\",b.COMMENTS \"comments\"" +
//				"  from all_tables a, all_tab_comments b " +
//				" where a.OWNER='CPAB' and a.OWNER=b.OWNER and a.TABLE_NAME=b.TABLE_NAME(+) and b.TABLE_TYPE='TABLE' and b.TABLE_NAME not like 'BIN$%'"
				//oracle : lower(), upper()
				//mysql:  lower(), upper()
				"SELECT lower(table_name) tname,table_comment comments FROM INFORMATION_SCHEMA.TABLES "+
				"WHERE TABLE_SCHEMA = 'tool' "
		);
		
		map.put("total", total );
		map.put("rows", rows );
		
//		Printer.printJSON(rows);
		
		TableNames = JSONObject.fromObject(map).toString();
		TableComment = List2Map.toMap(rows,"tname","comments");
				
		
		//assemble tablecolumns
		Iterator it = TableComment.keySet().iterator();
		while(it.hasNext()){
			String tableName = (String)it.next();
			String cols = "";
			String sqlcols = "";
			List<Map> columns = virtueDAO.sqlQuerylistAll(
					"SELECT lower(column_name) column_name, lower(data_type) data_type FROM information_schema.columns "+
					" WHERE TABLE_SCHEMA = 'tool' and table_name ='"+tableName+"'"
			);
			//SELECT lower(column_name) column_name FROM information_schema.columns WHERE table_name ='mig_task_config'
			//select * from information_schema.columns WHERE table_name ='mig_task_config'
			for( int i=0, j=columns.size(); i<j; i++ ){
				cols += columns.get(i).get("column_name");
				sqlcols += SQLManySwitch.getMySQLSelectSQLColumn(columns.get(i).get("column_name").toString(), columns.get(i).get("data_type").toString());
				if(i!=j-1){
					cols += ",";
					sqlcols += ",";
				}else{
					cols += " ";
					sqlcols += " ";
				}
			}
			TableColumns.put( tableName, cols );
			SelectSQLColumns.put( tableName, sqlcols );
		}
	}

	public String getTableNames() {
		return TableNames;
	}

	public Map<String, String> getTableComment() {
		return TableComment;
	}

	public Map<String, String> getTableColumns() {
		return TableColumns;
	}

	public Map getSelectSQLColumns() {
		return SelectSQLColumns;
	}

	public SpringHibernateDao getVirtueDAO() {
		return virtueDAO;
	}

	public void setVirtueDAO(SpringHibernateDao virtueDAO) {
		this.virtueDAO = virtueDAO;
	}

}
