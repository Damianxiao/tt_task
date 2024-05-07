package tt;

import java.util.List;

import processing.core.PApplet;
import tt.map.Map;
import tt.map.MapLoader;
import tt.map.Position;
import tt.player.Tank;


public class Main extends PApplet{
    private Map map;

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
        int x = tank.getX() * 10 + 5;
        int y = tank.getY() * 10 + 5;
        
    }


    public class keyPressed implements PApplet.KeyListener{
        public void keyPressed() {
            if (key == 'w') {
                map.movePlayer(Position.UP);
            } else if (key == 's') {
                map.movePlayer(Position.DOWN);
            } else if (key == 'a') {
                map.movePlayer(Position.LEFT);
            } else if (key == 'd') {
                map.movePlayer(Position.RIGHT);
            } else if (key == 'q'){
                tank.rotateTower(-5);
            } else if (key == 'e'){
                tank.rotateTower(5);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}