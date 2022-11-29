package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import utility.Utility;

public class Introduction_Interface extends InterfaceProgram{
    private JPanel introduction_gui;
    private JPanel wrapper_btn_start;
    private JButton startbtn;
    private JLabel picLabel;

    public JPanel ui_introduction() {
        introduction_gui = new JPanel();
        introduction_gui.setBackground(Color.white);
        introduction_gui.setLayout(new BoxLayout(introduction_gui, BoxLayout.Y_AXIS));
        BufferedImage myPicture;
        try {
            System.out.printf(Utility.getPathIamge());
            myPicture = ImageIO.read(new File(Utility.getPathIamge()));
            picLabel = new JLabel(new ImageIcon(myPicture));
            introduction_gui.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        wrapper_btn_start = new JPanel();
        wrapper_btn_start.setBackground(Color.WHITE);
        wrapper_btn_start.setLayout(new FlowLayout(FlowLayout.CENTER));
        startbtn= new JButton("Start");
        startbtn.setActionCommand("start");
        StartBtnListener startAcBtn=new StartBtnListener();
        startbtn.addActionListener(startAcBtn);
        wrapper_btn_start.add(startbtn);
        introduction_gui.add(wrapper_btn_start);

        return introduction_gui;
    }

}
class StartBtnListener extends InterfaceProgram implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
        String accommed = ae.getActionCommand();
        if (accommed.equals("start")) {

            CardLayout cardLayout = (CardLayout) (app.getLayout());
            cardLayout.show(app, "function");
        }

    }
}