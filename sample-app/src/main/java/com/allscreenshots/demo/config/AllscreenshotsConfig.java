package com.allscreenshots.demo.config;

import com.allscreenshots.sdk.client.AllscreenshotsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllscreenshotsConfig {

    @Bean
    public AllscreenshotsClient allscreenshotsClient() {
        return AllscreenshotsClient.builder().build();
    }
}
