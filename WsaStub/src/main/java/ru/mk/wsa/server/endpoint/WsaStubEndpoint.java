package ru.mk.wsa.server.endpoint;

import lombok.extern.log4j.Log4j2;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.server.client.WsaClient;
import ru.mk.wsa.server.param.SearchCustomDossierDocumentRequest;

import java.net.URISyntaxException;


@Log4j2
@Endpoint
public class WsaStubEndpoint {

//    @Action("http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1/SearchCustomerDossierDocument")
//    public void searchCustomerDossierDocument(@RequestPayload SearchCustomerDossierDocumentReqType req
//            , @SoapHeader("{http://www.w3.org/2005/08/addressing}ReplyTo") SoapHeaderElement replyTo
//            , @SoapHeader("{http://www.w3.org/2005/08/addressing}FaultTo") SoapHeaderElement faultTo
//            , @SoapHeader("{http://www.w3.org/2005/08/addressing}MessageID") SoapHeaderElement messageID) throws URISyntaxException {
//
//        WsaClient client = new WsaClient();
//        client.sendSearchCustomerDossierDocumentResp(new SearchCustomDossierDocumentRequest(req, replyTo, messageID));
//
//    }

//
//    @PayloadRoot(namespace = "http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1", localPart = "SearchCustomerDossierDocumentReq")
//    public @ResponsePayload void searchCustomerDossierDocument(@RequestPayload SearchCustomerDossierDocumentReqType req, MessageContext messageContext) {
//        WsaClient client = new WsaClient();
//        client.sendSearchCustomerDossierDocumentResp(req, messageContext);
//    }

    @PayloadRoot(namespace = "http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1", localPart = "SearchCustomerDossierDocumentReq")
    public @ResponsePayload void searchCustomerDossierDocument(@RequestPayload SearchCustomerDossierDocumentReqType req
            , @SoapHeader("{http://www.w3.org/2005/08/addressing}ReplyTo") SoapHeaderElement replyTo
            , @SoapHeader("{http://www.w3.org/2005/08/addressing}FaultTo") SoapHeaderElement faultTo
            , @SoapHeader("{http://www.w3.org/2005/08/addressing}MessageID") SoapHeaderElement messageID
            , MessageContext messageContext
    ) throws URISyntaxException, InterruptedException {
        WsaClient client = new WsaClient();
//        SoapHeaderElement soapHeader = null;
        log.info("replyTo - "+messageContext.getRequest().getPayloadSource());

        client.sendSearchCustomerDossierDocumentResp(new SearchCustomDossierDocumentRequest(req, replyTo, faultTo, messageID, messageContext));
    }

}
