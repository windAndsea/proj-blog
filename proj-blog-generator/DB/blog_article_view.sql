-- 分类下文章数的统计视图
CREATE VIEW v_article_category_total AS
SELECT
    c.`name`,
    IFNULL( SUM( t.total ), 0 ) total
FROM
    t_blog_category c
        LEFT JOIN t_blog_label l ON c.id = l.category_id
        LEFT JOIN (
        SELECT
            al.label_id,
            count( a.id ) total
        FROM
            t_blog_article a
                LEFT JOIN t_blog_article_label al ON a.id = al.article_id
        WHERE
                a.`status` = 2
          AND a.ispublic = 1
        GROUP BY
            al.label_id
    ) t ON l.id = t.label_id
GROUP BY
    c.`name`