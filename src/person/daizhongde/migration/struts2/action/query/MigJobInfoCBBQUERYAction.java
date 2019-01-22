package person.daizhongde.migration.struts2.action.query;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.configutils.SQLNode;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.Operator;
import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstMigJobInfo;
import person.daizhongde.migration.spring.service.MigJobInfoService;

/**
 * 控件信息查询
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobInfoCBBQUERYAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long total;// 只有private的变量(并且定义get方法)json插件才能返回
	private List rows;// pageSize->results

	/** 页号 **/
	protected int page;// pageNumber
	
	/** level,leaf */
	protected String sort;//sort column name or column's index
	/** desc,asc */
	protected String order;//'desc','asc' can be used
	
	protected MigJobInfoService dataService;
	
	private String q;
	/**
	 * 查询模块信息 row is json
	 * <br>invoke service method: getRowsInMap
	 * @return
	 */
	public String dfind() {
		int pageSize = Integer.parseInt(rows==null?"0":rows.get(0).toString());// pageSize
		int offset = (page - 1) * pageSize;// 第一条记录的索引,offset begin from 0, page begin from 1
		// 当jdata.condition为空 没有where条件
		// 在这里读配置文件sql并组装sql和参数values
		JSONObject jsonObject = JSONObject.fromObject( super.getJdata() );
		AbstractConstant absConstant = new ConstMigJobInfo();

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
		
		String querycbb = ((SQLNode)absConstant.getSQLDOC().getQuery()
				.get("querycbb")
			).getSQL();
		
		Map condition = new HashMap();
		Map operator = new HashMap();
		
		if( StringUtils.isNotEmpty(q) ){
			condition.put("job_name", q );
			operator.put("job_name", Operator.CONTAIN );
		}
		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				querycbb,
				condition, 
				operator,
				absConstant.getColumnTypes(),
				absConstant.getFront2col(),
				sort
			);
		
		if( pageSize==0 ){//parameter sqlA pass in, because of it can only assemble a time
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
		JSONObject jsonObject = JSONObject.fromObject( super.getJdata() );
//		SQLAssemble2 sqlA = new SQLAssemble2(jsonObject, "MigJobInfo");
		AbstractConstant absConstant = new ConstMigJobInfo();
		
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
//			rows = null;
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
		JSONObject jsonObject = JSONObject.fromObject( super.getJdata() );
		AbstractConstant absConstant = new ConstMigJobInfo();

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

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setDataService(MigJobInfoService dataService) {
		this.dataService = dataService;
	}
}
