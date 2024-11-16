package com.pikake.api.pikakeapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PikakeApiApplication {

    private static final Logger LOGGER = LogManager.getLogger(PikakeApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PikakeApiApplication.class, args);

    }

}
