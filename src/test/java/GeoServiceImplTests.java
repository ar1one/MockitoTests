import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTests {
    GeoServiceImpl geoService;

    @BeforeEach
    public void beforeEach() {
        geoService = new GeoServiceImpl();
    }

    @AfterEach
    public void afterEach() {
        geoService = null;
    }

    @ParameterizedTest
    @MethodSource("methodSource")
    public void byIpTest(String argument, Location expected) {
        Location location = geoService.byIp(argument);
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getBuiling(), location.getBuiling()),
                () -> Assertions.assertEquals(expected.getCountry(), location.getCountry()),
                () -> Assertions.assertEquals(expected.getCity(), location.getCity()),
                () -> Assertions.assertEquals(expected.getStreet(), location.getStreet())
        );
    }

    public static Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0))
        );
    }

    @Test
    public void byCoordinatesTest() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(1, 1));
    }
}
