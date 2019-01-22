package person.daizhongde.migration.struts2.action.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.Operator;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstCommon;
import person.daizhongde.migration.spring.service.CommonQUERYService;
import person.daizhongde.migration.spring.service.CommonService;

/**
 * 通用查询
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class CommonCBBQUERYAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long total;// 只有private的变量(并且定义get方法)json插件才能返回
	private List rows;// pageSize->results

	/** 页号 **/
	private int page;// pageNumber
	
	/** level,leaf */
	private String sort;//sort column name or column's index
	/** desc,asc */
	private String order;//'desc','asc' can be used
	
	private String columnNames;
	private String tableName;
	
	private CommonQUERYService queryService;
	private CommonService commonService;
	
	private String q;
	/** This columns is like search columns, it is different from select column in sql */
	private String columns; 
	
	/**
	 * 查询模块信息 row is json
	 * <br>invoke service method: getRowsInMap
	 * @return
	 */
	public String dfind() {
		int pageSize = Integer.parseInt(rows==null?"0":rows.get(0).toString());// pageSize
		int offset = (page - 1) * pageSize;// 第一条记录的索引,offset begin from 0, page begin from 1
		
		JSONObject jsonObject = JSONObject.fromObject( super.getJdata() );
		AbstractConstant absConstant = new ConstCommon();
		
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
		
		Map condition = new HashMap();
		Map operator = new HashMap();
		
		//目前还不支持多列模糊查询，需要改造SQLAssembleQ或新增一个SQLAssembleX类
		if( StringUtils.isNotEmpty(q) ){
			String[] arr = columns.split("\\|");
			for( String col : arr ){
				condition.put( col, q );
				operator.put( col, Operator.CONTAIN );
			}
		}
		
		columnNames = StringUtils.isEmpty(columnNames)?
				commonService.getSelectSQLColumns().get(tableName.toLowerCase())
				:columnNames; 
				
		SQLAssembleQ sqlA = new SQLAssembleQ(
				null,
				"select "+columnNames+" from "+tableName,
				condition, 
				operator,
				absConstant.getColumnTypes(),
				null,
				sort
			);
		
		if( pageSize==0 ){//parameter sqlA pass in, because of it can only assemble a time
			total = queryService.getTotal(sqlA);
			rows = queryService.getRowsInMap(sqlA);
		}else{//parameter sqlA pass in, because of it can only assemble a time
			total = queryService.getTotal(sqlA);
			rows = queryService.getRowsInMap(sqlA, offset, pageSize);
		}
		
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

	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setQ(String q) {
		this.q = q;
	}
	
	public void setColumns(String columns) {
		this.columns = columns;
	}

	public void setQueryService(CommonQUERYService queryService) {
		this.queryService = queryService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
}
