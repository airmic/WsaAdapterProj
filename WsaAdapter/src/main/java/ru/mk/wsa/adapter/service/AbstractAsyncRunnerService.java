package ru.mk.wsa.adapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import ru.mk.wsa.adapter.repository.WsaRepository;
import ru.mk.wsa.adapter.sender.ClientSender;

import java.util.concurrent.CompletableFuture;

@Log4j2
@RequiredArgsConstructor
public abstract class AbstractAsyncRunnerService<Req, Resp>  implements AsyncRunnerService<Req, Resp> {

    private final WsaRepository wsaRepository;
    private final ClientSender<Req> sender;

    @Async("redisThreadPoolExecutor")
    public CompletableFuture<Resp> getResponse(String messageID) {
        log.trace("Start waiting response cycle");
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.error("Redis request timeout expired");
                    throw new RuntimeException("Redis request timeout expired");
                }
                Resp ret = wsaRepository.get(messageID);
                if (ret == null) {
                    Thread.yield();
                } else {
                    log.trace("get response - {} ", ret);
                    return CompletableFuture.completedFuture(ret);
                }
            }
        } finally {
            log.trace("Stop waiting response cycle");
        }
    }


    @Async("redisThreadPoolExecutor")
    public void sendAsyncRequest(Req jaxbReq, String messageId) {
        sender.sendPayload(jaxbReq, messageId);
    }
}