package top.jarry.personal.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @description: 文章
 * @author: 天数
 * @since: 2023/2/24 10:15
 */
@Entity
@Data
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "modificator_id")
    private User modificator;

    private LocalDateTime modifyTime;

    private LocalDateTime publishTime;

    @OneToOne
    @JoinColumn(name = "draftArticleInfoId" ,referencedColumnName = "id")
    private ArticleInfo draftArticleInfo;

    @OneToOne
    @JoinColumn(name = "publishedArticleInfoId" ,referencedColumnName = "id")
    private ArticleInfo publishedArticleInfo;

    @OneToMany
    @JoinColumn(name = "articleInfoIds" ,referencedColumnName = "id")
    private List<ArticleInfo> articleInfos;

}
