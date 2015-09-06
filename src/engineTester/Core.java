package engineTester;

import entity.Camera;
import entity.Entity;
import entity.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;



/**
 * Created by mai714 on 02.09.2015.
 *
 */
public class Core {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();





        RawModel model = OBJLoader.loadObjModel("model/dragon/dragon",loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("rainbow2")));
        ModelTexture texture =staticModel.getModelTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);

        Entity dragonEntity = new Entity(staticModel,new Vector3f(0,0,-25),0,0,0,1);
        Light light = new Light(new Vector3f(3000,2000,2000),new Vector3f(1,1,1));

        Terrain terrain= new Terrain(-1,-1,loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2= new Terrain(0,-1,loader, new ModelTexture(loader.loadTexture("grass")));

        Camera camera= new Camera();
        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()){

            camera.move();
            renderer.processEntity(dragonEntity);
            dragonEntity.increaseRotation(0, 1, 0);


            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);

            renderer.render(light,camera);
            DisplayManager.updtadeDisplay();
        }
        renderer.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();
    }
}
