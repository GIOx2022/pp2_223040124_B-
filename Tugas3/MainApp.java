import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

class StudentFormPanel extends JPanel {
    private JTextField npmField;
    private JTextField nameField;
    private JTextArea addressArea;
    private JRadioButton rbMale, rbFemale;
    private JCheckBox cbScholarship;
    private JComboBox<String> majorBox;
    private JList<String> registrationYearList;
    private JSlider scoreSlider;
    private JSpinner ageSpinner;
    private StudentTablePanel studentTablePanel;

    public StudentFormPanel() {
        setLayout(new GridLayout(11, 2));

        npmField = new JTextField(20);
        nameField = new JTextField(20);
        addressArea = new JTextArea(3, 20);
        rbMale = new JRadioButton("Laki-laki");
        rbFemale = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        cbScholarship = new JCheckBox("Beasiswa");
        majorBox = new JComboBox<>(new String[]{
            "Pilih Program Studi", "Teknik Informatika", "Teknik Industri", 
            "Teknik Lingkungan", "Teknik Mesin", "Teknologi Pangan", 
            "Perencanaan Wilayah dan Kota"
        });
        registrationYearList = new JList<>(new String[]{"2021", "2022", "2023", "2024"});

        // Setup slider with labels
        scoreSlider = new JSlider(0, 100, 50);  // Nilai awal diatur pada 50
        scoreSlider.setPaintLabels(true);
        scoreSlider.setPaintTicks(true);
        scoreSlider.setMajorTickSpacing(20);
        scoreSlider.setMinorTickSpacing(5);

        // Menambahkan label angka pada slider
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("0"));
        labelTable.put(20, new JLabel("20"));
        labelTable.put(40, new JLabel("40"));
        labelTable.put(60, new JLabel("60"));
        labelTable.put(80, new JLabel("80"));
        labelTable.put(100, new JLabel("100"));
        scoreSlider.setLabelTable(labelTable);

        ageSpinner = new JSpinner(new SpinnerNumberModel(18, 17, 25, 1));

        JButton addButton = new JButton("Tambahkan Mahasiswa");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String npm = npmField.getText();
                String name = nameField.getText();
                String address = addressArea.getText();
                String gender = rbMale.isSelected() ? "Laki-laki" : "Perempuan";
                boolean isScholarship = cbScholarship.isSelected();
                String major = (String) majorBox.getSelectedItem();
                String registrationYear = registrationYearList.getSelectedValue();
                int score = scoreSlider.getValue();
                int age = (int) ageSpinner.getValue();

                studentTablePanel.addStudent(new Object[]{npm, name, address, gender, isScholarship, major, registrationYear, score, age});
                JOptionPane.showMessageDialog(null, "Data Mahasiswa berhasil ditambahkan!");
            }
        });

        add(new JLabel("NPM:")); add(npmField);
        add(new JLabel("Nama:")); add(nameField);
        add(new JLabel("Alamat:")); add(new JScrollPane(addressArea));
        add(new JLabel("Jenis Kelamin:")); add(rbMale); add(new JLabel("")); add(rbFemale);
        add(new JLabel("Beasiswa:")); add(cbScholarship);
        add(new JLabel("Program Studi:")); add(majorBox);
        add(new JLabel("Tahun Pendaftaran:")); add(new JScrollPane(registrationYearList));
        add(new JLabel("Nilai Tes:")); add(scoreSlider);
        add(new JLabel("Umur:")); add(ageSpinner);
        add(new JLabel("")); add(addButton);
    }

    public void setStudentTablePanel(StudentTablePanel studentTablePanel) {
        this.studentTablePanel = studentTablePanel;
    }
}

class StudentTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public StudentTablePanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{
            "NPM", "Nama", "Alamat", "Jenis Kelamin", "Beasiswa", 
            "Program Studi", "Tahun Pendaftaran", "Nilai Tes", "Umur"
        }, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void addStudent(Object[] studentData) {
        model.addRow(studentData);
    }
}
