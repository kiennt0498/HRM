/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.app.hrm.UI;

import com.app.hrm.DAO.ChamCongDao;
import com.app.hrm.DAO.KyCongDao;
import com.app.hrm.DAO.PhongBanDao;
import com.app.hrm.Entity.ChamCong;
import com.app.hrm.Entity.KyCong;
import com.app.hrm.Entity.NhanVien;
import com.app.hrm.Entity.PhongBan;
import com.app.hrm.Utils.Auth;
import com.app.hrm.Utils.MsgBox;
import com.app.hrm.Utils.XDate;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class BangCong extends javax.swing.JPanel {

    KyCongDao dao = new KyCongDao();
    PhongBanDao pbdao = new PhongBanDao();
    Mainform main;
    public static int thang;
    public static int nam;
    public static int idkc;
    boolean check = true;

    /**
     * Creates new form BangCong
     */
    public BangCong() {
        initComponents();
        CheckNut(true);

        fillNam();
        btnXoa.setEnabled(Auth.isBoss());
    }

    void CheckNut(boolean check) {
        btnThem.setEnabled(check);

        btnXoa.setEnabled(check);

    }

    void filltable() {
        DefaultTableModel tblMode = (DefaultTableModel) tblChamCong.getModel();
        tblMode.setRowCount(0);

        try {
            List<KyCong> list = dao.selectAll();
            for (KyCong kc : list) {
                Object[] row = {kc.getMaKC(), kc.getNgayTinh(), kc.getThang(), kc.getNam()};
                tblMode.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void filltableTim() {
        DefaultTableModel tblMode = (DefaultTableModel) tblChamCong.getModel();
        tblMode.setRowCount(0);
        try {
            Object n = cboNam.getSelectedItem();
            Object t = cboThang.getSelectedItem();
            if (n != null && t != null) {
                int nam = (int) n;
                int thang = (int) t;
                List<KyCong> list = (List<KyCong>) dao.selectByThangNam(nam, thang);
                for (KyCong kc : list) {
                    Object[] row = {kc.getMaKC(),XDate.toString( kc.getNgayTinh()), kc.getThang(), kc.getNam()};
                    tblMode.addRow(row);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fillNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<Object> list = dao.selectNam();
        for (Object ob : list) {
            model.addElement(ob);
        }

        cboNam.setSelectedIndex(0);
        fillThang();
    }

    void fillThang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboThang.getModel();
        model.removeAllElements();
        try {
            Object nam = cboNam.getSelectedItem();

            if (nam != null) {
                List<Object> list = dao.selectThang(nam);
                for (Object i : list) {

                    model.addElement(i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void Them() {
        LocalDate day = LocalDate.now();
        List<KyCong> list = dao.selectAll();
        int check = 0;
        for (KyCong kc : list) {
            if (kc.getNam() == day.getYear() && kc.getThang() == day.getMonthValue()) {
                check++;
            }
        }
        if (check == 0) {
            KyCong kc = new KyCong();

            kc.setNam(day.getYear());
            kc.setThang(day.getMonthValue());
            Date ngay = new Date(day.getYear() - 1901, day.getMonthValue() + 11, day.getMonth().length(true));
            kc.setNgayTinh(ngay);
            try {
                dao.insert(kc);
                this.filltable();

                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            MsgBox.alert(this   , "Chấm công đã tồn tại");
        }

    }

    

    public void Delete() {
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa")) {
            try {
                for (int row : tblChamCong.getSelectedRows()) {
                    int ma = (int) tblChamCong.getValueAt(row, 0);
                    dao.delete(ma);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            filltable();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCT = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pnlChamCong = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChamCong = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        btnTim = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));

        jLabel1.setBackground(new java.awt.Color(51, 153, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bảng chấm công");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/hrm/icon/Add-icon2.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/hrm/icon/delete-file-icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnCT.setText("Xem chi tiết");
        btnCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnCT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnThem)
                .addComponent(btnXoa)
                .addComponent(btnCT))
        );

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        jLabel2.setText("Năm");

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        tblChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã chấm công", "Ngày tính công", "Tháng", "Năm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblChamCong);

        jLabel6.setText("Tháng");

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTim)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlChamCongLayout = new javax.swing.GroupLayout(pnlChamCong);
        pnlChamCong.setLayout(pnlChamCongLayout);
        pnlChamCongLayout.setHorizontalGroup(
            pnlChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChamCongLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlChamCongLayout.setVerticalGroup(
            pnlChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChamCongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.add(pnlChamCong, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        fillThang();
    }//GEN-LAST:event_cboNamActionPerformed

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
        filltable();
    }//GEN-LAST:event_cboThangActionPerformed

    private void btnCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTActionPerformed

        int index = tblChamCong.getSelectedRow();
        if (index < 0) {
            MsgBox.alert(this, "Chọn bảng công để xem chi tiết");
        } else {
            int maID = (int) tblChamCong.getValueAt(index, 0);
            KyCong kc = dao.selectById(maID);
            thang = kc.getThang();
            nam = kc.getNam();
            idkc = maID;
            new BangChamCongJDialog(main, true).setVisible(true);
        }
    }//GEN-LAST:event_btnCTActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        filltableTim();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Delete();
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCT;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlChamCong;
    private javax.swing.JTable tblChamCong;
    // End of variables declaration//GEN-END:variables
}
