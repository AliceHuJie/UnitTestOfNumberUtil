import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberUtilsTest {

    @Ignore
    @Test
    public void test_IsCreatable_should_return_false_when_str_is_null() {
        assertFalse(NumberUtils.isCreatable(null));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_str_is_empty() {
        assertFalse(NumberUtils.isCreatable(""));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_check_hex_and_str_is_0x() {

        assertFalse(NumberUtils.isCreatable("0x"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_check_hex_and_str_is_0X() {
        assertFalse(NumberUtils.isCreatable("0X"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_check_hex_but_contain_invalid_char() {
        //when leading with 0x, 0-9 A-F a-f is valid
        assertFalse(NumberUtils.isCreatable("0X/")); //  '/' is less than 0
        assertFalse(NumberUtils.isCreatable("0X:")); //  ':' is greater than 9
        assertFalse(NumberUtils.isCreatable("0X@")); //  '@' is less than A
        assertFalse(NumberUtils.isCreatable("0XG")); //  'G' is greater than F
        assertFalse(NumberUtils.isCreatable("0X`")); //  '`' is less than a
        assertFalse(NumberUtils.isCreatable("0Xg")); //  'g' is greater than f
    }

    @Test
    public void test_IsCreatable_should_return_true_when_check_hex_success() {
        assertTrue(NumberUtils.isCreatable("0X12Aa")); //  char in : 0-9 or A-F or a-f
    }

    @Test
    public void test_IsCreatable_should_return_false_when_octal_contain_invalid_char() {
        // for octal, 0-7 is valid
        assertFalse(NumberUtils.isCreatable("0/")); //  '/' is less than 0
        assertFalse(NumberUtils.isCreatable("08")); //  '8' is greater than 7
    }

    @Test
    public void test_IsCreatable_should_return_true_when_check_octal_success() {
        assertTrue(NumberUtils.isCreatable("01234567")); // leading 0 and is octal
    }

    @Test
    public void test_IsCreatable_should_return_false_when_digit_has_two_points() {
        assertFalse(NumberUtils.isCreatable("1.2.3"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_exp_before_point() {
        assertFalse(NumberUtils.isCreatable("4E.1"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_double_exp() {
        assertFalse(NumberUtils.isCreatable("12eE2"));
        assertFalse(NumberUtils.isCreatable("12EE2"));
        assertFalse(NumberUtils.isCreatable("12ee2"));
        assertFalse(NumberUtils.isCreatable("12Ee2"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_no_digit_before_exp() {
        assertFalse(NumberUtils.isCreatable("E12"));
        assertFalse(NumberUtils.isCreatable("e12"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_sign_is_not_following_with_exp() {
        assertFalse(NumberUtils.isCreatable("12+3"));
        assertFalse(NumberUtils.isCreatable("12-3"));
    }

    @Test
    public void test_IsCreatable_should_return_true_when_sign_is_following_with_exp_and_digit_is_valid() {
        assertTrue(NumberUtils.isCreatable("12e+3"));
        assertTrue(NumberUtils.isCreatable("12e-3"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_str_contains_invalid_char() {
        // for digit, char not in "0-9 eE +- ." is invalid
        assertFalse(NumberUtils.isCreatable("12*1")); // '*' is less than +
        assertFalse(NumberUtils.isCreatable("12,1")); // ',' is greater than + and less than - .
        assertFalse(NumberUtils.isCreatable("12/1")); // '/' is less than 0
        assertFalse(NumberUtils.isCreatable("12:1")); // ':' is greater than 9
        assertFalse(NumberUtils.isCreatable("12D1")); // 'D' is less than E
        assertFalse(NumberUtils.isCreatable("12F1")); // 'F' is greater E
        assertFalse(NumberUtils.isCreatable("12d1")); // 'd' is less than e
        assertFalse(NumberUtils.isCreatable("12f1")); // 'f' is greater than e
    }

    @Test
    public void test_IsCreatable_should_return_true_when_digit_end_with_digit_and_is_valid() {
        assertTrue(NumberUtils.isCreatable("1235"));
    }

    @Test
    public void test_IsCreatable_should_return_true_when_digit_end_with_digit_and_contain_sign_and_is_valid() {
        assertTrue(NumberUtils.isCreatable("+1"));
        assertTrue(NumberUtils.isCreatable("-1"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_digit_end_with_exp() {
        assertFalse(NumberUtils.isCreatable("12e"));
        assertFalse(NumberUtils.isCreatable("12E"));
    }


    @Test
    public void test_IsCreatable_should_return_true_when_digit_is_valid_and_ends_with_point() {
        assertTrue(NumberUtils.isCreatable("123."));
    }

    @Test
    public void test_IsCreatable_should_return_true_when_str_ends_with_point_but_no_digit() {
        assertFalse(NumberUtils.isCreatable("."));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_digit_ends_with_point_but_already_has_point() {
        assertFalse(NumberUtils.isCreatable("123.."));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_digit_ends_with_point_but_already_has_exp() {
        assertFalse(NumberUtils.isCreatable("12E+3."));
    }

    @Test
    public void test_IsCreatable_should_return_True_when_str_end_with_dDfF_but_no_digit_before() {
        assertFalse(NumberUtils.isCreatable("d"));
        assertFalse(NumberUtils.isCreatable("D"));
        assertFalse(NumberUtils.isCreatable("f"));
        assertFalse(NumberUtils.isCreatable("F"));
    }

    @Test
    public void test_IsCreatable_should_return_True_when_str_is_end_with_dDfF_and_is_valid() {
        assertTrue(NumberUtils.isCreatable("12d"));
        assertTrue(NumberUtils.isCreatable("12D"));
        assertTrue(NumberUtils.isCreatable("12f"));
        assertTrue(NumberUtils.isCreatable("12F"));
    }

    @Test
    public void test_IsCreatable_should_return_true_when_str_is_end_with_L_but_no_digit_before() {
        assertFalse(NumberUtils.isCreatable("L"));
        assertFalse(NumberUtils.isCreatable("l"));
    }

    @Test
    public void test_IsCreatable_should_return_true_when_str_is_ends_with_L_and_is_valid() {
        assertTrue(NumberUtils.isCreatable("12L"));
        assertTrue(NumberUtils.isCreatable("12l"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_str_end_with_L_but_already_has_exp() {
        assertFalse(NumberUtils.isCreatable("12eL"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_str_end_with_L_but_already_has_point() {
        assertFalse(NumberUtils.isCreatable("12.3L"));
    }

    @Test
    public void test_IsCreatable_should_return_false_when_str_is_just_a_sign() {
        assertFalse(NumberUtils.isCreatable("+"));
        assertFalse(NumberUtils.isCreatable("-"));
    }
}