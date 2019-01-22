package junit.test;

/**
 * TIsoftstoneEmployeeId entity. @author MyEclipse Persistence Tools
 */
public class TIsoftstoneEmployeeId extends AbstractTIsoftstoneEmployeeId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TIsoftstoneEmployeeId() {
	}

	/** full constructor */
	public TIsoftstoneEmployeeId(String employeeNo, String employeeName,
			String enName, String sex, String tel, String mobile, String email,
			String dept, String costCenter, String corpCode, String corpName,
			String position, String rank, String qualLevel, String hwbgLevel) {
		super(employeeNo, employeeName, enName, sex, tel, mobile, email, dept,
				costCenter, corpCode, corpName, position, rank, qualLevel,
				hwbgLevel);
	}

}
