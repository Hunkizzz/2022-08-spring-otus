package ru.otus.education.spring.security.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.otus.education.spring.security.springsecurity.service.AuthorityService;
import ru.otus.education.spring.security.springsecurity.service.UserService;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final DataSource dataSource;
    private final UserService userService;
    private final AuthorityService authorityService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users " +
                                "where username=?")
                .authoritiesByUsernameQuery(
                        "select username_id, authority from authorities " +
                                "where username_id = (select id from users t where t.username = ?)");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        httpSecurity.csrf()
                .ignoringAntMatchers("/h2-console/**");
        httpSecurity.headers()
                .frameOptions()
                .sameOrigin();
        return httpSecurity.build();
    }


}
