package com.samotyja.Wikia;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunTest {
	private static WebDriver driver;  //variables
	private static WebDriverWait wait;
	public int TimeWaitForWebside =15; //Time timeout for loading webside or waiting for element
	public String Username = "Mateusz_Samotyja";  //USername
	public String Password = "XXXXXXXX"; //Password
	public String WebsideWikia = "http://qm-homework.wikia.com"; //main webside
	
	@Test  //TEST nr 1
	public void SignIn() throws InterruptedException {
		
		System.out.println("\n######### START TEST 1 - SIGN IN #########");
		driver = new FirefoxDriver();  //Open new firefox webBrowser
		//FOR CHROME BROWSER
		// System.setProperty("webdriver.chrome.driver", "C:\\Program Files   
		// (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(TimeWaitForWebside, TimeUnit.SECONDS);
		
		try {
			driver.get(WebsideWikia);  //Open webside wikia
		} catch (TimeoutException exception) {
			System.out.println("(WARNING) time loading webside 'http://qm-homework.wikia.com' was too long"); //Printout to console when the time loading webside was too long   
		}
		Assert.assertEquals("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia", driver.getCurrentUrl()); //Comparision address webside with expected adress
		System.out.println("(OK) - The webside: 'http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia' was runned correctly");
		
		Actions action = new Actions(driver);		
		WebElement we = driver.findElement(By.id("AccountNavigation")); //Find element Account navigation on webside
		Assert.assertTrue(we.isDisplayed());
		List<WebElement> findElements = we.findElements(By.tagName("span")); //Find elements on webside with tagName span
		Assert.assertTrue(findElements.size() >= 1);
		boolean flag = false;
		boolean flag2 = false;
		
		for (WebElement element : findElements) {
			if (element.getText() != null && element.getText().equals("Sign In")) {  //Comparision Text 'Sign In' with finds elements
				flag = true;  
				
				break;
			}
		}

		Assert.assertTrue("\"(FAULT) Sign in\" not found", flag); //test has error when result of comparision is false (no find element) 
		action = action.moveToElement(we); //Hover Mouse over the 'Sign in' label
		action.perform(); //running action
		
		wait = new WebDriverWait(driver, TimeWaitForWebside);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserLoginDropdown"))); //waiting for element UserLoginDropdown on new webside
		action.perform();
		
		System.out.println("(OK) - LoginForm is visible"); //Login form is visible on webside
		action = action.moveToElement(driver.findElement(By.id("UserLoginDropdown")));  
		action.perform(); //UserLoginDropdown is drop-down
		
		action = action.click(driver.findElement(By.id("usernameInput"))).sendKeys(Username + Keys.TAB); //Enter Username to webside
		action.build().perform();
		
		driver.findElement(By.id("passwordInput")).sendKeys(Password); //Enter Password
		action.build().perform();
		
		driver.manage().timeouts().pageLoadTimeout(TimeWaitForWebside, TimeUnit.SECONDS);
		try
		{
			driver.findElement(By.id("passwordInput")).submit(); //send Login and Password
		}
		catch (TimeoutException exception)
		{
			System.out.println("(WARNING) time loading webside 'http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia' was too long"); //Printout to console when the time loading webside was too long   
		}
		action.build().perform();
		WebDriverWait wait2 = new WebDriverWait(driver, TimeWaitForWebside);
		
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("links-container"))); //Waiting for new classname
		we = driver.findElement(By.id("AccountNavigation"));
		
		findElements = we.findElements(By.tagName("span"));
		flag = true;
		for (WebElement element1 : findElements) {
			if (element1.getText() != null && element1.getText().equals("Sign In")) { //searching element Sign in on webside
				flag = false; //when find element Sign In, result has error.
				break;
			}
		}

		List<WebElement> findElements2 = we.findElements(By.tagName("a")); //searching title "Mateusz Samotyja - My page" 
		flag2 = false;
		for (WebElement element2 : findElements2) {
			if (element2.getAttribute("title") == null
					|| element2.getAttribute("title").equals("Mateusz Samotyja - My page")) {
				flag2 = true;  //when find result is OK 
				System.out.println("(OK) - user have successfull logged on 'Mateusz Samotyja - My page'");
				break;
				
			}
		}
		Assert.assertTrue("\"(FAULT) - Sign in\" still visible", flag);
		Assert.assertTrue("\"(FAULT) - Icon is not connected to username", flag2);

	}

	@Test  //TEST NR 2
	public void AddVideo() throws InterruptedException {
		System.out.println("\n######### START TEST 2 - ADD VIDEO #########");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AccountNavigation")));
		
		WebElement we = driver.findElement(By.id("AccountNavigation"));
		List<WebElement> findElements2 = we.findElements(By.tagName("a"));
		boolean flag2 = false;
		
		for (WebElement element2 : findElements2) {
			if (element2.getAttribute("title") != null
					&& element2.getAttribute("title").equals("Mateusz Samotyja - My page")) {  //User is log in 
				flag2 = true;
		System.out.println("(OK) - The webside: 'http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia' was runned before this test (AddVideo)");
				break;
			}
		}

		if (flag2 == false) {   //when user is log out
			System.out.println("'");
			driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(TimeWaitForWebside, TimeUnit.SECONDS);
			try {
				driver.get(WebsideWikia); //Open webside wikia
			} catch (TimeoutException exception) {
				System.out.println("(WARNING) - time loading webside 'http://qm-homework.wikia.com' was too long"); //Printout to console when the time loading webside was too long   
			}
			Assert.assertEquals("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia", driver.getCurrentUrl());//Comparision address webside with expected adress
			System.out.println("(OK) - The webside: 'http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia' was runned correctly");
			Actions action = new Actions(driver);
			
			we = driver.findElement(By.id("AccountNavigation")); //Find element Account navigation on webside
			Assert.assertTrue(we.isDisplayed());
			List<WebElement> findElements = we.findElements(By.tagName("span")); //Find elements on webside with tagName span
			Assert.assertTrue(findElements.size() >= 1);
			boolean flag = false;
			
			for (WebElement element : findElements) {
				if (element.getText() != null && element.getText().equals("Sign In")) {//Comparision Text 'Sign In' with finds elements
					flag = true;
					break;
				}
			}

			Assert.assertTrue("\"(FAULT) - Sign in\" not found", flag);//test has error when result of comparision is false (no find element) 
			action = action.moveToElement(we);

			action.perform();
			wait = new WebDriverWait(driver, TimeWaitForWebside);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserLoginDropdown"))); //waiting for element UserLoginDropdown on new webside
			

			action.perform();
			action = action.moveToElement(driver.findElement(By.id("UserLoginDropdown")));

			action.perform(); //UserLoginDropdown is drop-down
			action = action.click(driver.findElement(By.id("usernameInput"))).sendKeys(Username + Keys.TAB);//Enter Username to webside

			action.build().perform();
			driver.findElement(By.id("passwordInput")).sendKeys(Password); //Enter Password

			action.build().perform();

			try
			{
				driver.findElement(By.id("passwordInput")).submit(); //send Login and Password
			}
			catch (TimeoutException exception)
			{
				System.out.println("(WARNING) time loading webside 'http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia"); //Printout to console when the time loading webside was too long   
			}
			
			action.build().perform();
			WebDriverWait wait2 = new WebDriverWait(driver, TimeWaitForWebside);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("links-container"))); //Waiting for new classname

			we = driver.findElement(By.id("AccountNavigation"));
			List<WebElement> findElements2_2 = we.findElements(By.tagName("a"));
			flag2 = false;
			for (WebElement element2 : findElements2_2) {
				if (element2.getAttribute("title") == null
						|| element2.getAttribute("title").equals("Mateusz Samotyja - My page")) { //searching title "Mateusz Samotyja - My page" 
					flag2 = true;
					System.out.println("(OK) - user have successfull logged on 'Mateusz Samotyja - My page'");
					break;
				}
			}
			Assert.assertTrue("\"Icon is not connected to username", flag2); //Result s OK when find title "Mateusz Samotyja - My page" 
		}

		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, TimeWaitForWebside); //declaration waiting time
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("buttons")));//waiting for element buttons 
		WebElement Account = driver.findElement(By.id("AccountNavigation"));
		Assert.assertTrue(Account.isDisplayed());
		List<WebElement> findElements = Account.findElements(By.tagName("span")); 
		boolean flag = true;

		for (WebElement element : findElements) {
			if (element.getText() != null && element.getText().equals("Sign In")) {
				 flag=false;  //Sign in is visible. User is log out
				System.out.println("Error - user is logged out");
				break;
			}
		}
		Assert.assertTrue("\"(FAULT) - Sign in\" not found", flag);

		WebElement we2 = driver.findElement(By.className("buttons"));
		WebElement findElement = we2.findElement(By.tagName("nav")); 
		action = action.click(findElement); //Find element Contribute on webside and click on this element
		action.build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("WikiaMenuElement"))); //waiting for menuElement
		System.out.println("(OK) - Button contribute drop-down expanded");
		List<WebElement> MenuElement = driver.findElements(By.tagName("a"));

		for (WebElement element1 : MenuElement) {
			if (element1.getText() != null && element1.getText().equals("Add a Video")) { 
				action = action.click(element1);  //find and click on element Add Video
				action.build().perform();
				break;
			}
		}

		wait.until(ExpectedConditions.urlContains("http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd")); 

		Assert.assertEquals("http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd", driver.getCurrentUrl()); //Comparision address webside with expected adress
		
		System.out.println("(OK) - The webside: 'http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd' was runned correctly");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WikiaMainContent"))); 
		WebElement weURL = driver.findElement(By.id("WikiaMainContent"));
		WebElement UrlName = weURL.findElement(By.id("wpWikiaVideoAddUrl")); //Find element wpWikiaVideoAddUrl

		action = action.click(UrlName).sendKeys("https://www.youtube.com/watch?v=h9tRIZyTXTI"); //Write address
		action.build().perform();

		WebElement AddElement = driver.findElement(By.cssSelector("input[type='submit'][value='Add']")); //click on Add
		action = action.click(AddElement);
		action.build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("msg")));
		WebElement AddGood = driver.findElement(By.className("msg"));
		
		String fileTittle = "The Best Classical Music In The World";
		String fileTitleWithUnderscores = fileTittle.replace(" ", "_"); //replace character in fileTitle
		Assert.assertEquals("Video page File:" + fileTittle + " was successfully added.", AddGood.getText()); //check message on webside
		System.out.println("(OK)-"+AddGood.getText());
		AddGood.findElement(By.tagName("a")).click();
		wait.until(
				ExpectedConditions.urlContains("http://qm-homework.wikia.com/wiki/File:" + fileTitleWithUnderscores)); 
		System.out.println("(OK) - User is redirected to the webside http://qm-homework.wikia.com/wiki/File:The_Best_Classical_Music_In_The_World");
		Assert.assertEquals("http://qm-homework.wikia.com/wiki/File:" + fileTitleWithUnderscores,
				driver.getCurrentUrl()); //check the filename on webside
		System.out.println("(OK) - The name of loaded Video is correct.\n");

	}

}
