package engineTester;

import entity.Camera;
import entity.Entity;
import entity.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;



/**
 * Created by mai714 on 02.09.2015.
 *
 */
public class Core {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();

        StaticShader shader= new StaticShader();
        Renderer renderer = new Renderer(shader);


        RawModel model = OBJLoader.loadObjModel("model/dragon/dragon",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("drops"));
        TexturedModel staticModel = new TexturedModel(model, texture);

        Entity entity = new Entity(staticModel,new Vector3f(0,0,-25),0,0,0,1);
        Light light = new Light(new Vector3f(0,0,-20),new Vector3f(1,1,1));

        Camera camera= new Camera();


        while(!Display.isCloseRequested()){
            entity.increaseRotation(0,1,0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
            renderer.render(entity,shader);
            shader.stop();
            DisplayManager.updtadeDisplay();
        }
        shader.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();
    }
}
