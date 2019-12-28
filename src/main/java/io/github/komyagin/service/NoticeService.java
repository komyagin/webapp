package io.github.komyagin.service;

import io.github.komyagin.dao.NoticeSqlRepository;
import io.github.komyagin.model.Notice;

import java.util.List;

public class NoticeService {
    private final NoticeSqlRepository noticeSqlRepository = new NoticeSqlRepository();

    public Notice getNotice(int id) {
        return noticeSqlRepository.getNotice(id);
    }

    public List<Notice> getAllNotices() {
        return noticeSqlRepository.getAllNotices();
    }

    public boolean addNotice(Notice notice) {
        return noticeSqlRepository.addNotice(notice);
    }

    public boolean updateNotice(Notice notice) {
        return noticeSqlRepository.updateNotice(notice);
    }

    public boolean removeNotice(int id) {
        return noticeSqlRepository.removeNotice(id);
    }

    public List<Notice> getNoticeByPersonId(int personId) {
        return noticeSqlRepository.getAllNoticesByPersonId(personId);
    }

    public boolean removeNoticesByPersonId(int personId) {
        return noticeSqlRepository.removeNoticesByPersonId(personId);
    }
}
