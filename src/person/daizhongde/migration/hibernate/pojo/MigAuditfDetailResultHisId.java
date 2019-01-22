package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditfDetailResultHisId entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfDetailResultHisId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731086880383643397L;
	private String faresMainId;
	private String faresSerial;
	private String faresDryrunId;
	private Integer faresUnpassType;
	private String faresBitmap;
	private String faresUnpassSrc;
	private String faresUnpassDst;
	private Timestamp faresCreatetime;

	// Constructors

	/** default constructor */
	public MigAuditfDetailResultHisId() {
	}

	/** minimal constructor */
	public MigAuditfDetailResultHisId(Integer faresUnpassType,
			String faresBitmap, Timestamp faresCreatetime) {
		this.faresUnpassType = faresUnpassType;
		this.faresBitmap = faresBitmap;
		this.faresCreatetime = faresCreatetime;
	}

	/** full constructor */
	public MigAuditfDetailResultHisId(String faresMainId, String faresSerial,
			String faresDryrunId, Integer faresUnpassType, String faresBitmap,
			String faresUnpassSrc, String faresUnpassDst,
			Timestamp faresCreatetime) {
		this.faresMainId = faresMainId;
		this.faresSerial = faresSerial;
		this.faresDryrunId = faresDryrunId;
		this.faresUnpassType = faresUnpassType;
		this.faresBitmap = faresBitmap;
		this.faresUnpassSrc = faresUnpassSrc;
		this.faresUnpassDst = faresUnpassDst;
		this.faresCreatetime = faresCreatetime;
	}

	// Property accessors

	public String getFaresMainId() {
		return this.faresMainId;
	}

	public void setFaresMainId(String faresMainId) {
		this.faresMainId = faresMainId;
	}

	public String getFaresSerial() {
		return this.faresSerial;
	}

	public void setFaresSerial(String faresSerial) {
		this.faresSerial = faresSerial;
	}

	public String getFaresDryrunId() {
		return this.faresDryrunId;
	}

	public void setFaresDryrunId(String faresDryrunId) {
		this.faresDryrunId = faresDryrunId;
	}

	public Integer getFaresUnpassType() {
		return this.faresUnpassType;
	}

	public void setFaresUnpassType(Integer faresUnpassType) {
		this.faresUnpassType = faresUnpassType;
	}

	public String getFaresBitmap() {
		return this.faresBitmap;
	}

	public void setFaresBitmap(String faresBitmap) {
		this.faresBitmap = faresBitmap;
	}

	public String getFaresUnpassSrc() {
		return this.faresUnpassSrc;
	}

	public void setFaresUnpassSrc(String faresUnpassSrc) {
		this.faresUnpassSrc = faresUnpassSrc;
	}

	public String getFaresUnpassDst() {
		return this.faresUnpassDst;
	}

	public void setFaresUnpassDst(String faresUnpassDst) {
		this.faresUnpassDst = faresUnpassDst;
	}

	public Timestamp getFaresCreatetime() {
		return this.faresCreatetime;
	}

	public void setFaresCreatetime(Timestamp faresCreatetime) {
		this.faresCreatetime = faresCreatetime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigAuditfDetailResultHisId))
			return false;
		MigAuditfDetailResultHisId castOther = (MigAuditfDetailResultHisId) other;

		return ((this.getFaresMainId() == castOther.getFaresMainId()) || (this
				.getFaresMainId() != null && castOther.getFaresMainId() != null && this
				.getFaresMainId().equals(castOther.getFaresMainId())))
				&& ((this.getFaresSerial() == castOther.getFaresSerial()) || (this
						.getFaresSerial() != null
						&& castOther.getFaresSerial() != null && this
						.getFaresSerial().equals(castOther.getFaresSerial())))
				&& ((this.getFaresDryrunId() == castOther.getFaresDryrunId()) || (this
						.getFaresDryrunId() != null
						&& castOther.getFaresDryrunId() != null && this
						.getFaresDryrunId()
						.equals(castOther.getFaresDryrunId())))
				&& ((this.getFaresUnpassType() == castOther
						.getFaresUnpassType()) || (this.getFaresUnpassType() != null
						&& castOther.getFaresUnpassType() != null && this
						.getFaresUnpassType().equals(
								castOther.getFaresUnpassType())))
				&& ((this.getFaresBitmap() == castOther.getFaresBitmap()) || (this
						.getFaresBitmap() != null
						&& castOther.getFaresBitmap() != null && this
						.getFaresBitmap().equals(castOther.getFaresBitmap())))
				&& ((this.getFaresUnpassSrc() == castOther.getFaresUnpassSrc()) || (this
						.getFaresUnpassSrc() != null
						&& castOther.getFaresUnpassSrc() != null && this
						.getFaresUnpassSrc().equals(
								castOther.getFaresUnpassSrc())))
				&& ((this.getFaresUnpassDst() == castOther.getFaresUnpassDst()) || (this
						.getFaresUnpassDst() != null
						&& castOther.getFaresUnpassDst() != null && this
						.getFaresUnpassDst().equals(
								castOther.getFaresUnpassDst())))
				&& ((this.getFaresCreatetime() == castOther
						.getFaresCreatetime()) || (this.getFaresCreatetime() != null
						&& castOther.getFaresCreatetime() != null && this
						.getFaresCreatetime().equals(
								castOther.getFaresCreatetime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFaresMainId() == null ? 0 : this.getFaresMainId()
						.hashCode());
		result = 37
				* result
				+ (getFaresSerial() == null ? 0 : this.getFaresSerial()
						.hashCode());
		result = 37
				* result
				+ (getFaresDryrunId() == null ? 0 : this.getFaresDryrunId()
						.hashCode());
		result = 37
				* result
				+ (getFaresUnpassType() == null ? 0 : this.getFaresUnpassType()
						.hashCode());
		result = 37
				* result
				+ (getFaresBitmap() == null ? 0 : this.getFaresBitmap()
						.hashCode());
		result = 37
				* result
				+ (getFaresUnpassSrc() == null ? 0 : this.getFaresUnpassSrc()
						.hashCode());
		result = 37
				* result
				+ (getFaresUnpassDst() == null ? 0 : this.getFaresUnpassDst()
						.hashCode());
		result = 37
				* result
				+ (getFaresCreatetime() == null ? 0 : this.getFaresCreatetime()
						.hashCode());
		return result;
	}

}