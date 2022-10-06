package com.sanjuthomas;

public class RetryableException extends RuntimeException {
  public RetryableException() {
    super("This is RetryableException");
  }
}
