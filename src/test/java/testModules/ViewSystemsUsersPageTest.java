package testModules;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import base.BaseTest;
import dataInfo.EmployeeSearchInfo;
import dataProviders.ViewSystemUsersTestData;
import pages.Header;
import pages.LoginPage;
import utils.PropertyReader;


public class ViewSystemsUsersPageTest extends BaseTest {
	
	@Test(dataProvider = "SearchUserTestData", dataProviderClass = ViewSystemUsersTestData.class)
	public void checkSearch(EmployeeSearchInfo empInfo) { 
		//int actualRowCount = new ViewSystemsUsersPage(driver)
		//						.searchUser(empInfo);
								
		
		//Assert.assertEquals(actualRowCount, (int)Float.parseFloat(empInfo.getExpectedRowCount()));
	}
	
	
	@BeforeClass
	public void navigateToSystemUsersPage() {
		new LoginPage(driver)
		  .load()
		  .login(username, password)
		  .goToPageByMenuNavigation(PropertyReader.getInstance("config.properties").getValue("ViewSystemsUsersPage"));
	}
	
	@AfterClass
	public void doLogout() {
		new Header(driver).logout();
	}

}
