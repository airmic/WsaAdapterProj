package ru.mk.wsa.adapter.endpoint;

import org.springframework.ws.soap.SoapHeaderElement;

public interface WsaEndpoint<Req,Resp> {
    Resp getSyncRequest(Req req);
    void getAsyncResponse(Resp resp, SoapHeaderElement relatedTo);
}
