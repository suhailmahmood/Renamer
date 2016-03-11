package organiser;

import java.awt.Color;
import javax.swing.JOptionPane;

public class AddRulesDialog extends javax.swing.JDialog {

    Color defaultTextFieldBackgroundColor;
    String[] originalNames = Manager.getFileNames();
    String[] originalExtensions = Manager.getFileExtensions();
    int numberOfFiles = Manager.numberOfFiles;

    public AddRulesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, true);
        this.setTitle("Add Rule");
        initComponents();
        rulesList.setSelectedIndex(0);
        defaultTextFieldBackgroundColor = textToInsertTextField.getBackground();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        insertLocationButtonGroup = new javax.swing.ButtonGroup();
        removeOccurrencesButtonGroup = new javax.swing.ButtonGroup();
        replaceOccurrencesButtonGroup = new javax.swing.ButtonGroup();
        changeCaseButtonGroup = new javax.swing.ButtonGroup();
        serialiseLocationButtonGroup = new javax.swing.ButtonGroup();
        serialiseTextPadButtonGroup = new javax.swing.ButtonGroup();
        rulesListScrollPane = new javax.swing.JScrollPane();
        rulesList = new javax.swing.JList();
        addRuleButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        rulePanelsLayeredPane = new javax.swing.JLayeredPane();
        insertPanel = new javax.swing.JPanel();
        textToInsertLabel = new javax.swing.JLabel();
        textToInsertTextField = new javax.swing.JTextField();
        skipExtensionCheckBoxInsert = new javax.swing.JCheckBox();
        prefixRadioButton = new javax.swing.JRadioButton();
        suffixRadioButton = new javax.swing.JRadioButton();
        atPositionRadioButton = new javax.swing.JRadioButton();
        afterTextRadioButton = new javax.swing.JRadioButton();
        beforeTextRadioButton = new javax.swing.JRadioButton();
        afterTextTextField = new javax.swing.JTextField();
        caseSensitiveCheckBoxInsert = new javax.swing.JCheckBox();
        beforeTextTextField = new javax.swing.JTextField();
        insertAtPositionSpinner = new javax.swing.JSpinner();
        rightToLeftCheckBox = new javax.swing.JCheckBox();
        removePanel = new javax.swing.JPanel();
        textToRemoveLabel = new javax.swing.JLabel();
        textToRemoveTextField = new javax.swing.JTextField();
        allOccurrencesRadioButtonRemove = new javax.swing.JRadioButton();
        firstOccurrencesRadioButtonRemove = new javax.swing.JRadioButton();
        lastOccurrencesRadioButtonRemove = new javax.swing.JRadioButton();
        occurrencesLabel = new javax.swing.JLabel();
        skipExtensionCheckBoxRemove = new javax.swing.JCheckBox();
        caseSensitiveCheckBoxRemove = new javax.swing.JCheckBox();
        replacePanel = new javax.swing.JPanel();
        findTextLabel = new javax.swing.JLabel();
        replaceWithLabel = new javax.swing.JLabel();
        replacementTextTextField = new javax.swing.JTextField();
        findTextTextField = new javax.swing.JTextField();
        allOccurrencesRadioButtonReplace = new javax.swing.JRadioButton();
        firstOccurrencesRadioButtonReplace = new javax.swing.JRadioButton();
        lastOccurrencesRadioButtonReplace = new javax.swing.JRadioButton();
        occurrencesLabelReplace = new javax.swing.JLabel();
        skipExtensionCheckBoxReplace = new javax.swing.JCheckBox();
        caseSensitiveCheckBoxReplace = new javax.swing.JCheckBox();
        changeCasePanel = new javax.swing.JPanel();
        capitaliseFirstLetterRadioButton = new javax.swing.JRadioButton();
        capitaliseEveryWordRadioButton = new javax.swing.JRadioButton();
        invertCaseRadioButton = new javax.swing.JRadioButton();
        noteLabel = new javax.swing.JLabel();
        allUpperCaseRadioButton = new javax.swing.JRadioButton();
        allLowerCaseRadioButton = new javax.swing.JRadioButton();
        extensionsPanel = new javax.swing.JPanel();
        appendToOriginalFileNamesCheckBox = new javax.swing.JCheckBox();
        newExtensionLabel = new javax.swing.JLabel();
        extensionTextField = new javax.swing.JTextField();
        serialisePanel = new javax.swing.JPanel();
        indexLabel = new javax.swing.JLabel();
        indexSpinnerSerialise = new javax.swing.JSpinner();
        stepSpinnerSerialise = new javax.swing.JSpinner();
        stepLabel = new javax.swing.JLabel();
        prefixRadioButtonSerialise = new javax.swing.JRadioButton();
        suffixRadioButtonSerialise = new javax.swing.JRadioButton();
        atPositionRadioButtonSerialise = new javax.swing.JRadioButton();
        locationLabelSerialise = new javax.swing.JLabel();
        atPositionSpinnerSerialise = new javax.swing.JSpinner();
        skipExtensionCheckBoxSerialise = new javax.swing.JCheckBox();
        lengthOfSerialSpinner = new javax.swing.JSpinner();
        padNoteLabel = new javax.swing.JLabel();
        lengthOfSerialCheckBox = new javax.swing.JCheckBox();
        serialLabel = new javax.swing.JLabel();
        textToAddWithSerialLabel = new javax.swing.JLabel();
        textToAddWithSerialTextField = new javax.swing.JTextField();
        textBeforeSerialRadioButton = new javax.swing.JRadioButton();
        textAfterSerialRadioButton = new javax.swing.JRadioButton();
        cleanUpPanel = new javax.swing.JPanel();
        alphabetsCheckBox = new javax.swing.JCheckBox();
        alphabetsTextField = new javax.swing.JTextField();
        digitsTextField = new javax.swing.JTextField();
        digitsCheckBox = new javax.swing.JCheckBox();
        symbolsTextField = new javax.swing.JTextField();
        symbolsCheckBox = new javax.swing.JCheckBox();
        bracketsTextField = new javax.swing.JTextField();
        bracketsCheckBox = new javax.swing.JCheckBox();
        userDefinedTextField = new javax.swing.JTextField();
        userDefinedCheckBox = new javax.swing.JCheckBox();
        skipExtensionCheckBoxCleanup = new javax.swing.JCheckBox();
        caseInfoLabel = new javax.swing.JLabel();
        fileSizeFilterPanel = new javax.swing.JPanel();
        lowerLimitCheckBox = new javax.swing.JCheckBox();
        lowerLimitSpinner = new javax.swing.JSpinner();
        upperLimitSpinner = new javax.swing.JSpinner();
        upperLimitCheckBox = new javax.swing.JCheckBox();
        lowerLimitComboBox = new javax.swing.JComboBox();
        upperLimitComboBox = new javax.swing.JComboBox();
        excludeRangeCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        rulesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Insert", "Remove", "Replace", "Change Case", "Extension", "Serialise", "Clean Up", "File-size filter" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        rulesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        rulesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                rulesListValueChanged(evt);
            }
        });
        rulesListScrollPane.setViewportView(rulesList);

        addRuleButton.setMnemonic('a');
        addRuleButton.setText("Add Rule");
        addRuleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRuleButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        insertPanel.setPreferredSize(new java.awt.Dimension(310, 250));
        insertPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textToInsertLabel.setText("Text to Insert:");
        insertPanel.add(textToInsertLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        textToInsertTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textToInsertTextFieldFocusGained(evt);
            }
        });
        insertPanel.add(textToInsertTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 200, -1));

        skipExtensionCheckBoxInsert.setSelected(true);
        skipExtensionCheckBoxInsert.setText("Skip Extension");
        insertPanel.add(skipExtensionCheckBoxInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));

        insertLocationButtonGroup.add(prefixRadioButton);
        prefixRadioButton.setSelected(true);
        prefixRadioButton.setText("Prefix");
        insertPanel.add(prefixRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 39, -1, -1));

        insertLocationButtonGroup.add(suffixRadioButton);
        suffixRadioButton.setText("Suffix");
        insertPanel.add(suffixRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 68, -1, -1));

        insertLocationButtonGroup.add(atPositionRadioButton);
        atPositionRadioButton.setText("At position:");
        insertPanel.add(atPositionRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 97, -1, -1));

        insertLocationButtonGroup.add(afterTextRadioButton);
        afterTextRadioButton.setText("After text:");
        afterTextRadioButton.setMaximumSize(new java.awt.Dimension(150, 23));
        afterTextRadioButton.setMinimumSize(new java.awt.Dimension(50, 23));
        afterTextRadioButton.setPreferredSize(new java.awt.Dimension(93, 23));
        insertPanel.add(afterTextRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 126, 110, -1));

        insertLocationButtonGroup.add(beforeTextRadioButton);
        beforeTextRadioButton.setText("Before text:");
        beforeTextRadioButton.setMaximumSize(new java.awt.Dimension(150, 23));
        beforeTextRadioButton.setMinimumSize(new java.awt.Dimension(50, 23));
        beforeTextRadioButton.setPreferredSize(new java.awt.Dimension(93, 23));
        insertPanel.add(beforeTextRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 155, 110, -1));

        afterTextTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                afterTextTextFieldFocusGained(evt);
            }
        });
        insertPanel.add(afterTextTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 140, -1));

        caseSensitiveCheckBoxInsert.setText("Case Sensitive");
        insertPanel.add(caseSensitiveCheckBoxInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, -1));

        beforeTextTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                beforeTextTextFieldFocusGained(evt);
            }
        });
        insertPanel.add(beforeTextTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 140, -1));

        insertAtPositionSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        insertAtPositionSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                insertAtPositionSpinnerStateChanged(evt);
            }
        });
        insertPanel.add(insertAtPositionSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 51, -1));

        rightToLeftCheckBox.setText("Right-to-Left");
        rightToLeftCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rightToLeftCheckBoxItemStateChanged(evt);
            }
        });
        insertPanel.add(rightToLeftCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, -1, -1));

        rulePanelsLayeredPane.add(insertPanel);
        insertPanel.setBounds(0, 0, 310, 210);

        removePanel.setPreferredSize(new java.awt.Dimension(310, 250));

        textToRemoveLabel.setText("Text to Remove:");

        textToRemoveTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textToRemoveTextFieldFocusGained(evt);
            }
        });

        removeOccurrencesButtonGroup.add(allOccurrencesRadioButtonRemove);
        allOccurrencesRadioButtonRemove.setSelected(true);
        allOccurrencesRadioButtonRemove.setText("All");

        removeOccurrencesButtonGroup.add(firstOccurrencesRadioButtonRemove);
        firstOccurrencesRadioButtonRemove.setText("First");

        removeOccurrencesButtonGroup.add(lastOccurrencesRadioButtonRemove);
        lastOccurrencesRadioButtonRemove.setText("Last");

        occurrencesLabel.setText("Occurrences:");

        skipExtensionCheckBoxRemove.setSelected(true);
        skipExtensionCheckBoxRemove.setText("Skip Extension");

        caseSensitiveCheckBoxRemove.setText("Case Sensitive");

        javax.swing.GroupLayout removePanelLayout = new javax.swing.GroupLayout(removePanel);
        removePanel.setLayout(removePanelLayout);
        removePanelLayout.setHorizontalGroup(
            removePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removePanelLayout.createSequentialGroup()
                .addGroup(removePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(removePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textToRemoveLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textToRemoveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(removePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(occurrencesLabel))
                    .addGroup(removePanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(allOccurrencesRadioButtonRemove)
                        .addGap(32, 32, 32)
                        .addComponent(firstOccurrencesRadioButtonRemove)
                        .addGap(35, 35, 35)
                        .addComponent(lastOccurrencesRadioButtonRemove)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(removePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(caseSensitiveCheckBoxRemove)
                    .addComponent(skipExtensionCheckBoxRemove))
                .addGap(39, 39, 39))
        );
        removePanelLayout.setVerticalGroup(
            removePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(removePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textToRemoveLabel)
                    .addComponent(textToRemoveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(skipExtensionCheckBoxRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caseSensitiveCheckBoxRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(occurrencesLabel)
                .addGap(18, 18, 18)
                .addGroup(removePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allOccurrencesRadioButtonRemove)
                    .addComponent(firstOccurrencesRadioButtonRemove)
                    .addComponent(lastOccurrencesRadioButtonRemove))
                .addGap(87, 87, 87))
        );

        rulePanelsLayeredPane.add(removePanel);
        removePanel.setBounds(0, 0, 310, 250);

        replacePanel.setPreferredSize(new java.awt.Dimension(310, 250));

        findTextLabel.setText("      Find text:");

        replaceWithLabel.setText("Replace with:");

        replacementTextTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                replacementTextTextFieldFocusGained(evt);
            }
        });

        findTextTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                findTextTextFieldFocusGained(evt);
            }
        });

        replaceOccurrencesButtonGroup.add(allOccurrencesRadioButtonReplace);
        allOccurrencesRadioButtonReplace.setSelected(true);
        allOccurrencesRadioButtonReplace.setText("All");

        replaceOccurrencesButtonGroup.add(firstOccurrencesRadioButtonReplace);
        firstOccurrencesRadioButtonReplace.setText("First");

        replaceOccurrencesButtonGroup.add(lastOccurrencesRadioButtonReplace);
        lastOccurrencesRadioButtonReplace.setText("Last");

        occurrencesLabelReplace.setText("Occurrences:");

        skipExtensionCheckBoxReplace.setSelected(true);
        skipExtensionCheckBoxReplace.setText("Skip Extension");

        caseSensitiveCheckBoxReplace.setText("Case Sensitive");

        javax.swing.GroupLayout replacePanelLayout = new javax.swing.GroupLayout(replacePanel);
        replacePanel.setLayout(replacePanelLayout);
        replacePanelLayout.setHorizontalGroup(
            replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(replacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(skipExtensionCheckBoxReplace)
                    .addComponent(caseSensitiveCheckBoxReplace)
                    .addGroup(replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(occurrencesLabelReplace)
                        .addGroup(replacePanelLayout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addComponent(allOccurrencesRadioButtonReplace)
                            .addGap(18, 18, 18)
                            .addComponent(firstOccurrencesRadioButtonReplace)
                            .addGap(18, 18, 18)
                            .addComponent(lastOccurrencesRadioButtonReplace))
                        .addGroup(replacePanelLayout.createSequentialGroup()
                            .addGroup(replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(replaceWithLabel)
                                .addComponent(findTextLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(findTextTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(replacementTextTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        replacePanelLayout.setVerticalGroup(
            replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(replacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(findTextLabel)
                    .addComponent(findTextTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(replaceWithLabel)
                    .addComponent(replacementTextTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(skipExtensionCheckBoxReplace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caseSensitiveCheckBoxReplace)
                .addGap(18, 18, 18)
                .addComponent(occurrencesLabelReplace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(replacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allOccurrencesRadioButtonReplace)
                    .addComponent(firstOccurrencesRadioButtonReplace)
                    .addComponent(lastOccurrencesRadioButtonReplace))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        rulePanelsLayeredPane.add(replacePanel);
        replacePanel.setBounds(0, 0, 310, 250);

        changeCasePanel.setPreferredSize(new java.awt.Dimension(310, 250));

        changeCaseButtonGroup.add(capitaliseFirstLetterRadioButton);
        capitaliseFirstLetterRadioButton.setText("Capitalise first letter only");

        changeCaseButtonGroup.add(capitaliseEveryWordRadioButton);
        capitaliseEveryWordRadioButton.setSelected(true);
        capitaliseEveryWordRadioButton.setText("Capitalise First Letter Of Every Word");

        changeCaseButtonGroup.add(invertCaseRadioButton);
        invertCaseRadioButton.setText("iNVERT cASE");

        noteLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        noteLabel.setText("Note: To affect extension, use 'Extension' rule");

        changeCaseButtonGroup.add(allUpperCaseRadioButton);
        allUpperCaseRadioButton.setText("ALL UPPER CASE");

        changeCaseButtonGroup.add(allLowerCaseRadioButton);
        allLowerCaseRadioButton.setText("all lower case");

        javax.swing.GroupLayout changeCasePanelLayout = new javax.swing.GroupLayout(changeCasePanel);
        changeCasePanel.setLayout(changeCasePanelLayout);
        changeCasePanelLayout.setHorizontalGroup(
            changeCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeCasePanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(changeCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(noteLabel)
                    .addComponent(invertCaseRadioButton)
                    .addComponent(allLowerCaseRadioButton)
                    .addComponent(allUpperCaseRadioButton)
                    .addComponent(capitaliseFirstLetterRadioButton)
                    .addComponent(capitaliseEveryWordRadioButton))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        changeCasePanelLayout.setVerticalGroup(
            changeCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeCasePanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(capitaliseEveryWordRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(capitaliseFirstLetterRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allUpperCaseRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allLowerCaseRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invertCaseRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noteLabel)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        rulePanelsLayeredPane.add(changeCasePanel);
        changeCasePanel.setBounds(0, 0, 310, 250);

        extensionsPanel.setPreferredSize(new java.awt.Dimension(310, 250));

        appendToOriginalFileNamesCheckBox.setText("Append to original file names");

        newExtensionLabel.setText("New Extension (without dot):");

        extensionTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                extensionTextFieldFocusGained(evt);
            }
        });

        javax.swing.GroupLayout extensionsPanelLayout = new javax.swing.GroupLayout(extensionsPanel);
        extensionsPanel.setLayout(extensionsPanelLayout);
        extensionsPanelLayout.setHorizontalGroup(
            extensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extensionsPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(extensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appendToOriginalFileNamesCheckBox)
                    .addComponent(newExtensionLabel)
                    .addComponent(extensionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        extensionsPanelLayout.setVerticalGroup(
            extensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extensionsPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(newExtensionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extensionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(appendToOriginalFileNamesCheckBox)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        rulePanelsLayeredPane.add(extensionsPanel);
        extensionsPanel.setBounds(0, 0, 310, 250);

        serialisePanel.setMinimumSize(new java.awt.Dimension(310, 250));
        serialisePanel.setPreferredSize(new java.awt.Dimension(320, 230));

        indexLabel.setText("Index starts:");

        indexSpinnerSerialise.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));

        stepSpinnerSerialise.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), null, Integer.valueOf(100), Integer.valueOf(1)));

        stepLabel.setText("Step:");

        serialiseLocationButtonGroup.add(prefixRadioButtonSerialise);
        prefixRadioButtonSerialise.setSelected(true);
        prefixRadioButtonSerialise.setText("Prefix");
        prefixRadioButtonSerialise.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                prefixRadioButtonSerialiseItemStateChanged(evt);
            }
        });

        serialiseLocationButtonGroup.add(suffixRadioButtonSerialise);
        suffixRadioButtonSerialise.setText("Suffix");
        suffixRadioButtonSerialise.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                suffixRadioButtonSerialiseItemStateChanged(evt);
            }
        });

        serialiseLocationButtonGroup.add(atPositionRadioButtonSerialise);
        atPositionRadioButtonSerialise.setText("Position:");
        atPositionRadioButtonSerialise.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                atPositionRadioButtonSerialiseItemStateChanged(evt);
            }
        });

        locationLabelSerialise.setText("Location:");

        atPositionSpinnerSerialise.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        atPositionSpinnerSerialise.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                atPositionSpinnerSerialiseStateChanged(evt);
            }
        });

        skipExtensionCheckBoxSerialise.setSelected(true);
        skipExtensionCheckBoxSerialise.setText("Skip Extension");
        skipExtensionCheckBoxSerialise.setEnabled(false);

        lengthOfSerialSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 244, 1));
        lengthOfSerialSpinner.setEnabled(false);

        padNoteLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        padNoteLabel.setText("*padded with zeros to reach length");

        lengthOfSerialCheckBox.setText("*Length:");
        lengthOfSerialCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lengthOfSerialCheckBoxItemStateChanged(evt);
            }
        });

        serialLabel.setText("Serial:");

        textToAddWithSerialLabel.setText("Insert this with serial:");

        textToAddWithSerialTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textToAddWithSerialTextFieldFocusGained(evt);
            }
        });

        serialiseTextPadButtonGroup.add(textBeforeSerialRadioButton);
        textBeforeSerialRadioButton.setSelected(true);
        textBeforeSerialRadioButton.setText("Before serial");

        serialiseTextPadButtonGroup.add(textAfterSerialRadioButton);
        textAfterSerialRadioButton.setText("After serial");

        javax.swing.GroupLayout serialisePanelLayout = new javax.swing.GroupLayout(serialisePanel);
        serialisePanel.setLayout(serialisePanelLayout);
        serialisePanelLayout.setHorizontalGroup(
            serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serialisePanelLayout.createSequentialGroup()
                .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(serialisePanelLayout.createSequentialGroup()
                        .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(serialisePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(padNoteLabel)
                                    .addComponent(lengthOfSerialCheckBox)))
                            .addGroup(serialisePanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lengthOfSerialSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(serialisePanelLayout.createSequentialGroup()
                                        .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(indexLabel)
                                            .addComponent(stepLabel)
                                            .addComponent(serialLabel))
                                        .addGap(0, 0, 0)
                                        .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(stepSpinnerSerialise, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(indexSpinnerSerialise, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prefixRadioButtonSerialise)
                            .addComponent(suffixRadioButtonSerialise)
                            .addGroup(serialisePanelLayout.createSequentialGroup()
                                .addComponent(atPositionRadioButtonSerialise)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(atPositionSpinnerSerialise, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(locationLabelSerialise)
                            .addComponent(skipExtensionCheckBoxSerialise)))
                    .addGroup(serialisePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textToAddWithSerialLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textToAddWithSerialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(serialisePanelLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(textBeforeSerialRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(textAfterSerialRadioButton)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        serialisePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {indexLabel, stepLabel});

        serialisePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {indexSpinnerSerialise, lengthOfSerialSpinner, stepSpinnerSerialise});

        serialisePanelLayout.setVerticalGroup(
            serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serialisePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationLabelSerialise)
                    .addComponent(serialLabel))
                .addGap(5, 5, 5)
                .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(serialisePanelLayout.createSequentialGroup()
                        .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(indexLabel)
                            .addComponent(indexSpinnerSerialise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stepLabel)
                            .addComponent(stepSpinnerSerialise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lengthOfSerialSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lengthOfSerialCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(padNoteLabel))
                    .addGroup(serialisePanelLayout.createSequentialGroup()
                        .addComponent(prefixRadioButtonSerialise)
                        .addGap(6, 6, 6)
                        .addComponent(suffixRadioButtonSerialise)
                        .addGap(6, 6, 6)
                        .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(atPositionRadioButtonSerialise)
                            .addComponent(atPositionSpinnerSerialise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(skipExtensionCheckBoxSerialise)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textToAddWithSerialLabel)
                    .addComponent(textToAddWithSerialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(serialisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBeforeSerialRadioButton)
                    .addComponent(textAfterSerialRadioButton))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        rulePanelsLayeredPane.add(serialisePanel);
        serialisePanel.setBounds(0, 0, 320, 230);

        alphabetsCheckBox.setText("Alphabets:");

        alphabetsTextField.setEditable(false);
        alphabetsTextField.setText("abcdefghijklmnopqrstuvwxyz");

        digitsTextField.setEditable(false);
        digitsTextField.setText("0123456789");

        digitsCheckBox.setText("Digits:");

        symbolsTextField.setEditable(false);
        symbolsTextField.setText("!?@#$%^&~`_+-=.,'");

        symbolsCheckBox.setText("Symbols:");

        bracketsTextField.setEditable(false);
        bracketsTextField.setText("(){}[]");

        bracketsCheckBox.setText("Brackets:");

        userDefinedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userDefinedTextFieldFocusGained(evt);
            }
        });

        userDefinedCheckBox.setText("User defined:");

        skipExtensionCheckBoxCleanup.setSelected(true);
        skipExtensionCheckBoxCleanup.setText("Skip Extension");

        caseInfoLabel.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        caseInfoLabel.setText("Note: User defined letters are case-insensitive");

        javax.swing.GroupLayout cleanUpPanelLayout = new javax.swing.GroupLayout(cleanUpPanel);
        cleanUpPanel.setLayout(cleanUpPanelLayout);
        cleanUpPanelLayout.setHorizontalGroup(
            cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cleanUpPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cleanUpPanelLayout.createSequentialGroup()
                        .addComponent(caseInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(skipExtensionCheckBoxCleanup)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cleanUpPanelLayout.createSequentialGroup()
                        .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bracketsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(symbolsCheckBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(digitsCheckBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(alphabetsCheckBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userDefinedCheckBox))
                        .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(cleanUpPanelLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(alphabetsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(digitsTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cleanUpPanelLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(symbolsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cleanUpPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bracketsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userDefinedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23))))
        );
        cleanUpPanelLayout.setVerticalGroup(
            cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cleanUpPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alphabetsCheckBox)
                    .addComponent(alphabetsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(digitsCheckBox)
                    .addComponent(digitsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(symbolsCheckBox)
                    .addComponent(symbolsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bracketsCheckBox)
                    .addComponent(bracketsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userDefinedCheckBox)
                    .addComponent(userDefinedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cleanUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(skipExtensionCheckBoxCleanup)
                    .addComponent(caseInfoLabel))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        rulePanelsLayeredPane.add(cleanUpPanel);
        cleanUpPanel.setBounds(0, 0, 326, 250);

        fileSizeFilterPanel.setPreferredSize(new java.awt.Dimension(310, 250));

        lowerLimitCheckBox.setText("Lower Limit");

        lowerLimitSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2048, 1));

        upperLimitSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2048, 1));

        upperLimitCheckBox.setText("Upper Limit");

        lowerLimitComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "B", "KB", "MB" }));

        upperLimitComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "B", "KB", "MB" }));

        excludeRangeCheckBox.setText("Exclude files in this range");

        javax.swing.GroupLayout fileSizeFilterPanelLayout = new javax.swing.GroupLayout(fileSizeFilterPanel);
        fileSizeFilterPanel.setLayout(fileSizeFilterPanelLayout);
        fileSizeFilterPanelLayout.setHorizontalGroup(
            fileSizeFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileSizeFilterPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(fileSizeFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fileSizeFilterPanelLayout.createSequentialGroup()
                        .addComponent(excludeRangeCheckBox)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(fileSizeFilterPanelLayout.createSequentialGroup()
                        .addGroup(fileSizeFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fileSizeFilterPanelLayout.createSequentialGroup()
                                .addComponent(lowerLimitCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lowerLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 11, Short.MAX_VALUE)
                                .addComponent(lowerLimitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(fileSizeFilterPanelLayout.createSequentialGroup()
                                .addComponent(upperLimitCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(upperLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(upperLimitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(76, 76, 76))))
        );

        fileSizeFilterPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lowerLimitCheckBox, upperLimitCheckBox});

        fileSizeFilterPanelLayout.setVerticalGroup(
            fileSizeFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileSizeFilterPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(fileSizeFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lowerLimitCheckBox)
                    .addComponent(lowerLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lowerLimitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fileSizeFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upperLimitCheckBox)
                    .addComponent(upperLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upperLimitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(excludeRangeCheckBox)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        rulePanelsLayeredPane.add(fileSizeFilterPanel);
        fileSizeFilterPanel.setBounds(0, 0, 340, 200);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(addRuleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rulesListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rulePanelsLayeredPane)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rulePanelsLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rulesListScrollPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRuleButton)
                    .addComponent(cancelButton))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addRuleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRuleButtonActionPerformed
        if (rulesList.getSelectedIndex() == 0) {
            invokeInsert();
        } else if (rulesList.getSelectedIndex() == 1) {
            invokeRemove();
        } else if (rulesList.getSelectedIndex() == 2) {
            invokeReplace();
        } else if (rulesList.getSelectedIndex() == 3) {
            invokeChangeCase();
        } else if (rulesList.getSelectedIndex() == 4) {
            invokeExtension();
        } else if (rulesList.getSelectedIndex() == 5) {
            invokeSerialise();
        } else if (rulesList.getSelectedIndex() == 6) {
            invokeCleanUp();
        } else if (rulesList.getSelectedIndex() == 7) {
            invokeFileSizeFilter();
        }
    }//GEN-LAST:event_addRuleButtonActionPerformed

    private void rulesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_rulesListValueChanged
        if (rulesList.getSelectedIndex() == 0) {
            insertPanel.setVisible(true);
            removePanel.setVisible(false);
            replacePanel.setVisible(false);
            changeCasePanel.setVisible(false);
            extensionsPanel.setVisible(false);
            serialisePanel.setVisible(false);
            cleanUpPanel.setVisible(false);
            fileSizeFilterPanel.setVisible(false);
        } else if (rulesList.getSelectedIndex() == 1) {
            insertPanel.setVisible(false);
            removePanel.setVisible(true);
            replacePanel.setVisible(false);
            changeCasePanel.setVisible(false);
            extensionsPanel.setVisible(false);
            serialisePanel.setVisible(false);
            cleanUpPanel.setVisible(false);
            fileSizeFilterPanel.setVisible(false);
        } else if (rulesList.getSelectedIndex() == 2) {
            insertPanel.setVisible(false);
            removePanel.setVisible(false);
            replacePanel.setVisible(true);
            changeCasePanel.setVisible(false);
            extensionsPanel.setVisible(false);
            serialisePanel.setVisible(false);
            cleanUpPanel.setVisible(false);
            fileSizeFilterPanel.setVisible(false);
        } else if (rulesList.getSelectedIndex() == 3) {
            insertPanel.setVisible(false);
            removePanel.setVisible(false);
            replacePanel.setVisible(false);
            changeCasePanel.setVisible(true);
            extensionsPanel.setVisible(false);
            serialisePanel.setVisible(false);
            cleanUpPanel.setVisible(false);
            fileSizeFilterPanel.setVisible(false);
        } else if (rulesList.getSelectedIndex() == 4) {
            insertPanel.setVisible(false);
            removePanel.setVisible(false);
            replacePanel.setVisible(false);
            changeCasePanel.setVisible(false);
            extensionsPanel.setVisible(true);
            serialisePanel.setVisible(false);
            cleanUpPanel.setVisible(false);
            fileSizeFilterPanel.setVisible(false);
        } else if (rulesList.getSelectedIndex() == 5) {
            insertPanel.setVisible(false);
            removePanel.setVisible(false);
            replacePanel.setVisible(false);
            changeCasePanel.setVisible(false);
            extensionsPanel.setVisible(false);
            serialisePanel.setVisible(true);
            cleanUpPanel.setVisible(false);
            fileSizeFilterPanel.setVisible(false);
        } else if (rulesList.getSelectedIndex() == 6) {
            insertPanel.setVisible(false);
            removePanel.setVisible(false);
            replacePanel.setVisible(false);
            changeCasePanel.setVisible(false);
            extensionsPanel.setVisible(false);
            serialisePanel.setVisible(false);
            cleanUpPanel.setVisible(true);
            fileSizeFilterPanel.setVisible(false);
        } else if (rulesList.getSelectedIndex() == 7) {
            insertPanel.setVisible(false);
            removePanel.setVisible(false);
            replacePanel.setVisible(false);
            changeCasePanel.setVisible(false);
            extensionsPanel.setVisible(false);
            serialisePanel.setVisible(false);
            cleanUpPanel.setVisible(false);
            fileSizeFilterPanel.setVisible(true);
        }
    }//GEN-LAST:event_rulesListValueChanged

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void afterTextTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_afterTextTextFieldFocusGained
        afterTextRadioButton.setSelected(true);
    }//GEN-LAST:event_afterTextTextFieldFocusGained

    private void beforeTextTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_beforeTextTextFieldFocusGained
        beforeTextRadioButton.setSelected(true);
    }//GEN-LAST:event_beforeTextTextFieldFocusGained

    private void insertAtPositionSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_insertAtPositionSpinnerStateChanged
        atPositionRadioButton.setSelected(true);
    }//GEN-LAST:event_insertAtPositionSpinnerStateChanged

    private void atPositionSpinnerSerialiseStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_atPositionSpinnerSerialiseStateChanged
        atPositionRadioButtonSerialise.setSelected(true);
    }//GEN-LAST:event_atPositionSpinnerSerialiseStateChanged

    private void suffixRadioButtonSerialiseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_suffixRadioButtonSerialiseItemStateChanged
        skipExtensionCheckBoxSerialise.setEnabled(true);
    }//GEN-LAST:event_suffixRadioButtonSerialiseItemStateChanged

    private void atPositionRadioButtonSerialiseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_atPositionRadioButtonSerialiseItemStateChanged
        skipExtensionCheckBoxSerialise.setEnabled(true);
    }//GEN-LAST:event_atPositionRadioButtonSerialiseItemStateChanged

    private void prefixRadioButtonSerialiseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_prefixRadioButtonSerialiseItemStateChanged
        skipExtensionCheckBoxSerialise.setEnabled(false);
    }//GEN-LAST:event_prefixRadioButtonSerialiseItemStateChanged

    private void lengthOfSerialCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lengthOfSerialCheckBoxItemStateChanged
        if (lengthOfSerialCheckBox.isSelected()) {
            lengthOfSerialSpinner.setEnabled(true);
        } else {
            lengthOfSerialSpinner.setEnabled(false);
        }
    }//GEN-LAST:event_lengthOfSerialCheckBoxItemStateChanged

    private void rightToLeftCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rightToLeftCheckBoxItemStateChanged
        atPositionRadioButton.setSelected(true);
    }//GEN-LAST:event_rightToLeftCheckBoxItemStateChanged

    private void userDefinedTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userDefinedTextFieldFocusGained
        userDefinedCheckBox.setSelected(true);
    }//GEN-LAST:event_userDefinedTextFieldFocusGained

    private void textToInsertTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textToInsertTextFieldFocusGained
        textToInsertTextField.setBackground(defaultTextFieldBackgroundColor);
    }//GEN-LAST:event_textToInsertTextFieldFocusGained

    private void textToRemoveTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textToRemoveTextFieldFocusGained
        textToRemoveTextField.setBackground(defaultTextFieldBackgroundColor);
    }//GEN-LAST:event_textToRemoveTextFieldFocusGained

    private void findTextTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_findTextTextFieldFocusGained
        findTextTextField.setBackground(defaultTextFieldBackgroundColor);
    }//GEN-LAST:event_findTextTextFieldFocusGained

    private void replacementTextTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_replacementTextTextFieldFocusGained
        replacementTextTextField.setBackground(defaultTextFieldBackgroundColor);
    }//GEN-LAST:event_replacementTextTextFieldFocusGained

    private void extensionTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_extensionTextFieldFocusGained
        extensionTextField.setBackground(defaultTextFieldBackgroundColor);
    }//GEN-LAST:event_extensionTextFieldFocusGained

    private void textToAddWithSerialTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textToAddWithSerialTextFieldFocusGained
        textToAddWithSerialTextField.setBackground(defaultTextFieldBackgroundColor);
    }//GEN-LAST:event_textToAddWithSerialTextFieldFocusGained

    public void invokeInsert() {
        String textToInsert = textToInsertTextField.getText();
        boolean dispose = true;
        if (textToInsert.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Text to insert is empty!");
            textToInsertTextField.setBackground(Color.YELLOW);
        } else if (!isValidName(textToInsert)) {
            JOptionPane.showMessageDialog(rootPane, "Text to insert contains invalid character(s)");
            textToInsertTextField.setBackground(Color.RED);
        } else {
            String insertRuleString = String.format("ins:%s:", textToInsert);
            boolean skipExtension;
            boolean caseSensitive;

            if (prefixRadioButton.isSelected()) {
                insertRuleString += "pre";
            } else if (suffixRadioButton.isSelected()) {
                skipExtension = skipExtensionCheckBoxInsert.isSelected();
                insertRuleString += "suf:" + skipExtension;
            } else if (atPositionRadioButton.isSelected()) {
                String pos = insertAtPositionSpinner.getValue().toString();
                skipExtension = skipExtensionCheckBoxInsert.isSelected();
                boolean rightToLeft = rightToLeftCheckBox.isSelected();
                insertRuleString += String.format("pos:%s:%s:%s", pos, skipExtension, rightToLeft);
            } else if (afterTextRadioButton.isSelected()) {
                String searchText = afterTextTextField.getText();
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Search text can't be empty!");
                    dispose = false;
                } else {
                    skipExtension = skipExtensionCheckBoxInsert.isSelected();
                    caseSensitive = caseSensitiveCheckBoxInsert.isSelected();
                    insertRuleString += String.format("aft:%s:%s:%s", searchText, skipExtension, caseSensitive);
                    dispose = true;
                }
            } else if (beforeTextRadioButton.isSelected()) {
                String searchText = beforeTextTextField.getText();
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Search text can't be empty!");
                    dispose = false;
                } else {
                    skipExtension = skipExtensionCheckBoxInsert.isSelected();
                    caseSensitive = caseSensitiveCheckBoxInsert.isSelected();
                    insertRuleString += String.format("bef:%s:%s:%s", searchText, skipExtension, caseSensitive);
                    dispose = true;
                }
            }
            if (dispose) {
                Manager.rulesList.add(insertRuleString);
                dispose();
            }
        }
    }

    public void invokeRemove() {
        String textToRemove = textToRemoveTextField.getText();
        if (textToRemove.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Text to Remove field is empty!");
            textToRemoveTextField.setBackground(Color.yellow);
        } else if (!isValidName(textToRemove)) {
            JOptionPane.showMessageDialog(rootPane, "Text to remove contains invalid character(s)");
            textToRemoveTextField.setBackground(Color.red);
        } else {
            String removeRuleString = String.format("rem:%s:", textToRemove);
            boolean skipExtension = skipExtensionCheckBoxRemove.isSelected();
            boolean caseSensitive = caseSensitiveCheckBoxRemove.isSelected();

            if (allOccurrencesRadioButtonRemove.isSelected()) {
                removeRuleString += String.format("all:%s:%s", skipExtension, caseSensitive);
            } else if (firstOccurrencesRadioButtonRemove.isSelected()) {
                removeRuleString += String.format("first:%s:%s", skipExtension, caseSensitive);
            } else if (lastOccurrencesRadioButtonRemove.isSelected()) {
                removeRuleString += String.format("last:%s:%s", skipExtension, caseSensitive);
            }
            Manager.rulesList.add(removeRuleString);
            dispose();
        }
    }

    public void invokeReplace() {
        String textToFind = findTextTextField.getText();
        String replacementText = replacementTextTextField.getText();
        if (textToFind.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Text to find is empty!");
            findTextTextField.setBackground(Color.yellow);
        } else if (replacementText.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Replacement text is empty!");
            replacementTextTextField.setBackground(Color.yellow);
        } else if (!isValidName(textToFind)) {
            JOptionPane.showMessageDialog(rootPane, "Text to find contains invalid character(s)!");
            findTextTextField.setBackground(Color.red);
        } else if (!isValidName(replacementText)) {
            JOptionPane.showMessageDialog(rootPane, "Replacement text contains invalid character(s)!");
            replacementTextTextField.setBackground(Color.red);
        } else {
            String replaceRuleString = String.format("rep:%s:%s:", textToFind, replacementText);
            boolean skipExtension = skipExtensionCheckBoxReplace.isSelected();
            boolean caseSensitive = caseSensitiveCheckBoxReplace.isSelected();

            if (allOccurrencesRadioButtonReplace.isSelected()) {
                replaceRuleString += String.format("all:%s:%s", skipExtension, caseSensitive);
            } else if (firstOccurrencesRadioButtonReplace.isSelected()) {
                replaceRuleString += String.format("first:%s:%s", skipExtension, caseSensitive);
            } else if (lastOccurrencesRadioButtonReplace.isSelected()) {
                replaceRuleString += String.format("last:%s:%s", skipExtension, caseSensitive);
            }
            Manager.rulesList.add(replaceRuleString);
            dispose();
        }
    }

    public void invokeChangeCase() {
        String changeCaseRuleString = "cas:";
        if (capitaliseEveryWordRadioButton.isSelected()) {
            changeCaseRuleString += "1";
        } else if (capitaliseFirstLetterRadioButton.isSelected()) {
            changeCaseRuleString += "2";
        } else if (allUpperCaseRadioButton.isSelected()) {
            changeCaseRuleString += "3";
        } else if (allLowerCaseRadioButton.isSelected()) {
            changeCaseRuleString += "4";
        } else if (invertCaseRadioButton.isSelected()) {
            changeCaseRuleString += "5";
        }
        Manager.rulesList.add(changeCaseRuleString);
        dispose();
    }

    public void invokeExtension() {
        String extensionRuleString = extensionTextField.getText();
        if (extensionRuleString.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Extension field is empty!");
            extensionTextField.setBackground(Color.yellow);
        } else if (!isValidName(extensionRuleString)) {
            JOptionPane.showMessageDialog(rootPane, "Extension contains invalid character(s)!");
            extensionTextField.setBackground(Color.red);
        } else {
            boolean appendToOriginalName = appendToOriginalFileNamesCheckBox.isSelected();
            extensionRuleString = String.format("ext:.%s:%s", extensionRuleString, appendToOriginalName);
            Manager.rulesList.add(extensionRuleString);
            dispose();
        }
    }

    public void invokeSerialise() {
        String serialiseRuleString = null;
        String index = indexSpinnerSerialise.getValue().toString();
        String step = stepSpinnerSerialise.getValue().toString();
        String length = null;
        if (lengthOfSerialCheckBox.isSelected()) {
            length = lengthOfSerialSpinner.getValue().toString();
        }
        String textPadding = textToAddWithSerialTextField.getText();
        String textPaddingLocation = null;
        if (!textPadding.isEmpty()) {
            if (!isValidName(textPadding)) {
                JOptionPane.showMessageDialog(rootPane, "Text to add with serial contains invalid character(s)", "Warning!", JOptionPane.WARNING_MESSAGE);
                textToAddWithSerialTextField.setBackground(Color.RED);
                return;
            }
            if (textBeforeSerialRadioButton.isSelected()) {
                textPaddingLocation = "bef";
            } else {
                textPaddingLocation = "aft";
            }
        }
        if (prefixRadioButtonSerialise.isSelected()) {
            serialiseRuleString = String.format("ser:pre:%s:%s:%s", index, step, length != null ? length : "0");
            if (!textPadding.isEmpty()) {
                serialiseRuleString += String.format(":%s:%s", textPadding, textPaddingLocation.equals("bef") ? "bef" : "aft");
            }
        } else if (suffixRadioButtonSerialise.isSelected()) {
            boolean skipExtension = skipExtensionCheckBoxSerialise.isSelected();
            serialiseRuleString = String.format("ser:suf:%s:%s:%s:%s", index, step, length != null ? length : "0", skipExtension);
            if (!textPadding.isEmpty()) {
                serialiseRuleString += String.format(":%s:%s", textPadding, textPaddingLocation.equals("bef") ? "bef" : "aft");
            }
        } else if (atPositionRadioButtonSerialise.isSelected()) {
            boolean skipExtension = skipExtensionCheckBoxSerialise.isSelected();
            String pos = atPositionSpinnerSerialise.getValue().toString();
            serialiseRuleString = String.format("ser:pos:%s:%s:%s:%s:%s", index, step, length != null ? length : "0", skipExtension, pos);
            if (!textPadding.isEmpty()) {
                serialiseRuleString += String.format(":%s:%s", textPadding, textPaddingLocation.equals("bef") ? "bef" : "aft");
            }
        }
        Manager.rulesList.add(serialiseRuleString);
        dispose();
    }

    public void invokeCleanUp() {
        String cleanUpRuleString;
        boolean alphabets = alphabetsCheckBox.isSelected();
        boolean digits = digitsCheckBox.isSelected();
        boolean symbols = symbolsCheckBox.isSelected();
        boolean brackets = bracketsCheckBox.isSelected();
        String userDefined = userDefinedTextField.getText();
        boolean skipExtension = skipExtensionCheckBoxCleanup.isSelected();
        cleanUpRuleString = String.format("cle:%s:%s:%s:%s:%s:%s", alphabets, digits, symbols, brackets, userDefined, skipExtension);
        Manager.rulesList.add(cleanUpRuleString);
        dispose();
    }

    public void invokeFileSizeFilter() {
        String lowerLimit = null;
        String upperLimit = null;
        String lowerLimitMultiplier = null;
        String upperLimitMultiplier = null;
        long lowerLimitLong = 0;
        long upperLimitLong = 0;
        if (lowerLimitCheckBox.isSelected()) {
            lowerLimit = lowerLimitSpinner.getValue().toString();
            lowerLimitMultiplier = lowerLimitComboBox.getSelectedItem().toString();
            lowerLimitLong = Long.parseLong(lowerLimit);
            switch (lowerLimitMultiplier) {
                case "KB":
                    lowerLimitLong *= 1024;
                    break;
                case "MB":
                    lowerLimitLong *= 1024 * 1024;
                    break;
            }
        }
        if (upperLimitCheckBox.isSelected()) {
            upperLimit = upperLimitSpinner.getValue().toString();
            upperLimitMultiplier = upperLimitComboBox.getSelectedItem().toString();
            upperLimitLong = Long.parseLong(upperLimit);
            switch (upperLimitMultiplier) {
                case "KB":
                    upperLimitLong *= 1024;
                    break;
                case "MB":
                    upperLimitLong *= 1024 * 1024;
                    break;
            }
        }
        if (lowerLimit == null && upperLimit == null) {
            JOptionPane.showMessageDialog(this, "Choose lower and/or upper limit", "Caution", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (lowerLimitLong > upperLimitLong) {
            JOptionPane.showMessageDialog(rootPane, "Lower limit cannot be greater than\nupper limit", "Error!", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (lowerLimitLong == upperLimitLong) {
            JOptionPane.showMessageDialog(rootPane, "Lower limit is equal to upper limit\nOnly files of this size will be renamed", "Equal limits?", JOptionPane.INFORMATION_MESSAGE);
        }
        lowerLimit = lowerLimit == null ? "" : lowerLimit;
        upperLimit = upperLimit == null ? "" : upperLimit;
        lowerLimitMultiplier = lowerLimitMultiplier == null ? "" : lowerLimitMultiplier;
        upperLimitMultiplier = upperLimitMultiplier == null ? "" : upperLimitMultiplier;
        boolean excludeRange = excludeRangeCheckBox.isSelected();
        String fileSizeFilterString = String.format("siz:%s:%s:%s:%s:%s", lowerLimit, lowerLimitMultiplier, upperLimit, upperLimitMultiplier, excludeRange);

        Manager.rulesList.add(fileSizeFilterString);
        dispose();
    }

    public boolean isValidName(String name) {
        char[] nameCharArray = name.toCharArray();
        for (char c : nameCharArray) {
            if (c == '*' || c == ':' || c == '"' || c == '|' || c == '\\') {
                return false;
            } else if ((c == '/' || c == '?' || c == '>' || c == '<')) {
                return false;
            }
        }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRuleButton;
    private javax.swing.JRadioButton afterTextRadioButton;
    private javax.swing.JTextField afterTextTextField;
    private javax.swing.JRadioButton allLowerCaseRadioButton;
    private javax.swing.JRadioButton allOccurrencesRadioButtonRemove;
    private javax.swing.JRadioButton allOccurrencesRadioButtonReplace;
    private javax.swing.JRadioButton allUpperCaseRadioButton;
    private javax.swing.JCheckBox alphabetsCheckBox;
    private javax.swing.JTextField alphabetsTextField;
    private javax.swing.JCheckBox appendToOriginalFileNamesCheckBox;
    private javax.swing.JRadioButton atPositionRadioButton;
    private javax.swing.JRadioButton atPositionRadioButtonSerialise;
    private javax.swing.JSpinner atPositionSpinnerSerialise;
    private javax.swing.JRadioButton beforeTextRadioButton;
    private javax.swing.JTextField beforeTextTextField;
    private javax.swing.JCheckBox bracketsCheckBox;
    private javax.swing.JTextField bracketsTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton capitaliseEveryWordRadioButton;
    private javax.swing.JRadioButton capitaliseFirstLetterRadioButton;
    private javax.swing.JLabel caseInfoLabel;
    private javax.swing.JCheckBox caseSensitiveCheckBoxInsert;
    private javax.swing.JCheckBox caseSensitiveCheckBoxRemove;
    private javax.swing.JCheckBox caseSensitiveCheckBoxReplace;
    private javax.swing.ButtonGroup changeCaseButtonGroup;
    private javax.swing.JPanel changeCasePanel;
    private javax.swing.JPanel cleanUpPanel;
    private javax.swing.JCheckBox digitsCheckBox;
    private javax.swing.JTextField digitsTextField;
    private javax.swing.JCheckBox excludeRangeCheckBox;
    private javax.swing.JTextField extensionTextField;
    private javax.swing.JPanel extensionsPanel;
    private javax.swing.JPanel fileSizeFilterPanel;
    private javax.swing.JLabel findTextLabel;
    private javax.swing.JTextField findTextTextField;
    private javax.swing.JRadioButton firstOccurrencesRadioButtonRemove;
    private javax.swing.JRadioButton firstOccurrencesRadioButtonReplace;
    private javax.swing.JLabel indexLabel;
    private javax.swing.JSpinner indexSpinnerSerialise;
    private javax.swing.JSpinner insertAtPositionSpinner;
    private javax.swing.ButtonGroup insertLocationButtonGroup;
    private javax.swing.JPanel insertPanel;
    private javax.swing.JRadioButton invertCaseRadioButton;
    private javax.swing.JRadioButton lastOccurrencesRadioButtonRemove;
    private javax.swing.JRadioButton lastOccurrencesRadioButtonReplace;
    private javax.swing.JCheckBox lengthOfSerialCheckBox;
    private javax.swing.JSpinner lengthOfSerialSpinner;
    private javax.swing.JLabel locationLabelSerialise;
    private javax.swing.JCheckBox lowerLimitCheckBox;
    private javax.swing.JComboBox lowerLimitComboBox;
    private javax.swing.JSpinner lowerLimitSpinner;
    private javax.swing.JLabel newExtensionLabel;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel occurrencesLabel;
    private javax.swing.JLabel occurrencesLabelReplace;
    private javax.swing.JLabel padNoteLabel;
    private javax.swing.JRadioButton prefixRadioButton;
    private javax.swing.JRadioButton prefixRadioButtonSerialise;
    private javax.swing.ButtonGroup removeOccurrencesButtonGroup;
    private javax.swing.JPanel removePanel;
    private javax.swing.ButtonGroup replaceOccurrencesButtonGroup;
    private javax.swing.JPanel replacePanel;
    private javax.swing.JLabel replaceWithLabel;
    private javax.swing.JTextField replacementTextTextField;
    private javax.swing.JCheckBox rightToLeftCheckBox;
    private javax.swing.JLayeredPane rulePanelsLayeredPane;
    private javax.swing.JList rulesList;
    private javax.swing.JScrollPane rulesListScrollPane;
    private javax.swing.JLabel serialLabel;
    private javax.swing.ButtonGroup serialiseLocationButtonGroup;
    private javax.swing.JPanel serialisePanel;
    private javax.swing.ButtonGroup serialiseTextPadButtonGroup;
    private javax.swing.JCheckBox skipExtensionCheckBoxCleanup;
    private javax.swing.JCheckBox skipExtensionCheckBoxInsert;
    private javax.swing.JCheckBox skipExtensionCheckBoxRemove;
    private javax.swing.JCheckBox skipExtensionCheckBoxReplace;
    private javax.swing.JCheckBox skipExtensionCheckBoxSerialise;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JSpinner stepSpinnerSerialise;
    private javax.swing.JRadioButton suffixRadioButton;
    private javax.swing.JRadioButton suffixRadioButtonSerialise;
    private javax.swing.JCheckBox symbolsCheckBox;
    private javax.swing.JTextField symbolsTextField;
    private javax.swing.JRadioButton textAfterSerialRadioButton;
    private javax.swing.JRadioButton textBeforeSerialRadioButton;
    private javax.swing.JLabel textToAddWithSerialLabel;
    private javax.swing.JTextField textToAddWithSerialTextField;
    private javax.swing.JLabel textToInsertLabel;
    private javax.swing.JTextField textToInsertTextField;
    private javax.swing.JLabel textToRemoveLabel;
    private javax.swing.JTextField textToRemoveTextField;
    private javax.swing.JCheckBox upperLimitCheckBox;
    private javax.swing.JComboBox upperLimitComboBox;
    private javax.swing.JSpinner upperLimitSpinner;
    private javax.swing.JCheckBox userDefinedCheckBox;
    private javax.swing.JTextField userDefinedTextField;
    // End of variables declaration//GEN-END:variables
}
