package world.snowcrystal.template.domain.post.repository;

import world.snowcrystal.template.infrastructure.repository.po.Post;

public interface PostRepository {
    /**
     * 保存
     */
    void save(Post post);

    /**
     * 查询订单
     */
    Post findByTradeNumber(String tradeNumber);

}
