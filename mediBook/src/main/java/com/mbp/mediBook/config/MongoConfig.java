package com.mbp.mediBook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.medibook.repository")
@EnableMongoAuditing
public class MongoConfig {
    // MongoDB configuration is handled by application.properties
}