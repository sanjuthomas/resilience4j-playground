package com.sanjuthomas;

public class Service {

  Void call(int i) {
    System.out.println(i);
    if(i < 3)
      throw new RetryableException();
    throw new NonRetryableException();
  }

}
