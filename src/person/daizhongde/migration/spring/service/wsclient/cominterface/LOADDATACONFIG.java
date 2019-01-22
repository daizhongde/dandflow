
package person.daizhongde.migration.spring.service.wsclient.cominterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>LOADDATACONFIG complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LOADDATACONFIG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="task-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-ins-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dryrun-id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="delimiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="input-path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deal-path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="success-path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="backup-path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parallel-num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loadfile-over" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isWait" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCheck" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="com-para" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="business" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isTruncateTable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LOADDATACONFIG", propOrder = {
    "taskId",
    "jobId",
    "jobInsId",
    "dryrunId",
    "delimiter",
    "inputPath",
    "dealPath",
    "successPath",
    "errorPath",
    "backupPath",
    "parallelNum",
    "loadfileOver",
    "isWait",
    "isCheck",
    "comPara",
    "business",
    "isTruncateTable"
})
public class LOADDATACONFIG {

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
    @XmlElementRef(name = "input-path", type = JAXBElement.class, required = false)
    protected JAXBElement<String> inputPath;
    @XmlElementRef(name = "deal-path", type = JAXBElement.class, required = false)
    protected JAXBElement<String> dealPath;
    @XmlElementRef(name = "success-path", type = JAXBElement.class, required = false)
    protected JAXBElement<String> successPath;
    @XmlElementRef(name = "error-path", type = JAXBElement.class, required = false)
    protected JAXBElement<String> errorPath;
    @XmlElementRef(name = "backup-path", type = JAXBElement.class, required = false)
    protected JAXBElement<String> backupPath;
    @XmlElementRef(name = "parallel-num", type = JAXBElement.class, required = false)
    protected JAXBElement<String> parallelNum;
    @XmlElementRef(name = "loadfile-over", type = JAXBElement.class, required = false)
    protected JAXBElement<String> loadfileOver;
    @XmlElementRef(name = "isWait", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isWait;
    @XmlElementRef(name = "isCheck", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isCheck;
    @XmlElementRef(name = "com-para", type = JAXBElement.class, required = false)
    protected JAXBElement<String> comPara;
    @XmlElementRef(name = "business", type = JAXBElement.class, required = false)
    protected JAXBElement<String> business;
    @XmlElementRef(name = "isTruncateTable", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isTruncateTable;

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
     * 获取inputPath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInputPath() {
        return inputPath;
    }

    /**
     * 设置inputPath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInputPath(JAXBElement<String> value) {
        this.inputPath = value;
    }

    /**
     * 获取dealPath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDealPath() {
        return dealPath;
    }

    /**
     * 设置dealPath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDealPath(JAXBElement<String> value) {
        this.dealPath = value;
    }

    /**
     * 获取successPath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSuccessPath() {
        return successPath;
    }

    /**
     * 设置successPath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSuccessPath(JAXBElement<String> value) {
        this.successPath = value;
    }

    /**
     * 获取errorPath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorPath() {
        return errorPath;
    }

    /**
     * 设置errorPath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorPath(JAXBElement<String> value) {
        this.errorPath = value;
    }

    /**
     * 获取backupPath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBackupPath() {
        return backupPath;
    }

    /**
     * 设置backupPath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBackupPath(JAXBElement<String> value) {
        this.backupPath = value;
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
     * 获取loadfileOver属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLoadfileOver() {
        return loadfileOver;
    }

    /**
     * 设置loadfileOver属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLoadfileOver(JAXBElement<String> value) {
        this.loadfileOver = value;
    }

    /**
     * 获取isWait属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsWait() {
        return isWait;
    }

    /**
     * 设置isWait属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsWait(JAXBElement<String> value) {
        this.isWait = value;
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
     * 获取isTruncateTable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsTruncateTable() {
        return isTruncateTable;
    }

    /**
     * 设置isTruncateTable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsTruncateTable(JAXBElement<String> value) {
        this.isTruncateTable = value;
    }

}
