package com.example.cars.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("idUserDetailImpl")
public class UserDetailsImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("horacio")) {
            return User.withDefaultPasswordEncoder()
                    .username("horacio")
                    .password("123")
                    .roles("USER")
                    .build();
        } else if (username.equals("admin")) {
            return User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin")
                    .roles("USER", "ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("Usuário não encontrado!");
    }

    @Bean
    public PasswordEncoder passwordEncoderUserDetailsService() {
        return new BCryptPasswordEncoder();
    }
}
