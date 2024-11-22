package world.snowcrystal.template.domain.like.repository;


import world.snowcrystal.template.domain.favorite.entity.Favourite;
import world.snowcrystal.template.domain.identifier.primitive.Id;

import java.util.List;

public interface FavouriteRepository {


    void save(Favourite favourite);

    /**
     * 这个文章总共被收藏了多少次
     */
    Long count(Id postId);

    /**
     * 这个用户收藏了多少文章
     */
    Long countUserFavours(Id userId);

    boolean exists(Id postId, Id userId);

    /**
     * 都是谁收藏了这篇文章
     */
    List<Favourite> load(Id postId);

    Favourite load(Id postId, Id userId);

    Favourite loadById(Id id);

    /**
     * 这个用户收藏了哪些文章，按照收藏时间倒序，最近的在最前面
     */
    List<Favourite> loadUserFavours(Id userId);

    void remove(Favourite favourite);


}
