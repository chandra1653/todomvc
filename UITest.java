/*Author : Chandrasekhar
Date:- 31-05-2017
*/
/* This File has all the Testcases to run the UI Test on the T0-do APP*/
/* Run The below Tests in any Java IDE */
/*  Selenium 2.53 version 
Firefox Browser 46 version 
JDK version 1.8
*/ 

package UITest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Add_Todo {
	static WebDriver driver ;
	String Xpath_TextBox = "//input[@class='new-todo']";
	
	public void IntializeBrowser(){
		driver = new FirefoxDriver();
		driver.get("http://todomvc.com/examples/react/#/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		}
	public void Separator(){
		System.out.println("**************************************");
		}
	public void AddTodoList(){
		
		System.out.println("Started Adding a TO-DO");
		// Adding details in the Todo list

		
		driver.findElement(By.xpath(Xpath_TextBox)).sendKeys("Assignment_TestCases_defined");
		driver.findElement(By.xpath(Xpath_TextBox)).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(Xpath_TextBox)).sendKeys("Assignment_TestCases_Fixed");
		driver.findElement(By.xpath(Xpath_TextBox)).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(Xpath_TextBox)).sendKeys("Assignment started");
		driver.findElement(By.xpath(Xpath_TextBox)).sendKeys(Keys.ENTER);
		}
	public void EditTodoList(){
		
		System.out.println("Started Editing the TO-Do List");
		// Editing the Task
		String Xpath_SecondTask = "//ul[@class='todo-list']/li[3]/div/label";
		String Xpath_SecondTask_Edit = "//ul[@class='todo-list']/li[3]/input[@class='edit']";
		try {
			Boolean bol = driver.findElement(By.xpath(Xpath_SecondTask)).isDisplayed();
			if (bol) {

				Actions act = new Actions(driver);
				act.doubleClick(driver.findElement(By.xpath(Xpath_SecondTask))).build().perform();
				driver.findElement(By.xpath(Xpath_SecondTask_Edit)).clear();
				driver.findElement(By.xpath(Xpath_SecondTask_Edit)).sendKeys("Assignment Completed");
				driver.findElement(By.xpath(Xpath_TextBox)).click();
			}
		} catch (Exception e) {
			System.out.println("The Webelement is not present at the specified location" + e);
		}
	}
	public void CompleteTodoList(){
		// Completing a Todo
		System.out.println("Completing  the TO-DO");
		String Task_added = driver.findElement(By.xpath("//ul[@class='todo-list']/li[3]/div/label")).getText();
		driver.findElement(By.xpath("//ul[@class='todo-list']/li[3]/div/input[@class='toggle']")).click();
		//System.out.println(Task_added);
		driver.findElement(By.xpath("//footer/ul/li[3]/a")).click();
		String Task_completed = driver.findElement(By.xpath("//ul[@class='todo-list']/li/div/label")).getText();
		if (Task_added.equals(Task_completed)) {
			System.out.println("Task completed : Matched when filtered");
		} else {
			System.out.println("Task completed : Not matched when filtereds");
		}
	}
	public void FilterTodoList(){
		
	// filtering a Todo-List 
	
	System.out.println("Started Filtering of To-Do List");
	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//footer/ul/li[1]/a"))))
			.click();
	driver.findElement(By.xpath("//input[@class='toggle-all']")).click();
	driver.findElement(By.xpath("//footer/ul/li[3]/a")).click();
	int Total_Completed_Tasks = driver.findElements(By.xpath("//ul[@class='todo-list']/li")).size();
	//Verifying the Completed Tasks
	System.out.println("Completed Tasks");
	for(int i=0;i<Total_Completed_Tasks;i++){
		System.out.println(driver.findElements(By.xpath("//ul[@class='todo-list']/li")).get(i).getText());
	}
	//Toggle off all the completed Tasks
	//driver.findElement(By.xpath("//input[@class='toggle-all']")).click();
	//Verifying the Active Tasks By Turning off the Completed Button for Second Task
	driver.findElement(By.xpath("//ul[@class='todo-list']/li[2]/div/input[@class='toggle']")).click();
	driver.findElement(By.xpath("//footer/ul/li[2]/a")).click();
	System.out.println("The Active Task Currently Available is:"+driver.findElement(By.xpath("//ul[@class='todo-list']/li[1]/div/label")).getText());
	}

	public void DeleteTodoList(){
	//Displaying All the Available Tasks 
	System.out.println("The Available Tasks");
	driver.findElement(By.xpath("//footer/ul/li[1]/a")).click();
	//Deleting The  Active Task
	driver.findElement(By.xpath("//ul[@class='todo-list']/li[2]/div/button")).click();
	//Deleting The Completed Tasks
	driver.findElement(By.xpath("//footer/button")).click();
	System.out.println("Deleted All the Tasks");
	}
	public void RunTests(){
		Separator();
		AddTodoList();
		Separator();
		EditTodoList();
		Separator();
		CompleteTodoList();
		Separator();
		FilterTodoList();
		Separator();
		DeleteTodoList();
		Separator();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Add_Todo Test = new Add_Todo();
		Test.IntializeBrowser();
		Test.RunTests();
		driver.quit();
	}

}
