package ru.mk.wsa.adapter.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ws.soap.SoapHeaderElement;
import ru.mk.wsa.adapter.model.soapheaderelements.RelatesTo;
import ru.mk.wsa.adapter.service.ReceiveResponseWsaService;
import ru.mk.wsa.adapter.service.SendRequestWsaService;
import ru.mk.wsa.common.helper.WsaHeaderHelper;

@Log4j2
@RequiredArgsConstructor
public abstract class AbstractWsaEndpoint<Req, Resp, Sender extends SendRequestWsaService<Req, Resp>, Receiver extends ReceiveResponseWsaService<Resp>> implements WsaEndpoint<Req, Resp> {
    private final Sender sender;
    private final Receiver receiver;

    private String getInitialMessageID(SoapHeaderElement relatedToElement) {
        String relatedTo;
        try {
            relatedTo = WsaHeaderHelper.unmarshal(relatedToElement, RelatesTo.class).getValue();
            if( relatedTo == null || relatedTo.isEmpty() || relatedTo.length()!=41) {
                throw new RuntimeException("Error in the RelatedTo field of the WSA header of the SearchCustomerDossierDocument message");
            }
        } catch (Exception ex) {
            log.error("RelatedTo error.", ex);
            throw new RuntimeException(ex);
        }
        return relatedTo.substring(5);
    }

    protected Resp getSyncRequest2(Req req) {
        log.info("Start processing the received SearchCustomerDossierDocument request");
        Resp ret = sender.send(req);
        log.info("End of processing SearchCustomerDossierDocument request");
        return ret;
    }

    protected void getAsyncResponse2(Resp resp, SoapHeaderElement relatedToElem) {
        log.info("Start processing the async received SearchCustomerDossierDocument response");
        String messageID = getInitialMessageID(relatedToElem);
        log.trace("RelatesTo - " + messageID);
        receiver.receive(messageID, resp);
        log.trace("End of processing SearchCustomerDossierDocument response");
    }
}
