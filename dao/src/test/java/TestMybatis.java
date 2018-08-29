import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;

public class TestMybatis {
    private static final Log LOGGER = LogFactory.getLog(TestMybatis.class);

    public static void main(String[] args) throws Exception {
        String path = "D:\\develop\\IntelliJ IDEA 2018.1.6\\workspace\\myspringboot\\dao\\src\\main\\java\\com\\myspringboot\\dao\\mysql\\mapper\\OrderMapper.xml";
        // LogFactory.getLog(TestMybatis.class);
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        Resource resource = new InputStreamResource(new FileInputStream(new File(path)));
        Resource[] resources = new Resource[]{resource};
        bean.setMapperLocations(resources);
        bean.setDataSource(dzDataSource());
    //   bean.setConfiguration(new Configuration());
        bean.setSqlSessionFactoryBuilder(new SqlSessionFactoryBuilder());
        bean.afterPropertiesSet();


        InputStream in = new FileInputStream(new File(path));
        // File file= Resources.getResourceAsFile(path);

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        //  sqlSession.selectList()

    }

    public static DruidDataSource dzDataSource() throws SQLException {
       DruidDataSource druidDataSource = new DruidDataSource();
 /*        druidDataSource.setUsername(dbUserName);
        druidDataSource.setPassword(dbPassword);
        druidDataSource.setUrl(connUrl);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setInitialSize(poolInitialSize);
        druidDataSource.setMinIdle(poolMinIdle);
        druidDataSource.setMaxActive(poolMaxActive);
        druidDataSource.setMaxWait(poolMaxWait);
        druidDataSource.setFilters("stat");*/
        return druidDataSource;
    }
}
