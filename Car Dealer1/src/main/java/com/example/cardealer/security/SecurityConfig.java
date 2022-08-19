package com.example.cardealer.security;


import com.example.cardealer.dto.Constants;
import com.example.cardealer.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

//        http.authorizeRequests()
//                        .antMatchers("/cars-management").hasAuthority(Constants.ROLE_ADMIN);
//
        http.authorizeRequests().antMatchers("/cars-management")
                .access("hasRole( '" + Constants.ROLE_ADMIN + "')");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().antMatchers(
                "/css/**",
                "/js/**",
                "/img/**",
                "/uploaded/*",
                "/vendor/**",
                "/images/*",
                "/fonts/**",
                "/login",
                "/register",
                "/index",
                "/car/*",
                "/cars"
        ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/")
                .defaultSuccessUrl("/home")
                .failureUrl("/login=fail")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll().and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/");
    }
}
