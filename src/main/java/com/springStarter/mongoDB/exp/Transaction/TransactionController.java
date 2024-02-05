package com.springStarter.mongoDB.exp.Transaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Api(tags = "Transaction Controller", description = "Operations pertaining to topics")
public class TransactionController {

	@Autowired
	private TransactionService topicService;

	@GetMapping
	@ApiOperation("Get all topics")
	public List<Transaction> getAllTopics() {
		return topicService.getTopics();
	}

	@GetMapping("/{id}")
	@ApiOperation("Get a topic by ID")
	public Transaction getTopicById(@PathVariable String id) {
		return topicService.getTopicById(id);
	}

	@PostMapping
	@ApiOperation("Add a new topic")
	public void addTopic(@RequestBody Transaction topic) {
		topicService.addTopic(topic);
	}

	@PutMapping("/{id}")
	@ApiOperation("Update a topic by ID")
	public void updateTopic(@PathVariable String id, @RequestBody Transaction topic) {
		topicService.updateTopic(id, topic);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete a topic by ID")
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
}
