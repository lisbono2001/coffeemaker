package edu.ncsu.csc326.coffeemaker;

import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class CoffeeMakerCucumber {

    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Given("Coffeemaker with recipes")
    public void coffeemaker_with_recipes() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        //Set up for recipe 1
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        coffeeMaker.addRecipe(recipe1);
    }

    @When("The customer order coffee with enough money.")
    public void the_customer_order_coffee_with_enough_money() {
        assertEquals(coffeeMaker.getRecipes()[0], recipe1);
    }

    @Then("The customer order recipe number: {int} and paid: {int} then the change equal to {int}.")
    public void the_customer_order_recipe_number_and_paid_then_the_change_equal_to(int recipe, int paid, int change) {
        assertEquals(change, coffeeMaker.makeCoffee(recipe, paid));
    }

    @When("The customer order coffee with not enough money.")
    public void the_customer_order_coffee_with_not_enough_money() {
        assertEquals(coffeeMaker.getRecipes()[0], recipe1);
    }

    @When("The customer order coffee with invalid recipe.")
    public void the_customer_order_coffee_with_invalid_recipe() {
        assertNull(coffeeMaker.getRecipes()[2]);
    }

    @Then("Then the amount of milk, sugar, coffee, and chocolate in inventory updated correctly")
    public void then_the_amount_of_milk_sugar_coffee_and_chocolate_in_inventory_updated() {
        int change = coffeeMaker.makeCoffee(0, 50);
        assertEquals("Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n",coffeeMaker.checkInventory());
    }

    @Then("Then the amount of milk, sugar, coffee, and chocolate in inventory won't updated")
    public void then_the_amount_of_milk_sugar_coffee_and_chocolate_in_inventory_wont_updated() {
        int change = coffeeMaker.makeCoffee(0, 30);
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n",coffeeMaker.checkInventory());
    }
}