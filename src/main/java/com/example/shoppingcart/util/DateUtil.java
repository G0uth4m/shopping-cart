package com.example.shoppingcart.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {
  public static LocalDateTime now() {
    return LocalDateTime.now(ZoneOffset.UTC);
  }
}
