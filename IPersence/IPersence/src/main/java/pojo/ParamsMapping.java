package pojo;

/**
 * @author zzy
 * @date 2020/6/13 16:17
 */
public class ParamsMapping {

    public String content;//存放sql中解析的参数

    public ParamsMapping(String content){
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
