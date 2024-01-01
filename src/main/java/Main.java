
public class Main {
    public static void main(String[] args) {
       Board board = new Board(1, 5);
        int [][] initialState = {
                {0, 1, 1, 1, 1}
        };
        board.fixedState(initialState);
        board.advance();
        board.render();
    }
}