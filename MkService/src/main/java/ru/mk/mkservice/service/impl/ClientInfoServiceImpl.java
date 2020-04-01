package ru.mk.mkservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mk.mkservice.client.clientinfo.DBInfoReceiver;
import ru.mk.mkservice.client.clientinfo.PhotoReceiver;
import ru.mk.mkservice.model.ClientInfo;
import ru.mk.mkservice.service.ClientInfoService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientInfoServiceImpl implements ClientInfoService {

    private final DBInfoReceiver dbInfoReceiver;
    private final PhotoReceiver photoReceiver;

    public ClientInfo getClientInfo(long clientId) {
        ClientInfo clientinfo = getFlexteraClientInfo(clientId);
        return clientinfo;
    }

    private ClientInfo getFlexteraClientInfo(long clientId) {
        String sessionId = UUID.randomUUID().toString();
        ClientInfo clientInfo = dbInfoReceiver.getMainClientInfo(sessionId, clientId);
        clientInfo.setPhoto( photoReceiver.getPhotoUrl(sessionId, clientInfo.getMdmID()));
        return clientInfo;
    }

}
