package javaapplication1;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends javax.swing.JFrame {

    public MainWindow() 
    {
        initComponents();
        show_Products_In_JTable();
    }
    
    String ImagePath = null;  
    int pos = 0;
    public Connection getConection()
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/products_db_2?autoReconnect=true&useSSL=false","root","root");
           // JOptionPane.showMessageDialog(null,"Connected");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Not Connected");
            return null;
        }
    }
    
    // chcek input fields
    
    public boolean checkInputs()
    {
        if(testField_name.getText() == null ||
                textfield_price.getText() == null ||
                textfield_date.getDate() == null)
        {
            return false;
        }
        else
        {
            try{
                Float.parseFloat(textfield_price.getText());
                return true;
            }
            catch(Exception ex)
                    {
                        return false;
                    }
        }
    }
    
    // resize Image
    public ImageIcon ResizeImage(String ImagePath, byte[] pic)
    {
        ImageIcon myImage= null;
        if(ImagePath != null)
        {
            myImage = new ImageIcon(ImagePath);
        }
        else
        {
            myImage = new ImageIcon(pic);
        }
         Image img = myImage.getImage();
         Image img2 = img.getScaledInstance(imageTestBox.getWidth(), imageTestBox.getHeight(), Image.SCALE_SMOOTH);
         ImageIcon image = new ImageIcon(img2);
         return image;
    }
    public ArrayList<Product> getPrductList()
    {
        
            ArrayList<Product> productList = new ArrayList<>();
            Connection con = getConection();
            String query = "Select * from products";
            
            Statement st;
            ResultSet rs;
            
        try {            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Product product;
            
            while(rs.next())
            {
                product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getBytes("image"));
                productList.add(product);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }
    
    public void show_Products_In_JTable()
    {
        ArrayList<Product> list = getPrductList();
        DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel();
        
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i=0; i< list.size(); i++)
        {
            row[0] = list.get(i).getid();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();
            
            model.addRow(row);
            
        }
    }
    
    public void showItems(int index)
    {
            id_textField.setText(Integer.toString(getPrductList().get(index).getid()));
            testField_name.setText(getPrductList().get(index).getName());
            textfield_price.setText(Float.toString(getPrductList().get(index).getPrice()));
        try {
            Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getPrductList().get(index).getAddDate());
            textfield_date.setDate(addDate);
        } catch (ParseException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageTestBox.setIcon(ResizeImage(null, getPrductList().get(index).getPicture()));
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        testField_name = new javax.swing.JTextField();
        textfield_price = new javax.swing.JTextField();
        id_textField = new javax.swing.JTextField();
        textfield_date = new com.toedter.calendar.JDateChooser();
        imageTestBox = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        button_choose_Image = new javax.swing.JButton();
        button_update = new javax.swing.JButton();
        button_insert = new javax.swing.JButton();
        button_delete = new javax.swing.JButton();
        buttonNext = new javax.swing.JButton();
        buttonLast = new javax.swing.JButton();
        buttonFirst = new javax.swing.JButton();
        buttonPrevious = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton8.setText("First");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Price:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Add Date:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Picture:");

        testField_name.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        testField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testField_nameActionPerformed(evt);
            }
        });

        textfield_price.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        id_textField.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        id_textField.setEnabled(false);

        textfield_date.setDateFormatString("MM-dd-yyyy");
        textfield_date.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        imageTestBox.setBackground(new java.awt.Color(204, 204, 255));
        imageTestBox.setOpaque(true);

        JTable_Products.setBackground(new java.awt.Color(204, 204, 255));
        JTable_Products.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Add Date"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        button_choose_Image.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        button_choose_Image.setText("Choose Image");
        button_choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_choose_ImageActionPerformed(evt);
            }
        });

        button_update.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        button_update.setText("Update");
        button_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_updateActionPerformed(evt);
            }
        });

        button_insert.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        button_insert.setText("Insert");
        button_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_insertActionPerformed(evt);
            }
        });

        button_delete.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        button_delete.setText("Delete");
        button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_deleteActionPerformed(evt);
            }
        });

        buttonNext.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonNext.setText("Next");
        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });

        buttonLast.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonLast.setText("Last");
        buttonLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLastActionPerformed(evt);
            }
        });

        buttonFirst.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonFirst.setText("First");
        buttonFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFirstActionPerformed(evt);
            }
        });

        buttonPrevious.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonPrevious.setText("Previous");
        buttonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPreviousActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button_insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_update, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(buttonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLast, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(117, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(testField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textfield_price, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textfield_date, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imageTestBox, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(button_choose_Image))
                        .addGap(86, 86, 86)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(testField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textfield_price, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textfield_date, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageTestBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_choose_Image)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_update)
                    .addComponent(button_insert)
                    .addComponent(button_delete)
                    .addComponent(buttonFirst)
                    .addComponent(buttonNext)
                    .addComponent(buttonPrevious)
                    .addComponent(buttonLast))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void testField_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testField_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_testField_nameActionPerformed

    private void button_choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_choose_ImageActionPerformed
    
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            imageTestBox.setIcon(ResizeImage(path, null));
            ImagePath = path;
        }
        else
        {
            System.out.println("No File selected");
        }
    }//GEN-LAST:event_button_choose_ImageActionPerformed

    private void button_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_insertActionPerformed
    
        if(checkInputs() && ImagePath!= null)
        {
            try {
                Connection con = getConection();
                PreparedStatement ps = con.prepareStatement("Insert into products(name,price,add_date,image)"
                        + "values(?,?,?,?)");
                ps.setString(1, testField_name.getText());
                ps.setString(2, textfield_price.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(textfield_date.getDate());
                ps.setString(3, addDate);
                
                InputStream img = new FileInputStream(new File(ImagePath));
                ps.setBlob(4, img);
                ps.executeUpdate();
                show_Products_In_JTable();
                
                JOptionPane.showMessageDialog(null, "Data Inserted");
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "One or more Fields are Empty");
        }
          
    }//GEN-LAST:event_button_insertActionPerformed

    private void button_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_updateActionPerformed
    
        if(checkInputs() && id_textField.getText()!= null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConection();
            
            //update without image
            if(ImagePath == null)
            {
                try {
                    UpdateQuery = "UPDATE products set name = ?, price = ?"
                            + ", add_date = ? WHERE id = ?";
                    
                    ps = con.prepareCall(UpdateQuery);
                    ps.setString(1, testField_name.getText());
                    ps.setString(2, textfield_price.getText());
                    
                    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dataFormat.format(textfield_date.getDate());
                    
                    ps.setString(3, addDate);
                    ps.setInt(4, Integer.parseInt(id_textField.getText()));
                    ps.executeUpdate();
                    show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Updated successfully");

                    
                } catch (SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            //update with image
            else
            {
                try {
                    InputStream img = new FileInputStream(new File(ImagePath));
                    UpdateQuery = "UPDATE products set name = ?, price = ?"
                            + ", add_date = ?, image = ? WHERE id = ?";
                    
                    ps = con.prepareCall(UpdateQuery);
                    ps.setString(1, testField_name.getName());
                    ps.setString(2, textfield_price.getText());
                    
                    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dataFormat.format(textfield_date.getDate());
                    
                    ps.setString(3, addDate);
                    ps.setBlob(4, img);
                    ps.setInt(5, Integer.parseInt(id_textField.getText()));
                    ps.executeUpdate();
                    show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Updated successfully");
                } 
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, "One or more Fields are Empty or Wrong");
                }  
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "");
        }
    }//GEN-LAST:event_button_updateActionPerformed

    private void button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deleteActionPerformed
        if(!id_textField.getText().equals(""))
        {
            try {
                Connection con = getConection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM PRODUCTS WHERE "
                        + "id=?"); 
                int id = Integer .parseInt(id_textField.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                show_Products_In_JTable();
                JOptionPane.showMessageDialog(null, "Product Deleted");
                        } catch (SQLException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Product not Deleted");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Product not Deleted: No id to Delete");
        }
    }//GEN-LAST:event_button_deleteActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
       
        int index = JTable_Products.getSelectedRow();
        showItems(index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void buttonFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFirstActionPerformed
        
        pos = 0;
        showItems(pos);
    }//GEN-LAST:event_buttonFirstActionPerformed

    private void buttonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPreviousActionPerformed
       pos--;
       if(pos < 0)
       {
           pos = 0;
       }
       showItems(pos);
    }//GEN-LAST:event_buttonPreviousActionPerformed

    private void buttonLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLastActionPerformed

        pos = getPrductList().size()-1;
        showItems(pos);
    }//GEN-LAST:event_buttonLastActionPerformed

    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed
           pos++;
           if(pos >= getPrductList().size())
           {
               pos = getPrductList().size() - 1;
           }
           showItems(pos);
        
    }//GEN-LAST:event_buttonNextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Products;
    private javax.swing.JButton buttonFirst;
    private javax.swing.JButton buttonLast;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonPrevious;
    private javax.swing.JButton button_choose_Image;
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_insert;
    private javax.swing.JButton button_update;
    private javax.swing.JTextField id_textField;
    private javax.swing.JLabel imageTestBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField testField_name;
    private com.toedter.calendar.JDateChooser textfield_date;
    private javax.swing.JTextField textfield_price;
    // End of variables declaration//GEN-END:variables
}
