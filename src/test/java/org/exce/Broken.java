package org.exce;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Broken {
	
	public static void main(String[] args) throws Throwable {
		
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		List<WebElement> links =driver.findElements(By.tagName("a"));
		System.out.println("Total links:"+ links.size());
		
		
		int i =0;
		int j =0;
		int k =0;
		for ( WebElement link :links){
			
			String url= link.getAttribute("href");
			
			
			if (url == null || url.isEmpty()){
				
				System.out.println("empty url");
				k++;
				
				continue;
			}
				try {
					
	HttpURLConnection huc = (HttpURLConnection) ( new URL(url).openConnection());
	huc.connect();
	
	if (huc.getResponseCode()>=400){
		System.out.println("Broken url");
		System.out.println(url);
		int code = huc.getResponseCode();
	    System.out.println(code);
		String message  = huc.getResponseMessage();
		System.out.println(message);
		j++;

	
	}
	else{
//  System.out.println("valid url");
	i++;
	}
	}
				catch(IOException e){
					
					e.printStackTrace();
				}
		}
		System.out.println("valid url count :" +i);
		System.out.println("empty url count :" +k);
	    System.out.println("broken url count :" +j);
		driver.quit();
	}

}
