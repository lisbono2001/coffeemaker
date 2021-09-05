package edu.ncsu.csc326.coffeemaker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Unit tests for Recipe class.
 *
 * @author Theetouch Kasemarnontana
 */
public class RecipeTest {
    // Sample recipes to use in testing.
    private Recipe recipe;


    @Before
    public void setUp() throws RecipeException {
        recipe = new Recipe();
    }

    /**
     * Test Case ID : 1
     *
     * Given an empty recipe
     * When we set chocolate with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetChocolateTextException() throws RecipeException {
        recipe.setAmtChocolate("Number");
    }

    /**
     * Test Case ID : 2
     *
     * Given an empty recipe
     * When we set chocolate with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetChocolateNegativeException() throws RecipeException {
        recipe.setAmtChocolate("-1");
    }
    /**
     * Test Case ID : 3
     *
     * Given an empty recipe
     * When we set coffee with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetCoffeeTextException() throws RecipeException {
        recipe.setAmtCoffee("Number");
    }

    /**
     * Test Case ID : 4
     *
     * Given an empty recipe
     * When we set coffee with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetCoffeeNegativeException() throws RecipeException {
        recipe.setAmtCoffee("-1");
    }

    /**
     * Test Case ID : 5
     *
     * Given an empty recipe
     * When we set milk with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetMilkTextException() throws RecipeException {
        recipe.setAmtMilk("Number");
    }

    /**
     * Test Case ID : 6
     *
     * Given an empty recipe
     * When we set milk with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetMilkNegativeException() throws RecipeException {
        recipe.setAmtMilk("-1");
    }

    /**
     * Test Case ID : 7
     *
     * Given an empty recipe
     * When we set sugar with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetSugarTextException() throws RecipeException {
        recipe.setAmtSugar("Number");
    }

    /**
     * Test Case ID : 8
     *
     * Given an empty recipe
     * When we set sugar with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetSugarNegativeException() throws RecipeException {
        recipe.setAmtSugar("-1");
    }

    /**
     * Test Case ID : 9
     *
     * Given an empty recipe
     * When we set price with malformed value (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetPriceTextException() throws RecipeException {
        recipe.setPrice("Number");
    }

    /**
     * Test Case ID : 10
     *
     * Given an empty recipe
     * When we set price with malformed value (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testSetPriceNegativeException() throws RecipeException {
        recipe.setPrice("-1");
    }

    /**
     * Test Case ID : 11
     *
     * Initialize Recipe with valid values.
     *
     * @throws RecipeException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test
    public void testSetRecipeCorrectly() throws RecipeException {
        recipe.setName("Coffee");
        recipe.setAmtChocolate("0");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("1");
        recipe.setAmtSugar("1");
        recipe.setPrice("50");
    }


    /**
     * Test Case ID : 12
     *
     * Given an empty recipe
     * then we test on the same recipe
     * it's should return true if it's the same object
     *
     */
    @Test
    public void testEqualsSameObject() {
        assertTrue(recipe.equals(recipe));
    }

    /**
     * Test Case ID : 13
     *
     * Given an empty recipe
     * then we test it with null
     * it's should return false
     *
     */
    @Test
    public void testEqualsToNull() {
        assertFalse(recipe.equals(null));
    }

    /**
     * Test Case ID : 14
     *
     * Given an empty recipe
     * then we test it with another class
     * it's should return false
     *
     */
    @Test
    public void testEqualsToAnotherObject() {
        assertFalse(recipe.equals("String"));
    }

    /**
     * Test Case ID : 15
     *
     * Given an empty recipe
     * then we test it with another class
     * it's should return false
     *
     */
    @Test
    public void testNullNamedEqualsToAnotherNamedObject() {
        recipe.setName(null);
        Recipe recipe2 = new Recipe();
        recipe2.setName("Java");
        assertFalse(recipe.equals(recipe2));
    }

    /**
     * Test Case ID : 16
     *
     * Given an empty recipe
     * then we test it with another class
     * it's should return false
     *
     */
    @Test
    public void testNullNamedEqualsToAnotherNullNamedObject() {
        recipe.setName(null);
        Recipe recipe2 = new Recipe();
        recipe2.setName(null);
        assertFalse(recipe.equals(recipe2));
    }

    /**
     * Test Case ID : 17
     *
     * Given an empty named recipe
     * then we test it with another same name recipe
     * it's should return true
     *
     */
    @Test
    public void testOnlySameNameRecipe() {
        recipe.setName("Coff");
        Recipe recipe2 = new Recipe();
        recipe2.setName("Coff");
        assertTrue(recipe.equals(recipe2));
    }

    /**
     * Test Case ID : 18
     *
     * Given an empty recipe
     * then we create another recipe to test that
     * every recipe mustn't return same value on this method
     * but same recipe must return the same.
     *
     */
    @Test
    public void testHashCode() {
        Recipe recipe2 = new Recipe();
        assertNotEquals(recipe.hashCode(),recipe2.hashCode());
        assertEquals(recipe.hashCode(),recipe.hashCode());
    }

    /**
     * Test Case ID : 19
     *
     * Given an empty recipe
     * then we set its name with null
     * Then we should get a recipe exception
     *
     * @throws RecipeException  if there was an error parsing the string to string
     */
    @Test
    public void testSetNameNull() {
        recipe.setName(null);
    }
}