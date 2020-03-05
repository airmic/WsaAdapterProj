package ru.mk.wsa.adapter.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "wsa")
@NoArgsConstructor
@Data
public class WsaPropertiesList {

    @NotBlank
    private Integer defTimeoutMs;
    @NotBlank
    private Map<String, ServerInfo> servers;
    @NotBlank
    private Integer threadPoolSize;

    @NoArgsConstructor
    @Data
    public static class ServerInfo {
        @NotBlank
        private String sendUri;
        private String replyToUri;
        private String faultToUri;
        private String action;
        private String namespace;
        private String contextPath;
        private Integer waitingTimeoutMs;
        private String localPartReq;
        private String localPartResp;
    }

}
