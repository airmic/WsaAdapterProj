package ru.mk.mkservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mk.mkservice.model.ClientInfo;
import ru.mk.mkservice.service.ClientInfoService;

@RestController
@RequestMapping("clientInfo")
@RequiredArgsConstructor
public class ServiceController {

    private final ClientInfoService clientInfoService;

    @GetMapping("/{clientID}")
    public ClientInfo getClientInfo(@PathVariable Long clientID) {
        return clientInfoService.getClientInfo(clientID);
    }
}
