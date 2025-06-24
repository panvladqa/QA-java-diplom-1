package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class IngredientTests {
    private Ingredient ingredient;
    private final IngredientType expectedIngredientType = IngredientType.SAUCE;
    private final String expectedName = "sauceName";
    private final float expectedPrice = 50f;

    @Before
    public void setUp() {
        this.ingredient = new Ingredient(
                expectedIngredientType,
                expectedName,
                expectedPrice
        );
    }

    /**
     * Проверяет, что метод getPrice() возвращает корректную цену ингредиента
     */
    @Test
    public void testGetPriceWhenCalledReturnsCorrectPrice() {
        MatcherAssert.assertThat(
                "Цена ингредиента должна соответствовать значению, установленному в конструкторе",
                ingredient.getPrice(),
                equalTo(expectedPrice)
        );
    }

    /**
     * Проверяет, что метод getName() возвращает корректное название ингредиента
     */
    @Test
    public void testGetNameWhenCalledReturnsCorrectName() {
        MatcherAssert.assertThat(
                "Название ингредиента должно соответствовать значению, установленному в конструкторе",
                ingredient.getName(),
                equalTo(expectedName)
        );
    }

    /**
     * Проверяет, что метод getType() возвращает корректный тип ингредиента,
     */
    @Test
    public void testGetTypeWhenCalledReturnsCorrectType() {
        MatcherAssert.assertThat(
                "Тип ингредиента должен соответствовать значению, установленному в конструкторе",
                ingredient.getType(),
                equalTo(expectedIngredientType)
        );
    }
}