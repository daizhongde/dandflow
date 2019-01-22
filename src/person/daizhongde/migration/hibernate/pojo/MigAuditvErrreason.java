package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditvErrreason entity. @author MyEclipse Persistence Tools
 */

public class MigAuditvErrreason implements java.io.Serializable {

	// Fields

	private MigAuditvErrreasonId id;
	private String dmpNo;
	private String reason;

	// Constructors

	/** default constructor */
	public MigAuditvErrreason() {
	}

	/** minimal constructor */
	public MigAuditvErrreason(MigAuditvErrreasonId id) {
		this.id = id;
	}

	/** full constructor */
	public MigAuditvErrreason(MigAuditvErrreasonId id, String dmpNo,
			String reason) {
		this.id = id;
		this.dmpNo = dmpNo;
		this.reason = reason;
	}

	// Property accessors

	public MigAuditvErrreasonId getId() {
		return this.id;
	}

	public void setId(MigAuditvErrreasonId id) {
		this.id = id;
	}

	public String getDmpNo() {
		return this.dmpNo;
	}

	public void setDmpNo(String dmpNo) {
		this.dmpNo = dmpNo;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}