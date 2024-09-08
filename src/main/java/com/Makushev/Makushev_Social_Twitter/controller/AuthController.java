package com.Makushev.Makushev_Social_Twitter.controller;

import com.Makushev.Makushev_Social_Twitter.Repository.UserRepository;
import com.Makushev.Makushev_Social_Twitter.config.JwtProvider;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.request.LoginRequest;
import com.Makushev.Makushev_Social_Twitter.response.AuthResponse;
import com.Makushev.Makushev_Social_Twitter.service.CustomerUserDetailsService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findUserByEmail(user.getEmail());

        if(isExist!=null) {
            throw new Exception("email already user with another account");
        }

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword()); // создает объект Authentication с указанным email и паролем пользователя.

        String token = JwtProvider.generateToken(authentication); // создает JWT токен с помощью JwtProvider.generateToken.

        AuthResponse res = new AuthResponse(token, "Register Success"); // создает объект AuthResponse с токеном и сообщением об успешной регистрации.

        return res;

    } // good


    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
       Authentication authentication =
               authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Login Success");

        return res;
    }


    /**
         * AutoWired
         */


       private UserService userService;
       private UserRepository userRepository;
       private PasswordEncoder passwordEncoder;
       private CustomerUserDetailsService customerUserDetailsService;

       @Autowired
    public AuthController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder, CustomerUserDetailsService customerUserDetailsService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerUserDetailsService = customerUserDetailsService;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) { // проверяет, что переданный пароль соответствует зашифрованному паролю пользователя.
            throw new BadCredentialsException("password not matched");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
    }


}
