package ru.mk.wsa.adapter.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://www.w3.org/2005/08/addressing", name = "MessageID")
@NoArgsConstructor
@Data
public class Message {

    @XmlValue
    private String value;
}
