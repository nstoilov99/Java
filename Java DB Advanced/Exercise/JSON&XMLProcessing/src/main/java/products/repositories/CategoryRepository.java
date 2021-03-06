package products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import products.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT c.category_name, count(p.product_id) AS count, avg(p.price), sum(p.price)\n" +
            "  FROM categories AS c\n" +
            "  INNER JOIN products_categories AS pc\n" +
            "  ON c.category_id = pc.category_id\n" +
            "  INNER JOIN products AS p\n" +
            "  ON pc.product_id = p.product_id\n" +
            "  GROUP BY c.category_id\n" +
            "ORDER BY count DESC", nativeQuery = true)
    List<Object> getCategoriesByProductsCount();
}
