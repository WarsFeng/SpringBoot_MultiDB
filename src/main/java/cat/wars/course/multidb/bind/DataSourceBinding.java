package cat.wars.course.multidb.bind;

public class DataSourceBinding {

  private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static void bindDataSource(String dbType) {
    threadLocal.set(dbType);
  }

  public static String getDataSource() {
    return threadLocal.get();
  }

  public static void clearDataSource() {
    threadLocal.remove();
  }
}
