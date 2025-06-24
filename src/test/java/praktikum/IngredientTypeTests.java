package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Тестирует корректность работы перечисления IngredientType
 */
@RunWith(Parameterized.class)
public class IngredientTypeTests {
    private final String expectedTypeName;

    /**
     * Параметры для тестирования всех возможных типов ингредиентов
     */
    @Parameterized.Parameters(name = "Тестируемый тип ингредиента: {0}")
    public static Object[][] ingredientTypeParameters() {
        return new Object[][] {
                {"SAUCE"},
                {"FILLING"}
        };
    }

    public IngredientTypeTests(String expectedTypeName) {
        this.expectedTypeName = expectedTypeName;
    }

    /**
     * Проверяет, что все объявленные типы ингредиентов корректно определены в перечислении
     */
    @Test
    public void testValueOfForAllDeclaredTypesShouldReturnCorrectEnumValue() {
        MatcherAssert.assertThat(
                "Тип ингредиента " + expectedTypeName + " должен быть корректно определен в перечислении",
                IngredientType.valueOf(expectedTypeName).name(),
                equalTo(expectedTypeName)
        );
    }
}