package com.payment.demo.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("rzp_test_CcY3oiWr8HiQMZ", "dKb0s8IulcLRG0QvuMHtfb8d");
    }
}
