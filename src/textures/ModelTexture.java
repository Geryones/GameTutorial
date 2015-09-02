package textures;

/**
 * Created by mai714 on 02.09.2015.
 */
public class ModelTexture {

    private int textureID;
    private float shineDamper=1;
    private float reflectivity=0;

    public ModelTexture(int textureID){
        this.textureID=textureID;
    }

    public int getID(){
        return this.textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
}
