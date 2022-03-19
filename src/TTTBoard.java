import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTTBoard
{
    private static final int ROW = 3;
    private static final int COL = 3;
    public static final JButton[][] board = new JButton[ROW][COL];

    private static String currentPlayer = "X";

    public static void createGameBoard(JPanel panel)
    {
        for(int row = 0; row <=2; row++) {
            for (int col = 0; col <= 2; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                panel.add(board[row][col]);
                board[row][col].setText(" ");
                board[row][col].setBackground(Color.white);
            }
        }
    }

    public static void setNewBoard()
    {
        TicTacToe.clearBoard();
        for(int row = 0; row <=2; row++) {
            for (int col = 0; col <= 2; col++) {
                board[row][col].setText(" ");
            }
        }
    }

    public static void setBoardActions(JLabel label)
    {
        for(int row = 0; row <=2; row++)
        {
            for (int col = 0; col <= 2; col++)
            {
                board[row][col].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        TicTacToeTile buttonClicked = (TicTacToeTile) e.getSource();

                        //invalid move check
                        if (!buttonClicked.getText().isBlank()) {
                            // Show the error msg option pane
                            JOptionPane.showMessageDialog(null, "This move is not valid");
                        }
                        else
                        {
                            buttonClicked.setText(String.valueOf(currentPlayer));
                            TicTacToe.updateBoard(String.valueOf(currentPlayer), buttonClicked.getRow(), buttonClicked.getCol());

                            winBoardAction(label);
                            tieBoardAction();
                            switchPlayer(label);
                        }
                    }
                });
            }
        }
    }

    private static void winBoardAction(JLabel label)
    {
        if (TicTacToe.isWin(currentPlayer))
        {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins! Play again!");
            setNewBoard();

            currentPlayer = "O";
            //TicTacToeRunner.currentPlayerLbl.setText("Current Player:" + currentPlayer);
            label.setText("Current Player:" + currentPlayer);

        }
    }

    private static void tieBoardAction()
    {
        if (TicTacToe.isTie())
        {
            JOptionPane.showMessageDialog(null, "The game is a tie! Play again!");
            setNewBoard();
        }
    }

    private static void switchPlayer(JLabel label)
    {
        if (currentPlayer == "X") {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }

        //TicTacToeRunner.currentPlayerLbl.setText("Current Player:" + currentPlayer);
        label.setText("Current Player:" + currentPlayer);

    }
}
