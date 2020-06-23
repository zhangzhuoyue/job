package sqlSession;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/13 14:32
 */
public interface SqlSession {
    //查询所有
    public <T> List<T> selectList(String statementId, Object... params) throws Exception;

    //根据条件查询单个条件
    public <T> T selectOne(String statementId,Object... params) throws Exception;

    //根据条件进行更新
    public void update(String statementId,Object... params) throws Exception;

    //插入
    public void insert(String statementId,Object... params) throws Exception;


    //删除
    public void delete(String statementId,Object... params) throws Exception;
    //为dao接口生成代理类
    public <T> T getMapper(Class<?> mapperClass);


}
