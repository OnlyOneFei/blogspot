package com.floating.common;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr_Fei
 * @description mybatis plus 代码生成器
 * @date 2020-12-22 22:28
 */
public class CodeGenerator {

    /**
     * 作者
     */
    private static final String AUTHOR = "Mr_Fei";
    /**
     * 表名，多表采用“,”分隔
     */
    private static final String TABLE_NAME = "sys_blog,sys_user";
    /**
     * 表前缀 生成类去掉前缀
     */
    private static final String TABLE_PREFIX = "sys_";
    /**
     * 父包名
     */
    private static final String PARENT = "com.floating";
    /**
     * 数据库连接
     */
    private static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/blogspot?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    /**
     * 用户名
     */
    private static final String USERNAME = "root";
    /**
     * 密码
     */
    private static final String PASSWORD = "root";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    /**
     * 模板引擎
     * freemarker: /templates/mapper.xml.ftl
     * velocity: /templates/mapper.xml.vm
     */
    private static final String TEMPLATE_PATH = "/templates/mapper.xml.ftl";


    /**
     * 生成主函数
     *
     * @param args args
     * @author Mr_Fei
     * @date 2020/12/22 22:56
     * @description main
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //获取项目路径
        String projectPath = System.getProperty("user.dir");
        System.out.println("=========>>>projectPath=" + projectPath);
        // 全局配置
        mpg.setGlobalConfig(initGlobalConfig(projectPath));
        // 数据源配置
        mpg.setDataSource(initDataSourceConfig());
        // 包配置
        mpg.setPackageInfo(new PackageConfig()
                .setModuleName(null)
                .setParent(PARENT));
        // 自定义配置
        mpg.setCfg(initInjectionConfig(projectPath));
        // 配置模板
        mpg.setTemplate(new TemplateConfig().setXml(null));
        // 策略配置
        mpg.setStrategy(initStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 全局配置
     *
     * @param projectPath projectPath
     * @return com.baomidou.mybatisplus.generator.config.GlobalConfig
     * @author Mr_Fei
     * @date 2020/12/22 22:55
     * @description initGlobalConfig
     */
    private static GlobalConfig initGlobalConfig(String projectPath) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor(AUTHOR);
        globalConfig.setOpen(false);
        // globalConfig.setSwagger2(true); 实体属性 Swagger2 注解
        globalConfig.setServiceName("%sService");
        return globalConfig;
    }

    /**
     * 数据源配置
     *
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     * @author Mr_Fei
     * @date 2020/12/22 22:52
     * @description initDataSourceConfig
     */
    private static DataSourceConfig initDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(DATABASE_URL);
        // dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        return dataSourceConfig;
    }

    /**
     * 自定义配置
     *
     * @param projectPath projectPath
     * @return com.baomidou.mybatisplus.generator.InjectionConfig
     * @author Mr_Fei
     * @date 2020/12/22 22:51
     * @description initInjectionConfig
     */
    private static InjectionConfig initInjectionConfig(String projectPath) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 策略配置
     *
     * @return com.baomidou.mybatisplus.generator.config.StrategyConfig
     * @author Mr_Fei
     * @date 2020/12/22 22:47
     * @description initStrategyConfig
     */
    private static StrategyConfig initStrategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLE_NAME.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(TABLE_PREFIX);
        return strategy;
    }
}
