package guis;

import org.lwjgl.util.vector.Vector2f;


/**
 * Created by mai714 on 15.09.2015.
 */
public class GuiTexture {

    private int texture;
    private Vector2f scale;
    private Vector2f position;

    public GuiTexture(int texture,  Vector2f position, Vector2f scale) {
        this.texture = texture;
        this.scale = scale;
        this.position = position;
    }

    public int getTexture() {
        return texture;
    }

    public Vector2f getScale() {
        return scale;
    }

    public Vector2f getPosition() {
        return position;
    }
}
