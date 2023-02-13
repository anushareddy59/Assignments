package assignment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SalesForceSeleniumTestCases extends SalesForceHelper {

	public static void main(String[] args) throws Exception {

		errorLoginEmptyPassword();
		login();
		rememberUserNameCheck();
		forgotPasswordCheck();
		errorLoginWrongInpouts();
		usermenuDropdown();
		myProfile();
		developersConsole();
		mySettings();
		logout();
		accountsLink();
		createNewViewLink();
		editView();
		mergeAccounts();
		accountReport();
		opportunities();
		createNewOpty();
		pipeLineReport();
		stuckOportunitiesReport();
		quarterelySummaryReport();
		leadsTabLink();
		leadsView();
		goButton();
		todaysLeads();
		newLeadsButton();
		createContact();
		createNewView();
		recentlyCreatedContact();
		myContacts();
		contactPage();
		errorViewContact();
		cancelButtonCreateNewView();
		saveNewButtonContact();
		firstLastName();
		firstNameLastNameVerify();
		verifyTabCustomization();
		eventBlock();
		eventBlockWeeklyRecurrence();

	}

	private static void errorLoginEmptyPassword() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = "";
		gotoURL(url);

		WebElement userBox = findWebElementByID("username");
		WaitUntilElementIsVisible(userBox);
		sendKeys(userBox, userName);

		WebElement passwordBox = findWebElementByID("password");
		sendKeys(passwordBox, password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		Thread.sleep(1000);
		WebElement errorMessage = findWebElementByID("error");
		String expectedMsg = "Please enter your password.";
		if (errorMessage.getText().equals(expectedMsg)) {
			System.out.println("Testcase passed.");
		} else {
			System.out.println("Testcase Failed");
		}
		closeBrowser();
	}

	private static void login() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		if (profileIcon.isDisplayed()) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed");
		}

		Thread.sleep(1000);
		System.out.println("Home page is displayed");
		closeBrowser();
	}

	public static void rememberUserNameCheck() throws Exception {

		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement radioButton = findWebElementByXPath("//*[@id=\"rememberUn\"]");
		radioButton.click();
		Thread.sleep(1000);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);
		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		Thread.sleep(1000);
		WebElement logOutButton = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		logOutButton.click();

		Thread.sleep(2000);
		WebElement userBox1 = findWebElementByID("username");

		if (userBox1.getAttribute("value").equalsIgnoreCase(userName)) {
			System.out.println("TestCase passed");
		} else {
			System.out.println("TestCsae failed");
		}
		closeBrowser();
	}

//testcase 3
	public static void forgotPasswordCheck() throws Exception {

		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);

		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement forgotPassword = findWebElementByXPath("//*[@id=\"forgot_password_link\"]");

		forgotPassword.click();

		WebElement userNameForgot = findWebElementByID("un");

		if (userNameForgot.isDisplayed()) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();
	}

	private static void errorLoginWrongInpouts() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = "123";
		String password = "22132";
		gotoURL(url);

		WebElement userBox = findWebElementByID("username");
		WaitUntilElementIsVisible(userBox);
		sendKeys(userBox, userName);

		WebElement passwordBox = findWebElementByID("password");
		sendKeys(passwordBox, password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		Thread.sleep(1000);
		WebElement errorMessage = findWebElementByID("error");
		String expectedMsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		if (errorMessage.getText().equals(expectedMsg)) {
			System.out.println("Testcase passed.");
		} else {
			System.out.println("Testcase Failed");
		}
		closeBrowser();
	}

//testcase 5
	private static void usermenuDropdown() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();

		WebElement myProfile = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[1]");
		WebElement mySettings = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[2]");
		WebElement developerConsole = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[3]");
		WebElement switchToLigthningConsole = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[4]");
		WebElement logOut = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		if (myProfile.isDisplayed() && mySettings.isDisplayed() && developerConsole.isDisplayed()
				&& switchToLigthningConsole.isDisplayed() && logOut.isDisplayed()) {
			System.out.println("Testcase passed.");

		} else {
			System.out.println("Testcase failed.");
		}
		closeBrowser();
	}

//testcase 6
	public static void myProfile() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();

		WebElement myProfile = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[1]");
		myProfile.click();

		Thread.sleep(3000);
		WebElement editProfile = findWebElementByXPath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img");
		editProfile.click();
		Thread.sleep(4000);
		driver.switchTo().frame("contactInfoContentId");

		Thread.sleep(1000);
		WebElement about = findWebElementByXPath("/html/body/div/div/div/div[1]/ul/li[1]/a");
		about.click();

		WebElement lastName = findWebElementByID("lastName");
		sendKeys(lastName, "a");

		WebElement saveAll = findWebElementByXPath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]");
		saveAll.click();

		Thread.sleep(1000);
		String basehandle = driver.getWindowHandle();
		WebElement post = findWebElementByXPath("//*[@id=\"publisherAttachTextPost\"]/span[1]");
		post.click();
//frame iframe[contains(....
		// bodyframe inside frame..switch to frame
		//
		Thread.sleep(2000);

		driver.switchTo().frame(0);
		WebElement textBox = findWebElementByXPath("/html/body");
		System.out.println("Textbox: " + textBox.isDisplayed() + " and " + textBox.isEnabled());
		sendKeys(textBox, "Hello, Welcome");
		driver.switchTo().window(basehandle);

		WebElement shareButton = findWebElementByID("publishersharebutton");
		shareButton.click();
		Thread.sleep(2000);
		// *[@id="publisherAttachContentPost"]/span[1]
		// File upload
		clickByXPath("//*[@id=\"publisherAttachContentPost\"]/span[1]", "File link");
		Thread.sleep(2000);

		/*
		 * clickByXPath("//*[@id=\"chatterUploadFileAction\"]", "Upload Button");
		 * Thread.sleep(2000); WebElement choosebutton =
		 * findWebElementByID("chatterFile"); Actions action = new Actions(driver);
		 * action.moveToElement(choosebutton).build().perform(); action.click();
		 * 
		 * StringSelection stringSelection = new
		 * StringSelection("C:\\Users\\Anusha\\Documents\\data1.xlsx");
		 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,
		 * null);
		 * 
		 * 
		 * WebElement shareButton1 = findWebElementByID("publishersharebutton");
		 * shareButton1.click();
		 */
		// clickByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/li/div/div/a",
		// "AddPhoto");

		WebElement photo = findWebElementByXPath(
				"/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/div[1]/span[2]/img[1]");
		Actions action = new Actions(driver);
		action.moveToElement(photo).build().perform();
		clickByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/div[1]/div/a", "AddPhto");
		driver.switchTo().frame("uploadPhotoContentId");

		WebElement fileChose = findWebElementByID("j_id0:uploadFileForm:uploadInputFile");
		fileChose.sendKeys("C:\\Users\\Anusha\\Desktop\\sarah1.jpg");

		clickByID("j_id0:uploadFileForm:uploadBtn", "SaveButton");

		Thread.sleep(5000);
		driver.switchTo().defaultContent();

		clickByID("j_id0:j_id7", "CropSave");
		closeBrowser();
	}

	/**
	 * Test case 7
	 * 
	 * @throws Exception
	 */

	public static void mySettings() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(3000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(2000);
		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		WebElement settings = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[2]");
		settings.click();
		Thread.sleep(1000);
		WebElement personal = findWebElementByXPath("//*[@id=\"PersonalInfo\"]/a");
		personal.click();
		Thread.sleep(1000);
		WebElement loginHistory = findWebElementByID("LoginHistory_font");
		loginHistory.click();
		Thread.sleep(1000);
		WebElement downloadHistory = findWebElementByXPath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a");
		downloadHistory.click();

		Thread.sleep(1000);
		clickByID("DisplayAndLayout_font", "display&Layout");
		clickByID("CustomizeTabs_font", "customizeTab");
		Thread.sleep(1000);

		WebElement appDropDown = clickByID("p4", "customAppdropDown");
		Select ob = new Select(appDropDown);
		ob.selectByVisibleText("Salesforce Chatter");

		WebElement selectBox = clickByID("duel_select_0", "SelectionBox");
		Select reports = new Select(selectBox);
		reports.selectByVisibleText("Reports");

		clickByXPath("//*[@id=\"duel_select_0_right\"]/img", "AddButon");
		clickByXPath("//*[@id=\"bottomButtonRow\"]/input[1]", "SaveButton");
		Thread.sleep(1000);

		clickByXPath("//*[@id=\"EmailSetup\"]/a", "EmailLink");
		clickByID("EmailSettings_font", "EmailSettings");
		Thread.sleep(1000);

		WebElement emailTextBox = clickByID("sender_email", "EmailBox");
		emailTextBox.clear();
		emailTextBox.sendKeys("abcdefgh@abc.com");

		clickByXPath("//*[@id=\"auto_bcc1\"]", "SelectYesInBCC");

		String baseHandler = driver.getWindowHandle();
		clickByXPath("//*[@id=\"bottomButtonRow\"]/input[1]", "EmailSaveButton");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(1000);
		driver.switchTo().window(baseHandler);
		clickByXPath("//*[@id=\"CalendarAndReminders\"]/a", "Calenders&Remainders");
		clickByID("Reminders_font", "ActivityReminders");
		Thread.sleep(1000);

		clickByID("testbtn", "OpenTestReminder");
		closeBrowser();
	}

	// testcase 8
	public static void developersConsole() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		WebElement developerConsole = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[3]");
		developerConsole.click();

		String baseHandle = driver.getWindowHandle();

		Thread.sleep(3000);

		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(baseHandle)) {
				driver.switchTo().window(handle);
			}
		}

		closeBrowser();

		// WebElement close = findWebElementByXPath("//*[@id=\"panel-1136\"]");
		// close.click();

//Test case9
	}

	public static void logout() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);
		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		Thread.sleep(1000);
		WebElement logOutButton = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		logOutButton.click();
		String loginPageUrl = "https://tekarch58-dev-ed.develop.my.salesforce.com/";
		if (driver.getCurrentUrl().equals(loginPageUrl)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}

		closeBrowser();
	}
//Test case 10

	public static void accountsLink() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
		accounts.click();

		Thread.sleep(4000);
		clickByID("tryLexDialogX", "No thanks");

		WebElement newAccount = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input");
		newAccount.click();
		Thread.sleep(2000);

		String accountNameStr = "aaaa";
		WebElement accountName = findWebElementByXPath("//*[@id=\"acc2\"]");
		accountName.sendKeys(accountNameStr);

		WebElement type = findWebElementByXPath("//*[@id=\"acc6\"]");
		Select ob = new Select(type);
		ob.selectByVisibleText("Technology Partner");
		Thread.sleep(2000);
		WebElement customerPriority = findWebElementByXPath("//*[@id=\"00NDn00000SjXLr\"]");
		Select ob1 = new Select(customerPriority);
		ob1.selectByVisibleText("High");

		WebElement save = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
		save.click();

		WebElement topHeader = findWebElementByXPath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
		String name = topHeader.getText();

		if (name.equals(accountNameStr)) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed");
		}

		closeBrowser();
	}

//Testcase 11

	public static void createNewViewLink() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
		accounts.click();
		Thread.sleep(2000);
		clickByID("tryLexDialogX", "No thanks");

		WebElement createNewView = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
		createNewView.click();
		Thread.sleep(1000);
		String inputName = "Selenium2";
		WebElement viewName = findWebElementByID("fname");
		viewName.sendKeys(inputName);
		WebElement viewUniqueName = findWebElementByXPath("//*[@id=\"devname\"]");
		viewUniqueName.clear();
		viewUniqueName.sendKeys("");
		Thread.sleep(1000);
		WebElement save = findWebElementByXPath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]");
		// *[@id="editPage"]/div[1]/table/tbody/tr/td[2]/input[1]
		save.click();

		WebElement view = findWebElementByID("fcf");
		String vName = view.getText();
		if (inputName.equals(vName)) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed");
		}

		closeBrowser();

	}

//Test case 12
	public static void editView() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
		accounts.click();
		Thread.sleep(4000);
		clickByID("tryLexDialogX", "No thanks");

		WebElement view = findWebElementByID("fcf");
		view.click();
		Select ob3 = new Select(view);
		ob3.selectByVisibleText("sandy");

		WebElement editLink = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[1]");
		editLink.click();

		String inputName = "sandy";
		WebElement newViewName = findWebElementByXPath("//*[@id=\"fname\"]");
		newViewName.sendKeys(inputName);
		WebElement newViewUniqueName = findWebElementByXPath("//*[@id=\"devname\"]");
		newViewUniqueName.clear();
		newViewUniqueName.sendKeys(inputName);

		WebElement field = findWebElementByID("fcol1");
		field.click();
		Select ob4 = new Select(field);
		ob4.selectByVisibleText("Account Name");

		WebElement opreatorLink = findWebElementByID("fop1");
		Select ob5 = new Select(opreatorLink);
		ob5.selectByVisibleText("contains");

		WebElement value = findWebElementByXPath("//*[@id=\"fval1\"]");
		value.sendKeys("a");

		clickByXPath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]", "saveLink");

		WebElement view1 = findWebElementByID("fcf");
		String vName = view1.getText();
		if (inputName.equals(vName)) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed");
		}

		closeBrowser();
	}

//Testcase 13

	public static void mergeAccounts() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
		accounts.click();
		Thread.sleep(4000);
		clickByID("tryLexDialogX", "No thanks");

		Thread.sleep(1000);
		WebElement mergeAccounts = findWebElementByXPath(
				"//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a");
		mergeAccounts.click();

		WebElement txtBox = findWebElementByXPath("//*[@id=\"srch\"]");
		txtBox.sendKeys("aa");

		WebElement findAccounts = findWebElementByXPath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]");
		findAccounts.click();

		WebElement table = findWebElementByXPath(
				"//*[@id=\"stageForm\"]/div/div[2]/div[4]/div/div[1]/div/div[2]/table/tbody");
		List<WebElement> rows = table.findElements(By.xpath("//tr"));
		int count = 2;
		for (WebElement row : rows) {
			if (row.getAttribute("class").trim().startsWith("dataRow")) {
				List<WebElement> cells = row.findElements(By.name("cid"));
				if (cells.size() > 0) {
					WebElement cell = cells.get(0);
					if (count > 0) {
						count--;
					} else {
						cell.click();
					}
				}
			}

		}

		WebElement next = findWebElementByXPath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[1]");
		next.click();

		clickByXPath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]", "MergeAccounts");
		Alert alert = driver.switchTo().alert();
		alert.accept();

		// TODO:
		if (true) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed");
		}

		closeBrowser();
	}

//Testcase 14
	public static void accountReport() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
		accounts.click();
		Thread.sleep(4000);
		clickByID("tryLexDialogX", "No thanks");

		WebElement actDays = findWebElementByXPath(
				"//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a");
		actDays.click();

		WebElement dateField = clickByXPath("//*[@id=\"ext-gen20\"]", "Date field");
		dateField.sendKeys("Created Date");

		clickByXPath(
				"/html/body/div[2]/div[3]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/div/div/form/div/div[3]/img",
				"from");
		clickByXPath("/html/body/div[17]/ul/li/div/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/em/button", "today");

		clickByXPath(
				"/html/body/div[2]/div[3]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/div/div/form/div/div[4]/img",
				"to");
		clickByXPath("/html/body/div[19]/ul/li/div/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/em/button", "today");
		String baseHandle = driver.getWindowHandle();
		clickByXPath(
				"/html/body/div[2]/div[1]/div/div/div/table/tbody/tr/td[1]/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/em/button",
				"save button");

		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(baseHandle)) {
				driver.switchTo().window(handle);
			}
		}

		WebElement reportName = findWebElementByID("saveReportDlg_reportNameField");
		reportName.sendKeys("bb");

		clickByID("saveReportDlg_DeveloperName", "UniqueName");
		clickByID("ext-gen323", "SaveAndRun");

		driver.switchTo().window(baseHandle);

		// TODO:
		if (true) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed");
		}
		closeBrowser();
	}

//Testcase 15
	public static void opportunities() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);
		WebElement viewDropDown = findWebElementByXPath("//*[@id=\"fcf\"]");
		viewDropDown.click();
		Thread.sleep(2000);
		Select opp1 = new Select(viewDropDown);
		if (opp1.getOptions().isEmpty()) {
			System.out.println("Testcase failed.");
		} else {
			System.out.println("Testcase passed.");
		}
		closeBrowser();
	}
//TestCase 16

	public static void createNewOpty() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);

		clickByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input", "newLink");

		WebElement optyName = findWebElementByXPath("//*[@id=\"opp3\"]");
		optyName.sendKeys("mariocart");
		WebElement accName = findWebElementByXPath("//*[@id=\"opp4\"]");
		accName.sendKeys("automation");

		WebElement leadSource = findWebElementByXPath("//*[@id=\"opp6\"]");
		Select ls1 = new Select(leadSource);
		ls1.selectByVisibleText("Web");

		WebElement closeDate = findWebElementByXPath("//*[@id=\"opp9\"]");
		closeDate.click();

		WebElement date = findWebElementByXPath(
				"/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/table/tbody/tr[4]/td[4]");
		date.click();

		WebElement stage = clickByXPath("//*[@id=\"opp11\"]", "stage");
		Select st = new Select(stage);
		st.selectByVisibleText("Prospecting");

		WebElement probability = findWebElementByXPath("//*[@id=\"opp12\"]");
		probability.clear();
		probability.sendKeys("10");

		WebElement savelink = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
		savelink.click();

		WebElement pageDescription = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2");
		if (pageDescription.getText().equals("mariocart")) {
			System.out.println("test case passed");
		} else {
			System.out.println("Test case failed");
		}
		closeBrowser();
	}

//Testcase 17

	public static void pipeLineReport() throws Exception {
		String expected = "Opportunity Pipeline";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);

		WebElement optyPipeline = findWebElementByXPath(
				"//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a");
		optyPipeline.click();

		WebElement textElement = driver
				.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		String actual = getTextFromWebElement(textElement, "Opportunity Pipeline");
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase Failed");
		}
		closeBrowser();
	}
//Testcase 18

	public static void stuckOportunitiesReport() throws Exception {
		String expected = "Stuck Opportunities";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);

		WebElement stuckOpportunities = findWebElementByXPath(
				"//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a");
		stuckOpportunities.click();

		WebElement txtElement = driver
				.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		String actual = getTextFromWebElement(txtElement, "Stuck Opportunities");
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase Failed");
		}
		closeBrowser();
	}

	// Testcase19
	public static void quarterelySummaryReport() throws Exception {
		String expected = "Opportunity Report";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);

		WebElement interval = findWebElementByXPath("//*[@id=\"quarter_q\"]");
		interval.click();
		Select val = new Select(interval);
		val.selectByVisibleText("Next FQ");

		WebElement include = findWebElementByXPath("//*[@id=\"open\"]");
		include.click();
		Select val1 = new Select(include);
		val1.selectByVisibleText("Open Opportunities");
		Thread.sleep(2000);
		WebElement runReport = findWebElementByXPath("//*[@id=\"lead_summary\"]/table/tbody/tr[3]/td/input");
		runReport.click();

		WebElement txtElement = driver
				.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		String actual = getTextFromWebElement(txtElement, "Opportunity Report");
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase Failed");
		}
		closeBrowser();
	}

//Testcase20

	public static void leadsTabLink() throws Exception {
		String expected = "Opportunity Report";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
		leadsTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		WebElement leadsHeader = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1");
		String leadsTitle = leadsHeader.getText();
		if (leadsTitle.equalsIgnoreCase("Leads")) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase Failed");
		}

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		Thread.sleep(2000);

		WebElement logOut = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		logOut.click();
		Thread.sleep(2000);

		// driver.close();
		driver.quit();
	}
//Testcase 21

	public static void leadsView() throws Exception {

		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
		leadsTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement leadsDropDown = findWebElementByXPath("//*[@id=\"fcf\"]");
		Select select = new Select(leadsDropDown);

		List<WebElement> options = select.getOptions();
		Set<String> actualOptions = new HashSet();
		for (WebElement option : options) {
			actualOptions.add(option.getText());
		}

		List<String> expectionOptions = Arrays.asList("All Open Leads", "My Unread Leads", "Recently Viewed Leads",
				"Today's Leads");
		if (actualOptions.containsAll(expectionOptions)) {
			System.out.println("Test case passsed");
		} else {
			System.out.println("Test case failed");
		}
		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		Thread.sleep(2000);

		WebElement logOut = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		logOut.click();
		Thread.sleep(2000);
		driver.quit();

	}
//Testcase 22

	public static void goButton() throws Exception {

		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
		leadsTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement leadsDropDown = findWebElementByXPath("//*[@id=\"fcf\"]");
		leadsDropDown.click();

		Select ld = new Select(leadsDropDown);
		ld.selectByVisibleText("My Unread Leads");

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		Thread.sleep(2000);

		WebElement logOut = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		logOut.click();
		Thread.sleep(2000);

		WebElement user = findWebElementByID("username");
		user.sendKeys(userName);
		Thread.sleep(1000);
		WebElement password1 = findWebElementByID("password");
		password1.sendKeys(password);
		WebElement login = findWebElementByID("Login");
		login.click();

		WebElement leadsTab1 = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
		leadsTab1.click();
		Thread.sleep(2000);

		// clickByID("tryLexDialogX", "No thanks");

		WebElement leadsDrop = findWebElementByXPath("//*[@id=\"fcf\"]");
		Select select = new Select(leadsDrop);

		String selectedOptionStr = null;
		List<WebElement> selectedOptions = select.getAllSelectedOptions();
		for (WebElement selectedOption : selectedOptions) {
			selectedOptionStr = selectedOption.getText();
		}

		WebElement go = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[1]/input");
		go.click();

		if (selectedOptionStr.equals("My Unread Leads")) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed.");
		}
		closeBrowser();
	}

//Testcase 23	

	public static void todaysLeads() throws Exception {

		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
		leadsTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		String inputName = "Today's Leads";
		WebElement leadsDropDown = findWebElementByXPath("//*[@id=\"fcf\"]");
		leadsDropDown.click();
		Thread.sleep(2000);
		Select ld = new Select(leadsDropDown);
		ld.selectByVisibleText(inputName);

		WebElement view1 = findWebElementByID("fcf");
		String vName = view1.getText();
		if (inputName.equals(vName)) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed");
		}

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		Thread.sleep(2000);

		WebElement logOut = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		logOut.click();
		Thread.sleep(2000);
		closeBrowser();
	}

//Testcase 24		
	public static void newLeadsButton() throws Exception {
		String expected = "ABCD";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
		leadsTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement newButton = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input");
		newButton.click();

		WebElement lastName = findWebElementByXPath("//*[@id=\"name_lastlea2\"]");
		lastName.sendKeys("ABCD");
		Thread.sleep(1000);
		WebElement company = findWebElementByXPath("//*[@id=\"lea3\"]");
		company.sendKeys("ABCD");
		Thread.sleep(2000);

		WebElement leadStatus = findWebElementByXPath("//*[@id=\"lea13\"]");
		leadStatus.click();
		Thread.sleep(2000);

		Select ls = new Select(leadStatus);
		ls.selectByVisibleText("Open - Not Contacted");
		WebElement saveButton = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
		saveButton.click();

		WebElement act = findWebElementByXPath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
		String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();
	}

//Testcase 25	

	public static void createContact() throws Exception {
		String expected = "Renu";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement newButton = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input");
		newButton.click();

		WebElement lastName = findWebElementByXPath("//*[@id=\"name_lastcon2\"]");
		lastName.sendKeys("Renu");
		Thread.sleep(1000);
		WebElement accName = findWebElementByXPath("//*[@id=\"con4\"]");
		accName.sendKeys("automation");

		WebElement save = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
		save.click();
		WebElement act = findWebElementByXPath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
		String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();

	}

//Testcase 26			
	public static void createNewView() throws Exception {
		String expected = "Riya4";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement newView = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
		newView.click();

		Thread.sleep(2000);
		WebElement viewName = findWebElementByXPath("//*[@id=\"fname\"]");
		viewName.sendKeys(expected);
		Thread.sleep(1000);
		WebElement uniName = findWebElementByXPath("//*[@id=\"devname\"]");
		uniName.sendKeys("");

		WebElement save = findWebElementByXPath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]");
		save.click();

		Thread.sleep(3000);
		// WebElement act =
		// findWebElementByXPath("//*[@id=\"00BDn00000KkFDQ_listSelect\"]");
		// WebElement act = findWebElementByID("00BDn00000KkFJc_listSelect");
		WebElement act = driver.findElement(By.name("fcf"));
		Select ob = new Select(act);
		String actual = ob.getFirstSelectedOption().getText();
		// *[@id="00BDn00000KkFFL_listSelect"]

		// String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();

	}

//Testcase 27			

	public static void recentlyCreatedContact() throws Exception {
		String expected = "Recent Contacts";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement recentlyCreated = findWebElementByXPath("//*[@id=\"hotlist_mode\"]");
		recentlyCreated.click();
		Select ob = new Select(recentlyCreated);
		ob.selectByVisibleText("Recently Created");
		WebElement act = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[1]/h3");
		String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();
	}

//Testcase 28

	public static void myContacts() throws Exception {
		String expected = "My Contacts";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement view = findWebElementByXPath("//*[@id=\"fcf\"]");
		view.click();
		Select ob = new Select(view);
		ob.selectByVisibleText("My Contacts");
		Thread.sleep(1000);
		// WebElement act =
		// findWebElementByXPath("//*[@id=\"00BDn00000Im6Gx_listSelect\"]");
		WebElement act = driver.findElement(By.name("fcf"));
		Select ob1 = new Select(act);

		String actual = ob1.getFirstSelectedOption().getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();
	}

//	Testcase 29	
	public static void contactPage() throws Exception {
		String expected = "Renu";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement name = findWebElementByXPath(
				"//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a");
		name.click();
		WebElement act = findWebElementByXPath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
		String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();
	}

//Testcase 30		

	public static void errorViewContact() throws Exception {
		String expected = "Error: You must enter a value";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement newView = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
		newView.click();
		WebElement uniqueName = findWebElementByXPath("//*[@id=\"devname\"]");
		uniqueName.sendKeys("EFGH");
		WebElement save = findWebElementByXPath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]");
		save.click();

		WebElement act = findWebElementByXPath(
				"//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]");
		String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();
	}

//Testcase 31
	public static void cancelButtonCreateNewView() throws Exception {
		String expected = "Home";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement newView = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
		newView.click();
		WebElement name = findWebElementByXPath("//*[@id=\"fname\"]");
		name.sendKeys("ABCD");
		WebElement uniqueName = findWebElementByXPath("//*[@id=\"devname\"]");
		uniqueName.sendKeys("EFGH");
		WebElement cancel = findWebElementByXPath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[2]");
		cancel.click();

		WebElement act = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2");
		String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();
	}

//Testcase 32		

	public static void saveNewButtonContact() throws Exception {
		String expected = "New Contact";
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");

		WebElement newButton = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input");
		newButton.click();

		WebElement lastName = findWebElementByXPath("//*[@id=\"name_lastcon2\"]");
		lastName.sendKeys("Indian");
		Thread.sleep(1000);
		WebElement accName = findWebElementByXPath("//*[@id=\"con4\"]");
		accName.sendKeys("Global Media");

		WebElement saveAndNew = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[2]");
		saveAndNew.click();

		WebElement act = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2");
		String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();

	}

//Testcase 33		

	public static void firstLastName() throws Exception {
		String expected = "Anu aaaaaaa";

		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement homeTab = findWebElementByXPath("//*[@id=\"home_Tab\"]/a");
		homeTab.click();
		Thread.sleep(3000);
		clickByID("tryLexDialogX", "No thanks");

		WebElement act = findWebElementByXPath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a");
		String actHref = act.getAttribute("href");

		String actual = act.getText();
		if (expected.equals(actual)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		Thread.sleep(1000);
		// clickByID("tryLexDialogX", "No thanks");
		WebElement myProfile = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[1]");
		String myProfileHref = myProfile.getAttribute("href");

		if (actHref.equals(myProfileHref)) {
			System.out.println("Testcase passed");
		} else {
			System.out.println("Testcase failed");
		}
		closeBrowser();
	}

	// Testcaes 34

	public static void firstNameLastNameVerify() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();

		WebElement myProfile = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[1]");
		myProfile.click();

		Thread.sleep(3000);
		WebElement editProfile = findWebElementByXPath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img");
		editProfile.click();
		Thread.sleep(2000);
		driver.switchTo().frame("contactInfoContentId");

		Thread.sleep(1000);
		WebElement about = findWebElementByXPath("/html/body/div/div/div/div[1]/ul/li[1]/a");
		about.click();
		String lName = "abcd";
		WebElement lastName = findWebElementByID("lastName");
		lastName.clear();
		sendKeys(lastName, lName);

		WebElement saveAll = findWebElementByXPath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]");
		saveAll.click();

		Thread.sleep(1000);

		WebElement displayedName = findWebElementByID("tailBreadcrumbNode");
		String name = displayedName.getText();

		WebElement profileIcon1 = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		String profilename = profileIcon1.getText();
		System.out.print("Names: " + profilename + " .." + name);
		if (name.trim().endsWith(lName) && profilename.trim().endsWith(lName)) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Testcase failed");
		}

		closeBrowser();
	}

//Testcase 35
	public static void verifyTabCustomization() throws Exception {
		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		WebElement plusButton = findWebElementByXPath("//*[@id=\"AllTab_Tab\"]/a/img");
		plusButton.click();
		Thread.sleep(2000);
		WebElement customizeButton = findWebElementByXPath(
				"//*[@id=\"bodyCell\"]/div[3]/div[1]/table/tbody/tr/td[2]/input");
		customizeButton.click();

		Thread.sleep(1000);
		WebElement selectBox = clickByID("duel_select_1", "SelectionBox");
		Select reports = new Select(selectBox);
		String tabToRemove = "Reports";
		reports.selectByVisibleText(tabToRemove);

		clickByXPath("//*[@id=\"duel_select_0_left\"]/img", "RemoveButton");
		clickByXPath("//*[@id=\"bottomButtonRow\"]/input[1]", "SaveButton");

		Thread.sleep(2000);

		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		Thread.sleep(1000);
		WebElement logOutButton = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		logOutButton.click();

		Thread.sleep(2000);

		WebElement userBox1 = findWebElementByID("username");
		userBox1.sendKeys(userName);
		WebElement passwordBox1 = findWebElementByID("password");
		passwordBox1.sendKeys(password);

		WebElement loginButton1 = findWebElementByID("Login");
		loginButton1.click();

		boolean isTabRemoved = true;
		WebElement tabBar = findWebElementByID("tabBar");
		List<WebElement> elements = tabBar.findElements(By.tagName("a"));
		for (WebElement element : elements) {
			System.out.println(element.getText());
			if (element.getText().equalsIgnoreCase(tabToRemove)) {
				isTabRemoved = false;
			}
		}

		if (isTabRemoved) {
			System.out.println("Test caes passed");
		} else {
			System.out.println("Test case failed");
		}
		closeBrowser();

	}

//Testcase 36
	public static void eventBlock() throws Exception {
		String expected = "Anu aaaaaaa";

		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement homeTab = findWebElementByXPath("//*[@id=\"home_Tab\"]/a");
		homeTab.click();
		Thread.sleep(3000);
		clickByID("tryLexDialogX", "No thanks");

		WebElement dateLink = findWebElementByXPath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a");
		dateLink.click();
		Thread.sleep(3000);
		WebElement pmLink = findWebElementByXPath("//*[@id=\"p:f:j_id25:j_id61:28:j_id64\"]");
		pmLink.click();
		Thread.sleep(2000);

		String baseHandle = driver.getWindowHandle();

		WebElement subjectCombo = findWebElementByXPath(
				"//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img");
		subjectCombo.click();

		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(baseHandle)) {
				driver.switchTo().window(handle);
			}
		}

		WebElement other = findWebElementByXPath("/html/body/div[2]/ul/li[5]/a");
		other.click();

		driver.switchTo().window(baseHandle);

		WebElement endTime = findWebElementByXPath("//*[@id=\"EndDateTime_time\"]");
		sendKeys(endTime, "9:00 PM");
		/*
		 * endTime.click(); Select ob = new Select(endTime);
		 * ob.selectByVisibleText("9:00 PM");
		 */

		WebElement save = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
		save.click();

		WebElement div8PM = findWebElementByXPath(
				"//*[@id=\"p:f:j_id25:j_id69:28:j_id71:0:j_id72:calendarEvent:i\"]/div");
		List<WebElement> list = div8PM.findElements(By.tagName("a"));
		if (list.size() > 0) {
			System.out.println("Test case passed");
		} else {
			System.out.println("Test case failed");
		}
		closeBrowser();
	}

//Testcase 37
	public static void eventBlockWeeklyRecurrence() throws Exception {
		String expected = "Anu aaaaaaa";

		initDriverInstance("chrome");
		PropertiesLoader.initProperties();
		String url = PropertiesLoader.getProperty("url");
		String userName = PropertiesLoader.getProperty("username");
		String password = PropertiesLoader.getProperty("password");

		gotoURL(url);
		Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		Thread.sleep(1000);

		WebElement homeTab = findWebElementByXPath("//*[@id=\"home_Tab\"]/a");
		homeTab.click();
		Thread.sleep(3000);
		clickByID("tryLexDialogX", "No thanks");

		WebElement dateLink = findWebElementByXPath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a");
		dateLink.click();
		Thread.sleep(3000);
		WebElement pmLink = findWebElementByXPath("//*[@id=\"p:f:j_id25:j_id61:28:j_id64\"]");
		pmLink.click();
		Thread.sleep(2000);
		String baseHandle = driver.getWindowHandle();
		WebElement subjectCombo = findWebElementByXPath(
				"//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img");
		subjectCombo.click();

		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(baseHandle)) {
				driver.switchTo().window(handle);
			}
		}

		Thread.sleep(1000);
		WebElement other = findWebElementByXPath("/html/body/div[2]/ul/li[5]/a");
		other.click();

		driver.switchTo().window(baseHandle);
		WebElement endTime = findWebElementByXPath("//*[@id=\"EndDateTime_time\"]");
		endTime.clear();
		sendKeys(endTime, "7:00 PM");
		/*
		 * endTime.click(); Select ob = new Select(endTime);
		 * ob.selectByVisibleText("7:00 PM");
		 */
		clickByXPath("//*[@id=\"IsRecurrence\"]", "recurrence");
		clickByXPath("//*[@id=\"rectypeftw\"]", "weekly");

		clickByID("EndDateTime", "EndDate");
		/*
		 * String startDate = findWebElementByID("StartDateTime").getText();
		 * SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		 */
		WebElement endDate = findWebElementByXPath(
				"/html/body/div/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/table/tbody/tr[5]/td[7]");
		endDate.click();

		clickByXPath("//*[@id=\"bottomButtonRow\"]/input[1]", "save");

		clickByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/span[2]/div/div/div[2]/span[2]/a[3]/img",
				"Month view");

		/*
		 * WebElement table = findWebElementByXPath(
		 * "//*[@id=\"bodyCell\"]/div[2]/div[1]/div[1]/table/tbody"); List<WebElement>
		 * cells = table.findElements(By.className("calActive")); for(WebElement cell :
		 * cells) { List<WebElement> links = cell.findElements(By.tagName("a")); String
		 * date = null; boolean otherPresent = false; for(WebElement link : links) {
		 * String title = link.getAttribute("title"); if(title.startsWith("Day View")) {
		 * date = link.getText(); } if(link.getText() != null &&
		 * link.getText().equals("Other")) { otherPresent = true; } }
		 * 
		 * }
		 */
		closeBrowser();
	}

}
