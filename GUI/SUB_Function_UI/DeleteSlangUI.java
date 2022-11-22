package Project01.GUI.SUB_Function_UI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import Project01.GUI.Functions_Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DeleteSlangUI extends Functions_Interface implements ActionListener {
    private JPanel search_panel;
    private JPanel header_search;
    private JLabel header_text_title;
    private JPanel content;
    private JPanel input;
    private JLabel input_label;
    private JTextField input_textfile;
    private JPanel wrapper_deleteBtn;
    private JButton deleteBtn;
    
    public DeleteSlangUI() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String cmd=e.getActionCommand();
        if(cmd.equals("Delete")){
            if(input_textfile.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Please input the slang", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else 
            {
                String slang = input_textfile.getText();
                boolean state = dic.checkExists(slang);
            if (state) {
                JDialog d = new JDialog(jframe, "Message");
                JPanel confirmJPanel = new JPanel();
                confirmJPanel.setLayout(new BoxLayout(confirmJPanel, BoxLayout.Y_AXIS));
                JLabel l = new JLabel("Do you really want to delete this Slang");
                l.setAlignmentX(Component.CENTER_ALIGNMENT);
                confirmJPanel.add(l);
                JButton confirmbtn=new JButton("Yes");
                confirmbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                confirmbtn.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                           boolean checkDelete= dic.deleteSlang(slang);
                            if(checkDelete){
                                JOptionPane.showMessageDialog(null, "Slang deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                                return ;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Slang not deleted", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        
                    }

                });
                confirmJPanel.add(Box.createRigidArea(new Dimension(10, 10)));
                confirmJPanel.add(confirmbtn);
                

                d.add(confirmJPanel);
                d.setSize(300, 150);
                d.setLocationRelativeTo(null);
                d.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Slang not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
         }
        }
        
        }
    
    
    
    public JPanel deletePanel() {
        search_panel = new JPanel();

        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.PAGE_AXIS));

        header_search = new JPanel();
        header_search.setBackground(Color.WHITE);

        header_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_text_title = new JLabel("Delete Slang");

        header_search.add(header_text_title);
        header_search.setBorder(BorderFactory.createLineBorder(Color.gray));

        content = new JPanel();
        content.setLayout(new BorderLayout());

        input = new JPanel();
        input.setLayout(new FlowLayout(FlowLayout.CENTER));

        input_label = new JLabel("Enter word");
        input_textfile = new JTextField(15);
        input.add(input_label);
        input.add(input_textfile);

        deleteBtn=new JButton("Delete");
        deleteBtn.setActionCommand("Delete");
        deleteBtn.addActionListener(this);
        wrapper_deleteBtn =new JPanel();
        wrapper_deleteBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
        wrapper_deleteBtn.add(deleteBtn);
    
        content.add(input, BorderLayout.NORTH);
        content.add(wrapper_deleteBtn, BorderLayout.CENTER);

        search_panel.add(Box.createRigidArea(new Dimension(10, 8)));
        search_panel.add(header_search);
        search_panel.add(content);

        return search_panel;
    }
    
    public void resetUI() {
        input_textfile.setText("");
    }
}

