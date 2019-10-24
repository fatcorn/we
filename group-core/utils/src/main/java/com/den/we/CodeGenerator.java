package com.den.we;

import org.springframework.security.crypto.codec.Utf8;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * mybatis-plus mapper、service、serviceImpl 代码生成
 * @author fatKarin
 * @date 2019/10/24 16:34
 */
public class CodeGenerator {

    // 项目路径
    static final String projectPath = System.getProperty("user.dir");

    static final String srcPath = "/src/main/java";

    static final String packagePath = "/com/dem/we";

    static final String mapperPath = "/mapper";

    static final String servicePath = "/service";

    static final String serviceImplPath = "/service/impl";

    static final String classSuffix = ".java";

    static final String xmlSuffix = ".xml";


    public static void main(String[]  args) {
        // 实体类名称
        String entityName = "Test";
        // 模块名称，代码文件生成地
        String moduleName = "";

        String mapperTemplate = "package com.den.we.mapper;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "\n" +
                "/**\n" +
                " * <p>\n" +
                " *  Mapper 接口\n" +
                " * </p>\n" +
                " *\n" +
                " * @author fatKarin\n" +
                " * @since 2019-09-09\n" +
                " */\n" +
                "\n" +
                "public interface" + entityName + "Mapper extends BaseMapper<" + entityName + "> {\n" +
                "\n" +
                "}";
        byte[] utf8Byte = mapperTemplate.getBytes(StandardCharsets.UTF_8);
        mapperTemplate = new String(utf8Byte, StandardCharsets.UTF_8);


        String filePath = "C:/Users/maccura/Desktop/" + entityName+ "Mapper" + classSuffix;

        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(mapperTemplate);// 往文件里写入字符串
            ps.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println(System.getProperty("user.dir"));

    }
}
