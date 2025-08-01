package com.cirilgroup.aventurier.service;

import com.cirilgroup.aventurier.exceptions.MoveFormatException;
import com.cirilgroup.aventurier.io.MoveLoader;
import com.cirilgroup.aventurier.model.MoveSequence;
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
class MoveServiceTest {

    @Mock
    private MoveLoader moveLoader;

    @InjectMocks
    private MoveService moveService;

    @Captor
    private ArgumentCaptor<Path> pathCaptor;

    private Path dummyPath;
    private MoveSequence dummySequence;

    @BeforeEach
    void setUp() {
        dummyPath = Path.of("moves.txt");
        dummySequence = mock(MoveSequence.class);
    }

    @Test
    void loadMoves_ShouldReturnSequence_WhenLoaderSucceeds() throws Exception {
        // Arrange
        when(moveLoader.loadMoves(dummyPath)).thenReturn(dummySequence);

        // Act
        MoveSequence result = moveService.loadMoves(dummyPath);

        // Assert
        assertThat(result).isSameAs(dummySequence);
        verify(moveLoader).loadMoves(pathCaptor.capture());
        assertThat(pathCaptor.getValue()).isEqualTo(dummyPath);
        verifyNoMoreInteractions(moveLoader);
    }

    @Test
    void loadMoves_ShouldThrowIOException_WhenLoaderThrowsIOException() throws Exception {
        // Arrange
        when(moveLoader.loadMoves(dummyPath)).thenThrow(new IOException("IO failure"));

        // Act & Assert
        assertThatThrownBy(() -> moveService.loadMoves(dummyPath))
            .isInstanceOf(IOException.class)
            .hasMessageContaining("IO failure");

        verify(moveLoader).loadMoves(dummyPath);
        verifyNoMoreInteractions(moveLoader);
    }

    @Test
    void loadMoves_ShouldThrowMoveFormatException_WhenLoaderThrowsMoveFormatException() throws Exception {
        // Arrange
        when(moveLoader.loadMoves(dummyPath)).thenThrow(new MoveFormatException("Bad format"));

        // Act & Assert
        assertThatThrownBy(() -> moveService.loadMoves(dummyPath))
            .isInstanceOf(MoveFormatException.class)
            .hasMessageContaining("Bad format");

        verify(moveLoader).loadMoves(dummyPath);
        verifyNoMoreInteractions(moveLoader);
    }
}
