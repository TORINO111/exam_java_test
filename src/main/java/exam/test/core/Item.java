package exam.test.core;

import java.util.List;

public interface Item<T> {
    public void create(T element);

    public void update(T element);

    public List<T> listAll();
}
