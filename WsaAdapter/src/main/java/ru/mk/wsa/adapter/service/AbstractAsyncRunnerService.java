package ru.mk.wsa.adapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import ru.mk.wsa.adapter.repository.WsaRepository;
import ru.mk.wsa.adapter.sender.ClientSender;
import ru.mk.wsa.adapter.utils.WsaMS;

import java.util.concurrent.CompletableFuture;

@Log4j2
@RequiredArgsConstructor
public abstract class AbstractAsyncRunnerService<Req, Resp>  implements AsyncRunnerService<Req, Resp> {

    private final WsaRepository wsaRepository;
    private final ClientSender<Req> sender;

    @Async("redisThreadPoolExecutor")
    public CompletableFuture<Resp> getResponse(String messageID) {
        log.trace(WsaMS.getString("AbstractAsyncRunnerService.getResponse.start"));
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.error(WsaMS.getString("error.AbstractAsyncRunnerService.getResponse.th1"));
                    throw new RuntimeException(WsaMS.getString("error.AbstractAsyncRunnerService.getResponse.th1"));
                }
                Resp ret = wsaRepository.get(messageID);
                if (ret == null) {
                    Thread.yield();
                } else {
                    log.trace(WsaMS.getString("AbstractAsyncRunnerService.getResponse.get.response"), ret);
                    return CompletableFuture.completedFuture(ret);
                }
            }
        } finally {
            log.trace(WsaMS.getString("AbstractAsyncRunnerService.getResponse.stop"));
        }
    }


    @Async("redisThreadPoolExecutor")
    public void sendAsyncRequest(Req jaxbReq, String messageId) {
        sender.sendPayload(jaxbReq, messageId);
    }
}