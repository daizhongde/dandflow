
package person.daizhongde.restful.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CONTROLSIGNAL complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
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
     * ��ȡtaskSignal���Ե�ֵ��
     * 
     */
    public int getTaskSignal() {
        return taskSignal;
    }

    /**
     * ����taskSignal���Ե�ֵ��
     * 
     */
    public void setTaskSignal(int value) {
        this.taskSignal = value;
    }

}
