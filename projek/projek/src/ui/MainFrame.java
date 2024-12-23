package ui;

import dao.YourTableDAO;
import java.awt.*;
import java.sql.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.YourTable;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtColumn1, txtColumn2, txtColumn3, txtColumn4, txtColumn5;
    private YourTableDAO yourTableDAO;

    public MainFrame() {
        yourTableDAO = new YourTableDAO();
        setTitle("Your Table Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel Utama
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Alamat", "Umur", "Tanggal Lahir", "Hobi"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Form Input
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Manage Data"));

        inputPanel.add(new JLabel("Nama:"));
        txtColumn1 = new JTextField();
        inputPanel.add(txtColumn1);

        inputPanel.add(new JLabel("Alamat:"));
        txtColumn2 = new JTextField();
        inputPanel.add(txtColumn2);

        inputPanel.add(new JLabel("Umur:"));
        txtColumn3 = new JTextField();
        inputPanel.add(txtColumn3);

        inputPanel.add(new JLabel("Tanggal Lahir (YYYY-MM-DD):"));
        txtColumn4 = new JTextField();
        inputPanel.add(txtColumn4);

        inputPanel.add(new JLabel("Hobi:"));
        txtColumn5 = new JTextField();
        inputPanel.add(txtColumn5);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Tombol
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Load data ke tabel
        loadTableData();

        // Event Handlers
        btnAdd.addActionListener(e -> addData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());

        setVisible(true);
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        List<YourTable> data = yourTableDAO.getAll();
        for (YourTable row : data) {
            tableModel.addRow(new Object[]{
                row.getId(),
                row.getColumn1(),
                row.getColumn2(),
                row.getColumn3(),
                row.getColumn4(),
                row.getColumn5()
            });
        }
    }

    private void addData() {
        try {
            YourTable newRow = new YourTable();
            newRow.setColumn1(txtColumn1.getText());
            newRow.setColumn2(txtColumn2.getText());
            newRow.setColumn3(Integer.parseInt(txtColumn3.getText()));
            newRow.setColumn4(Date.valueOf(txtColumn4.getText()));
            newRow.setColumn5(txtColumn5.getText());

            yourTableDAO.insert(newRow);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Data added successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateData() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to update.");
                return;
            }

            int id = (int) tableModel.getValueAt(selectedRow, 0);
            YourTable updatedRow = new YourTable();
            updatedRow.setId(id);
            updatedRow.setColumn1(txtColumn1.getText());
            updatedRow.setColumn2(txtColumn2.getText());
            updatedRow.setColumn3(Integer.parseInt(txtColumn3.getText()));
            updatedRow.setColumn4(Date.valueOf(txtColumn4.getText()));
            updatedRow.setColumn5(txtColumn5.getText());

            yourTableDAO.update(updatedRow);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Data updated successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteData() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }

            int id = (int) tableModel.getValueAt(selectedRow, 0);
            yourTableDAO.delete(id);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Data deleted successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
