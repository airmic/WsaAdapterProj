package ru.mk.mkservice.client.clientinfo;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class PhotoReceiver extends WebServiceGatewaySupport {
    public String getPhotoUrl(long mdmId) {
        return "photoUrl for " + mdmId;
    }
}
