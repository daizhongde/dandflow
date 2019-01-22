package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee;

public interface TAsiainfoEmployeeDAO  extends SpringHibernateDao {

	// property constants
	public static final String SBU_ID = "sbu_id";
	public static final String SBU = "sbu";
	public static final String COMPANY_ID = "company_id";
	public static final String COMPANY = "company";
	public static final String ORGANIZATION_ID = "organization_id";
	public static final String ORG_NAME = "org_name";
	public static final String OFFICE = "office";
	public static final String PAGER = "pager";
	public static final String PERSON_ID = "person_id";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String FULL_NAME = "full_name";
	public static final String EMAIL_ADDRESS = "email_address";
	public static final String AGE = "age";
	public static final String ASSIGNMENT_ID = "assignment_id";
	public static final String CLASS_ = "class_";
	public static final String WORKING_LOCATION = "working_location";
	public static final String SEAT_NO = "seat_no";
	public static final String MOBILE = "mobile";
	public static final String NT_ACCOUNT = "nt_account";
	public static final String SUPERVISOR_ID = "supervisor_id";
	public static final String SUPERVISOR_NAME = "supervisor_name";
	public static final String HIGHEST_DEGREE = "highest_degree";

	public abstract List<TAsiainfoEmployee> findAllBoss();
	public abstract List findChildrenNoRecursive( Integer parentId );
	
	public abstract void save(TAsiainfoEmployee transientInstance);

	public abstract void delete(TAsiainfoEmployee persistentInstance);

	public abstract TAsiainfoEmployee findById(java.lang.String id);

	public abstract List findByExample(TAsiainfoEmployee instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findBySbuId(Object sbuId);

	public abstract List findBySbu(Object sbu);

	public abstract List findByCompanyId(Object companyId);

	public abstract List findByCompany(Object company);

	public abstract List findByOrganizationId(Object organizationId);

	public abstract List findByOrgName(Object orgName);

	public abstract List findByOffice(Object office);

	public abstract List findByPager(Object pager);

	public abstract List findByPersonId(Object personId);

	public abstract List findByFirstName(Object firstName);

	public abstract List findByLastName(Object lastName);

	public abstract List findByFullName(Object fullName);

	public abstract List findByEmailAddress(Object emailAddress);

	public abstract List findByAge(Object age);

	public abstract List findByAssignmentId(Object assignmentId);

	public abstract List findByClass_(Object class_);

	public abstract List findByWorkingLocation(Object workingLocation);

	public abstract List findBySeatNo(Object seatNo);

	public abstract List findByMobile(Object mobile);

	public abstract List findByNtAccount(Object ntAccount);

	public abstract List findBySupervisorId(Object supervisorId);

	public abstract List findBySupervisorName(Object supervisorName);

	public abstract List findByHighestDegree(Object highestDegree);

	public abstract List findAll();

	public abstract TAsiainfoEmployee merge(TAsiainfoEmployee detachedInstance);

	public abstract void attachDirty(TAsiainfoEmployee instance);

	public abstract void attachClean(TAsiainfoEmployee instance);

}