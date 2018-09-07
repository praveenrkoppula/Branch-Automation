package baseFunctions;

import baseFunctions.Functions;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseFunctions.Functions;
import pageObjects.Branch_PageObjects;

public class BusinessLayer extends ExtentReporting{ 

  Functions base = new Functions();
  Branch_PageObjects elements;
  
  
  public void VerifyEmployeeCount_FromAllTabVsCategoryTab(ArrayList<String> categoryList, WebDriver driver)
  {
	//Count of employees in ALL tab
    int employeeCount_fromAllTab = base.EmployeesNamesAndCategory_FromAllTab(driver).size();
	logger.info("Employee count from All tab: " + employeeCount_fromAllTab);
	
    //Count of employees from categories 
    int employeeCount_fromCategoriesTabs = 0;
    for(int i=1; i < categoryList.size() ; i++)
    {
      employeeCount_fromCategoriesTabs += base.EmployeesNames_UnderACategory(categoryList.get(i), driver).size();
    }
	logger.info("Employee count from Category tabs: " + employeeCount_fromCategoriesTabs);
	
   	Assert.assertEquals(employeeCount_fromCategoriesTabs,employeeCount_fromAllTab, " Employees count from All tab and Rest of the Tabs doesnt match");
  }

  public void ComparingEmployeesName_FromAllTabVsCategoryTab(ArrayList<String> categoryList, Map<String,String> map, WebDriver driver)
  {
    Map<String, String> empNamesAndCategory_FromCategoryTabs = new LinkedHashMap<String,String>();

    //Concatenating individual category maps into single map
    for(int i=1; i < categoryList.size() ; i++)
    {
      empNamesAndCategory_FromCategoryTabs.putAll(base.EmployeesNamesAndCategory_UnderACategory(categoryList.get(i), driver));
    }

    int missingEmployeesCount = 0;
    Set<Entry<String,String>> hashSet=map.entrySet();
    
	  logger.info("Below is the list of Employees missing under categories");
	  //iterating for all employees in both maps (All Vs Category)
	  for(Entry entry:hashSet)
	  {
	    String Category_fromAll = entry.getValue().toString().toLowerCase();
	    String Category_fromtab = empNamesAndCategory_FromCategoryTabs.get(entry.getKey().toString());
	
	    if(Category_fromAll.equals("partner growth"))
	      Category_fromAll="partner-growth";
	
	    if(Category_fromtab==null) {
	      logger.info(entry.getKey().toString() + " - " + Category_fromAll);
	      missingEmployeesCount++;
	    }
	  }
	  logger.info("Count of Employees missing : "+ missingEmployeesCount);
	  
	  Assert.assertEquals(missingEmployeesCount,0, " Employees details from All tab and Rest of the Tabs doesnt match");      

  }

  public void ComparingCategoryNameOfemployees(ArrayList<String> categoryList, Map<String,String> map, WebDriver driver)
  {

    Map<String, String> empNamesAndCategory_FromCategoryTabs = new LinkedHashMap<String,String>();

    //Consolidating individual category maps into one map
    for(int i=1; i < categoryList.size() ; i++)
    {
      empNamesAndCategory_FromCategoryTabs.putAll(base.EmployeesNamesAndCategory_UnderACategory(categoryList.get(i), driver));
    }

    logger.info("Below is the list of employees having different categories in All tab and Categories tabs");
    Set<Entry<String,String>> hashSet=map.entrySet();
    try {     
      for(Entry entry:hashSet)
      {
        String Category_fromAll = entry.getValue().toString().toLowerCase();
        String Category_fromtab = empNamesAndCategory_FromCategoryTabs.get(entry.getKey().toString());

        if(Category_fromAll.equals("partner growth"))
          Category_fromAll="partner-growth";

        //if(!Category_fromAll.contains(Category_fromtab))
        if(Category_fromtab!=null) {
          if (!Category_fromAll.contains(Category_fromtab)) {
        	  
        	    logger.info("Employee name: " + entry.getKey().toString());
        	    logger.info("Category displayed in All tab: " + Category_fromAll);
        	    logger.info("Category displayed in Category tab: " + Category_fromtab);
          }
        }
      }
    }catch(Exception e)
    {
      logger.info("\n" + "Unexpected error occurred" + "\n");
    }

  }

  
  public void VerifyRequestDemoURL_FromDifferentScreens(WebDriver driver)
	{
	  
	  	elements = new Branch_PageObjects(driver);
		elements.CompanyLogo.click();
		
		WebElement mainMenu = elements.SolutionsMenu;
		Actions actions = new Actions(driver);
		actions.moveToElement(mainMenu).perform();
		elements.DeepLinking.click();		
		logger.info("Mouse hovered on Solutions menu and Deep linking option clicked ");
		 
		elements.RequestADemo_DeepLinkingPage.click();
		String URL_FromDeepLinkPage=driver.getCurrentUrl();
		logger.info("Request a demo button clicked from Deep linking page and URL captured");

		driver.navigate().back();
		elements.RequestADemo_MainMenu.click();
		//Navigating back to home screen. On clicking logo new window opens so need 2 taps on back button
		String URL_FromMainMenuPage=driver.getCurrentUrl();
		logger.info("Navigated back and Sign in button clicked from home page and URL captured");
		
		Assert.assertEquals(URL_FromDeepLinkPage,URL_FromMainMenuPage, "Both URLs are DIFFERENT");
		logger.info("Both Sign In page URLs are same");

	}
  
	public void VerifyNavigatingToHomePageByClickingCompanyLogo(WebDriver driver) throws InterruptedException
	{
	  	elements = new Branch_PageObjects(driver);
	  	try {
				elements.SignIn_Menu.click();
				logger.info("Sign in button clicked in Request demo screen");
				Thread.sleep(2000);
				elements.CompanyLogo.click();
				logger.info("Navigated back to home page from Sign screen by clicking compny logo");
	}catch(NoSuchElementException e)
	  	{
			//logger.info(e);
			logger.log(Status.FAIL,"Company logo missing in Sign in page, which is present in all other screens-Inconsitency");

	  	}
	}
  
}
