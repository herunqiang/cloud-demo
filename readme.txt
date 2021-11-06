单击启动nacos :  startup.cmd -m standalone
集群启动nacos :
    1.先启动每个nacos实例
    2.再启动Nginx代理每个nacos实例
    注意：如果启动抛nacos异常导致获取不到nacos服务列表，删除一下nacos服务的data文件夹