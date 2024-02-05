package com.springStarter.mongoDB;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.springStarter.mongoDB.exp", mongoTemplateRef = "newdb2MongoTemplate")
public class NewDb2Config {
    protected static final String MONGO_TEMPLATE = "newdb2MongoTemplate";
}
