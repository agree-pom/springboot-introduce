package cn.com.agree.afa.controller;

import cn.com.agree.afa.entity.UserInfo;
import cn.com.agree.afa.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("save")
    public UserInfo save(@RequestBody UserInfo userInfo) {
        log.info("保存用户 -> {}", userInfo);

        return userInfoService.save(userInfo);
    }

    /**
     * 可以使用get/post/put方法，且@RequestParam中name可指定（如果不指定则认为是和变量名一致）
     * @param name
     * @return
     */
    @RequestMapping("/find-by-code")
    public UserInfo findUserByUserCode(@RequestParam(name = "userName") String name) {
        return userInfoService.findOneByUserCode(name);
    }

    @GetMapping("find-all")
    public List<UserInfo> findAll() {
        return userInfoService.findAllUsers();
    }

    /**
     * 参数不写注解直接引用，默认是在@RequestParam
     * @param userInfo
     * @param size
     * @param page
     * @return
     */
    @GetMapping("query-page")
    public Page<UserInfo> queryPage(UserInfo userInfo, int size, int page) {
        log.info("" + userInfo);
        // 如果有复杂的比如上送sort条件，需要正常接收
        PageRequest pageRequest = PageRequest.of(page, size);



        return userInfoService.queryPage(pageRequest, userInfo);
    }

}
