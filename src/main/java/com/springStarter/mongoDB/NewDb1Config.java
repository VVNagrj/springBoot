package com.springStarter.mongoDB;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.springStarter.mongoDB.bookstore", mongoTemplateRef = "newdb1MongoTemplate")
public class NewDb1Config {
    protected static final String MONGO_TEMPLATE = "newdb1MongoTemplate";
}
