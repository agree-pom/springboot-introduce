package cn.com.agree.mockremote.api;

import cn.com.agree.mockremote.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 模拟远程服务器
 *
 * @author mx
 * @date 2021/8/3 10:49
 */
@Slf4j
@RestController
public class MockServerController {

    @GetMapping("/find-one")
    public String findOne(@RequestParam String name) {
        log.info("查询 name -> {}", name);
        return "服务端已收到请求数据 name -> " + name;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "/save")
    public String save(@RequestBody User user) {
        log.info("保存用户 user -> {}", user);


        return "服务端收到保存的用户，数据 -> " + user.toString();
    }

}
