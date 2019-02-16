package me.hzhou.springdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.plivo.api.Plivo;
import com.plivo.api.PlivoClient;
import me.hzhou.springdata.exception.MissingPropertyException;

@Configuration
public class AppConfiguration {

    @Value("${plivo.auth.id}")
    private String plivoAuthId;
    @Value("${plivo.auth.token}")
    private String plivoAuthToken;

    @Bean
    public PlivoClient plivoClient() {
        if (StringUtils.isEmpty(plivoAuthId) || StringUtils.isEmpty(plivoAuthToken)) {
            throw new MissingPropertyException("plivo auth information is missing");
        }
        return Plivo.init(plivoAuthId, plivoAuthToken);
    }
}
