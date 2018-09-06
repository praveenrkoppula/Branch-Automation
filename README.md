# Branch-Automation
===========================

Prerequisites:
---------------
Chrome browser is installed on machine

Environment:
-------------
Mac server
Languages and tools used:
Java, using Maven, Selenium WebDriver, TestNG  and Avant extent reporting

Test scenarios:
----------------
- Navigate to google.com and search for branch website 
- Navigate to branch website > Scroll down to footnotes and click on "Team" link 
- Team page has tabs: All tab(that lists all employees) and department tabs(lists employees by their departments) 
- Verify that number of employees match between All tab and sum of other tabs. 
- Verify that employee names match between All tab and other tabs. 
- Verify that employee departments are listed correctly between All tab and Department tabs. 
- [Added] Click the category tabs and verify the URLs 
- [Added] Print the names of Co-founders
- [Added] Verify the URL of “Request a demo” is same irrespective of the entry point
- [Failure case] Verify navigating to Home page from Sign in page by clicking company logo

Report:
------------------
Name : 
Location: Directly under the root directory (Along with POM.xml)
