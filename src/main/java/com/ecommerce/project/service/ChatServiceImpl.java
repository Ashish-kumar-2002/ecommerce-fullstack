package com.ecommerce.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.ResponseCreateParams;

@Service
public class ChatServiceImpl implements ChatService {

    private final OpenAIClient client;

    public ChatServiceImpl(
            @Value("${openai.api.key}") String apiKey) {

        this.client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }

    @Override
    public String getChatResponse(String message) {

        ResponseCreateParams params =
                ResponseCreateParams.builder()
                        .model("gpt-5.6-luna")
                        .input(message)
                        .build();

        var response = client.responses().create(params);

         return response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(messageItem -> messageItem.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(outputText -> outputText.text())
                .findFirst()
                .orElse("Sorry, I could not generate a response.");
    }
}