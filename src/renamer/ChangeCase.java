package renamer;

import organiser.Manager;

public class ChangeCase {

    private String[] originalNames;
    private String[] newNames;
    private int numberOfFiles;
    private String theRule;

    public ChangeCase(String rule) {
        originalNames = Manager.getFileNames();
        numberOfFiles = Manager.numberOfFiles;
        theRule = rule;
    }

    public void applyChangeCaseRule() {
        if (theRule.endsWith("1")) {
            capitaliseEveryWord();
        }
        else if (theRule.endsWith("2")) {
            capitaliseFirstLetter();
        }
        else if (theRule.endsWith("3")) {
            allUpperCase();
        }
        else if (theRule.endsWith("4")) {
            allLowerCase();
        }
        else if (theRule.endsWith("5")) {
            invertCase();
        }
        Manager.setRenamedFileNamesAndExtensions(newNames, Manager.getFileExtensions());
    }

    public void capitaliseEveryWord() {
        newNames = new String[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            char[] nameCharArray = originalNames[i].toCharArray();
            int nameLength = nameCharArray.length;

            nameCharArray[0] = Character.toUpperCase(nameCharArray[0]);
            for (int j = 1; j < nameLength; j++) {
                if (isWordSplitter(nameCharArray[j - 1])) {
                    nameCharArray[j] = Character.toUpperCase(nameCharArray[j]);
                }
                else {
                    nameCharArray[j] = Character.toLowerCase(nameCharArray[j]);
                }
            }
            newNames[i] = new String(nameCharArray);
        }
    }

    public void capitaliseFirstLetter() {
        newNames = new String[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            char[] nameCharArray = originalNames[i].toCharArray();
            int j = 0;
            while (!Character.isLetter(nameCharArray[j]))
                j++;
            nameCharArray[j] = Character.toUpperCase(nameCharArray[j]);
            for (j++; j < nameCharArray.length; j++) {
                nameCharArray[j] = Character.toLowerCase(nameCharArray[j]);
            }
            newNames[i] = new String(nameCharArray);
        }
    }

    public void allUpperCase() {
        newNames = new String[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            String name = originalNames[i];
            newNames[i] = name.toUpperCase();
        }
    }

    public void allLowerCase() {
        newNames = new String[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            String name = originalNames[i];
            newNames[i] = name.toLowerCase();
        }
    }

    public void invertCase() {
        newNames = new String[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            char[] nameCharArray = originalNames[i].toCharArray();
            int nameLength = nameCharArray.length;
            for (int j = 0; j < nameLength; j++) {
                if (Character.isUpperCase(nameCharArray[j])) {
                    nameCharArray[j] = Character.toLowerCase(nameCharArray[j]);
                }
                else if (Character.isLowerCase(nameCharArray[j])) {
                    nameCharArray[j] = Character.toUpperCase(nameCharArray[j]);
                }
            }
            newNames[i] = new String(nameCharArray);
        }
    }

    public boolean isWordSplitter(char ch) {
        if (Character.isLetter(ch))
            return false;
        if (Character.isWhitespace(ch)) {
            return true;
        }
        if (Character.isDigit(ch)) {
            return true;
        }
        if (ch == ',' || ch == '.') {
            return true;
        }
        if (ch == '(' || ch == ')' || ch == '{' || ch == '}' || ch == '[' || ch == ']') {
            return true;
        }
        if (ch == '-' || ch == '_' || ch == '+' || ch == '=') {
            return true;
        }
        if (ch == '~' || ch == '`') {
            return true;
        }
        if (ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&') {
            return true;
        }
        return false;
    }
}
