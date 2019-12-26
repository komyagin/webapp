package io.github.komyagin.service;

import io.github.komyagin.dao.NoticeSqlRepository;
import io.github.komyagin.model.Notice;

import java.util.List;

public class NoticeService {
    NoticeSqlRepository noticeSqlRepository = new NoticeSqlRepository();

    public Notice getNotice(int id) {
        return noticeSqlRepository.getNotice(id);
    }

    public List<Notice> getAllNotices() {
        return noticeSqlRepository.getAllNotices();
    }

    public void addNotice(Notice notice) {
        noticeSqlRepository.addNotice(notice);
    }

    public void updateNotice(Notice notice) {
        noticeSqlRepository.updateNotice(notice);
    }

    public void removeNotice(int id) {
        noticeSqlRepository.removeNotice(id);
    }

    public List<Notice> getNoticeByPersonId(int personId) {
        return noticeSqlRepository.getAllNoticesByPersonId(personId);
    }
}
