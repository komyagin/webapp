package io.github.komyagin.dao;

import io.github.komyagin.model.Notice;

import java.util.List;

public interface NoticeRepository {
    Notice addNotice(Notice note);
    Notice readNotice(int id);
    Notice updateNotice(int id, Notice notice);
    Notice removeNotice(int id);
    List<Notice> showAll();
    boolean isExist(int id);
}
