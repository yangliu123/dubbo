package com.dubbo.demo.comsumer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.upsmart.api.DemoService;
import com.upsmart.api.UserService;



public class DemoConsumer {

    public static void main(String[] args) {
        //final String port = "8888";

        // 测试Rest服务
        //getUser("http://localhost:" + port + "/services/users/1.json");
        //getUser("http://localhost:" + port + "/services/users/1.xml");

        
        // dubbox调用
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
        context.start();
        
        UserService  userService= context.getBean(UserService.class);
        System.out.println(userService.getUser(1L));
        
        //demo
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法

        System.out.println( hello );
        
        context.close();
    }

    
    
  /*  private static void getUser(String url) {
        System.out.println("Getting user via " + url);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Response response = target.request().get();
        try {
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
            }
            System.out.println("Successfully got result: " + response.readEntity(String.class));
        } finally {
            response.close();
            client.close();
        }
    }*/
}
