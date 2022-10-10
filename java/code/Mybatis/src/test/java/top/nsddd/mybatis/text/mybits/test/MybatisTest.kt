package top.nsddd.mybatis.text.mybits.test

import org.example.mapper.UserMapper
import java.io.InputStream

class MybatisTest {

    @Test
    public void textMyBits() {
        // 1.读取配置文件
        String resource = "mybatis-config.xml"
        InputStream inputStream = Resources.getResourceAsStream(resource)
        // 2.创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream)
        // 3.获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession()
        // 4.获取接口的实现类对象
        // 会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
        UserMapper mapper = sqlSession.getMapper(UserMapper.class)
        // 5.执行查询所有方法
        List<User> userList = mapper.findAll()
        for (User user : userList) {
            System.out.println(user)
        }
        // 6.释放资源
        sqlSession.close()
        inputStream.close()
    }

}