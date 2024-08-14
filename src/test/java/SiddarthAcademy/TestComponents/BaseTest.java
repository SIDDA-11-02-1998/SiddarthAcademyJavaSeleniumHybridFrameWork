package SiddarthAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SiddarthAcademy.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lpage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//SiddarthAcademy//resources//GlobaData.properties");
		prop.load(fis);
		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browsername = prop.getProperty("browser");
		if (browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();

			WebDriverManager.chromedriver().setup();
			if (browsername.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 980));

		} else if (browsername.equalsIgnoreCase("FireFox")) {
			// FireFox
		} else if (browsername.equalsIgnoreCase("Edge")) {
			// Edge
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public String getScreenShot(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		// jackson data bind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;

	}

	@BeforeMethod(alwaysRun = true)

	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		lpage = new LandingPage(driver);
		lpage.goTo();
		return lpage;
	}

	@AfterMethod(alwaysRun = true)
	public void CloseBrowser() {
		driver.quit();
	}
}
