package ru.mk.wsa.adapter.service;

public interface SendRequestWsaService<Req, Resp>{
    Resp send(Req req) ;
}
