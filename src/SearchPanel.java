import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private Store store = Store.getInstance();
    public SearchPanel(){
        init();
    }
    public void init(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] combo = {"Title", "Collection", "Type", "Price"};
        JComboBox comboBox = new JComboBox(combo);
        comboBox.setSelectedIndex(0);
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Search");
        add(comboBox);
        add(textField);
        add(button);

        button.addActionListener(e -> {
           if(comboBox.getSelectedIndex() == 0){
               String title = textField.getText();
               Main.cdTable.fillTable(store.containsTitle(title));
           } else if(comboBox.getSelectedIndex() == 1){
               String collection = textField.getText();
               Main.cdTable.fillTable(store.searchCollection(collection));
           } else if(comboBox.getSelectedIndex() == 2){
               String type = textField.getText().toUpperCase();
               Main.cdTable.fillTable(store.searchType(type));
           } else if(comboBox.getSelectedIndex() == 3){
               try {
                   int price = Integer.parseInt(textField.getText());
                   Main.cdTable.fillTable(store.searchPrice(price));
               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(null, "Price must be a Number", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
        });
    }
}
