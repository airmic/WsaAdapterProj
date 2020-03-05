package ru.mk.wsa.adapter.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableWs
@Configuration
public class WsaAdapterConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformSchemaLocations(true);
        return new ServletRegistrationBean(servlet, "/wsa", "/wsa/adapter587", "/wsa/adapter587.wsdl","/sync", "/sync/adapter587sync", "/sync/adapter587sync.wsdl");
    }

    @Bean(name = "adapter587")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/SearchCustomerDossierDocumentDSFT_Service.wsdl"));
        return wsdl11Definition;
    }

    @Bean(name = "adapter587sync")
    public Wsdl11Definition defaultWsdl11Definition2() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/SearchCustomerDossierDocumentDSFT_Service_Sync.wsdl"));
        return wsdl11Definition;
    }

    @Bean
    public ExecutorService singleExecutorService() {
        return Executors.newSingleThreadExecutor();
    }

}
