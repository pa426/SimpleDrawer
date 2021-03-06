/*
 * DrawerMain.java
 *
 * @author Gill Windall
 *
 * A very simple drawing program.
 *
 * The UI for the class has been created using NetBeans GUI Builder.  If you
 * wish to edit the parts generated by the form editor you have two choices:
 * 1) Use a text editor to remove the "editor" comments that NetBeans uses
 * to identify the parts that should not be edited.  This is a one way trip. 
 * Once you have done this you won't be able to use the NetBeans GUI editor 
 * any more for this class
 * 2) Work out how to use the "Code" feature on the property editor in NetBeans
 * to customise the generated code.  You can do most things by use of properties 
 * such as "Pre-Init Code" and "Post-Init Code"
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DrawerMain extends javax.swing.JFrame {

    private final DrawingPanel drawingPanel; // the drawing panel
    private final int[] colour = new int[3];
    private static int decSelection;
    private static int thicknessInt;

    /**
     * Initialise the components in the screen and then cast a reference to the
     * panel that will be drawn on so it can be used elsewhere.
     */
    public DrawerMain() {
        initComponents();
        drawingPanel = (DrawingPanel) panDrawingArea;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpShape = new javax.swing.ButtonGroup();
        grpDecorator = new javax.swing.ButtonGroup();
        panControls = new javax.swing.JPanel();
        panBrightness = new javax.swing.JPanel();
        coloursBean = new coloursbean.ColoursBean();
        jLabel3 = new javax.swing.JLabel();
        scrBrightness = new javax.swing.JScrollBar();
        panShape = new javax.swing.JPanel();
        radLine = new javax.swing.JRadioButton();
        radOval = new javax.swing.JRadioButton();
        radTriangle = new javax.swing.JRadioButton();
        radSquare = new javax.swing.JRadioButton();
        panShape1 = new javax.swing.JPanel();
        radNormal = new javax.swing.JRadioButton();
        radDotted = new javax.swing.JRadioButton();
        radFilled = new javax.swing.JRadioButton();
        backgroundBtn1 = new javax.swing.JButton();
        backgroundBtn = new javax.swing.JButton();
        btnLeft1 = new javax.swing.JButton();
        panMoreControls = new javax.swing.JPanel();
        btnLoadXML = new javax.swing.JButton();
        btnLoadJSON = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        thicknessCmbBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        btnLeft = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnRight = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        panDrawingArea = new DrawingPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Draw");

        panControls.setPreferredSize(new java.awt.Dimension(270, 300));
        panControls.setLayout(new java.awt.BorderLayout());

        panBrightness.add(coloursBean);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setText("Brightness");
        panBrightness.add(jLabel3);

        scrBrightness.setMaximum(110);
        scrBrightness.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        scrBrightness.setValue(110);
        scrBrightness.setPreferredSize(new java.awt.Dimension(150, 16));
        scrBrightness.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrBrightnessAdjustmentValueChanged(evt);
            }
        });
        panBrightness.add(scrBrightness);

        grpShape.add(radLine);
        radLine.setSelected(true);
        radLine.setText("Line");
        radLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        panShape.add(radLine);

        grpShape.add(radOval);
        radOval.setText("Oval");
        radOval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        panShape.add(radOval);

        grpShape.add(radTriangle);
        radTriangle.setText("Triangle");
        radTriangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        panShape.add(radTriangle);

        grpShape.add(radSquare);
        radSquare.setText("Square");
        radSquare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        panShape.add(radSquare);

        panBrightness.add(panShape);

        panShape1.setMinimumSize(new java.awt.Dimension(261, 45));

        grpDecorator.add(radNormal);
        radNormal.setSelected(true);
        radNormal.setLabel("Normal");
        radNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grpDecoratorEvent(evt);
            }
        });
        panShape1.add(radNormal);

        grpDecorator.add(radDotted);
        radDotted.setLabel("Dotted");
        radDotted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grpDecoratorEvent(evt);
            }
        });
        panShape1.add(radDotted);

        grpDecorator.add(radFilled);
        radFilled.setLabel("Filled in");
        radFilled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grpDecoratorEvent(evt);
            }
        });
        panShape1.add(radFilled);

        panBrightness.add(panShape1);

        backgroundBtn1.setText("Change shape color");
        backgroundBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundBtn1ActionPerformed(evt);
            }
        });
        panBrightness.add(backgroundBtn1);

        backgroundBtn.setText("Change Background Color");
        backgroundBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundBtnActionPerformed(evt);
            }
        });
        panBrightness.add(backgroundBtn);

        btnLeft1.setText("Animation");
        btnLeft1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeft1ActionPerformed(evt);
            }
        });
        panBrightness.add(btnLeft1);

        btnLoadXML.setText("load XML");
        btnLoadXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadXMLActionPerformed(evt);
            }
        });

        btnLoadJSON.setText("load JSON");
        btnLoadJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadJSONActionPerformed(evt);
            }
        });

        jLabel1.setText("Thickness");

        thicknessCmbBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        thicknessCmbBox.setSelectedIndex(4);
        thicknessCmbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thicknessCmbBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Rotate");

        btnLeft.setText("Left");
        btnLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeftActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnRight.setText("Right");
        btnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRightActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panMoreControlsLayout = new javax.swing.GroupLayout(panMoreControls);
        panMoreControls.setLayout(panMoreControlsLayout);
        panMoreControlsLayout.setHorizontalGroup(
            panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMoreControlsLayout.createSequentialGroup()
                .addGroup(panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panMoreControlsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClear)
                            .addComponent(btnLoadXML)
                            .addGroup(panMoreControlsLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLeft)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRight)
                            .addComponent(btnReset)
                            .addComponent(btnLoadJSON)))
                    .addGroup(panMoreControlsLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(thicknessCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panMoreControlsLayout.setVerticalGroup(
            panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMoreControlsLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(thicknessCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnLeft)
                    .addComponent(btnRight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMoreControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoadXML)
                    .addComponent(btnLoadJSON))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panBrightness.add(panMoreControls);

        panControls.add(panBrightness, java.awt.BorderLayout.CENTER);

        getContentPane().add(panControls, java.awt.BorderLayout.WEST);

        panDrawingArea.setBackground(new java.awt.Color(255, 255, 255));
        panDrawingArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panDrawingArea.setFocusCycleRoot(true);
        panDrawingArea.setPreferredSize(new java.awt.Dimension(500, 500));
        panDrawingArea.setLayout(new javax.swing.BoxLayout(panDrawingArea, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(panDrawingArea, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* change the type of shape to be drawn */
    private void radShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radShapeActionPerformed
        if (radLine.isSelected()) {
            drawingPanel.setCurrentShapeType(ShapeType.LINE);
            return;
        } else if (radOval.isSelected()) {
            drawingPanel.setCurrentShapeType(ShapeType.OVAL);
            return;
        } else if (radTriangle.isSelected()) {
            drawingPanel.setCurrentShapeType(ShapeType.TRIANGLE);
            return;
        } else if (radSquare.isSelected()) {
            drawingPanel.setCurrentShapeType(ShapeType.SQUARE);
            return;
        }
    }//GEN-LAST:event_radShapeActionPerformed

    private void scrBrightnessAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrBrightnessAdjustmentValueChanged
        float currentBrightness = scrBrightness.getValue();
        int colourValue = (int) (255 * ((100 - currentBrightness) / 100));

        getColour();
        int newRed, newGreen, newBlue;
        newRed = colour[0] + colourValue;
        newGreen = colour[1] + colourValue;
        newBlue = colour[2] + colourValue;

        if (newRed > 255) {
            newRed = 255;
        } else if (newGreen > 255) {
            newGreen = 255;
        } else if (newBlue > 255) {
            newBlue = 255;
        } else if (newRed < 0) {
            newRed = 0;
        } else if (newGreen < 0) {
            newGreen = 0;
        } else if (newBlue < 0) {
            newBlue = 0;
        }

        coloursBean.setColLabelValue(new Color(newRed, newGreen, newBlue));
        drawingPanel.setCurrentBrightness((100 - currentBrightness) / 100);
        drawingPanel.setCurrentColor(new Color(newRed, newGreen, newBlue));
    }//GEN-LAST:event_scrBrightnessAdjustmentValueChanged

    private void btnLoadXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadXMLActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Whould you like to open from the XML file", "Info", dialogButton);
        if (dialogResult == 0) {
            try {
                XMLShapeReader shapeReader = new XMLShapeReader();

                shapeReader.getShapesFromFile("stored_shapes.xml");
                List listOfShapes = null;

                if (radLine.isSelected()) {
                    listOfShapes = shapeReader.getSlList();
                } else if (radOval.isSelected()) {
                    listOfShapes = shapeReader.getOlList();
                } else if (radTriangle.isSelected()) {
                    listOfShapes = shapeReader.getTlList();
                } else if (radSquare.isSelected()) {
                    listOfShapes = shapeReader.getSqlList();
                }

                drawingPanel.setShapes(listOfShapes);
                drawingPanel.repaint();

            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(XMLShapeReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnLoadXMLActionPerformed

    private void btnLoadJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadJSONActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Whould you like to open from the JSON file", "Info", dialogButton);
        if (dialogResult == 0) {
            try {
                JSONShapeReader shapeReader = new JSONShapeReader();
                shapeReader.getShapesFromFile("stored_shapes.json");

                List listOfShapes = null;

                if (radLine.isSelected()) {
                    listOfShapes = shapeReader.getSlList();
                } else if (radOval.isSelected()) {
                    listOfShapes = shapeReader.getOlList();
                } else if (radTriangle.isSelected()) {
                    listOfShapes = shapeReader.getTlList();
                } else if (radSquare.isSelected()) {
                    listOfShapes = shapeReader.getSqlList();
                }

                drawingPanel.setShapes(listOfShapes);
                drawingPanel.repaint();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DrawerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLoadJSONActionPerformed

    public Color getColour() {
        colour[0] = coloursBean.getRedLabelValue();
        colour[1] = coloursBean.getGreenLabelValue();
        colour[2] = coloursBean.getBlueLabelValue();
        return coloursBean.getColour();
    }


    private void backgroundBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundBtnActionPerformed
        getColour();
        panDrawingArea.setBackground(new Color(colour[0], colour[1], colour[2]));
    }//GEN-LAST:event_backgroundBtnActionPerformed

    private void grpDecoratorEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grpDecoratorEvent
        if (radNormal.isSelected()) {
            decSelection = 0;
        } else if (radDotted.isSelected()) {
            decSelection = 1;
        } else if (radFilled.isSelected()) {
            decSelection = 2;
        }
        getDecoratorSelection();
    }//GEN-LAST:event_grpDecoratorEvent

    private void thicknessCmbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thicknessCmbBoxActionPerformed
        thicknessInt = Integer.parseInt(thicknessCmbBox.getSelectedItem().toString());
        drawingPanel.setCurrentThickness(thicknessInt);
    }//GEN-LAST:event_thicknessCmbBoxActionPerformed

    private void backgroundBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundBtn1ActionPerformed
        getColour();
        drawingPanel.setCurrentColor(new Color(colour[0], colour[1], colour[2]));
    }//GEN-LAST:event_backgroundBtn1ActionPerformed

    private void btnLeft1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeft1ActionPerformed
        drawingPanel.createAnimation();
    }//GEN-LAST:event_btnLeft1ActionPerformed

    /* rotate the drawing 90 degrees to the left */
    private void btnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        drawingPanel.rotate(-90);
    }//GEN-LAST:event_btnLeftActionPerformed

    /* rotate the drawing 90 degrees to the right */
    private void btnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        drawingPanel.rotate(90);
    }//GEN-LAST:event_btnRightActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        if (evt.getSource() == btnReset) {
            decSelection = 0;
            dispose();
            new DrawerMain().setVisible(true);

        }
    }//GEN-LAST:event_btnResetActionPerformed

    /* clear the drawing */
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        drawingPanel.clearDisplay();
    }//GEN-LAST:event_btnClearActionPerformed

    public int getDecoratorSelection() {
        return decSelection;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        new DrawerMain().setVisible(true);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backgroundBtn;
    private javax.swing.JButton backgroundBtn1;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLeft;
    private javax.swing.JButton btnLeft1;
    private javax.swing.JButton btnLoadJSON;
    private javax.swing.JButton btnLoadXML;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRight;
    private coloursbean.ColoursBean coloursBean;
    private javax.swing.ButtonGroup grpDecorator;
    private javax.swing.ButtonGroup grpShape;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panBrightness;
    private javax.swing.JPanel panControls;
    private javax.swing.JPanel panDrawingArea;
    private javax.swing.JPanel panMoreControls;
    private javax.swing.JPanel panShape;
    private javax.swing.JPanel panShape1;
    private javax.swing.JRadioButton radDotted;
    private javax.swing.JRadioButton radFilled;
    private javax.swing.JRadioButton radLine;
    private javax.swing.JRadioButton radNormal;
    private javax.swing.JRadioButton radOval;
    private javax.swing.JRadioButton radSquare;
    private javax.swing.JRadioButton radTriangle;
    private javax.swing.JScrollBar scrBrightness;
    private javax.swing.JComboBox thicknessCmbBox;
    // End of variables declaration//GEN-END:variables

}
