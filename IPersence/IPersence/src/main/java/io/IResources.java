package io;

import java.io.InputStream;

public class IResources {


    public static InputStream getResourceAsStream(String path){
        /**
         * 由于maven打包会把 src/main/java 和 src/main/resources 下的文件放到 target/classes 下，所以下面统一以根路径代表此目录，总结起来有以下几个规律：
         *
         * Class.getResource()的资源获取如果以 / 开头，则从根路径开始搜索资源。
         * Class.getResource()的资源获取如果不以 / 开头，则从当前类所在的路径开始搜索资源。
         * ClassLoader.getResource()的资源获取不能以 / 开头，统一从根路径开始搜索资源。      ****使用中
         *
         * getResourceAsStream读取的文件路径只局限与工程的源文件夹中，包括在工程src根目录下，以及类包里面任何位置，但是如果配置文件路径是在除了源文件夹之外的其他文件夹中时，该方法是用不了的。
         * ————————————————
         * 版权声明：本文为CSDN博主「Captain249」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
         * 原文链接：https://blog.csdn.net/Captain249/article/details/98172911
         */
        if (path == null || path.trim().equals("")) return  null;
        //InputStream inputStream = IResources.class.getResourceAsStream(path);
        InputStream inputStream = IResources.class.getClassLoader().getResourceAsStream(path);

        return inputStream;
    }
}
