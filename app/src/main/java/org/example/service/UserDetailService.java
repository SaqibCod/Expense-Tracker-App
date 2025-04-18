package org.example.service;

import lombok.Data;
import org.example.entities.UserInfo;
import org.example.repository.UserRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.logging.Logger;

@Data
public class UserDetailService implements UserDetailsService {
    @Autowired
    private final UserRepo userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


//    private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userRepository.findByUsername(username);
        if(user == null){
//            log.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
//        log.info("User Authenticated Successfully..!!!");
        return new CustomUserDetail(user);
    }
}
