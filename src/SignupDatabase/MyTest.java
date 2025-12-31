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
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTest extends ParameterClass{
	
	@BeforeTest
	public void mytest() throws SQLException {
		
		TheSetupForDatabaseandSelenium();
	}
	
	@BeforeMethod
	public void RunBeforeEachTest() throws SQLException {
		
		stmt = con.createStatement();
	}
	
	
	@Test(priority = 1)
	public void ReadData()throws SQLException, IOException, InterruptedException {
		
		
	    rs = stmt.executeQuery(QueryToRead);
	
		while(rs.next()) {
			firstname = rs.getString("contactFirstName").replace(" ","_");
			Lastname = rs.getString("contactLastName").replace(" ","_");
			email= firstname+Lastname+rand.nextInt(376564)+"@gmail.com";
		
		address=rs.getString("addressLine1");
		postalCode = rs.getString("postalCode");
		city=rs.getString("city");
		}
	}
	@Test(priority = 2)
	public void Signup() throws InterruptedException, IOException {
		WebElement TheFirstName=driver.findElement(By.id("AccountFrm_firstname"));
		WebElement TheLastName=driver.findElement(By.id("AccountFrm_lastname"));
		WebElement TheEmailAddress= driver.findElement(By.id("AccountFrm_email"));
		WebElement Address=driver.findElement(By.id("AccountFrm_address_1"));
		WebElement countrySelect=driver.findElement(By.id("AccountFrm_country_id"));
		WebElement RegionSelect= driver.findElement(By.id("AccountFrm_zone_id"));
		WebElement postalcode=driver.findElement(By.id("AccountFrm_postcode"));
		WebElement City =driver.findElement(By.id("AccountFrm_city"));
		WebElement LoginNameInput=driver.findElement(By.id("AccountFrm_loginname"));
		WebElement Password=driver.findElement(By.id("AccountFrm_password"));
		WebElement ConfirmPassword=driver.findElement(By.id("AccountFrm_confirm"));
		WebElement ContinuButton=driver.findElement(By.xpath("//button[@title='Continue']"));
		WebElement acceptterms= driver.findElement(By.id("AccountFrm_agree"));                                
		
		TheFirstName.sendKeys(firstname);
		TheLastName.sendKeys(Lastname);
		TheEmailAddress.sendKeys(email);
		Address.sendKeys(address);
		
		ScrollAndScreenshot(100,"1");
		
		
		//SelectCountry
		Select MySelectForTheCountries = new Select(countrySelect);
		MySelectForTheCountries.selectByIndex(3);
		Thread.sleep(2000);
		
		//SelectRegion
		Select MySelectForRegion = new Select(RegionSelect);
		MySelectForRegion.selectByIndex(6);
		
		postalcode.sendKeys(postalCode);
		City.sendKeys(city);
		LoginName= firstname+Lastname+randomLoginNumber3;
		ScrollAndScreenshot(600,"2");
		
		LoginNameInput.sendKeys(LoginName);
		Password.sendKeys(password);
		ConfirmPassword.sendKeys(password);
		acceptterms.click();
		ScrollAndScreenshot(1000,"3");
		ContinuButton.click();
		Thread.sleep(4000);
		boolean ActualResult=driver.getPageSource().contains("Welcome back");
		Assert.assertEquals(ActualResult, expextedresultTheSignup);
	}
	
	
	@Test(priority = 3)
	public void InsertIntoTable() throws SQLException {
		int rowUpdate = stmt.executeUpdate(QueryToInsert);
	}
	
	@Test(priority = 4)
	public void UpdateTheData() throws SQLException, InterruptedException {
		int rowUpdate = stmt.executeUpdate(QueryToUpdate);
	}
	
	@Test(priority = 5)
	public void deleteQuery() throws SQLException {
		int rowUpdate = stmt.executeUpdate(QueryToDelete);
	}
	
	@AfterTest
    public void AfterFinishingMyTest() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
