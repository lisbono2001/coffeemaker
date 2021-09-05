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
 * Unit tests for Inventory class.
 *
 * @author Theetouch Kasemarnontana
 */
public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setUp() {
        inventory = new Inventory();
    }

    /**
     * Test Case ID : 1
     *
     * Given an Inventory with default value
     * When we add chocolate with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddChocolateTextException() throws InventoryException {
        inventory.addChocolate("Oleoelele");
    }

    /**
     * Test Case ID : 2
     *
     * Given a coffee maker with the default inventory
     * When we add chocolate with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddChocolateNegativeNumberException() throws InventoryException {
        inventory.addChocolate("-1");
    }

    /**
     * Test Case ID : 3
     *
     * Given an Inventory with default value
     * When we add coffee with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddCoffeeTextException() throws InventoryException {
        inventory.addCoffee("Oleoelele");
    }

    /**
     * Test Case ID : 4
     *
     * Given a coffee maker with the default inventory
     * When we add coffee with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddCoffeeNegativeNumberException() throws InventoryException {
        inventory.addCoffee("-1");
    }

    /**
     * Test Case ID : 5
     *
     * Given an Inventory with default value
     * When we add milk with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddMilkTextException() throws InventoryException {
        inventory.addMilk("Oleoelele");
    }

    /**
     * Test Case ID : 6
     *
     * Given a coffee maker with the default inventory
     * When we add milk with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddMilkNegativeNumberException() throws InventoryException {
        inventory.addMilk("-1");
    }

    /**
     * Test Case ID : 7
     *
     * Given an Inventory with default value
     * When we add sugar with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddSugarTextException() throws InventoryException {
        inventory.addSugar("Oleoelele");
    }

    /**
     * Test Case ID : 8
     *
     * Given an Inventory with default value
     * When we add sugar with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test (expected = InventoryException.class)
    public void testAddSugarNegativeNumberException() throws InventoryException {
        inventory.addSugar("-1");
    }

    /**
     * Test Case ID : 9
     *
     * Given an Inventory with default value
     * When we set chocolate with negative quantities
     * Then we should get an inventory exception
     *
     */
    @Test (expected = InventoryException.class)
    public void testSetChocolateNegativeNumber() throws InventoryException {
        inventory.setChocolate(-1);
    }
    /**
     * Test Case ID : 10
     *
     * Given an Inventory with default value
     * When we add sugar with negative quantities
     * Then we should get an inventory exception
     *
     */
    @Test (expected = InventoryException.class)
    public void testSetCoffeeNegativeNumber() throws InventoryException {
        inventory.setCoffee(-1);
    }
    /**
     * Test Case ID : 11
     *
     * Given an Inventory with default value
     * When we set milk with negative quantities
     * Then we should get an inventory exception
     *
     */
    @Test (expected = InventoryException.class)
    public void testSetMilkNegativeNumber() throws InventoryException {
        inventory.setMilk(-1);
    }


    /**
     * Test Case ID : 12
     *
     * Given an Inventory with default value
     * When we set sugar with negative quantities
     * Then we should get an inventory exception
     *
     */
    @Test (expected = InventoryException.class)
    public void testSetSugarNegativeNumber() throws InventoryException {
        inventory.setSugar(-1);
    }
}