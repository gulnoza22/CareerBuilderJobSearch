package com.careerbuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CareerBuilderJobSearch {

	public static void main(String[] args) throws IOException, InterruptedException {
	
		WebDriverManager.chromedriver().setup();
		FileReader reader = new FileReader("Jobs.txt");
		BufferedReader bufread= new BufferedReader(reader);
		
		
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1; i <=10; i++) {
			list.add(bufread.readLine());
		}
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String url = "https://www.careerbuilder.com/";
		driver.get(url);
		String zipcode="78717";
		ArrayList<String> newList= new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			
			driver.findElement(By.id("keywords")).clear();
			driver.findElement(By.id("keywords")).sendKeys(list.get(i));
			driver.findElement(By.id("location")).clear();
			driver.findElement(By.id("location")).sendKeys(zipcode);
			driver.findElement(By.xpath("//input[@data-gtm='search_form_find_jobs_btn_clicked']")).click();
			newList.add(list.get(i)+" "+driver.findElement(By.cssSelector("div>.count")).getText());
			driver.navigate().back();
		}
		
		for (String each : newList) {
			System.out.println(each);
		}
		
		
		Thread.sleep(5000);
		driver.close();
	}

}
