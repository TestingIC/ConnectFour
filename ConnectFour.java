import java.util.Scanner;

class ConnectFour
{
    public static void main(String args[])
    {
        Scanner player_input = new Scanner(System.in);

        System.out.println("Welcome to Isaac's version of Connect Four.");

        char[][] board = create_board();
        char play_again = 'Y';
        while (play_again == 'Y')
        {
            print_board(board);
            System.out.print("Type a number between 0 and 6, this is the column where the cheker will be dropped: ");
            int column_to_drop_checker_in = Integer.parseInt(player_input.nextLine());
            drop_checker(board, column_to_drop_checker_in, 'X');
            check_connections(board, column_to_drop_checker_in);
        }

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
            if (board[row][column] != '_') //Hit another checker below
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

    public static int check_connections(char[][] board, int column_the_checker_was_droppped_in)
    {
        int highest_connection = 1; //Can range between 1 for no connecting checkers and 4 for a connect four

        //For the highest checker in the column, check all 8 surrounding positions and connect cardinal/diagonal connections
        for (int row = 0; row < board.length; row++)
        {
            if (board[row][column_the_checker_was_droppped_in] != '_' && row != (board.length - 1)) //Hit another checker below
            {
                char checker_to_check_for = board[row][column_the_checker_was_droppped_in];
            }
            else if (row == (board.length - 1)) // Hit the bottom of the board
            {
                char checker_to_check_for = board[row][column_the_checker_was_droppped_in];
                
                int checkers_to_the_right = 0;
                for (int column = column_the_checker_was_droppped_in + 1; column < board[0].length; column++) //Checks for checers to the right
                {
                    if (board[row][column] == checker_to_check_for)
                    {
                        checkers_to_the_right += 1;
                    }
                    else
                    {
                        break;
                    }
                }

                //Combine connnections
            }
        }

        return highest_connection;
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