package com.ntt.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.apirest.domain.classes.UserUpdateRequest;
import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.domain.errors.RegistrationException;
import com.ntt.apirest.domain.errors.UserNotFoundException;
import com.ntt.apirest.domain.service.UserService;
import com.ntt.apirest.models.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "USER CONTROLLER", description = "User operations")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
	private UserService userService;

    /**
	 * Method: GET.
	 * Return: All users.
	 */
	@Operation(description = "Get Users", summary = "USER CONTROLLER")
	@GetMapping("/")
	List<User> getUsers() throws Exception {
		return userService.getAllUsers();
	}

	/**
	 * Method: GET.
	 * Return: User by ID.
	 */
	@Operation(description = "Get User by id", summary = "USER CONTROLLER")
	@GetMapping("/{id}")
	User getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}

	/**
     * Method: POST.
     * Description: Create a new User.
	 * @throws RegistrationException
     */
    @Operation(description = "Create a new User", summary = "USER CONTROLLER")
    @PostMapping("/")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequest) throws RegistrationException {
        return userService.createUser(userRequest);
    }

	/**
     * Method: PUT.
     * Description: Update a User.
	 * @throws UserNotFoundException
     */
    @Operation(description = "Update a User", summary = "USER CONTROLLER")
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequest userRequest) throws UserNotFoundException {
        return userService.updateUser(userId, userRequest);
    }

    /**
     * Method: PATCH.
     * Description: Update a User partially.
	*/
    @Operation(description = "Update a User partially", summary = "USER CONTROLLER")
    @PatchMapping("/{userId}")
    public Boolean patchUser(@PathVariable("userId") int userId, @RequestBody Object partialUser) {
        return true;
    }

    /**
     * Method: DELETE.
     * Description: Delete a User.
	*/
    @Operation(description = "Delete a User", summary = "USER CONTROLLER")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);;
    }

    
}
