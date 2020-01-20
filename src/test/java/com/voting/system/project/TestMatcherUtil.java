package com.voting.system.project;

import com.voting.system.project.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMatcherUtil {
    public static final Map<Class<? extends HasId>, String[]> ignoreFieldsMap = new HashMap<>();

    static {
        ignoreFieldsMap.put(User.class, new String[]{"registered"});
        ignoreFieldsMap.put(Restaurant.class, new String[]{"menus"});
        ignoreFieldsMap.put(Menu.class, new String[]{"dishes", "restaurant"});
        ignoreFieldsMap.put(Dish.class, new String[]{"menu"});
    }

    public static <T extends HasId> void assertMatch(T actual, T expected) {
        String[] ignoredFields = ignoreFieldsMap.getOrDefault(actual.getClass(), new String[0]);
        assertThat(actual).isEqualToIgnoringGivenFields(expected, ignoredFields);
    }

    @SafeVarargs
    public static <T extends HasId> void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T extends HasId> void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        String[] ignoredFields = ignoreFieldsMap.getOrDefault(actual.iterator().next().getClass(), new String[0]);
        assertThat(actual).usingElementComparatorIgnoringFields(ignoredFields).isEqualTo(expected);
    }
}