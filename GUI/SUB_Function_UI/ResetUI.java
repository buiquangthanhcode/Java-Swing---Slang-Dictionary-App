package Project01.GUI.SUB_Function_UI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Project01.utility.Utility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Project01.GUI.Functions_Interface;
public class ResetUI extends Functions_Interface implements ActionListener {
    private JPanel reset_panel;
    private JButton reset_button;
    private JButton cancel_button;
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        if(cmd.equals("Reset")) {
            dic.resetHistory();
            UI_ADDSLANG.resetUI();
            UI_DELETE.resetUI();
            UI_EDIT.resetUI();
            UI_HISTORY.resetUI();
            UI_SEARCH.resetUI();
            UI_RANDOM.resetUI();
            UI_QUIZ.resetCheckBox();
            UI_QUIZ.resetLable();
            UI_QUIZ_DEFINITION.resetCheckBox();
            UI_QUIZ_DEFINITION.resetLable();
            UI_SEARCH_DEFINITION.resetUI();
            ArrayList<String> reset_file=new ArrayList<String>();
            reset_file.add("");
            Utility.writeFile(reset_file);
            JOptionPane.showMessageDialog(null, "Slang Reset");
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "search");
        }else if (cmd.equals("Cancel")){
            CardLayout cardLayout = (CardLayout) (menu_function.getLayout());
            cardLayout.show(menu_function, "search");
        }

    }

    public JPanel resetPanel() {

        reset_panel = new JPanel();
        JLabel lablereset = new JLabel("Reset Slang -> ");
        reset_button = new JButton("Reset");
        reset_button.setActionCommand("Reset");
        reset_button.addActionListener(this);
        cancel_button = new JButton("Cancel");
        cancel_button.setActionCommand("Cancel");
        cancel_button.addActionListener(this);
        reset_panel.add(lablereset);
        reset_panel.add(reset_button);
        reset_panel.add(cancel_button);
        return reset_panel;
    }
}

// search nghia da them vo 
