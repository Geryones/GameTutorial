package engineTester;

import entity.Camera;
import entity.Entity;
import entity.Light;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by mai714 on 02.09.2015.
 *
 */
public class Core {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();

        ModelData dataTree = OBJFileLoader.loadOBJ("tree");
        RawModel treeModel = loader.loadToVAO(dataTree.getVertices(),dataTree.getTextureCoords(),dataTree.getNormals(),dataTree.getIndices());
        TexturedModel staticTree = new TexturedModel(treeModel, new ModelTexture(loader.loadTexture("tree2")));



        ModelData dataFern = OBJFileLoader.loadOBJ("fern");
        RawModel modelFern = loader.loadToVAO(dataFern.getVertices(),dataFern.getTextureCoords(),dataFern.getNormals(),dataFern.getIndices());
        TexturedModel staticFern= new TexturedModel(modelFern,new ModelTexture(loader.loadTexture("fern_old")));
        staticFern.getModelTexture().setHasTransparency(true);


        ModelData dataGrass = OBJFileLoader.loadOBJ("grassModel");
        RawModel modelGrass = loader.loadToVAO(dataGrass.getVertices(),dataGrass.getTextureCoords(),dataGrass.getNormals(),dataGrass.getIndices());
        TexturedModel staticGrass= new TexturedModel(modelGrass,new ModelTexture(loader.loadTexture("grass_old")));
        staticGrass.getModelTexture().setHasTransparency(true);
        staticGrass.getModelTexture().setUseFakeLighting(true);



        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();
        for (int i = 0; i < 3000; i++){

            //TexturedModel model,    Vector3f position,     float rotX,    float rotY,   float rotZ,    float scale

            entities.add(new Entity(staticTree, new Vector3f(random.nextFloat() * 1600-800 , 0, random.nextFloat() *1600 -800), 0, 0, 0, 3));

            entities.add(new Entity(staticGrass, new Vector3f(random.nextFloat() * 1600-800, 0, random.nextFloat() *1600 -800), 0, 0, 0, 1));

            entities.add(new Entity(staticFern, new Vector3f(random.nextFloat() * 1600-800, 0, random.nextFloat() *1600 -800), 0, 0, 0, 0.6f));
        }






        Light light = new Light(new Vector3f(3000,2000,2000),new Vector3f(1,1,1));

        Terrain terrain= new Terrain(-1,-1,loader, new ModelTexture(loader.loadTexture("grass_flat")));
        Terrain terrain2= new Terrain(0,-1,loader, new ModelTexture(loader.loadTexture("grass_flat")));
        Terrain terrain3= new Terrain(-1,0,loader, new ModelTexture(loader.loadTexture("grass_flat")));
        Terrain terrain4= new Terrain(0,0,loader, new ModelTexture(loader.loadTexture("grass_flat")));


        Camera camera= new Camera();
        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()){

            camera.move();


            for (Entity grassX : entities){
                renderer.processEntity(grassX);

            }


            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            renderer.processTerrain(terrain3);
            renderer.processTerrain(terrain4);

            renderer.render(light,camera);
            DisplayManager.updtadeDisplay();
        }
        renderer.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();
    }
}
