package com.cirilgroup.aventurier.io;

import com.cirilgroup.aventurier.exceptions.MoveFormatException;
import com.cirilgroup.aventurier.model.Direction;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.model.Position;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class MoveLoaderTest {

    private final MoveLoader loader = new MoveLoader();

    @Test
    void testLoadValidMoves() throws Exception {
        Path p = Paths.get("src/test/resources/movements/movement1.txt");
        MoveSequence seq = loader.loadMoves(p);
        assertEquals(new Position(3, 0), seq.getStart());
        assertEquals(5, seq.getDirections().size());
        assertEquals(Direction.S, seq.getDirections().get(0));
    }

    @Test
    void testLoadIncompleteFile() {
        Path p = Paths.get("src/test/resources/movements/incomplete.txt");
        assertThrows(MoveFormatException.class, () -> loader.loadMoves(p));
    }

    @Test
    void testLoadInvalidCoords() {
        Path p = Paths.get("src/test/resources/movements/bad_coords.txt");
        assertThrows(MoveFormatException.class, () -> loader.loadMoves(p));
    }
}
