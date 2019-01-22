package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditfValueMapId entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfValueMapId implements java.io.Serializable {

	// Fields

	private String fauditSrctableName;
	private String fauditSrcField;
	private String fauditFieldSrcvalue;
	private String fauditDsttableName;
	private String fauditDstField;
	private String fauditFieldDstvalue;

	// Constructors

	/** default constructor */
	public MigAuditfValueMapId() {
	}

	/** full constructor */
	public MigAuditfValueMapId(String fauditSrctableName,
			String fauditSrcField, String fauditFieldSrcvalue,
			String fauditDsttableName, String fauditDstField,
			String fauditFieldDstvalue) {
		this.fauditSrctableName = fauditSrctableName;
		this.fauditSrcField = fauditSrcField;
		this.fauditFieldSrcvalue = fauditFieldSrcvalue;
		this.fauditDsttableName = fauditDsttableName;
		this.fauditDstField = fauditDstField;
		this.fauditFieldDstvalue = fauditFieldDstvalue;
	}

	// Property accessors

	public String getFauditSrctableName() {
		return this.fauditSrctableName;
	}

	public void setFauditSrctableName(String fauditSrctableName) {
		this.fauditSrctableName = fauditSrctableName;
	}

	public String getFauditSrcField() {
		return this.fauditSrcField;
	}

	public void setFauditSrcField(String fauditSrcField) {
		this.fauditSrcField = fauditSrcField;
	}

	public String getFauditFieldSrcvalue() {
		return this.fauditFieldSrcvalue;
	}

	public void setFauditFieldSrcvalue(String fauditFieldSrcvalue) {
		this.fauditFieldSrcvalue = fauditFieldSrcvalue;
	}

	public String getFauditDsttableName() {
		return this.fauditDsttableName;
	}

	public void setFauditDsttableName(String fauditDsttableName) {
		this.fauditDsttableName = fauditDsttableName;
	}

	public String getFauditDstField() {
		return this.fauditDstField;
	}

	public void setFauditDstField(String fauditDstField) {
		this.fauditDstField = fauditDstField;
	}

	public String getFauditFieldDstvalue() {
		return this.fauditFieldDstvalue;
	}

	public void setFauditFieldDstvalue(String fauditFieldDstvalue) {
		this.fauditFieldDstvalue = fauditFieldDstvalue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigAuditfValueMapId))
			return false;
		MigAuditfValueMapId castOther = (MigAuditfValueMapId) other;

		return ((this.getFauditSrctableName() == castOther
				.getFauditSrctableName()) || (this.getFauditSrctableName() != null
				&& castOther.getFauditSrctableName() != null && this
				.getFauditSrctableName().equals(
						castOther.getFauditSrctableName())))
				&& ((this.getFauditSrcField() == castOther.getFauditSrcField()) || (this
						.getFauditSrcField() != null
						&& castOther.getFauditSrcField() != null && this
						.getFauditSrcField().equals(
								castOther.getFauditSrcField())))
				&& ((this.getFauditFieldSrcvalue() == castOther
						.getFauditFieldSrcvalue()) || (this
						.getFauditFieldSrcvalue() != null
						&& castOther.getFauditFieldSrcvalue() != null && this
						.getFauditFieldSrcvalue().equals(
								castOther.getFauditFieldSrcvalue())))
				&& ((this.getFauditDsttableName() == castOther
						.getFauditDsttableName()) || (this
						.getFauditDsttableName() != null
						&& castOther.getFauditDsttableName() != null && this
						.getFauditDsttableName().equals(
								castOther.getFauditDsttableName())))
				&& ((this.getFauditDstField() == castOther.getFauditDstField()) || (this
						.getFauditDstField() != null
						&& castOther.getFauditDstField() != null && this
						.getFauditDstField().equals(
								castOther.getFauditDstField())))
				&& ((this.getFauditFieldDstvalue() == castOther
						.getFauditFieldDstvalue()) || (this
						.getFauditFieldDstvalue() != null
						&& castOther.getFauditFieldDstvalue() != null && this
						.getFauditFieldDstvalue().equals(
								castOther.getFauditFieldDstvalue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFauditSrctableName() == null ? 0 : this
						.getFauditSrctableName().hashCode());
		result = 37
				* result
				+ (getFauditSrcField() == null ? 0 : this.getFauditSrcField()
						.hashCode());
		result = 37
				* result
				+ (getFauditFieldSrcvalue() == null ? 0 : this
						.getFauditFieldSrcvalue().hashCode());
		result = 37
				* result
				+ (getFauditDsttableName() == null ? 0 : this
						.getFauditDsttableName().hashCode());
		result = 37
				* result
				+ (getFauditDstField() == null ? 0 : this.getFauditDstField()
						.hashCode());
		result = 37
				* result
				+ (getFauditFieldDstvalue() == null ? 0 : this
						.getFauditFieldDstvalue().hashCode());
		return result;
	}

}