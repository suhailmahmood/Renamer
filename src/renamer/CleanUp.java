package renamer;

import organiser.Manager;

public class CleanUp {

    private String[] originalNames;
    private String[] originalExtensions;
    private String[] newNames;
    private String[] newExtensions;
    private String theRule;
    private int numberOfFiles;
    private String userDefinedString;
    private boolean stripUserDefined;
    private boolean newExtensionCreated;

    public CleanUp(String rule) {
        originalNames = Manager.getFileNames();
        originalExtensions = Manager.getFileExtensions();
        numberOfFiles = Manager.numberOfFiles;
        theRule = rule;
    }

    public void applyCleanUpRule() {
        String[] terms = theRule.split(":");
        boolean stripAlphabets = Boolean.parseBoolean(terms[1]);
        boolean stripDigits = Boolean.parseBoolean(terms[2]);
        boolean stripSymbols = Boolean.parseBoolean(terms[3]);
        boolean stripBrackets = Boolean.parseBoolean(terms[4]);
        if (!terms[5].equals(""))
            stripUserDefined = true;
        userDefinedString = terms[5];

        boolean skipExtension = Boolean.parseBoolean(terms[6]);
        stripSelected(stripAlphabets, stripDigits, stripSymbols, stripBrackets, stripUserDefined, skipExtension);
        if (newExtensionCreated) {
            Manager.setRenamedFileNamesAndExtensions(newNames, newExtensions);
        }
        else {
            Manager.setRenamedFileNamesAndExtensions(newNames, originalExtensions);
        }
    }

    public void stripSelected(boolean stripAlphabets, boolean stripDigits, boolean stripSymbols, boolean stripBrackets, boolean stripUserDefined, boolean skipExtension) {
        newNames = new String[numberOfFiles];
        if (skipExtension) {
            newNames = setNewNamesAndExtensions(originalNames, stripAlphabets, stripDigits, stripSymbols, stripBrackets, stripUserDefined);
        }
        else {
            newExtensionCreated = true;
            newExtensions = new String[numberOfFiles];
            String[] fullNames = new String[numberOfFiles];
            for (int i = 0; i < numberOfFiles; i++) {
                fullNames[i] = originalNames[i] + originalExtensions[i];
            }
            fullNames = setNewNamesAndExtensions(fullNames, stripAlphabets, stripDigits, stripSymbols, stripBrackets, stripUserDefined);

            for (int i = 0; i < numberOfFiles; i++) {
                if (fullNames[i].indexOf('.') == -1) {
                    newNames[i] = fullNames[i];
                    newExtensions[i] = "";
                }
                else {
                    newNames[i] = fullNames[i].substring(0, fullNames[i].lastIndexOf('.'));
                    newExtensions[i] = fullNames[i].substring(fullNames[i].lastIndexOf('.'), fullNames[i].length());
                }
            }
        }
    }

    public String[] setNewNamesAndExtensions(String[] names, boolean stripAlphabets, boolean stripDigits, boolean stripSymbols, boolean stripBrackets, boolean stripUserDefined) {
        String[] newNamesLocal = new String[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            char[] name = names[i].toCharArray();
            char[] newName = new char[name.length];
            int newNameIndex = 0;
            for (int j = 0; j < name.length; j++) {
                if ((stripAlphabets && Character.isAlphabetic(name[j])) || (stripDigits && Character.isDigit(name[j])) || (stripSymbols && isSymbol(name[j])) || (stripBrackets && isBracket(name[j])) || (stripUserDefined && isUserDefined(name[j]))) {
                    continue;
                }
                else {
                    newName[newNameIndex] = name[j];
                    newNameIndex++;
                }
            }
            newNamesLocal[i] = new String(newName, 0, newNameIndex);
        }
        return newNamesLocal;
    }

    public boolean isSymbol(char ch) {
        if (ch == ',' || ch == '.' || ch == '\'') {
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

    public boolean isBracket(char ch) {
        if (ch == '(' || ch == ')' || ch == '{' || ch == '}' || ch == '[' || ch == ']')
            return true;
        return false;
    }

    public boolean isUserDefined(char ch) {
        for (int i = 0; i < userDefinedString.length(); i++) {
            if (ch == userDefinedString.charAt(i))
                return true;
        }
        return false;
    }
}
