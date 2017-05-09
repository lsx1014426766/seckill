 第一部分  秒杀之dao层
 1maven项目管理
 	新建maven项目，通过pom.xml引入需要的依赖--》
 	对于依赖包的groupid和artifactid可以通过在网上找到maven repository
 	输入所需的artifactid即可找到在pom.xml中的写法
 	--》在项目上右击--》Run As-->maven install 把依赖包下载到本地
 	
 2配置文件的加载顺序：
	  spring-dao.xml
	  mybatis-config.xml
	  mapper/*.xml
	 spring统一管理mybatis,数据库，dao层，entity类
 3完成顺序：
	   1pom.xml-->maven install
	   2entity类
	   3db table创建
	   4dao层接口定义
	   5写mybatis的配置文件 做简单的设置 列名和类字段的映射关系等
	   6sql的xml文件
	   7spring配置文件 管理mybatis
	   8spring junit单元测试
4遇到的问题：
  1xml文件，没有属性提示--通过windows-->preferences-->xml catalog -->add-->location指定dtd位置
   关于获取dtd，可从项目下的MAVEN Project里面找到相对应的jar包，在某个位置有个schema的文件，里面有这个dtd所在的位置提示
  2  上面的操作只是解决了属性名的提示，但是关于属性值则没有
	   我下了intellij，它可以做到智能提示但是只对有的类，在写包名类名时可以提示
	   也做不到把某个类下的name值提示出来 
	   
 第二部分  秒杀之service层
 秒杀  “事务性”   秒杀 红包常见
 Java web 常用的框架：springMVC+spring+mybatis
 dao:接口设计+sql  代码和sql分离  方便review
 dto:关注web和service的数据传递   entity包内的和table表对应
 业务接口：
    三方面： 方法定义粒度  简单参数 返回类型
   spring管理service依赖
   spring IOC
       对象工厂
                 一致性的访问接口
       依赖管理

     业务对象的依赖图

     seckillService  SeckillDao,successKilledDAO  sqlSessionFacotry  dataSource
     why IOC
     对象创建统一托管  not new
     规范的生命周期管理  初始化 销毁
     灵活的依赖注入
     一致的获取对象   从容器获取已知的实例

     Spring-IOC的注入方式
         XML
              applicationzContext.xml
              Bean实现类来自第三方类库  DataSource等
              需要命名空间配置 context aop mvc 强大的spring配置
         注解
              自定义类 @Service @Controller
         JAVA配置
              spring的配置类
              需要通过代码控制对象创建逻辑
              自定义修改依赖类库等
     使用：
         XML配置
         package-scan  包扫描
         Annotaiton注解
   spring声明式事务
      开启事务
      修改sql-1
      修改sql-2
      提交/回滚事务
      全自动方式
   使用方式
      ProxyFactoryBean+XML 早期
      tx:advice+ap命令空间  一次配置
      注解@Teansactional   注解控制（推荐）
   事务方式嵌套
      传播行为 propagation_required

   何时回滚事务：
      抛出运行期异常RuntimeException
      小心不当的try-catch
   spring 配置声明式事务

   logback的官网http://logback.qos.ch/demo.html
   查看配置文件xml示例

   日志级别
        权重由大到小：
        FATAL(CRITICAL)
        ERROR
        WARN
        INFO
        DEBUG

   service层总结：
     1log日志的输出级别为debug时，可以打印sql执行语句，info时不可以，应该有默认设置
     2抛出异常如果有自定义异常可以使用具体的异常catch,最后用Exception类型的
     3对象工厂，对象bean间的依赖管理由spring来托管
      需要使用事务的地方，用注解的方式配置

   java高并发API之web层

     前端交互设计
     Restful  规范url设计规范
     SpringMVC  配置  应用
     bootstrp+jquery  页面布局 交互实现

     1前端交互设计
     需求设计   配合   友好的系统
         产品
         前端
         后端   存储展示
       列表页   秒杀产品列表
       详情页   login-------->展示逻辑
                登录--cookie
                1获取标准系统时间  服务器时间
                2时间判断  开始   结束时间
                  结束   倒计时--》秒杀地址--》秒杀操作--》秒杀结果
       Restful接口
       是什么
         起于Rail  优雅的url表达方式 资源的状态 和状态的转移
       怎么用
       GET /seckill/list
       POST /seckill/execute/{seckillId}  不好
       POST /seckill/{seckillId}/execution  好
       DELETE /seckill/{id}/delete
       GET 查询
       POST 添加修改  非幂等操作
       PUT 修改
       DELETE
       URL设计
       /模块/资源/{标示}/集合1/...
       /user/{uid}/friends ->好友列表
       /user/{uid}/followers ->关注者列表

       秒杀API的URL设计
       GET /seckill/list
       GET /seckill/{id}/detail
       GET /seckill/time/now 系统时间
       POST /seckill/{id}/exposer 暴露秒杀
       POST /seckill/{id}/{md5}/execution >执行秒杀

       Spring MVC理论

       Handler
       数据Model
       页面View
       运行流程：
        用户发送请求
        DispatcherServlet
        HandlerMapping
        HandlerAdapter
        Controller
        ModelAndView /seckill/list
        InternalResourceViewResolver  jsp
        Model
        list.jsp

        Http请求地址映射原理

         Servlet  (tomcat,jetty等)
         SpringMVC
         Handler
         Mapping
         注解 xml
         Handler处理
        @RequestMapping注解
         标注url
         ant风格url
         {xxx}占位符的url

         例如：
          /user/*/creation   /user/aa/creation
          /user/**/creation  /user/a/b/c/creation
          /user/{userId}

          /company/{companyId}/user/{userId}/detail

          请求方法的细节处理

          1请求参数绑定
          2请求方法限制
          3请求转发和重定向
          4数据模型赋值
          5返回json数据
          6cookie访问
          @RequestMapping(value="/{seckillId}/detail,
           method=RequestMethod.GET")
          public String detail(@PathVariable("seckillId"Long seckillId,Model model)){}

     json返回
          @RequestMapping(value="/{seckillId}/{md5}/execution,
          method=RequestMethod.POST,
          produces={"application/json;charset=UTF-8"})
          @ResponseBody  //返回json
          public SeckillResult<Exposer>exportSeckillURL(
          @PathVariable("seckillId") long id){}

     cookie访问
           @RequestMapping(value="/{seckillId}/{md5}/execution,
            method=RequestMethod.POST)
            public SeckillResult<Exposer>exportSeckillURL(
                      @PathVariable("seckillId") long id,
                      @PathVariable("md5") String md5,
                      @CookieValue(value="killPhone",required=false)Long phone){}


                   配置整合SpringMVC框架
      Bootstrap前端框架

      js复习

      太久没用过，犯了好多基础的错误
      引入controller中放入model的attribute的key，需要用${}来获取
      访问路径不想写项目名，可以通过设置web root=/

     通过jquery插件开发交互
     http://www.bootcdn.cn/  CDN服务

     测试js使用：
     在用alert调试时，一定要在调用某个方法前，明确是否为空
     否则的话，不会报错，反而会给测试带来混乱
     当获取到某个对象时，比如是个数字对象，在进行操作时，必须
     知道自己在操作的是对象还是在对对象的值做操作

     js文件的引入的一段js代码不知道为什么总是红色注释错误
     但是不影响运行？？？


     1验证手机登录  手机号存储在cookie
     2计时交互

     技术回顾

     前端交互设计
     Restful接口规范
     SpringMVC使用技巧
     Bootstrap和JS的使用

     倒计时
     未开始
     结束

     产品——前端--后端

     秒杀API的url设计

     SpringMVC使用
       配置  运行流程
       DTO数据传递
       注解映射驱动

      javascript模块化
      类似java的分包

      Jquery&plugin的使用
      计时插件
      cookie插件


      高并发分析和优化分析
      Java高并发秒杀API之高并发优化
      1分析
        发生在哪：详情页
                 地址暴露接口
                 执行秒杀
        why 单独获取系统时间？
         用户大量刷新
         CDN detail页静态化
             静态资源css,js等
         理解CDN:加速用户获取数据的系统  静 动
         部署在离用户最近的网络节点上
         命中CDN不需要访问后端服务器
         互联网公司自己搭建或是租用

         获取系统时间不用优化
          访问一次内存大约10ns
          1s可以1亿次

          获取秒杀地址解耦分析
            无法使用CDN缓存
            随着时间会变
            适合服务器缓存的redis，memache等
            后端缓存 业务控制
            一致性为本成本低

          秒杀地址接口优化
            请求地址--》redis-->mysql
            超时穿透  主动更新

          秒杀操作优化分析：
            无法cdn缓存
            后端缓存困难：库存问题
            一行数据竞争：热点商品

          其他解决方案分析：
           常用：
             执行秒杀--》原子计数器  redis nosql
             减库存--》记录行为消息  分布式MQ 消息队列
             消费消息并落地  mysql

            好处：抗并发
            成本分析：
              nosql  mq  运维成本和稳定性 分布式组件的稳定性要支持
              开发成本：数据一致性，回滚方案等
              幂等行难保证：重复秒杀问题
              不适合新手的架构

           why not mysql?
             mysql低效？  1s 4w qps
                低效的原因？
                java控制事务的行为分析
                update table set num=num-1 where id=10 and num>0
                insert购买
                另一个人执行这一操作，就要等待行锁，只有等上个操作
                完成后，释放锁后，才能被第二个人获得行锁
                同样的并行操作变成了串行等待
                阻塞操作

             瓶颈分析：
               update 减库存
                      网络延迟 GC garbage collection
               insert 购买明细
                      网络延迟 GC
                      commit/rollback
               一个线程结束
               慢的不是mysql,java
               java和数据库的通信有延迟
               执行时间

               行级锁在commit后释放
               优化方向：如何减少行级锁的持有时间
               延迟分析

               更新成功判断：
                 自身没报错
                 客户端确认update影响的记录数
                优化思路：
                  客户端逻辑放在mysql服务器，避免网络延迟和GC影响
                  解决：
                   1定制sql 但是需要修改mysql源码
                      不需要再返回给java做判断
                   2使用存储过程：整个事务在mysql端完成


                前端控制：暴露接口  按钮防重复
                动静态数据分离：CDN缓存静态资源，后端缓存redis
                事务竞争优化：减少事务锁时间

                在mysql服务端完成，不再mysql和java间在通讯

                Redis后端缓存优化编码

                详情页做静态化  CDN
                系统时间   不用优化
                计时在浏览器端  对服务器没有影响
                地址暴露接口 Resful请求访问后端
                执行秒杀请求

                使用Redis优化地址暴露接口

                引入jedis-client,protostuff的依赖后，pom.xml报错，编译也不能通过
                是引入的顺序问题放在前面就解决了

                秒杀操作：并发优化1

                减库存rowLock
                insert购买明细
                commit freeLock

                瓶颈主要在网络延迟 GC
                简单优化：
                 缩短时间
                 调整顺序
                 insert 主键冲突不多
                 update减库存 rowLock
                 commit freeLock

                 insert ignore 0 1
                 客户端会根据返回值判断是否继续执行
                  降低rowLock的持有时间

               存储过程  深度优化
                事务sql放在mysql端执行

bootcdn地址：http://www.bootcdn.cn/

降低行级锁到commit的时间，使得mysql获得更高的qps

在cmd控制台执行存储过程：
mysql -uroot -proot -Dseckill
show tables;
show create procedure execute_seckill\G
select * from seckill where seckill_id=1003;\G
DELIMITER ;
set @r_result=-3;
 call execute_seckill(1003,13501234567,now(),@r_result);
  --获取结果
  select @r_result;

  存储过程优化：事务行级锁持有的时间
  不要过度依赖存储过程

简单的逻辑可以应用存储过程

在项目中调用存储过程
db创建存储过程--》通过mybatis调用存储过程，dao层逻辑--》service层直接调用dao层的方法
 --》web层调用service的存储过程方法

 系统部署架构

 bootstrap  公网的CDN加速获取内容的方式
 静态化数据  强制静态化内容 推送CDN
 降低服务器的请求量
 WEBserver :Nginx+Jetty
 Redis
 Mysql

 系统架构：
  流量： cdn缓存
 只能DNS解析  Nginx 帮service容器做集群
  逻辑集群  Jetty  Jetty  Jetty
  缓存集群  Redis  Redis  Redis
  分库分表  DB-1  DB-2 DB-N   TDDL做分表用
  统计分析  handoop

  角色：
   开发： 前端+后端
   测试
   DBA
   运维  机器监控  脚本

   数据层技术回顾

    数据库设计和实现   db schema
    mybatis理解和使用技巧
    mybatis整合spring技巧

   业务层技术回顾

    业务接口设计和封装
    springIOC配置技巧   声明式事务xml，自定义类注解方式
    Spring的声明式事务使用和理解  何时回滚和提交

   WEB技术回顾

     Restful 接口运用 get post
     SpringMVC使用技巧
     前端交互分析过程
     Bootstrap和JS的使用

   并发优化

     系统瓶颈点分析
     事务 锁 网络延迟的理解
     前端 CDN 缓存等理解使用
     集群化部署


