## SSO单点登录系统

![](../gif/emall-sso.gif)

### 实现原理
> 用户登录后将token信息写入Redis和cookie中.

### 配置方法
1. 添加host

    `127.0.0.1 sso.emall.com`
    
2. 启动工程登录

    `http://sso.emall.com:8084/login`

### Wiki
> 详见[SSO单点登录](https://github.com/chiahaolu/emall/tree/master/doc/4.SSO%E5%8D%95%E7%82%B9%E7%99%BB%E5%BD%95)