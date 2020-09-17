package cat.wars.course.multidb.annotation;

import cat.wars.course.multidb.enunms.DataSourceType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBSelector {

  DataSourceType value() default DataSourceType.READER;
}
