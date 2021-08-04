package cn.com.agree.afa.repository;

import cn.com.agree.afa.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户仓库dao
 *
 * 可使用默认的jpa方法 如save、delete、findAll等
 * 也可根据jpa规则扩展查询方法
 *
 * 更复杂的则需要自定义hql或者sql语句
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {

    /**
     * 扩展jpa的用法
     * @param userCode
     * @return
     */
    Optional<UserInfo> findOneByUserCode(String userCode);

    /**
     * 使用自定义hql
     * @param userName
     * @param password
     * @return
     */
    @Query("select m from UserInfo m where m.userName = ?1 and m.password = ?2 ")
    UserInfo findUser(String userName, String password);

    /**
     * 使用原生sql执行查询
     * @return
     */
    @Query(value = "select nvl(max(user_id), 0) + 1 from user_info ", nativeQuery = true)
    Long findMaxId();

    /**
     * 自定义hql修改数据
     * @param userCode
     * @param loginCnt
     */
    @Modifying
    @Query("update UserInfo set loginCnt =?2 where userCode = ?1")
    void modifyUserLoginCnt(String userCode, int loginCnt);

}
