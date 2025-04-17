package com.henrique.to_do_list.configuration;

import com.henrique.to_do_list.Model.UserSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class SessionConfiguration {
    @Bean
    @Scope(value="session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserSession userSession(){
        return new UserSession();
    }
}
