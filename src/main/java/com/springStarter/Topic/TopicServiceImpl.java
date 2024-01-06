package com.springStarter.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(String id) {
        return topicRepository.findById(id).get();
    }


    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }



    public void updateTopic(String id, Topic topic) {
        topicRepository.save(topic);
    }


    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }


}
