package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TokopediaPages extends PageObject{
    //WEB PRE-CONDITION
    @FindBy(xpath = "//img[@alt='tokopedia-logo']")
    WebElement welcomeLogo;

    public void welcomeTokopedia() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        waitFor(welcomeLogo).waitUntilVisible();
        Assert.assertTrue(welcomeLogo.isDisplayed());
    }

    @FindBy(css = "[placeholder='Cari di Tokopedia']")
    WebElement searchBoxTokped;

    public void setSearchBoxTokped(String items){
        searchBoxTokped.sendKeys(items);
        searchBoxTokped.sendKeys(Keys.ENTER);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        for (int i = 0; i < 5; i++) {  // Scroll down multiple times to load more products
            js.executeScript("window.scrollBy(0,500)");
            try {
                Thread.sleep(2000);  // Wait for products to load
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FindBy(xpath = "//a[contains(.,'24%Ad(RESMI) iPhone 15 Pro 128 256 512 1TB Titanium Dual Nano RESMI IBOX  - PROM')]")
    List<WebElement> productContainers;

    public List<Product> fetchProductDetails() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(productContainers));
        if (productContainers.isEmpty()) {
            System.out.println("No products found.");
            System.out.println(getDriver().getPageSource());  // Print page source for debugging
        }

        List<Product> products = new ArrayList<>();

        for (WebElement productContainer : productContainers) {
            try {
                WebElement productNameElement = productContainer.findElement(By.xpath("//span[contains(.,'(RESMI) iPhone 15 Pro 128 256 512 1TB Titanium Dual Nano RESMI IBOX  - PROMO 128')]"));
                WebElement productPriceElement = productContainer.findElement(By.xpath("//div[@class='css-rjanld']//div[@class='css-jza1fo']/div[1]//div[.='Rp18.130.000']"));
                WebElement productLinkElement = productContainer.findElement(By.xpath("//a[contains(.,'24%Ad(RESMI) iPhone 15 Pro 128 256 512 1TB Titanium Dual Nano RESMI IBOX  - PROM')]"));

                String name = productNameElement.getText();
                String price = productPriceElement.getText().replace("Rp", "").replace(".", "").replace(",", "").trim();
                String url = productLinkElement.getAttribute("href");

                products.add(new Product("Tokopedia", name, Integer.parseInt(price), url));
            } catch (Exception e) {
                System.out.println("Missing elements in one of the product containers: " + e.getMessage());
            }
        }
        return products;
    }

}
