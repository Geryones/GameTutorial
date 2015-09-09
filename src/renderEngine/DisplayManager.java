package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

/**
 * Created by mai714 on 02.09.2015.
 */
public class DisplayManager {
    private static final int WIDTH=1280;
    private static final int HEIGHT=720;
    private final static int FPS_CAP=120;
    private static long lastFrameTime;
    private static float delta;


    public static void createDisplay(){

        ContextAttribs attribs= new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);



        try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create(new PixelFormat(),attribs);
            Display.setTitle("Game Tutorial");
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0,0,WIDTH,HEIGHT);
        lastFrameTime=getCurrenTime();
    }

    public static void updtadeDisplay(){

        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime=getCurrenTime();
        delta=(currentFrameTime-lastFrameTime)/1000f;
        lastFrameTime=currentFrameTime;
    }

    public static float getFrameTimeSeconds(){
        return delta;
    }
    public static void closeDisplay(){
        Display.destroy();
    }


    private static long getCurrenTime(){
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }
}
