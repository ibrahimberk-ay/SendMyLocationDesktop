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

    //Kullan�c� verilerini tutan kullan�c� seti
    private final List<Users> users = new ArrayList<>();

    //T�klama eventini sa�layan de�i�ken
    private EventWaypoint event;

    public MapFrame() {
        //harita ve bile�enlerinin �a��r�lmas�
        initComponents();
        init();

    }

    //Burada harita bile�enleri ayarlan�yor
    private void init() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);

        //Harita �a��r�ld���nda ilk hangi pozisyondan ba�lanaca��n� ayarl�yorum
        GeoPosition geo = new GeoPosition(38.963745, 35.243322);//T�rkiye
        jXMapViewer.setAddressLocation(geo);
        jXMapViewer.setZoom(13);

        //Fare hareketlerine g�re davran��� ayarl�yorum
        MouseInputListener mm = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mm);
        jXMapViewer.addMouseMotionListener(mm);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));
        event = getEvent();

    }

    //Etikete t�kland���nda etiket bilgilerini yans�tan event
    private EventWaypoint getEvent() {
        return (MyLabel label) -> {
            JOptionPane.showMessageDialog(MapFrame.this, label.getName());
        };
    }

    //Etiketleri ekrana �izdiriyorum
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
        //Kullan�c� listesini her seferinde 0'l�yorum ki her �a��r�ld���nda ayn� kullan�c�dan defalarca �a��r�lmas�n ve bellek kullan�m� artmas�n
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
            // database'den locationDataSets koleksiyonu �a��r�yorum
            ApiFuture<QuerySnapshot> query = db.collection("locationDataSets").get();

            // query.get() blocks on response
            QuerySnapshot querySnapshot = query.get();

            /*�a��r�lan koleksiyonun d�k�mantasyonlar�n� �a��r�yorum bunlar bizim tan�mlad���m�z isim,zaman,konum bilgilerini i�eriyor.*/
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            //B�t�n d�k�manlar� tek tek �a��r�yorum ve verileri ile yeni kullan�c�lar olu�turuyorum.
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

    //Bilgisayar�n mevcut tarihini al�p gelen kullan�c�n�n verisiyle k�yaslayacak olan fonksiyon
    private boolean checkDate(QueryDocumentSnapshot document) {
        //Bilgisayar�n tarih bilsini al�yorum ve formatl�yorum
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = dtf.format(now);
        int index = dateTime.indexOf(" ");
        dateTime = dateTime.substring(0, index);
        //Kullan�c�n�n tarih bilgisini al�p formatl�yorum
        String documentDateTime = document.getString("dateTime").substring(0, index);
        try {  
            //�ki tarihi de kar��la�t�rma yapacak fonksiyona g�re tekrar formatl�yorum
            Date localDate = new SimpleDateFormat("yyyy/MM/dd").parse(dateTime);
            Date documentDate = new SimpleDateFormat("yyyy/MM/dd").parse(documentDateTime);      
            //iki tarih birbirine denk ise fonksiyon 0 de�eri g�nderiyor ve ana fonksiyon ��k��� true d�n�yor.
            //Buna g�re e�er konumlar ayn� g�n g�nderilmi�se haritada i�aretlenmesini sa�l�yor.
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
