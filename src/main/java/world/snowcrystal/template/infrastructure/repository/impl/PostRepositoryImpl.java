package world.snowcrystal.template.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.infrastructure.converter.PostConverter;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.post.primitive.Title;
import world.snowcrystal.template.domain.post.repository.PostRepository;
import world.snowcrystal.template.infrastructure.repository.mapper.PostMapper;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;

import java.util.List;
import java.util.stream.Collectors;

@Repository("postRepository")
@RequiredArgsConstructor
public class PostRepositoryImpl extends ServiceImpl<PostMapper, PostPO>
        implements IService<PostPO>, PostRepository {

    private final PostConverter postConverter;

    private Post entity(PostPO po) {
        if (po == null) {
            throw new BusinessException(ApplicationResponseStatusCode.NOT_FOUND_ERROR, "没有找到相应文章");
        }
        return postConverter.toEntity(po);
    }

    private PostPO po(Post entity) {
        return postConverter.toPO(entity);
    }

    @Override
    public void save(Post post) {
        this.save(po(post));
    }

    @Override
    public Post load(Id id) {
        return entity(this.getById(id.getValue()));
    }

    @Override
    public Post load(Id id, Id userId) {
        QueryWrapper<PostPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id.getValue())
                .eq("userId", userId.getValue());
        Post post = entity(getOne(queryWrapper));
        if (post == null) {
            throw new BusinessException(ApplicationResponseStatusCode.NOT_FOUND_ERROR, "没有找到相应文章");
        }
        return post;
    }

    @Override
    public Post load(Title title) {
        QueryWrapper<PostPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title.getValue());
        return entity(this.getOne(queryWrapper));
    }

    @Override
    public List<Post> loadBatch(List<Id> ids) {
        return listByIds(ids.stream().map(Id::getValue).distinct().toList())
                .stream().map(this::entity).toList();
    }

    @Override
    public void remove(Post post) {
        this.remove(post.getId());
    }

    @Override
    public void remove(Id id) {
        this.removeById(id.getValue());
    }

    @Override
    public void remove(Title title) {
        QueryWrapper<PostPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title.getValue());
        this.remove(queryWrapper);
    }

    @Override
    public void removeBatch(List<Post> posts) {
        this.removeByIds(posts.stream().map(this::po).map(PostPO::getId).collect(Collectors.toList()));
    }
}
