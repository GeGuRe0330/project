package com.eunoia.gptapi;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GptClient {

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.openai.com/v1";
    private static final String MODEL = "gpt-4o";
    private static final double TEMPERATURE = 0.7;

    private WebClient webClient;

    @PostConstruct
    private void initWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String call(String prompt) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", MODEL);

        List<Map<String, String>> messages = List.of(
                Map.of("role", "system", "content", "당신은 감정 분석을 돕는 심리 상담사입니다."),
                Map.of("role", "user", "content", prompt));

        requestBody.put("messages", messages);
        requestBody.put("temperature", TEMPERATURE);

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            System.err.println("❌ GPT API 에러 응답: " + errorBody);
                            return clientResponse.createException();
                        }))
                .bodyToMono(String.class)
                .doOnNext(body -> System.out.println("✅ GPT 응답 원문: " + body))
                .block();
    }
}
