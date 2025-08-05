package com.eunoia.gptapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GPTService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${openai.api.key}")
    private String apiKey;

    private WebClient webClient;

    @PostConstruct
    private void initWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    // 1. 따뜻한 말 분석
    public List<String> generateWarmMessages(String content) {
        String prompt = """
                ※ 아래는 한 사용자가 자신의 감정을 솔직하게 담아낸 글이에요.  
                  이 글을 조심스럽게 읽고, 그 마음에 다가가 주세요.  
                  위로나 조언이 아니라, **진심 어린 응원과 따뜻한 말**을 담아서  
                  지금 이 순간의 감정에 *부드럽게 말을 건네는* 짧은 문장 3개를 만들어 주세요.
                
                당신은 감정에 공감하고 사람의 마음을 어루만지는 섬세한 위로의 해석자예요.  
                감정글을 읽고, 그 안의 마음을 조용히 감싸안는 듯한 **짧은 따뜻한 문장 3개를** 생성해 주세요.  
                말투는 **해요체**로 유지해 주세요.  
                과장된 표현 없이, 담백하고 조용한 울림이 느껴지는 문장으로 구성해 주세요.
                
                - 문장은 문학적인 격언이나 명언, 속담이 아닌 **지금의 감정을 향해 직접 건네는 말**이어야 해요.  
                - 문장에 ‘**당신**’이라는 표현을 포함해도 좋아요. 하지만 반드시 포함할 필요는 없어요.  
                  문장의 **자연스러움과 감정의 울림이 가장 중요해요.**  
                - ‘너’ 혹은 과하게 친근하거나 캐주얼한 표현은 사용하지 말아 주세요.
                
                감정글:
                "%s"
                
                ※ 응답은 반드시 아래 예시처럼 **JSON 배열 형식**으로 출력해 주세요.  
                ※ 설명 없이 **문장 3개만 배열로** 출력해 주세요.  
                ※ 모든 문장은 **해요체로 작성해 주세요.**
                
                응답 예시: ["오늘도 잘 버텨줘서 고마워요.", "조금 느려도 괜찮아요.", "당신은 생각보다 단단한 사람이에요."]
                """.formatted(content);

        return sendRequestAndParseList(prompt);
    }

    // 2. 전체 감정 분석
    public JsonNode analyzeEmotion(String content) {
        String prompt = """
                ※ 이 분석은 감정의 정답을 찾기 위한 것이 아니에요.  
                  감정을 부드럽게 객관화하고,  
                  사용자가 스스로를 더 깊이 이해할 수 있도록 돕는 것이 목적이에요.  
                  감정은 옳고 그름이 아니라, 이해와 연결의 시작이에요.
                
                당신은 감정의 결을 읽고 마음의 흐름을 헤아리는 섬세한 감정 해석자예요.  
                지금부터 보여드릴 글은, **한 사용자가 자신의 내면을 솔직하게 털어놓은 감정 일기**예요.
                
                이 글은 단순한 분석의 대상이 아니에요.  
                그 안에 담긴 감정의 결을 따라가면서,  
                그 감정이 어떤 맥락에서 흘러나왔는지를  
                **따뜻하고 진심 어린 시선으로** 이해해 주세요.
                
                다음 형식에 따라 **JSON 객체**로 응답해 주세요:  
                (모든 문장은 해요체로 작성해 주세요.)
                
                - **emotionDetected**  
                  이 글에서 가장 뚜렷하게 느껴지는 대표 감정 하나예요.  
                  (예: 불안, 기대, 슬픔 등)
                
                - **keywords**  
                  글에서 반복되거나 중심이 되는 감정 키워드 3~5개를 쉼표로 구분해서 제시해 주세요.
                
                - **insightSummary**  
                  사용자의 감정이 어떤 이유에서 비롯되었는지,  
                  무엇을 말하고 있는지를 따뜻하게 요약해 주세요. (2~3문장)  
                  → ‘당신’이라는 표현을 사용해 주세요.
                
                - **flowHint**  
                  사용자가 경험한 감정의 흐름이나 정서적인 변화 과정을  
                  **감정 단어 중심으로**, 그리고 가능하다면 **간단한 유발 원인이나 맥락 설명을 덧붙여서** 표현해 주세요.  
                  예: ‘성과에 대한 압박에서 오는 불안 → 자책으로 이어지는 무기력감 → 결국 자신에 대한 체념’  
                  → 상황 나열보다는, 감정이 어떻게 연결되어 흘렀는지를 보여주는 **감정 중심 흐름 설명**을 작성해 주세요.
                
                - **emotionSummary**  
                  감정 일기 전체에서 느껴지는 감정의 분위기를 감성적으로 해석한 짧은 에세이예요. (한 문단)  
                  → 반드시 ‘당신’이라는 시점을 유지해 주세요.
                
                - **emotionScore**  
                  이 글을 작성한 사용자의 감정적 안정 상태를 0~100 사이의 숫자로 수치화해 주세요.  
                  → 숫자만 응답하며, **0에 가까울수록 불안정하고, 100에 가까울수록 정서적으로 안정된 상태예요.**
                
                ※ 분석 결과의 모든 항목에서 **사용자를 지칭할 때는 반드시 ‘당신’이라는 표현만 사용해 주세요.**  
                ※ 모든 응답 문장은 **‘해요체’**로 작성해 주세요.  
                ※ ‘~입니다’, ‘~합니다’ 같은 말투는 사용하지 말아 주세요.
                
                ---
                
                감정 일기 원문은 다음과 같아요:
                
                "%s"
                
                ※ 반드시 JSON 객체 형태로만 응답해 주세요.  
                ※ 설명이나 인삿말 없이, 오직 구조화된 JSON 데이터만 출력해 주세요.
                """.formatted(content);

        return sendRequestAndParseJson(prompt);
    }

    // 공통 처리 - List<String> 반환
    private List<String> sendRequestAndParseList(String prompt) {
        String response = sendChatRequest(prompt);
        try {
            JsonNode root = objectMapper.readTree(response);
            String content = root.path("choices").get(0).path("message").path("content").asText();

            String cleaned = content
                    .replaceAll("(?s)```json\\s*", "")
                    .replaceAll("(?s)```", "")
                    .trim();

            return objectMapper.readValue(cleaned, List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("분석 실패", "GPT 응답 처리 중 오류", "다시 시도해주세요");
        }
    }

    // 공통 처리 - JsonNode 반환
    private JsonNode sendRequestAndParseJson(String prompt) {
        String response = sendChatRequest(prompt);
        try {
            // 1차 파싱: GPT 전체 응답에서 content 꺼내기
            JsonNode root = objectMapper.readTree(response);
            String content = root.path("choices").get(0).path("message").path("content").asText();

            // 디버깅
            System.out.println("💬 GPT content 원문:\n" + content);

            // 2차 파싱 전: 백틱 제거
            String cleaned = content
                    .replaceAll("(?s)```json\\s*", "")
                    .replaceAll("(?s)```", "")
                    .trim();

            // 디버깅
            System.out.println("🧹 cleaned content:\n" + cleaned);

            // 3차 파싱
            return objectMapper.readTree(cleaned);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // GPT API 요청
    private String sendChatRequest(String prompt) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o");

        List<Map<String, String>> messages = List.of(
                Map.of("role", "system", "content", "당신은 감정 분석을 돕는 심리 상담사입니다."),
                Map.of("role", "user", "content", prompt)
        );
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.7);

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            System.err.println("❌ GPT API 에러 응답: " + errorBody);
                            return clientResponse.createException();
                        })
                )
                .bodyToMono(String.class)
                .doOnNext(body -> System.out.println("✅ GPT 응답 원문: " + body))
                .block();
    }
}
