import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListExample {
    public static void main(String[] args) {
        // Membuat JFrame
        JFrame frame = new JFrame("JList Example");

        // Data untuk JList
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

        // Membuat JList dengan data
        JList<String> list = new JList<>(items);

        // Mengatur mode seleksi menjadi SINGLE_SELECTION
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Menambahkan ListSelectionListener untuk menangani seleksi item
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Mencegah pemicu ganda saat seleksi berubah
                if (!e.getValueIsAdjusting()) {
                    String selectedItem = list.getSelectedValue();
                    System.out.println("Selected: " + selectedItem);
                }
            }
        });

        // Menambahkan JScrollPane agar bisa menggulir jika item terlalu banyak
        JScrollPane scrollPane = new JScrollPane(list);

        // Mengatur layout dan posisi komponen
        frame.setLayout(null);
        scrollPane.setBounds(50, 50, 150, 100);
        frame.add(scrollPane);

        // Konfigurasi JFrame
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Konfigurasi frame
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
    }
}

  
