package com.lqyrmk.transportation;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/6/18 5:16
 */
@Slf4j
@EnableHystrix
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class WaitItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaitItemApplication.class, args);
    }

}
