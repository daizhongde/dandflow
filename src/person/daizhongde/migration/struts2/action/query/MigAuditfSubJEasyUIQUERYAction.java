package person.daizhongde.migration.struts2.action.query;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.constant.AbstractConstant;
import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstMigAuditfSub;
import person.daizhongde.migration.spring.service.MigAuditfSubService;

/**
 * field audit config subtable
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigAuditfSubJEasyUIQUERYAction extends BaseAction {

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
	
	protected MigAuditfSubService dataService;

	/**
	 * 查询模块信息 row is json
	 * <br>invoke service method: getRowsInMap
	 * @return
	 */
	public String dfind() {
		int pageSize = Integer.parseInt(rows==null?"0":rows.get(0).toString());// pageSize
		int offset = (page - 1) * pageSize;// 第一条记录的索引,offset begin from 0, page begin from 1

		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditfSub();

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
//		SQLAssemble2 sqlA = new SQLAssemble2(jsonObject, "MigAuditfSub");
		AbstractConstant absConstant = new ConstMigAuditfSub();
		
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
		AbstractConstant absConstant = new ConstMigAuditfSub();

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

	public void setDataService(MigAuditfSubService dataService) {
		this.dataService = dataService;
	}
}
