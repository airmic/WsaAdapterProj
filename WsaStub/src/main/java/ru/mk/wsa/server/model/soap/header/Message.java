package ru.mk.wsa.server.model.soap.header;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = ReplyToAddress.WSA_NS, name = "MessageID")
@NoArgsConstructor
@Data
public class Message {
    @XmlValue
    private String value;
}
