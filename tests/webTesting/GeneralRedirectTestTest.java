package webTesting;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class GeneralRedirectTestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void generalRedirectTest() {
    // Test name: General Redirect Test
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("http://localhost:12350/");
    // 2 | setWindowSize | 1058x812 | 
    driver.manage().window().setSize(new Dimension(1058, 812));
    // 3 | click | linkText=do external links work? | 
    driver.findElement(By.linkText("do external links work?")).click();
    // 4 | open | / | 
    driver.get("http://localhost:12350/");
    // 5 | click | linkText=do simple relative internal links work? | 
    driver.findElement(By.linkText("do simple relative internal links work?")).click();
    // 6 | click | linkText=back | 
    driver.findElement(By.linkText("back")).click();
    // 7 | click | linkText=do general relative links work | 
    driver.findElement(By.linkText("do general relative links work")).click();
    // 8 | click | linkText=back | 
    driver.findElement(By.linkText("back")).click();
    // 9 | click | linkText=do simple absolute links work | 
    driver.findElement(By.linkText("do simple absolute links work")).click();
    // 10 | click | linkText=back | 
    driver.findElement(By.linkText("back")).click();
    // 11 | click | linkText=do general absolute links work | 
    driver.findElement(By.linkText("do general absolute links work")).click();
    // 12 | click | linkText=back | 
    driver.findElement(By.linkText("back")).click();
    // 13 | click | linkText=do TXT files work | 
    driver.findElement(By.linkText("do TXT files work")).click();
    // 14 | open | / | 
    driver.get("http://localhost:12350/");
  }
}
