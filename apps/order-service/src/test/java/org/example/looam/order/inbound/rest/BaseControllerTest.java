package org.example.looam.order.inbound.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import org.example.looam.order.inbound.MvcTest;

@WebMvcTest
public class BaseControllerTest {
  @Autowired private ObjectMapper objectMapper;
  @Autowired private MockMvc mockMvc;

  private MvcTest mvcTest;

  @BeforeEach
  void setUp() {
    mvcTest = new MvcTest(objectMapper, mockMvc);
  }

  protected ResultActions perform(MockHttpServletRequestBuilder builder) {
    return mvcTest.perform(builder);
  }

  protected ResultActions perform(MockHttpServletRequestBuilder builder, Object content) {
    return mvcTest.perform(builder, content);
  }
}
