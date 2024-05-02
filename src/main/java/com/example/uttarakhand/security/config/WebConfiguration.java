package com.example.uttarakhand.security.config;

import com.example.uttarakhand.user.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true) // Do not use (debug=true) in a production system! as this contain sensitive information.
public class WebConfiguration {

    private final UserService userService;

    public WebConfiguration(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    AuthenticationProvider authenticationProvider (){
//
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return daoAuthenticationProvider;
//    }


    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests((authorize) -> authorize
                        // make sure it is in order to access the proper Url
                          .requestMatchers("/api/v*/registration/**").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/register").permitAll()
//                        .requestMatchers("/home").permitAll()
//                        .requestMatchers("/register/**").permitAll() // denyAll will deny all. we can use it when we do Maintenance
//                        .requestMatchers("/user").hasAuthority("user")
//                        .requestMatchers("/admin").hasAuthority("admin")
                        .anyRequest().permitAll()

                )
                .csrf((csrf) -> csrf.disable())
                .formLogin(withDefaults())
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

}
