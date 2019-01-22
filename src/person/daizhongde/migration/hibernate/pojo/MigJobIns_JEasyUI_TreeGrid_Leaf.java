package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;


/**
 * TAuthorityModule entity.
 * leaf node entity
 * id,text,url,note
 * @author MyEclipse Persistence Tools
 */
public class MigJobIns_JEasyUI_TreeGrid_Leaf implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5490135395716545037L;
	private String id;
	private String text;//name
	private String iconCls;
		
	private String note;//remark
	
	private int dryrunId;
	private String type;// instance type ,process is control type
	private String status;
	private String author;
	private Timestamp beginTime;
	private Timestamp endTime;
	private String totalTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getDryrunId() {
		return dryrunId;
	}
	public void setDryrunId(int dryrunId) {
		this.dryrunId = dryrunId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getTotalTime() {
		return this.totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dryrunId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MigJobIns_JEasyUI_TreeGrid_Leaf other = (MigJobIns_JEasyUI_TreeGrid_Leaf) obj;
		if (dryrunId != other.dryrunId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
