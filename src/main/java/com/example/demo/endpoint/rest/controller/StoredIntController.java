package com.example.demo.endpoint.rest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoredIntController {

  @GetMapping("/stored-int")
  public String storedInt() {
    File file = new File("/tmp/stored-int.txt");
    Random random = new Random();

    if (file.exists()) {
      try {
        return Files.readString(file.toPath());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    int randomNumber = random.nextInt(100);
    String randomStringValue = String.valueOf(randomNumber);

    try {
      Files.writeString(file.toPath(), randomStringValue);
      return randomStringValue;
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }
}
