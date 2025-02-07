package cl.tenpo.rest.api.challenge.utils.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class LocalDateTimeConverterTest {

    private LocalDateTimeConverter converter;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @BeforeEach
    void setUp() {
        converter = new LocalDateTimeConverter(DATE_FORMAT);
    }

    @Test
    void shouldConvertValidStringToLocalDateTime() {
        String validDateString = "2025-02-07 14:30:00";
        LocalDateTime expectedDate = LocalDateTime.parse(validDateString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        LocalDateTime result = converter.convert(validDateString);

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
        String invalidDateString = "07-02-2025 14:30:00"; // Incorrect format

        assertThrows(Exception.class, () -> converter.convert(invalidDateString));
    }
}