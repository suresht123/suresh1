package org.exce;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

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

public class Webtab {
	
	public static void main(String[] args) throws Throwable {
		
			
			WebDriver driver;
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://nxtgenaiacademy.com/webtable/");
				
			File f = new File("C:\\Users\\Windows 11\\workspace\\Newproject\\target\\webtable.xlsx");
			FileOutputStream d= new FileOutputStream(f);
			Workbook w = new XSSFWorkbook();
		    Sheet s = w.createSheet("selftask");
			
			List<WebElement> iTable = driver.findElements(By.tagName("table"));
			
			for(int i=0; i<iTable.size(); i++) {
			WebElement t = iTable.get(i);
			WebElement iBody = t.findElement(By.tagName("tbody"));
			List<WebElement> iRow = iBody.findElements(By.tagName("tr"));
			 
			for(int j=0; j<iRow.size();j++) {
				WebElement r = iRow.get(j);
				Row ro =s.createRow(j);
								
				List<WebElement> tdata = r.findElements(By.tagName("td"));
				for(int k=0; k<tdata.size();k++) {
					WebElement data = tdata.get(k);
					String text = data.getText();
					System.out.println(text);	
					Cell ce = ro.createCell(k);
					ce.setCellValue(text);
				 }
				}	
				
		
				w.write(d);	
				}
	    }
	}