package com.example.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuckyWordController {

  @Value("${lucky-word}") 
  private String luckyWord;

  @RequestMapping("/lucky-word")
  public String showLuckyWord() {
    return "La palabra afortunada es: " + luckyWord;
  }
}
