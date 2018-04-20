package tw.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;
import tw.core.model.Record;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.fest.assertions.api.Assertions.assertThat;
import static tw.core.GameStatus.CONTINUE;
import static tw.core.GameStatus.FAIL;
import static tw.core.GameStatus.SUCCESS;
/**
 * 在GameTest文件中完成Game中对应的单元测试
 */

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    @Mock
    private AnswerGenerator mockGenerator;

    public Answer getAnswer(String str){
        Answer answer = new Answer();
        answer.setNumList(Arrays.stream(str.split(" ")).collect(Collectors.toList()));
        return answer;
    }

    @Test
    public void shuold_return_guess_result_history_list(){
        try{
            Mockito.when(mockGenerator.generate()).thenReturn(getAnswer("1 2 3 4"));
            Game game = new Game(mockGenerator);

            Answer answerInput1 = getAnswer("4 3 2 1");
            GuessResult result1 = game.guess(answerInput1);

            Answer answerInput2 = getAnswer("4 3 5 6");
            GuessResult result2 = game.guess(answerInput2);

            Answer answerInput3 = getAnswer("1 2 3 4");
            GuessResult result3 = game.guess(answerInput3);

            GuessResult[] resultArray ={result1,result2,result3};
             assertThat(game.guessHistory()).isEqualTo(Arrays.asList(resultArray));

        }catch (OutOfRangeAnswerException ex){
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }

    }

    @Test
    public void should_return_success_status(){
        try{
            Mockito.when(mockGenerator.generate()).thenReturn(getAnswer("1 2 3 4"));
            Game game = new Game(mockGenerator);

            Answer answerInput = getAnswer("1 2 3 4");
            GuessResult result = game.guess(answerInput);
            assertThat(game.checkStatus()).isEqualTo(SUCCESS);

        }catch (OutOfRangeAnswerException ex){
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }
    }

    @Test
    public void should_return_fail_status(){
        try{
            Mockito.when(mockGenerator.generate()).thenReturn(getAnswer("1 2 3 4"));
            Game game = new Game(mockGenerator);

            Answer answerInput1 = getAnswer("4 3 2 1");
            GuessResult result1 = game.guess(answerInput1);

            Answer answerInput2 = getAnswer("4 3 5 6");
            GuessResult result2 = game.guess(answerInput2);

            Answer answerInput3 = getAnswer("3 5 6 7");
            GuessResult result3 = game.guess(answerInput3);

            Answer answerInput4 = getAnswer("6 7 8 9");
            GuessResult result4 = game.guess(answerInput4);

            Answer answerInput5 = getAnswer("5 3 4 1");
            GuessResult result5 = game.guess(answerInput5);

            Answer answerInput6 = getAnswer("1 3 4 2");
            GuessResult result6 = game.guess(answerInput6);

            assertThat(game.checkStatus()).isEqualTo(FAIL);

        }catch (OutOfRangeAnswerException ex){
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }
    }

    @Test
    public void should_return_continue_status(){
        try{
            Mockito.when(mockGenerator.generate()).thenReturn(getAnswer("1 2 3 4"));
            Game game = new Game(mockGenerator);

            Answer answerInput = getAnswer("4 3 2 1");
            GuessResult result = game.guess(answerInput);

            assertThat(game.checkStatus()).isEqualTo(CONTINUE);

        }catch (OutOfRangeAnswerException ex){
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }
    }

    @Test
    public void should_return_true_when_check_continue_status(){
        try{
            Mockito.when(mockGenerator.generate()).thenReturn(getAnswer("1 2 3 4"));
            Game game = new Game(mockGenerator);

            Answer answerInput = getAnswer("4 3 2 1");
            GuessResult result = game.guess(answerInput);

            assertThat(game.checkCoutinue()).isEqualTo(true);

        }catch (OutOfRangeAnswerException ex){
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }
    }

    @Test
    public void should_return_false_when_check_continue_status(){
        try{
            Mockito.when(mockGenerator.generate()).thenReturn(getAnswer("1 2 3 4"));
            Game game1 = new Game(mockGenerator);
            Game game2 = new Game(mockGenerator);

            Answer answerInput = getAnswer("1 2 3 4");
            GuessResult result = game1.guess(answerInput);

            Answer answerInput1 = getAnswer("4 3 2 1");
            GuessResult result1 = game2.guess(answerInput1);

            Answer answerInput2 = getAnswer("4 3 5 6");
            GuessResult result2 = game2.guess(answerInput2);

            Answer answerInput3 = getAnswer("3 5 6 7");
            GuessResult result3 = game2.guess(answerInput3);

            Answer answerInput4 = getAnswer("6 7 8 9");
            GuessResult result4 = game2.guess(answerInput4);

            Answer answerInput5 = getAnswer("5 3 4 1");
            GuessResult result5 = game2.guess(answerInput5);

            Answer answerInput6 = getAnswer("1 3 4 2");
            GuessResult result6 = game2.guess(answerInput6);

            assertThat(game1.checkCoutinue()).isEqualTo(false);
            assertThat(game2.checkCoutinue()).isEqualTo(false);

        }catch (OutOfRangeAnswerException ex){
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }
    }

    @Test
    public void shuold_return_guess_result(){
        try{
            Mockito.when(mockGenerator.generate()).thenReturn(getAnswer("1 2 3 4"));
            Game game = new Game(mockGenerator);

            Answer answerInput1 = getAnswer("4 3 2 1");
            GuessResult result1 = game.guess(answerInput1);

            Answer answerInput2 = getAnswer("4 3 5 6");
            GuessResult result2 = game.guess(answerInput2);

            Answer answerInput3 = getAnswer("1 2 3 4");
            GuessResult result3 = game.guess(answerInput3);

            assertThat(result1.getResult()).isEqualTo("0A4B");
            assertThat(result1.getInputAnswer().toString()).isEqualTo(answerInput1.toString());

            assertThat(result2.getResult()).isEqualTo("0A2B");
            assertThat(result2.getInputAnswer().toString()).isEqualTo(answerInput2.toString());

            assertThat(result3.getResult()).isEqualTo("4A0B");
            assertThat(result3.getInputAnswer().toString()).isEqualTo(answerInput3.toString());

        }catch (OutOfRangeAnswerException ex){
            assertThat(ex.getMessage()).isEqualTo("Answer format is incorrect");
        }

    }

}
