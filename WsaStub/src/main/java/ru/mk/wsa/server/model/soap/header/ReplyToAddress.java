package ru.mk.wsa.server.model.soap.header;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = ReplyToAddress.WSA_NS, name = "ReplyTo")
@Data
@NoArgsConstructor
public class ReplyToAddress {
    final public static String WSA_NS="http://www.w3.org/2005/08/addressing";

    @XmlElement( namespace = WSA_NS, name = "Address")
    private String value;

}
