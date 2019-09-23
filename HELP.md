# Getting Started

### Guides
The following guides illustrates how to use certain features concretely:

* [Service Registration and Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)

项目包搭建要求：gradle运行添加根目录his的build.gradle

配置环境要求：docker、mysql5.7+、tomcat7、java8、redis


模块划分：
   eureka注册中心： eureka-sever-one、eureka-sever-two
   hystrix熔断器： hystrix-dashboard
   用户中心: user-infor
   zuul对外网关：zuul-outer-one
   授权认证中心：security-authorization-sever
   地图模块: map