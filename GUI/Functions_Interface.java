package Project01.GUI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JDialog;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Project01.GUI.SUB_Function_UI.*;

public class Functions_Interface extends InterfaceProgram {
    private JPanel function_panel;
    private JPanel header;
    private JLabel header_title;
    protected JButton btnSearch;
    protected JButton btnSearch_Definitons;
    protected JButton btnHistory;
    protected JButton btnaddSlang;
    protected JButton btnEditSalng;
    protected JButton btnDelete;
    protected JButton btnReset;
    protected JButton btnRandom;
    protected JButton btnQuizSlang;
    protected JButton btnQuizSlangDefinitions;
    protected static JPanel menu_function;
    protected static JList history;
    protected static SearchUI UI_SEARCH;
    protected static SearchDefinitionUI UI_SEARCH_DEFINITION;
    protected static HistoryUI UI_HISTORY;
    protected static AddSlangUI UI_ADDSLANG;
    protected static ResetUI UI_RESET;
    protected static UpdateSlangUI UI_EDIT;
    protected static DeleteSlangUI UI_DELETE;
    protected static QuizSlangUI UI_QUIZ;
    protected static QuizSlangDefinitionUI UI_QUIZ_DEFINITION;
    protected static RandomSlangUI UI_RANDOM;

    public Functions_Interface() {

        history = new JList(new String[0]);

    }

    public JPanel ui_function() {
        BtnEvent even = new BtnEvent();
        function_panel = new JPanel();
        function_panel.setLayout(new BorderLayout());
        function_panel.setBackground(Color.WHITE);
        function_panel.setPreferredSize(new Dimension(500, 500));

        header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_title = new JLabel("Slang Words Dictionary");
        JButton homtBtn = new JButton("Back Introdcution");
        homtBtn.setActionCommand("Back Introdcution");
        homtBtn.addActionListener(even);
        header.add(header_title);
        header.add(homtBtn);

        menu_function = new JPanel();

        menu_function.setLayout(new CardLayout());
        UI_SEARCH = new SearchUI();
        UI_SEARCH_DEFINITION = new SearchDefinitionUI();
        UI_HISTORY = new HistoryUI();
        UI_ADDSLANG = new AddSlangUI();
        UI_EDIT = new UpdateSlangUI();
        UI_RESET = new ResetUI();
        UI_DELETE = new DeleteSlangUI();
        UI_QUIZ = new QuizSlangUI();
        UI_QUIZ_DEFINITION = new QuizSlangDefinitionUI();
        UI_RANDOM = new RandomSlangUI();

        menu_function.add("search", UI_SEARCH.searchPanel());
        menu_function.add("search-definition", UI_SEARCH_DEFINITION.searchPanel());
        menu_function.add("history", UI_HISTORY.historyPanel());
        menu_function.add("add-slang", UI_ADDSLANG.addSlangPanel());
        menu_function.add("update-slang", UI_EDIT.editPanel());
        menu_function.add("reset", UI_RESET.resetPanel());
        menu_function.add("delete", UI_DELETE.deletePanel());
        menu_function.add("quiz", UI_QUIZ.quizJPanel());
        menu_function.add("quiz-definition", UI_QUIZ_DEFINITION.quizJPanel());
        menu_function.add("random", UI_RANDOM.randoJPanel());

        JPanel side_bar = new JPanel();
        side_bar.setLayout(new GridLayout(10, 1));

        btnSearch = new JButton("Search Slang");
        btnSearch.setActionCommand("Search Slang");
        btnSearch.addActionListener(even);
        side_bar.add(btnSearch);

        btnSearch_Definitons = new JButton("Search Definitions");
        btnSearch_Definitons.setActionCommand("Search Definitions");
        btnSearch_Definitons.addActionListener(even);
        side_bar.add(btnSearch_Definitons);

        btnHistory = new JButton("History");
        btnHistory.setActionCommand("History");
        btnHistory.addActionListener(even);

        side_bar.add(btnHistory);

        btnaddSlang = new JButton("Add Slang");
        btnaddSlang.setActionCommand("Add Slang");
        btnaddSlang.addActionListener(even);

        side_bar.add(btnaddSlang);

        btnEditSalng = new JButton("Edit Slang");
        btnEditSalng.setActionCommand("Edit Slang");
        btnEditSalng.addActionListener(even);

        side_bar.add(btnEditSalng);

        btnDelete = new JButton("Delete Slang");
        btnDelete.setActionCommand("Delete Slang");
        btnDelete.addActionListener(even);

        side_bar.add(btnDelete);

        btnReset = new JButton("Reset Slang");
        btnReset.setActionCommand("Reset Slang");
        btnReset.addActionListener(even);
        side_bar.add(btnReset);

        btnRandom = new JButton("Random Slang");
        btnRandom.setActionCommand("Random Slang");
        btnRandom.addActionListener(even);
        side_bar.add(btnRandom);

        btnQuizSlang = new JButton("Quiz Slang");
        btnQuizSlang.setActionCommand("Quiz Slang");
        btnQuizSlang.addActionListener(even);
        side_bar.add(btnQuizSlang);

        btnQuizSlangDefinitions = new JButton("Quiz Slang Definitions");
        btnQuizSlangDefinitions.setActionCommand("Quiz Slang Definitions");
        btnQuizSlangDefinitions.addActionListener(even);

        side_bar.add(btnQuizSlangDefinitions);

        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton exitbtn = new JButton("EXIT");
        exitbtn.setActionCommand("EXIT");
        exitbtn.addActionListener(even);

        exitbtn.setForeground(Color.red);
        footer.add(exitbtn);

        // add buttons into the ui_function
        function_panel.add(header, BorderLayout.NORTH);
        function_panel.add(menu_function, BorderLayout.CENTER);
        function_panel.add(side_bar, BorderLayout.EAST);
        function_panel.add(footer, BorderLayout.SOUTH);

        return function_panel;
    }

}

class BtnEvent extends Functions_Interface implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Search Slang")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "search");
        } else if (cmd.equals("Add Slang")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "add-slang");
        } else if (cmd.equals("Search Definitions")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "search-definition");
        } else if (cmd.equals("History")) {

            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "history");
            String[] historys = new String[dic.getSizeHistory()];
            historys = dic.getHistoryList().toArray(historys);
            history.setListData(historys);

        } else if (cmd.equals("Delete Slang")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "delete");
        } else if (cmd.equals("Reset Slang")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "reset");
        } else if (cmd.equals("Random Slang")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "random");
        } else if (cmd.equals("Quiz Slang")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "quiz");
        } else if (cmd.equals("Quiz Slang Definitions")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "quiz-definition");
        } else if (cmd.equals("Edit Slang")) {
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "update-slang");
        } else if (cmd.equals("Back Introdcution")) {
            CardLayout cardLayout = (CardLayout) (app.getLayout());
            cardLayout.show(app, "intro");
        } else if (cmd.equals("EXIT")) {
            JDialog d = new JDialog(jframe, "Message");
            JPanel confirmJPanel = new JPanel();
            confirmJPanel.setLayout(new BoxLayout(confirmJPanel, BoxLayout.Y_AXIS));
            JLabel l = new JLabel("Press Confirm to exit");
            l.setAlignmentX(Component.CENTER_ALIGNMENT);
            confirmJPanel.add(l);
            JButton cfButton = new JButton("Confirm");
            cfButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }

            });
            cfButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            confirmJPanel.add(Box.createRigidArea(new Dimension(10, 10)));
            confirmJPanel.add(cfButton);
            d.add(confirmJPanel);
            d.setSize(300, 150);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        }

    }
}
