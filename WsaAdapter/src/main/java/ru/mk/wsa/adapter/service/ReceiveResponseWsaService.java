package ru.mk.wsa.adapter.service;

public interface ReceiveResponseWsaService<Resp> {
    void receive(String messageId, Resp resp);
}
