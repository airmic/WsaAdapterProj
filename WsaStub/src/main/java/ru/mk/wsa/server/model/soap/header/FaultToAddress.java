package ru.mk.wsa.server.model.soap.header;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = FaultToAddress.WSA_NS, name = "FaultTo")
@Data
@NoArgsConstructor
public class FaultToAddress {
    final public static String WSA_NS="http://www.w3.org/2005/08/addressing";

    @XmlElement( namespace = WSA_NS, name = "Address")
    private String value;

}
