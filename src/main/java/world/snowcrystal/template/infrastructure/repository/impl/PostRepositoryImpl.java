package world.snowcrystal.template.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.enums.SortOrderEnum;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.common.query.PageQuery;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.post.dto.query.PostQuery;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.post.primitive.Title;
import world.snowcrystal.template.domain.post.repository.PostRepository;
import world.snowcrystal.template.infrastructure.converter.PostConverter;
import world.snowcrystal.template.infrastructure.repository.mapper.PostMapper;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;
import world.snowcrystal.template.infrastructure.utils.SqlUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("postRepository")
@RequiredArgsConstructor
public class PostRepositoryImpl extends ServiceImpl<PostMapper, PostPO>
        implements IService<PostPO>, PostRepository {

    private final PostConverter postConverter;

    private QueryWrapper<PostPO> getQueryWrapper(PostQuery postQueryRequest) {
        QueryWrapper<PostPO> queryWrapper = new QueryWrapper<>();
        if (postQueryRequest == null) {
            return queryWrapper;
        }
        String searchText = postQueryRequest.getSearchText();
        String sortField = postQueryRequest.getSortField();
        String sortOrder = postQueryRequest.getSortOrder();
        Long id = postQueryRequest.getId();
        String title = postQueryRequest.getTitle();
        String content = postQueryRequest.getContent();
        List<String> tagList = postQueryRequest.getTags();
        Long userId = postQueryRequest.getUserId();
        Long notId = postQueryRequest.getNotId();
        // 拼接查询条件
        if (StringUtils.isNotBlank(searchText)) {
            queryWrapper.like("title", searchText).or().like("content", searchText);
        }
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
        if (CollectionUtils.isNotEmpty(tagList)) {
            for (String tag : tagList) {
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }
        queryWrapper.ne(ObjectUtils.isNotEmpty(notId), "id", notId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), SortOrderEnum.ASCENDING.getValue().equals(sortOrder),
                sortField);
        return queryWrapper;
    }

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
    public Page<Post> loadBatchByPage(List<Id> ids, PageQuery pageQuery) {
        int current = pageQuery.getCurrent();
        int pageSize = pageQuery.getSize();
        String sortField = pageQuery.getSortField();
        SortOrderEnum sortOrder = Optional.of(SortOrderEnum.valueOf(pageQuery.getSortOrder())).orElse(SortOrderEnum.ASCENDING);
        QueryWrapper<PostPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isDelete", false)
                .in(CollectionUtils.isNotEmpty(ids), "id", ids);
        if (StringUtils.isNotBlank(sortField)) {
            queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.getValue().equals(SortOrderEnum.ASCENDING.getValue()),
                    sortField);
        }
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<PostPO> postPage
                = page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current, pageSize), queryWrapper
        );
        return Page.<Post>builder().current((long) current)
                .records(postPage.getRecords().stream().map(this::entity).toList())
                .total(postPage.getTotal())
                .size((long) pageSize)
                .build();
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

    @Override
    public Page<Post> listPage(PostQuery postQuery) {
        long current = postQuery.getCurrent();
        long pageSize = postQuery.getSize();
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<PostPO> postPage = page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current, pageSize),
                getQueryWrapper(postQuery));
        return Page.<Post>builder().current(current)
                .records(postPage.getRecords().stream().map(this::entity).toList())
                .total(postPage.getTotal())
                .size(pageSize)
                .build();
    }
}
