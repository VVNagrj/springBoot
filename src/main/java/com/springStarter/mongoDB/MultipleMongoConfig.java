package com.springStarter.mongoDB;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {

    @Primary
    @Bean(name = "newdb1Properties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.first")
    public MongoProperties getNewDb1Props() {
        return new MongoProperties();
    }

    @Bean(name = "newdb2Properties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.second")
    public MongoProperties getNewDb2Props() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "newdb1MongoTemplate")
    public MongoTemplate newdb1MongoTemplate() {
        return new MongoTemplate(newdb1MongoDatabaseFactory(getNewDb1Props()));
    }

    @Bean(name = "newdb2MongoTemplate")
    public MongoTemplate newdb2MongoTemplate() {
        return new MongoTemplate(newdb2MongoDatabaseFactory(getNewDb2Props()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory newdb1MongoDatabaseFactory(MongoProperties mongo) {
        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
    }

    @Bean
    public MongoDatabaseFactory newdb2MongoDatabaseFactory(MongoProperties mongo) {
        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
    }
}
