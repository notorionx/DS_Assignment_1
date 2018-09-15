import java.lang.Math;

public class KnightMove {

    private static class Move {
        // Exercise 1
        private char col;
        private int row;
        private Move next;

        //Set column
        public void setCol(char c){
            col = c;
        }

        //Set row
        public void setRow(int i){
            row = i;
        }

        //Set successor
        public void setNext(Move m){
            next = m;
        }

        //Return column of move
        public char getCol(){
            return col;
        }

        //Return row of move
        public int getRow(){
            return row;
        }

        //Return successor
        public Move getNext(){
            return next;
        }

        public boolean validMoveCheck() {
            // Exercise 3
            //Move is valid if there is no next move
            if(next == null){
                return true;
            }

            /*Set variables for how many tiles are moved in each
            direction as well as total number of tiles moved*/
            char nextCol = next.getCol();
            int nextRow = next.getRow();
            int colDiff = Math.abs(nextCol - col);
            int rowDiff = Math.abs(nextRow - row);
            int totalDiff = colDiff + rowDiff;

            //Check if next move is in the grid
            if(nextCol >= 'A' && nextCol <= 'H' && nextRow >= 1 && nextRow <= 8){
                /*Check if next move is in L shape
                i.e. no more than 2 tiles in either direction
                and total number of tiles moved must equal 3*/
                if(colDiff <= 2 && rowDiff <= 2 && totalDiff == 3){
                    return true;
                }
            }
            //Move is invalid if above conditions not met
            return false;
        }
    }

    private Move head;
    private int size = 0;

    // constructors follow per JAVA convention

    public KnightMove() {
        // Initial Constructor for 5.1
    }

    public KnightMove(int k) {
        // Constructor for 5.2
    }

    public KnightMove(char[] columns, int[] rows) {
        // Constructor for 5.3
    }

    // Initialize with your definition of Move
    //head = new Move();

    public int size() { return size; }


    // Validator as given in class
    private Move validate(Move m) throws IllegalArgumentException {
        if(!(m instanceof Move)) throw new IllegalArgumentException("Invalid Move");
        if(m.getNext() == null) throw new IllegalArgumentException("the move is no longer in the list");

        return m;
    }

    public Move nextMove(Move m) throws IllegalArgumentException {
        // should return next move of m, if defined.
        // Otherwise it should catch the error
        return null;
    }

    public void add(Move m) {
        // definition of add per 2
        Move current = head;
        while(current != null){
            current = current.getNext();
        }
    }

    public Move remove() {
        // Exercise 2 :: should remove the end node + return the move as the output
        return null;
    }

    public Move randomMove() {
        // Exercise 4 :: This should return the random move as the output
        return null;
    }

    public void printMoves() {
        // Exercise 6
    }

    public static void main(String[] args){

    }
}