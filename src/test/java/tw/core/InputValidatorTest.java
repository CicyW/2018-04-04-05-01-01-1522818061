package tw.core;

import org.junit.Test;
import tw.validator.InputValidator;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    @Test
    public void should_string_is_validate(){
        InputValidator validator = new InputValidator();
        boolean reslut1 = validator.validate("2541");
        boolean reslut2 = validator.validate("25 41 1 3");
        boolean reslut3 = validator.validate("10 4 1 3");
        boolean reslut4 = validator.validate("4 2 1 ");
        boolean reslut5 = validator.validate("6 6 1 2");
        boolean reslut6 = validator.validate("0 5 4 9");
        assertThat(reslut1).isEqualTo(false);
        assertThat(reslut2).isEqualTo(false);
        assertThat(reslut3).isEqualTo(false);
        assertThat(reslut4).isEqualTo(false);
        assertThat(reslut5).isEqualTo(false);
        assertThat(reslut6).isEqualTo(true);
    }
}
