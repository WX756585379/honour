# honour-server


## 技术选型

技术 | 名称 
----|----
Spring Boot | 容器+MVC框架
Spring Security | 认证和授权框架
MyBatis | ORM框架  
MyBatisGenerator | 数据层代码生成  
PageHelper | MyBatis物理分页插件  
Swagger-UI | 文档生产工具
Hibernator-Validator | 验证框架
RabbitMq | 消息队列
Redis | 分布式缓存
MongoDb | NoSql数据库
Druid | 数据库连接池

1.redis

打开 Redis，解压目录下的 redis.conf文件，主要修改如下几个地方 :

```
#允许Redis在后台启动
daemonize yes   

#设置登录密码 
requirepass foobared  

#由于有设置登录密码，所以可以关闭保护模式
protected-mode no   

#默认情况下只允许本地连接，将默认配直注释掉，外网就可以连接 Redis 了
# bind 127.0.1

```

为了能够远程连接上 Redis，还需要关闭 CentOS 防火墙，执行如下命令:

```
#关闭防火墙
systemctl stop firewalld.service 

#禁止防火墙开机启动
systemctl disable firewalld.service
```

Redis 启动与关闭

```
redis-server redis.conf

#Redis启动成功后，再执行如下命令进入Redis控制台，其中-a表示Redis登录密码:
redis-cli -a foobared

#其中，-p表示要关闭的Redis实例的端口号，-a表示Redis登录密码。
redis-cli -p 6379 - a foobared shutdown
```







### 后台功能
#### 后台登录功能 ✔
- 后台用户注册功能
- 后台用户登录后获取token
- 刷新token功能

