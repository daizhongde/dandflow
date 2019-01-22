package person.daizhongde.migration.hibernate.pojo;

/**
 * MigLoadMapId entity. @author MyEclipse Persistence Tools
 */

public class MigLoadMapId implements java.io.Serializable {

	// Fields

	private String tname;
	private String htype;
	private String srcColumn;
	private String tagColumn;
	private String defaultValue;
	private String SSql;

	// Constructors

	/** default constructor */
	public MigLoadMapId() {
	}

	/** full constructor */
	public MigLoadMapId(String tname, String htype, String srcColumn,
			String tagColumn, String defaultValue, String SSql) {
		this.tname = tname;
		this.htype = htype;
		this.srcColumn = srcColumn;
		this.tagColumn = tagColumn;
		this.defaultValue = defaultValue;
		this.SSql = SSql;
	}

	// Property accessors

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getHtype() {
		return this.htype;
	}

	public void setHtype(String htype) {
		this.htype = htype;
	}

	public String getSrcColumn() {
		return this.srcColumn;
	}

	public void setSrcColumn(String srcColumn) {
		this.srcColumn = srcColumn;
	}

	public String getTagColumn() {
		return this.tagColumn;
	}

	public void setTagColumn(String tagColumn) {
		this.tagColumn = tagColumn;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getSSql() {
		return this.SSql;
	}

	public void setSSql(String SSql) {
		this.SSql = SSql;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigLoadMapId))
			return false;
		MigLoadMapId castOther = (MigLoadMapId) other;

		return ((this.getTname() == castOther.getTname()) || (this.getTname() != null
				&& castOther.getTname() != null && this.getTname().equals(
				castOther.getTname())))
				&& ((this.getHtype() == castOther.getHtype()) || (this
						.getHtype() != null && castOther.getHtype() != null && this
						.getHtype().equals(castOther.getHtype())))
				&& ((this.getSrcColumn() == castOther.getSrcColumn()) || (this
						.getSrcColumn() != null
						&& castOther.getSrcColumn() != null && this
						.getSrcColumn().equals(castOther.getSrcColumn())))
				&& ((this.getTagColumn() == castOther.getTagColumn()) || (this
						.getTagColumn() != null
						&& castOther.getTagColumn() != null && this
						.getTagColumn().equals(castOther.getTagColumn())))
				&& ((this.getDefaultValue() == castOther.getDefaultValue()) || (this
						.getDefaultValue() != null
						&& castOther.getDefaultValue() != null && this
						.getDefaultValue().equals(castOther.getDefaultValue())))
				&& ((this.getSSql() == castOther.getSSql()) || (this.getSSql() != null
						&& castOther.getSSql() != null && this.getSSql()
						.equals(castOther.getSSql())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTname() == null ? 0 : this.getTname().hashCode());
		result = 37 * result
				+ (getHtype() == null ? 0 : this.getHtype().hashCode());
		result = 37 * result
				+ (getSrcColumn() == null ? 0 : this.getSrcColumn().hashCode());
		result = 37 * result
				+ (getTagColumn() == null ? 0 : this.getTagColumn().hashCode());
		result = 37
				* result
				+ (getDefaultValue() == null ? 0 : this.getDefaultValue()
						.hashCode());
		result = 37 * result
				+ (getSSql() == null ? 0 : this.getSSql().hashCode());
		return result;
	}

}