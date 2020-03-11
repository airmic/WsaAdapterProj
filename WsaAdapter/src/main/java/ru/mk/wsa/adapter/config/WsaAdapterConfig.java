package ru.mk.wsa.adapter.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointExceptionResolver;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

import java.util.Properties;
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

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setBasename("/i18n/messages");
        return bundleMessageSource;
    }

    @Bean
    public EndpointExceptionResolver exceptionResolver() {
        SoapFaultMappingExceptionResolver soapFaultMappingExceptionResolver = new SoapFaultMappingExceptionResolver();
        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        soapFaultMappingExceptionResolver.setDefaultFault(faultDefinition);

        Properties properties = new Properties();
        properties.setProperty("java.lang.Exception","SERVER,Internal error");
//        properties.setProperty("java.lang.RuntimeException","SERVER,Internal error");
//        properties.setProperty("java.util.concurrent.ExecutionException","SERVER,Internal error");
//        properties.setProperty("org.springframework.data.redis.RedisConnectionFailureException","SERVER,Internal error");
//        properties.setProperty("io.lettuce.core.RedisConnectionException","CLIENT,Internal error");
//        properties.setProperty("org.springframework.ws.soap.client.SoapFaultClientException","CLIENT,Internal error");
//        properties.setProperty("java.net.ConnectException","CLIENT,Internal error");

        soapFaultMappingExceptionResolver.setExceptionMappings(properties);
        soapFaultMappingExceptionResolver.setOrder(1);
        return soapFaultMappingExceptionResolver;
    }
}
