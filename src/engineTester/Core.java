package engineTester;

import entity.Camera;
import entity.Entity;
import entity.Light;
import entity.Player;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

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

        // ******************Terrain Texture Stuff ****************

        TerrainTexture backgroundTexture= new TerrainTexture(loader.loadTexture("grass_flat"));
        TerrainTexture rTexture= new TerrainTexture(loader.loadTexture("dirt"));
        TerrainTexture gTexture= new TerrainTexture(loader.loadTexture("flowerGrass"));
        TerrainTexture bTexture= new TerrainTexture(loader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
        TerrainTexture blendMap= new TerrainTexture(loader.loadTexture("blendMap2"));

        Terrain terrain= new Terrain(0,0,loader, texturePack,blendMap,"heightMap00");
        Terrain terrain2= new Terrain(1,0,loader, texturePack,blendMap,"heightMap10");
        Terrain terrain3= new Terrain(0,1,loader, texturePack,blendMap,"heightMap01");
        Terrain terrain4= new Terrain(1,1,loader,texturePack,blendMap,"heightMap11");


        Terrain[][] terrains = new Terrain[2][2];

        terrains[0][0] = terrain;
        terrains[1][0] = terrain2;
        terrains[0][1] = terrain3;
        terrains[1][1] = terrain4;



        //*************** Objekte in Welt***************
        ModelData dataTree = OBJFileLoader.loadOBJ("tree");
        RawModel treeModel = loader.loadToVAO(dataTree.getVertices(),dataTree.getTextureCoords(),dataTree.getNormals(),dataTree.getIndices());
        TexturedModel staticTree = new TexturedModel(treeModel, new ModelTexture(loader.loadTexture("tree2")));


        ModelData dataTreeBig = OBJFileLoader.loadOBJ("lowPolyTree");
        RawModel treeModelBig = loader.loadToVAO(dataTreeBig.getVertices(),dataTreeBig.getTextureCoords(),dataTreeBig.getNormals(),dataTreeBig.getIndices());
        TexturedModel staticBigTree = new TexturedModel(treeModelBig, new ModelTexture(loader.loadTexture("tree2")));



        ModelData dataFern = OBJFileLoader.loadOBJ("fern");
        RawModel modelFern = loader.loadToVAO(dataFern.getVertices(),dataFern.getTextureCoords(),dataFern.getNormals(),dataFern.getIndices());
        TexturedModel staticFern= new TexturedModel(modelFern,new ModelTexture(loader.loadTexture("fern_old")));
        staticFern.getModelTexture().setHasTransparency(true);


        ModelData dataGrass = OBJFileLoader.loadOBJ("grassModel");
        RawModel modelGrass = loader.loadToVAO(dataGrass.getVertices(),dataGrass.getTextureCoords(),dataGrass.getNormals(),dataGrass.getIndices());
        TexturedModel staticGrass= new TexturedModel(modelGrass,new ModelTexture(loader.loadTexture("grass2")));
        staticGrass.getModelTexture().setHasTransparency(true);
        staticGrass.getModelTexture().setUseFakeLighting(true);


        ModelData dataFlower = OBJFileLoader.loadOBJ("grassModel");
        RawModel modelFlower = loader.loadToVAO(dataFlower.getVertices(), dataFlower.getTextureCoords(), dataFlower.getNormals(), dataFlower.getIndices());
        TexturedModel staticFlower= new TexturedModel(modelFlower,new ModelTexture(loader.loadTexture("flower")));
        staticFlower.getModelTexture().setHasTransparency(true);
        staticFlower.getModelTexture().setUseFakeLighting(true);



        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();
        for (int i = 0; i < 3000; i++){

            //TexturedModel model,    Vector3f position,     float rotX,    float rotY,   float rotZ,    float scale

            if (i%15==0){
                float x = random.nextFloat()*1600;
                float z = random.nextFloat()*1600;
                int gridXModels = (int) (x / Terrain.SIZE);
                int gridZModels = (int) (z / Terrain.SIZE);
                Terrain currentTerrain = terrains[gridXModels][gridZModels];
                float y = currentTerrain.getHeightOfTerrain(x,z);

                entities.add(new Entity(staticTree, new Vector3f(x,y,z),0,random.nextFloat()*360,0,3));
            }
            if (i%5==0){
                float x = random.nextFloat()*1600;
                float z = random.nextFloat()*1600;
                int gridXModels = (int) (x / Terrain.SIZE);
                int gridZModels = (int) (z / Terrain.SIZE);
                Terrain currentTerrain = terrains[gridXModels][gridZModels];
                float y = currentTerrain.getHeightOfTerrain(x,z);

                entities.add(new Entity(staticGrass, new Vector3f(x,y,z),0,random.nextFloat()*360,0,1));
            }
            if (i%8==0){
                float x = random.nextFloat()*1600;
                float z = random.nextFloat()*1600;
                int gridXModels = (int) (x / Terrain.SIZE);
                int gridZModels = (int) (z / Terrain.SIZE);
                Terrain currentTerrain = terrains[gridXModels][gridZModels];
                float y = currentTerrain.getHeightOfTerrain(x,z);

                entities.add(new Entity(staticFlower, new Vector3f(x,y,z),0,random.nextFloat()*360,0,1));
            }
            if (i%20==0){
                float x = random.nextFloat()*1600;
                float z = random.nextFloat()*1600;
                int gridXModels = (int) (x / Terrain.SIZE);
                int gridZModels = (int) (z / Terrain.SIZE);
                Terrain currentTerrain = terrains[gridXModels][gridZModels];
                float y = currentTerrain.getHeightOfTerrain(x,z);

                entities.add(new Entity(staticBigTree, new Vector3f(x,y,z),0,random.nextFloat()*360,0,2));
            }
            if (i%10==0){
                float x = random.nextFloat()*1600;
                float z = random.nextFloat()*1600;
                int gridXModels = (int) (x / Terrain.SIZE);
                int gridZModels = (int) (z / Terrain.SIZE);
                Terrain currentTerrain = terrains[gridXModels][gridZModels];
                float y = currentTerrain.getHeightOfTerrain(x,z);

                entities.add(new Entity(staticFern, new Vector3f(x,y,z),0,random.nextFloat()*360,0,1));
            }




        }

        //***************** Light Source*******************
        Light light = new Light(new Vector3f(3000,2000,2000),new Vector3f(1,1,1));

        //*****************Player******************
        ModelData bunnyPlayer = OBJFileLoader.loadOBJ("person");
        RawModel bunnyModel = loader.loadToVAO(bunnyPlayer.getVertices(),bunnyPlayer.getTextureCoords(),bunnyPlayer.getNormals(),bunnyPlayer.getIndices());
        TexturedModel thePlayer=new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("player")));

        Player player = new Player(thePlayer,new Vector3f(800,0,800),0,0,0,0.5f);





        Camera camera= new Camera(player);
        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()){

            int gridX=(int) (player.getPosition().x/Terrain.SIZE);
            int gridZ=(int)(player.getPosition().z/Terrain.SIZE);
            Terrain currentTerrain = terrains[gridX][gridZ];

            camera.move();
            player.move(currentTerrain);
            renderer.processEntity(player);


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
