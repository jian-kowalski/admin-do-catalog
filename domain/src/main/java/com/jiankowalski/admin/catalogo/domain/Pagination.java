package com.jiankowalski.admin.catalogo.domain;

import java.util.List;
import java.util.function.Function;

public record Pagination<T>(
        int currentPage,
        int perPage,
        long total,
        List<T> items
) {
    public <R> Pagination<R> map(final Function<T, R> mapper) {
        final List<R> aNewItems = this.items.stream().map(mapper).toList();
        return new Pagination<>(currentPage(), perPage(), total(), aNewItems);
    }
}
