package com.jiankowalski.admin.catalogo.domain.category;

public record SearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
