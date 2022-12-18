package com.example.todolist.config;

import com.example.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.inject.Named;
import javax.servlet.Filter;

import javax.servlet.Filter;
import javax.inject.Inject;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class SecurityConfig {
    @Autowired
    private UserRepository userRepository;


    @Qualifier("oauth2ClientContext")
    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
        // TODO
        /*http.authorizeRequests()
                .antMatchers("/registration", "/login","/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/tasks/categoryId=0", true).permitAll()
                .and()
                .logout().permitAll();
        http.addFilterBefore(ssoFilter(), UsernamePasswordAuthenticationFilter.class);*/
        //http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails getGoogleCodeResourceDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    public FilterRegistrationBean oAuth2ClientFilterRegistration(OAuth2ClientContextFilter oAuth2ClientContextFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(oAuth2ClientContextFilter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties getGoogleResource() {
        return new ResourceServerProperties();
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(getGoogleCodeResourceDetails(), oAuth2ClientContext);
        googleFilter.setRestTemplate(googleTemplate);
        CustomUserInfoTokenServices tokenServices = new CustomUserInfoTokenServices(getGoogleResource().getUserInfoUri(), getGoogleCodeResourceDetails().getClientId());
        tokenServices.setRestTemplate(googleTemplate);
        googleFilter.setTokenServices(tokenServices);
        tokenServices.setUserRepository(userRepository);
        tokenServices.setPasswordEncoder(getPasswordEncoder());
        return googleFilter;
    }

}