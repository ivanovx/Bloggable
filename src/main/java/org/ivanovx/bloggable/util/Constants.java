package org.ivanovx.bloggable.util;

import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.entity.Role;
import org.ivanovx.bloggable.entity.User;

public class Constants {
    public static User DEFAULT_ADMIN = new User("me@ivanov.pro", "ivanovx", "ivanovx", Role.ADMIN);

    public static Category DEFAULT_CATEGORY = new Category("Uncategorized");
}
