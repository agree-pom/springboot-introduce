package cn.com.agree.afa.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * feign客户端
 * 使用url请求（如果有注册中心则使用服务名）
 *
 * 扩展：
 * 可自定义配置retry、connect timeout和read timeout等配置
 * 可自定义异常处理方法
 *
 * @author mx
 */
@Component
@FeignClient(name = "remote", url = "${remote.url.prefix}")
public interface RemoteClient {

    /**
     * remote为get类型请求时，需要加上@RequestParam注解（feign的bug）
     * @param name
     * @return
     */
    @GetMapping("/find-one")
    String findInvoke(@RequestParam String name);

    /**
     * RequestBody注解可以不加上
     * @param map
     * @return
     */
    @PostMapping("/save")
    String saveInvoke(@RequestBody Map<String, Object> map);

}
