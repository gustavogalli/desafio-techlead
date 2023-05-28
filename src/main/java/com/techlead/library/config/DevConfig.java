package com.techlead.library.config;

import com.techlead.library.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("S{spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean startsDB(){
        if(value.equals("create")){
            this.dbService.startsDB();
        }
        return false;
    }
}
