package entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by mai714 on 02.09.2015.
 */
public class Camera {
    private Vector3f position=new Vector3f(0,1,0);
    private float pitch;
    private float yaw;
    private float roll;
    private static float CAMERASPEED = 1F;

    public Camera(){}



    public void move(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            position.z-=CAMERASPEED;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            position.x+=CAMERASPEED;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            position.x-=CAMERASPEED;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            position.z+=CAMERASPEED;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            position.y+=CAMERASPEED;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_C)){
            position.y-=CAMERASPEED;
        }

    }


    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

   /* private float speed;

    public Camera()
    {

        this.speed = 0.5f;

    }

    public void move()
    {

        yaw =  - (Display.getWidth() - Mouse.getX() / 2) / 2;
        pitch =  (Display.getHeight() / 2) - Mouse.getY();

        if (pitch >= 90)
        {

            pitch = 90;

        }
        else if (pitch <= -90)
        {

            pitch = -90;

        }

        if (Keyboard.isKeyDown(Keyboard.KEY_W))
        {

            position.z += -(float)Math.cos(Math.toRadians(yaw)) * speed;
            position.x += (float)Math.sin(Math.toRadians(yaw)) * speed;

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            position.z -= -(float)Math.cos(Math.toRadians(yaw)) * speed;
            position.x -= (float)Math.sin(Math.toRadians(yaw)) * speed;


        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D))
        {

            position.z += (float)Math.sin(Math.toRadians(yaw)) * speed;
            position.x += (float)Math.cos(Math.toRadians(yaw)) * speed;

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_A))
        {

            position.z -= (float)Math.sin(Math.toRadians(yaw)) * speed;
            position.x -= (float)Math.cos(Math.toRadians(yaw)) * speed;

        }
        System.out.println(position);
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

*/

}
