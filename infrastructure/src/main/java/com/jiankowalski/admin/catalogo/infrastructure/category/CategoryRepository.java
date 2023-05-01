package com.jiankowalski.admin.catalogo.infrastructure.category;

import com.jiankowalski.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryJpaEntity, String> {
}
