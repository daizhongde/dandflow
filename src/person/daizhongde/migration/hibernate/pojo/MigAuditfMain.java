package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditfMain entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfMain implements java.io.Serializable {

	// Fields

	private Integer fauditId;
	private Integer domain;
	private String fauditName;
	private String fauditSrctableName;
	private String fauditSrctableConn;
	private String fauditDsttableName;
	private String fauditDsttableConn;
	private String author;
	private String fauditDesc;
	private Timestamp fauditCreatetime;

	// Constructors

	/** default constructor */
	public MigAuditfMain() {
	}

	/** minimal constructor */
	public MigAuditfMain(Integer domain, String fauditName,
			String fauditSrctableName, String fauditSrctableConn,
			String fauditDsttableName, String fauditDsttableConn,
			String author, Timestamp fauditCreatetime) {
		this.domain = domain;
		this.fauditName = fauditName;
		this.fauditSrctableName = fauditSrctableName;
		this.fauditSrctableConn = fauditSrctableConn;
		this.fauditDsttableName = fauditDsttableName;
		this.fauditDsttableConn = fauditDsttableConn;
		this.author = author;
		this.fauditCreatetime = fauditCreatetime;
	}

	/** full constructor */
	public MigAuditfMain(Integer domain, String fauditName,
			String fauditSrctableName, String fauditSrctableConn,
			String fauditDsttableName, String fauditDsttableConn,
			String author, String fauditDesc, Timestamp fauditCreatetime) {
		this.domain = domain;
		this.fauditName = fauditName;
		this.fauditSrctableName = fauditSrctableName;
		this.fauditSrctableConn = fauditSrctableConn;
		this.fauditDsttableName = fauditDsttableName;
		this.fauditDsttableConn = fauditDsttableConn;
		this.author = author;
		this.fauditDesc = fauditDesc;
		this.fauditCreatetime = fauditCreatetime;
	}

	// Property accessors

	public Integer getFauditId() {
		return this.fauditId;
	}

	public void setFauditId(Integer fauditId) {
		this.fauditId = fauditId;
	}

	public Integer getDomain() {
		return this.domain;
	}

	public void setDomain(Integer domain) {
		this.domain = domain;
	}

	public String getFauditName() {
		return this.fauditName;
	}

	public void setFauditName(String fauditName) {
		this.fauditName = fauditName;
	}

	public String getFauditSrctableName() {
		return this.fauditSrctableName;
	}

	public void setFauditSrctableName(String fauditSrctableName) {
		this.fauditSrctableName = fauditSrctableName;
	}

	public String getFauditSrctableConn() {
		return this.fauditSrctableConn;
	}

	public void setFauditSrctableConn(String fauditSrctableConn) {
		this.fauditSrctableConn = fauditSrctableConn;
	}

	public String getFauditDsttableName() {
		return this.fauditDsttableName;
	}

	public void setFauditDsttableName(String fauditDsttableName) {
		this.fauditDsttableName = fauditDsttableName;
	}

	public String getFauditDsttableConn() {
		return this.fauditDsttableConn;
	}

	public void setFauditDsttableConn(String fauditDsttableConn) {
		this.fauditDsttableConn = fauditDsttableConn;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFauditDesc() {
		return this.fauditDesc;
	}

	public void setFauditDesc(String fauditDesc) {
		this.fauditDesc = fauditDesc;
	}

	public Timestamp getFauditCreatetime() {
		return this.fauditCreatetime;
	}

	public void setFauditCreatetime(Timestamp fauditCreatetime) {
		this.fauditCreatetime = fauditCreatetime;
	}

}