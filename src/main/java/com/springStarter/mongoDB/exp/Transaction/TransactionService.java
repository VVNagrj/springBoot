package com.springStarter.mongoDB.exp.Transaction;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {


	public List<Transaction> getTopics();

    public Transaction getTopicById(String id);


    public void addTopic(Transaction topic);


    public void updateTopic(String id, Transaction topic);


    public void deleteTopic(String id);


}
