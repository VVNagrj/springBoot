package com.springStarter.mongoDB.exp.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository topicRepository;

    public List<Transaction> getTopics() {
        return topicRepository.findAll();
    }

    public Transaction getTopicById(String id) {
        return topicRepository.findById(id).get();
    }


    public void addTopic(Transaction topic) {
        topicRepository.save(topic);
    }



    public void updateTopic(String id, Transaction topic) {
        topicRepository.save(topic);
    }


    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }


}
