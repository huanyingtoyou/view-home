package com.lihy.view.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 根据官网示例写的java示例
 * @author lihy
 * @date 2018/10/26
 */
@Configuration
//@MapperScan(basePackages = "com.lihy.view.sharding.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class ShardingDataSourceConfig {
    /*@Bean
    public DataSource getDataSource() throws SQLException {
        return getShardingDataSource();
    }*/
    //@Primary
    //@Bean(name = "shardingDataSource")
    @Bean
    public DataSource shardingDataSource() throws SQLException {
        // 配置真实数据源
        //Map<String, DataSource> dataSourceMap = new HashMap<>(2);

        //dataSourceMap.put("ds_0", buildDataSourceConfig("ds_0"));
        //dataSourceMap.put("ds_1", buildDataSourceConfig("ds_1"));

        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
        //DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "ds_0");

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        //shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds_${user_id % 2}"));
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new DatabaseShardingAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new TablePreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new ConcurrentHashMap(), new Properties());
        // 获取数据源对象
        //DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), new Properties());
        //return  dataSource;
    }

    /**
     * 需要手动配置事务管理器
     * @param shardingDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(shardingDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/sharding/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds_0", buildDataSourceConfig("ds_0"));
        result.put("ds_1", buildDataSourceConfig("ds_1"));
        return result;
    }

    /**
     * 数据源配置
     * @param dataSourceName
     * @return
     */
    private static DataSource buildDataSourceConfig(final String dataSourceName) {
        //使用druid连接数据库
        DruidDataSource druidDataSource = new DruidDataSource();
        //result.setDriverClassName(Driver.class.getName());
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl(String.format("jdbc:mysql://localhost:3306/%s?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true", dataSourceName));
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("");
        return druidDataSource;
    }

    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_${0..1}");
        return orderTableRuleConfig;
    }

    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
        orderItemTableRuleConfig.setLogicTable("t_order_item");
        //orderItemTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_item_${[0, 1]}");
        orderItemTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_item_${0..1}");
        return orderItemTableRuleConfig;
    }
}
