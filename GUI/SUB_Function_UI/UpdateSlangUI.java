package Project01.GUI.SUB_Function_UI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import Project01.GUI.Functions_Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSlangUI extends Functions_Interface implements ActionListener {
    private JPanel search_panel;
    private JPanel header_search;
    private JLabel header_text_title;
    private JPanel content;
    private JPanel input;
    private JLabel input_label;
    private JTextField input_textfile;
    private JPanel wraper_submit;
    private JButton submitbtn;
    private JTextField jTextArea_edit;
    private JLabel editSlanglable;
    private JButton cleaButton;

    public UpdateSlangUI() {
        cleaButton = new JButton("Clear");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String cmd = e.getActionCommand();
        if (cmd.equals("Submit-Edit")) {

            String slang = input_textfile.getText();
            String newinfor = jTextArea_edit.getText();
            boolean state = dic.checkExists(slang);
            System.out.println(state);
            if (state) {
                JDialog d = new JDialog(jframe, "Message");
                JPanel confirmJPanel = new JPanel();
                confirmJPanel.setLayout(new BoxLayout(confirmJPanel, BoxLayout.Y_AXIS));
                JLabel l = new JLabel("Please choose edit Slang or Definitions");
                l.setAlignmentX(Component.CENTER_ALIGNMENT);
                confirmJPanel.add(l);
                JButton overridebtn = new JButton("Slang");
                overridebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                overridebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Slang");
                        if (newinfor.length() != 0) {
                            dic.editSlang(slang, newinfor, "SLANG");
                        } else {
                            JOptionPane.showMessageDialog(jframe, "Please enter new information");
                        }

                    }

                });
                JButton duplicatebtn = new JButton("Definitions");
                duplicatebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Definitions");
                        if (newinfor.length() != 0) {
                            dic.editSlang(slang, newinfor, "SLANG_DEFINITION");
                        } else {
                            JOptionPane.showMessageDialog(jframe, "Please enter new information");
                        }

                    }

                });
                duplicatebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                confirmJPanel.add(Box.createRigidArea(new Dimension(10, 10)));
                confirmJPanel.add(overridebtn);
                confirmJPanel.add(duplicatebtn);

                d.add(confirmJPanel);
                d.setSize(300, 150);
                d.setLocationRelativeTo(null);
                d.setVisible(true);

            }
        } else if (cmd.equals("Clear")) {
            input_textfile.setText("");
            jTextArea_edit.setText("");
        }

    }

    public JPanel editPanel() {
        search_panel = new JPanel();

        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.Y_AXIS));

        header_search = new JPanel();
        header_search.setBackground(Color.WHITE);

        header_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_text_title = new JLabel("Edit Slang");

        header_search.add(header_text_title);
        header_search.setBorder(BorderFactory.createLineBorder(Color.gray));

        content = new JPanel();
        content.setLayout(new BorderLayout());

        editSlanglable = new JLabel("Enter new information ");
        jTextArea_edit = new JTextField(15);

        input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));

        input_label = new JLabel("Enter word");
        input_textfile = new JTextField(15);
        input.add(input_label);
        input.add(input_textfile);
        input.add(editSlanglable);
        input.add(jTextArea_edit);

        wraper_submit = new JPanel();
        wraper_submit.setBackground(Color.WHITE);
        wraper_submit.setLayout(new FlowLayout(FlowLayout.CENTER));
      
        submitbtn = new JButton("Submit");
        submitbtn.setActionCommand("Submit-Edit");
        submitbtn.addActionListener(this);
        cleaButton.setActionCommand("Clear");
        cleaButton.addActionListener(this);
        wraper_submit.add(submitbtn);
        wraper_submit.add(cleaButton);

        content.add(input, BorderLayout.NORTH);
        content.add(wraper_submit, BorderLayout.CENTER);

        search_panel.add(Box.createRigidArea(new Dimension(10, 8)));
        search_panel.add(header_search);
        search_panel.add(content);

        return search_panel;
    }
    
    public void resetUI() {
        input_textfile.setText("");
        jTextArea_edit.setText("");
    }
}
