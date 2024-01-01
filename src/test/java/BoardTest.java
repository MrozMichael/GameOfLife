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
        assertTrue(testBoard(currentState, initialState));
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
        int [][] currentState = board.getState();
        assertTrue(testBoard(currentState, expectedState));
    }

    @Test
    void livingCellWith4PlusNeighboursDies(){
        int[][] initialState = {
                {0,1,0},
                {1,1,1},
                {0,1,0}
        };
        int[][] expectedState = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        Board board = new Board(3, 3);
        board.fixedState(initialState);
        board.advance();
        int[][] currentState = board.getState();
        assertTrue(testBoard(currentState, expectedState));

    }

    @Test

    void cornersWorkAsExpected(){
        int [][] initialState = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 1, 0}
        };
        int [][] expectedState = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {0, 1, 1, 1}
        };
        Board board = new Board(4, 4);
        board.fixedState(initialState);
        board.advance();
        int[][] currentState = board.getState();
        assertTrue(testBoard(currentState, expectedState));
    }

    @Test
    void singleColumnWorks(){
        int [][] initialState = {
                {1}, //0 0 0 0 0 0 1 1 1 0
                {0},
                {1},
                {1},
                {0},
                {1},
                {1},
                {1},
                {1},
                {1},
        };
        int[][] expectedState = {
                {0},
                {0},
                {0},
                {0},
                {0},
                {0},
                {1},
                {1},
                {1},
                {0},
        };
        Board board = new Board(10, 1);
        board.fixedState(initialState);
        board.advance();
        assertTrue(testBoard(board.getState(), expectedState));
    }

    @Test
    void singleRowWorks(){
        int[][] initialState = {{0, 1, 1, 1, 1}};
        int[][] expectedState = {{0, 0, 1, 1, 0}};

        Board board = new Board(1, 5);
        board.fixedState(initialState);
        board.advance();
        assertTrue(testBoard(board.getState(), expectedState));
    }

    public boolean testBoard(int[][] current, int[][] expected){
        if (current.length != expected.length || current[0].length != expected[0].length){
            return false;
        }
        for (int i = 0; i < current.length; i++ ){
            for (int j = 0; j < current[i].length; j++){
                if (current[i][j] != expected[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}