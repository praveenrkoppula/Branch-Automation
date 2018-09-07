package branch;

import java.util.ArrayList;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseFunctions.BusinessLayer;
import baseFunctions.ExtentReporting;
import baseFunctions.Functions;
import pageObjects.Branch_PageObjects;


public class Branch extends ExtentReporting{
	public WebDriver driver;
    Map<String,String> All_Employees_NameAndCategory; //To store all employee's names & their category
    ArrayList<String> categoryList = new ArrayList<String>();
	int categoriesCount=0;	
	Branch_PageObjects elements;
	Functions base = new Functions();
	BusinessLayer branch = new BusinessLayer();
	
    @BeforeTest
    public void init()
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver=new ChromeDriver();
        elements = new Branch_PageObjects(driver);
        
    }

    @Test 
    public void OpenBranchSiteAndVerifyURL()
    {
        logger=extent.createTest("SearchBranch", "Launch Browser and open Google search and search for branch and open the link");
        driver.get(elements.GoogleURL);
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver,50);
        WebElement search= wait.until(ExpectedConditions.elementToBeClickable((elements.SearchBox)));
        
        search.sendKeys("Branch");
        search.sendKeys(Keys.ENTER);
        logger.info("Branch search success");
        elements.BranchLink_InResults.click();
        logger.info("Branch link is cliked");
	    base.WaitFor(2000);
        
        
        if(driver.getCurrentUrl().equals(elements.BranchURL))
        {
        	logger.info("Branch site is opened and the URL is correct");
            WebElement selectcookie=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elements.AcceptCookies)));
            selectcookie.click();
    	    base.WaitFor(2000);
        }
        else
        {
            logger.info("Branch site is opened and but the URL is incorrect");
            driver.quit();
        }
    }

	@Test
	public void ClickTeamLinkAtBottom()
	{
		logger=extent.createTest("Click Team link", "Scroll the screen to the bottom and clicks the Team link");
	    WebElement TeamLink = elements.TeamLink_AtBottom;
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", TeamLink);
	    TeamLink.click();
	    base.WaitFor(2000);
        logger.info("Team link is clicked");
        
	}
	
	@Test (enabled=true)
	public void GetCategoryAndEmployeesList() throws InterruptedException
	{
		logger=extent.createTest("GetCategoryAndEmployeesList", "Get Category List And Employees List");
		
		 //Creating Category list		
		 categoryList = base.CategoryList(driver);	
		 //Creating employees names&category map from All tab
		 All_Employees_NameAndCategory = base.EmployeesNamesAndCategory_FromAllTab(driver);

		 logger.info("Categories count: " + categoryList.size());
	}
	
	@Test
	public void VerifyEmployeeCount_FromCategoryTabsVsAllTab()
	{
		logger=extent.createTest("Compare employee count", "Verify employee count in All tab and from Categories");
		branch.VerifyEmployeeCount_FromAllTabVsCategoryTab(categoryList, driver);	
	}

	@Test 
	public void EmployeesMissingUnderCategories()
	{
	    logger=extent.createTest("Compare Employees names", "Comparing EmployeesName from All tab and Category tabs");
		branch.ComparingEmployeesName_FromAllTabVsCategoryTab(categoryList, All_Employees_NameAndCategory, driver);
	}
	
	@Test
	public void ComparingCategoryNamesOfemployees()
	{
		logger=extent.createTest("Compare employee's category Name", "Compare Category Name Of employees from All tab and Category tabs");
		branch.ComparingCategoryNameOfemployees(categoryList, All_Employees_NameAndCategory, driver);
	}

	@Test 
	public void ClickAndVerifyLinkOfCategoryTabs() {
		
		logger=extent.createTest("Verify links of category tabs", "Click a tab and verify th URL of that category");
	
    	base.ClickAndVerifyCategoriesLink(categoryList,driver);
	}

	@Test 
	public void VerifyRequestDemoURL_FromDifferentScreens()
	{
		logger=extent.createTest("Verify Request Demo URL", "Verify Request Demo URL is same irrespective of the screen invoked from different screens");
		branch.VerifyRequestDemoURL_FromDifferentScreens(driver);
	}

	@Test 
	public void NavigatingToHomePageByClickingCompanyLogo() throws InterruptedException
	{
		logger=extent.createTest("FAILURE CASE: Verify Navigation To Home screen", "VerifyNavigatingToHomePageByClickingCompanyLogo");
		branch.VerifyNavigatingToHomePageByClickingCompanyLogo(driver);
	}

	@Test 
	public void Print_CoFoundersNames()
	{
		logger=extent.createTest("Printing co-founders names", "Printing co-founders names");
		base.Print_CoFoundersNames(All_Employees_NameAndCategory);
	}
	
	@AfterTest 
	public void CloseBrowser()
	{
		driver.quit();
	}

}
