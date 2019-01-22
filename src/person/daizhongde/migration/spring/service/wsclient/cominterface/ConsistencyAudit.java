
package person.daizhongde.migration.spring.service.wsclient.cominterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="szMsg" type="{urn:ComInterFace}CONSISTENCYAUDITCONFIG" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "szMsg"
})
@XmlRootElement(name = "ConsistencyAudit")
public class ConsistencyAudit {

    @XmlElementRef(name = "szMsg", type = JAXBElement.class, required = false)
    protected JAXBElement<CONSISTENCYAUDITCONFIG> szMsg;

    /**
     * 获取szMsg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CONSISTENCYAUDITCONFIG }{@code >}
     *     
     */
    public JAXBElement<CONSISTENCYAUDITCONFIG> getSzMsg() {
        return szMsg;
    }

    /**
     * 设置szMsg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CONSISTENCYAUDITCONFIG }{@code >}
     *     
     */
    public void setSzMsg(JAXBElement<CONSISTENCYAUDITCONFIG> value) {
        this.szMsg = value;
    }

}
