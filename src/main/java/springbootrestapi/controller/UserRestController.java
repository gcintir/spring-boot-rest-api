package springbootrestapi.controller;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllUsers() {

		ApiResponseDto response = null;
		try {
			Optional data = userService.getAllUsers();
			if (data.isPresent()) {
				List<User> users = (List<User>) data.get();
				response = new ApiResponseDto(Response.SUCCESS,
						users.stream().map(user -> convertToDto(user)).collect(Collectors.toList()));
			} else {
				response = new ApiResponseDto(Response.SUCCESS);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null == response) {
				response = new ApiResponseDto(Response.INTERNAL_SERVER_ERROR);
			}
		}
		return ResponseEntity.ok(response);

	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUserByUserId(@PathVariable("userId") Long userId) {
		
		ApiResponseDto response = null;
		try {
			Optional data = userService.getUserById(userId);
			if (data.isPresent()) {
				User user = (User) data.get();
				response = new ApiResponseDto(Response.SUCCESS, convertToDto(user));
			}else {
				response = new ApiResponseDto(Response.NOT_FOUND);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null == response) {
				response = new ApiResponseDto(Response.INTERNAL_SERVER_ERROR);
			}
		}
		return ResponseEntity.ok(response);

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
