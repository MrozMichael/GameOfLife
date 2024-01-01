import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @Test
    void deadBoardStaysDead(){
        board = new Board(5,5 );
        board.deadState();
        int[][] initialState = board.getState();
        board.advance();
        int[][] newState = board.getState();
        for (int i = 0; i < 5; i++){
            for (int k = 0; k < 5; k++){
                assertEquals(initialState[i][k], newState[i][k]);
            }
        }
    }

    @Test

    void boardEqualsGivenState(){
        int[][] initialState = {{1,0}, {0, 1}};
        Board board = new Board(2,2);
        board.fixedState(initialState);
        int[][] currentState = board.getState();
        for(int i = 0; i < 2; i++){
            for(int k = 0; k < 2; k++){
                assertEquals(currentState[i][k], initialState[i][k]);
            }
        }
    }
    @Test
    void deadCellsWith3NeighboursLive(){
        int[][] initialState = {
                {0, 0, 1},
                {0, 1, 1},
                {0, 0, 0}
        };
        int[][] expectedState = {
                {0, 1, 1},
                {0, 1, 1},
                {0, 0, 0}
        };
        Board board = new Board(3, 3);
        board.fixedState(initialState);
        board.advance();
        board.render();
        int [][] currentState = board.getState();
        for( int i = 0; i < 3; i++){
            for(int k = 0; k < 3; k++){
                assertEquals(expectedState[i][k], currentState[i][k]);
            }
        }
    }
}