package renamer;

import organiser.Manager;

/**
 * This class handles the removal of a text from the file names in a number of
 * ways. An instance of this class when created fetches the original file names
 * and extensions, and calling of its method <b>applyRemoveRule()</b> sets up
 * the renamed file names in Manager's fields for renamed file names and
 * extensions.
 *
 * @author Suhail
 */
public class Remove {

    private String textToRemove;
    private String[] originalNames;
    private String[] originalExtensions;
    private String[] newNames;
    private String[] newExtensions;
    private int numberOfFiles;
    private String theRule;
    private boolean newExtensionCreated;

    public Remove(String rule) {
	originalNames = Manager.getFileNames();
	originalExtensions = Manager.getFileExtensions();
	numberOfFiles = Manager.numberOfFiles;
	theRule = rule;
    }

    public void applyRemoveRule() {
	String[] terms = theRule.split(":");
	textToRemove = terms[1];
	String occurrence = terms[2];
	boolean skipExtension = Boolean.parseBoolean(terms[3]);
	boolean caseSensitive = Boolean.parseBoolean(terms[4]);
	switch (occurrence) {
	    case "all":
		removeAllOccurrences(skipExtension, caseSensitive);
		break;
	    case "first":
		removeFirstOccurrence(skipExtension, caseSensitive);
		break;
	    case "last":
		removeLastOccurrence(skipExtension, caseSensitive);
		break;
	}
	if (newExtensionCreated) {
	    Manager.setRenamedFileNamesAndExtensions(newNames, newExtensions);
	}
	else {
	    Manager.setRenamedFileNamesAndExtensions(newNames, originalExtensions);
	}
    }

    public void removeAllOccurrences(boolean skipExtension, boolean caseSensitive) {
	newNames = new String[numberOfFiles];
	if (caseSensitive) {
	    newNames = new String[numberOfFiles];

	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    newNames[i] = originalNames[i].replace(textToRemove, "");
		}
	    }
	    else {
		newExtensions = new String[numberOfFiles];
		newExtensionCreated = true;

		for (int i = 0; i < numberOfFiles; i++) {
		    String fullName = (originalNames[i] + originalExtensions[i]).replace(textToRemove, "");

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
	    int textToRemoveLength = textToRemove.length();
	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    String name = originalNames[i];
		    int originalNameLength = name.length();

		    int index;
		    for (index = 0; index <= originalNameLength - textToRemoveLength; index++) {
			if (name.regionMatches(!caseSensitive, index, textToRemove, 0, textToRemoveLength)) {
			    name = new StringBuffer(name).delete(index, index + textToRemoveLength).toString();
			    originalNameLength = name.length();
			    index--;
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
		    for (index = 0; index <= fullNameLength - textToRemoveLength; index++) {
			if (fullName.regionMatches(!caseSensitive, index, textToRemove, 0, textToRemoveLength)) {
			    fullName = new StringBuffer(fullName).delete(index, index + textToRemoveLength).toString();
			    fullNameLength = fullName.length();
			    index--;
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

    public void removeFirstOccurrence(boolean skipExtension, boolean caseSensitive) {
	newNames = new String[numberOfFiles];
	if (caseSensitive) {
	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    newNames[i] = originalNames[i].replaceFirst(textToRemove, "");
		}
	    }
	    else {
		newExtensions = new String[numberOfFiles];
		newExtensionCreated = true;

		for (int i = 0; i < numberOfFiles; i++) {
		    String fullName = (originalNames[i] + originalExtensions[i]).replaceFirst(textToRemove, "");

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
	    int textToRemoveLength = textToRemove.length();
	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    int originalNameLength = originalNames[i].length();
		    StringBuilder nameBuilder = new StringBuilder(originalNames[i]);
		    boolean foundIt = false;

		    int index;
		    for (index = 0; index <= originalNameLength - textToRemoveLength; index++) {
			if (originalNames[i].regionMatches(!caseSensitive, index, textToRemove, 0, textToRemoveLength)) {
			    foundIt = true;
			    break;
			}
		    }
		    if (foundIt) {
			nameBuilder.delete(index, index + textToRemoveLength);
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
		    for (index = 0; index <= fullNameLength - textToRemoveLength; index++) {
			if (fullName.regionMatches(!caseSensitive, index, textToRemove, 0, textToRemoveLength)) {
			    foundIt = true;
			    break;
			}
		    }
		    if (foundIt) {
			fullNameBuffer = new StringBuffer(fullName);
			fullNameBuffer.delete(index, index + textToRemoveLength);
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

    public void removeLastOccurrence(boolean skipExtension, boolean caseSensitive) {
	newNames = new String[numberOfFiles];
	String textToRemoveReverse = new StringBuffer(textToRemove).reverse().toString();

	if (caseSensitive) {
	    if (skipExtension) {
		StringBuffer nameBufferReverse;
		String nameReverse;
		for (int i = 0; i < numberOfFiles; i++) {
		    nameBufferReverse = new StringBuffer(originalNames[i]).reverse();
		    nameReverse = new String(nameBufferReverse).replaceFirst(textToRemoveReverse, "");
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
		    fullName = new StringBuffer(new String(fullNameBufferReverse).replaceFirst(textToRemoveReverse, "")).reverse().toString();

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
	    int textToRemoveLength = textToRemove.length();

	    if (skipExtension) {
		for (int i = 0; i < numberOfFiles; i++) {
		    String nameReverse = new StringBuffer(originalNames[i]).reverse().toString();
		    int originalNameLength = nameReverse.length();

		    boolean foundIt = false;

		    int index;
		    for (index = 0; index <= originalNameLength - textToRemoveLength; index++) {
			if (nameReverse.regionMatches(!caseSensitive, index, textToRemoveReverse, 0, textToRemoveLength)) {
			    foundIt = true;
			    break;
			}
		    }
		    if (foundIt) {
			newNames[i] = new StringBuffer(nameReverse).delete(index, index + textToRemoveLength).reverse().toString();
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
			if (fullNameReverse.regionMatches(!caseSensitive, index, textToRemoveReverse, 0, textToRemoveLength)) {
			    foundIt = true;
			    break;
			}
		    }
		    if (foundIt) {
			fullNameBuffer = new StringBuffer(fullNameReverse);
			fullNameBuffer.delete(index, index + textToRemoveLength).reverse();
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
