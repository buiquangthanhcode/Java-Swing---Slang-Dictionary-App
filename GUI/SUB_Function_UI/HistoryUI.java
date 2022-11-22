package Project01.GUI.SUB_Function_UI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import Project01.GUI.Functions_Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryUI extends Functions_Interface implements ActionListener {
    private JPanel search_panel;
    private JPanel header_search;
    private JLabel header_text_title;
    private JPanel content;
    private JButton clear_History;
    public HistoryUI(){
        clear_History=new JButton("Clear");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
        if(cmd.equals("Clear History")){
           history.setListData(new String[0]);
           dic.clearHistory();
        }
    }
    public JPanel historyPanel() {
        search_panel = new JPanel();

        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.PAGE_AXIS));

        header_search = new JPanel();
        header_search.setBackground(Color.WHITE);

        header_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_text_title = new JLabel("History Search");

        header_search.add(header_text_title);
        header_search.setBorder(BorderFactory.createLineBorder(Color.gray));

        content = new JPanel();
        content.setLayout(new BorderLayout());

        clear_History.setActionCommand("Clear History");
        clear_History.addActionListener(this);
        content.add(clear_History,BorderLayout.NORTH);
        content.add(history, BorderLayout.CENTER);

        search_panel.add(Box.createRigidArea(new Dimension(10, 8)));
        search_panel.add(header_search);
        search_panel.add(content);

        return search_panel;
    }
    
    public void resetUI() {
        history.setListData(new String[0]);
        dic.clearHistory();
    }
}
