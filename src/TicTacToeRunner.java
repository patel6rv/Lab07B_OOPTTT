import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeRunner extends JFrame
{
    JPanel mainPnl;
    JPanel currentPlayerPnl; //top
    JPanel gamePnl; //mid
    JPanel controlPnl; //bottom

    JLabel currentPlayerLbl;

    JButton quitBtn;

    private static final int ROW = 3;
    private static final int COL = 3;
    private static JButton[][] board = new JButton[ROW][COL];

    String currentPlayer = "X";

    public TicTacToeRunner()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createCurrentPlayerPanel();
        mainPnl.add(currentPlayerPnl, BorderLayout.NORTH);

        createDisplayPanel();
        mainPnl.add(gamePnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        TicTacToe.clearBoard();
    }

    private void createCurrentPlayerPanel()
    {
        currentPlayerPnl = new JPanel();
        currentPlayerLbl = new JLabel("Current Player:" + currentPlayer + " ");

        currentPlayerPnl.add(currentPlayerLbl);
    }

    private void createDisplayPanel()
    {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(3, 3));
        createButtons();
    }

    private void createControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 1));

        quitBtn = new JButton("Quit");
        controlPnl.add(quitBtn);
        quitBtn.addActionListener(ActiveEvent_ae -> System.exit(0));
    }

    private void createButtons()
    {
        createGameBoard();
        setBoardActions();
    }

    private void createGameBoard()
    {
        for(int row = 0; row <=2; row++) {
            for (int col = 0; col <= 2; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                gamePnl.add(board[row][col]);
                board[row][col].setText(" ");
                board[row][col].setBackground(Color.white);
            }
        }
    }

    private void setBoardActions()
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

                        //set move and record it
                        buttonClicked.setText(String.valueOf(currentPlayer));
                        TicTacToe.updateBoard(currentPlayer, buttonClicked.getRow(), buttonClicked.getCol());

                        winBoardAction();
                        tieBoardAction();
                        switchPlayer();
                    }
                });
            }
        }
    }




    private void winBoardAction()
    {
        if (TicTacToe.isWin(currentPlayer))
        {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins! Play again!");
            setNewBoard();
        }
    }

    private void tieBoardAction()
    {
        if (TicTacToe.isTie())
        {
            JOptionPane.showMessageDialog(null, "The game is a tie! Play again!");
            setNewBoard();
        }
    }

    private void setNewBoard()
    {
        TicTacToe.clearBoard();
        for(int row = 0; row <=2; row++) {
            for (int col = 0; col <= 2; col++) {
                board[row][col].setText(" ");
            }
        }

        currentPlayer = "O";
        currentPlayerLbl.setText("Current Player:" + currentPlayer + " ");
    }

    private void switchPlayer()
    {
        if (currentPlayer == "X") {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
        currentPlayerLbl.setText("Current Player:" + currentPlayer + " ");
    }

}
