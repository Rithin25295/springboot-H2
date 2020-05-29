package com.rithin.springboot.practise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world/internationalized")
    public String helloWorldInternationalized(@RequestHeader("Accept-Language") Locale locale){
        return messageSource.getMessage(
                "good.morning.message",null,locale);
    }
}
