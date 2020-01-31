package ru.mk.wsa.adapter.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://www.w3.org/2005/08/addressing", name = "RelatesTo")
@NoArgsConstructor
@Data
public class RelatesTo {

    @XmlValue
    private String value;
}
