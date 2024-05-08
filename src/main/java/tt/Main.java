package tt;

import java.util.List;

import processing.core.PApplet;
import tt.map.Map;
import tt.map.MapLoader;
import tt.map.Position;
import tt.player.Tank;


public class Main extends PApplet{
    private Map map;
    private Tank tank;

    // setup
    public void setup() {
        tank = new Tank('A', 100, 100, 100);
        keyPressed = new KeyPressed();
    }

    // set map size
    public void settings() {
        size(864, 640);
    }

    // load map
    public void loadMap() {
        Map = MapLoader.loadMap("map.txt");
        Map.addTrees();
    }

    // draw map
    public void draw() {
        background(255);
        map.draw(map.smoothMap());
        fill(tank.getSymbol());
        rect(tank.getX() * 10, tank.getY() * 10, 10, 10);
        drawTankAngle(tank.getAngle());
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
        int x = tank.getX()+16;
        int y = tank.getY()+16;
        beginShape();
        vertex(x, y);
        vertex(centerX - 16, centerY - 16);
        vertex(centerX + 16, centerY - 16);
        // vertex(x + (int) (Math.cos(Math.toRadians(angle)) * 10), y + (int) (Math.sin(Math.toRadians(angle)) * 10));
        float angleMarkerX = centerX - 16 * Math.cos(Math.toRadians(angle));
        float angleMarkerY = centerY - 16 * Math.sin(Math.toRadians(angle));
        line(centerX, centerY, angleMarkerX, angleMarkerY);
    }


    public class keyPressed implements PApplet.KeyListener{
        public void keyPressed() {
            if (key == 'a') {
                map.movePlayer(Position.LEFT);
            } else if (key == 'd') {
                map.movePlayer(Position.RIGHT);
            } else if (key == 's'){
                tank.rotateTower(-5);
            } else if (key == 'w'){
                tank.rotateTower(5);
        }
    }


    public void towerRotateAnimated(){
        
    }



    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}