package com.blog.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.exception.ResourseNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepository.findById(userId).orElseThrow
				(()-> new ResourseNotFoundException("User", "Id", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepository.save(user);
		UserDto userDto2 = this.userToDto(updatedUser);
		return userDto2;
	}

	@Override
	public UserDto getUserById(Integer userId) {
	
		User user = this.userRepository.findById(userId).orElseThrow
				(()-> new ResourseNotFoundException("User", "Id", userId));
		UserDto userById = this.userToDto(user);
		return userById;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> allUsers = this.userRepository.findAll();
		List<UserDto> allUserDtos = allUsers.stream().map(user -> this.userToDto(user))
				.collect(Collectors.toList());
		
		return allUserDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		this.userRepository.deleteById(userId);	
	}

	private User dtoToUser(UserDto userDto) {
		
		User user= new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto userToDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
}
