/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Label;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Berk
 */
//Etiket özelliklerinin tanýmlandýðý sýnýf
public class LabelButton extends JButton{
    
    
    public LabelButton(int priority){
        setContentAreaFilled(false);   
        if(priority == 0){
            setIcon(new ImageIcon("C:\\Users\\Berk\\Desktop\\MapApplicationExamples\\MapLabelApplication\\app\\src\\main\\java\\com\\deneme\\icon\\pin0.png"));
        }
        if(priority == 1){
            setIcon(new ImageIcon("C:\\Users\\Berk\\Desktop\\MapApplicationExamples\\MapLabelApplication\\app\\src\\main\\java\\com\\deneme\\icon\\pin1.png"));
        }
        if(priority == 2){
            setIcon(new ImageIcon("C:\\Users\\Berk\\Desktop\\MapApplicationExamples\\MapLabelApplication\\app\\src\\main\\java\\com\\deneme\\icon\\pin2.png"));
        }
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(new Dimension(24,24));
              
    }
    
    
}
