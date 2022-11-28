
package Project01.GUI.SUB_Function_UI;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import Project01.GUI.Functions_Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddSlangUI extends Functions_Interface implements ActionListener {
    private JPanel search_panel;
    private JPanel header_search;
    private JLabel header_text_title;
    private JPanel content;
    private JPanel input;
    private JLabel input_label;
    private JTextField input_textfile;
    private JLabel input_Definition_Label;
    private JTextField input_Slang_Definition;
    private JPanel wraper_submit;
    private JButton submitbtn;

    public AddSlangUI() {
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        if (cmd.equals("Submit")) {
            String slang = input_textfile.getText();
            String slang_definition = input_Slang_Definition.getText();
            boolean state = dic.checkExists(slang);
            if (state) {
                JDialog d = new JDialog(jframe, "Message");
                JPanel confirmJPanel = new JPanel();
                confirmJPanel.setLayout(new BoxLayout(confirmJPanel, BoxLayout.Y_AXIS));
                JLabel l = new JLabel("Slang aready existed");
                l.setAlignmentX(Component.CENTER_ALIGNMENT);
                confirmJPanel.add(l);
                JButton overridebtn = new JButton("Override");
                overridebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                overridebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        input_textfile.setText("");
                        input_Slang_Definition.setText("");
                        d.dispose();
                        dic.addSlang(slang, slang_definition, "Override");
                    }

                });
                JButton duplicatebtn = new JButton("Duplicate");
                duplicatebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        input_textfile.setText("");
                        input_Slang_Definition.setText("");
                        d.dispose();
                        dic.addSlang(slang, slang_definition, "Duplicate");

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

            } else {
                JDialog d = new JDialog(jframe, "Message");
                JPanel confirmJPanel = new JPanel();
                confirmJPanel.setLayout(new BoxLayout(confirmJPanel, BoxLayout.Y_AXIS));
                JLabel l = new JLabel("Add Successfully");
                l.setAlignmentX(Component.CENTER_ALIGNMENT);
                confirmJPanel.add(l);
                JButton okButton = new JButton("Ok");
                okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dic.addSlang(slang, slang_definition, "Addnew");
                       
                        input_textfile.setText("");
                        input_Slang_Definition.setText("");
                        d.dispose();

                    }

                });

                confirmJPanel.add(okButton);

                d.add(confirmJPanel);
                d.setSize(300, 150);
                d.setLocationRelativeTo(null);
                d.setVisible(true);
            }
        }

    }

    public JPanel addSlangPanel() {
        search_panel = new JPanel();

        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.PAGE_AXIS));

        header_search = new JPanel();
        header_search.setBackground(Color.WHITE);

        header_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_text_title = new JLabel("Add new Slang");

        header_search.add(header_text_title);
        header_search.setBorder(BorderFactory.createLineBorder(Color.gray));

        content = new JPanel();
        content.setLayout(new BorderLayout());

        input = new JPanel();
        input.setLayout(new FlowLayout(FlowLayout.CENTER));

        input_label = new JLabel("Enter slang");
        input_textfile = new JTextField(10);
        input.add(input_label);
        input.add(input_textfile);
        input_Definition_Label = new JLabel("Enter definition");
        input_Slang_Definition = new JTextField(12);
        input.add(input_Definition_Label);
        input.add(input_Slang_Definition);

        wraper_submit = new JPanel();
        wraper_submit.setBackground(Color.WHITE);
        wraper_submit.setLayout(new FlowLayout(FlowLayout.CENTER));
        submitbtn = new JButton("Submit");
        submitbtn.setActionCommand("Submit");
        submitbtn.addActionListener(this);
        wraper_submit.add(submitbtn);

        content.add(input, BorderLayout.NORTH);
        content.add(wraper_submit, BorderLayout.CENTER);

        search_panel.add(Box.createRigidArea(new Dimension(10, 8)));
        search_panel.add(header_search);
        search_panel.add(content);

        return search_panel;
    }

    public void resetUI() {
        input_textfile.setText("");
        input_Slang_Definition.setText("");
    }
}
