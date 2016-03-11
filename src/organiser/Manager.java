package organiser;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;
import renamer.*;
import tablemodels.*;

public class Manager extends JFrame {

    public static File[] originalFiles;
    public static File[] renamedFiles;
    public static String[] originalFileNames;
    public static String[] renamedFileNames;
    public static String[] originalFileExtensions;
    public static String[] renamedFileExtensions;
    public static File path;
    public static int numberOfFiles;
    public static int numberOfRules;
    public static LinkedList<String> rulesList = new LinkedList<>();
    public static long lowerFileSizeLimit = -1;
    public static long upperFileSizeLimit = -1;
    public static boolean excludeFilesInRange;

    public Manager() {
        initComponents();
        addRulesTableKeyBinding();
    }

    public void setOriginalFiles(File[] files) {
        originalFiles = files;
        numberOfFiles = files.length;
        path = files[0].getParentFile();
        setOriginalFileNamesAndExtensions();
    }

    public void setOriginalFileNamesAndExtensions() {
        originalFileNames = new String[numberOfFiles];
        originalFileExtensions = new String[numberOfFiles];

        for (int i = 0; i < numberOfFiles; i++) {
            String nameWithExtension = originalFiles[i].getName();
            int lastIndexOfDot = nameWithExtension.lastIndexOf('.');
            if (lastIndexOfDot == -1) {
                originalFileNames[i] = nameWithExtension;
                originalFileExtensions[i] = "";
            }
            else {
                originalFileNames[i] = nameWithExtension.substring(0, nameWithExtension.lastIndexOf('.'));
                originalFileExtensions[i] = nameWithExtension.substring(nameWithExtension.lastIndexOf('.'), nameWithExtension.length());
            }
        }
        if (!rulesList.isEmpty()) {
            processRulesList();
        }
        setupFileNamesTable();
    }

    public static void setRenamedFileNamesAndExtensions(String[] names, String[] extensions) {
        renamedFileNames = names;
        renamedFileExtensions = extensions;
    }

    public static void setRenamedFiles() {
        String[] names = new String[numberOfFiles];
        if (renamedFileNames == null) {
            for (int i = 0; i < numberOfFiles; i++) {
                names[i] = originalFileNames[i] + renamedFileExtensions[i];
            }
        }
        else if (renamedFileExtensions == null) {
            for (int i = 0; i < numberOfFiles; i++) {
                names[i] = renamedFileNames[i] + originalFileExtensions[i];
            }
        }
        else {
            for (int i = 0; i < numberOfFiles; i++) {
                names[i] = renamedFileNames[i] + renamedFileExtensions[i];
            }
        }
        renamedFiles = new File[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            renamedFiles[i] = new File(path, names[i]);
        }
    }

    public void setupFileNamesTable() {
        fileNamesTable.setModel(new FileNamesTableModel());
        TableColumn column;
        for (int i = 0; i < 3; i++) {
            column = fileNamesTable.getColumnModel().getColumn(i);
            int width = fileNamesTable.getParent().getWidth();
            if (i == 0) {
                column.setPreferredWidth(100);
            }
            else {
                column.setPreferredWidth(width);
            }
        }
    }

    public void setupRulesTable() {
        rulesTable.setModel(new RulesTableModel());
        TableColumn column;
        int width = rulesTable.getParent().getWidth();
        for (int i = 0; i < 2; i++) {
            column = rulesTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            }
            else {
                column.setPreferredWidth(width);
            }
        }
    }

    public static String[] getFileNames() {
        if (renamedFileNames == null) {
            return originalFileNames;
        }
        else {
            return renamedFileNames;
        }
    }

    public static String[] getFileExtensions() {
        if (renamedFileExtensions == null) {
            return originalFileExtensions;
        }
        else {
            return renamedFileExtensions;
        }
    }

    public void processRulesList() {
        renamedFileNames = renamedFileExtensions = null;
        for (Iterator<String> it = rulesList.iterator(); it.hasNext();) {
            String currentRule = it.next();
            if (currentRule.startsWith("ins")) {
                Insert insert = new Insert(currentRule);
                insert.applyInsertRule();
            }
            else if (currentRule.startsWith("rem")) {
                Remove remove = new Remove(currentRule);
                remove.applyRemoveRule();
            }
            else if (currentRule.startsWith("rep")) {
                Replace replace = new Replace(currentRule);
                replace.applyReplaceRule();
            }
            else if (currentRule.startsWith("cas")) {
                ChangeCase changeCase = new ChangeCase(currentRule);
                changeCase.applyChangeCaseRule();
            }
            else if (currentRule.startsWith("ext")) {
                Extension extension = new Extension(currentRule);
                extension.applyExtensionRule();
            }
            else if (currentRule.startsWith("ser")) {
                Serialise serialise = new Serialise(currentRule);
                serialise.applySerialiseRule(currentRule);
            }
            else if (currentRule.startsWith("cle")) {
                CleanUp cleanUp = new CleanUp(currentRule);
                cleanUp.applyCleanUpRule();
            }
            else if (currentRule.startsWith("siz")) {
                FileSizeFilter fileSizeFilter = new FileSizeFilter(currentRule);
                fileSizeFilter.applyFileSizeFilterRule();
            }
        }
        setupFileNamesTable();
    }

    public void rename() {
        if (rulesList.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Renaming rules list empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (originalFileNames != null) {
            if (renamedFileNames == null && renamedFileExtensions == null) {
                processRulesList();
            }
            if (originalFileNames != null && (renamedFileNames != null || renamedFileExtensions != null)) {
                setRenamedFiles();
                int numberOfFilesNotRenamed = 0;
                boolean renameSuccessful;

                if (lowerFileSizeLimit != -1 && upperFileSizeLimit != -1) {
                    if (excludeFilesInRange) {
                        for (int i = 0; i < numberOfFiles; i++) {
                            renameSuccessful = originalFiles[i].length() <= upperFileSizeLimit && originalFiles[i].length() >= lowerFileSizeLimit ? false : originalFiles[i].renameTo(renamedFiles[i]);
                            if (!renameSuccessful) {
                                numberOfFilesNotRenamed++;
                            }
                        }
                    }
                    else {
                        for (int i = 0; i < numberOfFiles; i++) {
                            renameSuccessful = originalFiles[i].length() <= upperFileSizeLimit && originalFiles[i].length() >= lowerFileSizeLimit ? originalFiles[i].renameTo(renamedFiles[i]) : false;
                            if (!renameSuccessful) {
                                numberOfFilesNotRenamed++;
                            }
                        }
                    }
                }
                else if (lowerFileSizeLimit == -1 && upperFileSizeLimit != -1) {
                    if (excludeFilesInRange) {
                        for (int i = 0; i < numberOfFiles; i++) {
                            renameSuccessful = originalFiles[i].length() <= upperFileSizeLimit ? false : originalFiles[i].renameTo(renamedFiles[i]);
                            if (!renameSuccessful) {
                                numberOfFilesNotRenamed++;
                            }
                        }
                    }
                    else {
                        for (int i = 0; i < numberOfFiles; i++) {
                            renameSuccessful = originalFiles[i].length() <= upperFileSizeLimit ? originalFiles[i].renameTo(renamedFiles[i]) : false;
                            if (!renameSuccessful) {
                                numberOfFilesNotRenamed++;
                            }
                        }
                    }
                }
                else if (lowerFileSizeLimit != -1 && upperFileSizeLimit == -1) {
                    if (excludeFilesInRange) {
                        for (int i = 0; i < numberOfFiles; i++) {
                            renameSuccessful = originalFiles[i].length() >= lowerFileSizeLimit ? false : originalFiles[i].renameTo(renamedFiles[i]);
                            if (!renameSuccessful) {
                                numberOfFilesNotRenamed++;
                            }
                        }
                    }
                    else {
                        for (int i = 0; i < numberOfFiles; i++) {
                            renameSuccessful = originalFiles[i].length() >= lowerFileSizeLimit ? originalFiles[i].renameTo(renamedFiles[i]) : false;
                            if (!renameSuccessful) {
                                numberOfFilesNotRenamed++;
                            }
                        }
                    }
                } // rename without file-size-filter
                else {
                    for (int i = 0; i < numberOfFiles; i++) {
                        renameSuccessful = originalFiles[i].renameTo(renamedFiles[i]);
                        if (!renameSuccessful) {
                            numberOfFilesNotRenamed++;
                        }
                    }
                }
                if (numberOfFilesNotRenamed == 0) {
                    JOptionPane.showMessageDialog(this, numberOfFiles + " file(s) renamed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, numberOfFiles - numberOfFilesNotRenamed + " out of " + numberOfFiles + " file(s) renamed successfully", "Message", JOptionPane.WARNING_MESSAGE);
                }
                if (numberOfFilesNotRenamed != numberOfFiles) {
                    renamedFileNames = renamedFileExtensions = originalFileNames = originalFileExtensions = null;
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(rootPane, "No files added to rename!", "Alert!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void undoRename() {
        if (renamedFiles == null) {
            JOptionPane.showMessageDialog(rootPane, "No renaming operation carried out yet!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (originalFiles == null) {
            JOptionPane.showMessageDialog(rootPane, "Nothing to undo!", "Alert!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int numberOfFilesUndone = 0;
        for (int i = 0; i < numberOfFiles; i++) {
            boolean undoSuccessful = renamedFiles[i].renameTo(originalFiles[i]);
            if (undoSuccessful) {
                numberOfFilesUndone++;
            }
        }
        if (numberOfFilesUndone == 0) {
            JOptionPane.showMessageDialog(this, String.format("Undo operation failed"), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, String.format("Undo operation successful on %d of %d files!", numberOfFilesUndone, numberOfFiles), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        clearFilesListMenuItemActionPerformed(null);
    }

    private void addRulesTableKeyBinding() {
        KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        rulesTable.getActionMap().put("delete", deleteAction());
        rulesTable.getInputMap(JComponent.WHEN_FOCUSED).put(deleteKeyStroke, "delete");
    }

    private AbstractAction deleteAction() {
        AbstractAction action = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rulesList.isEmpty() && rulesTable.getSelectedRow() != -1) {
                    if (JOptionPane.showConfirmDialog(null, "Delete the selected rule?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        if (rulesList.get(rulesTable.getSelectedRow()).startsWith("siz")) {
                            FileSizeFilter fileSizeFilter = new FileSizeFilter("");
                            fileSizeFilter.applyFileSizeFilterRule();
                        }
                        rulesList.remove(rulesTable.getSelectedRow());
                        if (originalFileNames != null) {
                            processRulesList();
                        }
                        setupFileNamesTable();
                        setupRulesTable();
                    }
                }
            }
        };
        return action;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addFilesButton = new javax.swing.JButton();
        addRulesButton = new javax.swing.JButton();
        renameButton = new javax.swing.JButton();
        fileNamesTableScrollPane = new javax.swing.JScrollPane();
        fileNamesTable = new javax.swing.JTable();
        rulesTableScrollPane = new javax.swing.JScrollPane();
        rulesTable = new javax.swing.JTable();
        clearRulesButton = new javax.swing.JButton();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        addFilesMenuItem = new javax.swing.JMenuItem();
        addRulesMenuItem = new javax.swing.JMenuItem();
        saveRuleSetMenuItem = new javax.swing.JMenuItem();
        loadRuleSetMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        renameMenuItem = new javax.swing.JMenuItem();
        undoRenameMenuItem = new javax.swing.JMenuItem();
        clearFilesListMenuItem = new javax.swing.JMenuItem();
        clearRulesListMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        changeThemeMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Renamer");
        setName("TheMainWindow"); // NOI18N
        setResizable(false);

        addFilesButton.setMnemonic('f');
        addFilesButton.setText("Add Files");
        addFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFilesButtonActionPerformed(evt);
            }
        });

        addRulesButton.setMnemonic('a');
        addRulesButton.setText("Add Rules");
        addRulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRulesButtonActionPerformed(evt);
            }
        });

        renameButton.setMnemonic('r');
        renameButton.setText("Rename");
        renameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameButtonActionPerformed(evt);
            }
        });

        fileNamesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Serial", "Old Names", "New Names"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fileNamesTableScrollPane.setViewportView(fileNamesTable);

        rulesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Serial", "Rule Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rulesTableScrollPane.setViewportView(rulesTable);

        clearRulesButton.setText("Clear Rules");
        clearRulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearRulesButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        addFilesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        addFilesMenuItem.setText("Add Files");
        addFilesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFilesMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(addFilesMenuItem);

        addRulesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        addRulesMenuItem.setText("Add Rules");
        addRulesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRulesMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(addRulesMenuItem);

        saveRuleSetMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveRuleSetMenuItem.setText("Save RuleSet");
        saveRuleSetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveRuleSetMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveRuleSetMenuItem);

        loadRuleSetMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        loadRuleSetMenuItem.setText("Load RuleSet");
        loadRuleSetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadRuleSetMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadRuleSetMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        editMenu.setText("Edit");

        renameMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        renameMenuItem.setText("Rename");
        renameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(renameMenuItem);

        undoRenameMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        undoRenameMenuItem.setText("Undo rename");
        undoRenameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoRenameMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(undoRenameMenuItem);

        clearFilesListMenuItem.setText("Clear files list");
        clearFilesListMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFilesListMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(clearFilesListMenuItem);

        clearRulesListMenuItem.setText("Clear rules list");
        clearRulesListMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearRulesListMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(clearRulesListMenuItem);

        mainMenuBar.add(editMenu);

        helpMenu.setText("Help");

        changeThemeMenuItem.setText("Change Theme");
        changeThemeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeThemeMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(changeThemeMenuItem);

        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addFilesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addRulesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearRulesButton)
                .addGap(18, 18, 18)
                .addComponent(renameButton)
                .addContainerGap())
            .addComponent(fileNamesTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(rulesTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addFilesButton)
                    .addComponent(addRulesButton)
                    .addComponent(renameButton)
                    .addComponent(clearRulesButton))
                .addGap(18, 18, 18)
                .addComponent(rulesTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(fileNamesTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void addFilesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilesMenuItemActionPerformed
        FileChooser fileChooser = new FileChooser(path);
        File[] files = fileChooser.getFiles();

        if (files != null) {
            setOriginalFiles(files);
        }
    }//GEN-LAST:event_addFilesMenuItemActionPerformed

    private void addFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilesButtonActionPerformed
        addFilesMenuItemActionPerformed(evt);
    }//GEN-LAST:event_addFilesButtonActionPerformed

    private void addRulesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRulesButtonActionPerformed
        AddRulesDialog addRulesDialog = new AddRulesDialog(this, true);
        addRulesDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addRulesDialog.setLocationRelativeTo(this);
        addRulesDialog.setVisible(true);
        if (!rulesList.isEmpty()) {
            setupRulesTable();
            if (originalFileNames != null) {
                processRulesList();
            }
            setupFileNamesTable();
        }
    }//GEN-LAST:event_addRulesButtonActionPerformed

    private void renameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameButtonActionPerformed
        rename();
    }//GEN-LAST:event_renameButtonActionPerformed

    private void renameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameMenuItemActionPerformed
        rename();
    }//GEN-LAST:event_renameMenuItemActionPerformed

    private void undoRenameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoRenameMenuItemActionPerformed
        undoRename();
    }//GEN-LAST:event_undoRenameMenuItemActionPerformed

    private void loadRuleSetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadRuleSetMenuItemActionPerformed
        RuleSetManager ruleSetManager = new RuleSetManager();
        ruleSetManager.loadRuleSetFile();
        if (!rulesList.isEmpty()) {
            setupRulesTable();
            if (originalFileNames != null) {
                processRulesList();
            }
            setupFileNamesTable();
        }
    }//GEN-LAST:event_loadRuleSetMenuItemActionPerformed

    private void saveRuleSetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveRuleSetMenuItemActionPerformed
        RuleSetManager ruleSetManager = new RuleSetManager();
        ruleSetManager.saveRuleSet();
    }//GEN-LAST:event_saveRuleSetMenuItemActionPerformed

    private void addRulesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRulesMenuItemActionPerformed
        addRulesButtonActionPerformed(evt);
    }//GEN-LAST:event_addRulesMenuItemActionPerformed

    private void clearRulesListMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearRulesListMenuItemActionPerformed
        clearRulesButtonActionPerformed(evt);
    }//GEN-LAST:event_clearRulesListMenuItemActionPerformed

    private void clearRulesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearRulesButtonActionPerformed
        rulesList.clear();
        renamedFileNames = renamedFileExtensions = null;
        setupRulesTable();
        setupFileNamesTable();
    }//GEN-LAST:event_clearRulesButtonActionPerformed

    private void clearFilesListMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFilesListMenuItemActionPerformed
        originalFiles = null;
        originalFileNames = originalFileExtensions = renamedFileNames = renamedFileExtensions = null;
        setupFileNamesTable();
    }//GEN-LAST:event_clearFilesListMenuItemActionPerformed

    private void changeThemeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeThemeMenuItemActionPerformed
        ThemeManager themeManager = new ThemeManager(this, true);
        themeManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        themeManager.setLocationRelativeTo(this);
        themeManager.setVisible(true);
    }//GEN-LAST:event_changeThemeMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addFilesButton;
    private javax.swing.JMenuItem addFilesMenuItem;
    private javax.swing.JButton addRulesButton;
    private javax.swing.JMenuItem addRulesMenuItem;
    private javax.swing.JMenuItem changeThemeMenuItem;
    private javax.swing.JMenuItem clearFilesListMenuItem;
    private javax.swing.JButton clearRulesButton;
    private javax.swing.JMenuItem clearRulesListMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTable fileNamesTable;
    private javax.swing.JScrollPane fileNamesTableScrollPane;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem loadRuleSetMenuItem;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JButton renameButton;
    private javax.swing.JMenuItem renameMenuItem;
    private javax.swing.JTable rulesTable;
    private javax.swing.JScrollPane rulesTableScrollPane;
    private javax.swing.JMenuItem saveRuleSetMenuItem;
    private javax.swing.JMenuItem undoRenameMenuItem;
    // End of variables declaration//GEN-END:variables

}
