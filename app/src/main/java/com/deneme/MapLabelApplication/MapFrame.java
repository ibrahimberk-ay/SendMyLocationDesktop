/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.deneme.MapLabelApplication;

import Label.EventWaypoint;
import Label.LabelRender;
import Label.MyLabel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Berk
 */
public class MapFrame extends javax.swing.JFrame {

    //etiketleri tutan set
    private final Set<MyLabel> labels = new HashSet<>();

    //Kullanýcý verilerini tutan kullanýcý seti
    private final List<Users> users = new ArrayList<>();

    //Týklama eventini saðlayan deðiþken
    private EventWaypoint event;

    public MapFrame() {
        //harita ve bileþenlerinin çaðýrýlmasý
        initComponents();
        init();

    }

    //Burada harita bileþenleri ayarlanýyor
    private void init() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);

        //Harita çaðýrýldýðýnda ilk hangi pozisyondan baþlanacaðýný ayarlýyorum
        GeoPosition geo = new GeoPosition(38.963745, 35.243322);//Türkiye
        jXMapViewer.setAddressLocation(geo);
        jXMapViewer.setZoom(13);

        //Fare hareketlerine göre davranýþý ayarlýyorum
        MouseInputListener mm = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mm);
        jXMapViewer.addMouseMotionListener(mm);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));
        event = getEvent();

    }

    //Etikete týklandýðýnda etiket bilgilerini yansýtan event
    private EventWaypoint getEvent() {
        return (MyLabel label) -> {
            JOptionPane.showMessageDialog(MapFrame.this, label.getName());
        };
    }

    //Etiketleri ekrana çizdiriyorum
    private void initLabel() {
        WaypointPainter<MyLabel> lb = new LabelRender();
        lb.setWaypoints(labels);
        jXMapViewer.setOverlayPainter(lb);
        for (MyLabel label : labels) {
            jXMapViewer.add(label.getButton());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXMapViewer = new org.jxmapviewer.JXMapViewer();
        addLabel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addLabel.setText("Update");
        addLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXMapViewerLayout = new javax.swing.GroupLayout(jXMapViewer);
        jXMapViewer.setLayout(jXMapViewerLayout);
        jXMapViewerLayout.setHorizontalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(addLabel)
                .addContainerGap(984, Short.MAX_VALUE))
        );
        jXMapViewerLayout.setVerticalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(addLabel)
                .addContainerGap(751, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLabelActionPerformed
        /*Clear Labels*/
        clearWaypoint();

        /*Add Labels*/
        createUserWithData();
        for (Users user : users) {
            String clickString
                    = "User Id: " + user.getUserId() + "\n"
                    + "User Name: " + user.getUserName() + "\n"
                    + "Latitude: " + user.getLatitude() + "\n"
                    + "Longitude: " + user.getLongitude() + "\n"
                    + "Date & Time: " + user.getDateTime() + "\n"
                    + "Condition: " + user.getCondition();
            labels.add(new MyLabel(clickString, event, new GeoPosition(user.getLatitude(), user.getLongitude()), user.getPriority()));
        }
        //Kullanýcý listesini her seferinde 0'lýyorum ki her çaðýrýldýðýnda ayný kullanýcýdan defalarca çaðýrýlmasýn ve bellek kullanýmý artmasýn
        users.clear();
        initLabel();
    }//GEN-LAST:event_addLabelActionPerformed

    private void clearWaypoint() {
        //Etiket silme fonksiyonu
        for (MyLabel lbl : labels) {
            jXMapViewer.remove(lbl.getButton());
        }
        labels.clear();
        initLabel();
    }

    private void createUserWithData() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            // database'den locationDataSets koleksiyonu çaðýrýyorum
            ApiFuture<QuerySnapshot> query = db.collection("locationDataSets").get();

            // query.get() blocks on response
            QuerySnapshot querySnapshot = query.get();

            /*Çaðýrýlan koleksiyonun dökümantasyonlarýný çaðýrýyorum bunlar bizim tanýmladýðýmýz isim,zaman,konum bilgilerini içeriyor.*/
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            //Bütün dökümanlarý tek tek çaðýrýyorum ve verileri ile yeni kullanýcýlar oluþturuyorum.
            for (QueryDocumentSnapshot document : documents) {
                if (checkDate(document)) {
                    users.add(new Users(
                            document.getId(),
                            document.getString("userName"),
                            document.getDouble("latitude"),
                            document.getDouble("longitude"),
                            document.getString("dateTime"),
                            document.getDouble("priority").intValue(),
                            document.getString("condition"))
                    );
                }
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MapFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Bilgisayarýn mevcut tarihini alýp gelen kullanýcýnýn verisiyle kýyaslayacak olan fonksiyon
    private boolean checkDate(QueryDocumentSnapshot document) {
        //Bilgisayarýn tarih bilsini alýyorum ve formatlýyorum
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = dtf.format(now);
        int index = dateTime.indexOf(" ");
        dateTime = dateTime.substring(0, index);
        //Kullanýcýnýn tarih bilgisini alýp formatlýyorum
        String documentDateTime = document.getString("dateTime").substring(0, index);
        try {  
            //Ýki tarihi de karþýlaþtýrma yapacak fonksiyona göre tekrar formatlýyorum
            Date localDate = new SimpleDateFormat("yyyy/MM/dd").parse(dateTime);
            Date documentDate = new SimpleDateFormat("yyyy/MM/dd").parse(documentDateTime);      
            //iki tarih birbirine denk ise fonksiyon 0 deðeri gönderiyor ve ana fonksiyon çýkýþý true dönüyor.
            //Buna göre eðer konumlar ayný gün gönderilmiþse haritada iþaretlenmesini saðlýyor.
            if(localDate.compareTo(documentDate) == 0){
                return true;
            }
        } catch (ParseException ex) {
            Logger.getLogger(MapFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

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
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MapFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addLabel;
    private org.jxmapviewer.JXMapViewer jXMapViewer;
    // End of variables declaration//GEN-END:variables
}
