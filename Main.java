import java.util.Scanner;

public class Main {

    static String[][] board = {  {"","",""},
                                 {"","",""},
                                 {"","",""} };
    static char symbolplayer ; // indicator of the player X or O playing for the First game.


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


     while ( startnewgame(scanner) );

     }

    // checking the last player and switch to other player. // the player who loose is starting game.
    private static void switchplayer() {
        if (symbolplayer == 'X') {

            symbolplayer   = 'O';
        }
        else symbolplayer = 'X';

    }

    // put the player move in the chosen place
    private static void Makemove(char playersymbol ,Scanner scanner) {
        int row;  int col;
        do {
            System.out.println("Player " + playersymbol + ", enter your move (row and column):");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while(!validrowcol(row,col));

        board[row][col]= String.valueOf(playersymbol);

    }


    private static boolean validrowcol(int row ,int col){ // checking a legal input for the row and column and if the chosen place is'nt already taken

        if ( row>=0 && row<=2 && col>=0 && col<=2 && !board[row][col].equals("X") && !board[row][col].equals("O"))
        return true;
        else printboard();

        // checking if the input is legal
        if (row>=0 && row<=2 && col>=0 && col<=2) {
            System.out.println("The place you choose is already taken");
            return false;
        }
        else {
            System.out.println("Wrong input please choose row and column between 0 and 2");
            return false;
        }


    }

    private static boolean Gameover() { // scanning the array to check if the game is over and printing the game result

         String antidiagonal= "" ;
         String diagonal= "" ;

          for (int i = 0; i < board.length ; i++) { // scanning the whole board to check if the board is full and there is a winner

              if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) { // horizontally scanning board

                  System.out.println("The Winner is Player " + board[i][0]);
                  return true;
              }


              if ( board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) ) { //  vertically scanning board and checking if there is winner

                  System.out.println("The Winner is Player " + board[1][i]);
                  return true;
              }

               if (board[i][i].equals("X") || board[i][i].equals("O")) { // scanning diagonal

                   diagonal += board[i][i];
               }

              if (board[i][board.length - 1 - i].equals("X") || board[i][board.length - 1 - i].equals("O")) {   // scanning antidiagonal

                  antidiagonal += board[i][board.length - 1 - i];

              }
          }


        if (diagonal.equals("XXX") || diagonal.equals("OOO")) { // checking diagonal if there is a winner

            System.out.println("The Winner is Player "+ diagonal.charAt(0));

            return true;
        }


        if ( antidiagonal.equals("XXX") || antidiagonal.equals("OOO") ) { // checking antidiagonal if there is a winner

            System.out.println("The Winner is Player "+ antidiagonal.charAt(0));

            return true ;

        }


        for (int i = 0; i < board.length; i++) {    // checking if the board is not fully filled by players
            for (int j = 0; j < board[i].length; j++) {
                if ( board[i][j].equals("X") || board[i][j].equals("O") ) {
                    continue;

                }
                else return false;

            }
        }
        System.out.println("It's a Draw... No Winner ");
        return true;
    }

        private static void printboard() { // printing the board
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print("["+board[i][j]+"]");

                }
                System.out.println();
            }
            System.out.println();

        }

    private static boolean startnewgame(Scanner scanner) { // starting new game
        int start; symbolplayer = 'X';
        System.out.println("to Start a new game please press 1");
        start =  scanner.nextInt();

         if ( start == 1 ) {

             for (int i = 0; i < board.length ; i++) { // filling the array with its index to let player recognize the actual place to choose
                 for (int j = 0; j < board.length ; j++) {
                     board[i][j] = ""+i+j+"" ;
                 }

             }

             while (!Gameover()) {

                 printboard();
                 Makemove(symbolplayer,scanner);
                 switchplayer();
             }
             for (int i = 0; i < board.length; i++) {    // filling "_" in the index where empty for better view at the end of every game
                 for (int j = 0; j < board[i].length; j++) {
                     if (board[i][j].equals("X")  || board[i][j].equals("O") ) {
                         continue ;

                     }
                     else board[i][j] = "_";
                 }
             }

             printboard();
             return true;
           }
         else {
             System.out.println("Thank you for Playing Tic Tac Toe");
             return false ;

         }

    }

}