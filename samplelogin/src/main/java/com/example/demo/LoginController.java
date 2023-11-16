package com.example.demo;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	final String userName = "ADMIN";
	
	final String password = "password";
	
	@GetMapping("/api/login")
	public ResponseEntity<APIResponse> processLogin(@RequestBody LoginDTO loginDTO){
		APIResponse apiResponse = new APIResponse();
		if(userName.equalsIgnoreCase(loginDTO.getUserName()) && password.equalsIgnoreCase(loginDTO.getPassword())) {
			apiResponse.setSuccess("TRUE");
			apiResponse.setMessage("Login successfull");
			apiResponse.setToken(new Random().nextInt(1000000));
			User user = new User();
			user.setId(123l);
			user.setUserName(loginDTO.getUserName());
			user.setEmail("test@gmail.com");
			apiResponse.setUser(user);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		else{
			apiResponse.setSuccess("FALSE");
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setCode(401);
			errorDTO.setDetails("Invalid credentials");
			apiResponse.setError(errorDTO);
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
}
