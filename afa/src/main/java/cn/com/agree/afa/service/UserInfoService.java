package cn.com.agree.afa.service;

import cn.com.agree.afa.common.constant.UserResultConstant;
import cn.com.agree.afa.common.enums.UserStatus;
import cn.com.agree.afa.common.exception.UserSecurityException;
import cn.com.agree.afa.entity.UserInfo;
import cn.com.agree.afa.repository.UserInfoRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * service类
 *
 * @author mx
 */
@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> findAllUsers() {
        // 排序
        Sort sort = Sort.by(
                Sort.Order.desc("userId")
        );
        return userInfoRepository.findAll(sort);
    }

    /**
     * 分页查询
     * @param page
     * @param parameters
     * @return
     */
    public Page<UserInfo> queryPage(PageRequest page, UserInfo parameters) {
        // 1、基本分页查询
        // 简单的分页查询，不牵扯到查询条件
        Page<UserInfo> basePage = userInfoRepository.findAll(page);

        // 2、带查询条件的分页
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(parameters.getUserName())
                .setUserCode(parameters.getUserCode())
                .setPassword(parameters.getPassword());

        // 日期格式区间等情况无法处理，使用方案3
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // null忽略
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                // userCode以a开头
                .withMatcher("userCode", ExampleMatcher.GenericPropertyMatchers.endsWith())
                // userName包含t
                .withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.contains())
                // 如果userInfo对象中传入过多属性，同时并不做为查询条件需要忽略
                .withIgnoreCase("password");
        Example<UserInfo> userInfoExample = Example.of(userInfo, exampleMatcher);

        Page<UserInfo> examplePage = userInfoRepository.findAll(userInfoExample, page);


        // 3、使用更灵活方案
        Page<UserInfo> specificationPage = userInfoRepository.findAll(new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                // 根据查询条件动态判断
                if (StringUtils.hasText(parameters.getUserName())) {
                    predicateList.add(
                            criteriaBuilder.like(root.get("userName").as(String.class), "%" + parameters.getUserName() + "%")
                    );
                }
                // 查询条件，自行扩展（例如date类型 le，ge，between）
                if (StringUtils.hasText(parameters.getUserCode())) {
                    predicateList.add(
                            criteriaBuilder.equal(root.get("userCode").as(String.class), parameters.getUserCode())
                    );
                }

                return criteriaQuery.where(predicateList.toArray(new Predicate[0])).getRestriction();
            }
        }, page);


        return specificationPage;
    }

    public UserInfo findOneById(Long id) {
        return userInfoRepository.getOne(id);
    }

    /**
     * 保存用户
     * @param userInfo
     * @return
     */
    public UserInfo save(UserInfo userInfo) {
        // 使用原生sql查询最大id
        Long id = userInfoRepository.findMaxId();
        userInfo.setUserId(id)
                .setPassword("123456")
                .setUserStatus(UserStatus.NORMAL);
        return userInfoRepository.save(userInfo);
    }

    public UserInfo findOneByUserCode(String userCode) {
        return userInfoRepository.findOneByUserCode(userCode).orElse(null);
    }

    /**
     * 用户登录
     * @param userCode
     * @param password
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public UserInfo login(String userCode, String password) {
        UserInfo user = userInfoRepository.findUser(userCode, password);
        if (user == null) {
            throw new UserSecurityException(UserResultConstant.INVALID_USER);
        }

        // 也可以修改属性后直接调用saveAndFlush方法
        int loginCnt = user.getLoginCnt() + 1;
        userInfoRepository.modifyUserLoginCnt(userCode, loginCnt);

        return user;
    }

}
