package io.github.komyagin.dao;

import io.github.komyagin.model.Notice;

public interface NoticeRepository {
    void addNotice(Notice note);
    Notice readNotice(int id);
    void updateNotice(int id, Notice notice);
    void removeNotice(int id);
    void showAll();
    boolean isExist(int id);
}
