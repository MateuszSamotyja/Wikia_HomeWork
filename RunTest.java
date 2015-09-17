package com.samotyja.Wikia;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PauseAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunTest {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	
	@Test
	public void SignIn() throws InterruptedException {
		driver = new FirefoxDriver();
		// System.setProperty("webdriver.chrome.driver", "C:\\Program Files
		// (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		// driver = new ChromeDriver();
	//	WebDriver driver = new HtmlUnitDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		try {
			driver.get("http://qm-homework.wikia.com");
		} catch (TimeoutException exception) {
			System.out.println("timeout");
		}
		Assert.assertEquals("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia", driver.getCurrentUrl());
		System.out.println(driver.getCurrentUrl());
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.id("AccountNavigation"));
		Assert.assertTrue(we.isDisplayed());
		List<WebElement> findElements = we.findElements(By.tagName("span"));
		Assert.assertTrue(findElements.size() >= 1);
		boolean flag = false;
		boolean flag2 = false;
		for (WebElement element : findElements) {
			if (element.getText() != null && element.getText().equals("Sign In")) {
				flag = true;
				break;
			}
		}

		Assert.assertTrue("\"Sign in\" not found", flag);
		action = action.moveToElement(we);

		action.perform();
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserLoginDropdown")));

		action.perform();
		action = action.moveToElement(driver.findElement(By.id("UserLoginDropdown")));

		action.perform();
		action = action.click(driver.findElement(By.id("usernameInput"))).sendKeys("Mateusz_Samotyja" + Keys.TAB);

		action.build().perform();
		driver.findElement(By.id("passwordInput")).sendKeys("wikia368a");

		action.build().perform();

		driver.findElement(By.id("passwordInput")).submit();

		action.build().perform();
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("links-container")));

		we = driver.findElement(By.id("AccountNavigation"));
		findElements = we.findElements(By.tagName("span"));
		flag = true;
		for (WebElement element1 : findElements) {
			if (element1.getText() != null && element1.getText().equals("Sign In")) {
				flag = false;
				break;
			}
		}

		List<WebElement> findElements2 = we.findElements(By.tagName("a"));
		flag2 = false;
		for (WebElement element2 : findElements2) {
			if (element2.getAttribute("title") == null
					|| element2.getAttribute("title").equals("Mateusz Samotyja - My page")) {
				flag2 = true;
				break;
			}
		}
		Assert.assertTrue("\"Sign in\" still visible", flag);
		Assert.assertTrue("\"Icon is not connected to username", flag2);

	}

	@Test
	public void AddVideo() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AccountNavigation")));
		WebElement we = driver.findElement(By.id("AccountNavigation"));
		List<WebElement> findElements2 = we.findElements(By.tagName("a"));
		boolean flag2 = false;
		for (WebElement element2 : findElements2) {
			if (element2.getAttribute("title") != null
					&& element2.getAttribute("title").equals("Mateusz Samotyja - My page")) {
				flag2 = true;
				break;
			}
		}

		if (flag2 == false) {

			driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			try {
				driver.get("http://qm-homework.wikia.com");
			} catch (TimeoutException exception) {
				System.out.println("timeout");
			}
			Assert.assertEquals("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia", driver.getCurrentUrl());
			System.out.println(driver.getCurrentUrl());
			Actions action = new Actions(driver);

			we = driver.findElement(By.id("AccountNavigation"));
			Assert.assertTrue(we.isDisplayed());
			List<WebElement> findElements = we.findElements(By.tagName("span"));
			Assert.assertTrue(findElements.size() >= 1);
			boolean flag = false;
			for (WebElement element : findElements) {
				if (element.getText() != null && element.getText().equals("Sign In")) {
					flag = true;
					break;
				}
			}

			Assert.assertTrue("\"Sign in\" not found", flag);
			action = action.moveToElement(we);

			action.perform();
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserLoginDropdown")));

			action.perform();
			action = action.moveToElement(driver.findElement(By.id("UserLoginDropdown")));

			action.perform();
			action = action.click(driver.findElement(By.id("usernameInput"))).sendKeys("Mateusz_Samotyja" + Keys.TAB);

			action.build().perform();
			driver.findElement(By.id("passwordInput")).sendKeys("wikia368a");

			action.build().perform();

			driver.findElement(By.id("passwordInput")).submit();

			action.build().perform();
			WebDriverWait wait2 = new WebDriverWait(driver, 30);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("links-container")));

			we = driver.findElement(By.id("AccountNavigation"));
			List<WebElement> findElements2_2 = we.findElements(By.tagName("a"));
			flag2 = false;
			for (WebElement element2 : findElements2_2) {
				if (element2.getAttribute("title") == null
						|| element2.getAttribute("title").equals("Mateusz Samotyja - My page")) {
					flag2 = true;
					break;
				}
			}
			Assert.assertTrue("\"Icon is not connected to username", flag2);
		}

		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("buttons")));
		WebElement Account = driver.findElement(By.id("AccountNavigation"));
		Assert.assertTrue(Account.isDisplayed());
		List<WebElement> findElements = Account.findElements(By.tagName("span"));
		// Assert.assertTrue(findElements.size() >= 1);
		boolean flag = true;

		for (WebElement element : findElements) {
			if (element.getText() != null && element.getText().equals("Sign In")) {
				// flag=false;
				System.out.println("Error - user is logged out");
				break;
			}
		}

		Assert.assertTrue("\"Sign in\" not found", flag);

		WebElement we2 = driver.findElement(By.className("buttons"));
		WebElement findElement = we2.findElement(By.tagName("nav"));

		// Assert.assertTrue(driver.findElement(By.className("WikiaMenuElement")).isDisplayed());

		action = action.click(findElement);
		action.build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("WikiaMenuElement")));

		List<WebElement> MenuElement = driver.findElements(By.tagName("a"));

		for (WebElement element1 : MenuElement) {
			if (element1.getText() != null && element1.getText().equals("Add a Video")) {
				action = action.click(element1);
				action.build().perform();
				break;
			}
		}

		wait.until(ExpectedConditions.urlContains("http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd"));

		Assert.assertEquals("http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd", driver.getCurrentUrl());
		System.out.println(driver.getCurrentUrl());

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WikiaMainContent")));
		WebElement weURL = driver.findElement(By.id("WikiaMainContent"));
		WebElement UrlName = weURL.findElement(By.id("wpWikiaVideoAddUrl"));

		action = action.click(UrlName).sendKeys("https://www.youtube.com/watch?v=h9tRIZyTXTI");
		action.build().perform();

		WebElement AddElement = driver.findElement(By.cssSelector("input[type='submit'][value='Add']"));
		action = action.click(AddElement);
		action.build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("msg")));

		WebElement AddGood = driver.findElement(By.className("msg"));
		System.out.println(AddGood.getText());
		String fileTittle = "The Best Classical Music In The World";
		String fileTitleWithUnderscores = fileTittle.replace(" ", "_");
		Assert.assertEquals("Video page File:" + fileTittle + " was successfully added.", AddGood.getText());
		AddGood.findElement(By.tagName("a")).click();
		wait.until(
				ExpectedConditions.urlContains("http://qm-homework.wikia.com/wiki/File:" + fileTitleWithUnderscores));
		Assert.assertEquals("http://qm-homework.wikia.com/wiki/File:" + fileTitleWithUnderscores,
				driver.getCurrentUrl());

		// wait.until(ExpectedConditions
		// .visibilityOfElementLocated(By.className("fullImageLink")));
		// we = driver.findElement(By.className("ytp-title-text"));
		// WebElement TitleVideo = we.findElement(By.tagName("a"));
		// Assert.assertEquals(
		// "The_Best_Classical_Music_In_The_World",
		// TitleVideo.getText());

	}

}
