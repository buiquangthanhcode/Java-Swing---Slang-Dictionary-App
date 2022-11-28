package Project01.GUI.SUB_Function_UI;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import Project01.GUI.Functions_Interface;
import Project01.utility.Utility;

public class ShowSlangUI extends Functions_Interface  {
    private JPanel search_panel;
    private JPanel header_search;
    private JLabel header_text_title;
    private JPanel content;
    private JList meanings;
  

    public ShowSlangUI() {

    }

    public JPanel searchPanel() {

        search_panel = new JPanel();
        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.PAGE_AXIS));

        header_search = new JPanel();
        header_search.setBackground(Color.WHITE);

        header_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_text_title = new JLabel("SHOW SLANG");

        header_search.add(header_text_title);
        header_search.setBorder(BorderFactory.createLineBorder(Color.gray));

        content = new JPanel();
        content.setLayout(new BorderLayout());
        meanings = new JList(Utility.getDataOneLine());

        JScrollPane cp = new JScrollPane(meanings);
        content.add(cp, BorderLayout.CENTER);

        search_panel.add(Box.createRigidArea(new Dimension(10, 8)));
        search_panel.add(header_search);
        search_panel.add(content);

        return search_panel;
    }

   
}
