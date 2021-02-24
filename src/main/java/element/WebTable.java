package element;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTable extends Element{
	

	public WebTable(WebDriver driver, WebElement element) {
		super(driver, element);
	}
	
	
	public int noOfRows() {
		List<WebElement> rows = this.element.findElements(By.cssSelector("tbody>tr"));
		return rows.size();

	}
	
	public int getRowNoByText(String columnHeader, String text) {
		int col = getElementIndexByText(this.element.findElements(By
					.xpath("th")), columnHeader);
		
		return getElementIndexByText(this.element.findElements(By
				.cssSelector("tbody>tr>td:nth-of-type("+col+")")), text);
	
	}
	
	public void clickCheckBoxOfRowByText(String columnHeader, String text) {
		int row = getRowNoByText(columnHeader, text);
		
		this.element.findElements(By.cssSelector("td>input[type=checkbox]")).get(row).click();
	}
	
	public void clickCellByText(String columnHeader, String text) {
		int col = getElementIndexByText(this.element.findElements(By
				.cssSelector("th")), columnHeader);
		filterElementByText(this.element.findElements(By
				.cssSelector("tbody>tr>td:nth-of-type("+col+")")), text).click();
	}

}
