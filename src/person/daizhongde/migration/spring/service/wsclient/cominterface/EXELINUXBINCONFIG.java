
package person.daizhongde.migration.spring.service.wsclient.cominterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EXELINUXBINCONFIG complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EXELINUXBINCONFIG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="task-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-ins-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dryrun-id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="host-ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="host-conn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="command" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iswait" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCheck" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="com-para" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EXELINUXBINCONFIG", propOrder = {
    "taskId",
    "jobId",
    "jobInsId",
    "dryrunId",
    "hostIp",
    "hostConn",
    "command",
    "iswait",
    "isCheck",
    "comPara"
})
public class EXELINUXBINCONFIG {

    @XmlElementRef(name = "task-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> taskId;
    @XmlElementRef(name = "job-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jobId;
    @XmlElementRef(name = "job-ins-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jobInsId;
    @XmlElement(name = "dryrun-id")
    protected int dryrunId;
    @XmlElementRef(name = "host-ip", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hostIp;
    @XmlElementRef(name = "host-conn", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hostConn;
    @XmlElementRef(name = "command", type = JAXBElement.class, required = false)
    protected JAXBElement<String> command;
    @XmlElementRef(name = "iswait", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iswait;
    @XmlElementRef(name = "isCheck", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isCheck;
    @XmlElementRef(name = "com-para", type = JAXBElement.class, required = false)
    protected JAXBElement<String> comPara;

    /**
     * 获取taskId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTaskId() {
        return taskId;
    }

    /**
     * 设置taskId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTaskId(JAXBElement<String> value) {
        this.taskId = value;
    }

    /**
     * 获取jobId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJobId() {
        return jobId;
    }

    /**
     * 设置jobId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJobId(JAXBElement<String> value) {
        this.jobId = value;
    }

    /**
     * 获取jobInsId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJobInsId() {
        return jobInsId;
    }

    /**
     * 设置jobInsId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJobInsId(JAXBElement<String> value) {
        this.jobInsId = value;
    }

    /**
     * 获取dryrunId属性的值。
     * 
     */
    public int getDryrunId() {
        return dryrunId;
    }

    /**
     * 设置dryrunId属性的值。
     * 
     */
    public void setDryrunId(int value) {
        this.dryrunId = value;
    }

    /**
     * 获取hostIp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHostIp() {
        return hostIp;
    }

    /**
     * 设置hostIp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHostIp(JAXBElement<String> value) {
        this.hostIp = value;
    }

    /**
     * 获取hostConn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHostConn() {
        return hostConn;
    }

    /**
     * 设置hostConn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHostConn(JAXBElement<String> value) {
        this.hostConn = value;
    }

    /**
     * 获取command属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCommand() {
        return command;
    }

    /**
     * 设置command属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCommand(JAXBElement<String> value) {
        this.command = value;
    }

    /**
     * 获取iswait属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIswait() {
        return iswait;
    }

    /**
     * 设置iswait属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIswait(JAXBElement<String> value) {
        this.iswait = value;
    }

    /**
     * 获取isCheck属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsCheck() {
        return isCheck;
    }

    /**
     * 设置isCheck属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsCheck(JAXBElement<String> value) {
        this.isCheck = value;
    }

    /**
     * 获取comPara属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getComPara() {
        return comPara;
    }

    /**
     * 设置comPara属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setComPara(JAXBElement<String> value) {
        this.comPara = value;
    }

}
