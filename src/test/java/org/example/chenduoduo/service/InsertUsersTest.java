//package org.example.chenduoduo.service;
//
//import jakarta.annotation.Resource;
//import org.apache.commons.lang3.time.StopWatch;
//import org.example.chenduoduo.mapper.UserMapper;
//import org.example.chenduoduo.model.domain.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
//@SpringBootTest
//public class InsertUsersTest {
//
//    @Resource
//    private UserMapper userMapper;
//    @Autowired
//    private UserService userService;
//    private ExecutorService executorService=new ThreadPoolExecutor(60, 100, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));
//    /**
//     * 终于学到并发了，这次使用并发批量插入用户
//     */
//    @Test
//    public void doConcurrencyInsertUsers() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM=1000;
//        //开始时间
//        Long start = System.currentTimeMillis();
//        //将用户信息分组，分成十组
//        int j = 0;
//        //创建CompletableFuture对象,futureList保存所有CompletableFuture对象
//        List<CompletableFuture<Void>> futureList = new ArrayList<>();
//        for(int i=0;i<10;i++){
//            List<User> userList = new ArrayList<>();
//            while(true){
//                j++;
//                User user = new User();
//                user.setUserAccount("updateluochen");
//                user.setUsername("假用户");
//                user.setAvatarUrl("https://www.keaitupian.cn/cjpic/frombd/1/253/1215285637/1396751085.jpg");
//                user.setGender(0);
//                user.setUserPassword("12345678");
//                user.setPhone("123");
//                user.setEmail("123");
//                user.setUserStatus(0);
//                user.setUserRole(0);
//                user.setPlanetCode("10000");
//                user.setTags("[]");
//                userList.add(user);
//                if (j%100   ==0){
//                    break;
//                }
//            }
//            //分批插入数据,并且是异步的
//            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//                System.out.println("当前线程：" + Thread.currentThread().getId());
//                userService.saveBatch(userList,100);
//            },  executorService);
//            futureList.add(future);
//        }
//        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
//        stopWatch.stop();
//        //结束时间
//        Long end = System.currentTimeMillis();
//        System.out.println("插入" + INSERT_NUM + "条数据耗时：" + (end - start) + "ms");
//    }
//}
//
