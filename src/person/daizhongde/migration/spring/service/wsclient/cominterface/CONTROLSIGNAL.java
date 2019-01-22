
package person.daizhongde.migration.spring.service.wsclient.cominterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CONTROLSIGNAL complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CONTROLSIGNAL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="task-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job-ins-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dryrun-id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="task-signal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CONTROLSIGNAL", propOrder = {
    "taskId",
    "jobId",
    "jobInsId",
    "dryrunId",
    "taskSignal"
})
public class CONTROLSIGNAL {

    @XmlElementRef(name = "task-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> taskId;
    @XmlElementRef(name = "job-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jobId;
    @XmlElementRef(name = "job-ins-id", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jobInsId;
    @XmlElement(name = "dryrun-id")
    protected int dryrunId;
    @XmlElement(name = "task-signal")
    protected int taskSignal;

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
     * 获取taskSignal属性的值。
     * 
     */
    public int getTaskSignal() {
        return taskSignal;
    }

    /**
     * 设置taskSignal属性的值。
     * 
     */
    public void setTaskSignal(int value) {
        this.taskSignal = value;
    }

}
