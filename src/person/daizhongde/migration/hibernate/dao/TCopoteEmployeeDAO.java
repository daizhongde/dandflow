package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.migration.hibernate.pojo.TCopoteEmployee;
import person.daizhongde.virtue.dao.SpringHibernateDao;

public interface TCopoteEmployeeDAO  extends SpringHibernateDao{

	//property constants
	String UIN = "uin";
	String PID = "pid";
	String NAME = "name";
	String ALIAS = "alias";
	String SEX = "sex";
	String POS = "pos";
	String TEL = "tel";
	String BIRTH = "birth";
	String SLAVE_ALIAS = "slaveAlias";
	String DEPARTMENT = "department";
	String MOBILE = "mobile";
	String EMPLOYEE_NO = "employeeNo";
	String EMPLOYEE_CARDNO = "employeeCardno";
	String EMPLOYEE_IDCARD = "employeeIdcard";
	String LOGNAME = "logname";

	void save(TCopoteEmployee transientInstance);

	void updateYGBHZJHM(	String employee_no,
			String name,
			String employee_idcard);
	
	void delete(TCopoteEmployee persistentInstance);

	TCopoteEmployee findById(java.lang.Integer id);

	List findByExample(TCopoteEmployee instance);

	List findByProperty(String propertyName, Object value);

	List findByUin(Object uin);

	List findByPid(Object pid);

	List findByName(Object name);

	List findByAlias(Object alias);

	List findBySex(Object sex);

	List findByPos(Object pos);

	List findByTel(Object tel);

	List findByBirth(Object birth);

	List findBySlaveAlias(Object slaveAlias);

	List findByDepartment(Object department);

	List findByMobile(Object mobile);

	List findByEmployeeNo(Object employeeNo);

	List findByEmployeeCardno(Object employeeCardno);

	List findByEmployeeIdcard(Object employeeIdcard);

	List findByLogname(Object logname);

	List findAll();

	TCopoteEmployee merge(TCopoteEmployee detachedInstance);

	void attachDirty(TCopoteEmployee instance);

	void attachClean(TCopoteEmployee instance);

}