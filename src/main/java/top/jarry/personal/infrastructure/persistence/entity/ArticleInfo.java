package top.jarry.personal.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 文章信息
 * @Date 2023/4/12 23:05
 * @Author king
 */
@Entity
@Data
public class ArticleInfo {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 文章唯一标识
     * @see Article#getId()
     */
    private long articleId;

    /**
     * 文章编码
     * @see Article#getCode()
     */
    private String articleCode;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;

    /**
     * 版本
     * V3.2.1
     */
    private String version;

}
