package org.service.allazaniuserservice.service;

import lombok.RequiredArgsConstructor;
import org.service.allazaniuserservice.dto.UserRequestDto;
import org.service.allazaniuserservice.dto.UserResponseDto;
import org.service.allazaniuserservice.entity.User;
import org.service.allazaniuserservice.exception.UserNotFoundException;
import org.service.allazaniuserservice.mapper.UserMapper;
import org.service.allazaniuserservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
