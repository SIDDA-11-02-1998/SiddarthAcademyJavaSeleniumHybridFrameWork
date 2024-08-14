package SiddarthAcademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNg {
public  static ExtentReports getReportsObjects() {
	 String path = System.getProperty("user.dir")+"//reports//index.html";
	ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	reporter.config().setReportName("Web Automation Results");
	reporter.config().setDocumentTitle("Test Reults");
	ExtentReports exent = new ExtentReports();
	exent.attachReporter(reporter);
	exent.setSystemInfo("Tester", "Siddarth");
	return exent;
	
	
	
}
}
