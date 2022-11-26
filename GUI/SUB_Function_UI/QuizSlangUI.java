package Project01.GUI.SUB_Function_UI;

import java.awt.*;
import javax.swing.*;
import Project01.GUI.Functions_Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.event.ItemEvent;

public class QuizSlangUI extends Functions_Interface implements ActionListener, ItemListener {
    private JPanel search_panel;
    private JPanel header_search;
    private JLabel header_text_title;
    private JPanel content;
    private JPanel wrapper_answer;
    private JButton randombtn;
    private JButton submit_answerbtn;
    private JLabel show_Slang_randow;
    private JCheckBox checkboxA;
    private JCheckBox checkboxB;
    private JCheckBox checkboxC;
    private JCheckBox checkboxD;
    private JLabel one_answer;
    private JLabel two_answer;
    private JLabel third_answer;
    private JLabel four_answer;
    private int count = 0;
    private HashMap<String, Integer> mark;
    private HashMap<String, String> map;

    public QuizSlangUI() {
        checkboxA = new JCheckBox("A");
        checkboxB = new JCheckBox("B");
        checkboxC = new JCheckBox("C");
        checkboxD = new JCheckBox("D");
        map = new HashMap<String, String>();
        mark = new HashMap<>();
        mark.put("A", 0);
        mark.put("B", 0);
        mark.put("C", 0);
        mark.put("D", 0);
        submit_answerbtn = new JButton("Submit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        String[] takeIndex = new String[] { "A", "B", "C", "D" };
        for (Map.Entry<String, Integer> entry : mark.entrySet()) {
            if (entry.getValue() == 1) {
                count++;
            }
        }
        if (cmd.equals("Random")) {
            ArrayList<String> random_question = dic.randomQuizSlang();
            one_answer.setText("A." + dic.findSlang(random_question.get(0), "random").toString());
            two_answer.setText("B." + dic.findSlang(random_question.get(1), "random").toString());
            third_answer.setText("C." + dic.findSlang(random_question.get(2), "random").toString());
            four_answer.setText("D." + dic.findSlang(random_question.get(3), "random").toString());
            Random rand = new Random();
            int index = rand.nextInt(3 - 0 + 1) + 0;
            String random = random_question.get(index);
            String random_answer = dic.findSlang((random_question.get(index)), "random").toString();
            map.put(takeIndex[index], random_answer);
            show_Slang_randow.setText(random);
        } else if (cmd.equals("Submit")) {
            System.out.println(count);
            if (count > 1 || count <= 0) {
                JOptionPane.showMessageDialog(null, "Please choose 1 answer", "Error", JOptionPane.ERROR_MESSAGE);
                count = 0;
                resetCheckBox();
            } else {
                String press = "";
                for (Map.Entry<String, Integer> entry : mark.entrySet()) {
                    if (entry.getValue() == 1) {
                        press = entry.getKey();
                        break;
                    }

                }
                if (map.containsKey(press)) {
                    JDialog d = new JDialog(jframe, "Message");
                    JPanel confirmJPanel = new JPanel();
                    confirmJPanel.setLayout(new BoxLayout(confirmJPanel, BoxLayout.Y_AXIS));
                    JLabel l = new JLabel("Correctly");
                    l.setAlignmentX(Component.CENTER_ALIGNMENT);
                    confirmJPanel.add(l);
                    JButton continuebtn = new JButton("Continue");
                    continuebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                    continuebtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            resetLable();
                            resetCheckBox();
                            count = 0;
                            d.dispose();

                        }

                    });
                    confirmJPanel.add(Box.createRigidArea(new Dimension(10, 10)));
                    confirmJPanel.add(continuebtn);

                    d.add(confirmJPanel);
                    d.setSize(200, 100);
                    d.setLocationRelativeTo(null);
                    d.setVisible(true);
                } else {
                    JDialog d = new JDialog(jframe, "Message");
                    JPanel confirmJPanel = new JPanel();
                    confirmJPanel.setLayout(new BoxLayout(confirmJPanel, BoxLayout.Y_AXIS));
                    JLabel l = new JLabel("Wrong. Try again.");
                    l.setAlignmentX(Component.CENTER_ALIGNMENT);
                    confirmJPanel.add(l);
                    JButton continuebtn = new JButton("Try again");
                    continuebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                    continuebtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            resetCheckBox();
                            count = 0;
                            d.dispose();

                        }

                    });
                    confirmJPanel.add(Box.createRigidArea(new Dimension(10, 10)));
                    confirmJPanel.add(continuebtn);

                    d.add(confirmJPanel);
                    d.setSize(200, 100);
                    d.setLocationRelativeTo(null);
                    d.setVisible(true);
                }

                count = 0;
            }

        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        JCheckBox checkbox = (JCheckBox) e.getSource();

        int value = mark.get(checkbox.getText());
        if (mark.containsKey(checkbox.getText())) {
            if (e.getStateChange() == 1) {
                mark.put(checkbox.getText(), value + 1);
            } else {
                mark.put(checkbox.getText(), value - 1);
            }
        }

    }

    public JPanel quizJPanel() {
        search_panel = new JPanel();

        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.PAGE_AXIS));

        header_search = new JPanel();
        header_search.setBackground(Color.WHITE);

        header_search.setLayout(new FlowLayout(FlowLayout.CENTER));
        header_text_title = new JLabel("Quiz Slang ");

        header_search.add(header_text_title);
        header_search.setBorder(BorderFactory.createLineBorder(Color.gray));

        content = new JPanel();
        content.setLayout(new BorderLayout());

        wrapper_answer = new JPanel();
        wrapper_answer.setBackground(Color.WHITE);
        wrapper_answer.setLayout(new BoxLayout(wrapper_answer, BoxLayout.PAGE_AXIS));

        JPanel header_content = new JPanel();
        header_content.setLayout(new FlowLayout(FlowLayout.CENTER));
        randombtn = new JButton("Randon Slang ");
        randombtn.setActionCommand("Random");
        randombtn.addActionListener(this);
        show_Slang_randow = new JLabel("");
        header_content.setMaximumSize(new Dimension(Integer.MAX_VALUE, header_content.getMinimumSize().height));
        header_content.add(randombtn);
        header_content.add(show_Slang_randow);

        JPanel fourAnswer = new JPanel();
        fourAnswer.setLayout(new GridLayout(2, 2));
        one_answer = new JLabel("Press Random to show ");
        one_answer.setHorizontalAlignment(JTextField.CENTER);
        one_answer.setBorder(BorderFactory.createLineBorder(Color.gray));
        two_answer = new JLabel("Press Random to show ");
        two_answer.setHorizontalAlignment(JTextField.CENTER);
        two_answer.setBorder(BorderFactory.createLineBorder(Color.gray));

        third_answer = new JLabel("Press Random to show ");
        third_answer.setHorizontalAlignment(JTextField.CENTER);
        third_answer.setBorder(BorderFactory.createLineBorder(Color.gray));

        four_answer = new JLabel("Press Random to show ");
        four_answer.setHorizontalAlignment(JTextField.CENTER);
        four_answer.setBorder(BorderFactory.createLineBorder(Color.gray));

        fourAnswer.add(one_answer);
        fourAnswer.add(two_answer);
        fourAnswer.add(third_answer);
        fourAnswer.add(four_answer);

        JPanel main_content = new JPanel();
        main_content.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkboxA.addItemListener(this);
        checkboxA.setActionCommand("A");
        checkboxB.addItemListener(this);
        checkboxB.setActionCommand("B");
        checkboxC.addItemListener(this);
        checkboxC.setActionCommand("C");
        checkboxD.addItemListener(this);
        checkboxD.setActionCommand("D");
        main_content.add(checkboxA);
        main_content.add(checkboxB);
        main_content.add(checkboxC);
        main_content.add(checkboxD);
        main_content.add(submit_answerbtn);
        submit_answerbtn.setActionCommand("Submit");
        submit_answerbtn.addActionListener(this);

        wrapper_answer.add(header_content);
        wrapper_answer.add(fourAnswer);
        wrapper_answer.add(main_content);
        content.add(wrapper_answer);

        search_panel.add(Box.createRigidArea(new Dimension(10, 8)));
        search_panel.add(header_search);
        search_panel.add(content);

        return search_panel;
    }

    public void resetLable() {
        one_answer.setText("Press Random to show ");
        two_answer.setText("Press Random to show ");
        third_answer.setText("Press Random to show ");
        four_answer.setText("Press Random to show ");
        show_Slang_randow.setText("<-");

    }

    public void resetCheckBox() {
        checkboxA.setSelected(false);
        checkboxB.setSelected(false);
        checkboxC.setSelected(false);
        checkboxD.setSelected(false);
    }
}
