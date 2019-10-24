package com.den.we;

import org.springframework.security.crypto.codec.Utf8;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * mybatis-plus mapper��service��serviceImpl ��������
 * @author fatKarin
 * @date 2019/10/24 16:34
 */
public class CodeGenerator {

    // ��Ŀ·��
    static final String projectPath = System.getProperty("user.dir");

    static final String srcPath = "/src/main/java";

    static final String packagePath = "/com/den/we";

    static final String mapperPath = "/mapper";

    static final String servicePath = "/service";

    static final String serviceImplPath = "/service/impl";

    static final String classSuffix = ".java";

    static final String xmlSuffix = ".xml";


    public static void main(String[]  args) {
        // ʵ��������
        String entityName = "Club";
        // ģ�����ƣ������ļ����ɵ�
        String moduleName = "/ucenter";

        String mapperTemplate = "package com.den.we.mapper;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "import com.den.we.entity." +entityName+ ";" +
                "\n" +
                "/**\n" +
                " * <p>\n" +
                " *  " + entityName + "Mapper �ӿ�\n" +
                " * </p>\n" +
                " *\n" +
                " * @author fatKarin\n" +
                " * @since " + DateUtil.getDate()+"\n" +
                " */\n" +
                "\n" +
                "public interface " + entityName + "Mapper extends BaseMapper<" + entityName + "> {\n" +
                "\n" +
                "}";

        String serviceTemplate ="package com.den.we.service;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.extension.service.IService;\n" +
                "import com.den.we.entity." + entityName + ";\n" +
                "\n" +
                "/**\n" +
                " * <p>\n" +
                " *  ������\n" +
                " * </p>\n" +
                " *\n" +
                " * @author fatKarin\n" +
                " * @since " + DateUtil.getDate() + "\n" +
                " */\n" +
                "public interface I" + entityName +"Service extends IService<" + entityName + "> {\n" +
                "\n" +
                "}";

        String serviceImplTemplate = "package com.den.we.service.impl;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n" +
                "import com.den.we.entity." + entityName+ ";\n" +
                "import com.den.we.mapper." + entityName+ "Mapper;\n" +
                "import com.den.we.service.I" + entityName+ "Service;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "/**\n" +
                " * @author fatKarin\n" +
                " * @since " + DateUtil.getDate() + "\n" +
                " */\n" +
                "@Service\n" +
                "public class " + entityName+ "ServiceImpl extends ServiceImpl<" + entityName+ "Mapper, " + entityName+ "> implements I" + entityName+ "Service {\n" +
                "\n" +
                "}";

        String xmlTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
                "<mapper namespace=\"com.den.we.mapper." + entityName+ "Mapper\">\n" +
                "</mapper>";

        String mapperFilePath = projectPath + moduleName + srcPath + packagePath + mapperPath + "/" + entityName+ "Mapper" + classSuffix;

        String serviceFilePath = projectPath + moduleName + srcPath + packagePath + servicePath + "/I" + entityName+ "Service" + classSuffix;

        String serviceImplFilePath = projectPath + moduleName + srcPath + packagePath + serviceImplPath + "/" + entityName+ "ServiceImpl" + classSuffix;

        String xmlFilePath = projectPath + moduleName + srcPath + packagePath + mapperPath + "/" + entityName+ "Mapper" + xmlSuffix;

        try {
            File file = new File(mapperFilePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(mapperTemplate);// ���ļ���д���ַ���

            file = new File(serviceFilePath);
            ps = new PrintStream(new FileOutputStream(file));
            ps.println(serviceTemplate);// ���ļ���д���ַ���

            file = new File(serviceImplFilePath);
            ps = new PrintStream(new FileOutputStream(file));
            ps.println(serviceImplTemplate);// ���ļ���д���ַ���

            file = new File(xmlFilePath);
            ps = new PrintStream(new FileOutputStream(file));
            ps.println(xmlTemplate);// ���ļ���д���ַ���

            ps.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(System.getProperty("user.dir"));
    }
}
