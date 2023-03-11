package org.example.looam.book.inbound.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import org.example.looam.book.BootstrapApplication;
import org.example.looam.book.inbound.MariaDB4jExtensiona;
import org.example.looam.book.inbound.MvcTest;
import org.example.looam.book.outbound.repository.book.BookJpaRepository;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {BootstrapApplication.class})
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, MariaDB4jExtensiona.class})
@ActiveProfiles("api-test")
public class BaseApiTest {
  @Autowired private ObjectMapper objectMapper;
  @Autowired private MockMvc mockMvc;

  @Autowired BookJpaRepository bookJpaRepository;

  private MvcTest mvcTest;

  @BeforeEach
  void setUp() {
    mvcTest = new MvcTest(objectMapper, mockMvc);
  }

  @AfterEach
  void tearDown() {
    clearDB();
  }

  private void clearDB() {
    bookJpaRepository.deleteAll();
  }

  protected ResultActions perform(MockHttpServletRequestBuilder builder) {
    return mvcTest.perform(builder);
  }

  protected ResultActions perform(MockHttpServletRequestBuilder builder, Object content) {
    return mvcTest.perform(builder, content);
  }

  public <T> T parseResponse(ResultActions resultActions, Class<T> type) {
    return mvcTest.parseResponse(resultActions, type);
  }

  public <T> T parseResponse(ResultActions resultActions, TypeReference<T> typeReference) {
    return mvcTest.parseResponse(resultActions, typeReference);
  }
}
