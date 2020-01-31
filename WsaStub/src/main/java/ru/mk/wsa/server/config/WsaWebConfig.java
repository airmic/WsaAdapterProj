package ru.mk.wsa.server.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.addressing.server.AnnotationActionEndpointMapping;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

@EnableWs
@Configuration
public class WsaWebConfig extends WsConfigurerAdapter {


//    @Bean
//    public AnnotationActionEndpointMapping annotationActionEndpointMapping() {
//        AnnotationActionEndpointMapping mapping = new AnnotationActionEndpointMapping();
//        mapping.setMessageSender(new HttpComponentsMessageSender());
//        return mapping;
//    }
//
//    @Bean
//    public AnnotationActionEndpointMapping annotationActionEndpointMapping(AnnotationActionEndpointMapping annotationActionEndpointMapping) {
//        annotationActionEndpointMapping.setMessageSender(new HttpComponentsMessageSender());
//        return annotationActionEndpointMapping;
//    }

    @Bean
    public ServletRegistrationBean responseServlet(){
        ResponseServlet servlet = new ResponseServlet();
        return new ServletRegistrationBean(servlet, "/response");
    }

    @Bean
    public ServletRegistrationBean faultServlet(){
        FaultServlet servlet = new FaultServlet();
        return new ServletRegistrationBean(servlet, "/fault");
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformSchemaLocations(true);
        return new ServletRegistrationBean(servlet, "/wsa/*");
    }

    @Bean(name = "stubWsa")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/SearchCustomerDossierDocumentDSFT_Service.wsdl"));
        return wsdl11Definition;
    }

    @Bean(name = "beers")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BeersPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://meorynotfound.com/beer");
        wsdl11Definition.setSchema(schema);

//        // fix for adding soap action to the dynamic generated wsdl
//        Properties soapActions = new Properties();
//        soapActions.setProperty("getBeer", "http://memorynotfound.com/getBeerRequest");
//        wsdl11Definition.setSoapActions(soapActions);
        return wsdl11Definition;
    }

}
