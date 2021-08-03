package cn.com.agree.afa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 调用第三方
 *
 * @author mx
 * @date 2021/8/3 11:01
 */
@Slf4j
@RestController
@RequestMapping("/remote")
public class RemoteController {
    @Autowired
    RestTemplate restTemplate;

    @Value("http")
    private String protocol;

    @Value("${remote.url.prefix}")
    private String prefixUrl;

    @Value("${remote.url.find:find-one}")
    private String findUrl;

    @Value("${remote.url.save:save}")
    private String saveUrl;

    @GetMapping("/invoke")
    public String invoke() {
        ResponseEntity<String> findResponse = restTemplate.getForEntity(
                prefixUrl + findUrl,
                String.class,
                "Test111"
        );

        String result = findResponse.getBody();
        log.info("result -> {}", result);

        return "Success";
    }

    public String invokeByFeign() {

        return "Success";
    }

}
