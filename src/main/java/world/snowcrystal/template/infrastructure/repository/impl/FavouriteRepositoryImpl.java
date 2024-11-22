package world.snowcrystal.template.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import world.snowcrystal.template.domain.favorite.entity.Favourite;
import world.snowcrystal.template.domain.favorite.repository.FavouriteRepository;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.infrastructure.repository.mapper.PostFavourMapper;
import world.snowcrystal.template.infrastructure.repository.po.PostFavourPO;

import java.util.List;

@Repository
public class FavouriteRepositoryImpl extends ServiceImpl<PostFavourMapper, PostFavourPO>
        implements FavouriteRepository {
    @Override
    public void save(Favourite favourite) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long count(Id postId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 这个用户收藏了多少文章
     */
    @Override
    public Long countUserFavours(Id userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean exists(Id postId, Id userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 都是谁收藏了这篇文章
     */
    @Override
    public List<Favourite> load(Id postId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Favourite load(Id postId, Id userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Favourite loadById(Id id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 这个用户收藏了哪些文章，最近的在最前面
     */
    @Override
    public List<Favourite> loadUserFavours(Id userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Favourite favourite) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
