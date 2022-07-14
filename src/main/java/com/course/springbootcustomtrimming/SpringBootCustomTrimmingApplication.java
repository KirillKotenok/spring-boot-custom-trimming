package com.course.springbootcustomtrimming;

import com.course.springbootcustomtrimming.annotation.EnableStringTrimming;
import com.course.springbootcustomtrimming.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableStringTrimming
@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootCustomTrimmingApplication {
    private final GreetingService greeting;

    public static void main(String[] args) {
        var context = SpringApplication.run(SpringBootCustomTrimmingApplication.class, args);
        var greetingService = context.getBean(GreetingService.class);

        greetingService.printGreeting("K i r i l l");
        System.out.println(greetingService.returnGreeting());
    }

}
