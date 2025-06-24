package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class BunTests {

    /**
     * Проверяет, что метод getName() возвращает корректное название булочки
     */
    @Test
    public void getNameWhenBunCreatedReturnsCorrectName() {
        String bunName = "bunName";
        Bun bun = new Bun(bunName, 100);

        MatcherAssert.assertThat(
                "Ожидалось, что название булочки совпадает с переданным в конструктор",
                bun.getName(),
                equalTo(bunName)
        );
    }

    /**
     * Проверяет, что метод getPrice() возвращает корректную цену булочки
     */
    @Test
    public void getPriceWhenBunCreatedReturnsCorrectPrice() {
        float bunPrice = 100;
        Bun bun = new Bun("bunName", bunPrice);

        MatcherAssert.assertThat(
                "Ожидалось, что цена булочки совпадает с переданной в конструктор",
                bun.getPrice(),
                equalTo(bunPrice)
        );
    }
}