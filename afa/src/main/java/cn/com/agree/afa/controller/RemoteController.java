package cn.com.agree.afa.controller;

import cn.com.agree.afa.remote.RemoteClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方
 * 目前多使用RestTemplate和Feign
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

    @Autowired
    RemoteClient remoteClient;

    /**
     * 从配置文件加载值，如果未配置该属性则启动报错
     */
    @Value("${remote.url.prefix}")
    private String prefixUrl;

    /**
     *  Value 可设置默认值，未配置则使用默认值
     *  可使用split转换为list.set.array等复杂结构
     */
    @Value("${remote.url.find:find-one}")
    private String findUrl;

    @Value("${remote.url.save:save}")
    private String saveUrl;


    /**
     * 使用rest template调用远程方法
     * @return
     */
    @GetMapping("/invoke")
    public String invoke() {
        log.info("使用RestTemplate调用远程方法 ...");
        log.info("测试调用远程find方法 ...");
        String param = "test1234";
        String url = prefixUrl + findUrl + "?name=" + param;
        log.info("find url -> {}", url);
        ResponseEntity<String> findResponse = restTemplate.getForEntity(url, String.class);

        String result = findResponse.getBody();
        log.info("find 结果 -> {}", result);

        log.info("\n\n\n");
        log.info("测试使用feign client调用远程方法 ...");
        String feignResult = remoteClient.findInvoke(param);
        log.info("feign客户端请求的结果 -> {}", feignResult);

        return "Success";
    }

    @GetMapping("invoke-save")
    public String invokeSave() {
        log.info("使用RestTemplate调用远程save方法 ...");
        // 多使用pojo类型或者JSONObject
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "test1");
        map.put("userCode", "test111");
        map.put("age", 10);
        map.put("address", "China");

        String url = prefixUrl + saveUrl;
        log.info("远程 url -> {}, data -> {}", url, map);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, map, String.class);
        log.info("save 结果 -> {}", responseEntity.getBody());


        log.info("\n\n\n");
        log.info("测试使用feign client调用远程方法 ...");
        String feignResult = remoteClient.saveInvoke(map);
        log.info("feign客户端请求的结果 -> {}", feignResult);


        return "Success";
    }

}
