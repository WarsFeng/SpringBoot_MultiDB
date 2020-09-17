package cat.wars.course.multidb.config;

import cat.wars.course.multidb.bind.DataSourceBinding;
import cat.wars.course.multidb.enunms.DataSourceType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiDataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.reader")
  public DataSource reader() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.writer")
  public DataSource writer() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @Primary
  public DynamicDataSource dataSource(DataSource reader, DataSource writer) {
    HashMap<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(DataSourceType.READER.name(), reader);
    targetDataSources.put(DataSourceType.WRITER.name(), writer);

    return new DynamicDataSource(reader, targetDataSources);
  }

  /** 动态数据源 DataSource */
  private static final class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(DataSource defaultDataSource, Map<Object, Object> targetDataSources) {
      this.setDefaultTargetDataSource(defaultDataSource);
      if (null == targetDataSources) return;
      this.setTargetDataSources(targetDataSources);
      this.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
      return DataSourceBinding.getDataSource();
    }
  }
}
