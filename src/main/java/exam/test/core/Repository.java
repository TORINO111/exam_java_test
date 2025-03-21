package exam.test.core;

import java.util.List;

public interface Repository<T> {
    void insert(T data);

    List<T> selectAll();

    void remove(T element);
}
