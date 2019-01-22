package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditcReportDetailResult entity. @author MyEclipse Persistence Tools
 */

public class MigAuditcReportDetailResult implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer entity;
	private String auditItem;
	private String enumDesc;
	private String splitFlag;
	private String srcEnum;
	private String dstEnum;
	private Integer srcCount;
	private Integer dstCount;
	private String auditAuthor;
	private String faresDryrunId;
	private String minAnalysis;
	private String minPer;

	// Constructors

	/** default constructor */
	public MigAuditcReportDetailResult() {
	}

	/** minimal constructor */
	public MigAuditcReportDetailResult(Integer entity) {
		this.entity = entity;
	}

	/** full constructor */
	public MigAuditcReportDetailResult(Integer entity, String auditItem,
			String enumDesc, String splitFlag, String srcEnum, String dstEnum,
			Integer srcCount, Integer dstCount, String auditAuthor,
			String faresDryrunId, String minAnalysis, String minPer) {
		this.entity = entity;
		this.auditItem = auditItem;
		this.enumDesc = enumDesc;
		this.splitFlag = splitFlag;
		this.srcEnum = srcEnum;
		this.dstEnum = dstEnum;
		this.srcCount = srcCount;
		this.dstCount = dstCount;
		this.auditAuthor = auditAuthor;
		this.faresDryrunId = faresDryrunId;
		this.minAnalysis = minAnalysis;
		this.minPer = minPer;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEntity() {
		return this.entity;
	}

	public void setEntity(Integer entity) {
		this.entity = entity;
	}

	public String getAuditItem() {
		return this.auditItem;
	}

	public void setAuditItem(String auditItem) {
		this.auditItem = auditItem;
	}

	public String getEnumDesc() {
		return this.enumDesc;
	}

	public void setEnumDesc(String enumDesc) {
		this.enumDesc = enumDesc;
	}

	public String getSplitFlag() {
		return this.splitFlag;
	}

	public void setSplitFlag(String splitFlag) {
		this.splitFlag = splitFlag;
	}

	public String getSrcEnum() {
		return this.srcEnum;
	}

	public void setSrcEnum(String srcEnum) {
		this.srcEnum = srcEnum;
	}

	public String getDstEnum() {
		return this.dstEnum;
	}

	public void setDstEnum(String dstEnum) {
		this.dstEnum = dstEnum;
	}

	public Integer getSrcCount() {
		return this.srcCount;
	}

	public void setSrcCount(Integer srcCount) {
		this.srcCount = srcCount;
	}

	public Integer getDstCount() {
		return this.dstCount;
	}

	public void setDstCount(Integer dstCount) {
		this.dstCount = dstCount;
	}

	public String getAuditAuthor() {
		return this.auditAuthor;
	}

	public void setAuditAuthor(String auditAuthor) {
		this.auditAuthor = auditAuthor;
	}

	public String getFaresDryrunId() {
		return this.faresDryrunId;
	}

	public void setFaresDryrunId(String faresDryrunId) {
		this.faresDryrunId = faresDryrunId;
	}

	public String getMinAnalysis() {
		return this.minAnalysis;
	}

	public void setMinAnalysis(String minAnalysis) {
		this.minAnalysis = minAnalysis;
	}

	public String getMinPer() {
		return this.minPer;
	}

	public void setMinPer(String minPer) {
		this.minPer = minPer;
	}

}