package organiser;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The starting point of the Renamer application. It instantiates the Manager
 * class which controls the whole application.
 *
 * @author Suhail
 * @version 1.0
 */
public class AppStarter {

    public static Manager mainWindow;
    public static Map<String, String> themeNamesMap;

    public static void main(String[] args) {

        try {
            ThemeManager.populateThemeNamesMap();
            javax.swing.UIManager.setLookAndFeel(ThemeManager.getLastTheme());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            try {
                javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex1) {
                Logger.getLogger(AppStarter.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AppStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainWindow = new Manager();
        mainWindow.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(700, 550);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}
