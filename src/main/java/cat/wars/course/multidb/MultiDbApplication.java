package cat.wars.course.multidb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MultiDbApplication {

  public static void main(String[] args) {
    SpringApplication.run(MultiDbApplication.class, args);
  }
}
