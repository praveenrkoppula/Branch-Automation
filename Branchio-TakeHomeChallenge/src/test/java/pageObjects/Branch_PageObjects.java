package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Branch_PageObjects {
	WebDriver driver;
	
	public String GoogleURL="https://www.Google.com";
	public String BranchURL="https://branch.io/"; 
	public String AcceptCookies = "#CybotCookiebotDialogBodyButtonAccept";
	public String EmployeeName = "findElement(By.tagName(\"h2\")).getText()";
	
	@FindBy(name="q")
	public WebElement SearchBox;	
	
	@FindBy(css="a[href*='https://branch.io/']")
	public WebElement BranchLink_InResults;
	
	@FindBy(css="a[href='https://branch.io/team/#all']")
	public WebElement TeamLink_AtBottom;
	
	@FindBy(xpath="/html/body/div/div/section[2]/div//*[@class='info-block']")
	public List<WebElement> InfoBlock;
	
	@FindBy(xpath="//*[@id=\"logo\"]")
	public WebElement CompanyLogo;
	
	@FindBy(xpath="/html/body/div/header/nav/div[2]/header/div[1]/ul/li[3]/a[2]")
	public WebElement SolutionsMenu;
	
	@FindBy(xpath="/html/body/div/header/nav/div[2]/header/div[2]/div[3]/div[2]/div/div/ul/li[2]/a")
	public WebElement DeepLinking;
	
 	@FindBy(xpath="/html/body/div/section/div/div/p[2]/a")
	public WebElement RequestADemo_DeepLinkingPage;

	@FindBy(xpath="/html/body/div/header/nav/div[2]/header/div[1]/ul/li[2]/a[3]")
	public WebElement RequestADemo_MainMenu;
 
 	@FindBy(xpath="//*[@id=\"bs-example-navbar-collapse-1\"]/ul[2]/li[2]/a")
	public WebElement SignIn_Menu;	
 	
 	@FindBy(xpath="//*[@type='text']")
	public WebElement WorkEmail;
 	
 	@FindBy(xpath="//*[@type='password']")
	public WebElement Password;
 	
 	@FindBy(xpath="//*[@type='submit']")
	public WebElement SignIn_Button;	
	
	@FindBy(xpath="*//a[@rel]")
	public List<WebElement> Categories;
	

	
	@FindBy(xpath="//a[@rel='data']")
	public WebElement DataTab;
	
	@FindBy(xpath="//a[@rel='engineering']")
	public WebElement EngineeringTab;
	
	@FindBy(xpath="//a[@rel='marketing']")
	public WebElement MarketingTab;
	
	@FindBy(xpath="//a[@rel='operations']")
	public WebElement OperationsTab;
	
	@FindBy(xpath="//a[@rel='partner-growth']")
	public WebElement PartnerGrowthTab;
	
	@FindBy(xpath="//a[@rel='product']")
	public WebElement ProductTab;
	
	@FindBy(xpath="//a[@rel='recruiting']")
	public WebElement RecruitingTab;
	
	@FindBy(xpath="//a[@rel='all']")
	public WebElement AllTab;
	
//	public void ClickCategory(String category)
//	{
//	    driver.findElement(By.xpath("//a[@rel='"+category+"']")).click();
//	}
	
	public Branch_PageObjects(WebDriver driver1) {
		PageFactory.initElements(driver1, this);
	}

	
}
