package Project01.GUI.SUB_Function_UI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import Project01.GUI.Functions_Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class SearchDefinitionUI extends Functions_Interface implements ActionListener{
    private JPanel search_panel;
    private JPanel header_search;
    private JLabel header_text_title;
    private JPanel content;
    private JPanel input;
    private JLabel input_label;
    private JTextField input_textfile;
    private JList meanings;
    private JButton search_button;
    private JButton clear_button;
    public SearchDefinitionUI(){

    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
        String cmd = e.getActionCommand();
        if (cmd.equals("Button-Search")) {

            String slang_input = input_textfile.getText();
            ArrayList<String>result = dic.findSlang_Definitions(slang_input,"Search");
             String[]stockArr=result.toArray(new String[0]);
            meanings.setListData(stockArr);
            

        } else if (cmd.equals("Clear")) {
            input_textfile.setText("");
            meanings.setListData(new String[0]);
        }
    

    }

    public JPanel searchPanel() {
        search_panel = new JPanel();

        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.PAGE_AXIS));

        header_search = new JPanel();
        header_search.setBackground(Color.WHITE);

        header_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_text_title = new JLabel("Search Definition Slang");

        header_search.add(header_text_title);
        header_search.setBorder(BorderFactory.createLineBorder(Color.gray));

        content = new JPanel();
        content.setLayout(new BorderLayout());

        input = new JPanel();
        input.setLayout(new FlowLayout(FlowLayout.CENTER));

        input_label = new JLabel("Enter word");
        input_textfile = new JTextField(15);
        search_button = new JButton("Search");
        clear_button = new JButton("Clear");
        clear_button.setActionCommand("Clear");
        clear_button.addActionListener(this);
        search_button.setActionCommand("Button-Search");
        search_button.addActionListener(this);
        input.add(input_label);
        input.add(input_textfile);
        input.add(search_button);
        input.add(clear_button);

        String[] lists = { "" };
        meanings = new JList(lists);
        content.add(input, BorderLayout.NORTH);
        content.add(meanings, BorderLayout.CENTER);

        search_panel.add(Box.createRigidArea(new Dimension(10, 8)));
        search_panel.add(header_search);
        search_panel.add(content);

        return search_panel;
    }
    
    public void resetUI() {
        input_textfile.setText("");
        meanings.setListData(new String[0]);
    }
}
