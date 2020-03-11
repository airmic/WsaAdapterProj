package ru.mk.wsa.adapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.mk.wsa.adapter.repository.WsaRepository;
import ru.mk.wsa.adapter.utils.WsaMS;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBElement;

//@Component
@Log4j2
@RequiredArgsConstructor
public abstract class AbstractReceiveResultWsaService<Resp> implements ReceiveResponseWsaService<Resp> {

    private final WsaRepository wsaRepository;

    @Override
    public void receive(String messageId, Resp resp) {
        try {
            log.trace(WsaMS.getString("save.result.in.repository"));
//            wsaRepository.put(messageId, getResponse(resp));
            wsaRepository.put(messageId, resp);
        } catch (Exception ex) {
            log.error(WsaMS.getString("save.to.repository.error"), ex);
            throw new RuntimeException(ex);
        }
    }
}
