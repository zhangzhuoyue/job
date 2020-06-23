package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import io.IResources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import pojo.Configuration;

import javax.sql.CommonDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author zzy
 * @date 2020/6/12 22:09
 */
public class XMLConfigBuilder {

    public Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 使用dom4j解析xml配置，将其封装早configuration
     */

    public Configuration parse(InputStream inputStream) throws DocumentException, PropertyVetoException {
        //读取刘流文件，生成document对象
        Document document = new SAXReader().read(inputStream);
        //获取document对象的根节点
        Element rootElement = document.getRootElement();
        //elementIterator()返回document对象中该元素的迭代器
        Iterator<Element> element = rootElement.elementIterator("properties");
        Properties properties = new Properties();
        while (element.hasNext()){
            Element next = element.next();
            String name = next.attributeValue("name");
            String value = next.attributeValue("value");
            properties.put(name,value);
        }
        //将数据库配置封装到datasource
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty("driverClass"));
        dataSource.setJdbcUrl(properties.getProperty("url"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("pwd"));
        configuration.setDataSource(dataSource);
        /**
         * Mapping.xml解析
         */

        //从数据库配置文件中获取sql配置文件的地址,可能有多个sql配置文件，所以使用迭代地址元素
        Iterator<Element> resource = rootElement.elementIterator("resource");
        while (resource.hasNext()){
            Element next = resource.next();
            String path = next.attributeValue("value");
            XMLMappingBuilder mapperingbuilder = new XMLMappingBuilder(configuration);
            configuration = mapperingbuilder.parseXMLMappering(path);
        }
        return configuration;
    }

}
