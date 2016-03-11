package organiser;

import java.io.*;
import java.util.*;
import java.util.Formatter;
import java.util.logging.*;
import javax.swing.*;
import static organiser.AppStarter.*;

public class ThemeManager extends javax.swing.JDialog {

    public ThemeManager(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        themesComboBox.setSelectedItem(javax.swing.UIManager.getLookAndFeel().getName());
    }

    public static void populateThemeNamesMap() {
        themeNamesMap = new HashMap<>();
        themeNamesMap.put("Acryl", "com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        themeNamesMap.put("Aluminium", "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        themeNamesMap.put("Bernstein", "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        themeNamesMap.put("Graphite", "com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        themeNamesMap.put("HiFi", "com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        themeNamesMap.put("McWin", "com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        themeNamesMap.put("Mint", "com.jtattoo.plaf.mint.MintLookAndFeel");
        themeNamesMap.put("Nimbus", "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        themeNamesMap.put("Noire", "com.jtattoo.plaf.noire.NoireLookAndFeel");
        themeNamesMap.put("Texture", "com.jtattoo.plaf.texture.TextureLookAndFeel");
        themeNamesMap.put("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }

    private void applyLookAndFeel(String LookAndFeelName) {
        try {
            
            UIManager.setLookAndFeel(themeNamesMap.get(LookAndFeelName));
            SwingUtilities.updateComponentTreeUI(mainWindow);
            mainWindow.pack();
            saveCurrentTheme(LookAndFeelName);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AppStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveCurrentTheme(String currentLookAndFeelName) {
        try {
            File renamerDir = new File((new File(System.getProperty("java.io.tmpdir"))).getParentFile().getPath().concat("\\Renamer"));
            if (renamerDir.exists()) {
                (new File(renamerDir.getPath().concat("theme.ini"))).delete();
            }
            else {
                renamerDir.mkdir();
            }
            renamerDir = new File(renamerDir.getPath().concat("\\theme.ini"));
            try (Formatter formatter = new Formatter(renamerDir)) {
                formatter.format("%s", currentLookAndFeelName);
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(AppStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getLastTheme() {
        File renamerDir = new File((new File(System.getProperty("java.io.tmpdir"))).getParentFile().getPath().concat("\\Renamer\\theme.ini"));
        if (renamerDir.exists()) {
            try {
                java.util.Scanner reader = new java.util.Scanner(renamerDir);
                if (reader.hasNext()) {
                    return themeNamesMap.get(reader.nextLine());
                }
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(AppStarter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return themeNamesMap.get("Nimbus");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        promptLabel = new javax.swing.JLabel();
        themesComboBox = new javax.swing.JComboBox();
        applyThemeButton = new javax.swing.JButton();
        themeNotificationLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Themes");
        setName("ThemeManager"); // NOI18N
        setResizable(false);

        promptLabel.setText("Select a Theme from the list");

        themesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Acryl", "Aluminium", "Bernstein", "Graphite", "HiFi", "McWin", "Mint", "Nimbus", "Noire", "Texture", "Windows" }));

        applyThemeButton.setText("Apply");
        applyThemeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyThemeButtonActionPerformed(evt);
            }
        });

        themeNotificationLabel.setText("* You may need to restart Renamer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(promptLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(applyThemeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(themesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(themeNotificationLabel)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(promptLabel)
                .addGap(18, 18, 18)
                .addComponent(themesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(applyThemeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(themeNotificationLabel)
                .addGap(9, 9, 9))
        );

        themeNotificationLabel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void applyThemeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyThemeButtonActionPerformed
        applyLookAndFeel(themesComboBox.getSelectedItem().toString());
        dispose();
    }//GEN-LAST:event_applyThemeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyThemeButton;
    private javax.swing.JLabel promptLabel;
    private javax.swing.JLabel themeNotificationLabel;
    private javax.swing.JComboBox themesComboBox;
    // End of variables declaration//GEN-END:variables
}
