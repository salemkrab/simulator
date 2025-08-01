package com.cirilgroup.aventurier.service;

import com.cirilgroup.aventurier.exceptions.MapFormatException;
import com.cirilgroup.aventurier.io.MapLoader;
import com.cirilgroup.aventurier.model.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapServiceTest {

    @Mock
    private MapLoader mapLoader;

    @InjectMocks
    private MapService mapService;

    @Captor
    private ArgumentCaptor<Path> pathCaptor;

    private Path dummyPath;
    private Map dummyMap;

    @BeforeEach
    void setUp() {
        dummyPath = Path.of("map.txt");
        dummyMap = mock(Map.class);
    }

    @Test
    void loadMap_ShouldReturnMap_WhenLoaderSucceeds() throws Exception {
        // Arrange
        when(mapLoader.loadMap(dummyPath)).thenReturn(dummyMap);

        // Act
        Map result = mapService.loadMap(dummyPath);

        // Assert
        assertThat(result).isSameAs(dummyMap);
        verify(mapLoader).loadMap(pathCaptor.capture());
        assertThat(pathCaptor.getValue()).isEqualTo(dummyPath);
        verifyNoMoreInteractions(mapLoader);
    }

    @Test
    void loadMap_ShouldThrowIOException_WhenLoaderThrowsIOException() throws Exception {
        // Arrange
        when(mapLoader.loadMap(dummyPath)).thenThrow(new IOException("IO failure"));

        // Act & Assert
        assertThatThrownBy(() -> mapService.loadMap(dummyPath))
            .isInstanceOf(IOException.class)
            .hasMessageContaining("IO failure");

        verify(mapLoader).loadMap(dummyPath);
        verifyNoMoreInteractions(mapLoader);
    }

    @Test
    void loadMap_ShouldThrowMapFormatException_WhenLoaderThrowsMapFormatException() throws Exception {
        // Arrange
        when(mapLoader.loadMap(dummyPath)).thenThrow(new MapFormatException("Bad format"));

        // Act & Assert
        assertThatThrownBy(() -> mapService.loadMap(dummyPath))
            .isInstanceOf(MapFormatException.class)
            .hasMessageContaining("Bad format");

        verify(mapLoader).loadMap(dummyPath);
        verifyNoMoreInteractions(mapLoader);
    }
}
