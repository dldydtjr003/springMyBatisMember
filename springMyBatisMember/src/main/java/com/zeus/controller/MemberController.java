package com.zeus.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@MapperScan(basePackages = "com.zeus.mapper")
@Slf4j
public class MemberController {

}
