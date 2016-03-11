package organiser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RuleSetManager {

    private File ruleSetFile;
    private Scanner fileScanner;
    private Formatter fileFormatter;
    private LinkedList<String> rules;
    private final String newLine = System.getProperty("line.separator");

    public void saveRuleSet() {
        if (Manager.rulesList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nothing to save", "Alert!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser saveRuleSetFileChooser = new JFileChooser();
        if (saveRuleSetFileChooser.showSaveDialog(saveRuleSetFileChooser) == JFileChooser.APPROVE_OPTION) {
            ruleSetFile = saveRuleSetFileChooser.getSelectedFile();
            ruleSetFile = new File(ruleSetFile.toString().concat(ruleSetFile.toString().endsWith("rrs") ? "" : ".rrs"));

            boolean creatingSuccessful = createRuleSetFile(ruleSetFile);
            if (creatingSuccessful) {
                JOptionPane.showMessageDialog(null, "Saved successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void loadRuleSetFile() {
        JFileChooser loadRuleSetFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Renamer Rule Set files(.rrs)", "rrs");

        loadRuleSetFileChooser.setMultiSelectionEnabled(false);
        loadRuleSetFileChooser.setDialogTitle("Select Renamer Rule Set file");
        loadRuleSetFileChooser.setApproveButtonText("Load");
        loadRuleSetFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        loadRuleSetFileChooser.setFileFilter(filter);
        loadRuleSetFileChooser.setAcceptAllFileFilterUsed(false);

        if (loadRuleSetFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            ruleSetFile = loadRuleSetFileChooser.getSelectedFile();

            boolean readingSuccessful = readRuleSetFile();
            if (readingSuccessful) {
                Manager.rulesList.clear();
                Manager.rulesList = rules;
            }
        }
    }

    public boolean createRuleSetFile(File path) {
        rules = Manager.rulesList;
        try {
            fileFormatter = new Formatter(path);
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Could not create file", "Error!", JOptionPane.PLAIN_MESSAGE);
            return false;
        }

        fileFormatter.format("###.^.139931RenamerRuleSetFile139931.^.###" + newLine);
        for (Iterator<String> it = rules.iterator(); it.hasNext();) {
            String currentRule = it.next();
            fileFormatter.format("%s%s", currentRule, newLine);
        }
        fileFormatter.close();
        return true;
    }

    public boolean readRuleSetFile() {
        try {
            fileScanner = new Scanner(ruleSetFile);
            if (fileScanner.hasNext()) {
                String firstLine = fileScanner.nextLine();
                if (!firstLine.equals("###.^.139931RenamerRuleSetFile139931.^.###")) {
                    JOptionPane.showMessageDialog(null, ruleSetFile.getName() + " is not a valid Renamer Rule Set file", "Fatal Error!", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Could not read the file!", "Fatal Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        rules = new LinkedList<>();
        while (fileScanner.hasNextLine()) {
            rules.add(fileScanner.nextLine());
        }
        return true;
    }
}
