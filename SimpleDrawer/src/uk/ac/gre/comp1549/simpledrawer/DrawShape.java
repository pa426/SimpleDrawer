/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.Graphics2D;

/**
 *
 * @author Alexandru
 */
public interface DrawShape {
    public void drawShape(Graphics2D g2d, float currentBrightness);
    public void fillShape(Graphics2D g2d, float currentBrightness);
}
