
package person.daizhongde.migration.spring.service.wsclient.cominterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "szMsg"
})
@XmlRootElement(name = "Controller")
public class Controller {

    @XmlElementRef(name = "szMsg", type = JAXBElement.class, required = false)
    protected JAXBElement<CONTROLSIGNAL> szMsg;

    /**
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CONTROLSIGNAL }{@code >}
     *     
     */
    public JAXBElement<CONTROLSIGNAL> getSzMsg() {
        return szMsg;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CONTROLSIGNAL }{@code >}
     *     
     */
    public void setSzMsg(JAXBElement<CONTROLSIGNAL> value) {
        this.szMsg = value;
    }

}
