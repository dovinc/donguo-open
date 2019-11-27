# donguo-open

[在线预览: admin/111111](http://47.98.119.177:8010/) 

#### 1. 使用技术

| 使用技术 | 版本 | 备注 |
| --- | --- | --- |
| springboot | 2.1.7.RELEASE |  |
| springSecurity | 依赖于springboot | 权限认证，接口权限 |
| mybatis-plus | 3.2.0 | dao层 |
| hutool | 4.6.7| 常用工具类 |

#### 2.环境需求
1. JDK1.8+
2. nginx
3. gradle【非必须，由于项目中有gradle wrapper】

#### 3.donguo-open 部署
1. nginx不论使用什么方式 安装
2. nginx配置文件修改：
```
vim /etc/nginx/nginx.conf
```
或 http节点中include 的conf 文件路径中新建conf文件
在http节点中添加如下节点:
```
    server {
        listen      8010;
        server_name localhost;
        location / {
            # 此处配置为 使用nginx 的8010端口作为 前端的静态服务器
            root /home/donguo-open/dist;
            index index.html index.html;
        }
        location /prod-api/ {
            # 反向代理 java 后端项目的端口与application.yml中的server port对应【同时使用此方式应该可以解决跨域的问题】
            proxy_pass http://localhost:8888/;
        }
    }
```
3. 重新加载配置文件
```
nginx -s reload
```
4. 本地 donguo-open 打包 【修改端口号为上述端口，修改数据库连接，username，password】并使用sftp 或其他方式上传至服务器
windows使用sftp 在jar包目录打开powershell【shift+鼠标右键】 
```
   sftp username@ip 登录服务器sftp
   
   put fileName 上传至服务器
```
5. ssh 连接服务器 后台运行jar包
```
nohup java -jar donguo-open-0.0.1-SNAPSHOT.jar >nohup.out &
```
#### 杀死进程
 
1.lsof -i:PortNumber(如8888)
2. kill Pid(如7052)
