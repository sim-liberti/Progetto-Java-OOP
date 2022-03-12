package GUI;

import Controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HomeDocente implements ActionListener{
    private JFrame frame = new JFrame();
    private JTabbedPane tp = new JTabbedPane();
    private JScrollPane sPane;
    private DefaultTableModel tm;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JButton btnCreate = new JButton("Crea test");
    private JButton btnGrade = new JButton("Correggi test");
    private JButton btnLogout = new JButton("Esci");

    Controller c;
    private String selectedCellValue;

    public HomeDocente(Controller controller){
        c = controller;

        frame.setSize(550, 500);
        frame.setResizable(false);
        frame.setLayout(null);

        // Adding the tables of the tests to the frame //
        // --------------------------------------------------- //
        JPanel tp1 = new JPanel();
        JPanel tp2 = new JPanel();
        JPanel tp3 = new JPanel();
        
        String cols1[] = {"Nome Test", "Data Creazione"};
        tm = new DefaultTableModel(cols1, 0);
        for (int i = 0; i < c.listaTest.size(); i++){
            Object[] rows = {
                c.listaTest.get(i).getNome(),
                c.listaTest.get(i).getDataCreazione()
            };
            tm.addRow(rows);
        }
        table1 = new JTable(tm);
        table1.setEnabled(false);
        sPane = new JScrollPane(table1);
        sPane.setBounds(table1.getBounds());
        tp1.add(sPane);

        String cols2[] = {"Nome Test", "Data Consegna"};
        tm = new DefaultTableModel(cols2, 0);
        for (int i = 0; i < c.listaTest.size(); i++){
            if (c.listaTest.get(i).isTaken == true && c.listaTest.get(i).isGraded == false){
                    Object[] rows = {
                    c.listaTest.get(i).getNome(),
                    c.listaTest.get(i).getDataConsegna()
                };
                tm.addRow(rows);
            }
        }
        table2 = new JTable(tm);
        table2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 1){
                    btnGrade.setEnabled(true);
                    JTable target = (JTable)e.getSource();
                    int selectedRow = target.getSelectedRow();
                    int selectedColumn = target.getSelectedColumn();
                    setSelectedCellValue(String.valueOf(target.getValueAt(selectedRow, selectedColumn)));
                }
            }
        });
        sPane = new JScrollPane(table2);
        sPane.setBounds(table2.getBounds());
        tp2.add(sPane);

        String cols3[] = {"Nome Test", "Data Correzione"};
        tm = new DefaultTableModel(cols3, 0);
        for (int i = 0; i < c.listaTest.size(); i++){
            if (c.listaTest.get(i).isGraded == true){
                    Object[] rows = {
                    c.listaTest.get(i).getNome(),
                    c.listaTest.get(i).getDataCorrezione(),
                    "",
                };
                tm.addRow(rows);
            }
        }
        table3 = new JTable(tm);
        table3.setEnabled(false);
        sPane = new JScrollPane(table3);
        sPane.setBounds(table3.getBounds());
        tp3.add(sPane);

        tp.add("Test creati", tp1);
        tp.add("Test da correggere", tp2);
        tp.add("Test corretti", tp3);
        tp.setBounds(10, 10, 500, 220);
        frame.add(tp);
        // --------------------------------------------------- //

        // Adding the button section //
        // --------------------------------------------------- //
        btnCreate.setBounds(20, 290, 120, 40);
        btnGrade.setBounds(200, 290, 120, 40);
        btnLogout.setBounds(370, 290, 120, 40);
        btnCreate.addActionListener(this);
        btnGrade.addActionListener(this);
        btnLogout.addActionListener(this);
        btnGrade.setEnabled(false);
        frame.add(btnCreate);
        frame.add(btnGrade);
        frame.add(btnLogout);
        // --------------------------------------------------- //

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setSelectedCellValue(String s){ this.selectedCellValue = s; };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreate){
            new CreaTest(c);
            frame.dispose();
        }
        if (e.getSource() == btnGrade){
            if (c.controllaNomeTest(selectedCellValue)){
                new CorreggiTest(c, selectedCellValue);
                frame.dispose();
            }
        }
        if (e.getSource() == btnLogout){
            frame.dispose();
            new Login(c);
        }
    }
}
