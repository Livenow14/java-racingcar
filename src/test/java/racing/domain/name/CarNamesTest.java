package racing.domain.name;

import org.junit.jupiter.api.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThatCode;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarNamesTest {
    @DisplayName("쉼표도 구분된 문자열이 생성자의 인자로 들어오면 정상 실행된다")
    @Test
    void CarNames_쉼표로_구분된_문자열_받으면_객체가_생성된다() {
        String carNames = "pobi,brown";
        assertThatCode(() -> CarNames.of(carNames))
                .doesNotThrowAnyException();
    }

    @DisplayName("유효하지 않은 문자열이 들어오면 에러가 발생한다")
    @Test
    void CarNames_잘못된_이름이_포함된_문자열_받으면_예외가_발생한다() {
        String carNames = "pobi,bro.wn,";
        assertThatCode(() -> {
            CarNames.of(carNames);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 문자열이 들어오면 에러가 발생한다")
    @Test
    void CarNames_빈_문자열_에러가_발생한다() {
        String carNames = "";
        assertThatCode(() -> {
            CarNames.of(carNames);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 문자열이 들어오면 에러가 발생한다")
    @Test
    void CarNames_중복된_문자열_에러가_발생한다() {
        String carNames = "pobi,pobi,brown";
        assertThatCode(() -> {
            CarNames.of(carNames);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 중복될 수 없습니다.");
    }

    @Test
    @Order(1)
    void patternCacheTest() {
        String inputValue = "//:\n1;2;3";

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1_000_000; i++) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            Matcher matcher = pattern.matcher(inputValue);
        }
        System.out.println("Matcher가 for문의 스코프를 가질 = " + (System.currentTimeMillis() - start));
    }

    @Test
    @Order(2)
    void patternMatcherCacheTest() {
        String inputValue = "//:\n1;2;3";

        long start = System.currentTimeMillis();
        Matcher matcher;
        for (int i = 0; i < 1_000_000; i++) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            matcher = pattern.matcher(inputValue);
        }
        System.out.println("Matcher가 메소드의 스코프를 가질 때 = " + (System.currentTimeMillis() - start));
    }


    @Test
    @Order(3)
    void patternTest() {
        String inputValue = "//:\n1;2;3";

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            Matcher matcher = pattern.matcher(inputValue);
        }
        System.out.println("Pattern이 for문의 스코프를 가질 때 = " + (System.currentTimeMillis() - start));
    }

    @Test
    @Order(4)
    void patternCacheTest2() {
        String inputValue = "//:\n1;2;3";

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1_000_000; i++) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            Matcher matcher = pattern.matcher(inputValue);
        }
        System.out.println("Matcher가 for문의 스코프를 가질2= " + (System.currentTimeMillis() - start));
    }

    @Test
    @Order(5)
    void patternMatcherCacheTest2() {
        String inputValue = "//:\n1;2;3";

        long start = System.currentTimeMillis();
        Matcher matcher;
        for (int i = 0; i < 1_000_000; i++) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            matcher = pattern.matcher(inputValue);
        }
        System.out.println("Matcher가 메소드의 스코프를 가질 때2 = " + (System.currentTimeMillis() - start));
    }
}