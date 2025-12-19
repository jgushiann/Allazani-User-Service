package org.service.allazaniuserservice.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.service.allazaniuserservice.dto.UserRequestDto;
import org.service.allazaniuserservice.dto.UserResponseDto;
import org.service.allazaniuserservice.entity.Role;
import org.service.allazaniuserservice.entity.User;
import org.service.allazaniuserservice.exception.UserAlreadyExistsException;
import org.service.allazaniuserservice.exception.UserNotFoundException;
import org.service.allazaniuserservice.mapper.UserMapper;
import org.service.allazaniuserservice.repository.UserRepository;
import org.service.allazaniuserservice.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

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

    public String loginUser(UserRequestDto userRequestDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(), userRequestDto.getPassword());
        Authentication authenticationResult = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);

        return jwtUtil.generateToken((UserDetails) authenticationResult.getPrincipal());
    }

    public UserResponseDto getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id" + id + " not found"));
        return userMapper.toDto(user);
    }

    public UserResponseDto getUserByUsername(UserRequestDto userRequestDto) {
        User user = userRepository.findByUsername(userRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return userMapper.toDto(user);
    }

    public UserResponseDto getUserByEmail(UserRequestDto userRequestDto) {
        User user = userRepository.findByEmail(userRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User with the email not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public UserResponseDto updateRole(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id" + id + " not found"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteCurrentUser(){
        userRepository.deleteById(0L);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
