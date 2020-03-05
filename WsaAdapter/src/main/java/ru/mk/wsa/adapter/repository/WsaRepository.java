package ru.mk.wsa.adapter.repository;

public interface WsaRepository {
    <T> void put(String key, T object);
    <T> T get(String key);
    void deleteKey(String key);
}
