package cn.com.agree.afa.controller;

import cn.com.agree.afa.entity.UserInfo;
import cn.com.agree.afa.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关处理类
 * controller层不应该做很多处理逻辑，一般只做一些入参合法性校验，具体处理逻辑需要放入到service中
 * 项目中省略了service的接口层（看项目要求）
 *
 * @author mx
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    /**
     * 路径参数id查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/find/{id}")
    public UserInfo findUser(@PathVariable(value = "id") Long userId) {
        log.info("进入到根据id查询用户方法 -> {}", userId);
        return userInfoService.findOneById(userId);
    }




}
