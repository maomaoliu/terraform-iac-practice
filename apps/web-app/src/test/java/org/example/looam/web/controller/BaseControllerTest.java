package org.example.looam.web.controller;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import org.example.looam.web.BootstrapApplication;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {BootstrapApplication.class})
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BaseControllerTest {
  @Autowired private ObjectMapper objectMapper;
  @Autowired private MockMvc mockMvc;

  @SneakyThrows
  public ResultActions perform(MockHttpServletRequestBuilder builder) {
    return mockMvc.perform(builder);
  }

  @SneakyThrows
  public ResultActions perform(MockHttpServletRequestBuilder builder, Object content) {
    return perform(builder, content, APPLICATION_JSON);
  }

  @SneakyThrows
  public ResultActions perform(
      MockHttpServletRequestBuilder builder, Object content, MediaType type) {
    return mockMvc.perform(
        builder.content(objectMapper.writeValueAsString(content)).contentType(type));
  }

  @SneakyThrows
  public <T> T parseResponse(ResultActions resultActions, Class<T> type) {
    String contentAsString =
        resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    return objectMapper.readValue(contentAsString, type);
  }

  @SneakyThrows
  public <T> T parseResponse(ResultActions resultActions, TypeReference<T> typeReference) {
    String contentAsString =
        resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    return objectMapper.readValue(contentAsString, typeReference);
  }
}
