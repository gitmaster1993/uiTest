package Support;

public class ExecutionUtil {
  //private static String urlLocal = "http://localhost:3000/addon-app/cwcalc/latest/";
  private static String urlLocal = "http://localhost:3000/addon-app/cwcalc/latest/";

//  private static String urlUnstable = "http://master.unstable.cwcalc.com/addon-app/cwcalc/latest/#/te/st/";
  private static String urlUnstable = "https://www.ikea.com/addon-app/cwcalc/20210629.1/#/"; //change version
  private static String urlIframe = "https://www.ikea.com/addon-app/cwcalc/iframe/index.html";
  public static String urlUnstableSE = "https://www.ikea.com/addon-app/cwcalc/20210629.1/#/se/en/"; //change version

  private static String overrideUrl = null;


  public enum BROWSER {
    IE,
    EDGE,
    CHROME,
    CHROME_MOBILE_EMULATED,
    CHROME_ANDROID,
    FIREFOX,
    SAFARI,
    PHANTOMJS,
  }


  public static BROWSER getExecutionBrowser()
  {
    String browserName = System.getProperty("browserName");
    if (browserName != null) {
      return BROWSER.valueOf(browserName);
    } else {
      return DEFAULT_EXEC_BROWSER;
    }
  }

  private static String url()
  {
    if(overrideUrl != null) {
      return overrideUrl;
    }
    String url = System.getProperty("url");
    if (url != null) {
      return url;
    } else {
      return DEFAULT_EXEC_URL;
    }
  }

  public static String getUrl()
  {
    return url() + "?uitest=disablePriceAnimation";
  }

  public static String getUrl(String append)
  {
    return url()+ append + "?uitest=disablePriceAnimation";
  }

  public static String getUrl(String[] queryParameters) {
    String url = getUrl();
    for (String query : queryParameters) {
      url += "&" + query;
    }
    return url;
  }

  public static String getUrl(String append, String[] queryParameters) {
    String url = getUrl(append);
    for (String query : queryParameters) {
      url += "&" + query;
    }
    return url;
  }

  public static String cleanId(String id) {
    return id.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
  }

  public static void setUrl(String url) {
    overrideUrl = url;
  }

  public static int getTimeout() {
    String timeout = System.getProperty("timeout");
    if (timeout != null) {
      try {
        return Integer.parseInt(timeout);
      } catch (NumberFormatException e) {
        System.out.println("WARNING: Wrong format of timeout parameter. Setting timeout to default value: " + DEFAULT_TIMEOUT + "s");
        return DEFAULT_TIMEOUT;
      }
    } else {
      return DEFAULT_TIMEOUT;
    }
  }


  /* DEFAULT VARIABLES */
  private static final BROWSER DEFAULT_EXEC_BROWSER = BROWSER.FIREFOX;
  private static final String DEFAULT_EXEC_URL = urlUnstable;
  //private static final String DEFAULT_EXEC_URL = urlLocal;
  private static final int DEFAULT_TIMEOUT = 10;
}
