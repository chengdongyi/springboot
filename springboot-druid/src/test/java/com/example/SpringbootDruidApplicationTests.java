package com.example;
import java.util.Date;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.dao.OrderInfoDao;
import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.dao.UserInfoDao;
import com.example.domain.OrderInfo;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDruidApplicationTests {

    @Resource
    private DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

        //关闭连接
        connection.close();
    }

    @Resource
    private UserDao userDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private OrderInfoDao orderInfoDao;

    @Test
    public void testUser() {
        User user = userDao.findByUserName("root");
        System.out.println(user);
    }

    @Test
    public void testUser1() {
        User user = userDao.findUserAndUserInfoByUserName("zhangsan");
        System.out.println(user);
    }

    @Test
    public void testUser2() {
        User user = userDao.findUserAndRoleByUserName("zhangsan");
        System.out.println(user);
    }

    @Test
    public void testUser3() {
        User user = userDao.findByName("zhangsan");
        System.out.println(user);
    }

    @Test
    public void testRole() {
        List<Role> roles = roleDao.findByUId(1);
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Test
    public void testUserInfo() {
        UserInfo userInfo = userInfoDao.findByUserId(2);
        System.out.println(userInfo);
    }

    @Test
    public void addOrderInfo() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("2020070311210500100001");
        orderInfo.setMobile("13088801234");
        orderInfo.setProductId("10000000521");
        orderInfo.setProductName("一次性医用口罩(30只装)");
        orderInfo.setPrice("55.00");
        orderInfo.setStatus("1");
        orderInfo.setOrderTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderInfoDao.insertRecord(orderInfo);
    }

    @Test
    public void updateOrderInfo() {
        OrderInfo orderInfo = orderInfoDao.findByOrderId("2020070311210500100001");
        if(orderInfo == null) {
            System.out.println("没有找到订单信息");
            return;
        }
        orderInfo.setStatus("2");
        orderInfo.setUpdateTime(new Date());
        orderInfoDao.updateRecord(orderInfo);
    }

    @Test
    public void deleteOrderInfo() {
        orderInfoDao.deleteOneRecord("2020070311210500100001");
    }

    @Test
    public void selectOrderInfo() {
        OrderInfo orderInfo = orderInfoDao.findByOrderId("2020070212300500100001");
        System.out.println(orderInfo);
    }

    @Test
    public void selectOrderInfo1() {
        OrderInfo orderInfo = orderInfoDao.findByMobile("13996994524");
        System.out.println(orderInfo);
    }

    @Test
    public void selectOrderInfo2() {
        List<OrderInfo> orderInfos = orderInfoDao.findByProductId("10000000522");
        for (OrderInfo orderInfo : orderInfos) {
            System.out.println(orderInfo);
        }
    }

}
