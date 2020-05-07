
package com.ds.rmi.client;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class SensorIndicator implements TableCellRenderer{
    
    private static final TableCellRenderer render = new DefaultTableCellRenderer();
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //To change body of generated methods, choose Tools | Templates.
        
        Component c = render.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
//        System.out.println(row);
//        System.out.println(column);

        if(column == 6){
            Object result = table.getModel().getValueAt(row, column);
            String status = result.toString();
            
//            System.out.println(status);
            
            if(status.equals("NORMAL")){
                c.setBackground(Color.GREEN);
                c.setForeground(Color.black);
                
            }else if(status.equals("CRITICAL")){
                c.setBackground(Color.red);
                c.setForeground(Color.yellow);
            }  
            
        }else{
            c.setBackground(Color.white); 
            c.setForeground(Color.black);

        }
     
        return c;
 
    }
    
    
    
}
