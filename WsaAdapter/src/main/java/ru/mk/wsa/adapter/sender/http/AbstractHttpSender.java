package ru.mk.wsa.adapter.sender.http;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import ru.mk.wsa.adapter.config.WsaPropertiesList;
import ru.mk.wsa.adapter.sender.ClientSender;
import javax.xml.namespace.QName;
import javax.xml.soap.*;

public abstract class AbstractHttpSender<Req> extends WebServiceGatewaySupport implements ClientSender<Req> {

    private static final String NS_WSA = "http://www.w3.org/2005/08/addressing";

    private final WsaPropertiesList.ServerInfo serverInfo;

    public AbstractHttpSender(String serverInfoKey, WsaPropertiesList wsaPropertiesList) {
        this.serverInfo = wsaPropertiesList.getServers().get(serverInfoKey);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(serverInfo.getContextPath());
        setMarshaller( marshaller);
        setUnmarshaller(marshaller);
        setDefaultUri(serverInfo.getSendUri());
    }

    @Override
    public void sendPayload(Req req, String messageId) {
        getWebServiceTemplate().marshalSendAndReceive(req,  message -> {
            try {
                SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
                SOAPMessage soapMessage = saajSoapMessage.getSaajMessage();
                SOAPPart soapPart = soapMessage.getSOAPPart();
                SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
                SOAPHeader soapHeader = soapEnvelope.getHeader();

                soapHeader.addNamespaceDeclaration("wsa", NS_WSA);
                soapHeader.addHeaderElement(new QName(NS_WSA,"MessageID","wsa")).addTextNode("uuid:"+ messageId);
                soapHeader.addHeaderElement(new QName(NS_WSA,"Action","wsa")).addTextNode(serverInfo.getAction());
                soapHeader.addHeaderElement(new QName(NS_WSA,"ReplyTo","wsa"))
                        .addChildElement(new QName(NS_WSA,"Address","wsa"))
                        .addTextNode(serverInfo.getReplyToUri());
                soapHeader.addHeaderElement(new QName(NS_WSA,"FaultTo","wsa"))
                        .addChildElement(new QName(NS_WSA,"Address","wsa"))
                        .addTextNode(serverInfo.getFaultToUri());

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
