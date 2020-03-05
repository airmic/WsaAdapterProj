package ru.mk.wsa.adapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class AbstractSendRequestWsaService<Req, Resp> implements SendRequestWsaService<Req,Resp> {

    private final AbstractTimeoutBreaker<Req, Resp, AsyncRunnerService<Req, Resp> > timeoutBreakerService;
    private final Long timeout = 10L;

    @Override
    public Resp send(Req req) {
        log.trace("Send request to target WSA server");
        String messageId = generateWsaMessageId();
        log.trace("Generated request messageID: " + messageId);
        timeoutBreakerService.sendAsyncRequestByTimeout(req, messageId, timeout);
        Resp resp = timeoutBreakerService.getResponseByTimeout(messageId, timeout);
        return resp;
    }

    private String generateWsaMessageId() {
        return UUID.randomUUID().toString();
    }
}
