package sqlSession;

import pojo.Configuration;
import pojo.MapperStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/13 14:44
 */
public interface Executor {
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException, Exception;

    public void update(Configuration configuration, MapperStatement mapperStatement,Object... params) throws SQLException, ClassNotFoundException, Exception;
    public void delete(Configuration configuration, MapperStatement mapperStatement,Object... params) throws SQLException, ClassNotFoundException, Exception;
    public void insert(Configuration configuration, MapperStatement mapperStatement,Object... params) throws SQLException, ClassNotFoundException, Exception;
}
