/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Label;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 *
 * @author Berk
 */
//DefaultWaypoint sýnýfýndan kalýtýlan etiket sýnýfý
public class MyLabel extends DefaultWaypoint{
    
    //Etiketler buton olarak çaðýrýlýyor ki üstlerine týklandýðýnda etiket bilgilerini okuyabilelim
    
    //Etiket ismi
    private String name;
    
    //Etiketin çaðýracaðý buton
    private JButton button;
    
    //Constructor with no parameters
    public MyLabel(){
        
    }
    //Constructor with parameters
    public MyLabel(String name, EventWaypoint event, GeoPosition coord) {
        super(coord);
        this.name = name;
        initButton(event);
    }
    
    private void initButton(EventWaypoint event){
        button = new LabelButton();
        button.addActionListener((ActionEvent e) -> {
            event.selected(MyLabel.this);
            System.out.println(name);
        });
    }
    
    //Getter ve Setter'lar
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
    
    
    
    
    
}
