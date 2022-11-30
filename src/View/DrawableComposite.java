package View;

import Controller.ConnectionGeometryProcessor;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class that can contain different Drawables and is itself a Drawable
 *
 * @author yagaa
 * @version 1.0
 * @see Drawable
 */
public abstract class DrawableComposite implements Drawable {

    private ArrayList<Drawable> drawableList = new ArrayList<>();

    /**
     * Add Drawables to the DrawableComposite
     *
     * @param drawable The Drawable to be added
     */
    public void addDrawable(Drawable drawable) {
        drawableList.add(drawable);
    }

    /**
     * Remove Drawables from the DrawableComposite
     *
     * @param drawable The Drawable to be removed
     */
    public void removeDrawable(Drawable drawable) {
        drawableList.remove(drawable);
    }

    /**
     * Draw all Drawables in the composite
     *
     * @param panel The JPanel to be drawn on
     * @param connectionProcessor The processor to processor connection params
     */
    public void draw(JPanel panel, ConnectionGeometryProcessor connectionProcessor) {
        for (Drawable drawable: drawableList) {
            drawable.draw(panel, connectionProcessor);
        }
    }
}