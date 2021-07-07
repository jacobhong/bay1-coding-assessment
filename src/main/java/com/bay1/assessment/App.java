package com.bay1.assessment;


import com.bay1.assessment.domain.Balance;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class App {
  private static AuthorizationParser authorizationParser = new AuthorizationParser();

  public static void main (String[] args) {
    if (args[0] == null) throw new RuntimeException("Pass in filename (e.g file.txt)");
    URL systemResource = ClassLoader.getSystemResource(args[0]);
    if (systemResource == null) throw new RuntimeException("file not found");
    try {
//      HashMap<String, Balance> balances = new HashMap<>();

      File file = new File(systemResource.getFile());
      BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()));
      String str;
      while ((str = in.readLine()) != null) {
        authorizationParser.parseRecord(str);
      }
      in.close();
    } catch (IOException e) {
      System.out.println("Error reading file, " + e.getMessage());
    }
  }
}
