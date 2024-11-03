

import javax.swing.*; // Harus di bagian atas

public class HelloWorldSwing {
    private static void createAndShowGUI() {
        
        // Membuat frame dan set judulnya
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menambahkan label ke dalam frame
        JLabel label = new JLabel("Hello Gio");
        frame.getContentPane().add(label);
        
        // Mengatur ukuran komponen sesuai dengan preferensi layout-nya
        frame.pack();
        
        // Membuat frame terlihat di layar
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Menjadwalkan pekerjaan untuk event dispatch thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
