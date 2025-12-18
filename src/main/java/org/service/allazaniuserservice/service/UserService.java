package org.service.allazaniuserservice.service;

import lombok.RequiredArgsConstructor;
import org.service.allazaniuserservice.dto.UserRequestDto;
import org.service.allazaniuserservice.dto.UserResponseDto;
import org.service.allazaniuserservice.entity.User;
import org.service.allazaniuserservice.exception.UserAlreadyExistsException;
import org.service.allazaniuserservice.exception.UserNotFoundException;
import org.service.allazaniuserservice.mapper.UserMapper;
import org.service.allazaniuserservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        if(userRepository.findByUsername(userRequestDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username" + userRequestDto.getUsername() + " already exists");
        }
        if(userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email" + userRequestDto.getEmail() + " already exists");
        }

        User user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }


    public UserResponseDto getByUsername(UserRequestDto userRequestDto) {
        User user = userRepository.findByUsername(userRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return userMapper.toDto(user);
    }

    public UserResponseDto getByEmail(UserRequestDto userRequestDto) {
        User user = userRepository.findByEmail(userRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User with the email not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
