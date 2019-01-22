package person.daizhongde.migration.spring.service;

import java.util.List;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.pojo.MigControlInfo;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigControlInfoService extends BaseService{
	/*
	 * list all supported control info
	 */
	public abstract List<MigControlInfo> getCtlInfoAll();
    
	/**
	 * Get control tree data from memory 
	 * @return
	 */
	public abstract List getData_JEasyUI_Tree();
	/**
	 * Assemble control tree data, This tree havn't Root
	 * <p>
	 * generate jsondata(array) for role tree,
	 * <br>The state assigned in code;
	 * <br>attention: only two level
	 * <br>id,text,state,attributes
	 * <p><b>used by JEasyUI</b>
	 * @return
	 */
	public abstract List getData_JEasyUI_Tree2();
	
}