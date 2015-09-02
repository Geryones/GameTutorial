package shaders;

import entity.Camera;
import org.lwjgl.util.vector.Matrix4f;
import toolbox.Maths;

/**
 * Created by mai714 on 02.09.2015.
 */
public class StaticShader extends ShaderProgramm{


    private static final String VERTEX_FILE="src/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE="src/shaders/fragmentShader.txt";
    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;


    public StaticShader(){
        super(VERTEX_FILE,FRAGMENT_FILE);
    }


    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix= super.getUniformLocation("transformationMatrix");
        location_projectionMatrix=super.getUniformLocation("projectionMatrix");
        location_viewMatrix=super.getUniformLocation("viewMatrix");


    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1,"textureCoords");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix,matrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix,projection);
    }
    public void loadViewMatrix(Camera camera){
       Matrix4f viewMatrix= Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix,viewMatrix);
    }
}
