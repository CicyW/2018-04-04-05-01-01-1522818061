package tw.core.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerGeneratorTest {
    @Mock
    private RandomIntGenerator  mockRandomIntGenerator;

    @Test
    public void should_throws_OutOfRangeAnswerException(){
        try {
            AnswerGenerator answerGenerator = new AnswerGenerator(mockRandomIntGenerator);
            Mockito.when(mockRandomIntGenerator.generateNums(9,4)).thenReturn("10 20 30 40");
            Answer answer = answerGenerator.generate();
        } catch (OutOfRangeAnswerException ex) {
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }
    }

    @Test
    public void should_answer_have_numList(){

        try {
            AnswerGenerator answerGenerator = new AnswerGenerator(mockRandomIntGenerator);
            String str = "1 2 3 4";
            Mockito.when(mockRandomIntGenerator.generateNums(9,4)).thenReturn(str);
            Answer answer = answerGenerator.generate();
            assertThat(answer.toString()).isEqualTo(str);
        } catch (OutOfRangeAnswerException ex) {
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }
    }
}

