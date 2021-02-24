package element;


import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Element implements IElement {

	protected WebDriver driver = null;
	protected WebElement element = null;
	
	private static final Logger logger = LogManager.getLogger(Element.class.getName());

	public Element(WebDriver driver, WebElement element) {
		this.driver = driver;
		this.element = element;
	}
	
	public Element(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement _element = wait.until(ExpectedConditions.visibilityOf(this.element));
		logger.info("isDisplayed: "+ _element.isDisplayed());
		return _element.isDisplayed();
	}	
	
	public static WebElement filterElementByText(List<WebElement> elements, String text) {
		
		WebElement _element = null;
		try {
			_element = filterElementsByText(elements, text).get(0);	
		}
		catch(Exception ex) {
			logger.error("filterElementByText: "+ex.getMessage());
		}
		logger.info("filterElementByText: element is " 
				+ ((_element == null) ? "null":"not null") + " and text is "+text);
		return _element;
	}
	
	
	public static List<WebElement> filterElementsByText(List<WebElement> elements, String text) {
		List<WebElement> _elements = new ArrayList<WebElement>();
		try {
			//_elements = elements.stream().filter(element -> element.getText().equals(text))
			//						.collect(Collectors.toList());
			
			for(WebElement element: elements) {
				if(element.getText().equals(text)) {
					_elements.add(element);
				}
			}
		}
		catch(Exception ex) {
			logger.error("filterElementsByText: "+ex.getMessage());
		}
		logger.info("filterElementsByText: input elements- "+elements.size()
				+ " filtered: "+_elements.size());
		
		return _elements;
	}
	
	public static int getElementIndexByText(List<WebElement> elements, String text) {
		int position = -1;
		for(int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getText().equals(text)) {
				position = i;
				break;
			}
		}
		logger.info("getElementIndexByText: input elements- "+elements.size()
				+ " filtered index position: "+position);
		return position;
	}
	
	public WebElement waitForElementToBeVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOf(this.element));
	}
	
	public WebElement waitForElementToBeClickable() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(this.element));
	}
	
	public void inputText(String text) {
		waitForElementToBeVisible();
		this.element.clear();
		this.element.sendKeys(text);
	}
	
	public void click() {
		waitForElementToBeClickable().click();
	}
	
	public void mouseHover() {
		Actions actions = new Actions(driver);
		actions.moveToElement(this.element).build().perform();
	}
	
	public void mouseHoverAndClick() {
		Actions actions = new Actions(driver);
		actions.moveToElement(this.element).click().build().perform();
	}
	
	public String getText() {
		String text = waitForElementToBeVisible().getText();
		logger.debug("getText: text- "+text);
		return text;
	}
	
	public void clickAndWaitForPageReload() {
		int oldHash = element.hashCode();
		element.click();
		WebDriverWait newWait = new WebDriverWait(driver, 5);
		try {
			newWait.until(ExpectedConditions.stalenessOf(element));
			logger.debug("oldHash: "+oldHash+", newHash: "+element.hashCode());
		}catch(Exception ex) {
			logger.error("clickAndWaitForPageReload: "+ex.getMessage());
		}

	}
}
