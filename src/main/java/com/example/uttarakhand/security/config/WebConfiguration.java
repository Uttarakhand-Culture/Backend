package com.example.uttarakhand.security.config;

import org.springframework.http.HttpMethod;
import com.example.uttarakhand.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity(debug = true) // Do not use (debug=true) in a production system! as this contain sensitive information.
public class WebConfiguration {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public WebConfiguration(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests((authorize) -> authorize
                        // make sure it is in order to access the proper Url
                          .requestMatchers(HttpMethod.POST,"/signup").permitAll()
                          .requestMatchers(HttpMethod.POST,"/login").permitAll()
                          .requestMatchers("/api/v*/registration/**").permitAll()
                          .requestMatchers("/home").permitAll()
                          .requestMatchers("/profile").authenticated()
                          .requestMatchers("/admin").hasAuthority("ADMIN")

//                        .requestMatchers(HttpMethod.POST,"/register").permitAll()
//                        .requestMatchers("/home").permitAll()
//                        .requestMatchers("/register/**").permitAll() // denyAll will deny all. we can use it when we do Maintenance
//                        .requestMatchers("/user").hasAuthority("user")
//                        .requestMatchers("/admin").hasAuthority("admin")
                        .anyRequest().authenticated()
                )
                .csrf((csrf) -> csrf.disable())
                .httpBasic(withDefaults());
        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoauthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoauthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }


//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:8080")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
//                        .allowCredentials(true);
//            }
//
//        };
//    }


}
