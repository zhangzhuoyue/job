package config;

import io.IResources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pojo.Configuration;
import pojo.MapperStatement;
import sun.security.krb5.Config;

import java.io.InputStream;
import java.util.Iterator;

/**
 * @author zzy
 * @date 2020/6/13 10:18
 */
public class XMLMappingBuilder {

    public Configuration configuration;

    public XMLMappingBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    //dom4j解析mapping.xml文件
    public Configuration parseXMLMappering(String path) throws DocumentException {
        //将sql配置文件加载到内存中
        InputStream resourceAsStream = IResources.getResourceAsStream(path);
        //将输入流转换为Document对象
        Document reader = new SAXReader().read(resourceAsStream);
        //
        Element rootElement = reader.getRootElement();
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()){
            Element next = elementIterator.next();
            String name = next.getName();
            String id = next.attributeValue("id");
            String paramterType = next.attributeValue("paramterType");
            String resultType = next.attributeValue("resultType");
            String textTrim = next.getTextTrim();
            MapperStatement statement = new MapperStatement();
            statement.setId(id);
            statement.setParamterType(paramterType);
            statement.setResultType(resultType);
            statement.setSql(textTrim);
            //每个sql配置文件标识 namespace+id
            configuration.getStatement().put(rootElement.attributeValue("namespace")+"."+id,statement);
        }
        return configuration;
    }



}
