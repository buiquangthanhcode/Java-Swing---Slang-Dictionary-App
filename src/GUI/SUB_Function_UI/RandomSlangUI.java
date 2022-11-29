package GUI.SUB_Function_UI;


import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import GUI.Functions_Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomSlangUI extends Functions_Interface implements ActionListener {
    private JPanel search_panel;
    private JPanel header_search;
    private JLabel header_text_title;
    private JPanel content;
    private JPanel wrapper_answer;
    private JButton randombtn;
    private JLabel show_Slang_randow;

    public RandomSlangUI() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        if (text.equals("Random")) {
            String result="";
            HashMap<String,ArrayList<String>>random= dic.RandomSlang();
            for (Map.Entry<String, ArrayList<String>>entry : random.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> data=entry.getValue();
                result=key+" = "+data.toString();
            }
            show_Slang_randow.setText(result);
        }

    }

    public JPanel randoJPanel() {
        search_panel = new JPanel();

        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.PAGE_AXIS));

        header_search = new JPanel();
        header_search.setBackground(Color.WHITE);

        header_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_text_title = new JLabel("Random Slang ");

        header_search.add(header_text_title);
        header_search.setBorder(BorderFactory.createLineBorder(Color.gray));

        content = new JPanel();
        content.setLayout(new BorderLayout());

        wrapper_answer = new JPanel();
        wrapper_answer.setBackground(Color.WHITE);
        wrapper_answer.setLayout(new BoxLayout(wrapper_answer, BoxLayout.PAGE_AXIS));

        JPanel header_content = new JPanel();
        header_content.setLayout(new FlowLayout(FlowLayout.CENTER));
        randombtn = new JButton("Random Slang ");
        randombtn.setActionCommand("Random");
        randombtn.addActionListener(this);

        header_content.setMaximumSize(new Dimension(Integer.MAX_VALUE, header_content.getMinimumSize().height));
        header_content.add(randombtn);

        JPanel main_content = new JPanel();
        main_content.setLayout(new FlowLayout(FlowLayout.CENTER));
        show_Slang_randow = new JLabel("Example");
        main_content.add(show_Slang_randow);

        wrapper_answer.add(header_content);
        wrapper_answer.add(main_content);
        content.add(wrapper_answer);

        search_panel.add(Box.createRigidArea(new Dimension(10, 8)));
        search_panel.add(header_search);
        search_panel.add(content);

        return search_panel;
    }
    public void resetUI(){
        show_Slang_randow.setText("");
    }
}
