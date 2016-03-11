package renamer;

import organiser.Manager;

public class Replace {

    private String textToFind;
    private String textToReplace;
    private String[] originalNames;
    private String[] originalExtensions;
    private String[] newNames;
    private String[] newExtensions;
    private int numberOfFiles;
    private String theRule;
    private boolean newExtensionCreated;

    public Replace(String rule) {
	originalNames = Manager.getFileNames();
	originalExtensions = Manager.getFileExtensions();
	numberOfFiles = Manager.numberOfFiles;
	theRule = rule;
    }

    public void applyReplaceRule() {
	String[] terms = theRule.split(":");
	textToFind = terms[1];
	textToReplace = terms[2];
	String occurrence = terms[3];
	boolean skipExtension = Boolean.parseBoolean(terms[4]);
	boolean caseSensitive = Boolean.parseBoolean(terms[5]);
	switch (occurrence) {
	    case "all":
		replaceAllOccurrences(skipExtension, caseSensitive);
		break;
	    case "first":
		replaceFirstOccurrence(skipExtension, caseSensitive);
		break;
	    default:
		replaceLastOccurrence(skipExtension, caseSensitive);
		break;
	}
	if (newExtensionCreated) {
	    Manager.setRenamedFileNamesAndExtensions(newNames, newExtensions);
	}
	else {
	    Manager.setRenamedFileNamesAndExtensions(newNames, originalExtensions);
	}
    }

    public void replaceAllOccurrences(boolean skipExtension, boolean caseSensitive) {
	newNames = new String[numberOfFiles];
	if (caseSensitive) {
	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    newNames[i] = originalNames[i].replace(textToFind, textToReplace);
		}
	    }
	    else {
		newExtensions = new String[numberOfFiles];
		newExtensionCreated = true;

		for (int i = 0; i < numberOfFiles; i++) {
		    String fullName = (originalNames[i] + originalExtensions[i]).replace(textToFind, textToReplace);

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
	}
	// if not case sensitive...
	else {
	    int textToFindLength = textToFind.length();
	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    String name = originalNames[i];
		    int originalNameLength = name.length();

		    int index;
		    for (index = 0; index <= originalNameLength - textToFindLength; index++) {
			if (name.regionMatches(!caseSensitive, index, textToFind, 0, textToFindLength)) {
			    name = new StringBuffer(name).replace(index, index + textToFindLength, textToReplace).toString();
			    originalNameLength = name.length();
			}
		    }
		    newNames[i] = name;
		}
	    }
	    else {
		newExtensions = new String[numberOfFiles];
		newExtensionCreated = true;
		String fullName;

		for (int i = 0; i < numberOfFiles; i++) {
		    fullName = originalNames[i] + originalExtensions[i];
		    int fullNameLength = fullName.length();

		    int index;
		    for (index = 0; index <= fullNameLength - textToFindLength; index++) {
			if (fullName.regionMatches(!caseSensitive, index, textToFind, 0, textToFindLength)) {
			    fullName = new StringBuffer(fullName).replace(index, index + textToFindLength, textToReplace).toString();
			    fullNameLength = fullName.length();
			}
		    }
		    if (fullName.indexOf('.') == -1) {
			newNames[i] = fullName;
			newExtensions[i] = "";
		    }
		    else {
			newNames[i] = fullName.substring(0, fullName.lastIndexOf('.'));
			newExtensions[i] = fullName.substring(fullName.lastIndexOf('.'), fullNameLength);
		    }
		}
	    }
	}
    }

    public void replaceFirstOccurrence(boolean skipExtension, boolean caseSensitive) {
	newNames = new String[numberOfFiles];
	if (caseSensitive) {
	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    newNames[i] = originalNames[i].replaceFirst(textToFind, textToReplace);
		}
	    }
	    else {
		newExtensions = new String[numberOfFiles];
		newExtensionCreated = true;

		for (int i = 0; i < numberOfFiles; i++) {
		    String fullName = (originalNames[i] + originalExtensions[i]).replaceFirst(textToFind, textToReplace);

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
	}
	else {
	    int textToFindLength = textToFind.length();

	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    int originalNameLength = originalNames[i].length();
		    StringBuilder nameBuilder = new StringBuilder(originalNames[i]);
		    boolean foundIt = false;

		    int index;
		    for (index = 0; index <= originalNameLength - textToFindLength; index++) {
			if (originalNames[i].regionMatches(!caseSensitive, index, textToFind, 0, textToFindLength)) {
			    foundIt = true;
			    break;
			}
		    }
		    if (foundIt) {
			nameBuilder.replace(index, index + textToFindLength, textToReplace);
		    }
		    newNames[i] = nameBuilder.toString();
		}
	    }
	    else {
		newExtensions = new String[numberOfFiles];
		newExtensionCreated = true;
		String fullName;
		StringBuffer fullNameBuffer;

		for (int i = 0; i < numberOfFiles; i++) {
		    fullName = originalNames[i] + originalExtensions[i];
		    int fullNameLength = fullName.length();

		    boolean foundIt = false;
		    int index;
		    for (index = 0; index <= fullNameLength - textToFindLength; index++) {
			if (fullName.regionMatches(!caseSensitive, index, textToFind, 0, textToFindLength)) {
			    foundIt = true;
			    break;
			}
		    }
		    if (foundIt) {
			fullNameBuffer = new StringBuffer(fullName);
			fullNameBuffer.replace(index, index + textToFindLength, textToReplace);
			fullName = new String(fullNameBuffer);
		    }
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
	}
    }

    public void replaceLastOccurrence(boolean skipExtension, boolean caseSensitive) {
	String textToFindReverse = new StringBuffer(textToFind).reverse().toString();
	String textToReplaceReverse = new StringBuffer(textToReplace).reverse().toString();
	newNames = new String[numberOfFiles];

	if (caseSensitive) {
	    if (skipExtension) {
		StringBuffer nameBufferReverse;
		String nameReverse;
		for (int i = 0; i < numberOfFiles; i++) {
		    nameBufferReverse = new StringBuffer(originalNames[i]).reverse();
		    nameReverse = new String(nameBufferReverse).replaceFirst(textToFindReverse, textToReplaceReverse);
		    newNames[i] = new StringBuffer(nameReverse).reverse().toString();
		}
	    }
	    else {
		newExtensions = new String[numberOfFiles];
		newExtensionCreated = true;
		StringBuffer fullNameBufferReverse;
		String fullName;

		for (int i = 0; i < numberOfFiles; i++) {
		    fullNameBufferReverse = new StringBuffer(originalNames[i] + originalExtensions[i]).reverse();
		    fullName = new StringBuffer(new String(fullNameBufferReverse).replaceFirst(textToFindReverse, textToReplaceReverse)).reverse().toString();

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
	}
	else {
	    int textToFindLength = textToFind.length();
	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    String nameReverse = new StringBuffer(originalNames[i]).reverse().toString();
		    int originalNameLength = nameReverse.length();

		    boolean foundIt = false;

		    int index;
		    for (index = 0; index <= originalNameLength - textToFindLength; index++) {
			if (nameReverse.regionMatches(!caseSensitive, index, textToFindReverse, 0, textToFindLength)) {
			    foundIt = true;
			    break;
			}
		    }
		    if (foundIt) {
			newNames[i] = new StringBuffer(nameReverse).replace(index, index + textToFindLength, textToReplaceReverse).reverse().toString();
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
		StringBuffer fullNameBuffer;
		String fullNameReverse;

		for (int i = 0; i < numberOfFiles; i++) {
		    fullNameReverse = new StringBuffer(originalNames[i] + originalExtensions[i]).reverse().toString();

		    boolean foundIt = false;
		    int index;
		    for (index = 0; index < numberOfFiles; index++) {
			if (fullNameReverse.regionMatches(!caseSensitive, index, textToFindReverse, 0, textToFindLength)) {
			    foundIt = true;
			    break;
			}
		    }
		    if (foundIt) {
			fullNameBuffer = new StringBuffer(fullNameReverse);
			fullNameBuffer.replace(index, index + textToFindLength, textToReplaceReverse).reverse();
			fullName = new String(fullNameBuffer);
		    }
		    else {
			fullName = originalNames[i] + originalExtensions[i];
		    }

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
	}
    }
}
