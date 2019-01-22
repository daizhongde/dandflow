package person.daizhongde.migration.struts2.action.query;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.assemble.sql.SQLUtil;
import person.daizhongde.virtue.configutils.SQLNode;
import person.daizhongde.virtue.constant.AbstractConstant;
import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstMigAuditvResult;
import person.daizhongde.migration.spring.service.MigAuditvResultService;

/**
 * field audit config main table
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigAuditvResultJEasyUIQUERYAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5613407420319662288L;
	private long total;// 只有private的变量(并且定义get方法)json插件才能返回
	private List rows;// pageSize->results

	/** 页号 **/
	protected int page;// pageNumber
	
	protected String jdata;
	/** level,leaf */
	protected String sort;//sort column name or column's index
	/** desc,asc */
	protected String order;//'desc','asc' can be used
	
	protected MigAuditvResultService dataService;

	/**
	 * 查询模块信息 row is json
	 * <br>invoke service method: getRowsInMap
	 * @return
	 */
	public String dfind() {
		int pageSize = Integer.parseInt(rows==null?"0":rows.get(0).toString());// pageSize
		int offset = (page - 1) * pageSize;// 第一条记录的索引,offset begin from 0, page begin from 1

		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvResult();

		List<Map> sort = new ArrayList<Map>();
		if( this.sort != null && !this.sort.trim().equalsIgnoreCase("") ){
			String[] a1 = this.sort.split("\\,");
			String[] a2 = this.order.split("\\,");
			
			for(int i=0, j=a1.length; i<j; i++ ){
				Map map = new HashMap();
				map.put( a1[i], a2[i] );
				sort.add(map);
			}
		}
		
		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				absConstant.getQuery_SQL(),
				jsonObject.getJSONObject("condition"), 
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col(),
				sort
			);
		
		if( jsonObject.getString("act").equalsIgnoreCase("noquery") ){
			total = 0;
			rows = new ArrayList();
		}else if( pageSize==0 ){//parameter sqlA pass in, because of it can only assemble a time
			total = dataService.getTotal(sqlA);
			rows = dataService.getRowsInMap(sqlA);
		}else{//parameter sqlA pass in, because of it can only assemble a time
			total = dataService.getTotal(sqlA);
			rows = dataService.getRowsInMap(sqlA, offset, pageSize);
		}
		return SUCCESS;
	}
	/**
	 * 查询模块信息 row is json
	 * <br>invoke service method: getRowsInMap
	 * @return
	 */
	public String dfindDiff() {
		int pageSize = Integer.parseInt(rows==null?"0":rows.get(0).toString());// pageSize
		int offset = (page - 1) * pageSize;// 第一条记录的索引,offset begin from 0, page begin from 1

		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvResult();

		List<Map> sort = new ArrayList<Map>();
		if( this.sort != null && !this.sort.trim().equalsIgnoreCase("") ){
			String[] a1 = this.sort.split("\\,");
			String[] a2 = this.order.split("\\,");
			
			for(int i=0, j=a1.length; i<j; i++ ){
				Map map = new HashMap();
				map.put( a1[i], a2[i] );
				sort.add(map);
			}
		}
		
		String queryDiff = ((SQLNode)absConstant.getSQLDOC().getQuery()
				.get("queryDiff")
			).getSQL();
		
		Map cond = jsonObject.getJSONObject("condition");
		String ok = "";
		String whereBackSQL2 ="";
		if(cond.containsKey("ok")){
			ok=cond.get("ok").toString();
			cond.remove("ok");
			whereBackSQL2 = " WHERE ok='"+ok+"' ";
		}
		
		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				queryDiff,
				cond, 
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col(),
				sort
			);
		
		/**
		 * SELECT t3.*, IFNULL(t4.reason,'') "reason", IFNULL(t4.dmp_no,'') "dmp_no" FROM 
(
	SELECT t2.AUDIT_ID "audit_id", 
	       t2.audit_name AS "audit_item",
	       t1.fares_dryrun_id "fares_dryrun_id",
	       (SELECT MIG_DRYRUN_NAME FROM tool.v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
	       IFNULL(t1.ENV,'') "env",
	       t2.domain "domain",
	       t2.AUDIT_AUTHOR AS "config_author",
	       DATE_FORMAT(t1.hdate,"%Y-%m-%d") "audit_date",
	#acorss 0 clock audit have problem
	       IF(SUM(IF(t1.success_flag='0',1,IF(t1.result='0',1,0)))>0,'FALSE','TRUE') AS "ok",
	       CAST(IF(MIN(t1.success_flag)='0','#',SUM(t1.min_value))  AS CHAR(11)) AS "invalid_count"
	  FROM tool.mig_auditv_result t1, tool.mig_auditv_config t2
	 WHERE t1.audit_id=t2.audit_id
	   #AND t1.fares_dryrun_id='5' AND t1.env='C' AND t1.domain='2' AND t2.AUDIT_AUTHOR='huyx3'   --whereBackSQL
     GROUP BY env,domain, config_author, AUDIT_ID, audit_item,fares_dryrun_id,  audit_date
) t3
  LEFT OUTER JOIN tool.mig_auditv_errreason t4
    ON t3.audit_id = t4.audit_id AND t3.env=t4.env
 WHERE ok='FALSE'   --whereBackSQL2
 
		 */

		String whereBackSQL = SQLUtil.getWhereBackSQL(
				cond, 
				jsonObject.getJSONObject("operator"), 
				absConstant.getFront2col(), 
				absConstant.getColumnTypes(),
				new HashMap(), 
				true,
				absConstant.getSQLDOC() );
		if(StringUtils.isNotEmpty(whereBackSQL)){
			queryDiff = queryDiff.replaceFirst("GROUP BY ", "and "+whereBackSQL+" GROUP BY ");
		}
		queryDiff += whereBackSQL2;
		
		String fromSQL = SQLUtil.getFromSQL(queryDiff);
		String countSQL = "select count(*) " + fromSQL;
		sqlA.setCountSQL(countSQL);
		
		queryDiff += SQLUtil.getOrderSQL( sort );
		sqlA.setSQL(queryDiff);
		
		if( jsonObject.getString("act").equalsIgnoreCase("noquery") ){
			total = 0;
			rows = new ArrayList();
		}else if( pageSize==0 ){//parameter sqlA pass in, because of it can only assemble a time
			total = dataService.getTotal(sqlA);
			rows = dataService.getRowsInMap(sqlA);
		}else{//parameter sqlA pass in, because of it can only assemble a time
			total = dataService.getTotal(sqlA);
			rows = dataService.getRowsInMap(sqlA, offset, pageSize);
		}
		return SUCCESS;
	}
	/**
	 * 查询模块信息 row is array
	 * <br>invoke service method: getRowsInArray
	 * @return
	 */
	public String dfindArray() {
		int pageSize = Integer.parseInt(rows.get(0).toString());// pageSize
		int offset = (page - 1) * pageSize;// 第一条记录的索引;
		// 当jdata.condition为空 没有where条件
		// 在这里读配置文件sql并组装sql的where条件
		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		SQLAssemble2 sqlA = new SQLAssemble2(jsonObject, "MigAuditvResult");
		AbstractConstant absConstant = new ConstMigAuditvResult();
		
		List<Map> sort = new ArrayList<Map>();
		if( this.sort != null && !this.sort.trim().equalsIgnoreCase("") ){
			Map map = new HashMap();
			map.put( this.sort, this.order );
			sort.add(map);
		}
		
		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				absConstant.getQuery_SQL(),
				jsonObject.getJSONObject("condition"), 
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col(),
				sort
			);

		if( jsonObject.getString("act").equalsIgnoreCase("noquery")){
			total = 0;
			rows = new ArrayList();
		}else{//parameter sqlA pass in, because of it can only assemble a time
			total = dataService.getTotal(sqlA);
			rows = dataService.getRowsInArray(sqlA, offset, pageSize);
		}
		return SUCCESS;
	}

	/**
	 * 查询模块总数
	 * 
	 * @return
	 */
	public String dfindTotal() {
		// 当jdata.condition为空 没有where条件
		// 在这里读配置文件sql并组装sql的where条件
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvResult();

		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				absConstant.getQuery_SQL(),
				jsonObject.getJSONObject("condition"), 
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
			);
		
		total = dataService.getTotal(sqlA);
		return SUCCESS;
	}

	public void validate() {
		if (jdata.length() == 0) {
//			addFieldError("jdata", getText("jdata.required"));
//			addFieldError("jdata", "you must input jdata!");
		} else {
//			JSONObject jsonObject = JSONObject.fromObject(jdata);
//			JdataChecker checker = new JdataChecker(jsonObject, "MigAuditvResult");
//			String result =  checker.queryCheck();
//			System.out.println(result);
//			if(!result.equalsIgnoreCase("true")){
//				addFieldError("jdata", result);
//			}
		}
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setSort(String sort) {
//		this.sort = String.valueOf(Integer.valueOf(sort) + 1 );//where datatable's data is array use NO. to order, because front column index begin from 0 ,so plus one
		this.sort = sort;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public void setJdata(String jdata) throws UnsupportedEncodingException {
//		log.debug("encoded jdata:" + jdata.toString());
		String decode = java.net.URLDecoder.decode(jdata, "UTF-8");
		log.debug("#######");
		log.debug("decoded jdata:" + decode.toString());
		log.debug("#######");
		this.jdata = decode;
	}
//	public void setParam(AParameter param) {
//		this.param = param;
//	}
//	
//	public AParameter getParam() {
//		return param;
//	}

	public void setDataService(MigAuditvResultService dataService) {
		this.dataService = dataService;
	}
}
