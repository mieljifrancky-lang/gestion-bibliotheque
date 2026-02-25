package com.mielji.bibliotheque.service;

import java.util.List;

public interface IMetier<T, ID> {
    T save(T entity);
    T update(T entity);
    void delete(ID id);
    T get(ID id);
    List<T> getAll();
}