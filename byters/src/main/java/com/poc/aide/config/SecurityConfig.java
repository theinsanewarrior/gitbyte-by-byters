//package com.poc.aide.config;
//
//import com.poc.aide.dtos.AppUser;
//import com.poc.aide.entities.User;
//import com.poc.aide.enums.Role;
//import com.poc.aide.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    @Autowired
//    UserRepository userRepository;
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
////                .formLogin(Customizer.withDefaults())
//                .oauth2Login(oc -> oc.userInfoEndpoint(ui -> ui.userService(oAuth2LoginHandler())))
//                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
//                .build();
//    }
//    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2LoginHandler() {
//        return userRequest -> {
//            DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
//            OAuth2User oAuth2User = delegate.loadUser(userRequest);
//            AppUser appUser = AppUser.builder()
//                    .username(oAuth2User.getAttribute("login"))
//                    .attributes(oAuth2User.getAttributes())
//                    .authorities(oAuth2User.getAuthorities())
//                    .build();
//            if (userRepository.findByUsername(appUser.getUsername())==null){
//                User user = User.builder()
//                        .username(appUser.getUsername())
//                        .role(Role.USER)
//                        .build();
//                userRepository.save(user);
//            }
//            return appUser;
//        };
//    }
//    @Bean
//    ApplicationListener<AuthenticationSuccessEvent> successLogger(){
//        return event -> {
//            System.out.println("success: {}" + event.getAuthentication());
//        };
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
