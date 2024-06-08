import javax.swing.*;
import java.awt.*;

public class NewCDDialog extends JDialog {
    private Store store = Store.getInstance();
    public NewCDDialog(){
        init();
    }
    public void init(){
        setTitle("New CD");
        setSize(400, 350);
        setLayout(new GridLayout(8,1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel cdID = new JLabel("CD ID");
        JTextField cdIDField = new JTextField(25);
        JPanel cdIDPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cdIDPanel.add(cdID);
        cdIDPanel.add(cdIDField);

        JLabel cdTitle = new JLabel("CD Title");
        JTextField cdTitleField = new JTextField(25);
        JPanel cdTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cdTitlePanel.add(cdTitle);
        cdTitlePanel.add(cdTitleField);

        JLabel cdCollection = new JLabel("CD Collection");
        String[] combo = {"Game", "Music", "Movie"};
        JComboBox cdCollectCombo = new JComboBox(combo);
        JLabel cdType = new JLabel("CD Type");
        JRadioButton cdTypeRadio = new JRadioButton("CD");
        JRadioButton vcdTypeRadio = new JRadioButton("VCD");
        ButtonGroup cdTypeGroup = new ButtonGroup();
        cdTypeGroup.add(cdTypeRadio);
        cdTypeGroup.add(vcdTypeRadio);
        JPanel cdTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cdTypePanel.add(cdCollection);
        cdTypePanel.add(cdCollectCombo);
        cdTypePanel.add(cdType);
        cdTypePanel.add(vcdTypeRadio);
        cdTypePanel.add(cdTypeRadio);

        JLabel cdPrice = new JLabel("CD Price");
        JTextField cdPriceField = new JTextField(25);
        JPanel cdPricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cdPricePanel.add(cdPrice);
        cdPricePanel.add(cdPriceField);

        JLabel cdYear = new JLabel("Release Year");
        JTextField cdYearField = new JTextField(25);
        JPanel cdYearPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cdYearPanel.add(cdYear);
        cdYearPanel.add(cdYearField);

        JButton addButton = new JButton("Add");
        JButton clearButton = new JButton("Clear");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);

        add(cdIDPanel);
        add(cdTitlePanel);
        add(cdTypePanel);
        add(cdPricePanel);
        add(cdYearPanel);
        add(buttonPanel);

        addButton.addActionListener(e -> {
            String id = cdIDField.getText();
            String title = cdTitleField.getText();
            String collection = cdCollectCombo.getSelectedItem().toString();
            String type = "null";
            if (cdTypeRadio.isSelected()){
                type = "CD";
            } else if (vcdTypeRadio.isSelected()){
                type = "VCD";
            }
            try{
                int price = Integer.parseInt(cdPriceField.getText());
                int year = Integer.parseInt(cdYearField.getText());
                CD cd = new CD(id, collection, type, title, price, year);
                store.addCD(cd);
                JOptionPane.showMessageDialog(null,  title+" Added Successfully");
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Price and Year must be a Number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        clearButton.addActionListener(e -> {
            cdIDField.setText("");
            cdTitleField.setText("");
            cdPriceField.setText("");
            cdYearField.setText("");
            cdCollectCombo.setSelectedIndex(0);
        });
    }
}
