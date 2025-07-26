package com.cirilgroup.aventurier.io;

import com.cirilgroup.aventurier.exceptions.MapFormatException;
import com.cirilgroup.aventurier.model.Map;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {

    private final MapLoader loader = new MapLoader();

    @Test
    void testLoadValidMap() throws Exception {
        Path p = Paths.get("src/test/resources/maps/valid_map.txt");
        Map map = loader.loadMap(p);
        assertEquals(5, map.getWidth());
        assertEquals(4, map.getHeight());
    }

    @Test
    void testLoadEmptyMap() {
        Path p = Paths.get("src/test/resources/maps/empty.txt");
        assertThrows(MapFormatException.class, () -> loader.loadMap(p));
    }

    @Test
    void testLoadIrregularMap() {
        Path p = Paths.get("src/test/resources/maps/irregular_map.txt");
        Exception exception = assertThrows(MapFormatException.class, () -> loader.loadMap(p));
        assertTrue(exception.getMessage().contentEquals("Lignes de taille inégale à la ligne 1"));
    }
}
