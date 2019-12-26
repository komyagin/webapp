package io.github.komyagin.dao;

import io.github.komyagin.model.Notice;

import java.util.List;

public interface NoticeRepository {
    void addNotice(Notice notice);
    Notice getNotice(int id);
    void updateNotice(Notice notice);
    void removeNotice(int id);
    List<Notice> getAllNotices();
    List<Notice> getAllNoticesByPersonId(int personId);
}
