/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rjferramentas.leitornfexml;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Will
 */
public class Main extends javax.swing.JFrame {

    DefaultTableModel mdlTable = new DefaultTableModel();
    File file = new File("");

    public Main() {
        initComponents();
        //this.setIconImage("//img//calendar.png");
        mdlTable.addColumn("Fatura número");
        mdlTable.addColumn("Data vencimento");
        mdlTable.addColumn("Valor parcela");
        tblDup.setModel(mdlTable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnXML = new javax.swing.JButton();
        txtEmissor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDup = new javax.swing.JTable();
        lblEmissao = new javax.swing.JLabel();
        pbLoading = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Leitor NFe Entrada");

        btnXML.setIcon(new javax.swing.ImageIcon("C:\\Users\\Will\\Documents\\Development\\Java\\LeitorNFeXML\\LeitorNFeXML\\src\\main\\resources\\img\\search_file.png")); // NOI18N
        btnXML.setText("Arquivo XML");
        btnXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXMLActionPerformed(evt);
            }
        });

        jLabel1.setText("Emissor:");

        tblDup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDup);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnXML)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pbLoading, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(lblEmissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(btnXML))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXMLActionPerformed
        JFileChooser fileChooser = new JFileChooser("C:\\");
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Arquivos XML", "xml");
        fileChooser.setFileFilter(fileFilter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            System.out.println("file: \n" + file.getAbsolutePath());
            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = builder.parse(file);

                String emissor = getName(document);
                if (emissor != null) {
                    txtEmissor.setText(emissor);
                } else {
                    JOptionPane.showMessageDialog(this, "Tag <xNome> não encontrada em <emit>.");
                }
                String date = getDate(document);
                lblEmissao.setText("Data de Emissão:  " + date + " (GMT-03:00)");
                mdlTable.setRowCount(0);
                pbIncrease();
                // Add dup tags to table
                addDupToTable(document, mdlTable);
                // model.addRow(new Object[]{selectedFile.getName(), "XML"});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar XML: " + ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnXMLActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*Invoke look and feel*/
        FlatDarculaLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXML;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmissao;
    private javax.swing.JProgressBar pbLoading;
    private javax.swing.JTable tblDup;
    private javax.swing.JTextField txtEmissor;
    // End of variables declaration//GEN-END:variables

    private String getName(Document document) {
        NodeList nodeParent = document.getElementsByTagName("emit");
        if (nodeParent.getLength() > 0) {
            Node nodeChild = nodeParent.item(0);
            NodeList nodeNames = nodeChild.getChildNodes();
            for (int i = 0; i < nodeNames.getLength(); i++) {
                Node subNode = nodeNames.item(i);
                if (subNode.getNodeType() == Node.ELEMENT_NODE && subNode.getNodeName().equalsIgnoreCase("xNome")) {
                    return subNode.getTextContent();
                }
            }
        }
        return null;
    }

    private String getDate(Document document) {
        NodeList nodeParent = document.getElementsByTagName("ide");
        if (nodeParent.getLength() > 0) {
            Node nodeChild = nodeParent.item(0);
            NodeList nodeNames = nodeChild.getChildNodes();
            for (int i = 0; i < nodeNames.getLength(); i++) {
                Node subNode = nodeNames.item(i);
                if (subNode.getNodeType() == Node.ELEMENT_NODE && subNode.getNodeName().equalsIgnoreCase("dhEmi")) {
                    String dhEmi = subNode.getTextContent();
                    try {
                        OffsetDateTime dateTime = OffsetDateTime.parse(dhEmi);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm ");
                        return dateTime.format(formatter);
                    } catch (DateTimeParseException e) {
                        return dhEmi; // Fallback to raw value if parsing fails
                    }
                }
            }
        }
        return null;
    }

    private void addDupToTable(Document document, DefaultTableModel tableModel) {
        // Date formatter for dVenc (YYYY-MM-DD to DD/MM/YYYY)
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Currency formatter for vDup (Brazilian Reais)
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        NodeList dupNodes = document.getElementsByTagName("dup");
        for (int i = 0; i < dupNodes.getLength(); i++) {
            Node dupNode = dupNodes.item(i);
            if (dupNode.getNodeType() == Node.ELEMENT_NODE) {
                NodeList childNodes = dupNode.getChildNodes();
                String nDup = "";
                String dVenc = "";
                String vDup = "";

                // Extract values from child nodes
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node child = childNodes.item(j);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        String nodeName = child.getNodeName();
                        String nodeValue = child.getTextContent();
                        if (nodeName.equals("nDup")) {
                            nDup = nodeValue;
                        } else if (nodeName.equals("dVenc")) {
                            try {
                                Date date = inputDateFormat.parse(nodeValue);
                                dVenc = outputDateFormat.format(date);
                            } catch (Exception e) {
                                dVenc = nodeValue; // Fallback to raw value if parsing fails
                            }
                        } else if (nodeName.equals("vDup")) {
                            try {
                                double value = Double.parseDouble(nodeValue);
                                vDup = currencyFormat.format(value);
                            } catch (NumberFormatException e) {
                                vDup = nodeValue; // Fallback to raw value if parsing fails
                            }
                        }
                    }
                }

                // Add row to table model
                tableModel.addRow(new Object[]{nDup, dVenc, vDup});
            }
        }
    }
    private void pbIncrease() {
    final int[] percentage = {0}; // Array to allow modification in lambda
    Timer timer = new Timer(10, e -> {
        if (percentage[0] <= 100) {
            pbLoading.setValue(percentage[0]);
            System.out.println(percentage[0]);
            percentage[0]++;
        } else {
            ((Timer) e.getSource()).stop(); // Stop the timer
        }
    });
    timer.start();
}
    
}
