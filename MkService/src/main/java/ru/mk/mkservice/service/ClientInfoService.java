package ru.mk.mkservice.service;

import ru.mk.mkservice.model.ClientInfo;


public interface ClientInfoService {
    ClientInfo getClientInfo(long clientId);
}
