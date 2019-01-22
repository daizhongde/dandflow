
package person.daizhongde.restful.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>FIELDAUDITCONFIG complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="FIELDAUDITCONFIG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="task-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-ins-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dryrun-id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="delimiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parallel-num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="business" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isWait" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "FIELDAUDITCONFIG", propOrder = {
    "taskId",
    "jobId",
    "jobInsId",
    "dryrunId",
    "delimiter",
    "parallelNum",
    "business",
    "isWait",
    "isCheck",
    "comPara"
})
public class FIELDAUDITCONFIG {

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
    @XmlElementRef(name = "parallel-num", type = JAXBElement.class, required = false)
    protected JAXBElement<String> parallelNum;
    @XmlElementRef(name = "business", type = JAXBElement.class, required = false)
    protected JAXBElement<String> business;
    @XmlElementRef(name = "isWait", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isWait;
    @XmlElementRef(name = "isCheck", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isCheck;
    @XmlElementRef(name = "com-para", type = JAXBElement.class, required = false)
    protected JAXBElement<String> comPara;

    /**
     * ��ȡtaskId���Ե�ֵ��
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
     * ����taskId���Ե�ֵ��
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
     * ��ȡjobId���Ե�ֵ��
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
     * ����jobId���Ե�ֵ��
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
     * ��ȡjobInsId���Ե�ֵ��
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
     * ����jobInsId���Ե�ֵ��
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
     * ��ȡdryrunId���Ե�ֵ��
     * 
     */
    public int getDryrunId() {
        return dryrunId;
    }

    /**
     * ����dryrunId���Ե�ֵ��
     * 
     */
    public void setDryrunId(int value) {
        this.dryrunId = value;
    }

    /**
     * ��ȡdelimiter���Ե�ֵ��
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
     * ����delimiter���Ե�ֵ��
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
     * ��ȡparallelNum���Ե�ֵ��
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
     * ����parallelNum���Ե�ֵ��
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
     * ��ȡbusiness���Ե�ֵ��
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
     * ����business���Ե�ֵ��
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
     * ��ȡisWait���Ե�ֵ��
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
     * ����isWait���Ե�ֵ��
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
     * ��ȡisCheck���Ե�ֵ��
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
     * ����isCheck���Ե�ֵ��
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
     * ��ȡcomPara���Ե�ֵ��
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
     * ����comPara���Ե�ֵ��
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
