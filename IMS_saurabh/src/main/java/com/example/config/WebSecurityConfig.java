package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses =  com.example.security.CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired
 private UserDetailsService userDetailsService;

 @Autowired
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(userDetailsService);
//             .passwordEncoder(passwordencoder());
 }



 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http.authorizeRequests()
          .antMatchers("/Home").access("hasRole('ROLE_ADMIN')")
//          .antMatchers("/Home").access("hasRole('ROLE_USER')")
          .antMatchers("/User_Register").access("hasRole('ROLE_USER')")
          .anyRequest().authenticated()
          .and()
          .formLogin().loginPage("/login")
          .usernameParameter("username").passwordParameter("password").permitAll()
          .and()
          .logout()
          .logoutSuccessUrl("/login?logout")
          .permitAll()
          .and()
          .exceptionHandling().accessDeniedPage("/403")
          .and()
          .csrf().disable();
 }
//
// @Bean(name="passwordEncoder")
//    public PasswordEncoder passwordencoder(){
//     return new BCryptPasswordEncoder();
//    }
}