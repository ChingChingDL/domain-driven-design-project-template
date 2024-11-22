package world.snowcrystal.template.domain.like.repository;


import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.like.entity.Like;

import java.util.List;

public interface LikeRepository {


    void save(Like Like);

    /**
     * 这个文章总共被点赞了多少次
     */
    Long count(Id postId);

    /**
     * 这个用户点赞了多少文章
     */
    Long countUserLikes(Id userId);

    boolean exists(Id postId, Id userId);

    /**
     * 都是谁点赞了这篇文章
     */
    List<Like> load(Id postId);

    Like load(Id postId, Id userId);

    Like loadById(Id id);

    /**
     * 这个用户点赞了哪些文章，按照点赞时间倒序，最近的在最前面
     */
    List<Like> loadUserLikes(Id userId);

    void remove(Like Like);


}
