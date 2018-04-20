package tw.core;

import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    public Answer getAnswer(String str){
        Answer answer = new Answer();
        answer.setNumList(Arrays.stream(str.split(" ")).collect(Collectors.toList()));
        return answer;
    }

    @Test
    public void should_throws_OutOfRangeAnswerException(){
        try {
            Answer answer = new Answer();
            String str = "1 20 30 ";
            answer.setNumList(Arrays.stream(str.split(" ")).collect(Collectors.toList()));
            answer.validate();
        } catch (OutOfRangeAnswerException ex) {
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }
    }

    @Test
    public void should_get_index_of_num(){
        Answer answer = getAnswer("1 2 3 4");
        assertThat(answer.getIndexOfNum("4")).isEqualTo(3);
        assertThat(answer.getIndexOfNum("4")==3).isEqualTo(true);
        assertThat(answer.getIndexOfNum("5")==-1).isEqualTo(true);
        assertThat(answer.getIndexOfNum("2")==3).isEqualTo(false);
    }

    @Test
    public void should_translate_arraylist_to_string(){
        Answer answer = getAnswer("1 2 3 4");
        assertThat(answer.toString()).isEqualTo("1 2 3 4");
    }

    @Test
    public void should_check_record_of_answer(){
        Answer answer1 = getAnswer("1 2 3 4");
        Answer answer2 = getAnswer("4 3 2 1");
        Answer answer3 = getAnswer("4 3 5 7");
        int[] array1 = {0,4};
        int[] array2 = {0,2};
        int[] array3 = {2,0};
        assertThat(answer1.check(answer2).getValue()).isEqualTo(array1);
        assertThat(answer1.check(answer3).getValue()).isEqualTo(array2);
        assertThat(answer2.check(answer3).getValue()).isEqualTo(array3);
    }

}