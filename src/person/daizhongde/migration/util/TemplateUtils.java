package person.daizhongde.migration.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;

public class TemplateUtils {
	/**
	 * 将某一个控件模板的参数封装成map对象<p>
	 * map方便id与name的转换
	 * @param templates
	 * @return
	 */
	public static Map<Integer, String> getIdNameMap(List<MigControlTemplate> templates){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(MigControlTemplate e : templates){
			map.put(e.getId().getParaId(), e.getParaName());
		}
		return map;
	}
	/**
	 * 将某一个控件模板的参数封装成map对象<p>
	 * map方便id与name的转换
	 * @param templates
	 * @return
	 */
	public static Map<Integer, MigControlTemplate> getMap(List<MigControlTemplate> templates){
		Map<Integer, MigControlTemplate> map = new HashMap<Integer, MigControlTemplate>();
		for(MigControlTemplate e : templates){
			map.put( e.getId().getParaId(), e );
		}
		return map;
	}
}
