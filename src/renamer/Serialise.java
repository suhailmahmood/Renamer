package renamer;

import organiser.Manager;


public class Serialise {

    private String[] originalNames;
    private String[] originalExtensions;
    private String[] newNames;
    private String[] newExtensions;
    private String theRule;
    private int numberOfFiles;
    private boolean newExtensionCreated;

    public Serialise(String rule) {
	originalNames = Manager.getFileNames();
	originalExtensions = Manager.getFileExtensions();
	numberOfFiles = Manager.numberOfFiles;
	theRule = rule;
    }

    public void applySerialiseRule(String theRule) {
	String[] terms = theRule.split(":");
	int index = Integer.parseInt(terms[2]);
	int step = Integer.parseInt(terms[3]);
	int length = Integer.parseInt(terms[4]);
	switch (terms[1]) {
	    case "pre":
		if (terms.length == 5)
		    prefixSerial(index, step, length, null, null);
		else
		    prefixSerial(index, step, length, terms[5], terms[6]);
		break;
	    case "suf": {
		boolean skipExtension = Boolean.parseBoolean(terms[5]);
		if (terms.length == 6)
		    suffixSerial(index, step, length, skipExtension, null, null);
		else
		    suffixSerial(index, step, length, skipExtension, terms[6], terms[7]);
		break;
	    }
	    default: {
		boolean skipExtension = Boolean.parseBoolean(terms[5]);
		int pos = Integer.parseInt(terms[6]) - 1;
		if (terms.length == 7)
		    atPositionSerial(index, step, length, skipExtension, pos, null, null);
		else
		    atPositionSerial(index, step, length, skipExtension, pos, terms[7], terms[8]);
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

    public void prefixSerial(int index, int step, int length, String textPad, String textPadLoc) {
	newNames = new String[numberOfFiles];
	int currentIndex = index;
	if (length == 0) {
	    if (textPad == null) {
		for (int i = 0; i < numberOfFiles; i++) {
		    newNames[i] = String.valueOf(currentIndex) + originalNames[i];
		    currentIndex += step;
		}
	    }
	    else {
		if (textPadLoc.equals("bef")) {
		    for (int i = 0; i < numberOfFiles; i++) {
			newNames[i] = textPad + String.valueOf(currentIndex) + originalNames[i];
			currentIndex += step;
		    }
		}
		else {
		    for (int i = 0; i < numberOfFiles; i++) {
			newNames[i] = String.valueOf(currentIndex) + textPad + originalNames[i];
		    }
		}
	    }
	}
	else {
	    if (textPad == null) {
		for (int i = 0; i < numberOfFiles; i++) {
		    int diff = length - String.valueOf(currentIndex).length();
		    if (diff > 0) {
			char[] zeroPadding = new char[diff];
			for (int j = 0; j < diff; j++) {
			    zeroPadding[j] = '0';
			}
			newNames[i] = new String(zeroPadding) + String.valueOf(currentIndex) + originalNames[i];
		    }
		    else {
			newNames[i] = String.valueOf(currentIndex) + originalNames[i];
		    }
		    currentIndex += step;
		}
	    }
	    else {
		if (textPadLoc.equals("bef")) {
		    for (int i = 0; i < numberOfFiles; i++) {
			int diff = length - String.valueOf(currentIndex).length();
			if (diff > 0) {
			    char[] zeroPadding = new char[diff];
			    for (int j = 0; j < diff; j++) {
				zeroPadding[j] = '0';
			    }
			    newNames[i] = textPad + new String(zeroPadding) + String.valueOf(currentIndex) + originalNames[i];
			}
			else {
			    newNames[i] = textPad + String.valueOf(currentIndex) + originalNames[i];
			}
			currentIndex += step;
		    }
		}
		else {
		    for (int i = 0; i < numberOfFiles; i++) {
			int diff = length - String.valueOf(currentIndex).length();
			if (diff > 0) {
			    char[] zeroPadding = new char[diff];
			    for (int j = 0; j < diff; j++) {
				zeroPadding[j] = '0';
			    }
			    newNames[i] = new String(zeroPadding) + String.valueOf(currentIndex) + textPad + originalNames[i];
			}
			else {
			    newNames[i] = String.valueOf(currentIndex) + textPad + originalNames[i];
			}
			currentIndex += step;
		    }
		}
	    }
	}
    }

    public void suffixSerial(int index, int step, int length, boolean skipExtension, String textPad, String textPadLoc) {
	newNames = new String[numberOfFiles];
	int currentIndex = index;
	if (length == 0) {
	    if (skipExtension) {
		if (textPad == null) {
		    for (int i = 0; i < numberOfFiles; i++) {
			newNames[i] = originalNames[i] + String.valueOf(currentIndex);
			currentIndex += step;
		    }
		}
		else {
		    if (textPadLoc.equals("bef")) {
			for (int i = 0; i < numberOfFiles; i++) {
			    newNames[i] = originalNames[i] + textPad + String.valueOf(currentIndex);
			    currentIndex += step;
			}
		    }
		    else {
			for (int i = 0; i < numberOfFiles; i++) {
			    newNames[i] = originalNames[i] + String.valueOf(currentIndex) + textPad;
			    currentIndex += step;
			}
		    }
		}
	    }
	    else {
		newExtensionCreated = true;
		newExtensions = new String[numberOfFiles];
		if (textPadLoc.equals("bef")) {
		    for (int i = 0; i < numberOfFiles; i++) {
			String fullName = originalNames[i] + originalExtensions[i] + textPad + String.valueOf(currentIndex);
			currentIndex += step;
			setNewNameAndExtensionAtIndex(fullName, i);
		    }
		}
		else {
		    for (int i = 0; i < numberOfFiles; i++) {
			String fullName = originalNames[i] + originalExtensions[i] + String.valueOf(currentIndex) + textPad;
			currentIndex += step;
			setNewNameAndExtensionAtIndex(fullName, i);
		    }
		}
	    }
	}
	// if(length != 0)...
	else {
	    if (skipExtension) {
		if (textPad == null) {
		    for (int i = 0; i < numberOfFiles; i++) {
			int diff = length - String.valueOf(currentIndex).length();
			char[] zeroPadding;

			if (diff > 0) {
			    zeroPadding = new char[diff];
			    for (int j = 0; j < diff; j++) {
				zeroPadding[j] = '0';
			    }
			    newNames[i] = originalNames[i] + new String(zeroPadding) + String.valueOf(currentIndex);
			}
			else {
			    newNames[i] = originalNames[i] + String.valueOf(currentIndex);
			}
			currentIndex += step;
		    }
		}
		else {
		    if (textPadLoc.equals("bef")) {
			for (int i = 0; i < numberOfFiles; i++) {
			    int diff = length - String.valueOf(currentIndex).length();
			    char[] zeroPadding;

			    if (diff > 0) {
				zeroPadding = new char[diff];
				for (int j = 0; j < diff; j++) {
				    zeroPadding[j] = '0';
				}
				newNames[i] = originalNames[i] + textPad + new String(zeroPadding) + String.valueOf(currentIndex);
			    }
			    else {
				newNames[i] = originalNames[i] + textPad + String.valueOf(currentIndex);
			    }
			    currentIndex += step;
			}
		    }
		    else {
			for (int i = 0; i < numberOfFiles; i++) {
			    int diff = length - String.valueOf(currentIndex).length();
			    char[] zeroPadding;

			    if (diff > 0) {
				zeroPadding = new char[diff];
				for (int j = 0; j < diff; j++) {
				    zeroPadding[j] = '0';
				}
				newNames[i] = originalNames[i] + new String(zeroPadding) + String.valueOf(currentIndex) + textPad;
			    }
			    else {
				newNames[i] = originalNames[i] + String.valueOf(currentIndex) + textPad;
			    }
			    currentIndex += step;
			}
		    }
		}
	    }
	    else {
		newExtensionCreated = true;
		newExtensions = new String[numberOfFiles];
		if (textPad == null) {
		    for (int i = 0; i < numberOfFiles; i++) {
			int diff = length - String.valueOf(currentIndex).length();
			char[] zeroPadding;

			if (diff > 0) {
			    zeroPadding = new char[diff];
			    for (int j = 0; j < diff; j++) {
				zeroPadding[j] = '0';
			    }
			    String fullName = originalNames[i] + originalExtensions[i] + new String(zeroPadding) + String.valueOf(currentIndex);
			    setNewNameAndExtensionAtIndex(fullName, i);
			}
			else {
			    String fullName = originalNames[i] + originalExtensions[i] + String.valueOf(currentIndex);
			    setNewNameAndExtensionAtIndex(fullName, i);
			}
			currentIndex += step;
		    }
		}
		else {
		    if (textPadLoc.equals("bef")) {
			for (int i = 0; i < numberOfFiles; i++) {
			    int diff = length - String.valueOf(currentIndex).length();
			    char[] zeroPadding;

			    if (diff > 0) {
				zeroPadding = new char[diff];
				for (int j = 0; j < diff; j++) {
				    zeroPadding[j] = '0';
				}
				String fullName = originalNames[i] + originalExtensions[i] + textPad + new String(zeroPadding) + String.valueOf(currentIndex);
				setNewNameAndExtensionAtIndex(fullName, i);
			    }
			    else {
				String fullName = originalNames[i] + originalExtensions[i] + textPad + String.valueOf(currentIndex);
				setNewNameAndExtensionAtIndex(fullName, i);
			    }
			    currentIndex += step;
			}
		    }
		    else {
			for (int i = 0; i < numberOfFiles; i++) {
			    int diff = length - String.valueOf(currentIndex).length();
			    char[] zeroPadding;

			    if (diff > 0) {
				zeroPadding = new char[diff];
				for (int j = 0; j < diff; j++) {
				    zeroPadding[j] = '0';
				}
				String fullName = originalNames[i] + originalExtensions[i] + new String(zeroPadding) + String.valueOf(currentIndex) + textPad;
				setNewNameAndExtensionAtIndex(fullName, i);
			    }
			    else {
				String fullName = originalNames[i] + originalExtensions[i] + String.valueOf(currentIndex) + textPad;
				setNewNameAndExtensionAtIndex(fullName, i);
			    }
			    currentIndex += step;
			}
		    }
		}
	    }
	}
    }

    public void atPositionSerial(int index, int step, int length, boolean skipExtension, int pos, String textPad, String textPadLoc) {
	newNames = new String[numberOfFiles];
	int currentIndex = index;
	if (length == 0) {
	    if (skipExtension) {
		if (textPad == null) {
		    for (int i = 0; i < numberOfFiles; i++) {
			StringBuilder name = new StringBuilder(originalNames[i]);

			int posForThisFile = pos > originalNames[i].length() ? originalNames[i].length() : pos;
			name.insert(posForThisFile, String.valueOf(currentIndex));
			newNames[i] = name.toString();
			currentIndex += step;
		    }
		}
		else {
		    if (textPadLoc.equals("bef")) {
			for (int i = 0; i < numberOfFiles; i++) {
			    StringBuilder name = new StringBuilder(originalNames[i]);

			    int posForThisFile = pos > originalNames[i].length() ? originalNames[i].length() : pos;
			    name.insert(posForThisFile, textPad.concat(String.valueOf(currentIndex)));
			    newNames[i] = name.toString();
			    currentIndex += step;
			}
		    }
		    else {
			for (int i = 0; i < numberOfFiles; i++) {
			    StringBuilder name = new StringBuilder(originalNames[i]);

			    int posForThisFile = pos > originalNames[i].length() ? originalNames[i].length() : pos;
			    name.insert(posForThisFile, String.valueOf(currentIndex).concat(textPad));
			    newNames[i] = name.toString();
			    currentIndex += step;
			}
		    }
		}
	    }
	    else {
		newExtensionCreated = true;
		newExtensions = new String[numberOfFiles];
		if (textPad == null) {
		    for (int i = 0; i < numberOfFiles; i++) {
			StringBuilder fullName = new StringBuilder(originalNames[i] + originalExtensions[i]);

			int posForThisFile = pos > fullName.length() ? fullName.length() : pos;
			fullName.insert(posForThisFile, String.valueOf(currentIndex));
			setNewNameAndExtensionAtIndex(fullName.toString(), i);
			currentIndex += step;
		    }
		}
		else {
		    if (textPadLoc.equals("bef")) {
			for (int i = 0; i < numberOfFiles; i++) {
			    StringBuilder fullName = new StringBuilder(originalNames[i] + originalExtensions[i]);

			    int posForThisFile = pos > fullName.length() ? fullName.length() : pos;
			    fullName.insert(posForThisFile, textPad.concat(String.valueOf(currentIndex)));
			    setNewNameAndExtensionAtIndex(fullName.toString(), i);
			    currentIndex += step;
			}
		    }
		    else {
			for (int i = 0; i < numberOfFiles; i++) {
			    StringBuilder fullName = new StringBuilder(originalNames[i] + originalExtensions[i]);

			    int posForThisFile = pos > fullName.length() ? fullName.length() : pos;
			    fullName.insert(posForThisFile, String.valueOf(currentIndex).concat(textPad));
			    setNewNameAndExtensionAtIndex(fullName.toString(), i);
			    currentIndex += step;
			}
		    }
		}
	    }
	}
	// if (length != 0)...
	else {
	    if (skipExtension) {
		if (textPad == null) {
		    for (int i = 0; i < numberOfFiles; i++) {
			StringBuilder name = new StringBuilder(originalNames[i]);
			int posForThisFile = Math.min(pos, name.length());
			int diff = length - String.valueOf(currentIndex).length();
			if (diff > 0) {
			    char[] zeroPadding = new char[diff];
			    for (int j = 0; j < diff; j++) {
				zeroPadding[j] = '0';
			    }
			    name.insert(posForThisFile, new String(zeroPadding).concat(String.valueOf(currentIndex)));
			    newNames[i] = name.toString();
			}
			else {
			    name.insert(posForThisFile, String.valueOf(currentIndex));
			    newNames[i] = name.toString();
			}
			currentIndex += step;
		    }
		}
		else {
		    if (textPadLoc.equals("bef")) {
			for (int i = 0; i < numberOfFiles; i++) {
			    StringBuilder name = new StringBuilder(originalNames[i]);
			    int posForThisFile = Math.min(pos, name.length());
			    int diff = length - String.valueOf(currentIndex).length();
			    if (diff > 0) {
				char[] zeroPadding = new char[diff];
				for (int j = 0; j < diff; j++) {
				    zeroPadding[j] = '0';
				}
				name.insert(posForThisFile, textPad.concat(new String(zeroPadding).concat(String.valueOf(currentIndex))));
				newNames[i] = name.toString();
			    }
			    else {
				name.insert(posForThisFile, String.valueOf(currentIndex));
				newNames[i] = name.toString();
			    }
			    currentIndex += step;
			}
		    }
		    else {
			for (int i = 0; i < numberOfFiles; i++) {
			    StringBuilder name = new StringBuilder(originalNames[i]);
			    int posForThisFile = Math.min(pos, name.length());
			    int diff = length - String.valueOf(currentIndex).length();
			    if (diff > 0) {
				char[] zeroPadding = new char[diff];
				for (int j = 0; j < diff; j++) {
				    zeroPadding[j] = '0';
				}
				name.insert(posForThisFile, new String(zeroPadding).concat(String.valueOf(currentIndex)).concat(textPad));
				newNames[i] = name.toString();
			    }
			    else {
				name.insert(posForThisFile, String.valueOf(currentIndex));
				newNames[i] = name.toString();
			    }
			    currentIndex += step;
			}
		    }
		}
	    }
	    else {
		newExtensionCreated = true;
		newExtensions = new String[numberOfFiles];
		if (textPad == null) {
		    for (int i = 0; i < numberOfFiles; i++) {
			StringBuilder fullNameBuilder = new StringBuilder(originalNames[i] + originalExtensions[i]);
			int posForThisFile = Math.min(fullNameBuilder.length(), pos);
			int diff = length - String.valueOf(currentIndex).length();
			if (diff > 0) {
			    char[] zeroPadding = new char[diff];
			    for (int j = 0; j < diff; j++) {
				zeroPadding[j] = '0';
			    }
			    fullNameBuilder.insert(posForThisFile, new String(zeroPadding).concat(String.valueOf(currentIndex)));
			    setNewNameAndExtensionAtIndex(fullNameBuilder.toString(), i);
			}
			else {
			    fullNameBuilder.insert(posForThisFile, String.valueOf(currentIndex));
			    setNewNameAndExtensionAtIndex(fullNameBuilder.toString(), i);
			}
			currentIndex += step;
		    }
		}
		else {
		    if (textPadLoc.equals("bef")) {
			for (int i = 0; i < numberOfFiles; i++) {
			    StringBuilder fullNameBuilder = new StringBuilder(originalNames[i] + originalExtensions[i]);
			    int posForThisFile = Math.min(fullNameBuilder.length(), pos);
			    int diff = length - String.valueOf(currentIndex).length();
			    if (diff > 0) {
				char[] zeroPadding = new char[diff];
				for (int j = 0; j < diff; j++) {
				    zeroPadding[j] = '0';
				}
				fullNameBuilder.insert(posForThisFile, textPad.concat(new String(zeroPadding).concat(String.valueOf(currentIndex))));
				setNewNameAndExtensionAtIndex(fullNameBuilder.toString(), i);
			    }
			    else {
				fullNameBuilder.insert(posForThisFile, String.valueOf(currentIndex));
				setNewNameAndExtensionAtIndex(fullNameBuilder.toString(), i);
			    }
			    currentIndex += step;
			}
		    }
		    else {
			for (int i = 0; i < numberOfFiles; i++) {
			    StringBuilder fullNameBuilder = new StringBuilder(originalNames[i] + originalExtensions[i]);
			    int posForThisFile = Math.min(fullNameBuilder.length(), pos);
			    int diff = length - String.valueOf(currentIndex).length();
			    if (diff > 0) {
				char[] zeroPadding = new char[diff];
				for (int j = 0; j < diff; j++) {
				    zeroPadding[j] = '0';
				}
				fullNameBuilder.insert(posForThisFile, new String(zeroPadding).concat(String.valueOf(currentIndex)).concat(textPad));
				setNewNameAndExtensionAtIndex(fullNameBuilder.toString(), i);
			    }
			    else {
				fullNameBuilder.insert(posForThisFile, String.valueOf(currentIndex));
				setNewNameAndExtensionAtIndex(fullNameBuilder.toString(), i);
			    }
			    currentIndex += step;
			}
		    }
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
