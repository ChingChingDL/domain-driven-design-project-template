package world.snowcrystal.template.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.like.entity.Like;
import world.snowcrystal.template.domain.like.repository.LikeRepository;

import java.util.List;

@Repository
public class LikeRepositoryImpl implements LikeRepository {
    @Override
    public void save(Like Like) {
        throw new UnsupportedOperationException();
    }

    /**
     * 这个文章总共被点赞了多少次
     *
     * @param postId
     */
    @Override
    public Long count(Id postId) {
        throw new UnsupportedOperationException();
    }

    /**
     * 这个用户点赞了多少文章
     *
     * @param userId
     */
    @Override
    public Long countUserFavours(Id userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exists(Id postId, Id userId) {
        throw new UnsupportedOperationException();
    }

    /**
     * 都是谁点赞了这篇文章
     *
     * @param postId
     */
    @Override
    public List<Like> load(Id postId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Like load(Id postId, Id userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Like loadById(Id id) {
        throw new UnsupportedOperationException();
    }

    /**
     * 这个用户点赞了哪些文章，按照点赞时间倒序，最近的在最前面
     *
     * @param userId
     */
    @Override
    public List<Like> loadUserFavours(Id userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Like Like) {
        throw new UnsupportedOperationException();
    }
}
