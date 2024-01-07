package com.springStarter.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "User Controller", description = "Operations pertaining to Users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	@ApiOperation("Get all Users")
	public List<User> getAllUsers() {
		return userService.getUsers();
	}

	@GetMapping("/{id}")
	@ApiOperation("Get a User by ID")
	public User getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}

	@PostMapping
	@ApiOperation("Add a new User")
	public void addUser(@RequestBody User User) {
		userService.addUser(User);
	}

	@PutMapping("/{id}")
	@ApiOperation("Update a User by ID")
	public void updateUser(@PathVariable String id, @RequestBody User User) {
		userService.updateUser(id, User);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete a User by ID")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}
}
