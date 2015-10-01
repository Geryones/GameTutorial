package textures;

/**
 * Created by mai714 on 25.09.2015.
 */
import java.nio.ByteBuffer;

public class TextureData {

    private int width;
    private int height;
    private ByteBuffer buffer;

    public TextureData(ByteBuffer buffer, int width, int height){
        this.buffer = buffer;
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public ByteBuffer getBuffer(){
        return buffer;
    }

}