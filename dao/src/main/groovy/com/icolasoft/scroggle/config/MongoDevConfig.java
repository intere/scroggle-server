package com.icolasoft.scroggle.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by einternicola on 11/14/15.
 */
@Configuration
@ComponentScan("com.icolasoft.scroggle")
@Profile("dev")
public class MongoDevConfig extends MongoGlobalConfig {
    private static final Logger LOG = Logger.getLogger(MongoDevConfig.class);

    @Autowired private MongoDbFactory mongoDbFactory;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        LOG.info("Using DEV Mongo Config");
        MongoDbFactory factory = new SimpleMongoDbFactory(mongoClient, "scroggle-dev");
        return factory;
    }
}
