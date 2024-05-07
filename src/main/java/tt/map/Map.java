package tt.map;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<List<Character>> grid;

    public Map(List<List<Character>> grid) {
        this.grid = grid;
    }

    public List<List<Integer>> calculateMapHeight() {
        List<List<Integer>> heightMap = new ArrayList<>();
        for (List<Character> row : grid) {
            List<Integer> heightRow = new ArrayList<>();
            for (char c : row) {
                heightRow.add(toHeight(c));
            }
            heightMap.add(new ArrayList<>(heightRow));
        }
        return heightMap;
    }

    private int toHeight(char c) {
        switch (c) {
            case 'X':
                return 10;
            case ' ':
                return 0;
            default:
                return 0;
        }
    }

    // smooth the map
    public List<List<Integer>> smoothMap() {
        List<List<Integer>> heightMap = calculateMapHeight();
        int smoothingValue = 3;

        for (int y = 0; y < heightMap.size(); y++) {
            for (int x = 0; x < heightMap.get(y).size(); x++) {
                int sum = 0;
                int count = 0;
                for (int dy = -smoothingValue; dy <= smoothingValue; dy++) {
                    for (int dx = -smoothingValue; dx <= smoothingValue; dx++) {
                        int nx = x + dx;
                        int ny = y + dy;
                        if (nx >= 0 && nx < heightMap.get(y).size() && ny >= 0 && ny < heightMap.size()) {
                            sum += heightMap.get(ny).get(nx);
                            count++;
                        }
                    }
                }
                heightMap.get(y).set(x, sum / count);
            }
        }
        return heightMap;
    }

    // get the position of the tank player A
    public Position getPosition() {
        for (List<Character> row : grid) {
            for (int x = 0; x < grid.get(0).size(); x++) {
                if (row.get(x) == 'A') {
                    return new Position(x, grid.indexOf(row) + 1);
                }
            }
        }
        return null;
    }

    public void addTrees() {
        List<List<Integer>> heightMap = calculateMapHeight();
        List<List<Character>> treeMap = new ArrayList<>();

        for (List<Integer> row : heightMap) {
            List<Character> treeRow = new ArrayList<>();
            for (int height : row) {
                if (height > 5) {
                    treeRow.add('T');
                } else {
                    treeRow.add(' ');
                }
            }
            treeMap.add(new ArrayList<>(treeRow));
        }

        for (int y = 0; y < treeMap.size(); y++) {
            for (int x = 0; x < treeMap.get(y).size(); x++) {
                // set the tree in the grid
                if (treeMap.get(y).get(x) == 'T') {
                    grid.get(y).set(x, 'T');
                }
            }
        }
    }

}
