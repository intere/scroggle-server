package com.icolasoft.scroggle.config;

import com.mongodb.MongoClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * Created by einternicola on 11/14/15.
 */
public class MongoGlobalConfig {
    private static final Logger LOG = Logger.getLogger(MongoGlobalConfig.class);

    @Autowired protected MongoClient mongoClient;
    @Autowired protected MongoDbFactory mongoDbFactory;
    @Autowired protected DbRefResolver dbRefResolver;
    @Autowired protected MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;
    @Autowired protected MappingMongoConverter mappingMongoConverter;
    @Autowired protected GridFsTemplate gridFsTemplate;

    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = new MongoClient(getEnvironmentHost(), getMongoPort());
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        return mongoTemplate;
    }

    @Bean
    public DbRefResolver dbRefResolver() {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        return dbRefResolver;
    }

    @Bean
    public MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext() {
        MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty>mappingContext = new MongoMappingContext();
        return mappingContext;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
        return mappingMongoConverter;
    }

    @Bean
    public GridFsTemplate gridFsTemplate() {
        GridFsTemplate gridFsTemplate = new GridFsTemplate(mongoDbFactory, mappingMongoConverter);
        return gridFsTemplate;
    }

    public static int getMongoPort() {
        String mongoPort = System.getenv("MONGODB_PORT_27017_TCP_PORT");
        LOG.info("MONGODB_PORT_27017_TCP_PORT=" + mongoPort);

        if(null == mongoPort || mongoPort.isEmpty()) {
            mongoPort = System.getenv("MONGODB_PORT");
            LOG.info("MONGODB_PORT=" + mongoPort);
        }

        if(null!= mongoPort && !mongoPort.isEmpty()) {
            try {
                return Integer.parseInt(mongoPort);
            } catch(NumberFormatException ex) {
                // We didn't parse the port correctly.
            }
        }

        int defaultPort = 27017;

        LOG.info("DEFAULT=" + defaultPort);
        return defaultPort;
    }

    public static String getEnvironmentHost() {

        // Check for presence of Docker Mongo Host Environment Variable:
        String mongoHost = System.getenv("MONGODB_PORT_27017_TCP_ADDR");
        LOG.info("MONGODB_PORT_27017_TCP_ADDR=" + mongoHost);


        // if that's not found, use the "alternative" (aka- we're not running in docker) environment variable.
        if(null == mongoHost || mongoHost.isEmpty()) {
            mongoHost = System.getenv("MONGODB_ADDR");
            LOG.info("MONGODB_ADDR=" + mongoHost);
        }

        // If neither of those are specified, default to the loopback interface:
        if(null == mongoHost || mongoHost.isEmpty()) {
            mongoHost = "127.0.0.1";
            LOG.info("DEFAULT=" + mongoHost);
        }

        return mongoHost;
    }
}
