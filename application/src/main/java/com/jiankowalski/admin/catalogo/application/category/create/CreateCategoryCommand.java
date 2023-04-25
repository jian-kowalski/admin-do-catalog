package com.jiankowalski.admin.catalogo.application.category.create;

public record CreateCategoryCommand(String name, String descrition, boolean isActive) {
    public static CreateCategoryCommand with(final String aName, final String aDescription,
                                             final boolean isActive) {
        return new CreateCategoryCommand(aName, aDescription, isActive);
    }
}
