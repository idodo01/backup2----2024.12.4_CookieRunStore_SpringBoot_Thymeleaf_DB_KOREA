package com.koreait.store.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure -> {
            configure.requestMatchers("/static/**").permitAll();
            configure.requestMatchers("/user/join").permitAll();
            configure.anyRequest().permitAll();
        });

        http.formLogin(configure -> {
            configure.loginPage("/user/login")
                    .loginProcessingUrl("/user/login")
                    .usernameParameter("id")
                    .passwordParameter("password")
                    .permitAll();
        });

        http.logout(configure -> {
            configure.logoutUrl("/user/logout").permitAll();
            configure.invalidateHttpSession(true);
            configure.deleteCookies("JSESSIONID");
            configure.clearAuthentication(true);
            configure.logoutSuccessUrl("/");
        });

//        http.exceptionHandling(configure -> {
//            configure.accessDeniedPage("/security/deny");
//        });

        http.rememberMe(configure -> {
            configure.userDetailsService(userDetailsService);
            configure.tokenRepository(persistentTokenRepository());
            configure.tokenValiditySeconds(60 * 60 * 24 * 7);
        });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
}
