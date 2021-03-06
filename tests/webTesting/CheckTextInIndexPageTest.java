package webTesting;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class CheckTextInIndexPageTest {
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
  public void checkTextInIndexPage() {
    // Test name: Check Text In Index Page
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("http://localhost:12350/");
    // 2 | verifyText | css=div | Hello, It works !!!\ndo external links work?\ndo simple relative internal links work?\ndo general relative links work\ndo simple absolute links work\ndo general absolute links work\ndo TXT files work
    assertThat(driver.findElement(By.cssSelector("div")).getText(), is("Hello, It works !!!\\ndo external links work?\\ndo simple relative internal links work?\\ndo general relative links work\\ndo simple absolute links work\\ndo general absolute links work\\ndo TXT files work"));
    // 3 | click | linkText=do simple relative internal links work? |
    driver.findElement(By.linkText("do simple relative internal links work?")).click();
    // 4 | verifyText | linkText=back | back
    assertThat(driver.findElement(By.linkText("back")).getText(), is("back"));
    // 5 | click | linkText=back | 
    driver.findElement(By.linkText("back")).click();
    // 6 | click | linkText=do TXT files work | 
    driver.findElement(By.linkText("do TXT files work")).click();
    // 7 | verifyText | css=pre | Hello TXT works
    assertThat(driver.findElement(By.cssSelector("pre")).getText(), is("Hello TXT works"));
  }
}
