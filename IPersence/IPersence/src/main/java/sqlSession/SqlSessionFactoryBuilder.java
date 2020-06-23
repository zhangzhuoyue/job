package sqlSession;

import config.XMLConfigBuilder;
import org.dom4j.DocumentException;
import pojo.Configuration;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author zzy
 * @date 2020/6/13 14:21
 */
public class SqlSessionFactoryBuilder {


    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        //使用dom4j解析配置文件，将数据库配置信息和sql配置信息封装在configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(inputStream);

        //创建sqlsessionfactory，工厂类，生产sqlsession回话
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }



}
