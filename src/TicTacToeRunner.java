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
        currentPlayerLbl = new JLabel("Current Player: X");

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
        TTTBoard.createGameBoard(gamePnl);
        TTTBoard.setBoardActions(currentPlayerLbl);
    }


}
