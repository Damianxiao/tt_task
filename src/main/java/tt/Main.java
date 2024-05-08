package tt;

import java.util.List;

import processing.core.PApplet;
import tt.map.Map;
import tt.map.MapLoader;
import tt.map.Position;
import tt.player.Tank;

public class Main extends PApplet {
    private Map map;
    private Tank tank;

    // setup
    public void setup() {
        tank = new Tank('A', 0, 0, 100, true, 0);
        keyPressed = new KeyPressed();
    }

    // set map size
    public void settings() {
        size(864, 640);
    }

    // load map
    public void loadMap() {
        map = MapLoader.loadMap("map.txt");
        map.addTrees();
        map.smoothMap();
    }

    // draw map
    public void draw() {
        background(255);
        fill(tank.getSymbol());
        rect(tank.getX() * 10, tank.getY() * 10, 10, 10);
        drawTankAngle(tank.getAngle());
        rotateAnimation();
    }

    private void drawMap(List<List<Integer>> heightMap) {
        for (int y = 0; y < heightMap.size(); y++) {
            for (int x = 0; x < heightMap.get(y).size(); x++) {
                int height = heightMap.get(y).get(x);
                fill(255 - height * 10);
                rect(x * 10, y * 10, 10, 10);
            }
        }
    }

    public void drawTankAngle(double angle) {
        float tankX = tank.getX() +16;
        float tankY = tank.getY() +16;

        beginShape();
        vertex(tankX, tankY);
        vertex(tankX -16,tankY - 16);
        vertex(tankX +16,tankY - 16);
        endShape();

        float angleMarkerX = tankX + cos(radians((float) angle)) * 16;
        float angleMarkerY = tankY + sin(radians((float) angle)) * 16;
        line(tankX,tankY,angleMarkerX,angleMarkerY);
    }

    private void rotateAnimation(){
        beginShape();
        vertex(tank.getX() + 16, tank.getY() + 16);
        vertex(tank.getX() - 16, tank.getY() - 16);
        vertex(tank.getX() + 16, tank.getY() - 16);
        endShape();

        float angleMarkerX = (float) (tank.getX() + 16 * Math.cos(Math.toRadians(tank.getAngle())));
        float angleMarkerY = (float) (tank.getY() + 16 * Math.sin(Math.toRadians(tank.getAngle())));
        line(tank.getX(), tank.getY(), angleMarkerX, angleMarkerY);
    }

    private class keyPressed extends PApplet {
        public void keyPressed() {
            // use eft right to move the tank,up down lto rotate the tower,use W S control bullet power
            if (key == CODED) {
                if (keyCode == LEFT) {
                    tank.setX(tank.getX() - 1);
                } else if (keyCode == RIGHT) {
                    tank.setX(tank.getX() + 1);
                } else if (keyCode == UP) {
                    tank.rotateTower(1);
                } else if (keyCode == DOWN) {
                    tank.rotateTower(-1);
                }
            } else if(key == 'w') {
//                tank.setPower(tank.getPower() + 1);
            } else if (key == 's') {
//                tank.setPower(tank.getPower() - 1);
            }
        }

    }


    public void towerRotateAnimated(){
        
    }



    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}