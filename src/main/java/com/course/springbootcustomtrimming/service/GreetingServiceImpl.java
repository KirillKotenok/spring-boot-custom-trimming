package com.course.springbootcustomtrimming.service;

import com.course.springbootcustomtrimming.annotation.Trimmed;
import org.springframework.stereotype.Service;

@Service
@Trimmed
public class GreetingServiceImpl implements GreetingService {

    @Override
    public void printGreeting(String name) {
        System.out.println("Hello, " + name);
    }

    @Override
    public String returnGreeting() {
        return "H e l l o !";
    }
}
