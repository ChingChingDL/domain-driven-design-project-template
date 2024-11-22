package world.snowcrystal.template.domain.post.repository;

import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.common.query.PageQuery;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.post.dto.query.PostQuery;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.post.primitive.Title;

import java.util.List;


public interface PostRepository {

    void save(Post post);

    Post load(Id id);

    Post load(Id id, Id userId);

    Post load(Title title);

    List<Post> loadBatch(List<Id> ids);

    Page<Post> loadBatchByPage(List<Id> ids, PageQuery pageQuery);

    void remove(Post post);

    void remove(Id id);

    void remove(Title title);

    void removeBatch(List<Post> posts);

    Page<Post> listPage(PostQuery postQuery);
}
