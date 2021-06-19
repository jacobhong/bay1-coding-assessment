package com.bay1.assessment.util;

import java.math.BigInteger;

public class BitmapUtil {

  public static String decodeBitmap(String input) {
    return String.format("%8s", new BigInteger(input, 16).toString(2))
                 .replace(' ', '0');
  }

  public static String encodeBitmap(String input) {
    return new BigInteger(input, 2).toString(16);
  }

}