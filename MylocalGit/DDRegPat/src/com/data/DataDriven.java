package com.data;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriven {
		 
		 
		 @Test(dataProvider = "data")
		 public void DataDrivenTest1(String FirstName, String LastName, String DoB, String License,String SSN, String State,String City,String Address,String Zip, String Age, 
				 String Height,String Weight, String Pharma, String PharmaAdd, String Email, String UserName, String Pwd, String ConfPwd, String SecurityQ, String SecurityA) {
		  
		  WebDriver driver = new FirefoxDriver();
		  
		  driver.navigate().to("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  
		  
		  driver.findElement(By.xpath(".//*[@id='navigation']/li[2]/a")).click();
	 		
	 	  WebElement webElement = driver.findElement(By.xpath("//*[@id='testimonials']/div/div/div/div[3]/a"));
	 	  JavascriptExecutor executor = (JavascriptExecutor) driver;
	 	  executor.executeScript("arguments[0].click();", webElement);
	 		
	 	  driver.findElement(By.id("firstname")).sendKeys(FirstName);
		  driver.findElement(By.id("lastname")).sendKeys(LastName);
		  driver.findElement(By.id("datepicker")).sendKeys(DoB);
		  driver.findElement(By.id("license")).clear();
		  driver.findElement(By.id("license")).sendKeys(License);
		  driver.findElement(By.id("ssn")).clear();
		  driver.findElement(By.id("ssn")).sendKeys(SSN);
		  driver.findElement(By.id("state")).sendKeys(State);
		  driver.findElement(By.id("city")).sendKeys(City);
		  driver.findElement(By.id("address")).sendKeys(Address);
		  driver.findElement(By.id("zipcode")).sendKeys(Zip);
		  driver.findElement(By.id("age")).sendKeys(Age);
		  driver.findElement(By.id("height")).sendKeys(Height);
		  driver.findElement(By.id("weight")).sendKeys(Weight);
		  driver.findElement(By.id("pharmacy")).sendKeys(Pharma);
		  driver.findElement(By.id("pharma_adress")).sendKeys(PharmaAdd);
	 	  driver.findElement(By.id("email")).sendKeys(Email);
		  driver.findElement(By.id("username")).sendKeys(UserName);
		  driver.findElement(By.id("password")).sendKeys(Pwd);
		  driver.findElement(By.id("confirmpassword")).sendKeys(ConfPwd);
			 
			 Select securitydropdown = new Select(driver.findElement(By.id("security")));
			 //securitydropdown.selectByVisibleText(SecurityQ);
			 securitydropdown.selectByIndex(2);	
			 
			 driver.findElement(By.id("answer")).sendKeys(SecurityA);
			 
			driver.findElement(By.xpath("//input[contains(@name,'register')]")).click();		

		  
		 }
		 
		 
		 @DataProvider(name = "data")
		 public Object[][] testDataSupplier() throws Exception{
		 //file path where excel file placed, containing test data.
		  String filePath = "TD.xlsx";
		  
		  //read excel file using file input stream, using Apache POI
		  FileInputStream fis = new FileInputStream(new File (filePath));
		  XSSFWorkbook wb = new XSSFWorkbook(fis);
		  XSSFSheet sheet = wb.getSheet("Sheet3");
		  wb.close();
		  
		  //calculate total number of rows and columns so that we can iterate over it.
		  int totalNumberOfRows = sheet.getLastRowNum()+1;			  
		  System.out.println("Rows  -- "+totalNumberOfRows);
		  int totalNumberOfCols = sheet.getRow(0).getLastCellNum();
		  System.out.println("Columns  --- "+totalNumberOfCols);
		  		  
		  //create an object array. which will store the test data from excel file
		  Object[][] testdata1 = new Object[totalNumberOfRows][totalNumberOfCols];

		  
		  for (int i = 0; i <totalNumberOfRows; i++ ){
		   for (int j = 0; j < totalNumberOfCols; j++){

		  testdata1[i][j] =  sheet.getRow(i).getCell(j).toString();   
		  System.out.println(testdata1[i][j]);
		   }
		  }
		  return testdata1;
		  
		   
		 } 
		 
		 }
		 
		 

		


/*@DataProvider(name = "data")
public String[][] readXlsFile() throws BiffException, IOException {
	
	File f = new File("TD.xlsx");
	FileInputStream fis = new FileInputStream(f);	
	XSSFWorkbook wb = new XSSFWorkbook(f);
	XSSFSheet sheet = wb.getSheet("Sheet3");
	wb.close();

	int rows = sheet.get
	int cols = sheet.getColumns();
	System.out.println(rows);
	System.out.println(cols);

	String data[][] = new String[rows-1][cols];
	int k=0;
	for(int i = 1; i<rows;i++)
	{
		for(int j=0;j<cols;j++)
		{
			data[k][j]=sheet.getCell(j, i).getContents();
			System.out.println(" k :: " + k );
			System.out.println(" j::: "  + j );
			System.out.println(data[k][j]);
		}
		k++;
	}
*/


