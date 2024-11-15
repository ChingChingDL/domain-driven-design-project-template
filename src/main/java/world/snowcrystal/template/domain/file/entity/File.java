package world.snowcrystal.template.domain.file.entity;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import org.springframework.http.MediaType;
import world.snowcrystal.template.domain.file.type.Filename;

import java.net.URI;

@Builder
public class File {
    @Nonnull
    URI uri;
    @Nonnull
    MediaType mediaType;
    @Nonnull
    Filename filename;
}
