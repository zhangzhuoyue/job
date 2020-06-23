package sqlSession;

import pojo.Configuration;

/**
 * @author zzy
 * @date 2020/6/13 14:33
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    public Configuration configuration;

    public  DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }
    @Override
    public SqlSession openSqlSession() {
        //通过工厂类构造sqlsession
        DefaultSqlSession defaultSqlSession = new DefaultSqlSession(configuration);
        return defaultSqlSession;
    }
}
