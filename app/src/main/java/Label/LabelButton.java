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
    
    public LabelButton(){
        setContentAreaFilled(false);   
        setIcon(new ImageIcon("C:\\Users\\Berk\\Desktop\\MapApplicationExamples\\MapLabelApplication\\app\\src\\main\\java\\com\\deneme\\icon\\pin1.png"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(new Dimension(24,24));
              
    }
    
    
}
