package com.springStarter.mongoDB.bookstore.Topic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends MongoRepository<Topic,String> {
	


}
