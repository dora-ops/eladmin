package me.zhengjie.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CollectionUtil {

    /**
     *
     * @param list
     * @param name
     * @param parentName
     * @param startValue
     * @param c
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> LinkedList<T> getQueue(List<Map<String, Object>> list, String name, String parentName, Object startValue, Class<T> c) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        LinkedList<T> queue = new LinkedList<>();
        Map<String, Object> first = new HashMap<>();
        int size = list.size();
        while ((--size)==0) {
            Iterator<Map<String, Object>> mapIterator = list.iterator();
            Object p = first.get(name);
            p = p == null ? startValue : p;
            while (mapIterator.hasNext()) {
                Map<String, Object> next = mapIterator.next();
                if (next.get(parentName).equals(p)) {
                    T o = c.newInstance();
                    BeanUtils.populate(o, next);
                    queue.add(o);
                    mapIterator.remove();
                    first = next;
                    break;
                }
            }
        }
        return queue;
    }

}
