package ru.mk.wsa.adapter.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncRunnerService<Req, Resp> {
    CompletableFuture<Resp> getResponse(String messageID) throws InterruptedException;
    void sendAsyncRequest(Req jaxbReq , String messageId);
}
