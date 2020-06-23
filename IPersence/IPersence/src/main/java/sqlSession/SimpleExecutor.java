package sqlSession;

import pojo.BoundSql;
import pojo.Configuration;
import pojo.MapperStatement;
import pojo.ParamsMapping;
import utils.GenericTokenParser;
import utils.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/13 14:47
 */
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception {
        /**
         * 数据库地址写错，造成的问题：程序一直停在这里，没有报错
         */
        //1. 注册驱动获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //将获取的sql进行装换，将#{} 转换为？(占位符)  。将#{}中的参数解析出来进行封装到boundSql对象中
        String sql = mapperStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);//封装SQl和参数名

        //3. 获取预处理对象
        PreparedStatement statement = connection.prepareStatement(boundSql.getSqlText());
        //获取参数的全路径
        String paramterType = mapperStatement.getParamterType();
        //根据全路径获取他的class对象
        Class<?> paramterClass = getClassType(paramterType);
        //通过反射获取参数值
        List<ParamsMapping> list = boundSql.getList();
        for (int i = 0;i < list.size() ;i++){
            ParamsMapping paramsMapping = list.get(i);
            String content = paramsMapping.getContent();
            Field declaredField = paramterClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            //预编译对象设置参数
            statement.setObject(i+1,o);
        }
        //执行sql
        ResultSet resultSet = statement.executeQuery();
        //获取sql配置文件中指定的返回参数类型的class对象
        String resultType = mapperStatement.getResultType();
        Class<?> resultClassType = getClassType(resultType);
        //存放结果对象集合
        ArrayList<Object> resultArray = new ArrayList();
        while (resultSet.next()){
            Object object = resultClassType.newInstance();
            /**
             * 得到结果集的结构信息，比如字段数、字段名等。
             *
             * ResultSetMetaData rsmt=rs.getMetaData();
             * 3、使用rs.getMetaData().getTableName(1))就可以返回表名
             * 4、rs.getMetaData().getColumnCount()字段数
             * 5、rs.getMetaData().getColumnName(i));字段名
             */
            ResultSetMetaData metaData = resultSet.getMetaData();
            //jdbc中传递参数，或者结果集中参数的元素的下标从1开始
            for (int i = 1;i <= metaData.getColumnCount() ;i++){
                //字段名
                String columnName = metaData.getColumnName(i);
                //字段值
                Object value = resultSet.getObject(columnName);
                /**使用反射或内省，将数据库表和实体对应，完成封装
                 *
                 * 使用反射：注意：1. 实体类型和数据库字段类型 2.查询出来的数据库字段名和实体字段的setxx是否匹配
                 *
                 */

                PropertyDescriptor descriptor = new PropertyDescriptor(columnName,resultClassType);
                Method writeMethod = descriptor.getWriteMethod();
                writeMethod.invoke(object,value);
            }
            resultArray.add(object);
        }
        return (List<E>)resultArray;
    }

    @Override
    public void update(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception {
        /**
         * 数据库地址写错，造成的问题：程序一直停在这里，没有报错
         */
        //1. 注册驱动获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //将获取的sql进行装换，将#{} 转换为？(占位符)  。将#{}中的参数解析出来进行封装到boundSql对象中
        String sql = mapperStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);//封装SQl和参数名

        //3. 获取预处理对象
        PreparedStatement statement = connection.prepareStatement(boundSql.getSqlText());
        //获取参数的全路径
        String paramterType = mapperStatement.getParamterType();
        //根据全路径获取他的class对象
        Class<?> paramterClass = getClassType(paramterType);
        //通过反射获取参数值
        List<ParamsMapping> list = boundSql.getList();
        for (int i = 0;i < list.size() ;i++){
            ParamsMapping paramsMapping = list.get(i);
            String content = paramsMapping.getContent();
            Field declaredField = paramterClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            //预编译对象设置参数
            statement.setObject(i+1,o);
        }
        //执行sql
        int resultSet = statement.executeUpdate();
        //return resultSet;
    }

    @Override
    public void delete(Configuration configuration, MapperStatement mapperStatement, Object... params) throws SQLException, ClassNotFoundException, Exception {
        /**
         * 数据库地址写错，造成的问题：程序一直停在这里，没有报错
         */
        //1. 注册驱动获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //将获取的sql进行装换，将#{} 转换为？(占位符)  。将#{}中的参数解析出来进行封装到boundSql对象中
        String sql = mapperStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);//封装SQl和参数名

        //3. 获取预处理对象
        PreparedStatement statement = connection.prepareStatement(boundSql.getSqlText());
        //获取参数的全路径
        String paramterType = mapperStatement.getParamterType();
        //根据全路径获取他的class对象
        Class<?> paramterClass = getClassType(paramterType);
        //通过反射获取参数值
        List<ParamsMapping> list = boundSql.getList();
        for (int i = 0;i < list.size() ;i++){
            ParamsMapping paramsMapping = list.get(i);
            String content = paramsMapping.getContent();
            Field declaredField = paramterClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            //预编译对象设置参数
            statement.setObject(i+1,o);
        }
        //执行sql
        int resultSet = statement.executeUpdate();
        //return resultSet;
    }

    @Override
    public void insert(Configuration configuration, MapperStatement mapperStatement, Object... params) throws SQLException, ClassNotFoundException, Exception {
        /**
         * 数据库地址写错，造成的问题：程序一直停在这里，没有报错
         */
        //1. 注册驱动获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //将获取的sql进行装换，将#{} 转换为？(占位符)  。将#{}中的参数解析出来进行封装到boundSql对象中
        String sql = mapperStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);//封装SQl和参数名

        //3. 获取预处理对象
        PreparedStatement statement = connection.prepareStatement(boundSql.getSqlText());
        //获取参数的全路径
        String paramterType = mapperStatement.getParamterType();
        //根据全路径获取他的class对象
        Class<?> paramterClass = getClassType(paramterType);
        //通过反射获取参数值
        List<ParamsMapping> list = boundSql.getList();
        for (int i = 0;i < list.size() ;i++){
            ParamsMapping paramsMapping = list.get(i);
            String content = paramsMapping.getContent();
            Field declaredField = paramterClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            //预编译对象设置参数
            statement.setObject(i+1,o);
        }
        //执行sql
        int resultSet = statement.executeUpdate();
        //return resultSet;
    }


    /**
     * 通过反射获取参数class对象
     * @param paramterType
     * @return
     */
    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null){
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
       return null;

    }

    /**
     * 完成对sql解析，1. 将#{}使用？替换， 2. 将#{}中的参数保存下来
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理器，配置标记解析器完成对占位符的解析
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        //解析出的sql
        String parseSql = tokenParser.parse(sql);
        //从#{}中解析出参数名
        List<ParamsMapping> parameterMappings = tokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql();
        boundSql.setSqlText(parseSql);
        boundSql.setList(parameterMappings);
        return boundSql;
    }


}
