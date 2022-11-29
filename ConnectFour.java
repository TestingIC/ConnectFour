class ConnectFour
{
    public static void main(String args[])
    {
        System.out.println("Welcome to Isaac's version of Connect Four.");

        char[][] board = create_board();
        
        print_board(board);
    }

    public static char[][] create_board()
    {
        char[][] board = new char[7][7];

        for (int row = 0; row < board.length; row++) //Filling in the board
        {
            for (int column = 0; column < board[row].length; column++)
            {
                board[row][column] = '_';
            }
        }
        
        return board;
    }

    public static void print_board(char[][] board)
    {
        for (int row = 0; row < board.length; row++) //Printing the board
        {
            for (int column = 0; column < board[row].length; column++)
            {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }

        for (int column = 0; column < board[0].length; column++) //Printing the column numbers
        {
            System.out.print(column + " ");
        }
        System.out.println();
    }

    public static char[][] drop_checker(char[][] board, int column, char checker)
    {
        for (int row = 0; row < board.length; row++)
        {
            if (board[row][column] != '_') //Hit another checker
            {
                board[row-1][column] = checker;
                return board;
            }
            else if (row == (board.length - 1)) // Hit the bottom of the board
            {
                board[row][column] = checker;
                return board;
            }
        }

        return board;
    }

    public static void check_connection(char[][] board, int number_of_connections)
    {
        //Find checker
        //Check 
    }

    public static int best_bot_move(char[][] boards)
    {
        return 4;
    }

    public static int worst_player_move(char[][] board)
    {
        return -4;
    }
}