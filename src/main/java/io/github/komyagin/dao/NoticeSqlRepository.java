package io.github.komyagin.dao;

import io.github.komyagin.model.Notice;
import io.github.komyagin.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class NoticeSqlRepository implements NoticeRepository {
    @Override
    public void addNotice(Notice notice) {

    }

    @Override
    public Notice readNotice(int id) {
        return null;
    }

    @Override
    public Notice updateNotice(Notice notice) {
        return null;
    }

    @Override
    public Notice removeNotice(int id) {
        return null;
    }

    @Override
    public List<Notice> showAll() {
        return null;
    }

    @Override
    public boolean isExist(int id) {
        return false;
    }
}
