package renamer;

import organiser.Manager;

public class Insert {

    private String textToInsert;
    private String[] originalNames;
    private String[] originalExtensions;
    private String[] newNames;
    private String[] newExtensions;
    private String theRule;
    private int numberOfFiles;
    private boolean newExtensionCreated;

    /**
     * The constructor populates some of the fields of the class.
     *
     * @param rule The RuleString, contains all the instructions needed to carry
     * out the renaming operation of Insert<br> in an "encoded" form. It is
     * decoded by the method <b>applyInsertRule()</b>
     */
    public Insert(String rule) {
        originalNames = Manager.getFileNames();
        originalExtensions = Manager.getFileExtensions();
        numberOfFiles = Manager.numberOfFiles;
        theRule = rule;
    }

    /**
     * This method "decodes" the information contained in the RuleString and
     * calls the respective method to get the job done.
     */
    public void applyInsertRule() {
        String[] terms = theRule.split(":");
        textToInsert = terms[1];
        switch (terms[2]) {
            case "pre":
                insertPrefix();
                break;
            case "suf": {
                boolean skipExtension = Boolean.parseBoolean(terms[3]);
                insertSuffix(skipExtension);
                break;
            }
            case "pos": {
                int pos = Integer.parseInt(terms[3]) - 1;
                boolean skipExtension = Boolean.parseBoolean(terms[4]);
                boolean rightToLeft = Boolean.parseBoolean(terms[5]);
                if (rightToLeft) {
                    insertAtPositionRightToLeft(pos, skipExtension);
                }
                else {
                    insertAtPosition(pos, skipExtension);
                }
                break;
            }
            case "aft": {
                String searchText = terms[3];
                boolean skipExtension = Boolean.parseBoolean(terms[4]);
                boolean caseSensitive = Boolean.parseBoolean(terms[5]);
                insertAfterText(searchText, skipExtension, caseSensitive);
                break;
            }
            case "bef": {
                String searchText = terms[3];
                boolean skipExtension = Boolean.parseBoolean(terms[4]);
                boolean caseSensitive = Boolean.parseBoolean(terms[5]);
                insertBeforeText(searchText, skipExtension, caseSensitive);
                break;
            }
        }
        if (newExtensionCreated) {
            Manager.setRenamedFileNamesAndExtensions(newNames, newExtensions);
        }
        else {
            Manager.setRenamedFileNamesAndExtensions(newNames, originalExtensions);
        }
    }

    public void insertPrefix() {
        newNames = new String[numberOfFiles];

        for (int i = 0; i < numberOfFiles; i++) {
            newNames[i] = textToInsert + originalNames[i];
        }
    }

    public void insertSuffix(boolean skipExtension) {
        newNames = new String[numberOfFiles];

        if (skipExtension) {
            for (int i = 0; i < numberOfFiles; i++) {
                newNames[i] = originalNames[i] + textToInsert;
            }
        }
        else {
            newExtensions = new String[numberOfFiles];
            newExtensionCreated = true;

            for (int i = 0; i < numberOfFiles; i++) {
                String fullName = originalNames[i] + originalExtensions[i] + textToInsert;
                setNewNameAndExtensionAtIndex(fullName, i);
            }
        }
    }

    public void insertAtPosition(int pos, boolean skipExtension) {
        newNames = new String[numberOfFiles];

        if (skipExtension) {
            for (int i = 0; i < numberOfFiles; i++) {
                StringBuilder name = new StringBuilder(originalNames[i]);

                int posForThisFile = pos > originalNames[i].length() ? originalNames[i].length() : pos;
                name.insert(posForThisFile, textToInsert);
                newNames[i] = name.toString();
            }
        }
        else {
            newExtensions = new String[numberOfFiles];
            newExtensionCreated = true;

            StringBuilder fullNameBuilder;
            for (int i = 0; i < numberOfFiles; i++) {
                fullNameBuilder = new StringBuilder(originalNames[i] + originalExtensions[i]);
                int posForThisFile = pos > fullNameBuilder.length() ? fullNameBuilder.length() : pos;

                fullNameBuilder.insert(posForThisFile, textToInsert);
                String newFullName = fullNameBuilder.toString();
                setNewNameAndExtensionAtIndex(newFullName, i);
            }
        }
    }

    public void insertAtPositionRightToLeft(int pos, boolean skipExtension) {
        newNames = new String[numberOfFiles];
        String textToInsertReverse = new StringBuilder(textToInsert).reverse().toString();
        if (skipExtension) {
            for (int i = 0; i < numberOfFiles; i++) {
                StringBuilder name = new StringBuilder(originalNames[i]);
                name = name.reverse();
                int posForThisFile = pos > originalNames[i].length() ? originalNames[i].length() : pos;
                name.insert(posForThisFile, textToInsertReverse);
                newNames[i] = name.reverse().toString();
            }
        }
        else {
            newExtensions = new String[numberOfFiles];
            newExtensionCreated = true;

            StringBuilder fullNameReverse;
            for (int i = 0; i < numberOfFiles; i++) {
                fullNameReverse = new StringBuilder(originalNames[i] + originalExtensions[i]).reverse();
                int posForThisFile = pos > fullNameReverse.length() ? fullNameReverse.length() : pos;

                fullNameReverse.insert(posForThisFile, textToInsertReverse);
                String newFullName = fullNameReverse.reverse().toString();
                setNewNameAndExtensionAtIndex(newFullName, i);
            }
        }
    }

    public void insertAfterText(String textToFind, boolean skipExtension, boolean caseSensitive) {
        newNames = new String[numberOfFiles];
        StringBuilder nameBuilder;
        int textToFindLength = textToFind.length();
        int pos;

        if (caseSensitive) {
            if (skipExtension) {
                for (int i = 0; i < numberOfFiles; i++) {
                    if ((pos = originalNames[i].indexOf(textToFind)) != -1) {
                        nameBuilder = new StringBuilder(originalNames[i]);
                        nameBuilder.insert(pos + textToFindLength, textToInsert);
                        newNames[i] = nameBuilder.toString();
                    }
                    else {
                        newNames[i] = originalNames[i];
                    }
                }
            }
            else {
                newExtensions = new String[numberOfFiles];
                newExtensionCreated = true;

                for (int i = 0; i < numberOfFiles; i++) {
                    String fullName = originalNames[i] + originalExtensions[i];

                    if ((pos = fullName.indexOf(textToFind)) != -1) {
                        nameBuilder = new StringBuilder(fullName);
                        fullName = nameBuilder.insert(pos + textToFindLength, textToInsert).toString();
                        setNewNameAndExtensionAtIndex(fullName, i);
                    }
                    else {
                        newNames[i] = originalNames[i];
                        newExtensions[i] = originalExtensions[i];
                    }
                }
            }
        }
        // if not case sensitive
        else {
            boolean foundIt;
            if (skipExtension) {
                for (int i = 0; i < numberOfFiles; i++) {
                    int originalNameLength = originalNames[i].length();
                    nameBuilder = new StringBuilder(originalNames[i]);
                    foundIt = false;

                    int index;
                    for (index = 0; index <= originalNameLength - textToFindLength; index++) {
                        if (originalNames[i].regionMatches(!caseSensitive, index, textToFind, 0, textToFindLength)) {
                            foundIt = true;
                            break;
                        }
                    }
                    if (foundIt) {
                        nameBuilder.insert(index + textToFindLength, textToInsert);
                        newNames[i] = nameBuilder.toString();
                    }
                    else {
                        newNames[i] = originalNames[i];
                    }
                }
            }
            else {
                newExtensions = new String[numberOfFiles];
                newExtensionCreated = true;
                String fullName;

                for (int i = 0; i < numberOfFiles; i++) {
                    fullName = originalNames[i] + originalExtensions[i];
                    nameBuilder = new StringBuilder(fullName);
                    int fullNameLength = fullName.length();

                    foundIt = false;
                    int index;
                    for (index = 0; index <= fullNameLength - textToFindLength; index++) {
                        if (fullName.regionMatches(!caseSensitive, index, textToFind, 0, textToFindLength)) {
                            foundIt = true;
                            break;
                        }
                    }
                    if (foundIt) {
                        nameBuilder.insert(index + textToFindLength, textToInsert);
                        fullName = new String(nameBuilder);
                        setNewNameAndExtensionAtIndex(fullName, i);
                    }
                    else {
                        newNames[i] = originalNames[i];
                        newExtensions[i] = originalExtensions[i];
                    }
                }
            }
        }
    }

    public void insertBeforeText(String textToFind, boolean skipExtension, boolean caseSensitive) {
        newNames = new String[numberOfFiles];
        int textToFindLength = textToFind.length();
        StringBuilder nameBuilder;
        int pos;

        if (caseSensitive) {
            if (skipExtension) {
                for (int i = 0; i < numberOfFiles; i++) {
                    if ((pos = originalNames[i].indexOf(textToFind)) != -1) {
                        nameBuilder = new StringBuilder(originalNames[i]);
                        nameBuilder.insert(pos, textToInsert);
                        newNames[i] = nameBuilder.toString();
                    }
                    else {
                        newNames[i] = originalNames[i];
                    }
                }
            }
            else {
                newExtensions = new String[numberOfFiles];
                newExtensionCreated = true;

                for (int i = 0; i < numberOfFiles; i++) {
                    String fullName = originalNames[i] + originalExtensions[i];
                    if ((pos = fullName.indexOf(textToFind)) != -1) {
                        nameBuilder = new StringBuilder(fullName);
                        fullName = nameBuilder.insert(pos, textToInsert).toString();

                        setNewNameAndExtensionAtIndex(fullName, i);
                    }
                    else {
                        newNames[i] = originalNames[i];
                        newExtensions[i] = originalExtensions[i];
                    }
                }
            }
        }
        else {
            boolean foundIt;
            if (skipExtension) {
                for (int i = 0; i < numberOfFiles; i++) {
                    int originalNameLength = originalNames[i].length();
                    nameBuilder = new StringBuilder(originalNames[i]);
                    foundIt = false;

                    int index;
                    for (index = 0; index <= originalNameLength - textToFindLength; index++) {
                        if (originalNames[i].regionMatches(!caseSensitive, index, textToFind, 0, textToFindLength)) {
                            foundIt = true;
                            break;
                        }
                    }
                    if (foundIt) {
                        nameBuilder.insert(index, textToInsert);
                    }
                    newNames[i] = nameBuilder.toString();
                }
            }
            else {
                newExtensions = new String[numberOfFiles];
                newExtensionCreated = true;
                String fullName;

                for (int i = 0; i < numberOfFiles; i++) {
                    fullName = originalNames[i] + originalExtensions[i];
                    nameBuilder = new StringBuilder(fullName);
                    int fullNameLength = fullName.length();

                    foundIt = false;
                    int index;
                    for (index = 0; index <= fullNameLength - textToFindLength; index++) {
                        if (fullName.regionMatches(!caseSensitive, index, textToFind, 0, textToFindLength)) {
                            foundIt = true;
                            break;
                        }
                    }
                    if (foundIt) {
                        nameBuilder.insert(index, textToInsert);
                        fullName = new String(nameBuilder);
                    }
                    setNewNameAndExtensionAtIndex(fullName, i);
                }
            }
        }
    }

    public void setNewNameAndExtensionAtIndex(String fullName, int i) {
        if (fullName.indexOf('.') == -1) {
            newNames[i] = fullName;
            newExtensions[i] = "";
        }
        else {
            newNames[i] = fullName.substring(0, fullName.lastIndexOf('.'));
            newExtensions[i] = fullName.substring(fullName.lastIndexOf('.'), fullName.length());
        }
    }
}
