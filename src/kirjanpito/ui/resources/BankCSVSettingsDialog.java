package kirjanpito.ui.resources;

import kirjanpito.db.*;
import kirjanpito.ui.Kirjanpito;
import kirjanpito.ui.SwingUtils;
import kirjanpito.util.Registry;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankCSVSettingsDialog extends JDialog {
    private Registry registry;
    private JComboBox<String> decimalSeparatorComboBox;
    private JLabel decimalSeparatorLabel;
    private JComboBox<String> fieldSeparatorComboBox;
    private JLabel fieldSeparatorLabel;
    private JComboBox<String> textIdentifierComboBox;
    private JLabel textIdentifierLabel;
    private JComboBox<Integer> sumFieldComboBox;
    private JLabel sumFieldLabel;
    private JComboBox<Integer> dateFieldComboBox;
    private JLabel dateFieldLabel;
    private JComboBox<Integer> descFieldComboBox;
    private JLabel descFieldLabel;
    private JTextField dateFormatTextField;
    private JButton okButton;
    private JButton cancelButton;
    private SimpleDateFormat dateFormat;

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Kirjanpito.LOGGER_NAME);


    public BankCSVSettingsDialog(Frame owner, Registry registry) {
        super(owner, "CSV tiedoston asetukset", true);
        this.registry = registry;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }


    /**
     * Luo ikkunan komponentit.
     */
    public void create() {
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        createContentPanel();
        createButtonPanel();
//        loadSettings();
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void createContentPanel() {
        GridBagConstraints c;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(panel, BorderLayout.CENTER);

        String[] decimalSeparators = {",", "."};
        decimalSeparatorLabel = new JLabel("Desimaalierotin: ");
        decimalSeparatorComboBox = new JComboBox(decimalSeparators);
//        decimalSeparatorComboBox.setEditable(true);
        String[] fieldSeparators = {";", ","};
        fieldSeparatorLabel = new JLabel("Kenttäerotin: ");
        fieldSeparatorComboBox = new JComboBox(fieldSeparators);
//        fieldSeparatorComboBox.setEditable(true);
        String[] textIndentifiers = {"\"", "'"};
        textIdentifierLabel = new JLabel("Tekstin tunnusmerkki: ");
        textIdentifierComboBox = new JComboBox(textIndentifiers); //TODO Jatka tästä
//        textIdentifierComboBox.setEditable(true);
//        sumFieldComboBox;
//        dateFieldComboBox;
//        descFieldComboBox;
        dateFormatTextField = new JTextField("Päivämäärämuoto");

        c = new GridBagConstraints();
        c.insets = new Insets(0, 5, 5, 0);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(decimalSeparatorLabel, c);
        c.gridx = 1;
        panel.add(decimalSeparatorComboBox, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(fieldSeparatorLabel, c);
        c.gridx = 1;
        panel.add(fieldSeparatorComboBox, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(textIdentifierLabel, c);
        c.gridx = 1;
        panel.add(textIdentifierComboBox, c);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(dateFormatTextField, c);


    }

    private void createButtonPanel() {
        GridBagConstraints c;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(panel, BorderLayout.SOUTH);

        okButton = new JButton("OK");
        okButton.setMnemonic('O');
        okButton.setPreferredSize(new Dimension(100, 30));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acceptChanges();
            }
        });

        cancelButton = new JButton("Peruuta");
        cancelButton.setMnemonic('P');
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        c = new GridBagConstraints();
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0, 0, 0, 5);
        panel.add(cancelButton, c);

        c = new GridBagConstraints();
        panel.add(okButton, c);

        rootPane.setDefaultButton(okButton);
    }

    private void acceptChanges() {

        System.out.println("SAVED");
//            saveSettings();


        dispose();
    }

}
