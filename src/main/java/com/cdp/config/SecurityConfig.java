package com.cdp.config;//package com.cdp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final FeatureConfig featureConfig;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        if (featureConfig.getSecurity().isEnabled()) {
//            http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/actuator/**").permitAll()
//                    .requestMatchers("/api/public/**").permitAll()
//                    .anyRequest().authenticated()
//                );
//        } else {
//            http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                    .anyRequest().permitAll()
//                );
//        }
//        return http.build();
//    }
//}