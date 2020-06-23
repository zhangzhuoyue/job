package pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    //数据源
    public DataSource dataSource;
    //key：statementId  value  :MapperStatement
    public  Map<String,MapperStatement> statement = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getStatement() {
        return statement;
    }

    public void setStatement(Map<String, MapperStatement> statement) {
        this.statement = statement;
    }
}
