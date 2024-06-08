import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CDTable extends JScrollPane {
    private DefaultTableModel model;
    private JTable table;
    private Store store = Store.getInstance();
    public CDTable(){
        init();
    }
    public void init(){
        model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Collection");
        model.addColumn("Type");
        model.addColumn("Price");


        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        fillTable(store.getCDlist());
        setViewportView(table);
    }
    public void fillTable(ArrayList<CD> list){
        model.setRowCount(0);
        for(CD cd : list){
            model.addRow(new String[]{
                    cd.getTitle(),
                    cd.getCollection(),
                    cd.getType(),
                    String.valueOf(cd.getPrice())
            });
        }
    }
    public void deleteRow(){
        int row = table.getSelectedRow();
        int col = 0;
        if(row != -1){
            String title = (String) model.getValueAt(row, col);
            model.removeRow(row);
            store.deleteByTitle(title);
        }
    }
}
