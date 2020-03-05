package ru.mk.wsa.adapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.*;

@Log4j2
@RequiredArgsConstructor
public abstract class AbstractTimeoutBreaker<Req, Resp, ARS extends AsyncRunnerService<Req, Resp> > implements TimeoutBreaker<Req, Resp>{
    private final ARS asyncRunnerService;
    private final ScheduledThreadPoolExecutor timeoutBreaker;

    public Resp getResponseByTimeout(String messageID, long timeout) {
        try {
            CompletableFuture<Resp> completableResp = asyncRunnerService.getResponse(messageID);
            timeoutBreaker.schedule(() -> {
                completableResp.cancel(true);
            }, timeout, TimeUnit.SECONDS);
            log.trace("Request done");
            return completableResp.get();
        } catch (CancellationException e) {
            log.error("Request cancelled", e);
            throw new RuntimeException("WAiting ",e);
        } catch (InterruptedException | ExecutionException e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }

    public void sendAsyncRequestByTimeout(Req jaxbReq , String messageId, long timeout) {
        asyncRunnerService.sendAsyncRequest(jaxbReq, messageId);
    }
}
