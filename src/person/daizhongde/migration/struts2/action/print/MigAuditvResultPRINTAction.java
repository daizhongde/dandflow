package person.daizhongde.migration.struts2.action.print;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
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
public class MigAuditvResultPRINTAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5613407420319662288L;
	private long total;// 只有private的变量(并且定义get方法)json插件才能返回
	private List rows;// pageSize->results
	private int pageSize;// pageSize
	
	/** 页号 **/
	protected int page;// pageNumber
	
	protected String jdata;
	/** level,leaf */
	protected String sort;//sort column name or column's index
	/** desc,asc */
	protected String order;//'desc','asc' can be used
	
	protected MigAuditvResultService dataService;
	
	/** Use for custom print format. struts config file is support dynamic config, It don't need modify **/
	private String printName;
	
	/**
	 * 打印
	 * <p>
	 * 准备打印数据
	 * @return
	 */
	public String print() {
//		int pageSize = Integer.parseInt(rows==null?"0":rows.get(0).toString());// pageSize
//		int offset = (page - 1) * pageSize;// 第一条记录的索引,offset begin from 0, page begin from 1

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
				absConstant.getEXP_SQL(),
				jsonObject.getJSONObject("condition"), 
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col(),
				sort
			);
		
		total = dataService.getTotal(sqlA);
		rows = dataService.getRowsInMap(sqlA);
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public String getPrintName() {
		return printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
	}
}
