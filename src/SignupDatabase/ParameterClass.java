package SignupDatabase;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class ParameterClass {

	WebDriver driver = new EdgeDriver();
	Random rand = new Random();
	
	Connection con;
	
	Statement stmt;
	
	ResultSet rs;
	
	String firstname;
	String Lastname;
	String email;
	String address;
	String city;
	String postalCode;
	String password="bb123";
	String LoginName= firstname+Lastname;
	Date timeStamp = new Date();
	
	String [] customerNumbers = {"103","114","119","121"};
	int randomCustomerNumber = rand.nextInt(customerNumbers.length);
	String TheSelectedCustomer = customerNumbers[randomCustomerNumber];
	String QueryToRead = "select * from customers where customerNumber ="+TheSelectedCustomer;
	String QueryToDelete = "delete from customers where customerNumber=9999";
	String QueryToInsert = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (9999, 'mahmoud', 'Schmitt', 'Carine', '40.32.2555', '54, rue Royale', NULL, 'Nantes', NULL, '44000', 'France', 1370, 21000);";
	String QueryToUpdate = "update customers set customerName='Qa Automation' where customerNumber=9999";
	
	boolean expextedresultTheSignup= true;
	
	int randomLoginNumber = rand.nextInt(23456);
	int randomLoginNumber2 = rand.nextInt(233456);
String randomLoginNumber3 =Integer.toString(randomLoginNumber*randomLoginNumber2);

	public void TheSetupForDatabaseandSelenium() throws SQLException {
		
		driver.get("https://automationteststore.com/index.php?rt=account/create");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","ib2807");
		
		
	}
	
	
	
	
	public void ScrollAndScreenshot(int TheValueWhereToStop,String screenshotorder) throws IOException, InterruptedException {
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,arguments[0])",TheValueWhereToStop);
		Thread.sleep(1000);
		
		TakesScreenshot ts =(TakesScreenshot) driver;
		File MyScreenshotFile = ts.getScreenshotAs(OutputType.FILE);
		
		String filename = timeStamp.toString().replace(":", "-")+screenshotorder;
		FileUtils.copyFile(MyScreenshotFile, new File("src/screenshot"+filename+".jpg"));
	
		
		
	}
	
	
	
	
	
	
	
	
}
