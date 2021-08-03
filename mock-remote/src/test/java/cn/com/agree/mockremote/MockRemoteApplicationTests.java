package cn.com.agree.mockremote;

import cn.com.agree.mockremote.api.MockServerController;
import cn.com.agree.mockremote.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockRemoteApplicationTests {

    @Autowired
    MockServerController mockServerController;

    @Test
    void find() {
        mockServerController.findOne("Jack");
    }

    @Test
    void save() {
        User user = new User().setUserCode("Jack").setUserName("杰克").setAge(10).setAddress("China");
        mockServerController.save(user);
    }
}
