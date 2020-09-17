package cat.wars.course.multidb.web.controller;

import cat.wars.course.multidb.annotation.DBSelector;
import cat.wars.course.multidb.enunms.DataSourceType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadWriteController {

  private final JdbcTemplate jdbcTemplate;

  public ReadWriteController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping("/read")
  @DBSelector(DataSourceType.READER)
  public String read() {
    return jdbcTemplate.queryForList("SELECT * FROM user").toString();
  }

  @GetMapping("/write")
  @DBSelector(DataSourceType.WRITER)
  public String write() {
    return jdbcTemplate.queryForList("SELECT * FROM t_user").toString();
  }
}
