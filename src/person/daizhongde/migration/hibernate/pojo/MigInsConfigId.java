package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigInsConfigId entity. @author MyEclipse Persistence Tools
 */

public class MigInsConfigId implements java.io.Serializable {

	// Fields

	private String migTaskId;
	private String jobInsId;
	private String migSrc;
	private String migSrcConn;
	private String migWhere;
	private String migDst;
	private String migDstConn;
	private String migAuthor;
	private String migDesc;
	private Timestamp migModifytime;
	private Integer migStatus;

	// Constructors

	/** default constructor */
	public MigInsConfigId() {
	}

	/** minimal constructor */
	public MigInsConfigId(String migTaskId, String jobInsId, String migAuthor,
			Timestamp migModifytime, Integer migStatus) {
		this.migTaskId = migTaskId;
		this.jobInsId = jobInsId;
		this.migAuthor = migAuthor;
		this.migModifytime = migModifytime;
		this.migStatus = migStatus;
	}

	/** full constructor */
	public MigInsConfigId(String migTaskId, String jobInsId, String migSrc,
			String migSrcConn, String migWhere, String migDst,
			String migDstConn, String migAuthor, String migDesc,
			Timestamp migModifytime, Integer migStatus) {
		this.migTaskId = migTaskId;
		this.jobInsId = jobInsId;
		this.migSrc = migSrc;
		this.migSrcConn = migSrcConn;
		this.migWhere = migWhere;
		this.migDst = migDst;
		this.migDstConn = migDstConn;
		this.migAuthor = migAuthor;
		this.migDesc = migDesc;
		this.migModifytime = migModifytime;
		this.migStatus = migStatus;
	}

	// Property accessors

	public String getMigTaskId() {
		return this.migTaskId;
	}

	public void setMigTaskId(String migTaskId) {
		this.migTaskId = migTaskId;
	}

	public String getJobInsId() {
		return this.jobInsId;
	}

	public void setJobInsId(String jobInsId) {
		this.jobInsId = jobInsId;
	}

	public String getMigSrc() {
		return this.migSrc;
	}

	public void setMigSrc(String migSrc) {
		this.migSrc = migSrc;
	}

	public String getMigSrcConn() {
		return this.migSrcConn;
	}

	public void setMigSrcConn(String migSrcConn) {
		this.migSrcConn = migSrcConn;
	}

	public String getMigWhere() {
		return this.migWhere;
	}

	public void setMigWhere(String migWhere) {
		this.migWhere = migWhere;
	}

	public String getMigDst() {
		return this.migDst;
	}

	public void setMigDst(String migDst) {
		this.migDst = migDst;
	}

	public String getMigDstConn() {
		return this.migDstConn;
	}

	public void setMigDstConn(String migDstConn) {
		this.migDstConn = migDstConn;
	}

	public String getMigAuthor() {
		return this.migAuthor;
	}

	public void setMigAuthor(String migAuthor) {
		this.migAuthor = migAuthor;
	}

	public String getMigDesc() {
		return this.migDesc;
	}

	public void setMigDesc(String migDesc) {
		this.migDesc = migDesc;
	}

	public Timestamp getMigModifytime() {
		return this.migModifytime;
	}

	public void setMigModifytime(Timestamp migModifytime) {
		this.migModifytime = migModifytime;
	}

	public Integer getMigStatus() {
		return this.migStatus;
	}

	public void setMigStatus(Integer migStatus) {
		this.migStatus = migStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigInsConfigId))
			return false;
		MigInsConfigId castOther = (MigInsConfigId) other;

		return ((this.getMigTaskId() == castOther.getMigTaskId()) || (this
				.getMigTaskId() != null && castOther.getMigTaskId() != null && this
				.getMigTaskId().equals(castOther.getMigTaskId())))
				&& ((this.getJobInsId() == castOther.getJobInsId()) || (this
						.getJobInsId() != null
						&& castOther.getJobInsId() != null && this
						.getJobInsId().equals(castOther.getJobInsId())))
				&& ((this.getMigSrc() == castOther.getMigSrc()) || (this
						.getMigSrc() != null && castOther.getMigSrc() != null && this
						.getMigSrc().equals(castOther.getMigSrc())))
				&& ((this.getMigSrcConn() == castOther.getMigSrcConn()) || (this
						.getMigSrcConn() != null
						&& castOther.getMigSrcConn() != null && this
						.getMigSrcConn().equals(castOther.getMigSrcConn())))
				&& ((this.getMigWhere() == castOther.getMigWhere()) || (this
						.getMigWhere() != null
						&& castOther.getMigWhere() != null && this
						.getMigWhere().equals(castOther.getMigWhere())))
				&& ((this.getMigDst() == castOther.getMigDst()) || (this
						.getMigDst() != null && castOther.getMigDst() != null && this
						.getMigDst().equals(castOther.getMigDst())))
				&& ((this.getMigDstConn() == castOther.getMigDstConn()) || (this
						.getMigDstConn() != null
						&& castOther.getMigDstConn() != null && this
						.getMigDstConn().equals(castOther.getMigDstConn())))
				&& ((this.getMigAuthor() == castOther.getMigAuthor()) || (this
						.getMigAuthor() != null
						&& castOther.getMigAuthor() != null && this
						.getMigAuthor().equals(castOther.getMigAuthor())))
				&& ((this.getMigDesc() == castOther.getMigDesc()) || (this
						.getMigDesc() != null && castOther.getMigDesc() != null && this
						.getMigDesc().equals(castOther.getMigDesc())))
				&& ((this.getMigModifytime() == castOther.getMigModifytime()) || (this
						.getMigModifytime() != null
						&& castOther.getMigModifytime() != null && this
						.getMigModifytime()
						.equals(castOther.getMigModifytime())))
				&& ((this.getMigStatus() == castOther.getMigStatus()) || (this
						.getMigStatus() != null
						&& castOther.getMigStatus() != null && this
						.getMigStatus().equals(castOther.getMigStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMigTaskId() == null ? 0 : this.getMigTaskId().hashCode());
		result = 37 * result
				+ (getJobInsId() == null ? 0 : this.getJobInsId().hashCode());
		result = 37 * result
				+ (getMigSrc() == null ? 0 : this.getMigSrc().hashCode());
		result = 37
				* result
				+ (getMigSrcConn() == null ? 0 : this.getMigSrcConn()
						.hashCode());
		result = 37 * result
				+ (getMigWhere() == null ? 0 : this.getMigWhere().hashCode());
		result = 37 * result
				+ (getMigDst() == null ? 0 : this.getMigDst().hashCode());
		result = 37
				* result
				+ (getMigDstConn() == null ? 0 : this.getMigDstConn()
						.hashCode());
		result = 37 * result
				+ (getMigAuthor() == null ? 0 : this.getMigAuthor().hashCode());
		result = 37 * result
				+ (getMigDesc() == null ? 0 : this.getMigDesc().hashCode());
		result = 37
				* result
				+ (getMigModifytime() == null ? 0 : this.getMigModifytime()
						.hashCode());
		result = 37 * result
				+ (getMigStatus() == null ? 0 : this.getMigStatus().hashCode());
		return result;
	}

}