
package person.daizhongde.migration.spring.service.wsclient.cominterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OUTDATAFILECONFIG complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OUTDATAFILECONFIG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="task-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-ins-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dryrun-id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="delimiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="host-ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="host-conn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="output-path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parallel-num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCheck" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="com-para" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="business" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isClearFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OUTDATAFILECONFIG", propOrder = {
    "taskId",
    "jobId",
    "jobInsId",
    "dryrunId",
    "delimiter",
    "hostIp",
    "hostConn",
    "outputPath",
    "parallelNum",
    "group",
    "isCheck",
    "comPara",
    "business",
    "isClearFile"
})
public class OUTDATAFILECONFIG {

    @XmlElementRef(name = "task-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> taskId;
    @XmlElementRef(name = "job-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jobId;
    @XmlElementRef(name = "job-ins-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jobInsId;
    @XmlElement(name = "dryrun-id")
    protected int dryrunId;
    @XmlElementRef(name = "delimiter", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delimiter;
    @XmlElementRef(name = "host-ip", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hostIp;
    @XmlElementRef(name = "host-conn", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hostConn;
    @XmlElementRef(name = "output-path", type = JAXBElement.class, required = false)
    protected JAXBElement<String> outputPath;
    @XmlElementRef(name = "parallel-num", type = JAXBElement.class, required = false)
    protected JAXBElement<String> parallelNum;
    @XmlElementRef(name = "group", type = JAXBElement.class, required = false)
    protected JAXBElement<String> group;
    @XmlElementRef(name = "isCheck", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isCheck;
    @XmlElementRef(name = "com-para", type = JAXBElement.class, required = false)
    protected JAXBElement<String> comPara;
    @XmlElementRef(name = "business", type = JAXBElement.class, required = false)
    protected JAXBElement<String> business;
    @XmlElementRef(name = "isClearFile", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isClearFile;

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
     * 获取delimiter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelimiter() {
        return delimiter;
    }

    /**
     * 设置delimiter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelimiter(JAXBElement<String> value) {
        this.delimiter = value;
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
     * 获取outputPath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOutputPath() {
        return outputPath;
    }

    /**
     * 设置outputPath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOutputPath(JAXBElement<String> value) {
        this.outputPath = value;
    }

    /**
     * 获取parallelNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParallelNum() {
        return parallelNum;
    }

    /**
     * 设置parallelNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParallelNum(JAXBElement<String> value) {
        this.parallelNum = value;
    }

    /**
     * 获取group属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroup() {
        return group;
    }

    /**
     * 设置group属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroup(JAXBElement<String> value) {
        this.group = value;
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

    /**
     * 获取business属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBusiness() {
        return business;
    }

    /**
     * 设置business属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBusiness(JAXBElement<String> value) {
        this.business = value;
    }

    /**
     * 获取isClearFile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsClearFile() {
        return isClearFile;
    }

    /**
     * 设置isClearFile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsClearFile(JAXBElement<String> value) {
        this.isClearFile = value;
    }

}
