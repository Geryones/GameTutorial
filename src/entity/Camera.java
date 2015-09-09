package entity;


import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by mai714 on 02.09.2015.
 */
public class Camera {

    private float distanceFromPlayer=50;
    private float angleAroundPlayer=0;
    private Vector3f position=new Vector3f(0,0,0);
    private float pitch=20;
    private float yaw=0;




    private Player player;

    public Camera(Player player){
        this.player=player;
    }



    public void move(){
        calculateZoom();
        calculatePitch();
        calculateAngleAroundPlayer();
        float horizontalDistance=calculateHorizontalDistance();
        float verticalDistance=calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance,verticalDistance);
        this.yaw=180-(player.getRotY()+angleAroundPlayer);

    }

    private float calculateHorizontalDistance(){
        return (float) (distanceFromPlayer*Math.cos(Math.toRadians(pitch)));
    }
    private float calculateVerticalDistance(){
        return (float) (distanceFromPlayer*Math.sin(Math.toRadians(pitch)));
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



    private void calculateCameraPosition(float horizDistance, float verticDistance){
        float theta=player.getRotY()+angleAroundPlayer;
        float offsetX= (float) (horizDistance*Math.sin(Math.toRadians(theta)));
        float offsetZ= (float) (horizDistance*Math.cos(Math.toRadians(theta)));
        position.x=player.getPosition().x - offsetX;
        position.z=player.getPosition().z - offsetZ;
        position.y=player.getPosition().y + verticDistance;

    }

    private void calculateZoom(){
        float zoomLevel = Mouse.getDWheel()*0.1f;
        distanceFromPlayer-=zoomLevel;
    }

    public void calculatePitch(){
        if (Mouse.isButtonDown(1)){
            float pitchChange=Mouse.getDY()*0.1f;
            pitch-=pitchChange;
        }
    }

    private void calculateAngleAroundPlayer(){
        if(Mouse.isButtonDown(1)){
            float angleChange=Mouse.getDX()*0.3f;
            angleAroundPlayer-=angleChange;
        }
    }

}
