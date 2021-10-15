-- 分类下文章数的统计视图
CREATE VIEW v_article_category_total AS
SELECT c.`name`,
       IFNULL(SUM(t.total), 0) total
FROM t_blog_category c
         LEFT JOIN t_blog_label l ON c.id = l.category_id
         LEFT JOIN (
    SELECT al.label_id,
           count(a.id) total
    FROM t_blog_article a
             LEFT JOIN t_blog_article_label al ON a.id = al.article_id
    WHERE a.`status` = 2
      AND a.ispublic = 1
    GROUP BY al.label_id
) t ON l.id = t.label_id
GROUP BY c.`name`

-- 统计近6个月发布的文章数
CREATE VIEW v_article_latest_six_month_publish_total AS
SELECT t.`year_month`,
       count(a.id) total
FROM (
         SELECT DATE_FORMAT(CURDATE(), '%Y-%m') `year_month`
         UNION
         SELECT DATE_FORMAT(CURDATE() - INTERVAL 1 MONTH, '%Y-%m') `year_month`
         UNION
         SELECT DATE_FORMAT(CURDATE() - INTERVAL 2 MONTH, '%Y-%m') `year_month`
         UNION
         SELECT DATE_FORMAT(CURDATE() - INTERVAL 3 MONTH, '%Y-%m') `year_month`
         UNION
         SELECT DATE_FORMAT(CURDATE() - INTERVAL 4 MONTH, '%Y-%m') `year_month`
         UNION
         SELECT DATE_FORMAT(CURDATE() - INTERVAL 5 MONTH, '%Y-%m') `year_month`
     ) t
         LEFT JOIN t_blog_article a ON t.`year_month` = DATE_FORMAT(a.create_date, '%Y-%m')
    AND a.`status` = 2
    AND a.ispublic = 1
GROUP BY t.`year_month` DESC