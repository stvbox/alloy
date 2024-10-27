package com.grafana.demo;

import io.micrometer.observation.annotation.Observed;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    private final RestTemplateBuilder builder;

    public HelloController(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    @Observed(name = "test-test-0")
    @RequestMapping("/test")
    public String index() {

        RestTemplate client = builder.build();

        log.info("enpoint: {} {}", "test-test-0", "/test");

        client.getForEntity("http://localhost:1235/test1", String.class);

        return "Greetings from Spring Boot!";
    }

    @Observed(name = "test-test-1-1")
    @RequestMapping("/test1")
    public String index1() {

        RestTemplate client = builder.build();

        log.info("enpoint: {} {}", "test-test-1", "/test1");

        client.getForEntity("http://localhost:1235/test2", String.class);

        return "Greetings from Spring Boot!";
    }

    @Observed(name = "test-test-2")
    @RequestMapping("/test2")
    public String index1(HttpServletRequest request) {

        RestTemplate client = builder.build();

        log.info("enpoint: {} {}", "test-test-1", "/test2");
        client.getForEntity("http://localhost:1235/test3", String.class);

        return "Greetings from Spring Boot!";
    }

    @Observed(name = "test-test-3")
    @RequestMapping("/test3")
    public String index2(HttpServletRequest request) {

        RestTemplate client = builder.build();

        Random rn = new Random();
        int rand = rn.nextInt(2) + 1;

        log.info("enpoint: {} {}", "test-test-1", "/test3");

        if (rand > 1) {
            client.getForEntity("http://localhost:1235/test" + rand, String.class);
        }

        log.error("Конец цепочки вызовов");

        return "Greetings from Spring Boot!";
    }
}
