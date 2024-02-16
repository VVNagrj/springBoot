package com.springStarter.mongoDB.exp.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository topicRepository;
    @Override
    public List<Transaction> getTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Transaction getTopicById(String id) {
        return topicRepository.findById(id).get();
    }

    @Override
    public void addTopic(Transaction topic) {
        topicRepository.save(topic);
    }



    @Override
    public void updateTopic(String id, Transaction topic) {
        topicRepository.save(topic);
    }


    @Override
    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }


    @Scheduled(cron = "0/30 * * * * ?")
    public void myScheduledMethod() {
        // Your logic here
        System.out.println("Executing scheduled task...");
    }

}
