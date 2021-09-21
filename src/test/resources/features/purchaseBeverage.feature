Feature: Make beverage from the customer's order
  CoffeeMaker make beverage from customer order correctly and return change correctly.
  Or if purchase failed or unsuccessful (not enough money and ), return all customer money.

  Background:
    Given Coffeemaker with recipes

    Scenario: Customer purchase a beverage with paid amount more than beverage price.
      When The customer order coffee with enough money.
      Then The customer order recipe number: 0 and paid: 75 then the change equal to 25.

    Scenario: Customer purchase a beverage with paid amount equal to beverage price.
      When The customer order coffee with enough money.
      Then The customer order recipe number: 0 and paid: 50 then the change equal to 0.

    Scenario: Customer purchase a beverage with paid amount less than beverage price.
      When The customer order coffee with not enough money.
      Then The customer order recipe number: 0 and paid: 40 then the change equal to 40.

    Scenario: Customer purchase an invalid beverage.
      When The customer order coffee with invalid recipe.
      Then The customer order recipe number: 2 and paid: 50 then the change equal to 50.

    Scenario: Customer purchase a beverage successful and inventory updated.
      When The customer order coffee with enough money.
      Then Then the amount of milk, sugar, coffee, and chocolate in inventory updated correctly

    Scenario: Customer purchase a beverage failed and inventory stay the same.
      When The customer order coffee with not enough money.
      Then Then the amount of milk, sugar, coffee, and chocolate in inventory won't updated