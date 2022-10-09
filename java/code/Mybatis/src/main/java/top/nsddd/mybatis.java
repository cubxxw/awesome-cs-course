package top.nsddd;

import java.io.InputStream;

/*
* mybatis入门代码
*
*/
public class mybatis {
    String resource = "org/mybatis/example/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
}
