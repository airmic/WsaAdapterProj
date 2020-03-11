package ru.mk.wsa.adapter.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ws.soap.SoapHeaderElement;
import ru.mk.wsa.adapter.model.soapheaderelements.RelatesTo;
import ru.mk.wsa.adapter.service.ReceiveResponseWsaService;
import ru.mk.wsa.adapter.service.SendRequestWsaService;
import ru.mk.wsa.adapter.utils.WsaMS;
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
                throw new RuntimeException(WsaMS.getString("error.AbstractWsaEndpoint.getInitialMessageID.th1", this.getClass().getSimpleName()));
            }
        } catch (Exception ex) {
            log.error(WsaMS.getString("error.AbstractWsaEndpoint.getInitialMessageID.th2"), ex);
            throw new RuntimeException(ex);
        }
        return relatedTo.substring(5);
    }

    protected Resp getSyncRequest2(Req req) {
        log.info(WsaMS.getString("AbstractWsaEndpoint.getSyncRequest2.start", this.getClass().getSimpleName()));
        Resp ret = sender.send(req);
        log.info(WsaMS.getString("AbstractWsaEndpoint.getSyncRequest2.end", this.getClass().getSimpleName()));
        return ret;
    }

    protected void getAsyncResponse2(Resp resp, SoapHeaderElement relatedToElem) {
        log.info(WsaMS.getString("AbstractWsaEndpoint.getAsyncResponse2.start", this.getClass().getSimpleName()));
        String messageID = getInitialMessageID(relatedToElem);
        log.trace(WsaMS.getString("trace.relatesto.0", messageID));
        receiver.receive(messageID, resp);
        log.info(WsaMS.getString("AbstractWsaEndpoint.getAsyncResponse2.end", this.getClass().getSimpleName()));
    }
}
