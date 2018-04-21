package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import tw.commands.GuessInputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.fest.assertions.api.Assertions.assertThat;
import static tw.core.GameStatus.CONTINUE;
import static tw.core.GameStatus.FAIL;
import static tw.core.GameStatus.SUCCESS;
/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Mock
    private Game mockGame;

    @Mock
    private GuessInputCommand mockInputCommand;

    @Mock
    private AnswerGenerator mockGenerator;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() { return outContent.toString(); }


    @Test
    public void should_show_game_begin(){
        GameController controller = new GameController(mockGame,new GameView());
        try {
            controller.beginGame();
            assertThat(systemOut()).isEqualTo("------Guess Number Game, You have 6 chances to guess!  ------\n");
        }catch (IOException ex){

        }
    }

    @Test
    public void should_show_game_over_when_continue_status_is_fail(){
        GameController controller = new GameController(mockGame,new GameView());
        try {
            Mockito.when(mockGame.checkStatus()).thenReturn(FAIL);
            controller.play(new GuessInputCommand());
            assertThat(systemOut()).isEqualTo("Game Status: " + FAIL + "\n");
        }catch (IOException ex){

        }
    }

    @Test
    public void should_show_game_over_when_continue_status_is_success(){
        GameController controller = new GameController(mockGame,new GameView());
        try {
            Mockito.when(mockGame.checkStatus()).thenReturn(SUCCESS);
            controller.play(new GuessInputCommand());
            assertThat(systemOut()).isEqualTo("Game Status: " + SUCCESS + "\n");
        }catch (IOException ex){

        }
    }
    @Test
    public void should_guess_1_time_when_input_is_correct(){
        try {
            Answer answerActual = getAnswer("4 3 2 1");
            Answer answerInput = getAnswer("4 3 2 1");
            Mockito.when(mockGenerator.generate()).thenReturn(answerActual);
            Mockito.when(mockInputCommand.input()).thenReturn(answerInput);

            Game game = new Game(mockGenerator);
            GameController controller = new GameController(game,new GameView());
            controller.play(mockInputCommand);
            Mockito.verify(mockGenerator, Mockito.times(1)).generate();
            Mockito.verify(mockInputCommand, Mockito.times(1)).input();
        }catch (OutOfRangeAnswerException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void should_guess_6_times_when_input_always_incorrect(){
        try {
            Answer answerActual = getAnswer("4 3 2 1");
            Answer answerInput = getAnswer("1 2 3 4");
            Mockito.when(mockGenerator.generate()).thenReturn(answerActual);
            Mockito.when(mockInputCommand.input()).thenReturn(answerInput);

            Game game = new Game(mockGenerator);
            GameController controller = new GameController(game,new GameView());
            controller.play(mockInputCommand);
            Mockito.verify(mockGenerator, Mockito.times(1)).generate();
            Mockito.verify(mockInputCommand, Mockito.times(6)).input();
        }catch (OutOfRangeAnswerException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public Answer getAnswer(String str){
        Answer answer = new Answer();
        answer.setNumList(Arrays.stream(str.split(" ")).collect(Collectors.toList()));
        return answer;
    }

}