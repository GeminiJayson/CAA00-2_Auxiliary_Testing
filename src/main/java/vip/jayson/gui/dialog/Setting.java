/*
 * Created by JFormDesigner on Mon Nov 15 10:23:15 CST 2021
 */

package vip.jayson.gui.dialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author GeminiJayson
 */
public class Setting extends JFrame {
    public Setting() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        ATTConfig = new JPanel();
        separator1 = new JSeparator();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        testPath = new JTextField();
        compareFile = new JTextField();
        ATTConfig2 = new JPanel();
        separator2 = new JSeparator();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        testPath2 = new JTextField();
        compareFile2 = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        compareFile3 = new JTextField();
        testPath3 = new JTextField();
        label9 = new JLabel();
        testPath4 = new JTextField();
        ATTConfig3 = new JPanel();
        separator3 = new JSeparator();
        label10 = new JLabel();
        label11 = new JLabel();
        label13 = new JLabel();
        label12 = new JLabel();
        menu1 = new JMenu();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        comboBox1 = new JComboBox<>();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //======== ATTConfig ========
                {

                    //---- label1 ----
                    label1.setText("\u8f85\u52a9\u5de5\u5177\u914d\u7f6e");
                    label1.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label2 ----
                    label2.setText("\u6d4b\u8bd5\u8def\u5f84");
                    label2.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label3 ----
                    label3.setText("\u5bf9\u6bd4\u6587\u4ef6\u540d\u79f0");
                    label3.setHorizontalAlignment(SwingConstants.CENTER);

                    GroupLayout ATTConfigLayout = new GroupLayout(ATTConfig);
                    ATTConfig.setLayout(ATTConfigLayout);
                    ATTConfigLayout.setHorizontalGroup(
                        ATTConfigLayout.createParallelGroup()
                            .addGroup(ATTConfigLayout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ATTConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ATTConfigLayout.createParallelGroup()
                                    .addComponent(testPath, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(compareFile, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
                            .addComponent(separator1)
                    );
                    ATTConfigLayout.setVerticalGroup(
                        ATTConfigLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, ATTConfigLayout.createSequentialGroup()
                                .addGroup(ATTConfigLayout.createParallelGroup()
                                    .addGroup(ATTConfigLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(ATTConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(testPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(7, 7, 7)
                                        .addGroup(ATTConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(compareFile, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10))
                                    .addGroup(GroupLayout.Alignment.TRAILING, ATTConfigLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                //======== ATTConfig2 ========
                {

                    //---- label4 ----
                    label4.setText("\u6d4b\u8bd5\u914d\u7f6e");

                    //---- label5 ----
                    label5.setText("\u4e3b\u914d\u7f6e\u6587\u4ef6");
                    label5.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label6 ----
                    label6.setText("\u7801\u7ec4\u6587\u4ef6");
                    label6.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label7 ----
                    label7.setText("LK\u8f93\u5165\u8def\u5f84");
                    label7.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label8 ----
                    label8.setText("LK\u8f93\u51fa\u8def\u5f84");
                    label8.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label9 ----
                    label9.setText("LK\u914d\u7f6e\u6587\u4ef6");
                    label9.setHorizontalAlignment(SwingConstants.CENTER);

                    GroupLayout ATTConfig2Layout = new GroupLayout(ATTConfig2);
                    ATTConfig2.setLayout(ATTConfig2Layout);
                    ATTConfig2Layout.setHorizontalGroup(
                        ATTConfig2Layout.createParallelGroup()
                            .addGroup(ATTConfig2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(separator2)
                                .addContainerGap())
                            .addGroup(ATTConfig2Layout.createSequentialGroup()
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ATTConfig2Layout.createParallelGroup()
                                    .addGroup(ATTConfig2Layout.createSequentialGroup()
                                        .addGroup(ATTConfig2Layout.createParallelGroup()
                                            .addGroup(ATTConfig2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(label9))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ATTConfig2Layout.createParallelGroup()
                                            .addComponent(compareFile2)
                                            .addComponent(testPath4)
                                            .addComponent(testPath2)))
                                    .addGroup(ATTConfig2Layout.createSequentialGroup()
                                        .addGroup(ATTConfig2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(label8, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label7))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ATTConfig2Layout.createParallelGroup()
                                            .addComponent(testPath3, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                            .addComponent(compareFile3)))))
                    );
                    ATTConfig2Layout.setVerticalGroup(
                        ATTConfig2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, ATTConfig2Layout.createSequentialGroup()
                                .addGroup(ATTConfig2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(ATTConfig2Layout.createSequentialGroup()
                                        .addGroup(ATTConfig2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(testPath2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6)
                                        .addGroup(ATTConfig2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(compareFile2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ATTConfig2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label9, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(testPath4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ATTConfig2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(testPath3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ATTConfig2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(compareFile3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                //======== ATTConfig3 ========
                {

                    //---- label10 ----
                    label10.setText("\u6d4b\u8bd5\u9700\u6c42\u914d\u7f6e");

                    //---- label11 ----
                    label11.setText("IO\u5757\u987a\u5e8f");
                    label11.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label13 ----
                    label13.setText("\u5206\u914d\u53d8\u91cf\u7f16\u53f7\u987a\u5e8f");
                    label13.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- label12 ----
                    label12.setText("\u5206\u914d\u53d8\u91cf\u7f16\u53f7\u987a\u5e8f");
                    label12.setHorizontalAlignment(SwingConstants.CENTER);

                    //======== menu1 ========
                    {
                        menu1.setText("ceshi");
                        menu1.setAutoscrolls(true);

                        //---- label14 ----
                        label14.setText("1");
                        menu1.add(label14);

                        //---- label15 ----
                        label15.setText("3");
                        menu1.add(label15);

                        //---- label16 ----
                        label16.setText("2");
                        menu1.add(label16);

                        //---- comboBox1 ----
                        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                            "OC",
                            "MMI",
                            "TCC4",
                            "RBC1",
                            "RBC2",
                            "RBC3",
                            "CBI3",
                            "KDAC",
                            "RBC4",
                            "FSFB2",
                            "OLC",
                            "CCFSFB2",
                            "CCSACEM",
                            "HLCI",
                            "HLVOBC"
                        }));
                        menu1.add(comboBox1);
                    }

                    GroupLayout ATTConfig3Layout = new GroupLayout(ATTConfig3);
                    ATTConfig3.setLayout(ATTConfig3Layout);
                    ATTConfig3Layout.setHorizontalGroup(
                        ATTConfig3Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, ATTConfig3Layout.createSequentialGroup()
                                .addGroup(ATTConfig3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(separator3, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                                    .addGroup(ATTConfig3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label10)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(ATTConfig3Layout.createParallelGroup()
                                            .addComponent(label12, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(ATTConfig3Layout.createSequentialGroup()
                                                .addComponent(label11, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(menu1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(label13, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(17, 17, 17))
                    );
                    ATTConfig3Layout.setVerticalGroup(
                        ATTConfig3Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, ATTConfig3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(ATTConfig3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(ATTConfig3Layout.createSequentialGroup()
                                        .addGroup(ATTConfig3Layout.createParallelGroup()
                                            .addComponent(label11, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(menu1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label10, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
                                .addGap(134, 134, 134)
                                .addComponent(separator3, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addComponent(ATTConfig3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(ATTConfig2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ATTConfig, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 0, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addComponent(ATTConfig, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ATTConfig2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ATTConfig3, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 103, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u4fdd\u5b58");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u9000\u51fa");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel ATTConfig;
    private JSeparator separator1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField testPath;
    private JTextField compareFile;
    private JPanel ATTConfig2;
    private JSeparator separator2;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextField testPath2;
    private JTextField compareFile2;
    private JLabel label7;
    private JLabel label8;
    private JTextField compareFile3;
    private JTextField testPath3;
    private JLabel label9;
    private JTextField testPath4;
    private JPanel ATTConfig3;
    private JSeparator separator3;
    private JLabel label10;
    private JLabel label11;
    private JLabel label13;
    private JLabel label12;
    private JMenu menu1;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JComboBox<String> comboBox1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
