package org.ivanovx.bloggable.util;

import org.ivanovx.bloggable.entity.Role;
import org.ivanovx.bloggable.entity.User;
import org.ivanovx.bloggable.entity.Category;

public final class Constants {
    public static final User DEFAULT_ADMIN = new User("me@ivanov.pro", "ivanovx", "ivanovx", Role.ADMIN);

    public static final Category DEFAULT_CATEGORY = new Category("Uncategorized");

    private Constants() { }
}