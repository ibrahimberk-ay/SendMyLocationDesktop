/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.deneme.MapLabelApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        //Servis dosyasını referans edicek değişkeni tanımlıyorum.
        FileInputStream serviceAccount
                = null;
        
        try {
            //Buradaki serviceAccount dosyası Firebase tarafından sağlanıyor.
            serviceAccount = new FileInputStream("C:/Users/Berk/Desktop/MapApplicationExamples/MapLabelApplication/serviceAccount.json");
            //Firebase yönlendirmesi ile firebasede ilgili uygulama çağırılıyor
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException ex) {
            //Servis dosyasına ulaşılmaması durumunda hata döndürmesi için catch oluşturuyorum
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //dosyayı kapatıyorum
                serviceAccount.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //LoginGui penceresini ana fonksiyondan çağırıyorum, aksi halde gui aracılığı ile database erişimi sağlanamaz.
        new LoginGui().setVisible(true);
    }
}
