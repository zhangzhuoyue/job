package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/13 16:15
 */
public class BoundSql {

    public String sqlText;//解析出来的sql

    public List<ParamsMapping> list = new ArrayList<ParamsMapping>();//村昂存放解析出来的参数

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParamsMapping> getList() {
        return list;
    }

    public void setList(List<ParamsMapping> list) {
        this.list = list;
    }
}
