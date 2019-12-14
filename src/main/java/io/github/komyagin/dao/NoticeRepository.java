package io.github.komyagin.dao;

import io.github.komyagin.model.Notice;

import java.util.List;

public interface NoticeRepository {
    void addNotice(Notice note);
    Notice readNotice(int id);
    Notice updateNotice(Notice notice);
    Notice removeNotice(int id);
    List<Notice> showAll();
    boolean isExist(int id);
}
