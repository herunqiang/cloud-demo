单击启动nacos :  startup.cmd -m standalone(开发使用)
集群启动nacos :
    1.先启动每个nacos实例
    2.再启动Nginx代理每个nacos实例
    注意：如果启动抛nacos异常导致获取不到nacos服务列表，删除一下nacos服务的data文件夹

docker:
    运行MySQL
    docker run --name mysql_dev -p 3306:3306 -v /tmp/mysql/conf/hmy.cnf:/etc/mysql/conf.d/hmy.cnf -v /tmp/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7.25
启动本地sentinel
    ：java -jar sentinel-dashboard-1.8.1.jar --server.port=7777   （本地模式）
    ：java -jar sentinel-dashboard.jar --server.port=7777 --nacos.addr=182.61.10.220:8848 （nacos模式）
启动事务协调者TC
    ：‪D:\dev\seata\seata-server-1.4.2\bin\seata-server.bat

坑！！！！！！！！！！！
mybatis没有数据源代理配置，导致分布式事务失效，调整为mybatisPlus就可以了

redis：
    一键启动：
        printf '%s\n' 7001 7002 7003 8001 8002 8003 | xargs -I{} -t redis-server {}/redis.conf
    一键关闭：
        redis printf '%s\n' 7001 7002 7003 | xargs -I{} -t redis-cli -p {} shutdown