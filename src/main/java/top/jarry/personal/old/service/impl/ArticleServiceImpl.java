package top.jarry.personal.old.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.jarry.personal.infrastructure.common.ServerResponse;
import top.jarry.personal.infrastructure.persistence.entity.Article;
import top.jarry.personal.infrastructure.persistence.ArticleRepository;
import top.jarry.personal.old.service.ArticleService;

import java.util.List;
import java.util.Optional;

/**
 * @Description 文章服务实现
 * @Date 2023/4/12 23:28
 * @Author king
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ServerResponse<List<Article>> getArticles(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Article> articles = articleRepository.findAll(pageable);
        List<Article> articleList = articles.getContent();
        if (articleList.size() == 0) {
            return ServerResponse.createByErrorMessage("文章不存在");
        }
        return ServerResponse.createBySuccess(articleList);
    }

    @Override
    public ServerResponse<Article> getArticleById(long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.map(ServerResponse::createBySuccess)
                .orElseGet(() -> ServerResponse.createByErrorMessage("文章不存在"));
    }

    @Override
    public ServerResponse<Article> getArticleByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return ServerResponse.createByErrorMessage("文章不存在");
        }
        Optional<Article> optionalArticle = articleRepository.findByCode(code);
        return optionalArticle.map(ServerResponse::createBySuccess)
                .orElseGet(() -> ServerResponse.createByErrorMessage("文章不存在"));
    }

    @Override
    public ServerResponse<Article> saveArticle(Article article) {
        if (article == null) {
            return ServerResponse.createByErrorMessage("文章不存在");
        }
        Article savedArticle = articleRepository.save(article);
        return ServerResponse.createBySuccess(savedArticle);
    }

    @Override
    public ServerResponse<Article> updateArticle(Article article) {
        if (article == null) {
            return ServerResponse.createByErrorMessage("文章不存在");
        }

        Article updatedArticle = articleRepository.save(article);
        return ServerResponse.createBySuccess(updatedArticle);
    }

    @Override
    public ServerResponse<Article> deleteArticle(long id) {
        articleRepository.deleteById(id);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse<Article> deleteArticle(String code) {
        articleRepository.deleteByCode(code);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse<Article> publishArticle(long id) {

        return null;
    }

    @Override
    public ServerResponse<Article> publishArticle(String code) {
        return null;
    }

    @Override
    public ServerResponse<Article> unPublishArticle(long id) {
        return null;
    }

    @Override
    public ServerResponse<Article> unPublishArticle(String code) {
        return null;
    }
}
