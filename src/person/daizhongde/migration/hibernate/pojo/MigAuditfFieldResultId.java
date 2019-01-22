package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditfFieldResultId entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfFieldResultId implements java.io.Serializable {

	// Fields

	private String mainId;
	private String dryrunId;
	private String faresSerial;
	private Integer domain;
	private Integer fieldIndex;
	private String srcTableName;
	private String dstTableName;
	private String srcFieldName;
	private String dstFieldName;
	private Integer unmatchCnt;
	private Float unmatchRate;
	private Float currentContentRate;
	private Float currentResultRate;
	private Float effectContentRate;
	private Float effectResultRate;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public MigAuditfFieldResultId() {
	}

	/** minimal constructor */
	public MigAuditfFieldResultId(Timestamp createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public MigAuditfFieldResultId(String mainId, String dryrunId,
			String faresSerial, Integer domain, Integer fieldIndex,
			String srcTableName, String dstTableName, String srcFieldName,
			String dstFieldName, Integer unmatchCnt, Float unmatchRate,
			Float currentContentRate, Float currentResultRate,
			Float effectContentRate, Float effectResultRate,
			Timestamp createTime) {
		this.mainId = mainId;
		this.dryrunId = dryrunId;
		this.faresSerial = faresSerial;
		this.domain = domain;
		this.fieldIndex = fieldIndex;
		this.srcTableName = srcTableName;
		this.dstTableName = dstTableName;
		this.srcFieldName = srcFieldName;
		this.dstFieldName = dstFieldName;
		this.unmatchCnt = unmatchCnt;
		this.unmatchRate = unmatchRate;
		this.currentContentRate = currentContentRate;
		this.currentResultRate = currentResultRate;
		this.effectContentRate = effectContentRate;
		this.effectResultRate = effectResultRate;
		this.createTime = createTime;
	}

	// Property accessors

	public String getMainId() {
		return this.mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getDryrunId() {
		return this.dryrunId;
	}

	public void setDryrunId(String dryrunId) {
		this.dryrunId = dryrunId;
	}

	public String getFaresSerial() {
		return this.faresSerial;
	}

	public void setFaresSerial(String faresSerial) {
		this.faresSerial = faresSerial;
	}

	public Integer getDomain() {
		return this.domain;
	}

	public void setDomain(Integer domain) {
		this.domain = domain;
	}

	public Integer getFieldIndex() {
		return this.fieldIndex;
	}

	public void setFieldIndex(Integer fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	public String getSrcTableName() {
		return this.srcTableName;
	}

	public void setSrcTableName(String srcTableName) {
		this.srcTableName = srcTableName;
	}

	public String getDstTableName() {
		return this.dstTableName;
	}

	public void setDstTableName(String dstTableName) {
		this.dstTableName = dstTableName;
	}

	public String getSrcFieldName() {
		return this.srcFieldName;
	}

	public void setSrcFieldName(String srcFieldName) {
		this.srcFieldName = srcFieldName;
	}

	public String getDstFieldName() {
		return this.dstFieldName;
	}

	public void setDstFieldName(String dstFieldName) {
		this.dstFieldName = dstFieldName;
	}

	public Integer getUnmatchCnt() {
		return this.unmatchCnt;
	}

	public void setUnmatchCnt(Integer unmatchCnt) {
		this.unmatchCnt = unmatchCnt;
	}

	public Float getUnmatchRate() {
		return this.unmatchRate;
	}

	public void setUnmatchRate(Float unmatchRate) {
		this.unmatchRate = unmatchRate;
	}

	public Float getCurrentContentRate() {
		return this.currentContentRate;
	}

	public void setCurrentContentRate(Float currentContentRate) {
		this.currentContentRate = currentContentRate;
	}

	public Float getCurrentResultRate() {
		return this.currentResultRate;
	}

	public void setCurrentResultRate(Float currentResultRate) {
		this.currentResultRate = currentResultRate;
	}

	public Float getEffectContentRate() {
		return this.effectContentRate;
	}

	public void setEffectContentRate(Float effectContentRate) {
		this.effectContentRate = effectContentRate;
	}

	public Float getEffectResultRate() {
		return this.effectResultRate;
	}

	public void setEffectResultRate(Float effectResultRate) {
		this.effectResultRate = effectResultRate;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigAuditfFieldResultId))
			return false;
		MigAuditfFieldResultId castOther = (MigAuditfFieldResultId) other;

		return ((this.getMainId() == castOther.getMainId()) || (this
				.getMainId() != null && castOther.getMainId() != null && this
				.getMainId().equals(castOther.getMainId())))
				&& ((this.getDryrunId() == castOther.getDryrunId()) || (this
						.getDryrunId() != null
						&& castOther.getDryrunId() != null && this
						.getDryrunId().equals(castOther.getDryrunId())))
				&& ((this.getFaresSerial() == castOther.getFaresSerial()) || (this
						.getFaresSerial() != null
						&& castOther.getFaresSerial() != null && this
						.getFaresSerial().equals(castOther.getFaresSerial())))
				&& ((this.getDomain() == castOther.getDomain()) || (this
						.getDomain() != null && castOther.getDomain() != null && this
						.getDomain().equals(castOther.getDomain())))
				&& ((this.getFieldIndex() == castOther.getFieldIndex()) || (this
						.getFieldIndex() != null
						&& castOther.getFieldIndex() != null && this
						.getFieldIndex().equals(castOther.getFieldIndex())))
				&& ((this.getSrcTableName() == castOther.getSrcTableName()) || (this
						.getSrcTableName() != null
						&& castOther.getSrcTableName() != null && this
						.getSrcTableName().equals(castOther.getSrcTableName())))
				&& ((this.getDstTableName() == castOther.getDstTableName()) || (this
						.getDstTableName() != null
						&& castOther.getDstTableName() != null && this
						.getDstTableName().equals(castOther.getDstTableName())))
				&& ((this.getSrcFieldName() == castOther.getSrcFieldName()) || (this
						.getSrcFieldName() != null
						&& castOther.getSrcFieldName() != null && this
						.getSrcFieldName().equals(castOther.getSrcFieldName())))
				&& ((this.getDstFieldName() == castOther.getDstFieldName()) || (this
						.getDstFieldName() != null
						&& castOther.getDstFieldName() != null && this
						.getDstFieldName().equals(castOther.getDstFieldName())))
				&& ((this.getUnmatchCnt() == castOther.getUnmatchCnt()) || (this
						.getUnmatchCnt() != null
						&& castOther.getUnmatchCnt() != null && this
						.getUnmatchCnt().equals(castOther.getUnmatchCnt())))
				&& ((this.getUnmatchRate() == castOther.getUnmatchRate()) || (this
						.getUnmatchRate() != null
						&& castOther.getUnmatchRate() != null && this
						.getUnmatchRate().equals(castOther.getUnmatchRate())))
				&& ((this.getCurrentContentRate() == castOther
						.getCurrentContentRate()) || (this
						.getCurrentContentRate() != null
						&& castOther.getCurrentContentRate() != null && this
						.getCurrentContentRate().equals(
								castOther.getCurrentContentRate())))
				&& ((this.getCurrentResultRate() == castOther
						.getCurrentResultRate()) || (this
						.getCurrentResultRate() != null
						&& castOther.getCurrentResultRate() != null && this
						.getCurrentResultRate().equals(
								castOther.getCurrentResultRate())))
				&& ((this.getEffectContentRate() == castOther
						.getEffectContentRate()) || (this
						.getEffectContentRate() != null
						&& castOther.getEffectContentRate() != null && this
						.getEffectContentRate().equals(
								castOther.getEffectContentRate())))
				&& ((this.getEffectResultRate() == castOther
						.getEffectResultRate()) || (this.getEffectResultRate() != null
						&& castOther.getEffectResultRate() != null && this
						.getEffectResultRate().equals(
								castOther.getEffectResultRate())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMainId() == null ? 0 : this.getMainId().hashCode());
		result = 37 * result
				+ (getDryrunId() == null ? 0 : this.getDryrunId().hashCode());
		result = 37
				* result
				+ (getFaresSerial() == null ? 0 : this.getFaresSerial()
						.hashCode());
		result = 37 * result
				+ (getDomain() == null ? 0 : this.getDomain().hashCode());
		result = 37
				* result
				+ (getFieldIndex() == null ? 0 : this.getFieldIndex()
						.hashCode());
		result = 37
				* result
				+ (getSrcTableName() == null ? 0 : this.getSrcTableName()
						.hashCode());
		result = 37
				* result
				+ (getDstTableName() == null ? 0 : this.getDstTableName()
						.hashCode());
		result = 37
				* result
				+ (getSrcFieldName() == null ? 0 : this.getSrcFieldName()
						.hashCode());
		result = 37
				* result
				+ (getDstFieldName() == null ? 0 : this.getDstFieldName()
						.hashCode());
		result = 37
				* result
				+ (getUnmatchCnt() == null ? 0 : this.getUnmatchCnt()
						.hashCode());
		result = 37
				* result
				+ (getUnmatchRate() == null ? 0 : this.getUnmatchRate()
						.hashCode());
		result = 37
				* result
				+ (getCurrentContentRate() == null ? 0 : this
						.getCurrentContentRate().hashCode());
		result = 37
				* result
				+ (getCurrentResultRate() == null ? 0 : this
						.getCurrentResultRate().hashCode());
		result = 37
				* result
				+ (getEffectContentRate() == null ? 0 : this
						.getEffectContentRate().hashCode());
		result = 37
				* result
				+ (getEffectResultRate() == null ? 0 : this
						.getEffectResultRate().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		return result;
	}

}