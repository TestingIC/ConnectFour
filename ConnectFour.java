import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

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
            System.out.print("Type a number between 0 and 6, this is the column where the checker will be dropped: ");
            int column_to_drop_checker_in = Integer.parseInt(player_input.nextLine());
            drop_checker(board, column_to_drop_checker_in, 'X');
            if (check_connections(board, column_to_drop_checker_in) + 1 == 4) //Player win conditional
            {
                print_board(board);
                System.out.println("The player has won!");
                play_again = 'N';
                break;
            }

            int best_bot_move = best_bot_move(board);
            drop_checker(board, best_bot_move, 'O');
            if (check_connections(board, best_bot_move) + 1 == 4) //Bot win conditional
            {
                print_board(board);
                System.out.println("The bot has won!");
                play_again = 'N';
                break;
            }
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
        if (board[0][column] != '_') //Check if the column is filled first
        {
            return board;
        }

        for (int row = 0; row < board.length; row++) //Drop from the top
        {
            if (row == board.length - 1 || board[row+1][column] != '_')
            {
                board[row][column] = checker;
                return board;
            }
        }

        return board;
    }

    public static int check_connections(char[][] board, int column)
    {
        int highest_connection = 0; //Can range between 0 for no connecting checkers and 7 for filling an entire row/column/diagonal

        //For the highest checker in the column, check all 8 surrounding positions and connect cardinal/diagonal connections
        for (int row = 0; row < board.length; row++)
        {
            if (row == board.length - 1 || board[row][column] != '_') //Hit another checker below
            {
                char checker_to_check_for = board[row][column];
                
                int checkers_to_the_right = 0;
                for (int temp_column = column + 1; temp_column < board[0].length; temp_column++) //Checks for checkers to the right
                {
                    if (board[row][temp_column] == checker_to_check_for)
                    {
                        checkers_to_the_right += 1;
                    }
                    else
                    {
                        break;
                    }
                }
                //System.out.println("Checkers to the right: " + checkers_to_the_right);

                int checkers_to_the_left = 0;
                for (int temp_column = column - 1; temp_column >= 0; temp_column--) //Checks for checkers to the left
                {
                    if (board[row][temp_column] == checker_to_check_for)
                    {
                        checkers_to_the_left += 1;
                    }
                    else
                    {
                        break;
                    }
                }
                //System.out.println("Checkers to the left: " + checkers_to_the_left);
                
                int checkers_below = 0;
                if (row != board.length - 1) //Don't need to check for checkers below if we're at the bottom
                {
                    for (int temp_row = row + 1; temp_row < board.length; temp_row++)
                    {
                        if (board[temp_row][column] == checker_to_check_for)
                        {
                            checkers_below += 1;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                //System.out.println("Checkers below: " + checkers_below);

                //Right up diagonal

                //Combine connnections

                int horizontal_connections = checkers_to_the_left + checkers_to_the_right;

                return Math.max(horizontal_connections, checkers_below);
            }
        }

        return highest_connection;
    }

    public static int best_bot_move(char[][] board)
    {
        int[] ranking_of_moves = new int[7]; //Each index corresponds to a connection ranking

        for (int column = 0; column < board.length; column++) //Fill ranking of moves
        {
            drop_checker(board, column, 'O');
            ranking_of_moves[column] = check_connections(board, column);

            //Undoes the O drop
            for (int row = 0; row < board.length; row++) //Search from the top
            {
                if (row == board.length - 1 || board[row][column] == 'O')
                {
                    board[row][column] = '_';
                    break;
                }
            }
        }

        int max_connection = 0;
        for (int index = 0; index < board.length; index++) //Find the max connection
        {
            if (ranking_of_moves[index] > max_connection)
            {
                max_connection = ranking_of_moves[index];
            }
        }

        int max_connection_count = 0;
        for (int index = 0; index < board.length; index++) //How many times does the max count appear?
        {
            if (ranking_of_moves[index] == max_connection)
            {
                max_connection_count += 1;
            }
        }

        int[] drop_columns = new int[max_connection_count];
        int drop_columns_index = 0;
        for (int index = 0; index < board.length; index++) //Find all of the eligible/best columns
        {
            if (ranking_of_moves[index] == max_connection)
            {
                drop_columns[drop_columns_index] = index;
                drop_columns_index += 1;
            }
        }

        Random random_generator = new Random();
        int random_index = random_generator.nextInt(drop_columns.length);

        return drop_columns[random_index];
    }
}