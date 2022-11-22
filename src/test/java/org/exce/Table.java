package org.exce;

public static void main(String[] args) throws Throwable {
	
	WebDriver driver;
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://nxtgenaiacademy.com/webtable/");
		
	File f = new File("C:\\Users\\Windows\\eclipse-vivek\\FlipkartExcel\\target\\TableBook.xlsx");
	FileOutputStream d= new FileOutputStream(f);
	Workbook w = new XSSFWorkbook();
    Sheet s = w.createSheet("selftask");
	
	List<WebElement> iTable = driver.findElements(By.tagName("table"));
	
	for(int i=0; i<iTable.size(); i++) {
	WebElement t = iTable.get(i);
	System.out.println(i);
	
	WebElement iBody = t.findElement(By.tagName("tbody"));
	List<WebElement> iRow = iBody.findElements(By.tagName("tr"));
	 
	for(int j=0; j<iRow.size();j++) {
		WebElement r = iRow.get(j);
		System.out.println("row "+ j);
		List<WebElement> tdata = r.findElements(By.tagName("td"));
//		System.out.println(tdata.size());
		for(int k=0; k<tdata.size();k++) {
			WebElement data = tdata.get(k);
			String text = data.getText();
			System.out.println("column "+k);
			System.out.println(text);	
			if (k==0) {
			Row row = s.createRow(j);
			Cell ce = row.createCell(k);
			ce.setCellValue(text);	
//			w.write(d);	
			}
			if (k==1) {
				Row row = s.createRow(j);
				Cell ce = row.createCell(k);
				ce.setCellValue(text);	
//			w.write(d);	
			}
		}	
		
}
		w.write(d);	
		
	}
	}

   