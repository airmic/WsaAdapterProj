package ru.mk.wsa.server.param;

import lombok.Data;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeaderElement;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.server.model.soap.header.FaultToAddress;
import ru.mk.wsa.server.model.soap.header.Message;
import ru.mk.wsa.server.model.soap.header.ReplyToAddress;

import static ru.mk.wsa.common.helper.WsaHeaderHelper.unmarshal;


@Data
public class SearchCustomDossierDocumentRequest {
    private final SearchCustomerDossierDocumentReqType req;
    private final ReplyToAddress replyTo;
    private final FaultToAddress faultTo;
    private final Message messageID;
    private final MessageContext messageContext;

    public SearchCustomDossierDocumentRequest(
            SearchCustomerDossierDocumentReqType req
            , SoapHeaderElement replyTo
            , SoapHeaderElement faultTo
            , SoapHeaderElement messageID
            , MessageContext messageContext
    ) {
        this.req = req;
        this.replyTo = unmarshal(replyTo, ReplyToAddress.class);
        this.faultTo = unmarshal(faultTo, FaultToAddress.class);
        this.messageID = unmarshal(messageID, Message.class);
        this.messageContext = messageContext;
    }


}
