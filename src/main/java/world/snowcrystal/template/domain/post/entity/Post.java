package world.snowcrystal.template.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.post.primitive.*;

import java.util.Date;
import java.util.List;

/**
 * 文章
 */
@Getter
@Builder
public class Post {

    /**
     * id
     */
    private Id id;

    /**
     * 标题
     */
    private Title title;

    /**
     * 内容
     */
    private Content content;

    /**
     * 标签列表
     */
    private List<Tag> tags;

    /**
     * 点赞数
     */
    private Likes likes;

    /**
     * 收藏数
     */
    private Favours favours;

    /**
     * 创建用户 id
     */
    private Id userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer deleted;


    public void incrementLikes() {
        this.likes = new Likes(this.likes.getValue() + 1);
    }

    public Likes incrementLikesAndGet() {
        this.likes = new Likes(this.likes.getValue() + 1);
        return this.likes;
    }

    public void decrementLikes() {
        this.likes = new Likes(Math.max(0, this.likes.getValue() - 1));
    }

    public void incrementFavours() {
        this.favours = new Favours(this.favours.getValue() + 1);
    }

    public Favours incrementFavoursAndGet() {
        this.favours = new Favours(this.favours.getValue() + 1);
        return this.favours;
    }


    public void decrementFavours() {
        this.favours = new Favours(Math.max(0, this.favours.getValue() - 1));
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void changeTitle(Title newTitle) {
        this.title = newTitle;
    }

    public void modifyContent(Content newContent) {
        this.content = newContent;
    }
    public void modifyTags(List<Tag> newTags) {
        this.tags = newTags;
    }

    public void modifyTitle(Title newTitle) {
        this.title = newTitle;
    }
    public void delete(){
        this.deleted = 1;
    }

}
