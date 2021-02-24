package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataInfo.EmployeeSearchInfo;
import element.AutoSuggestDD;
import element.DropDown;
import element.Element;
import element.ModalConfirmation;
import element.SearchBox;
import element.WebTable;


public class ViewSystemsUsersPage extends Header {
	
	//---------------------------------------------Logger------------------------------------------
	
	private static final Logger logger = LogManager.getLogger(ViewSystemsUsersPage.class.getName());
	
	//--------------------------------------------Locators-----------------------------------------

	//-----------------------------locators - related to Add, delete, edit-------------------------
	@FindBy(id = "btnAdd")
	private WebElement addBtn;

	@FindBy(id = "btnDelete")
	private WebElement deleteBtn;
	
	@FindBy(id = "resultTable")
	private WebElement resultTable;

	
	//--------------------------------------locator - modal_window---------------------------------
	@FindBy(id = "deleteConfModal")
	private WebElement modalConfirmWin;
	
	
	//------------------------------------locators - related to search-----------------------------
	@FindBy(id = "searchSystemUser_userName")
	private WebElement userNameTBox;

	@FindBy(id = "searchSystemUser_userType")
	private WebElement userRoleDD;

	@FindBy(id = "searchSystemUser_employeeName_empName")
	private WebElement employeeNameTBox;

	@FindBy(css = ".ac_results>ul>li")
	private List<WebElement> employeeIDs;

	@FindBy(id = "searchSystemUser_status")
	private WebElement employeeStatusDD;

	//----------------------------------------------Elements---------------------------------------
	private SearchBox searchBox = null;
	private Element uNameBox = null;
	private DropDown uRoleDD = null;
	private AutoSuggestDD empNameASDD = null;
	private DropDown statusDD = null;
	private WebTable table = null;
	
	//---------------------------------------------Constructor-------------------------------------
	public ViewSystemsUsersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.searchBox = new SearchBox(driver);
		this.uNameBox = new Element(driver, userNameTBox);
		this.uRoleDD = new DropDown(driver, userRoleDD);
		this.empNameASDD = new AutoSuggestDD(driver, employeeNameTBox);
		this.statusDD = new DropDown(driver, employeeStatusDD);
		this.table = new WebTable(driver, resultTable);
	}
	
	
	//---------------------------------------------Methods-----------------------------------------
	
	//----------------------------------------Search Box Methods-----------------------------------
	public ViewSystemsUsersPage inputUserName(String userName) {
		uNameBox.inputText(userName);
		return this;
	}
	
	public ViewSystemsUsersPage selectUserRole(String userRole) {
		uRoleDD.selectElementByText(userRole);
		return this;
	}
	
	
	public ViewSystemsUsersPage selectEmployeeName(String employeeNameFull, String employeeNamePart) {
		empNameASDD.typeAndSelectElement(employeeIDs, employeeNameFull, employeeNamePart);
		return this;
	} 
	
	
	public ViewSystemsUsersPage selectEmployeeStatus(String status) {
		statusDD.selectElementByText(status);
		return this;
	}
	
	
	public ViewSystemsUsersPage clickSearch() {
		searchBox.clickSearch();
		return this;
	}

	
	public ViewSystemsUsersPage clickReset() {
		searchBox.clickReset();
		return this;
	}
	
	
	public ViewSystemsUsersPage searchUser(EmployeeSearchInfo empInfo) {

		inputUserName(empInfo.getUserName());
		
		
		String userRole = empInfo.getUserRole();
		if(userRole != null && userRole.length()>0) {
			selectUserRole(userRole);
		}
		else selectUserRole("All");
	
		
		selectEmployeeName(empInfo.getEmpNameFull(), empInfo.getEmpNamePart());
		
		String status = empInfo.getEmpStatus();
		if(status != null && status.length()>0) {
			selectEmployeeStatus(status);
		}
		else selectEmployeeStatus("All");
		
		clickSearch();
		
		return this;
	}
	
	//----------------------------------------Delete Methods--------------------------------------
	public ViewSystemsUsersPage clickDelete() {
		deleteBtn.click();
		return this;
	}
	
	public ViewSystemsUsersPage clickCheckBoxByUser(String userName) {
		table.clickCheckBoxOfRowByText("Username", userName);
		return this;
	}
	
	
	public ViewSystemsUsersPage deleteUser(String userName){
		clickCheckBoxByUser(userName).clickDelete();
		return this;
	}
	
	public ViewSystemsUsersPage deleteMultipleUsers(List<String> userNames){
		for(String userName: userNames)
			clickCheckBoxByUser(userName);
		clickDelete();
		return this;
	}
	
	
	//----------------------------------------ModalConfirm Methods--------------------------------

	public ViewSystemsUsersPage clickDeleteOk() {
		new ModalConfirmation(driver, modalConfirmWin).clickOk();
		return this;
	}
	
	public ViewSystemsUsersPage clickDeleteCancel() {
		new ModalConfirmation(driver, modalConfirmWin).clickCancel();
		return this;
	}
	
	
	//----------------------------------------Edit User Methods-----------------------------------
	
	public void clickUserToEdit(String userName) {
		//TODO - return Edit system user Page
		table.clickCellByText("Username", userName);
	}
	
	//-----------------------------------------Add User Methods-----------------------------------
	
	public void clickAdd() {
		//TODO - return Add system user Page
		addBtn.click();
	}
	
	public boolean verifyUserSearchResult(EmployeeSearchInfo empInfo) {
		//TODO - more steps 
		List<WebElement> users = null;
		if(empInfo.getUserName() != null && empInfo.getUserName().length()> 0) {
			//users = filterElementsByText(userNamesColumn, userName);
		}
		
		return false;
	}

}
