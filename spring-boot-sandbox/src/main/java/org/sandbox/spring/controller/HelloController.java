package org.sandbox.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final String template = "Hello %s";

    @RequestMapping("/hello")
    String sayHello(@RequestParam(value="name", required=false, defaultValue="world") String name) {
        return String.format(template, name);
    }
}