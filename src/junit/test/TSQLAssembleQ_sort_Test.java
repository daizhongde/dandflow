package junit.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TSQLAssembleQ_sort_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** [{attribute: "LEVEL1", descending: true}]   **/
		@SuppressWarnings("rawtypes")
		List<Map> sort = new LinkedList<Map>();
		Map map = new HashMap();
		map.put("attribute", "LEVEL1");
//		map.put("descending", true);
		
		Map map1 = new HashMap();
		map1.put("attribute", "name");
//		map1.put("descending", true);
	
		sort.add(map);
		sort.add(map1);
		
		String SQL = "";
		
		//order by LEVEL1 asc, name asc
		if( sort != null && sort.size() != 0 ){
			SQL += " order by ";
			boolean b;
			for( int m = 0, n = sort.size(); m < n; m ++ ){
				if( sort.get( m ).get("descending") == null ){//default desc
					b = false;
				}else{
					b = (Boolean)sort.get( m ).get("descending");
				}
				if( m != n - 1){
					SQL += sort.get( m ).get("attribute") + " " + 
							( b ? "desc" : "asc" ) + ", ";
				}else{
					SQL += sort.get( m ).get("attribute") + " " + 
						    ( b ? "desc" : "asc" );
				}
			}
		}
		System.out.println("SQL:" + SQL);
	}

}
