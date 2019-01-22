package person.daizhongde.migration.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import person.daizhongde.migration.constant.ConstMigJobProcess;
import person.daizhongde.migration.spring.service.MigJobProcessService;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.constant.AbstractConstant;
import org.springframework.web.socket.server.standard.SpringConfigurator;

//@ServerEndpoint("/websocket/runningIns/{username}")
@ServerEndpoint(value ="/websocket/runningTask/{username}", configurator = SpringConfigurator.class)
public class WebSocket_MigJobProcess {

	private Session session;
	private MigJobProcessService dataService;
	
	private long total;// 只有private的变量(并且定义get方法)json插件才能返回
	private int pageSize;//
	private List rows;// 

	/** 页号 **/
	private int page;// pageNumber
	
	/** level,leaf */
	private String sort;//sort column name or column's index
	/** desc,asc */
	private String order;//'desc','asc' can be used
	private JSONObject jdata;
	
	public void setDataService(MigJobProcessService dataService) {
		this.dataService = dataService;
	}

	@OnOpen
	public void onOpen( Session session) throws IOException {

		this.session = session;

		System.out.println("已连接");
	}

	@OnClose
	public void onClose() throws IOException {
		System.out.println("连接关闭");
	}

	@OnMessage
	public void onMessage(String message ) throws IOException {
//		System.out.println("message1:"+message1+",messsage2:"+message2);//只支持一个参数
//	 	String jdata  = options.queryParams.jdata;
//	 	var page = options.pageNumber;
//	 	var rows = options.pageSize;
//	 	var sort = options.sortName;
//	 	var order = options.sortOrder;
		
		JSONObject options = JSON.parseObject( message );
		JSONObject queryParams = (JSONObject)options.get("queryParams");
		String jdataS = queryParams.getString("jdata");
		jdata = JSON.parseObject(java.net.URLDecoder.decode(jdataS, "UTF-8"));
		page = options.getIntValue("pageNumber");
		pageSize = options.getIntValue("pageSize");
		sort = options.getString("sortName");
		order = options.getString("sortOrder");
		
		System.out.println("page:"+page+",pageSize:"+pageSize+",sort:"+sort+",order:"+order);
		
		sendMessageAll(dfind().toJSONString());
//		sendMessageObject(dfind());
	}
	/**
	 * 查询模块信息 row is json
	 * <br>invoke service method: getRowsInMap
	 * @return
	 */
	public JSONObject dfind() {
		int offset = (page - 1) * pageSize;// 第一条记录的索引,offset begin from 0, page begin from 1
		// 当jdata.condition为空 没有where条件
		// 在这里读配置文件sql并组装sql和参数values
		JSONObject jsonObject = jdata;
		AbstractConstant absConstant = new ConstMigJobProcess();

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
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", rows);
		return json;
	}
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	public void sendMessageAll(String message) throws IOException {
		session.getAsyncRemote().sendText(message);
	}
	public void sendMessageObject(Object data) throws IOException {
		session.getAsyncRemote().sendObject(data);
	}
}