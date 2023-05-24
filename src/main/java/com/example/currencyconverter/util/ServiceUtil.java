package com.example.currencyconverter.util;

import java.util.Arrays;
import java.util.Objects;

public class ServiceUtil {

  private static boolean allNotNull(Object ... objects) {
    return Arrays.stream(objects)
            .takeWhile(o -> !Objects.isNull(o))
            .count() == objects.length;
  }

  public static String substringBetween(
          final String str,
          final String open,
          final String close
  ) {

    if (!allNotNull(str, open, close)) {
      return null;
    }
    final int start = str.indexOf(open);
    if (start != -1) {
      final int end = str.indexOf(close, start + open.length());
      if (end != -1) {
        return str.substring(start + open.length(), end);
      }
    }

    return null;
  }

  public static String[] halfCodePair(String codePair) {
    return new String[]{codePair.substring(0, 3), codePair.substring(3, 6)};
  }
}
