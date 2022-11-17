package View;

import Controller.ConnectionGeometryProcessor;
import Model.UserClass;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class that can contain different Drawables and is itself a Drawable
 *
 * @author yagaa
 * @version 1.0
 * @see Drawable
 */
public class DrawableComposite implements Drawable {

    private ArrayList<Drawable> drawableList = new ArrayList<>();

    /**
     * Add Drawables to the DrawableComposite
     *
     * @param drawable
     */
    public void addDrawable(Drawable drawable) {
        drawableList.add(drawable);
    }

    /**
     * Remove Drawables from the DrawableComposite
     *
     * @param drawable
     */
    public void removeDrawable(Drawable drawable) {
        drawableList.remove(drawable);
    }

    /**
     * Draw all Drawables in the composite
     *
     * @param panel The JPanel to be drawn on
     * @param  The list of Points that need to be drawn
     */
    public void draw(JPanel panel, ConnectionGeometryProcessor connectionProcessor) {
        for (Drawable drawable: drawableList) {
            drawable.draw(panel, connectionProcessor);
        }
    }
}
