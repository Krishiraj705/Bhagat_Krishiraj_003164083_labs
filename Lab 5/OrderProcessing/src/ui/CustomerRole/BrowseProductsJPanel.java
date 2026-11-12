/*
 * BrowseProducts.java
 *
 * Created on October 10, 2008, 9:10 AM
 */
package ui.CustomerRole;

import model.MasterOrderList;
import model.Order;
import model.OrderItem;
import model.Product;
import model.Supplier;
import model.SupplierDirectory;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Rushabh
 * Customer shopping logic added by Krishiraj Bhagat.
 */
public class BrowseProductsJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private SupplierDirectory supplierDirectory;
    private MasterOrderList masterOrderList;
    private Order currentOrder; // the customer's cart

    /** Creates new form BrowseProducts */
    public BrowseProductsJPanel(JPanel userProcessContainer, SupplierDirectory supplierDirectory, MasterOrderList masterOrderList) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.supplierDirectory = supplierDirectory;
        this.masterOrderList = masterOrderList;
        currentOrder = new Order();

        // The generated code has no listener for "Add to Cart", so we attach one here
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCart();
            }
        });

        populateSupplierCombo();
        populateProductTable();
        populateCartTable();
    }

    /** Puts every supplier into the supplier drop-down. */
    private void populateSupplierCombo() {
        cmbSupplier.removeAllItems();
        for (Supplier supplier : supplierDirectory.getSupplierlist()) {
            cmbSupplier.addItem(supplier);
        }
    }

    /** Shows all products of the selected supplier. */
    private void populateProductTable() {
        DefaultTableModel model = (DefaultTableModel) tblProductCatalog.getModel();
        model.setRowCount(0);
        Supplier supplier = (Supplier) cmbSupplier.getSelectedItem();
        if (supplier == null) {
            return;
        }
        for (Product product : supplier.getProductCatalog().getProductcatalog()) {
            addProductRow(model, product);
        }
    }

    private void addProductRow(DefaultTableModel model, Product product) {
        Object[] row = new Object[4];
        row[0] = product;
        row[1] = product.getModelNumber();
        row[2] = product.getPrice();
        row[3] = product.getAvailability();
        model.addRow(row);
    }

    /** Shows every item that is currently in the cart. */
    private void populateCartTable() {
        DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
        model.setRowCount(0);
        for (OrderItem item : currentOrder.getOrderItemList()) {
            Object[] row = new Object[4];
            row[0] = item;
            row[1] = item.getSalesPrice();
            row[2] = item.getQuantity();
            row[3] = item.getTotal();
            model.addRow(row);
        }
    }

    /** Returns the product selected in the catalog table, or null. */
    private Product getSelectedProduct() {
        int row = tblProductCatalog.getSelectedRow();
        if (row < 0) {
            return null;
        }
        return (Product) tblProductCatalog.getValueAt(row, 0);
    }

    /** Returns the item selected in the cart table, or null. */
    private OrderItem getSelectedOrderItem() {
        int row = tblCart.getSelectedRow();
        if (row < 0) {
            return null;
        }
        return (OrderItem) tblCart.getValueAt(row, 0);
    }

    private void warn(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void info(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Adds the selected product to the cart after checking price, quantity and stock. */
    private void addToCart() {
        Product product = getSelectedProduct();
        if (product == null) {
            warn("Please select a product from the catalog.");
            return;
        }

        String priceText = txtSalesPrice.getText().trim();
        if (priceText.isEmpty()) {
            warn("Please enter a sales price.");
            return;
        }
        double salesPrice;
        try {
            salesPrice = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            warn("Sales price must be a number.");
            return;
        }
        if (salesPrice < product.getPrice()) {
            warn("Sales price cannot be lower than the product price ($" + product.getPrice() + ").");
            return;
        }

        try {
            spnQuantity.commitEdit(); // use the number typed in the spinner
        } catch (java.text.ParseException e) {
            warn("Quantity must be a whole number.");
            return;
        }
        int quantity = (Integer) spnQuantity.getValue();
        if (quantity <= 0) {
            warn("Quantity must be at least 1.");
            return;
        }
        if (quantity > product.getAvailability()) {
            warn("Only " + product.getAvailability() + " of " + product.getProdName() + " left in stock.");
            return;
        }

        OrderItem existing = currentOrder.findOrderItem(product);
        if (existing != null) {
            // Same product again: increase the quantity and use the new price
            existing.setQuantity(existing.getQuantity() + quantity);
            existing.setSalesPrice(salesPrice);
        } else {
            currentOrder.addNewOrderItem(product, salesPrice, quantity);
        }
        product.setAvailability(product.getAvailability() - quantity);

        populateProductTable();
        populateCartTable();
        txtSalesPrice.setText("");
        spnQuantity.setValue(0);
        info(quantity + " x " + product.getProdName() + " added to cart.");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lblSupplier = new javax.swing.JLabel();
        cmbSupplier = new javax.swing.JComboBox();
        txtSearch = new javax.swing.JTextField();
        btnSearchProduct = new javax.swing.JButton();
        lblProductCatalogue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductCatalog = new javax.swing.JTable();
        lblSalesPrice = new javax.swing.JLabel();
        txtSalesPrice = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        spnQuantity = new javax.swing.JSpinner();
        btnAddToCart = new javax.swing.JButton();
        btnProductDetails = new javax.swing.JButton();
        lblItemsInCart = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        txtNewQuantity = new javax.swing.JTextField();
        btnModifyQuantity = new javax.swing.JButton();
        btnViewOrderItem = new javax.swing.JButton();
        btnRemoveOrderItem = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 240, 240));
        setPreferredSize(new java.awt.Dimension(650, 600));

        lblTitle.setBackground(new java.awt.Color(0, 0, 0));
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(204, 0, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/logoPleaseBuySmall.png"))); // NOI18N
        lblTitle.setText("Have a wonderful shopping experience!");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblTitle.setIconTextGap(20);

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblSupplier.setText("Supplier:");

        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });

        btnSearchProduct.setText("Search Product");
        btnSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchProductActionPerformed(evt);
            }
        });

        lblProductCatalogue.setText("Product Catalog:");

        tblProductCatalog.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        tblProductCatalog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Product Id", "Price", "Avail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductCatalog);

        lblSalesPrice.setText("Sales Price:");

        lblQuantity.setText("Quantity:");

        spnQuantity.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        btnAddToCart.setText("Add to Cart");

        btnProductDetails.setText("View Product Details");
        btnProductDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductDetailsActionPerformed(evt);
            }
        });

        lblItemsInCart.setText("Items in cart:");

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item Name", "Price", "Quantity", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCart);

        btnModifyQuantity.setText("Modify Quantity");
        btnModifyQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyQuantityActionPerformed(evt);
            }
        });

        btnViewOrderItem.setText("View Item");
        btnViewOrderItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewOrderItemActionPerformed(evt);
            }
        });

        btnRemoveOrderItem.setText("Remove");
        btnRemoveOrderItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveOrderItemActionPerformed(evt);
            }
        });

        btnCheckOut.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnCheckOut.setText("Check out");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblProductCatalogue)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnSearchProduct))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblSalesPrice)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSalesPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblQuantity)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnAddToCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnProductDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                        .addComponent(lblItemsInCart)
                        .addComponent(jScrollPane2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnModifyQuantity)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNewQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnViewOrderItem)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnRemoveOrderItem)))
                    .addComponent(btnCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(lblTitle)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spnQuantity, txtSalesPrice});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(btnBack))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProductCatalogue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProductDetails)
                    .addComponent(lblSalesPrice)
                    .addComponent(txtSalesPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantity)
                    .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddToCart))
                .addGap(18, 18, 18)
                .addComponent(lblItemsInCart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewOrderItem)
                    .addComponent(btnRemoveOrderItem)
                    .addComponent(btnModifyQuantity)
                    .addComponent(txtNewQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

    }// </editor-fold>//GEN-END:initComponents

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        // Show the products of the newly selected supplier
        populateProductTable();
    }//GEN-LAST:event_cmbSupplierActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // If the cart has items, put them back in stock before leaving
        if (!currentOrder.getOrderItemList().isEmpty()) {
            int answer = JOptionPane.showConfirmDialog(this, "Leaving this page will empty your cart. Continue?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
                return;
            }
            for (OrderItem item : currentOrder.getOrderItemList()) {
                Product product = item.getProduct();
                product.setAvailability(product.getAvailability() + item.getQuantity());
            }
        }
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnProductDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductDetailsActionPerformed
        Product product = getSelectedProduct();
        if (product == null) {
            warn("Please select a product from the catalog.");
            return;
        }
        ViewProductDetailJPanel vpdjp = new ViewProductDetailJPanel(userProcessContainer, product);
        userProcessContainer.add("ViewProductDetailJPanel", vpdjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnProductDetailsActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnModifyQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyQuantityActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnModifyQuantityActionPerformed

    private void btnSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchProductActionPerformed
        Supplier supplier = (Supplier) cmbSupplier.getSelectedItem();
        if (supplier == null) {
            warn("Please select a supplier first.");
            return;
        }
        String text = txtSearch.getText().trim();
        if (text.isEmpty()) {
            populateProductTable(); // empty search shows all products again
            return;
        }
        int id;
        try {
            id = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            warn("Please enter a product ID (a whole number).");
            return;
        }
        Product product = supplier.getProductCatalog().searchProduct(id);
        if (product == null) {
            info("No product with ID " + id + " for supplier " + supplier.getSupplyName() + ".");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblProductCatalog.getModel();
        model.setRowCount(0);
        addProductRow(model, product);
        tblProductCatalog.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnSearchProductActionPerformed

    private void btnRemoveOrderItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveOrderItemActionPerformed
       
    }//GEN-LAST:event_btnRemoveOrderItemActionPerformed

    private void btnViewOrderItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOrderItemActionPerformed
        
    }//GEN-LAST:event_btnViewOrderItemActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnModifyQuantity;
    private javax.swing.JButton btnProductDetails;
    private javax.swing.JButton btnRemoveOrderItem;
    private javax.swing.JButton btnSearchProduct;
    private javax.swing.JButton btnViewOrderItem;
    private javax.swing.JComboBox cmbSupplier;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblItemsInCart;
    private javax.swing.JLabel lblProductCatalogue;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblSalesPrice;
    private javax.swing.JLabel lblSupplier;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblProductCatalog;
    private javax.swing.JTextField txtNewQuantity;
    private javax.swing.JTextField txtSalesPrice;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
