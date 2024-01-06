package com.springStarter.Topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

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
