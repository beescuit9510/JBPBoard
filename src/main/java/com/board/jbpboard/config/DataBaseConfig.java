package com.board.jbpboard.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(
        sqlSessionFactoryRef = "sqlSessionFactory"
)
public class DataBaseConfig {

    @Primary
    @Bean(name = "dataSoruce")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }


    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        // 스프링 빈 객체
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        // 빈 객체에 application.properties에 작성한 설정 정보를 주입한다.
        bean.setDataSource(dataSource());

        return bean.getObject();

    }

}
