import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class KnightMove{

    private static class Move{

//      Instance variables
        private char col;
        private int row;
        private Move next;

        /**
         * Default constructor
         */
        public Move(){}

        /**
         * Initializes Move object with specified column, row, and next move
         * @param c a char from A to H representing the column
         * @param r an int from 1 to 8 representing the row
         * @param m the next move
         */
        public Move(char c, int r, Move m){
            col = c;
            row = r;
            next = m;
        }

        /**
         * Set column
         * @param c any char from A to H
         */
        public void setCol(char c){
            col = c;
        }

        /**
         * Set row
         * @param r any int from 1 to 8
         */
        public void setRow(int r){
            row = r;
        }

        /**
         * Set next move
         * @param m any Move object
         */
        public void setNext(Move m){
            next = m;
        }

        /**
         * Return column
         * @return A char from A to H
         */
        public char getCol(){
            return col;
        }

        /**
         * Return row
         * @return An int from 1 to 8
         */
        public int getRow(){
            return row;
        }

        /**
         * Returns the next Move if it exists. Otherwise returns null
         * @return Either a Move object or null
         */
        public Move getNext(){
            return next;
        }

        /**
         * Returns string representation of the position. Overrides toString() in Object class
         * @return The column and row of this Move object's position
         */
        public String toString(){
            String s = Character.toString(col) + row;
            return s;
        }

        /**
         * If the move from current position to next is valid, returns true. Otherwise returns false
         * @return true if valid move, false if invalid
         */
        public boolean validMoveCheck(){
            if(next == null){
                return true;
            }
            else{
//              For readability, create variables for row and col of next move
                char nextCol = next.getCol();
                int nextRow = next.getRow();


                if(col == 0 && row == 0){
//                  If we are calling this method from the head, the next move is valid
//                  provided it is inside of the grid. We can check if this method is
//                  being called from the head because we do not assign any values to
//                  the row or col variables in the head node, and Java will give these
//                  types a default value of 0

//                  Return true if move is inside the grid, i.e. col within A and H,
//                  row within 1 and 8. Otherwise return false
                    return (nextCol >= 'A' && nextCol <= 'H' && nextRow >= 1 && nextRow <= 8);
                }
                else{
//                  If we are not moving from the head, then the move is valid only if
//                  it is both inside the grid and also an L shape

//                  Create variables for how many tiles are moved in each
//                  direction as well as total number of tiles moved
                    int colDiff = Math.abs(nextCol - col);
                    int rowDiff = Math.abs(nextRow - row);
                    int totalDiff = colDiff + rowDiff;

                    if(nextCol >= 'A' && nextCol <= 'H' && nextRow >= 1 && nextRow <= 8){
//                      Enter if statement if move is inside the grid

//                      If inside grid, check if next move is in L shape,
//                      i.e. no more than 2 tiles moved in either direction
//                      and total number of tiles moved equals 3. Return true if
//                      valid L shape, otherwise false
                        return (colDiff <= 2 && rowDiff <= 2 && totalDiff == 3);
                    }
                    else{
//                      Move is invalid if above conditions are not met
                        return false;
                    }
                }
            }

        }

        /**
         * Returns ArrayList of all valid moves from current position.
         * Makes randomMove() method in KnightMove class more readable
         * @return An ArrayList of all valid moves from current position
         */
        public ArrayList<Move> getValidMoves(){

//          Create ArrayList of Move objects, to be populated with all valid moves
            ArrayList<Move> validMoves = new ArrayList<Move>();

//          Goes through all 8 variants of L-shaped movement from current position.
//          If validMoveCheck() returns true, m is added to validMoves
            for(int i = 0; i < 8; i++){
//              There are 8 possible moves from any given position in the grid:
//              (+1,+2),(+2,+1) and all possible negations of each.
//              We use a for loop in combination with a switch statement to check
//              all 8 possibilities. Of course, some of these moves will be out of
//              the grid, so in each case we will be calling validMoveCheck()
                switch(i){
                    case 0:
//                      Create new Move object with first possible move and set as
//                      next move so we can call validMoveCheck() from current position
                        Move a = new Move((char) (col - 2), row - 1, null);
                        setNext(a);

                        if(validMoveCheck()){
//                          If move is valid, add to ArrayList
                            validMoves.add(a);
                        }
//                      Set next move back to null because we don't want to add it yet
                        setNext(null);
                        break;
//                      Following cases for other 7 possibilities use same logic
                    case 1:
                        Move b = new Move((char) (col + 2), row - 1, null);
                        setNext(b);

                        if(validMoveCheck()){
                            validMoves.add(b);
                        }

                        setNext(null);
                        break;
                    case 2:
                        Move c = new Move((char) (col - 2), row + 1, null);
                        setNext(c);

                        if(validMoveCheck()){
                            validMoves.add(c);
                        }

                        setNext(null);
                        break;
                    case 3:
                        Move d = new Move((char) (col + 2), row + 1, null);
                        setNext(d);

                        if(validMoveCheck()){
                            validMoves.add(d);
                        }

                        setNext(null);
                        break;
                    case 4:
                        Move e = new Move((char) (col - 1), row - 2, null);
                        setNext(e);

                        if(validMoveCheck()){
                            validMoves.add(e);
                        }

                        setNext(null);
                        break;
                    case 5:
                        Move f = new Move((char) (col + 1), row - 2, null);
                        setNext(f);

                        if(validMoveCheck()){
                            validMoves.add(f);
                        }

                        setNext(null);
                        break;
                    case 6:
                        Move g = new Move((char) (col - 1), row + 2, null);
                        setNext(g);

                        if(validMoveCheck()){
                            validMoves.add(g);
                        }

                        setNext(null);
                        break;
                    case 7:
                        Move h = new Move((char) (col + 1), row + 2, null);
                        setNext(h);

                        if(validMoveCheck()){
                            validMoves.add(h);
                        }

                        setNext(null);
                        break;
                }
            }
            return validMoves;
        }
    }

//  Instance variables
    private Move head;
    private int size;

    /**
     * Default constructor
     */
    public KnightMove(){
        head = new Move();
        size = 0;
    }

    /**
     * Initializes KnightMove object and makes the desired number of calls to randomMove()
     * @param k the number of random moves to make
     */
    public KnightMove(int k){
        head = new Move();
        size = 0;

        for(int i = 0; i < k; i++){
//          Call randomMove() k times
            randomMove();
        }
    }

    /**
     * Initializes KnightMove object and adds moves to the linked list based on the contents of the
     * char and int arrays which are passed in. Throws an exception if the user tries to make an invalid move
     * @param columns an array of chars representing columns, to be processed sequentially
     * @param rows an array of ints representing rows, to be processed sequentially
     * @throws IllegalArgumentException if user tries to make an invalid move
     */
    public KnightMove(char[] columns, int[] rows) throws IllegalArgumentException{
        head = new Move();
        size = 0;

        for(int i = 0; i < Math.min(columns.length, rows.length); i++){
//          For loop to iterate through column and row arrays. Bound is
//          the length of the smaller array to avoid an IndexOutOfBounds
//          error, should the user pass in two arrays of different size

//          On each iteration, create a new Move object and add it. If
//          the program tries to add an invalid move, the add() method will
//          throw an IllegalArgumentException and the program will terminate
            Move m = new Move(columns[i], rows[i], null);
            add(m);
        }
    }

    /**
     * Returns head of the linked list
     * @return head of the linked list
     */
    public Move getHead(){
        return head;
    }

    /**
     * Returns size of the linked list
     * @return size of the linked list
     */
    public int size(){ return size; }

    /**
     * Increases size of linked list
     */
    public void grow(){
        size++;
    }

    /**
     * Decreases size of linked list
     */
    public void shrink(){
        size--;
    }


//    Following starter code is not used. The functions of validate() are performed by validMoveCheck()
//    and the functions of nextMove() are performed by getNext(), both of which are in the Move class.\

//    private Move validate(Move m) throws IllegalArgumentException{
//        if(!(m instanceof Move)) throw new IllegalArgumentException("Invalid Move");
//        if(m.getNext() == null) throw new IllegalArgumentException("the move is no longer in the list");
//
//        return m;
//    }
//
//    public Move nextMove(Move m) throws IllegalArgumentException{
//        // should return next move of m, if defined.
//        // Otherwise it should catch the error
//        return null;
//    }


    /**
     * Adds Move m to the end of the linked list
     * @param m any Move object
     * @throws IllegalArgumentException if the added move is deemed invalid by the
     * validMoveCheck() method within the Move class
     */
    public void add(Move m) throws IllegalArgumentException{
//      Create new pointer so as not to change the value of head
        Move current = getHead();
        while(current.getNext() != null){
//          Traverse through the linked list until we are at the last node
            current = current.getNext();
        }

//      Append Move m to last node and increment the size
        current.setNext(m);
        grow();

        if(!current.validMoveCheck()){
//          Once Move m has been added, we still need to check if it is a valid
//          move from the current position. If validMoveCheck() returns false
//          we throw an IllegalArgumentException and give the user an error message
            throw new IllegalArgumentException("The next move is invalid.");
        }
    }

    /**
     * Removes the last Move object from the linked list and returns it
     * @return The last Move object in the linked list which has been removed
     */
    public Move remove(){

        if(size() == 0){
//          Return null if list is empty
            return null;
        }
        else{
//          If list has at least one element, create two pointers, one to
//          store the removed node and one to access the second to last node
            Move last = null;
            Move current = getHead();


            while(current.getNext().getNext() != null){
//              Traverse through the linked list until the node two nodes ahead is null,
//              then current is pointing to the second to last node
                current = current.getNext();
            }

//          Store the last node to be returned, then set the pointer from current to
//          last as null and increment the size
            last = current.getNext();
            current.setNext(null);
            shrink();

//          Return the removed node
            return last;
        }
    }

    /**
     * Makes a random valid move by calling getValidMoves() in the Move class,
     * adds it to the end of the linked list, and returns it
     * @return A random valid move that has been added to the end of the linked list
     */
    public Move randomMove(){
//      Create Random object which we will use to generate random move, as well as
//      an empty Move object which will be added to the end of the linked list
        Random r = new Random();
        Move m = new Move();

        if(size == 0){
//          If list is empty, generate random column and list within the grid and
//          assign them to the move, then add the move
            m.setCol((char)(r.nextInt(8) + 'A'));
            m.setRow(r.nextInt(8) + 1);
            add(m);

//          Return added move
            return m;
        }
        else{
//          If list is not empty, traverse to the last move and adda move after it

//          Create pointer so as not to change the value of head
            Move current = getHead();

            while(current.getNext() != null){
//              Traverse until current points to the last move
                current = current.getNext();
            }

//          Create ArrayList of valid moves from current move by calling getValidMoves()
            ArrayList<Move> validMoves = current.getValidMoves();

//          Use random generator to pick a move from ArrayList and add it
            m = validMoves.get(r.nextInt(validMoves.size()));
            add(m);

//          Return added move
            return m;
        }
    }

    /**
     * Prints all moves in linked list sequentially. Informs the user if list is empty
     */
    public void printMoves(){
        if(size() == 0){
//          Tell user if list is empty
            System.out.println("The list of moves is empty.");
        }
        else{
//          Create pointer so as not to change the value of head
            Move current = getHead();
            while(current.getNext() != null){
//              Traverse through each move in linked list and print string representation
                current = current.getNext();
                System.out.print(current.toString());
                if(current.getNext() != null){
//                  If we are not on the last node, print an arrow to the next move
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

//    Main method that can be used to test things that are hard to test with JUnit. Namely, methods
//    within the Move class as well as randomMove() and printMoves() in the KnightMove class

//    public static void main(String[] args){
//        Move m = new Move('D', 5, null);

//        System.out.println(m.getCol());
//        System.out.println(m.getRow());
//        System.out.println(m.getValidMoves());
//
//        KnightMove k = new KnightMove(10);

//        k.printMoves();
//        System.out.println(k.size());

//        k.remove();

//        k.printMoves();
//        System.out.println(k.size());
//
//        char[] c = {'B','C','B','C'};
//        int[] r = {1,3,5,7};
//        KnightMove k1 = new KnightMove(c, r);

//        k1.printMoves();
//        System.out.println(k1.size());

//        Move m1 = new Move('E', 6, null);

//        k1.add(m1);

//        k1.printMoves();
//        System.out.println(k1.size());

//        k1.randomMove();

//        k1.printMoves();
//        System.out.println(k1.size());
//    }
}
