package ru.mk.wsa.adapter.service;

public interface TimeoutBreaker<Req, Resp> {
    Resp getResponseByTimeout(String messageID, long timeout);
    void sendAsyncRequestByTimeout(Req jaxbReq , String messageId, long timeout);
}
