package ru.mk.mkservice.client.clientinfo;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.mk.common.clientInfo.*;
import ru.mk.mkservice.model.ClientInfo;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.UUID;

public class DBInfoReceiver extends WebServiceGatewaySupport {
    public ClientInfo getMainClientInfo(String sessionIdd, Long clientId) {
        ClientInfoRequest req = new ClientInfoRequest();
        req.setSessionInfo( new SessionInfoType() {{
            setSessionId(sessionIdd);
            setMessageId(UUID.randomUUID().toString());
        }});
        req.setClientInfo( new ClientInfoRequestType() {{
            setOkdId(clientId);
        }});
        JAXBElement<ClientInfoRequest> jbClientInfoReq = new JAXBElement<>(new QName("http://www.mk.ru/clientInfo","clientInfoRequest"),ClientInfoRequest.class, req);
        JAXBElement<ClientInfoResponse> jbResponse = (JAXBElement<ClientInfoResponse>) getWebServiceTemplate().marshalSendAndReceive(jbClientInfoReq);
        if( jbResponse == null || jbResponse.getValue() == null || jbResponse.getValue().getClientInfo() == null || jbResponse.getValue().getClientInfo().getMdmId() == 0) {
            throw new RuntimeException("Ошибка при получении информации по клиенту");
        }
        ClientInfoResponseType responseType = jbResponse.getValue().getClientInfo();
        return new ClientInfo() {{
            setMdmID(responseType.getMdmId());
            setLastName(responseType.getLastName());
            setFirstName(responseType.getFirstName());
        }};
    }
}
