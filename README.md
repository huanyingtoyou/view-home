# springcloud各个部分的基础配置复习

### 模块说明
1、view-eureka：eureka服务注册中心<br>
2、view-eureka-cluster：eureka server的高可用<br>
3、view-config-server：配置中心server，配置中心在[view-home-config](https://github.com/huanyingtoyou/view-home-config)<br>
4、view-hystrix-dashboard：Hystrix Dashboard可视化监控<br>
5、view-hystrix-turbine：Hystrix Turbine可视化集群监控<br>
6、view-sleuth-zipkin-server：服务链路跟踪<br>
7、view-zuul：网关路由（zuul组件包含ribbon，可以实现负载均衡），可以通过zuul过滤器过滤请求<br>
8、view-api：feign接口<br>
9、view-common：实体类、工具类等<br>
10、view-user-service：测试的用户服务（有几个测试服务）<br>
11、view-user-service-second：复制的上面的user-service服务，为了测试负载均衡<br>
12、view-photo-service：测试的照片服务（待开发）<br>
13、view-video-service：测试的视频服务（待开发）<br>
14、view-view：web<br>
15、view-sleuth-elk：sleuth与elasticsearch、logstash、kibana的整合，未完成

### 使用说明
1、相继启动view-eureka、view-config-server、view-sleuth-zipkin-server等服务后，可以进行相应的测试。
2、eureka server的账号为lihy，密码为520，可修改；
3、user-service下有数据库文件，可以执行，数据库名字这里为view，可以修改；

### 注意
1、记得安装rabbitmq，这里就不叙述rabbitmq的安装过程，config server自动刷新用到了mq；
2、在windows服务器启动jar包后，控制输出日志可能乱码，需要在项目中添加logback.xml配置日志文件，并且将<[charset>UTF-8</charset]>注释掉或者删掉；linux服务器待试。

