package com.eunoia.gptapi;

import com.eunoia.gptapi.parser.GptJsonParser;
import com.eunoia.gptapi.parser.GptListParser;
import com.eunoia.gptapi.prompt.GptPromptFactory;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GPTService {

  private final GptClient gptClient;
  private final GptListParser listParser;
  private final GptJsonParser jsonParser;
  private final GptPromptFactory promptFactory;

  public List<String> generateWarmMessages(String content) {
    String prompt = promptFactory.warmMessages(content);
    String rawResponse = gptClient.call(prompt);
    return listParser.parse(rawResponse);
  }

  public JsonNode analyzeEmotion(String content) {
    String prompt = promptFactory.emotionAnalysis(content);
    String rawResponse = gptClient.call(prompt);
    return jsonParser.parse(rawResponse);
  }

  public JsonNode analyzeMeta(String metaInput) {
    String prompt = promptFactory.metaAnalysis(metaInput);
    String rawResponse = gptClient.call(prompt);
    return jsonParser.parse(rawResponse);
  }
}
