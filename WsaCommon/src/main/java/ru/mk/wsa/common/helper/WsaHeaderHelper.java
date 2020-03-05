package ru.mk.wsa.common.helper;

import org.springframework.ws.soap.SoapHeaderElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class WsaHeaderHelper {
    public static <T> T unmarshal(SoapHeaderElement messageID, Class<T> clazz) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(messageID.getSource());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return t;
    }
}
