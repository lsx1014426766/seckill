 ʹ��idea����tomcat eidtCOnfiguration ѡ��tomcat7,����Ҳ���ɫ������������
Running war on http://localhost:9090/������pom.xml�����ָ���˶˿ں�9090
jdbc��spring�еĻ�ȡ��ʽ����
 Connections could not be acquired from the underlying database!

 1bootstrap��ҳ��
 2ʹ��jquery��2�������coutdown,cookie
 3����js��ģ�黯��ʽ����url����һ���ط�ͳһ���� ��ȡ �����װ ���Ե�

 ��һ����  ��ɱ֮dao��
 1maven��Ŀ����
 	�½�maven��Ŀ��ͨ��pom.xml������Ҫ������--��
 	������������groupid��artifactid����ͨ���������ҵ�maven repository
 	���������artifactid�����ҵ���pom.xml�е�д��
 	--������Ŀ���һ�--��Run As-->maven install �����������ص�����
 	
 2�����ļ��ļ���˳��
	  spring-dao.xml
	  mybatis-config.xml
	  mapper/*.xml
	 springͳһ����mybatis,���ݿ⣬dao�㣬entity��
 3���˳��
	   1pom.xml-->maven install
	   2entity��
	   3db table����
	   4dao��ӿڶ���
	   5дmybatis�������ļ� ���򵥵����� ���������ֶε�ӳ���ϵ��
	   6sql��xml�ļ�
	   7spring�����ļ� ����mybatis
	   8spring junit��Ԫ����
4���������⣺
  1xml�ļ���û��������ʾ--ͨ��windows-->preferences-->xml catalog -->add-->locationָ��dtdλ��
   ���ڻ�ȡdtd���ɴ���Ŀ�µ�MAVEN Project�����ҵ����Ӧ��jar������ĳ��λ���и�schema���ļ������������dtd���ڵ�λ����ʾ
  2  ����Ĳ���ֻ�ǽ��������������ʾ�����ǹ�������ֵ��û��
	   ������intellij������������������ʾ����ֻ���е��࣬��д��������ʱ������ʾ
	   Ҳ��������ĳ�����µ�nameֵ��ʾ���� 
	   
 �ڶ�����  ��ɱ֮service��
 ��ɱ  �������ԡ�   ��ɱ �������
 Java web ���õĿ�ܣ�springMVC+spring+mybatis
 dao:�ӿ����+sql  �����sql����  ����review
 dto:��עweb��service�����ݴ���   entity���ڵĺ�table���Ӧ
 ҵ��ӿڣ�
    �����棺 ������������  �򵥲��� ��������
   spring����service����
   spring IOC
       ���󹤳�
                 һ���Եķ��ʽӿ�
       ��������

     ҵ����������ͼ

     seckillService  SeckillDao,successKilledDAO  sqlSessionFacotry  dataSource
     why IOC
     ���󴴽�ͳһ�й�  not new
     �淶���������ڹ���  ��ʼ�� ����
     ��������ע��
     һ�µĻ�ȡ����   ��������ȡ��֪��ʵ��

     Spring-IOC��ע�뷽ʽ
         XML
              applicationzContext.xml
              Beanʵ�������Ե��������  DataSource��
              ��Ҫ�����ռ����� context aop mvc ǿ���spring����
         ע��
              �Զ����� @Service @Controller
         JAVA����
              spring��������
              ��Ҫͨ��������ƶ��󴴽��߼�
              �Զ����޸���������
     ʹ�ã�
         XML����
         package-scan  ��ɨ��
         Annotaitonע��
   spring����ʽ����
      ��������
      �޸�sql-1
      �޸�sql-2
      �ύ/�ع�����
      ȫ�Զ���ʽ
   ʹ�÷�ʽ
      ProxyFactoryBean+XML ����
      tx:advice+ap����ռ�  һ������
      ע��@Transactional   ע����ƣ��Ƽ���
   ����ʽǶ��
      ������Ϊ propagation_required

   ��ʱ�ع�����
      �׳��������쳣RuntimeException
      С�Ĳ�����try-catch
   spring ��������ʽ����

   logback�Ĺ���http://logback.qos.ch/demo.html
   �鿴�����ļ�xmlʾ��

   ��־����
        Ȩ���ɴ�С��
        FATAL(CRITICAL)
        ERROR
        WARN
        INFO
        DEBUG

   service���ܽ᣺
     1log��־���������Ϊdebugʱ�����Դ�ӡsqlִ����䣬infoʱ�����ԣ�Ӧ����Ĭ������
     2�׳��쳣������Զ����쳣����ʹ�þ�����쳣catch,�����Exception���͵�
     3���󹤳�������bean�������������spring���й�
      ��Ҫʹ������ĵط�����ע��ķ�ʽ����

   java�߲���API֮web��

     ǰ�˽������
     Restful  �淶url��ƹ淶
     SpringMVC  ����  Ӧ��
     bootstrp+jquery  ҳ�沼�� ����ʵ��

     1ǰ�˽������
     �������   ���   �Ѻõ�ϵͳ
         ��Ʒ
         ǰ��
         ���   �洢չʾ
       �б�ҳ   ��ɱ��Ʒ�б�
       ����ҳ   login-------->չʾ�߼�
                ��¼--cookie
                1��ȡ��׼ϵͳʱ��  ������ʱ��
                2ʱ���ж�  ��ʼ   ����ʱ��
                  ����   ����ʱ--����ɱ��ַ--����ɱ����--����ɱ���
       Restful�ӿ�
       ��ʲô
         ����Rail  ���ŵ�url��﷽ʽ ��Դ��״̬ ��״̬��ת��
       ��ô��
       GET /seckill/list
       POST /seckill/execute/{seckillId}  ����
       POST /seckill/{seckillId}/execution  ��
       DELETE /seckill/{id}/delete
       GET ��ѯ
       POST ����޸�  ���ݵȲ���
       PUT �޸�
       DELETE
       URL���
       /ģ��/��Դ/{��ʾ}/����1/...
       /user/{uid}/friends ->�����б�
       /user/{uid}/followers ->��ע���б�

       ��ɱAPI��URL���
       GET /seckill/list
       GET /seckill/{id}/detail
       GET /seckill/time/now ϵͳʱ��
       POST /seckill/{id}/exposer ��¶��ɱ
       POST /seckill/{id}/{md5}/execution >ִ����ɱ

       Spring MVC����

       Handler
       ����Model
       ҳ��View
       �������̣�
        �û���������
        DispatcherServlet
        HandlerMapping
        HandlerAdapter
        Controller
        ModelAndView /seckill/list
        InternalResourceViewResolver  jsp
        Model
        list.jsp

        Http�����ַӳ��ԭ��

         Servlet  (tomcat,jetty��)
         SpringMVC
         Handler
         Mapping
         ע�� xml
         Handler����
        @RequestMappingע��
         ��עurl
         ant���url
         {xxx}ռλ����url

         ���磺
          /user/*/creation   /user/aa/creation
          /user/**/creation  /user/a/b/c/creation
          /user/{userId}

          /company/{companyId}/user/{userId}/detail

          ���󷽷���ϸ�ڴ���

          1���������
          2���󷽷�����
          3����ת�����ض���
          4����ģ�͸�ֵ
          5����json����
          6cookie����
          @RequestMapping(value="/{seckillId}/detail,
           method=RequestMethod.GET")
          public String detail(@PathVariable("seckillId"Long seckillId,Model model)){}

     json����
          @RequestMapping(value="/{seckillId}/{md5}/execution,
          method=RequestMethod.POST,
          produces={"application/json;charset=UTF-8"})
          @ResponseBody  //����json
          public SeckillResult<Exposer>exportSeckillURL(
          @PathVariable("seckillId") long id){}

     cookie����
           @RequestMapping(value="/{seckillId}/{md5}/execution,
            method=RequestMethod.POST)
            public SeckillResult<Exposer>exportSeckillURL(
                      @PathVariable("seckillId") long id,
                      @PathVariable("md5") String md5,
                      @CookieValue(value="killPhone",required=false)Long phone){}


                   ��������SpringMVC���
      Bootstrapǰ�˿��

      js��ϰ

      ̫��û�ù������˺ö�����Ĵ���
      ����controller�з���model��attribute��key����Ҫ��${}����ȡ
      ����·������д��Ŀ��������ͨ������web root=/

     ͨ��jquery�����������
     http://www.bootcdn.cn/  CDN����

     ����jsʹ�ã�
     ����alert����ʱ��һ��Ҫ�ڵ���ĳ������ǰ����ȷ�Ƿ�Ϊ��
     ����Ļ������ᱨ������������Դ�������
     ����ȡ��ĳ������ʱ�������Ǹ����ֶ����ڽ��в���ʱ������
     ֪���Լ��ڲ������Ƕ������ڶԶ����ֵ������

     js�ļ��������һ��js���벻֪��Ϊʲô���Ǻ�ɫע�ʹ���
     ���ǲ�Ӱ�����У�����


     1��֤�ֻ���¼  �ֻ��Ŵ洢��cookie
     2��ʱ����

     �����ع�

     ǰ�˽������
     Restful�ӿڹ淶
     SpringMVCʹ�ü���
     Bootstrap��JS��ʹ��

     ����ʱ
     δ��ʼ
     ����

     ��Ʒ����ǰ��--���

     ��ɱAPI��url���

     SpringMVCʹ��
       ����  ��������
       DTO���ݴ���
       ע��ӳ������

      javascriptģ�黯
      ����java�ķְ�

      Jquery&plugin��ʹ��
      ��ʱ���
      cookie���


      �߲����������Ż�����
      Java�߲�����ɱAPI֮�߲����Ż�
      1����
        �������ģ�����ҳ
                 ��ַ��¶�ӿ�
                 ִ����ɱ
        why ������ȡϵͳʱ�䣿
         �û�����ˢ��
         CDN detailҳ��̬��
             ��̬��Դcss,js��
         ���CDN:�����û���ȡ���ݵ�ϵͳ  �� ��
         ���������û����������ڵ���
         ����CDN����Ҫ���ʺ�˷�����
         ��������˾�Լ����������

         ��ȡϵͳʱ�䲻���Ż�
          ����һ���ڴ��Լ10ns
          1s����1�ڴ�

          ��ȡ��ɱ��ַ�������
            �޷�ʹ��CDN����
            ����ʱ����
            �ʺϷ����������redis��memache��
            ��˻��� ҵ�����
            һ����Ϊ���ɱ���

          ��ɱ��ַ�ӿ��Ż�
            �����ַ--��redis-->mysql
            ��ʱ��͸  ��������

          ��ɱ�����Ż�������
            �޷�cdn����
            ��˻������ѣ��������
            һ�����ݾ������ȵ���Ʒ

          �����������������
           ���ã�
             ִ����ɱ--��ԭ�Ӽ�����  redis nosql
             �����--����¼��Ϊ��Ϣ  �ֲ�ʽMQ ��Ϣ����
             ������Ϣ�����  mysql

            �ô���������
            �ɱ�������
              nosql  mq  ��ά�ɱ����ȶ��� �ֲ�ʽ������ȶ���Ҫ֧��
              �����ɱ�������һ���ԣ��ع�������
              �ݵ����ѱ�֤���ظ���ɱ����
              ���ʺ����ֵļܹ�

           why not mysql?
             mysql��Ч��  1s 4w qps
                ��Ч��ԭ��
                java�����������Ϊ����
                update table set num=num-1 where id=10 and num>0
                insert����
                ��һ����ִ����һ��������Ҫ�ȴ�������ֻ�е��ϸ�����
                ��ɺ��ͷ����󣬲��ܱ��ڶ����˻������
                ͬ���Ĳ��в�������˴��еȴ�
                ��������

             ƿ��������
               update �����
                      �����ӳ� GC garbage collection
               insert ������ϸ
                      �����ӳ� GC
                      commit/rollback
               һ���߳̽���
               ���Ĳ���mysql,java
               java�����ݿ��ͨ�����ӳ�
               ִ��ʱ��

               �м�����commit���ͷ�
               �Ż�������μ����м����ĳ���ʱ��
               �ӳٷ���

               ���³ɹ��жϣ�
                 ����û����
                 �ͻ���ȷ��updateӰ��ļ�¼��
                �Ż�˼·��
                  �ͻ����߼�����mysql�����������������ӳٺ�GCӰ��
                  �����
                   1����sql ������Ҫ�޸�mysqlԴ��
                      ����Ҫ�ٷ��ظ�java���ж�
                   2ʹ�ô洢���̣�����������mysql�����


                ǰ�˿��ƣ���¶�ӿ�  ��ť���ظ�
                ����̬���ݷ��룺CDN���澲̬��Դ����˻���redis
                �������Ż�������������ʱ��

                ��mysql�������ɣ�����mysql��java����ͨѶ

                Redis��˻����Ż�����

                ����ҳ����̬��  CDN
                ϵͳʱ��   �����Ż�
                ��ʱ���������  �Է�����û��Ӱ��
                ��ַ��¶�ӿ� Resful������ʺ��
                ִ����ɱ����

                ʹ��Redis�Ż���ַ��¶�ӿ�

                ����jedis-client,protostuff��������pom.xml��������Ҳ����ͨ��
                �������˳���������ǰ��ͽ����

                ��ɱ�����������Ż�1

                �����rowLock
                insert������ϸ
                commit freeLock

                ƿ����Ҫ�������ӳ� GC
                ���Ż���
                 ����ʱ��
                 ����˳��
                 insert ������ͻ����
                 update����� rowLock
                 commit freeLock

                 insert ignore 0 1
                 �ͻ��˻���ݷ���ֵ�ж��Ƿ����ִ��
                  ����rowLock�ĳ���ʱ��

               �洢����  ����Ż�
                ����sql����mysql��ִ��

bootcdn��ַ��http://www.bootcdn.cn/

�����м�����commit��ʱ�䣬ʹ��mysql��ø��ߵ�qps

��cmd����ִ̨�д洢���̣�
mysql -uroot -proot -Dseckill
show tables;
show create procedure execute_seckill\G
select * from seckill where seckill_id=1003;\G
DELIMITER ;
set @r_result=-3;
 call execute_seckill(1003,13501234567,now(),@r_result);
  --��ȡ���
  select @r_result;

  �洢�����Ż��������м������е�ʱ��
  ��Ҫ���������洢����

�򵥵��߼�����Ӧ�ô洢����

����Ŀ�е��ô洢����
db�����洢����--��ͨ��mybatis���ô洢���̣�dao���߼�--��service��ֱ�ӵ���dao��ķ���
 --��web�����service�Ĵ洢���̷���

 ϵͳ����ܹ�

 bootstrap  ������CDN���ٻ�ȡ���ݵķ�ʽ
 ��̬������  ǿ�ƾ�̬������ ����CDN
 ���ͷ�������������
 WEBserver :Nginx+Jetty
 Redis
 Mysql

 ϵͳ�ܹ���
  ������ cdn����
 ֻ��DNS����  Nginx ��service��������Ⱥ
  �߼���Ⱥ  Jetty  Jetty  Jetty
  ���漯Ⱥ  Redis  Redis  Redis
  �ֿ�ֱ�  DB-1  DB-2 DB-N   TDDL���ֱ���
  ͳ�Ʒ���  handoop

  ��ɫ��
   ������ ǰ��+���
   ����
   DBA
   ��ά  �������  �ű�

   ���ݲ㼼���ع�

    ���ݿ���ƺ�ʵ��   db schema
    mybatis����ʹ�ü���
    mybatis����spring����

   ҵ��㼼���ع�

    ҵ��ӿ���ƺͷ�װ
    springIOC���ü���   ����ʽ����xml���Զ�����ע�ⷽʽ
    Spring������ʽ����ʹ�ú����  ��ʱ�ع����ύ

   WEB�����ع�

     Restful �ӿ����� get post
     SpringMVCʹ�ü���
     ǰ�˽�����������
     Bootstrap��JS��ʹ��

   �����Ż�

     ϵͳƿ�������
     ���� �� �����ӳٵ����
     ǰ�� CDN ��������ʹ��
     ��Ⱥ������


