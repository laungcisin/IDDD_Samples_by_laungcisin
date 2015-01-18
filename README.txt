IDDD由四个子项目组成：

1.iddd_agilepm 项目是一个以LevelDB这样key-value存储作为持久层的案例，
    LevelDB 是一个纯Java实现: https://github.com/dain/leveldb
    iddd_agilepm并没有使用任何容器(such as Spring).

2.iddd_collaboration项目是一个使用 Event Sourcing 和
CQRS的案例.它避免使用了ORM之类框架，展示基于JDBC的查询引擎.
    这样技术虽然有其局限性，但是意味着小而快，无需任何元注解之类配置，虽然不完美。
  iddd_collaboration项目展示它持久Event Sourced写模型和在另外一个线程实现CQRS的读模型.
    使用LevelDB进行事件存储播放而 MySQL用于读模型的存储。也许两者之间有些数据不一致，可实现最终一致性。

3.The iddd_identityaccess 项目是使用ORM作为持久(Hibernate),
    但是也没有被它干扰，提供一个RESTful客户端接口，通过REST (logs)
    和 RabbitMQ.可发送Domain-Event提醒。

4.iddd_common 项目提供许多可重用组件，虽然并不试图成为一个框架，但是能提高代码重用


如何编译IDDD_Samples_by_laungcisin项目：
1.安装RabbitMQ；
    http://www.cnblogs.com/junrong624/p/4121656.html
2.启动RabbitMQ；
3.将IDDD_Samples_by_laungcisin\iddd_collaboration\src\main\mysql\collaboration.sql导入数据库；
4.将IDDD_Samples_by_laungcisin\iddd_common\src\main\mysql\common.sql导入数据库；
  将IDDD_Samples_by_laungcisin\iddd_common\src\main\mysql\test_common.sql导入数据库；
5.将IDDD_Samples_by_laungcisin\iddd_identityaccess\src\main\mysql\iam.sql导入数据库；

6.修改IDDD_Samples_by_laungcisin\iddd_collaboration\src\main\resources\applicationContext-collaboration.xml
中数据库相关配置；

7.修改IDDD_Samples_by_laungcisin\iddd_common\src\main\resources\applicationContext-common.xml和
      IDDD_Samples_by_laungcisin\iddd_common\src\main\resources\hibernate.cfg.xml中数据库相关配置；

8.修改IDDD_Samples_by_laungcisin\iddd_identityaccess\src\main\resources\hibernate.cfg.xml中数据库相关配置；



