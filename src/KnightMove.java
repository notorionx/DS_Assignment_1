import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class KnightMove {

    private static class Move {
        // Exercise 1
        private char col;
        private int row;
        private Move next;

        //Constructors
        public Move(){}

        public Move(char c, int r, Move m){
            col = c;
            row = r;
            next = m;
        }

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

        public String toString(){
            return Character.toString(col) + row;
        }

        public boolean validMoveCheck(){
            // Exercise 3
            //Move is valid if there is no next move
            if(next == null){
                return true;
            }

            /*Set variables for how many tiles are moved in each
            direction as well as total number of tiles moved*/
            char nextCol = next.getCol();
            int nextRow = next.getRow();


            /*If we are calling this method from the head, we only need to check
            that the next move is in the grid*/
            if(col == 0 && row == 0){
                if(nextCol >= 'A' && nextCol <= 'H' && nextRow >= 1 && nextRow <= 8){
                    return true;
                }
            }

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

        public ArrayList<Move> getValidMoves(){

            //Create move object to test all 8 possible moves

            //Create ArrayList to be populated with all valid moves
            ArrayList<Move> validMoves = new ArrayList<Move>();

            /*Goes through all 8 variants of L-shaped movement from current position.
            If validMoveCheck() returns true, m is added to validMoves*/
            for(int i = 0; i < 8; i++){
                switch (i) {
                    case 0:
                        Move a = new Move();
                        a.setCol((char) (col - 2));
                        a.setRow(row - 1);
                        setNext(a);
                        if (validMoveCheck()) {
                            validMoves.add(a);
                        }
                        setNext(null);
                        break;
                    case 1:
                        Move b = new Move();
                        b.setCol((char) (col + 2));
                        b.setRow(row - 1);
                        setNext(b);
                        if (validMoveCheck()) {
                            validMoves.add(b);
                        }
                        setNext(null);
                        break;
                    case 2:
                        Move c = new Move();
                        c.setCol((char) (col - 2));
                        c.setRow(row + 1);
                        setNext(c);
                        if (validMoveCheck()) {
                            validMoves.add(c);
                        }
                        setNext(null);
                        break;
                    case 3:
                        Move d = new Move();
                        d.setCol((char) (col + 2));
                        d.setRow(row + 1);
                        setNext(d);
                        if (validMoveCheck()) {
                            validMoves.add(d);
                        }
                        setNext(null);
                        break;
                    case 4:
                        Move e = new Move();
                        e.setCol((char) (col - 1));
                        e.setRow(row - 2);
                        setNext(e);
                        if (validMoveCheck()) {
                            validMoves.add(e);
                        }
                        setNext(null);
                        break;
                    case 5:
                        Move f = new Move();
                        f.setCol((char) (col + 1));
                        f.setRow(row - 2);
                        setNext(f);
                        if (validMoveCheck()) {
                            validMoves.add(f);
                        }
                        setNext(null);
                        break;
                    case 6:
                        Move g = new Move();
                        g.setCol((char) (col - 1));
                        g.setRow(row + 2);
                        setNext(g);
                        if (validMoveCheck()) {
                            validMoves.add(g);
                        }
                        setNext(null);
                        break;
                    case 7:
                        Move h = new Move();
                        h.setCol((char) (col + 1));
                        h.setRow(row + 2);
                        setNext(h);
                        if (validMoveCheck()) {
                            validMoves.add(h);
                        }
                        setNext(null);
                        break;
                }
            }

            return validMoves;
        }
    }

    private Move head;
    private int size;

    // constructors follow per JAVA convention

    public KnightMove() {
        // Initial Constructor for 5.1
        head = new Move();
        size = 0;
    }

    public KnightMove(int k) {
        // Constructor for 5.2
        head = new Move();
        size = 0;
        for(int i = 0; i < k; i++){
            randomMove();
        }
    }

    public KnightMove(char[] columns, int[] rows) throws IllegalArgumentException {
        // Constructor for 5.3
        head = new Move();
        size = 0;
        for(int i = 0; i < rows.length; i++){
            Move m = new Move(columns[i], rows[i], null);
            add(m);
        }
    }

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

    public void add(Move m) throws IllegalArgumentException{
        // definition of add per 2
        Move current = head;
        while(current.getNext() != null){
            current = current.getNext();
        }

        current.setNext(m);
        size++;

        if(!current.validMoveCheck()){
            remove();
            throw new IllegalArgumentException("The next move is invalid.");
        }
    }

    public Move remove() {
        // Exercise 2 :: should remove the end node + return the move as the output
        Move last = null;
        Move current = head;

        if(current.getNext() == null){
            return last;
        }

        while(current.getNext().getNext() != null){
            current = current.getNext();
        }

        last = current.getNext();
        current.setNext(null);
        size--;

        return last;
    }

    public Move randomMove() {
        // Exercise 4 :: This should return the random move as the output

        Random r = new Random();
        Move m = new Move();

        if(size == 0){
            m.setCol((char)(r.nextInt(8) + 'A'));
            m.setRow(r.nextInt(8) + 1);
            add(m);

            return m;
        }

        else{
            Move tail = head;

            while(tail.getNext() != null) {
                tail = tail.getNext();
            }

            ArrayList<Move> validMoves = tail.getValidMoves();

            m = validMoves.get(r.nextInt(validMoves.size()));
            add(m);

            return m;
        }
    }

    public void printMoves() {
        // Exercise 6
        if(size() == 0){
            System.out.println("The list of moves is empty.");
        }
        else{
            Move current = head;
            while(current.getNext() != null){
                current = current.getNext();
                System.out.print(current.toString());
                if(current.getNext() != null){
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
//        KnightMove k = new KnightMove(10);
//        k.printMoves();
//        System.out.println(k.size());
//        k.remove();
//        k.printMoves();
//        System.out.println(k.size());

        char[] c = {'B','C','B','C'};
        int[] r = {1,3,5,9};
        KnightMove kk = new KnightMove(c, r);
        kk.printMoves();
        System.out.println(kk.size());
    }


}
