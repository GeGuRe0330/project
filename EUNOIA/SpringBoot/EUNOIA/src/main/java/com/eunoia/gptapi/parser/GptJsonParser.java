package com.eunoia.gptapi.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GptJsonParser {

    private final ObjectMapper objectMapper;

    public JsonNode parse(String rawResponse) {
        try {
            // 1) OpenAI 전체 응답(JSON) -> choices[0].message.content 꺼내기
            JsonNode root = objectMapper.readTree(rawResponse);
            String content = root.path("choices").get(0).path("message").path("content").asText();

            // 디버깅(필요 시)
            System.out.println("💬 GPT content 원문:\n" + content);

            // 2) content에서 JSON 객체만 안전하게 추출
            String cleaned = stripCodeFences(content);
            String jsonObjectOnly = extractJsonObject(cleaned);

            System.out.println("🧹 cleaned content:\n" + jsonObjectOnly);

            // 3) JsonNode로 파싱
            return objectMapper.readTree(jsonObjectOnly);

        } catch (Exception e) {
            e.printStackTrace();

            // null 반환은 위험하니(권장) 실패 JSON으로 반환
            return fallbackFailureJson();
        }
    }

    private String stripCodeFences(String content) {
        return content
                .replaceAll("(?s)```json\\s*", "")
                .replaceAll("(?s)```", "")
                .trim();
    }

    /**
     * content 안에 잡설이 섞여도 첫 '{' ~ 마지막 '}' 범위를 잘라 JSON 객체만 추출
     */
    private String extractJsonObject(String content) {
        int start = content.indexOf('{');
        int end = content.lastIndexOf('}');
        if (start >= 0 && end > start) {
            return content.substring(start, end + 1).trim();
        }
        return content.trim();
    }

    private JsonNode fallbackFailureJson() {
        try {
            String fallback = """
                    {
                      "emotionDetected": "분석 실패",
                      "keywords": "",
                      "insightSummary": "분석에 실패했어요. 잠시 후 다시 시도해 주세요.",
                      "flowHint": "",
                      "emotionSummary": "",
                      "emotionScore": 50
                    }
                    """;
            return objectMapper.readTree(fallback);
        } catch (Exception ignored) {
            return null; // 여기까지 오면 정말 예외적이긴 함
        }
    }
}
