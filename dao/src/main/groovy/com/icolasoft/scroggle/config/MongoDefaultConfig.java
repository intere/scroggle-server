package com.icolasoft.scroggle.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by einternicola on 12/13/15.
 */
@Configuration
@ComponentScan("com.icolasoft.scroggle")
@Profile("default")
public class MongoDefaultConfig extends MongoDevConfig {
}
