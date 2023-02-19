package com.sist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //자동으로 메모리 할당
@ComponentScan(basePackages = "com.sist.model") //<context:component-scan base-package="com.sist.model"/>
public class ModelConfig {

}
