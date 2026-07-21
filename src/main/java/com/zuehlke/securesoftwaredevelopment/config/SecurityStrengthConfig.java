//package com.zuehlke.securesoftwaredevelopment.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.MapSessionRepository;
//import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//
//import java.util.concurrent.ConcurrentHashMap;
//
//@Configuration
//@EnableSpringHttpSession // Ovo aktivira tvoj CookieSerializer bean
//public class SecurityStrengthConfig {
//
//    @Bean
//    public MapSessionRepository sessionRepository() {
//        return new MapSessionRepository(new ConcurrentHashMap<>());
//    }
//
//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setUseHttpOnlyCookie(true);
//        serializer.setUseSecureCookie(true);
//        serializer.setSameSite("Strict");
//        return serializer;
//    }
//}
