package com.eunoia.gptapi.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GptListParser {

    private final ObjectMapper objectMapper;

    public List<String> parse(String rawResponse) {
        try {
            // 1) OpenAI 전체 응답(JSON) -> choices[0].message.content 꺼내기
            JsonNode root = objectMapper.readTree(rawResponse);
            String content = root.path("choices").get(0).path("message").path("content").asText();

            // 2) content에서 JSON 배열만 안전하게 추출
            String cleaned = stripCodeFences(content);
            String jsonArrayOnly = extractJsonArray(cleaned);

            // 3) List<String>으로 파싱
            List<String> result = objectMapper.readValue(jsonArrayOnly, new TypeReference<List<String>>() {
            });

            // 4) 항상 3개를 보장하고 싶으면(권장) 여기서 보정도 가능
            return normalizeToThree(result);

        } catch (Exception e) {
            e.printStackTrace();
            return List.of("분석 실패", "GPT 응답 처리 중 오류", "다시 시도해주세요");
        }
    }

    private String stripCodeFences(String content) {
        return content
                .replaceAll("(?s)```json\\s*", "")
                .replaceAll("(?s)```", "")
                .trim();
    }

    /**
     * content 안에 잡설이 섞여도 첫 '[' ~ 마지막 ']' 범위를 잘라 JSON 배열만 추출
     */
    private String extractJsonArray(String content) {
        int start = content.indexOf('[');
        int end = content.lastIndexOf(']');
        if (start >= 0 && end > start) {
            return content.substring(start, end + 1).trim();
        }
        return content.trim();
    }

    private List<String> normalizeToThree(List<String> list) {
        if (list == null || list.isEmpty()) {
            return List.of("지금 마음을 적어내고 계시네요.", "여러 마음이 함께 놓여 있는 것 같아요.", "지금은 어떤 감정이 더 크게 느껴지나요?");
        }
        if (list.size() >= 3)
            return list.subList(0, 3);

        // 부족하면 기본 문장으로 채우기
        String a = list.size() > 0 ? list.get(0) : "지금 마음을 적어내고 계시네요.";
        String b = list.size() > 1 ? list.get(1) : "여러 마음이 함께 놓여 있는 것 같아요.";
        String c = "지금은 어떤 감정이 더 크게 느껴지나요?";
        return List.of(a, b, c);
    }
}
