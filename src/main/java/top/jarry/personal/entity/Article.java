package top.jarry.personal.entity;

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

    @OneToMany
    @JoinColumn(name = "article_id" ,referencedColumnName = "id")
    private List<ArticleInfo> articleInfos;

}
