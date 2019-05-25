package springbootrestapi.controller;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springbootrestapi.dto.AddressDto;
import springbootrestapi.dto.ApiResponseDto;
import springbootrestapi.dto.ResponseType.Response;
import springbootrestapi.dto.UserDto;
import springbootrestapi.model.User;
import springbootrestapi.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllUsers() {
		
		ResponseEntity response = null;
		try {
			Optional data = userService.getAllUsers();
			if (data.isPresent()) {
				List<User> users = (List<User>) data.get();				
				response = new ResponseEntity<>(new ApiResponseDto(Response.SUCCESS,
						users.stream().map(user -> convertToDto(user)).collect(Collectors.toList())), HttpStatus.OK); 
			} else {
				response =  new ResponseEntity<>(new ApiResponseDto(Response.NOT_FOUND), HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			checkResponseEntityForNull(response);
		}
		return ResponseEntity.ok(response);

	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUserByUserId(@PathVariable("userId") Long userId) {
		
		ResponseEntity response = null;
		try {
			Optional data = userService.getUserById(userId);
			if (data.isPresent()) {
				User user = (User) data.get();
				response = new ResponseEntity<>(new ApiResponseDto(Response.SUCCESS, convertToDto(user)), HttpStatus.OK);
			} else {
				response =  new ResponseEntity<>(new ApiResponseDto(Response.NOT_FOUND), HttpStatus.NOT_FOUND);
			}
		}finally {
			checkResponseEntityForNull(response);
		}
		return response;
	}
	
	private ResponseEntity checkResponseEntityForNull(ResponseEntity response) {
		return response == null ? new ResponseEntity<>(new ApiResponseDto(Response.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR) : response; 
	}
	
	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setCreationTime(user.getCreatedAt());
		userDto.setUpdatedTime(user.getLastModifiedAt());
		if (user.getAddress() != null) {
			userDto.setAddressDto(modelMapper.map(user.getAddress(), AddressDto.class));
		}
		return userDto;
	}

	private User convertToEntity(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

}
