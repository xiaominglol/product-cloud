
#server:
#  port: 8012
#spring:
#  datasource:
#    druid:
#      db-type: mysql
#      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMills=5000
#      filters: stat,wall,config
#      test-while-idle: true
#      validation-query: SELECT 1
#      driver-class-name: com.p6spy.engine.spy.P6SpyDriver
#      url: jdbc:p6spy:mysql://47.110.47.45:3306/product?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC
#      username: root
#      password: root
#  redis:
#    ## 单机配置
#    host: 192.168.0.110
#    port: 6379
#    database: 0
#    lettuce:
#      pool:
#        ## 连接池最大连接数（使用负值表示没有限制）
#        max-active: 300
#        ## 连接池中的最大空闲连接
#        max-idle: 100
#        ## 连接池最大阻塞等待时间（使用负值表示没有限制）
#        max-wait: -1
#        ## 连接池中的最小空闲连接
#        min-idle: 20
#    ## 连接超时时间（毫秒）
#    timeout: 60000
#  resources:
#    static-locations: classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/,file:${gemini.pictureUploadPath}
#  thymeleaf:
#    #开发的时候禁用模板引擎的缓存，否则修改刷新无效
#    cache: false
#
##gemini自定义配置
#gemini:
#  #用户头像上传目录 /home/gemini/prod/picture
#  pictureUploadPath: E:/file/picture/
#
#mybatis-plus:
#  mapper-locations: classpath:/mapper/**/*.xml
#uid:
#  timeBits: 37
#  workerBits: 17
#  seqBits: 9
#management:
#  endpoints:
#    web:
#      exposure:
#        include: "*"
