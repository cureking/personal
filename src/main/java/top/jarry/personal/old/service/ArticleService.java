package top.jarry.personal.old.service;

import top.jarry.personal.infrastructure.common.ServerResponse;
import top.jarry.personal.infrastructure.persistence.entity.Article;

import java.util.List;

/**
 * @Description 文章服务
 * @Date 2023/4/12 22:58
 * @Author king
 */
public interface ArticleService {
    /**
     * 查询文章
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<List<Article>> getArticles(int pageNum, int pageSize);

    /**
     * 查询文章
     * @param id
     * @return
     */
    ServerResponse<Article> getArticleById(long id);

    /**
     * 查询文章
     * @param code
     * @return
     */
    ServerResponse<Article> getArticleByCode(String code);

    /**
     * 保存文章
     * @param article
     * @return
     */
    ServerResponse<Article> saveArticle(Article article);

    /**
     * 更新文章
     * @param article
     * @return
     */
    ServerResponse<Article> updateArticle(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    ServerResponse<Article> deleteArticle(long id);

    /**
     * 删除文章
     * @param code
     * @return
     */
    ServerResponse<Article> deleteArticle(String code);

    /**
     * 发布文章
     * @param id
     * @return
     */
    ServerResponse<Article> publishArticle(long id);

    /**
     * 发布文章
     * @param code
     * @return
     */
    ServerResponse<Article> publishArticle(String code);

    /**
     * 撤回发布文章
     * @param id
     * @return
     */
    ServerResponse<Article> unPublishArticle(long id);

    /**
     * 撤回发布文章
     * @param code
     * @return
     */
    ServerResponse<Article> unPublishArticle(String code);

}
