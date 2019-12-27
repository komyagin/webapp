package io.github.komyagin.dao;

import io.github.komyagin.model.Notice;

import java.util.List;

public interface NoticeRepository {
    boolean addNotice(Notice notice);
    Notice getNotice(int id);
    boolean updateNotice(Notice notice);
    boolean removeNotice(int id);
    List<Notice> getAllNotices();
    List<Notice> getAllNoticesByPersonId(int personId);
}
