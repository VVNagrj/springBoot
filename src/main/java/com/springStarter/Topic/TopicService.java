package com.springStarter.Topic;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {


	public List<Topic> getTopics();

    public Topic getTopicById(String id);


    public void addTopic(Topic topic);


    public void updateTopic(String id, Topic topic);


    public void deleteTopic(String id);


}
