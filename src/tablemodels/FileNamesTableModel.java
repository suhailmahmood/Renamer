package tablemodels;

import organiser.Manager;
import javax.swing.table.AbstractTableModel;

/**
 * An instance of this class creates the table model for file-names table in the
 * main window of the application. An instance of this class must be called
 * whenever table model needs to be updated.
 *
 * @author Suhail
 */
public class FileNamesTableModel extends AbstractTableModel {

    private String[] originalNames;
    private String[] newNames;
    private String[] originalExtensions;
    private String[] newExtensions;
    private int numberOfFiles;
    private String[] columnNames;
    private Object[][] dataObject;

    public FileNamesTableModel() {

	originalNames = Manager.originalFileNames;
	originalExtensions = Manager.originalFileExtensions;
	newNames = Manager.renamedFileNames;
	newExtensions = Manager.renamedFileExtensions;
	numberOfFiles = Manager.numberOfFiles;

	columnNames = new String[]{"Serial", "Old Names", "New Names"};
	dataObject = new Object[numberOfFiles][3];

	if (originalNames != null) {
	    for (int i = 0; i < numberOfFiles; i++) {
		dataObject[i][0] = i + 1;
		dataObject[i][1] = originalNames[i].concat(originalExtensions[i]);
		if (newNames == null && newExtensions == null) {
		    dataObject[i][2] = "";
		}
		else if (newNames == null && newExtensions != null) {
		    dataObject[i][2] = originalNames[i].concat(newExtensions[i]);
		}
		else if (newNames != null && newExtensions == null) {
		    dataObject[i][2] = newNames[i].concat(originalExtensions[i]);
		}
		else {
		    dataObject[i][2] = newNames[i].concat(newExtensions[i]);
		}
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
	    return 16;
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
}