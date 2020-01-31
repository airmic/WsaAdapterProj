package ru.mk.wsa.server.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.addressing.client.ActionCallback;
import org.springframework.ws.soap.addressing.core.EndpointReference;
import org.springframework.ws.soap.addressing.messageid.MessageIdStrategy;
import ru.mk.wsa.adapter587.DocumentRespType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;
import ru.mk.wsa.server.param.SearchCustomDossierDocumentRequest;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;


@Log4j2
public class WsaClient extends WebServiceGatewaySupport {

    private final String WSA_URI = "http://www.w3.org/2005/08/addressing";
    private final String RESP_ACTION = "http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1/SearchCustomerDossierDocumentCallBack";

    public void sendSearchCustomerDossierDocumentResp (SearchCustomDossierDocumentRequest request) throws URISyntaxException {
        SearchCustomerDossierDocumentRespType resp = getResp(request.getReq());

//        ((SaajSoapMessage)request.getMessageContext().getRequest()).getEnvelope().getHeader().removeHeaderElement(new QName("http://www.w3.org/2005/08/addressing","To" ));
//        ((SaajSoapMessage)request.getMessageContext().getRequest()).getEnvelope().getHeader().removeHeaderElement(new QName("http://www.w3.org/2005/08/addressing","ReplyTo" ));
//        ((SaajSoapMessage)request.getMessageContext().getRequest()).getEnvelope().getHeader().addAttribute(new QName("http://www.w3.org/2005/08/addressing","To" ),request.getReplyTo().getValue());
//        ((SaajSoapMessage)request.getMessageContext().getRequest()).getEnvelope().getHeader().addAttribute(new QName("http://www.w3.org/2005/08/addressing","RelatesTo" ),request.getMessageID().getValue());

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.mk.wsa.adapter587");
        setMarshaller( marshaller);
        setUnmarshaller(marshaller);
        QName respQName = new QName("http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1","SearchCustomerDossierDocumentResp");
        JAXBElement<SearchCustomerDossierDocumentRespType> restJb = new JAXBElement(respQName, SearchCustomerDossierDocumentRespType.class, resp);

        sendWithHeadersVersion2(request, restJb);
    }

    private void sendWithHeadersVersion1(SearchCustomDossierDocumentRequest request, JAXBElement<SearchCustomerDossierDocumentRespType> restJb) throws URISyntaxException {

        ActionCallback callback = new ActionCallback(
                new URI(RESP_ACTION));
        callback.setReplyTo(new EndpointReference(
                new URI(request.getReplyTo().toString())));
        callback.setMessageIdStrategy(new MessageIdStrategy() {
            @Override
            public boolean isDuplicate(URI messageId) {
                return false;
            }

            @Override
            public URI newMessageId(SoapMessage message) {
                try {
                    return new URI("TEST123MESSGA3");
                } catch (URISyntaxException e) {
                    throw new RuntimeException("Error");
                }
            }
        });
//                setReplyTo(new EndpointReference(
//                new URI("http://localhost:8044/response")));
//        callback.setFaultTo(new EndpointReference(
//                new URI("http://localhost:8044/fault")));

        getWebServiceTemplate().marshalSendAndReceive(request.getReplyTo().toString(), restJb, callback);
    }

    private void sendWithHeadersVersion2(SearchCustomDossierDocumentRequest request, JAXBElement<SearchCustomerDossierDocumentRespType> restJb) {
        getWebServiceTemplate().marshalSendAndReceive(request.getReplyTo().getValue(), restJb, new WebServiceMessageCallback() {
            @Override
            public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
                SoapHeader header = ((SoapMessage)message).getSoapHeader();
                header.addNamespaceDeclaration("wsa", WSA_URI);
                header.addHeaderElement(new QName(WSA_URI,"MessageID","wsa"))
                        .setText("uuid:"+UUID.randomUUID().toString());
                header.addHeaderElement(new QName(WSA_URI,"RelatesTo","wsa"))
                        .setText(request.getMessageID().getValue());
                header.addHeaderElement(new QName(WSA_URI,"Action","wsa"))
                        .setText(RESP_ACTION);
            }
        });
    }

    private SearchCustomerDossierDocumentRespType getResp(SearchCustomerDossierDocumentReqType req) {
        SearchCustomerDossierDocumentRespType resp = new SearchCustomerDossierDocumentRespType();
        resp.setDocument(
                new DocumentRespType() {{
                    if(req.getDocument() != null) {
                        if( req.getDocument().getDocType() != null )
                            setDocType(req.getDocument().getDocType());
                        if( req.getDocument().getDocSubType() != null )
                            setDocSubType(req.getDocument().getDocSubType());
                        setDocComment("Comment");
                    }
                }}
        );
        return resp;
    }


}
