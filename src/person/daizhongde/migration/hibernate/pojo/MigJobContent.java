package person.daizhongde.migration.hibernate.pojo;

/**
 * MigJobContent entity. @author MyEclipse Persistence Tools
 */

public class MigJobContent implements java.io.Serializable {

	// Fields

	private MigJobContentId id;
	private Integer isleaf;
	private String nodeStatus;
	private String prepos;
	private String postpos;
	private String coords;

	private transient MigTaskInfo task;
	private transient MigJobInfo job;
	
	private transient Coords zb;
	
	// Constructors

	/** default constructor */
	public MigJobContent() {
	}

	/** minimal constructor */
	public MigJobContent(MigJobContentId id, Integer isleaf) {
		this.id = id;
		this.isleaf = isleaf;
	}

	/** full constructor */
	public MigJobContent(MigJobContentId id, Integer isleaf, String nodeStatus,
			String prepos, String postpos, String coords) {
		this.id = id;
		this.isleaf = isleaf;
		this.nodeStatus = nodeStatus;
		this.prepos = prepos;
		this.postpos = postpos;
		this.coords = coords;
	}

	// Property accessors

	public MigJobContentId getId() {
		return this.id;
	}

	public void setId(MigJobContentId id) {
		this.id = id;
	}

	public Integer getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}

	public String getNodeStatus() {
		return this.nodeStatus;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public String getPrepos() {
		return this.prepos;
	}

	public void setPrepos(String prepos) {
		this.prepos = prepos;
	}

	public String getPostpos() {
		return this.postpos;
	}

	public void setPostpos(String postpos) {
		this.postpos = postpos;
	}

	public String getCoords() {
		return this.coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	public MigTaskInfo getTask() {
		return task;
	}

	public void setTask(MigTaskInfo task) {
		this.task = task;
	}

	public MigJobInfo getJob() {
		return job;
	}

	public void setJob(MigJobInfo job) {
		this.job = job;
	}
	public Coords getZb() {
		String[] s = this.coords.split("\\,");
		return new Coords(
				Integer.valueOf(s[0]).intValue(), Integer.valueOf(s[1]).intValue()
				);
	}

	public void setZb(Coords zb) {
		this.zb = zb;
	}
}