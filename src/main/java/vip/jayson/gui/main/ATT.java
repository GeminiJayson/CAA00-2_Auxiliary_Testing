/*
 * Created by JFormDesigner on Thu Nov 11 10:54:33 CST 2021
 */

package vip.jayson.gui.main;

import vip.jayson.gui.dialog.Setting;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author GeminiJayson
 */
public class ATT extends JFrame {
    public ATT() {
        initComponents();
    }

    private void settingActionPerformed(ActionEvent e) {
        // TODO add your code here
        Setting st = new Setting();
        st.setVisible(true);
    }

    private void helpActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        toolBar1 = new JToolBar();
        setting = new JButton();
        help = new JButton();
        splitPane1 = new JSplitPane();
        scrollPane1 = new JScrollPane();
        layeredPane1 = new JLayeredPane();

        //======== this ========
        Container contentPane = getContentPane();

        //======== toolBar1 ========
        {
            toolBar1.setRollover(true);

            //---- setting ----
            setting.setText("\u4fee\u6539\u914d\u7f6e");
            setting.addActionListener(e -> settingActionPerformed(e));
            toolBar1.add(setting);
            toolBar1.addSeparator();

            //---- help ----
            help.setText("\u5e2e\u52a9");
            help.addActionListener(e -> helpActionPerformed(e));
            toolBar1.add(help);
        }

        //======== splitPane1 ========
        {
            splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
            splitPane1.setDividerLocation(100);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(layeredPane1);
            }
            splitPane1.setTopComponent(scrollPane1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(toolBar1, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                .addComponent(splitPane1, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(toolBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(splitPane1, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JToolBar toolBar1;
    private JButton setting;
    private JButton help;
    private JSplitPane splitPane1;
    private JScrollPane scrollPane1;
    private JLayeredPane layeredPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        ATT at = new ATT();
        at.setVisible(true);
    }
}

