package com.learningplatform.app.smart_learn.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learningplatform.app.smart_learn.domain.Message;

public interface MessageRepo extends MongoRepository<Message, Integer> {

}
