package tablemodels;

import organiser.Manager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Suhail
 */
public final class RulesTableModel extends AbstractTableModel {

    private String[] theRules;
    private final String[] columnNames;
    private final Object[][] dataObject;
    private int numberOfRules;

    public RulesTableModel() {
	columnNames = new String[]{"Serial", "Rule Description"};
	if (!Manager.rulesList.isEmpty()) {
	    Object[] theRulesObjects = Manager.rulesList.toArray();
	    numberOfRules = theRulesObjects.length;
	    theRules = new String[numberOfRules];

	    for (int i = 0; i < theRulesObjects.length; i++) {
		theRules[i] = theRulesObjects[i].toString();
	    }

	    for (int i = 0; i < theRules.length; i++) {
		if (theRules[i].startsWith("ins")) {
		    theRules[i] = decodeInsertRule(theRules[i]);
		}
		else if (theRules[i].startsWith("rem")) {
		    theRules[i] = decodeRemoveRule(theRules[i]);
		}
		else if (theRules[i].startsWith("rep")) {
		    theRules[i] = decodeReplaceRule(theRules[i]);
		}
		else if (theRules[i].startsWith("cas")) {
		    theRules[i] = decodeChangeCaseRule(theRules[i]);
		}
		else if (theRules[i].startsWith("ext")) {
		    theRules[i] = decodeExtensionRule(theRules[i]);
		}
		else if (theRules[i].startsWith("ser")) {
		    theRules[i] = decodeSerialiseRule(theRules[i]);
		}
		else if (theRules[i].startsWith("cle")) {
		    theRules[i] = decodeCleanupRule(theRules[i]);
		}
                else if(theRules[i].startsWith("siz")) {
                    theRules[i] = decodeFileSizeFilterRule(theRules[i]);
                }
	    }
	    dataObject = new Object[numberOfRules][2];
	    for (int i = 0; i < numberOfRules; i++) {
		dataObject[i][0] = i + 1;
		dataObject[i][1] = theRules[i];
	    }
	}
	else {
	    dataObject = null;
	}
    }

    @Override
    public int getRowCount() {
	if (dataObject != null)
	    return dataObject.length;
	else
	    return 7;
    }

    @Override
    public int getColumnCount() {
	return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
	return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	if (dataObject != null)
	    return dataObject[rowIndex][columnIndex];
	else
	    return null;
    }

    public String decodeInsertRule(String insertRule) {
	insertRule = insertRule.substring(insertRule.indexOf(':') + 1, insertRule.length());
	String textToInsert = insertRule.substring(0, insertRule.indexOf(':'));
	insertRule = insertRule.substring(insertRule.indexOf(':') + 1, insertRule.length());
	if (insertRule.startsWith("pre")) {
	    return String.format("INSERT: \"%s\" as prefix", textToInsert);
	}
	if (insertRule.startsWith("suf")) {
	    boolean skipExtension = Boolean.parseBoolean(insertRule.substring(insertRule.indexOf(':') + 1, insertRule.length()));

	    return String.format("INSERT: \"%s\" as suffix -- %sskip Extension", textToInsert, skipExtension ? "" : "don't ");
	}
	if (insertRule.startsWith("pos")) {
	    insertRule = insertRule.replaceFirst("pos:", "");
	    int pos = Integer.parseInt(insertRule.substring(0, insertRule.indexOf(':')));
	    boolean skipExtension = Boolean.parseBoolean(insertRule.substring(insertRule.indexOf(':') + 1, insertRule.length()));

	    return String.format("INSERT: \"%s\" at position: %d -- %sskip Extension", textToInsert, pos, skipExtension ? "" : "don't ");
	}
	else {
	    String aftOrBef = insertRule.substring(0, insertRule.indexOf(':'));
	    insertRule = insertRule.substring(insertRule.indexOf(':') + 1, insertRule.length());
	    String findText = insertRule.substring(0, insertRule.indexOf(':'));
	    boolean skipExtension = Boolean.parseBoolean(insertRule.substring(insertRule.indexOf(':') + 1, insertRule.lastIndexOf(':')));
	    boolean caseSensitive = Boolean.parseBoolean(insertRule.substring(insertRule.lastIndexOf(':') + 1, insertRule.length()));


	    if (aftOrBef.equals("aft"))
		return String.format("INSERT: \"%s\" AFTER \"%s\" -- %sskip extension -- %scase sensitive", textToInsert, findText, skipExtension ? "" : "don't ", caseSensitive ? "" : "not ");
	    else
		return String.format("INSERT: \"%s\" BEFORE \"%s\" -- %sskip extension -- %scase sensitive", textToInsert, findText, skipExtension ? "" : "don't ", caseSensitive ? "" : "not ");
	}
    }

    public String decodeRemoveRule(String removeRule) {
	removeRule = removeRule.substring(removeRule.indexOf(':') + 1, removeRule.length());
	String textToRemove = removeRule.substring(0, removeRule.indexOf(':'));
	removeRule = removeRule.substring(removeRule.indexOf(':') + 1, removeRule.length());

	boolean skipExtension = Boolean.parseBoolean(removeRule.substring(removeRule.indexOf(':') + 1, removeRule.lastIndexOf(':')));
	boolean caseSensitive = Boolean.parseBoolean(removeRule.substring(removeRule.lastIndexOf(':') + 1, removeRule.length()));

	if (removeRule.startsWith("all"))
	    return String.format("REMOVE: \"%s\" -- ALL occurrences -- %sskip extension -- %scase sensitive", textToRemove, skipExtension ? "" : "don't ", caseSensitive ? "" : "not ");
	else if (removeRule.startsWith("first"))
	    return String.format("REMOVE: \"%s\" -- FIRST occurrence only -- %sskip extension -- %scase sensitive", textToRemove, skipExtension ? "" : "don't ", caseSensitive ? "" : "not ");
	else
	    return String.format("REMOVE: \"%s\" -- LAST occurrence only -- %sskip extension -- %scase sensitive", textToRemove, skipExtension ? "" : "don't ", caseSensitive ? "" : "not ");
    }

    public String decodeReplaceRule(String replaceRule) {
	replaceRule = replaceRule.substring(replaceRule.indexOf(':') + 1, replaceRule.length());
	String textToFind = replaceRule.substring(0, replaceRule.indexOf(':'));
	replaceRule = replaceRule.substring(replaceRule.indexOf(':') + 1, replaceRule.length());
	String textToReplace = replaceRule.substring(0, replaceRule.indexOf(':'));
	replaceRule = replaceRule.substring(replaceRule.indexOf(':') + 1, replaceRule.length());

	boolean skipExtension = Boolean.parseBoolean(replaceRule.substring(replaceRule.indexOf(':') + 1, replaceRule.lastIndexOf(':')));
	boolean caseSensitive = Boolean.parseBoolean(replaceRule.substring(replaceRule.lastIndexOf(':') + 1, replaceRule.length()));

	if (replaceRule.startsWith("all"))
	    return String.format("REPLACE: \"%s\" with \"%s\" -- ALL occurrences -- %sskip extension -- %scase sensitive", textToFind, textToReplace, skipExtension ? "" : "don't ", caseSensitive ? "" : "not ");
	if (replaceRule.startsWith("first"))
	    return String.format("REPLACE: \"%s\" with \"%s\" -- FIRST occurrence only -- %sskip extension -- %scase sensitive", textToFind, textToReplace, skipExtension ? "" : "don't ", caseSensitive ? "" : "not ");

	return String.format("REPLACE: \"%s\" with \"%s\" -- LAST occurrence only -- %sskip extension -- %scase sensitive", textToFind, textToReplace, skipExtension ? "" : "don't ", caseSensitive ? "" : "not ");
    }

    public String decodeChangeCaseRule(String changeCaseRule) {
	if (changeCaseRule.endsWith("1")) {
	    return "CHANGE CASE: Capitalise First Letter Of Every Word";
	}
	if (changeCaseRule.endsWith("2")) {
	    return "CHANGE CASE: Capitalise first letter only";
	}
	if (changeCaseRule.endsWith("3")) {
	    return "CHANGE CASE: ALL LETTERS IN CAPITAL CASE";
	}
	if (changeCaseRule.endsWith("4")) {
	    return "CHANGE CASE: all letters in lower case";
	}
	return "CHANGE CASE: iNVERT cASE oF eVERY letter";
    }

    public String decodeExtensionRule(String extensionRule) {
	String extension = extensionRule.substring(extensionRule.indexOf(':') + 1, extensionRule.lastIndexOf(':'));
	boolean append = Boolean.parseBoolean(extensionRule.substring(extensionRule.lastIndexOf(':') + 1, extensionRule.length()));

	return String.format("EXTENSION: Change current extension to %s%s", extension, append ? " -- append to original file name" : "");
    }

    public String decodeSerialiseRule(String serialiseRule) {
	String[] terms = serialiseRule.split(":");
	String index = " -- starting from " + terms[2];
	String step = " -- with a step of " + terms[3];
	String length = terms[4].equals("0") ? "" : " -- total length of serial " + terms[4];
	if (terms[1].equals("pre")) {
	    String decodedRule = String.format("SERIALISE: as prefix%s%s%s", index, step, length);
	    if (terms.length == 5)
		return decodedRule;
	    else {
		String textPad = " -- add \"" + terms[5] + "\"";
		String textPadLoc = String.format(" %s serial", terms[6].equals("bef") ? "before" : "after");
		return (decodedRule + textPad + textPadLoc);
	    }
	}
	String skipExtension = Boolean.parseBoolean(terms[5]) ? " -- skip extension" : "don't skip extension";
	if (terms[1].equals("suf")) {
	    String decodedRule = String.format("SERIALISE: as suffix%s%s%s%s", index, step, length, skipExtension);
	    if (terms.length == 6)
		return decodedRule;
	    else {
		String textPad = " -- add \"" + terms[6] + "\"";
		String textPadLoc = String.format(" %s serial", terms[7].equals("bef") ? "before" : "after");
		return (decodedRule + textPad + textPadLoc);
	    }
	}
	String decodedRule = String.format("SERIALISE: at position %s %s%s%s%s", terms[6], index, step, length, skipExtension);
	if (terms.length == 7)
	    return decodedRule;
	else {
	    String textPad = " -- add \"" + terms[7] + "\"";
	    String textPadLoc = String.format(" %s serial", terms[8].equals("bef") ? "before" : "after");
	    return (decodedRule + textPad + textPadLoc);
	}
    }

    public String decodeCleanupRule(String cleanupRule) {
	String[] terms = cleanupRule.split(":");
	String alphabets = terms[1].equals("true") ? "all alphabets -- " : "";
	String digits = terms[2].equals("true") ? "all digits -- " : "";
	String symbols = terms[3].equals("true") ? "all symbols -- " : "";
	String brackets = terms[4].equals("true") ? "all brackets -- " : "";
	String userDefined = terms[5].isEmpty() ? "" : String.format("the characters in: \"%s\" -- ", terms[5]);
	String skipExtension = terms[6].equals("true") ? "skip extension" : "don't skip extension";
	return String.format("CLEAN-UP: %s%s%s%s%s%s", alphabets, digits, symbols, brackets, userDefined, skipExtension);
    }
    
    public String decodeFileSizeFilterRule(String sizeFilterRule) {
        String[] terms = sizeFilterRule.split(":");
        String lowerLimit = terms[1].equals("") ? "" : String.format("Lower limit: %s %s -- ", terms[1], terms[2].concat("ytes"));
        String upperLimit = terms[3].equals("") ? "" : String.format("Upper limit: %s %s -- ", terms[3], terms[4].concat("ytes"));
        String excludeRange = Boolean.parseBoolean(terms[5]) ? "exclude files in this range" : "only rename files in this range";
        return String.format("FILE-SIZE FILTER: %s%s%s", lowerLimit, upperLimit, excludeRange);
    }
}