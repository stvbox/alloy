package com.grafana.demo;

import com.github.loki4j.logback.AbstractLoki4jEncoder;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
//import zipkin2.server.internal.EnableZipkinServer;

import java.util.Arrays;


//@EnableZipkinServer
@SpringBootApplication
public class IntegrationDemoApplication {

    public static void main(String[] args) {

        MDC.put("traceId", "system");
        MDC.put("spanId", "0");

        SpringApplication.run(IntegrationDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect beans provided by Spring Boot:");
            var beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
        //AbstractLoki4jEncoder;
        //org.springframework.boot.actuate.autoconfigure.tracing.zipkin.ZipkinRestTemplateSender;
        //AsyncReporter{org.springframework.boot.actuate.autoconfigure.tracing.zipkin.ZipkinRestTemplateSender@6598caab}
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "MYAPPNAME");
    }
}
