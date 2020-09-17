package cat.wars.course.multidb.aspect;

import cat.wars.course.multidb.annotation.DBSelector;
import cat.wars.course.multidb.bind.DataSourceBinding;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class MultiDataSourceAspect {

  @Around("@annotation(cat.wars.course.multidb.annotation.DBSelector)")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    MethodSignature signature = (MethodSignature) point.getSignature();
    DBSelector dbSelector = signature.getMethod().getAnnotation(DBSelector.class);
    if (null != dbSelector) {
      DataSourceBinding.bindDataSource(dbSelector.value().name());
    }

    try {
      return point.proceed();
    } finally {
      DataSourceBinding.clearDataSource();
    }
  }
}
