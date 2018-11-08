package com.zlj.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tori
 * 2018/8/6 下午4:35
 */

@RestController
public class ServerController {


    @GetMapping("/msg")
    public String msg() {
        return "sdfgshjfgshjgfhsdfgsdgfsdgfy";
    }
}
