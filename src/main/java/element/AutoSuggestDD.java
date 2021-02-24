package element;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AutoSuggestDD extends Element{
	private static final Logger logger = LogManager.getLogger(AutoSuggestDD.class.getName());
	private Element textBox = null;
	private Element selectElement = null;
	
	public AutoSuggestDD(WebDriver driver, WebElement textBox) {
		super(driver);
		this.textBox = new Element(driver, textBox);
	}
	
	public void typeAndSelectElement(List<WebElement> selectElements, String fullText, String partText) {
		 
		if(this.textBox.isDisplayed()) {
			String text = partText.isEmpty() ? fullText: partText;
			this.textBox.inputText(text);
			logger.debug("Text typed: "+ (text.isEmpty() ? "Empty":text));
			
			if(!fullText.isEmpty()) {
				selectElement = new Element(driver, Element.filterElementByText(selectElements, fullText));
				selectElement.click();
			}
		}
		else {
			logger.error("typeAndSelectElement: Element is not Visible");
		}
	}


}
