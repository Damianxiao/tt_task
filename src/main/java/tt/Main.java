package tt;

import java.util.List;

import processing.core.PApplet;
import tt.map.Map;
import tt.map.MapLoader;
import tt.map.Position;


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


    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}