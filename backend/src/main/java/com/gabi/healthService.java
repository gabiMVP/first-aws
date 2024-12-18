package com.gabi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthService {

    private static int COUNTER =0;
    record Health(String health){};

    @GetMapping("/health")
    public Health getHealth(){
        return new Health("am healthy" + ++COUNTER);
    }
}
