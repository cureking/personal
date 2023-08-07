package top.jarry.personal.infrastructure.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import top.jarry.personal.infrastructure.persistence.entity.Article;
import top.jarry.personal.infrastructure.persistence.entity.ArticleInfo;
import top.jarry.personal.infrastructure.persistence.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @description:
 * @author: 天数
 * @since: 2023/2/24 10:39
 */
public interface ArticleRepository extends CrudRepository<Article, Long>, ListPagingAndSortingRepository<Article, Long>, PagingAndSortingRepository<Article, Long> {
    void deleteByCode(String code);
    @Transactional
    @Modifying
    @Query("update Article a set a.modificator = ?1, a.modifyTime = ?2, a.articleInfos = ?3 where a.code = ?4")
    int updateModificatorAndModifyTimeAndArticleInfosByCode(User modificator, LocalDateTime modifyTime, ArticleInfo articleInfos, String code);
    Optional<Article> findByCode(String code);

}
