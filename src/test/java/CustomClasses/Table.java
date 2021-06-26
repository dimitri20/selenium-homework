package CustomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Table {
    private WebDriver driver;
    private WebElement table_webElement;
    private int[] size = {0,0}; // size[0] => rows; size[1] => columns

    public Table(WebDriver driver, By table_selector){
        table_webElement = driver.findElement(table_selector);
        size[0] = getRows().size();
        size[1] = getColumns().size();
    }

    public int[] getSize(){
        return size;
    }

    public WebElement getTableWebElement(){
        return table_webElement;
    }

    public List<WebElement> getColumns() {
        return table_webElement.findElements(By.cssSelector("tr th"));
    }

    public List<WebElement> getRows() {
        return table_webElement.findElements(By.cssSelector("tbody tr"));
    }

    public ArrayList<List<WebElement>> getTable() {
        ArrayList<List<WebElement>> table = new ArrayList<List<WebElement>>();

        for (int j = 0; j < getRows().size(); j++){
            table.add(getRows().get(j).findElements(By.tagName("td")));
        }
        return table;
    }

    public void getItemPosition(){
        System.out.println(getRows().size());
        System.out.println(getColumns().size());
    }

    public WebElement getItem(int row, int column){
        return getTable().get(column).get(row);
    }

    public List<WebElement> getItemByText(String text){
        return table_webElement.findElements(By.xpath("//td[text()='"+ text +"']"));
    }

    public String getItemText(int row, int column){
        return getTable().get(column).get(row).getText();
    }

    public WebElement getLastNonEmptyElement(){
        WebElement lastNonEmptyElement = null;
        List<WebElement> tempList = getTable().get(size[0]-1);
        Collections.reverse(tempList);
        for(WebElement elem : tempList){
            if (!elem.getText().replace(" ", "").isEmpty()){
                lastNonEmptyElement = elem;
                break;
            }
        }

        return lastNonEmptyElement;
    }
}
