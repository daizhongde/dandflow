package person.daizhongde.migration.struts2.action.tree;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.configutils.SQLNode;
import person.daizhongde.virtue.constant.AbstractConstant;

import person.daizhongde.migration.constant.ConstMigJobIns;
import person.daizhongde.migration.spring.service.MigJobInsService;
import person.daizhongde.authority.struts2.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 控件树<p>将任务包和控件一起在树中展示
 * <br>get tree json data 
 * @author dzd
 *
 */
public class MigJobInsTREEAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7769930693708478011L;

	/** ext3 and ext4 treeload params **/
	private String node;
	
	/** ext4 treeload params **/
	private String _dc;
	
	/** lazy load YUI2, JEasyUI, dnd node source, remove node id **/
	private String id;//lazy load parameter
	
	private int userId;
	
	/** move tree node refer **/
	private int target;
	private String point;
	
	/* below field copy from MigJobInsJEasyUIQUERYAction  */
	private long total;// 只有private的变量(并且定义get方法)json插件才能返回
	private List rows;// pageSize->results

	/** 页号 **/
	protected int page;// pageNumber
	
	/** level,leaf */
	protected String sort;//sort column name or column's index
	/** desc,asc */
	protected String order;//'desc','asc' can be used
	
	private MigJobInsService dataService;
				
	/**
	 * config Json数据-menu
	 * @return JSONArray
	 */
	public String query_JEasyUI_TreeGrid_Async(){
//		log.debug("action........query_JEasyUI_Tree_Async............");
//		if( org.apache.commons.lang3.StringUtils.isEmpty(id) ){
//			super.setJson( dataService.getData_JEasyUI_TreeGrid_Async( null, true ) ); 
//		}else{
//			super.setJson( dataService.getData_JEasyUI_TreeGrid_Async( id, false ) );
//		}
//		return SUCCESS;
		
		if( org.apache.commons.lang3.StringUtils.isEmpty(id) )
		{
			int pageSize = Integer.parseInt(rows==null?"0":rows.get(0).toString());// pageSize
			int offset = (page - 1) * pageSize;// 第一条记录的索引,offset begin from 0, page begin from 1
			// 当jdata.condition为空 没有where条件
			// 在这里读配置文件sql并组装sql和参数values
			JSONObject jsonObject = JSONObject.fromObject( super.getJdata() );
			AbstractConstant absConstant = new ConstMigJobIns();
	
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
			String queryTreeGridData = ((SQLNode)absConstant.getSQLDOC().getQuery()
					.get("queryTreeGridData")
				).getSQL();
			
			SQLAssembleQ sqlA = new SQLAssembleQ(
					absConstant.getSQLDOC(),
					queryTreeGridData,
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
				
//				List rows2 = new ArrayList(rows.size());
//				for(Object e : rows){
//					Map map = (Map)e;
//					map.put("parent", new ArrayList());
//					rows2.add(map);
//				}
//				rows = rows2;
			}else{//parameter sqlA pass in, because of it can only assemble a time
				total = dataService.getTotal(sqlA);
				rows = dataService.getRowsInMap(sqlA, offset, pageSize);
				
//				List rows2 = new ArrayList(rows.size());
//				for(Object e : rows){
//					Map map = (Map)e;
//					map.put("parent", new ArrayList());
//					rows2.add(map);
//				}
//				rows = rows2;
			}
			Map json = new HashMap();
			json.put("total", total);
			json.put("rows", rows);
			super.setJson(json);
			return SUCCESS;
		}else{
			super.setJson( dataService.getData_JEasyUI_TreeGrid_Async( id, false ) );
			
//			Map json = new HashMap();
//			json.put("total", total);
//			json.put("rows", rows);
//			super.setJson(json);
			return SUCCESS;
		}
	}
	
	public void setNode(String node) {
		this.node = node;
	}

	public void set_dc(String _dc) {
		this._dc = _dc;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

//	public void setJdata(String jdata) throws UnsupportedEncodingException {
////		log.debug("before decode jdata:" + jdata.toString());
//		String decode = java.net.URLDecoder.decode(jdata, "UTF-8");
////		log.debug("after  decode jdata:" + decode.toString());
//		this.jdata = decode;
//	}

	public void setTarget(int target) {
		this.target = target;
	}
	public void setPoint(String point) {
		this.point = point;
	}

	/* below method copy from MigJobInsJEasyUIQUERYAction  */
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
	public void setDataService(MigJobInsService dataService) {
		this.dataService = dataService;
	}
}
