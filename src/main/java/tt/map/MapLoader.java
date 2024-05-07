package tt.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    // load map file map.txt
    public static Map loadMap(String filePath) {
        List<List<Character>> map = new ArrayList<>();
        List<Character> row = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    row.add(line.charAt(i));
                }
                map.add(new ArrayList<>(row));
                row.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Map(map);
    }

}
