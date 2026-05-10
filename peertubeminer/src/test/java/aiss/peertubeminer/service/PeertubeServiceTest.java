package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeertubeServiceTest {

    @Autowired
    PeertubeService service;

    private final String realChannelId = "tv";
    private final String realVideoUuid = "8e0f1c3c-1a6e-4e8d-9f0c-1e8b4f1c3c1a";

    @Test
    @DisplayName("Get Channel from PeerTube")
    void getChannel() {
        Channel channel = service.getChannel(realChannelId);

        assertNotNull(channel, "El canal no puede ser null");
        assertEquals(realChannelId, channel.getId(), "El id del canal no coincide");
        assertNotNull(channel.getDisplayName(), "El canal debe tener displayName");

        System.out.println("Canal obtenido: " + channel);
    }

    @Test
    @DisplayName("Get Videos from PeerTube Channel")
    void getVideosByChannel() {
        List<Video> videos = service.getVideosByChannel(realChannelId, 5);

        assertNotNull(videos, "La lista no puede ser null");
        assertFalse(videos.isEmpty(), "La lista de vídeos está vacía");
        assertTrue(videos.size() <= 5, "Se han devuelto más vídeos de los solicitados");
        System.out.println("Número de vídeos obtenidos: " + videos.size());
        System.out.println("Vídeos obtenidos: " + videos);
    }

    @Test
    @DisplayName("Get Captions from PeerTube Video")
    void getCaptionsByVideo() {
        List<Caption> captions = service.getCaptionsByVideo(realVideoUuid);

        assertNotNull(captions, "La lista no puede ser null");
        assertFalse(captions.isEmpty(), "La lista de vídeos está vacía");

        System.out.println("Captions: " + captions);
    }

    @Test
    @DisplayName("Get Comments from PeerTube Video")
    void getCommentsByVideo() {
        List<Comment> comments = service.getCommentsByVideo(realVideoUuid, 5);
        assertNotNull(comments, "La lista no puede ser null");
        assertFalse(comments.isEmpty(), "La lista de vídeos está vacía");
        System.out.println("Comentarios: " + comments);
    }
}

