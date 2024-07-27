package steps;

import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import pages.Product;
import pages.TokopediaPages;
import pages.BukalapakPages;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class ProductComparisonSteps {
    @Steps
    TokopediaPages tokopediaPages;

    @Steps
    BukalapakPages bukalapakPages;

    List<Product> allProducts = new ArrayList<>();

    @Given("I am on the Tokopedia homepage")
    public void i_am_on_the_tokopedia_homepage() {
        String tokopediaUrl = System.getProperty("webdriver.base.url2", "https://www.tokopedia.com/");
        getDriver().get(tokopediaUrl);
        tokopediaPages.welcomeTokopedia();
    }

    @When("I search for {string} on Tokopedia")
    public void i_search_for_on_tokopedia(String item) {
        tokopediaPages.setSearchBoxTokped(item);
        List<Product> tokopediaProducts = tokopediaPages.fetchProductDetails();
        allProducts.addAll(tokopediaProducts);
    }

    @Given("I am on the Bukalapak homepage")
    public void i_am_on_the_bukalapak_homepage() {
        String bukalapakUrl = System.getProperty("webdriver.base.url1", "https://www.bukalapak.com/");
        getDriver().get(bukalapakUrl);
        bukalapakPages.welcomeBukalapak();
    }

    @When("I search for {string} on Bukalapak")
    public void i_search_for_on_bukalapak(String item) {
        bukalapakPages.setSearchBoxBukalapak(item);
        List<Product> bukalapakProducts = bukalapakPages.fetchProductDetails();
        allProducts.addAll(bukalapakProducts);
    }

    @Then("I should see a combined list of products from both websites with their name, price, and URL sorted by ascending order of price")
    public void i_should_see_a_combined_list_of_products_from_both_websites_with_their_name_price_and_url_sorted_by_ascending_order_of_price() {
        Assert.assertFalse("Product list is empty", allProducts.isEmpty());

        Collections.sort(allProducts, Comparator.comparingInt(Product::getPrice));

        for (Product product : allProducts) {
            System.out.println(product);
            Assert.assertNotNull(product.getName());
            Assert.assertTrue(product.getPrice() > 0);
            Assert.assertNotNull(product.getUrl());
        }
    }
}
