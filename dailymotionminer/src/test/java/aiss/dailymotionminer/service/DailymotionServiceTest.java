package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DailymotionServiceTest {

    @Autowired
    DailymotionService service;

    private final String realUserId = "x51lpey";   // Canal real de ejemplo
    private final String realVideoId = "xn6y98";  // Vídeo real de ejemplo

    @Test
    @DisplayName("Get Channel from Dailymotion - Validación completa")
    void getChannel() {
        Channel channel = service.getChannel(realUserId);

        assertNotNull(channel, "El canal no puede ser null");
        assertNotNull(channel.getId(), "El canal debe tener id");
        assertNotNull(channel.getNickname(), "El canal debe tener nombre");
        assertTrue(channel.getId().length() > 0, "El id no puede estar vacío");

        System.out.println("Canal obtenido: " + channel);
    }

    @Test
    @DisplayName("Get List of Videos - Validación completa")
    void getListVideos() {
        List<Video> videos = service.getListVideos(realUserId, 5, 1);

        assertNotNull(videos, "La lista no puede ser null");
        assertFalse(videos.isEmpty(), "La lista de vídeos está vacía");
        assertTrue(videos.size() <= 5, "Se han devuelto más vídeos de los solicitados");
        System.out.println("Vídeos obtenidos: " + videos.size());
        System.out.println(videos);
    }

    @Test
    @DisplayName("Get User from Dailymotion - Validación completa")
    void getUser() {
        User user = service.getUser(realUserId);

        assertNotNull(user, "El usuario no puede ser null");
        assertEquals(realUserId, user.getId(), "El ID del usuario no coincide");
        assertNotNull(user.getNickname(), "El usuario debe tener username");

        System.out.println("Usuario obtenido: " + user);
    }

    @Test
    @DisplayName("Get Tags from Dailymotion Video - Validación completa")
    void getTags() {
        Tag tags = service.getTags(realVideoId);

        if (!tags.getTags().isEmpty()) {
            assertNotNull(tags.getTags(), "La lista de tags no puede ser null");
            System.out.println("Tags: " + tags.getTags());
        } else {
            System.out.println("El vídeo no tiene tags");
        }
    }

    @Test
    @DisplayName("Get Subtitles from Dailymotion Video - Validación completa")
    void getSubtitles() {
        SubtitleContainer subtitles = service.getSubtitles(realVideoId);

        if (!subtitles.getList().isEmpty()) {
            assertNotNull(subtitles.getList(), "La lista de subtítulos no puede ser null");
            Subtitle first = subtitles.getList().isEmpty() ? null : subtitles.getList().get(0);
            assertNotNull(first.getLanguageLabel(), "El subtítulo debe tener idioma");
            assertNotNull(first.getUrl(), "El subtítulo debe tener URL");
            System.out.println("Subtítulos: " + subtitles.getList());
        } else {
            System.out.println("El vídeo no tiene subtítulos");
        }
    }
}

