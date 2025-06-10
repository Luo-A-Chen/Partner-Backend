//package org.example.chenduoduo.once;
//
//
//import jakarta.annotation.Resource;
//import org.apache.commons.lang3.time.StopWatch;
//import org.example.chenduoduo.mapper.UserMapper;
//import org.example.chenduoduo.model.User;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InsertUsers {
//    @Resource
//    private UserMapper userMapper;
//    /**
//     * 批量插入用户数据
//     */
//    // 每5秒执行一次
//    @Scheduled(fixedDelay = 5000)
//    public void doInsertUsers() {
////        final int INSERT_NUM=10000000;
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Long start = System.currentTimeMillis();
//        final int INSERT_NUM=1000;
//        for (int i = 0; i < INSERT_NUM; i++){
//            User  user = new User();
//            user.setUserAccount("updateluochen");
//            user.setUsername("假用户");
//            user.setAvatarUrl("https://www.keaitupian.cn/cjpic/frombd/1/253/1215285637/1396751085.jpg");
//            user.setGender(0);
//            user.setUserPassword("12345678");
//            user.setPhone("123");
//            user.setEmail("123");
//            user.setUserStatus(0);
//            user.setUserRole(0);
//            user.setPlanetCode("10000");
//            user.setTags("[]");
//            userMapper.insert(user);
//        }
//        stopWatch.stop();
//        Long end = System.currentTimeMillis();
//        System.out.println("插入" + INSERT_NUM + "条数据耗时：" + (end - start) + "ms");
//    }
//}
