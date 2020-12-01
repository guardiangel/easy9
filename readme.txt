1.dos下项目目录路径下执行mvnw.cmd
2.在pom.xml中添加logback相关包声明
    <!--采用logback日志框架-->
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-core</artifactId>
                <version>1.2.3</version>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-access</artifactId>
                <version>1.2.3</version>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-classic</artifactId>
                <version>1.2.3</version>
            </dependency>
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
                <version>1.7.30</version>
            </dependency>
三、启动REDIS，安装redis for windows版本
    F:\applicationfolder\Redis-x64-3.0.504>redis-server redis.windows.conf
四、安装rabbitmq for windows版本，并启动。
五、数据源配置application.yml
六、ShiroConfig 拦截
    设置登录URL  shiroFilterFactoryBean.setLoginUrl("/index/login");
    跳转到对应的Controller，此处为IndexController
七、注释及用法说明
@Api协议集描述,用在Conntroller类上
@Value("${xxxx}")注解从配置文件读取值的用法
${system.sql_path}配置参考application-dev.xml
@Slf4j作用如下：
  private  final Logger logger = LoggerFactory.getLogger(当前类名.class);

@Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
@AllArgsConstructor ： 注在类上，提供类的全参构造
@NoArgsConstructor ： 注在类上，提供类的无参构造
@Setter ： 注在属性上，提供 set 方法
@Getter ： 注在属性上，提供 get 方法
@EqualsAndHashCode ： 注在类上，提供对应的 equals 和 hashCode 方法
@Log4j/@Slf4j ： 注在类上，提供对应的 Logger 对象，变量名为 log
@ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改
    value–字段说明
    name–重写属性名字
    dataType–重写属性类型
    required–是否必填
    example–举例说明
    hidden–隐藏



