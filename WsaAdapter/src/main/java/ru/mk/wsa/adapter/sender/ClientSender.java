package ru.mk.wsa.adapter.sender;

public interface ClientSender<Req> {
    void sendPayload(Req req, String messageId);
}
