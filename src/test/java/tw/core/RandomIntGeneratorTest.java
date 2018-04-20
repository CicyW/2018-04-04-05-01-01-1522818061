package tw.core;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import tw.core.generator.RandomIntGenerator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    @Test
    public void should_throw_IllegalArgumentException(){
        try{
            RandomIntGenerator generator = new RandomIntGenerator();
            generator.generateNums(4,5);
        }catch (IllegalArgumentException ex){
            assertThat(ex.getMessage()).isEqualTo("Can't ask for more numbers than are available");
        }
    }

    @Test
    public void should_return_4digitsPlus3blanks_string(){

        RandomIntGenerator generator = new RandomIntGenerator();
        String numStr = generator.generateNums(9,4);

        assertEquals(7, numStr.length());
        assertEquals(4, numStr.replace(" ","").length());
        assertTrue(StringUtils.isNumeric(numStr.replace(" ","")));
    }

    @Test
    public void should__digit_not_repeated(){

        RandomIntGenerator generator = new RandomIntGenerator();
        String digitStr = generator.generateNums(9,4).replace(" ","");

        for (int i = 0; i < digitStr.length(); i++) {
            assertFalse(digitStr.substring(i + 1, digitStr.length()).contains(digitStr.substring(i, i + 1)));
        }
    }

}