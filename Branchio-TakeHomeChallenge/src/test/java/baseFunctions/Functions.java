package baseFunctions;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageObjects.Branch_PageObjects;

public class Functions extends ExtentReporting{
	Branch_PageObjects elements;
	
	//Creates List of categories
	public ArrayList<String> CategoryList(WebDriver driver)
	{
        elements = new Branch_PageObjects(driver);
		ArrayList<String> categoryList = new ArrayList<String>();
	    List<WebElement> categories = elements.Categories;
	    //Creating category list
	    for (WebElement element:categories) {
	    	categoryList.add(element.getAttribute("rel"));
	    }
	    
//		for(int i=1; i < categoryList.size() ; i++) {
//			driver.findElement(By.xpath("//a[@rel='" + categoryList.get(i) + "']")).click();
//			Thread.sleep(1000);
//		}
//		driver.findElement(By.xpath("//a[@rel='" + categoryList.get(0) + "']")).click();
	    
		return categoryList;
	}
	
	//Creates Map employee's name and their category 
	public Map<String,String> EmployeesNamesAndCategory_FromAllTab(WebDriver driver)
	{
        elements = new Branch_PageObjects(driver);
        
	    Map<String,String> EmployeesNamesAndCategory_FromAll = new LinkedHashMap<String, String>();
	    List<WebElement> AllTeam = elements.InfoBlock;
	    
	    for (WebElement element:AllTeam)
	    {
	  
	    	EmployeesNamesAndCategory_FromAll.put(element.findElement(By.tagName("h2")).getText(),element.findElement(By.tagName("h4")).getText());
	    }	    
	    
	    return EmployeesNamesAndCategory_FromAll;	    
	}
	
	//Creates List of employees based on Category
	public ArrayList<String> EmployeesNames_UnderACategory(String category, WebDriver driver)
	{
		String str = "category-"+category;

		List<WebElement> MembersList=driver.findElements(By.className(str));  		 
		ArrayList<String> MembersList_Array = new ArrayList<String>();
		 for (WebElement element:MembersList)
		 {
			 MembersList_Array.add(element.findElement(By.tagName("h2")).getText());
  		 }		 
		 return MembersList_Array;
	}	
	
	//Adds the category for the employee list and creates a map of specific category
	public Map<String,String> EmployeesNamesAndCategory_UnderACategory(String category, WebDriver driver)
	{
		List<String> MembersList=EmployeesNames_UnderACategory(category, driver);  		 
		//Creating map - names as KEY and category as VALUE  
		Map<String,String> Category_map = new LinkedHashMap<String,String>();
		 for (int i=0; i< MembersList.size(); i++)
		 {
			 Category_map.put(MembersList.get(i), category);
  		 }		 
		 
		 return Category_map;
	}
	
	//Creates list of all employee's names from <employeename, category> map 
	public ArrayList<String> EmployeesNames_From_AllTab(WebDriver driver, Map<String,String> map)
	{		
        ArrayList<String> EmployeesNamesList_FromAllTab = new ArrayList<String>();				 
	    Set<Entry<String,String>> hashSet=map.entrySet();
	    for(Entry entry:hashSet ) {
	    	EmployeesNamesList_FromAllTab.add(entry.getKey().toString());
        }		 
	    
	    return EmployeesNamesList_FromAllTab;
	}

	//To Click category tabs and verifying the URL 
	public void ClickAndVerifyCategoriesLink(ArrayList<String> categoryList, WebDriver driver)
	{
		((JavascriptExecutor) driver).executeScript("scroll(0,650)");
		for(int i=1; i < categoryList.size() ; i++) {
			driver.findElement(By.xpath("//a[@rel='" + categoryList.get(i) + "']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String expectedURL = "https://branch.io/team/#" + categoryList.get(i);
			logger.info(categoryList.get(i)+" category expected URL :" +expectedURL);
			logger.info(categoryList.get(i)+" category actual URL displayed:" +driver.getCurrentUrl());
			
			Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "This is not expected URL");			
		}
	}
	
	//Printing of elements of an array 
	public void PrintAList(ArrayList<String> List)
	{
        for (int i=0; i<List.size(); i++)
            System.out.print(i + " - " + List.get(i)+"\n");                
	}

	//Printing of elements of a map 
	public void PrintAMap(Map<String,String> map)
	{
		Set<Entry<String,String>> hashSet=map.entrySet();
		for(Entry entry:hashSet )
			System.out.println(entry.getKey().toString()+" : "+entry.getValue().toString());
	}
	
	//Printing of elements of a map 
	public void Print_CoFoundersNames(Map<String,String> map)
	{		
		Set<Entry<String,String>> hashSet=map.entrySet();
		logger.info("Below is the Co-founders list");
		for(Entry entry:hashSet )
		{
			if(entry.getValue().toString().toLowerCase().contains("co-founder"))
			{
				logger.info("Co-founder Name : "+entry.getKey().toString());
			}
		}
	}
	
	public void WaitFor(int i)
	{
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
