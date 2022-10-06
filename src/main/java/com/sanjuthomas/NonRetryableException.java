package com.sanjuthomas;

public class NonRetryableException extends RuntimeException {
  public NonRetryableException() {
    super("This is NonRetryableException");
  }
}
