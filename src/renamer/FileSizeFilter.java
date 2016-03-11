package renamer;

import organiser.Manager;

public class FileSizeFilter {

    private String theRule;

    public FileSizeFilter(String rule) {
        theRule = rule;
    }

    public void applyFileSizeFilterRule() {
        if (theRule.equals("")) {
            Manager.lowerFileSizeLimit = -1;
            Manager.upperFileSizeLimit = -1;
            return;
        }
        String[] terms = theRule.split(":");
        if (!terms[1].equals("")) {
            switch (terms[2]) {
                case "B":
                    Manager.lowerFileSizeLimit = Long.parseLong(terms[1]);
                    break;
                case "KB":
                    Manager.lowerFileSizeLimit = Long.parseLong(terms[1]) * 1024;
                    break;
                case "MB":
                    Manager.lowerFileSizeLimit = Long.parseLong(terms[1]) * 1024 * 1024;
                    break;
            }
        }
        else {
            Manager.lowerFileSizeLimit = -1;
        }
        if (!terms[3].equals("")) {
            switch (terms[4]) {
                case "B":
                    Manager.upperFileSizeLimit = Long.parseLong(terms[3]);
                    break;
                case "KB":
                    Manager.upperFileSizeLimit = Long.parseLong(terms[3]) * 1024;
                    break;
                case "MB":
                    Manager.upperFileSizeLimit = Long.parseLong(terms[3]) * 1024 * 1024;
                    break;
            }
        }
        else {
            Manager.upperFileSizeLimit = -1;
        }
        Manager.excludeFilesInRange = Boolean.parseBoolean(terms[5]);
    }
}
