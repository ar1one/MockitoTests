import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceImplTests {
    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @ParameterizedTest
    @MethodSource("methodSource")
    public void localTest(Country country, String expected) {
        String result = localizationService.locale(country);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> methodSource() {
        return Stream.of(Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome")
        );
    }

}
