package org.exce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Excel {
	
	public static void main(String[] args) throws Throwable   {
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		File f = new File("C:\\Users\\Windows 11\\workspace\\Newproject\\target\\flipkartexcemaven.xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("Flipkart");
		
		driver.get("http://www.flipkart.com");
		WebElement click= driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
		click.click();
		WebElement search=driver.findElement(By.xpath("//input[@type='text']"));
		search.sendKeys("MI mobiles");
        WebElement submit=driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
	    Thread.sleep(5000);
        List<WebElement> redmi=driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		int rs = redmi.size();
	    //System.out.println(rs);
		for(int i=0;i<redmi.size();i++) {
		WebElement name=redmi.get(i);
		String text=name.getText();
		 	 Row r = s.createRow(i);
		     Cell c = r.createCell(0);
		       	 c.setCellValue(text);
	       	 //System.out.println(text);
		}
			     FileOutputStream o = new FileOutputStream(f);
					w.write(o);
				
					FileInputStream f1 = new FileInputStream(f);
					Workbook w1 = new XSSFWorkbook(f1);
					Sheet s1 = w1.getSheet("Flipkart");
					Row r = s1.getRow(2);
					Cell c = r.getCell(0);
					String value = c.getStringCellValue();
					System.out.println("In Excel 3rd Value : ");
					System.out.println(value);
					
		String parant = driver.getWindowHandle();			
		driver.findElement(By.xpath("(//div[@class='_4rR01T'])[3]")).click();
		
		 Set<String> child = driver.getWindowHandles();
	        List<String> child1=new ArrayList<String>(child);
	        
	    driver.switchTo().window(child1.get(1));
		String text1 = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
		System.out.println("In Website Selected 3rd Mobile Link : ");
		System.out.println(text1);
		System.out.print("Both values are : ");
		if(value.equals(text1)) {
			System.out.println("both are equal");
		}
		else {
			System.out.println("Not equal");
		}
	}
}

