import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class KnightMove{

    private static class Move{
        //Instance variables
        private char col;
        private int row;
        private Move next;

        //Constructor
        public Move(){}

        //Constructor
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

        /*Returns string representation of position, makes printMoves() method
        in KnightMove class more readable*/
        public String toString(){
            return Character.toString(col) + row;
        }

        //Check if move from current position to next is valid
        public boolean validMoveCheck(){
            if(next == null){
                //Move is valid if there is no next move
                return true;
            }
            else{
                //For readability, create variables for row and col of next move
                char nextCol = next.getCol();
                int nextRow = next.getRow();


                if(col == 0 && row == 0){
                    /*If we are calling this method from the head, the next move is valid
                    provided it is inside of the grid. We can check if this method is
                    being called from the head because we do not assign any values to
                    the row or col variables in the head node, and Java will give these
                    types a default value of 0.*/

                    /*Return true if move is inside the grid, i.e. col within A and H,
                    row within 1 and 8. Otherwise return false*/
                    return (nextCol >= 'A' && nextCol <= 'H' && nextRow >= 1 && nextRow <= 8);
                }
                else{
                    /*If we are not moving from the head, then the move is valid only if
                    it is both inside the grid and also an L shape*/

                    /*Create variables for how many tiles are moved in each
                    direction as well as total number of tiles moved*/
                    int colDiff = Math.abs(nextCol - col);
                    int rowDiff = Math.abs(nextRow - row);
                    int totalDiff = colDiff + rowDiff;

                    if(nextCol >= 'A' && nextCol <= 'H' && nextRow >= 1 && nextRow <= 8){
                        //Enter if statement if move is inside the grid

                        /*If inside grid, check if next move is in L shape,
                        i.e. no more than 2 tiles moved in either direction
                        and total number of tiles moved equals 3. Return true if
                        valid L shape, otherwise false*/
                        return (colDiff <= 2 && rowDiff <= 2 && totalDiff == 3);
                    }
                    else{
                        //Move is invalid if above conditions are not met
                        return false;
                    }
                }
            }

        }

        /*Returns ArrayList of all valid moves from current position.
        Makes randomMove() method in KnightMove class more readable*/
        public ArrayList<Move> getValidMoves(){

            //Create ArrayList of Move objects, to be populated with all valid moves
            ArrayList<Move> validMoves = new ArrayList<Move>();

            /*Goes through all 8 variants of L-shaped movement from current position.
            If validMoveCheck() returns true, m is added to validMoves*/
            for(int i = 0; i < 8; i++){
                /*There are 8 possible moves from any given position in the grid:
                (+1,+2),(+2,+1) and all possible negations of each.
                We use a for loop in combination with a switch statement to check
                all 8 possibilities. Of course, some of these moves will be out of
                the grid, so in each case we will be calling validMoveCheck()*/
                switch(i){
                    case 0:
                        /*Create new Move object with first possible move and set as
                        next move so we can call validMoveCheck() from current position*/
                        Move a = new Move((char) (col - 2), row - 1, null);
                        setNext(a);

                        if(validMoveCheck()){
                            //If move is valid, add to ArrayList
                            validMoves.add(a);
                        }
                        //Set next move back to null because we don't want to add it yet
                        setNext(null);
                        break;
                        //Following cases for other 7 possibilities use same logic
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
            //After all cases checked, return ArrayList of valid moves
            return validMoves;
        }
    }

    //Instance variables
    private Move head;
    private int size;

    //Constructor for 5.1
    public KnightMove(){
        head = new Move();
        size = 0;
    }

    //Constructor for 5.2
    public KnightMove(int k){
        head = new Move();
        size = 0;

        for(int i = 0; i < k; i++){
            //For loop to call randomMove() k times
            randomMove();
        }
    }

    //Constructor for 5.3
    public KnightMove(char[] columns, int[] rows) throws IllegalArgumentException{
        head = new Move();
        size = 0;

        for(int i = 0; i < Math.min(columns.length, rows.length); i++){
            /*For loop to iterate through column and row arrays. Bound is
            the length of the smaller array to avoid an IndexOutOfBounds
            error, should the user pass in two arrays of different size*/

            /*On each iteration, create a new Move object and add it. If
            the program tries to add an invalid move, the add() method will
            throw an IllegalArgumentException and the program will terminate*/
            Move m = new Move(columns[i], rows[i], null);
            add(m);
        }
    }

    //Return head of linked list
    public Move getHead(){
        return head;
    }

    //Return size of linked list
    public int size(){ return size; }

    //Increase size of linked list
    public void grow(){
        size++;
    }

    //Decrease size of linked list
    public void shrink(){
        size--;
    }


    // Validator as given in class
    private Move validate(Move m) throws IllegalArgumentException{
        if(!(m instanceof Move)) throw new IllegalArgumentException("Invalid Move");
        if(m.getNext() == null) throw new IllegalArgumentException("the move is no longer in the list");

        return m;
    }

    public Move nextMove(Move m) throws IllegalArgumentException{
        // should return next move of m, if defined.
        // Otherwise it should catch the error
        return null;
    }


    //Add Move object to end of linked list
    public void add(Move m) throws IllegalArgumentException{
        //Create new pointer so as not to change the value of head
        Move current = getHead();
        while(current.getNext() != null){
            //Traverse through the linked list until we are at the last node
            current = current.getNext();
        }

        //Append Move m to last node and increment the size
        current.setNext(m);
        grow();

        if(!current.validMoveCheck()){
            /*Once Move m has been added, we still need to check if it is a valid
            move from the current position. If validMoveCheck() returns false
            we throw an IllegalArgumentException and give the user an error message*/
            throw new IllegalArgumentException("The next move is invalid.");
        }
    }

    //Remove the last node and return it
    public Move remove(){

        if(size() == 0){
            //Return null if list is empty
            return null;
        }
        else{
            /*If list has at least one element, create two pointers, one to
            store the removed node and one to access the second to last node*/
            Move last = null;
            Move current = getHead();


            while(current.getNext().getNext() != null){
                /*Traverse through the linked list until the node two nodes ahead is null,
                then current is pointing to the second to last node*/
                current = current.getNext();
            }

            /*Store the last node to be returned, then set the pointer from current to
            last as null and increment the size*/
            last = current.getNext();
            current.setNext(null);
            shrink();

            //Return the removed node
            return last;
        }
    }

    //Makes a random valid move, adds it to the list, and returns it
    public Move randomMove(){
        /*Create Random object which we will use to generate random move, as well as
        an empty Move object which will be added to the end of the linked list*/
        Random r = new Random();
        Move m = new Move();

        if(size == 0){
            /*If list is empty, generate random column and list within the grid and
            assign them to the move, then add the move*/
            m.setCol((char)(r.nextInt(8) + 'A'));
            m.setRow(r.nextInt(8) + 1);
            add(m);

            //Return added move
            return m;
        }
        else{
            //If list is not empty, traverse to the last move and adda move after it

            //Create pointer so as not to change the value of head
            Move current = getHead();

            while(current.getNext() != null){
                //Traverse until current points to the last move
                current = current.getNext();
            }

            //Create ArrayList of valid moves from current move by calling getValidMoves()
            ArrayList<Move> validMoves = current.getValidMoves();

            //Use random generator to pick a move from ArrayList and add it
            m = validMoves.get(r.nextInt(validMoves.size()));
            add(m);

            //Return added move
            return m;
        }
    }

    //Prints all moves in linked list sequentially
    public void printMoves(){
        if(size() == 0){
            //Tell user if list is empty
            System.out.println("The list of moves is empty.");
        }
        else{
            //Create pointer so as not to change the value of head
            Move current = getHead();
            while(current.getNext() != null){
                //Traverse through each move in linked list and print string representation
                current = current.getNext();
                System.out.print(current.toString());
                if(current.getNext() != null){
                    //If we are not on the last node, print an arrow to the next move
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    //Code to test things that are hard to test in JUnit
    public static void main(String[] args){
        KnightMove k = new KnightMove(10);
        k.printMoves();
        System.out.println(k.size());
        k.remove();
        k.printMoves();
        System.out.println(k.size());

        char[] c = {'B','C','B','C'};
        int[] r = {1,3,5,7};
        KnightMove kk = new KnightMove(c, r);
        kk.printMoves();
        System.out.println(kk.size());
        Move m = new Move('E', 6, null);
        kk.add(m);
        kk.printMoves();
        System.out.println(kk.size());
        kk.randomMove();
        kk.printMoves();
        System.out.println(kk.size());
    }
}
