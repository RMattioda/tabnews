package com.rodest;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableEncryptableProperties
@SpringBootApplication
public class ConfigServer {

//	 docker-compose -f .\common.yml -f .\kafka_cluster.yml -f .\services.yml -f .\elastic_cluster.yml up
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
    }
}
