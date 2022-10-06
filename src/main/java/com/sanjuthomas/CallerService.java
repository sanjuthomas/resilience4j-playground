package com.sanjuthomas;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import java.util.function.Supplier;

public class CallerService {

  public static void main(String[] args) {
    Service service = new Service();
    RetryConfig config = RetryConfig.custom()
      .maxAttempts(3)
      .waitDuration(Duration.ofMillis(1000))
      .retryExceptions(RetryableException.class)
      .ignoreExceptions(NonRetryableException.class)
      .failAfterMaxAttempts(true)
      .build();
    RetryRegistry registry = RetryRegistry.of(config);
    Retry retry = registry.retry("service");
    Supplier<Void> serviceSupplier = () -> service.call(1);
    Supplier<Void> retryingService = Retry.decorateSupplier(retry, serviceSupplier);
    try {
      retryingService.get();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
