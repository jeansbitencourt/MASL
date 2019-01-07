/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Jean Bitencourt
 */
public class IconifiedRenderer extends JLabel implements TableCellRenderer {

    public IconifiedRenderer() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = this;
        int cellValueA = -1;
        int cellValueB = -1;
        try {
            label.setText(String.valueOf(value));
            try {
                cellValueA = Integer.parseInt(String.valueOf(table.getValueAt(row, 7))); //0th for A
            } catch (ArrayIndexOutOfBoundsException aa) {
                //ignore
                cellValueA = -1;
            }
            try {
                cellValueB = Integer.parseInt(String.valueOf(table.getValueAt(row, 8))); //0th for A
            } catch (ArrayIndexOutOfBoundsException aa) {
                //ignore
                cellValueB = -1;
            }

            if (isSelected) {
                setOpaque(true);
                label.setBackground(Color.BLACK);
            } else {
                if (cellValueA != -1 && cellValueB != -1) {
                    if ((cellValueA == cellValueB) && (cellValueA != 0 && cellValueB != 0)) {
                        setOpaque(true);
                        label.setBackground(Color.DARK_GRAY);
                    } else {
                        setOpaque(true);
                        label.setBackground(Color.lightGray);
                    }
                } else {
                    setOpaque(true);
                    label.setBackground(Color.lightGray);
                }
            }

        } catch (Exception ex) {
            // no need to handle
        }
        return label;
    }
}
