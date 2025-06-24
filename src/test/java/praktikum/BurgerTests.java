package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    @Mock
    private Ingredient ingredient2;

    /**
     * Проверяет корректность установки булочек в бургер
     */
    @Test
    public void testSetBunsWhenCalledSetsBunsCorrectly() {
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("bunName");
        burger.setBuns(bun);

        MatcherAssert.assertThat(
                "Название булочки в бургере должно совпадать с установленной",
                bun.getName(),
                equalTo(burger.bun.getName())
        );
    }

    /**
     * Проверяет корректность добавления ингредиента в бургер
     */
    @Test
    public void testAddIngredientWhenCalledAddsIngredientToBurger() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        assertTrue(
                "Ингредиент должен присутствовать в бургере после добавления",
                burger.ingredients.contains(ingredient)
        );
    }

    /**
     * Проверяет корректность перемещения ингредиента в списке
     */
    @Test
    public void testMoveIngredientWhenCalledMovesIngredientToNewPosition() {
        Mockito.when(ingredient.getName()).thenReturn("ingredientName");

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        int originalIndex = burger.ingredients.indexOf(ingredient);
        int newIndex = burger.ingredients.indexOf(ingredient2);

        burger.moveIngredient(originalIndex, newIndex);

        MatcherAssert.assertThat(
                "Ингредиент должен быть перемещен на новую позицию",
                ingredient.getName(),
                equalTo(burger.ingredients.get(newIndex).getName())
        );
    }

    /**
     * Проверяет корректность удаления ингредиента из бургера
     */
    @Test
    public void testRemoveIngredientWhenCalledRemovesIngredientFromBurger() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        int index = burger.ingredients.indexOf(ingredient);
        burger.removeIngredient(index);

        assertFalse(
                "Ингредиент должен отсутствовать в бургере после удаления",
                burger.ingredients.contains(ingredient)
        );
    }

    /**
     * Проверяет корректность расчета цены бургера
     */
    @Test
    public void testGetPriceWhenCalledReturnsCorrectTotalPrice() {
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(250f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float expectedPrice = 2 * 100f + 250f; // Булочки x2 + ингредиент
        MatcherAssert.assertThat(
                "Общая цена бургера должна быть рассчитана корректно",
                expectedPrice,
                equalTo(burger.getPrice())
        );
    }

    /**
     * Проверяет корректность генерации рецепта бургера
     */
    @Test
    public void testGetReceiptWhenCalledReturnsProperlyFormattedReceipt() {
        Mockito.when(bun.getName()).thenReturn("bunName");
        Mockito.when(bun.getPrice()).thenReturn(80f);
        Mockito.when(ingredient.getName()).thenReturn("ingredientName");
        Mockito.when(ingredient.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);

        // Используем System.lineSeparator() для кросс-платформенной совместимости
        String lineSeparator = System.lineSeparator();
        String expectedReceipt =
                "(==== bunName ====)" + lineSeparator +
                        "= filling ingredientName =" + lineSeparator +
                        "(==== bunName ====)" + lineSeparator +
                        lineSeparator +
                        "Price: 260,000000" + lineSeparator;

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String normalizedExpected = expectedReceipt.replace("\r\n", "\n").replace("\r", "\n");
        String normalizedActual = burger.getReceipt().replace("\r\n", "\n").replace("\r", "\n");

        MatcherAssert.assertThat(
                "Сгенерированный рецепт должен соответствовать ожидаемому формату",
                normalizedActual,
                equalTo(normalizedExpected)
        );
    }
}