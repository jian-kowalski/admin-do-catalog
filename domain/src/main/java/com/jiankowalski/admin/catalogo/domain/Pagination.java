package com.jiankowalski.admin.catalogo.domain;

import java.util.List;

public record Pagination<T>(
        int currentPage,
        int perPage,
        int total,
        List<T> items
) {
}
