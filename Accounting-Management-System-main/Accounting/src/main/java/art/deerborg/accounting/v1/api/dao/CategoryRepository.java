package art.deerborg.accounting.v1.api.dao;

import art.deerborg.accounting.v1.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
