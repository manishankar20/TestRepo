package reusableComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class ActionEngine {

	//Customized sendkeys method-> To log sendkeys message for every occ.
	public void sendKeys_custom(WebElement element, String fieldName, String valueToBeSent) {
		try {
			element.sendKeys(valueToBeSent);
			//log success message in exgent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Ented value as: "+valueToBeSent);
		} catch (Exception e) {
			//log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field: "+fieldName + " is failed due to exception: "+e);
		}
	}


	//custom click method to log evey click action in to extent report
	public void click_custom(WebElement element, String fieldName) {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
			until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Clicked Successfully! ");
		} catch (Exception e) {
			//log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
		}
	}


	//clear data from field
	public void clear_custom(WebElement element,String fieldName) {
		try {
			element.clear();
			Thread.sleep(250);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Data Cleared Successfully! ");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to clear Data on field: " +fieldName +" due to exception: "+e);

		} 
	}

	//custom mouseHover 
	public void moveToElement_custom(WebElement element,String fieldName){
		try{
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());		
			actions.moveToElement(element).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		}catch(Exception e){
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to hover mouse on field: " +fieldName +" due to exception: "+e);

		}
	}


	//check if element is Present
	public boolean isElementPresent_custom(WebElement element,String fieldName){
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Presence of field is: "+ flag);
			return flag;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Checking for presence of field: " +fieldName +" not tested due to exception: "+e);
			return flag;
		}
	}


	//Select dropdown value value by visibleText
	public void selectDropDownByVisibleText_custom(WebElement element, String fieldName, String ddVisibleText) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddVisibleText);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
		}
	}

	//Select dropdown value value by value
	public void selectDropDownByValue_custom(WebElement element, String fieldName, String ddValue) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByValue(ddValue);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddValue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
		}
	}

	//String Asserts
	public void assertEqualsString_custom(String expvalue, String actualValue, String locatorName) throws Throwable {
		try {
			if(actualValue.equals(expvalue)) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
			}else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	//Get text from webelement
	public String getText_custom(WebElement element, String fieldName) {
		String text = "";
		try {
			text = element.getText();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Text retried is: "+ text);
			return text;
		} catch (Exception e) {		
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, fieldName+"==> Text not retried due to exception: "+ e);

		}
		return text;
	}
	
	
	
	// ---------------------------------------------------------------Appian Elements---------------------------------------------------
	//Enter text to the textfields
	public void enterText(String label, String value) {
		String xpath = "//label[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div/input[@type='text']";
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath)), label, value);

	}
	
	// this method will click on the dropdown value after click on the dropdown
	public void selectValueFromList(String value) {
		List<WebElement> list = DriverFactory.getInstance().getDriver().findElements(By.xpath("//ul[@role='listbox']//li"));
		//System.out.println(list.size());
		for(WebElement element : list){
			System.out.println(element.getText());
			if(element.getText().equals(value))
			{
				click_custom(element, value);
				break;
			}
		}	
	}
	
	// this method used to handle the dropdown
	public void selectDropdownByText(String label, String value)  {
		String dropdownXpath = "//span[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div[@role='listbox']";
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(dropdownXpath)), label);
		selectValueFromList(value);
	}
	
	public int getfield_grid(String label) {
		//it will return the column based on the label
		int lastColumncount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//table[1]")).findElements(By.tagName("th")).size();
		int column_number=0;
		for (int i=1; i<=lastColumncount; i++)
		{
			String columnXpath = "//table[1]//tr//th["+i+"]";
			String columnLabel = DriverFactory.getInstance().getDriver().findElement(By.xpath(columnXpath)).getText();
			if(columnLabel.equalsIgnoreCase(label) ) {
				 column_number = i;
				break;
			}	
		}
		return column_number;
	}
	
	//it will enter the data in last row of the grid
	public void enterText_Grid(String column_label, String value) {
		int lastRowcount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//table[1]/tbody")).findElements(By.tagName("tr")).size();
		int column_number = getfield_grid(column_label);
		WebElement e = DriverFactory.getInstance().getDriver().findElement(By.xpath("//table[1]/tbody/tr["+lastRowcount+"]/td["+column_number+"]//input[@type='text'")); 
		sendKeys_custom(e, column_label, value);
	}
	
	//This method handle the dropdown in the grid
	public void selectDropdown_Grid(String column_label, String value) {
		int lastRowcount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//table[1]/tbody")).findElements(By.tagName("tr")).size();
		int column_number = getfield_grid(column_label);
		WebElement e = DriverFactory.getInstance().getDriver().findElement(By.xpath("//table[1]/tbody/tr["+lastRowcount+"]/td["+column_number+"]//div[@role='listbox']")); 
		click_custom(e, column_label);
		selectValueFromList(value);	
	}
	
	//This method clicks on the button
	public void clickOnButton(String buttonText) {
		String buttonXpath = "//button[contains(text(),'"+buttonText+"')]";
		WebElement e =DriverFactory.getInstance().getDriver().findElement(By.xpath(buttonXpath)); 
		click_custom(e, buttonText);
	}
	
	//This method clicks on the buttons present  on appian popups
	public void clickOnButtonOnPopup(String buttonText) {
		String buttonXpath = "//button[contains(text(),'"+buttonText+"')and @class = 'Button---btn Button---default_direction Button---primary appian-context-first-in-list appian-context-last-in-list Button---inModalDialogLayout']";
		WebElement e =DriverFactory.getInstance().getDriver().findElement(By.xpath(buttonXpath)); 
		click_custom(e, buttonText);
	}
	
	//This method delete the rows in the grid
	public void deleteRowInGrid(int rowIndex) {
		int lastColumncount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//table[1]/tbody")).findElements(By.tagName("td")).size();
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//tr["+rowIndex+"]//td["+lastColumncount+"]//a//img")), rowIndex+"row deleted");
	}

}
