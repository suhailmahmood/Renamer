package renamer;

import organiser.Manager;

public class Extension {

    private String[] originalExtensions;
    private String[] newExtensions;
    private String extension;
    private int numberOfFiles;
    private String theRule;

    public Extension(String rule) {
        originalExtensions = Manager.getFileExtensions();
        numberOfFiles = Manager.numberOfFiles;
        theRule = rule;
    }

    public void applyExtensionRule() {
        extension = theRule.substring(theRule.indexOf(':') + 1, theRule.lastIndexOf(':'));
        boolean appendToOriginalName = Boolean.parseBoolean(theRule.substring(theRule.lastIndexOf(':') + 1, theRule.length()));

        setNewExtensions(appendToOriginalName);
        Manager.setRenamedFileNamesAndExtensions(Manager.getFileNames(), newExtensions);
    }

    public void setNewExtensions(boolean appendToOriginalNames) {
        newExtensions = new String[numberOfFiles];
        if (appendToOriginalNames) {
            for (int i = 0; i < numberOfFiles; i++) {
                newExtensions[i] = originalExtensions[i] + extension;
            }
        }
        else {
            for (int i = 0; i < numberOfFiles; i++) {
                newExtensions[i] = extension;
            }
        }
    }
}
