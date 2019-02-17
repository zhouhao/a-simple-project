package me.hzhou.todo.service;

import java.io.IOException;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.message.Message;
import com.plivo.api.models.message.MessageCreateResponse;
import me.hzhou.todo.exception.MissingPropertyException;

@Service
public class PlivoService {

    @Value("${plivo.auth.id}")
    private String plivoAuthId;
    @Value("${plivo.auth.token}")
    private String plivoAuthToken;
    @Value("${plivo.source.number}")
    private String plivoSender;

    @PostConstruct
    public void plivoClient() {
        if (StringUtils.isEmpty(plivoAuthId) || StringUtils.isEmpty(plivoAuthToken) || StringUtils.isEmpty(plivoSender)) {
            throw new MissingPropertyException("plivo auth information is missing");
        }
        Plivo.init(plivoAuthId, plivoAuthToken);
    }

    public MessageCreateResponse send(String messge, String target) throws IOException, PlivoRestException {
        return Message.creator(plivoSender, Collections.singletonList(target), messge).create();
    }
}
