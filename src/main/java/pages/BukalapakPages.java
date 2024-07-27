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

public class BukalapakPages extends PageObject {
    @FindBy(xpath = "//img[@src='https://assets.bukalapak.com/sigil/bukalapak-logo-primary.svg']")
    WebElement welcomeLogo;

    public void welcomeBukalapak() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        waitFor(welcomeLogo).waitUntilVisible();
        Assert.assertTrue(welcomeLogo.isDisplayed());
    }

    @FindBy(id = "v-omnisearch__input")
    WebElement searchBoxBukalapak;


//    @FindBy(css = "[alt='Apple Iphone 15 Pro Max 256GB 512GB 1TB Black Blue Natural Titanium - Garansi Resmi IBOX']")
//    WebElement getItems;

    public void setSearchBoxBukalapak(String items){
        searchBoxBukalapak.sendKeys(items);
        searchBoxBukalapak.sendKeys(Keys.ENTER);
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

    @FindBy(xpath = "//div[@class='bl-flex-item bl-product-list-wrapper']//div[3]/div[@class='bl-flex-container flex-wrap is-gutter-16']/div[7]//div[@class='bl-product-card-new__wrapper']")
    List<WebElement> productContainers;

    public List<Product> fetchProductDetails() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(productContainers));
        if (productContainers.isEmpty()) {
            System.out.println("No products found.");
            System.out.println(getDriver().getPageSource());
        }

        List<Product> products = new ArrayList<>();

        for (WebElement productContainer : productContainers) {
            try {
                WebElement productNameElement = productContainer.findElement(By.cssSelector("[title='Apple Iphone 15 Pro Garansi Resmi']"));
                WebElement productPriceElement = productContainer.findElement(By.cssSelector("[title='18.750.000']"));
                WebElement productLinkElement = productContainer.findElement(By.cssSelector("[title='Apple Iphone 15 Pro Garansi Resmi'] > .bl-link"));

                String name = productNameElement.getText();
                String price = productPriceElement.getText().replace("Rp", "").replace(".", "").replace(",", "").trim();
                String url = productLinkElement.getAttribute("href");

                products.add(new Product("Bukalapak", name, Integer.parseInt(price), url));
            } catch (Exception e) {
                System.out.println("Missing elements in one of the product containers: " + e.getMessage());
            }
        }
        return products;
    }

}
