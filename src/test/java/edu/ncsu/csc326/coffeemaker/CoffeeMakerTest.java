/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Sarah Heckman & Theetouch Kasemarnontana
 */
public class CoffeeMakerTest {

	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;

	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	private CoffeeMaker coffeeMakerWithRecipeBookMock;
	private RecipeBook recipeBookMock;
	private Recipe[] recipeArray;
	private Inventory inventory;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker}
	 * object we wish to test.
	 *
	 * @throws RecipeException  if there was an error parsing the ingredient
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();

		//Set up for coffeeMakerWithRecipeBookMock
		inventory = new Inventory();
		recipeBookMock = mock(RecipeBook.class);
		coffeeMakerWithRecipeBookMock = new CoffeeMaker(recipeBookMock, inventory);

		//Set up for recipe 1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		//Set up for recipe 2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");

		//Set up for recipe 3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");

		//Set up for recipe 4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("20");
		recipe4.setAmtCoffee("20");
		recipe4.setAmtMilk("20");
		recipe4.setAmtSugar("20");
		recipe4.setPrice("65");

		recipeArray = new Recipe[]{recipe1,recipe4};
		when(recipeBookMock.getRecipes()).thenReturn(recipeArray);
	}

	/**
	 * Test Case ID : 1
	 */
	@Test
	public void testWaitingState() {
	}

	/**
	 * Test Case ID : 2
	 *
	 * Given a coffee maker with the default inventory
	 * When we add a recipe
	 * Then we do not get an exception trying to add the recipe
	 */
	@Test
	public void testAddRecipe() {
		coffeeMaker.addRecipe(recipe1);
	}

	/**
	 * Test Case ID : 3
	 *
	 * Given a coffee maker with the default inventory
	 * When we add a recipe
	 * Then we do not get an exception trying to add the recipe
	 * and the recipe we added is successfully and correctly added
	 */
	@Test
	public void testAddRecipeCorrectness() {
		//create a new array and add 'recipe1' to the first element
		//Number of recipes in each coffee maker = 4
		Recipe[] testrecipeBook = new Recipe[4];
		testrecipeBook[0] = recipe1;
		//also do that for coffee maker
		coffeeMaker.addRecipe(recipe1);
		//check that recipe1 is the first recipe
		assertEquals(recipe1, coffeeMaker.getRecipes()[0]);
		//check that no next recipe added
		assertNull(coffeeMaker.getRecipes()[1]);
		//check that both recipeBook is equal
		assertArrayEquals(testrecipeBook,coffeeMaker.getRecipes());
	}

	/**
	 * Test Case ID : 4
	 *
	 * Given a coffee maker with the default inventory
	 * When we delete a recipe
	 * Then we do not get an exception trying to delete the recipe
	 */
	@Test
	public void testDeleteRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.deleteRecipe(0);
	}

	/**
	 * Test Case ID : 5
	 *
	 * Given a coffee maker with the default inventory
	 * When we delete a recipe
	 * Then we do not get an exception trying to delete the recipe
	 */
	@Test
	public void testDeleteRecipeCorrectness() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.deleteRecipe(0);
		assertNotEquals(recipe1, coffeeMaker.getRecipes()[0]);
		assertNull(coffeeMaker.getRecipes()[0]);
	}

	/**
	 * Test Case ID : 6
	 *
	 * Given a coffee maker with the default inventory
	 * When we delete a recipe
	 * Then we do not get an exception trying to delete the recipe
	 */
	@Test
	public void testDeleteRecipeSorting() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.deleteRecipe(0);
		assertEquals(recipe2, coffeeMaker.getRecipes()[1]);
	}

	/**
	 * Test Case ID : 7
	 *
	 * Given a coffee maker with the default inventory
	 * When we delete an invalid recipe (larger)
	 * Then we do get a null (delete failed)
	 */
	@Test
	public void testDeleteLargerRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertNull(coffeeMaker.deleteRecipe(1));
	}

	/**
	 * Test Case ID : 8
	 *
	 * Given a coffee maker with the default inventory
	 * When we delete an invalid recipe (smaller)
	 * Then we do get a null (delete failed)
	 */
	@Test
	public void testDeleteSmallerRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertNull(coffeeMaker.deleteRecipe(-1));
	}

	/**
	 * Test Case ID : 9
	 *
	 * Given a coffee maker with the default inventory
	 * When we delete a recipe
	 * Then we do not get an exception trying to delete the recipe
	 */
	@Test
	public void testEditRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.editRecipe(0,recipe2);
	}

	/**
	 * Test Case ID : 10
	 *
	 * Given a coffee maker with the default inventory
	 * When we edit a recipe
	 * Then we do not get an exception trying to edit the recipe
	 */
	@Test
	public void testEditRecipeCorrectness() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.editRecipe(0,recipe2);
		assertNotEquals(recipe1, coffeeMaker.getRecipes()[0]);
		assertEquals(recipe2, coffeeMaker.getRecipes()[0]);
	}

	/**
	 * Test Case ID : 11
	 *
	 * Given a coffee maker with the default inventory
	 * When we edit a recipe
	 * Then we do not get an exception trying to edit the recipe
	 */
	@Test
	public void testEditLargerRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertNull(coffeeMaker.editRecipe(1,recipe2));
	}

	/**
	 * Test Case ID : 12
	 *
	 * Given a coffee maker with the default inventory
	 * When we edit a recipe
	 * Then we do not get an exception trying to edit the recipe
	 */
	@Test
	public void testEditSmallerRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertNull(coffeeMaker.editRecipe(-1, recipe2));
	}

	/**
	 * Test Case ID : 13
	 *
	 * Given an inventory with specific value
	 * Then the expected output of showing inventory is correct
	 * and so on checkInventory function of coffee maker (since the input values
	 * is default of a coffee maker)
	 */
	@Test
	public void testCheckInventory() {
		Inventory inventory = new Inventory();
		inventory.setMilk(15);
		inventory.setChocolate(15);
		inventory.setCoffee(15);
		inventory.setSugar(15);
		assertEquals(
				"Coffee: 15\n" +
						"Milk: 15\n" +
						"Sugar: 15\n" +
						"Chocolate: 15\n" , inventory.toString()
		);
		assertEquals(inventory.toString(), coffeeMaker.checkInventory());
	}

	/**
	 * Test Case ID : 14
	 *
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}

	/**
	 * Test Case ID : 15
	 *
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventoryCorrectness() throws InventoryException {
		String amtCoffee = "4";
		String amtMilk= "7";
		String amtSugar = "0";
		String amtChocolate = "9";

		Inventory inventory = new Inventory();
		inventory.addCoffee(amtCoffee);
		inventory.addMilk(amtMilk);
		inventory.addSugar(amtSugar);
		inventory.addChocolate(amtChocolate);

		coffeeMaker.addInventory(amtCoffee,amtMilk,amtSugar,amtChocolate);
		assertEquals(inventory.toString(), coffeeMaker.checkInventory());
	}

	/**
	 * Test Case ID : 16
	 *
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddCoffeeInventoryException() throws InventoryException {
		coffeeMaker.addInventory("-4", "1", "1", "3");
	}

	/**
	 * Test Case ID : 17
	 *
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddMilkInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "1", "3");
	}

	/**
	 * Test Case ID : 18
	 *
	 * Given a coffee maker with the default inventory
	 * When we add with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddSugarInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}

	/**
	 * Test Case ID : 19
	 *
	 * Given a coffee maker with the default inventory
	 * When we add chocolate inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddChocolateInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "1", "2", "-1");
	}

	/**
	 * Test Case ID : 20
	 *
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}

	/**
	 * Test Case ID : 21
	 *
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffeeWithChange() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

	/**
	 * Test Case ID : 22
	 *
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying equal to
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffeeWithNoChange() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(0, coffeeMaker.makeCoffee(0, 50));
	}

	/**
	 * Test Case ID : 23
	 *
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying less than
	 * 		the coffee costs
	 * Then we get all cash back (purchase failed).
	 */
	@Test
	public void testMakeCoffeeNotEnoughBalance() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(40, coffeeMaker.makeCoffee(0, 40));
	}

	/**
	 * Test Case ID : 24
	 *
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting an invalid recipe and paying more than or equal
	 * 		the coffee costs
	 * Then we get all cash back (purchase failed).
	 *
	 */
	@Test(expected = RecipeException.class)
	public void testMakeCoffeeLargerRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(50, coffeeMaker.makeCoffee(1, 50));
	}

	/**
	 * Test Case ID : 25
	 *
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting an invalid recipe and paying more than or equal
	 * 		the coffee costs
	 * Then we get all cash back (purchase failed).
	 *
	 */
	@Test(expected = RecipeException.class)
	public void testMakeCoffeeSmallerRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(50, coffeeMaker.makeCoffee(-1, 50));
	}

	/**
	 * Test Case ID : 26
	 *
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting a valid recipe and paying more than or equal
	 * 		the coffee costs but the selecting recipe is out of stock
	 * Then we get all cash back (purchase failed).
	 *
	 */
	@Test(expected = RecipeException.class)
	public void testMakeCoffeeOutOfInventory() {
		coffeeMaker.addRecipe(recipe4);
		assertEquals(70, coffeeMaker.makeCoffee(0, 70));
	}

	/**
	 * Test Case ID : 27
	 *
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting a valid recipe and paying more than or equal
	 * 		the coffee costs
	 * Then the inventory updated correctly.
	 *
	 */
	@Test(expected = RecipeException.class)
	public void testInventoryMakeCoffeeSuccess() {
		coffeeMaker.addRecipe(recipe1);
		int change = coffeeMaker.makeCoffee(0, 30);
		assertEquals("Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n", coffeeMaker.checkInventory());
	}

	/**
	 * Test Case ID : 28
	 *
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting a valid recipe and paying more than or equal
	 * 		the coffee costs but the selecting recipe is out of stock
	 * Then the inventory won't be updated (purchase failed)
	 *
	 */
	@Test(expected = RecipeException.class)
	public void testInventoryMakeCoffeeFail() {
		coffeeMaker.addRecipe(recipe4);
		int change = coffeeMaker.makeCoffee(1, 30);
		assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", coffeeMaker.checkInventory());
	}

	/**
	 * Test Case ID : 29 (Mock RecipeBook)
	 *
	 * Given a coffee maker with Mock RecipeBook with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void mockTestMakeCoffeeWithChange() {
		verify(recipeBookMock.getRecipes());
		assertEquals(25, coffeeMakerWithRecipeBookMock.makeCoffee(0, 75));
	}

	/**
	 * Test Case ID : 30 (Mock RecipeBook)
	 *
	 * Given a coffee maker with Mock RecipeBook with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying equal to
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void mockTestMakeCoffeeWithNoChange() {
		verify(recipeBookMock.getRecipes());
		assertEquals(0, coffeeMakerWithRecipeBookMock.makeCoffee(0, 50));
	}

	/**
	 * Test Case ID : 31 (Mock RecipeBook)
	 *
	 * Given a coffee maker with Mock RecipeBook with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying less than
	 * 		the coffee costs
	 * Then we get all cash back (purchase failed).
	 */
	@Test
	public void mockTestMakeCoffeeNotEnoughBalance() {
		verify(recipeBookMock.getRecipes());
		assertEquals(40, coffeeMakerWithRecipeBookMock.makeCoffee(0, 40));
	}

	/**
	 * Test Case ID : 32 (Mock RecipeBook)
	 *
	 * Given a coffee maker with Mock RecipeBook with one valid recipe
	 * When we make coffee, selecting an invalid recipe and paying more than or equal
	 * 		the coffee costs
	 * Then we get all cash back (purchase failed).
	 *
	 */
	@Test(expected = RecipeException.class)
	public void mockTestMakeCoffeeLargerRecipe() {
		verify(recipeBookMock.getRecipes());
		assertEquals(50, coffeeMakerWithRecipeBookMock.makeCoffee(1, 50));
	}

	/**
	 * Test Case ID : 33 (Mock RecipeBook)
	 *
	 * Given a coffee maker with Mock RecipeBook with one valid recipe
	 * When we make coffee, selecting an invalid recipe and paying more than or equal
	 * 		the coffee costs
	 * Then we get all cash back (purchase failed).
	 *
	 */
	@Test(expected = RecipeException.class)
	public void mockTestMakeCoffeeSmallerRecipe() {
		verify(recipeBookMock.getRecipes());
		assertEquals(50, coffeeMakerWithRecipeBookMock.makeCoffee(-1, 50));
	}

	/**
	 * Test Case ID : 34 (Mock RecipeBook)
	 *
	 * Given a coffee maker with Mock RecipeBook with one valid recipe
	 * When we make coffee, selecting a valid recipe and paying more than or equal
	 * 		the coffee costs but the selecting recipe is out of stock
	 * Then we get all cash back (purchase failed).
	 *
	 */
	@Test(expected = RecipeException.class)
	public void mockTestMockMakeCoffeeOutOfInventory() {
		verify(recipeBookMock.getRecipes());
		assertEquals(70, coffeeMakerWithRecipeBookMock.makeCoffee(1, 70));
	}

	/**
	 * Test Case ID : 35 (Mock RecipeBook)
	 *
	 * Given a coffee maker with Mock RecipeBook with one valid recipe
	 * When we make coffee, selecting a valid recipe and paying more than or equal
	 * 		the coffee costs
	 * Then the inventory updated correctly.
	 *
	 */
	@Test(expected = RecipeException.class)
	public void mockTestInventoryMakeCoffeeSuccess() {
		verify(recipeBookMock.getRecipes());
		int change = coffeeMakerWithRecipeBookMock.makeCoffee(0, 30);
		assertEquals("Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n", coffeeMakerWithRecipeBookMock.checkInventory());
	}

	/**
	 * Test Case ID : 36 (Mock RecipeBook)
	 *
	 * Given a coffee maker with Mock RecipeBook with one valid recipe
	 * When we make coffee, selecting a valid recipe and paying more than or equal
	 * 		the coffee costs but the selecting recipe is out of stock
	 * Then the inventory won't be updated (purchase failed)
	 *
	 */
	@Test(expected = RecipeException.class)
	public void mockTestInventoryMakeCoffeeFail() {
		verify(recipeBookMock.getRecipes());
		int change = coffeeMakerWithRecipeBookMock.makeCoffee(1, 30);
		assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", coffeeMakerWithRecipeBookMock.checkInventory());
	}
}