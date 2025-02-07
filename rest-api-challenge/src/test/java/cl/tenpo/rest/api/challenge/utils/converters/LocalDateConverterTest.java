package cl.tenpo.rest.api.challenge.utils.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class LocalDateConverterTest {

    private LocalDateConverter converter;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @BeforeEach
    void setUp() {
        converter = new LocalDateConverter(DATE_FORMAT);
    }

    @Test
    void shouldConvertValidStringToLocalDate() {
        String validDateString = "2025-02-07";
        LocalDate expectedDate = LocalDate.parse(validDateString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        LocalDate result = converter.convert(validDateString);

        assertEquals(expectedDate, result);
    }

    @Test
    void shouldReturnNullForNullInput() {
        assertNull(converter.convert(null));
    }

    @Test
    void shouldReturnNullForEmptyStringInput() {
        assertNull(converter.convert(""));
    }

    @Test
    void shouldThrowExceptionForInvalidFormat() {
        String invalidDateString = "07-02-2025"; // Incorrect format

        assertThrows(Exception.class, () -> converter.convert(invalidDateString));
    }
}