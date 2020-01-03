package io.github.komyagin.service;

import io.github.komyagin.model.Category;
import io.github.komyagin.model.Notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlToNotice {
    private SqlToNotice() {
    }

    public static Notice getNotice(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Category category = Category.valueOf((resultSet.getString(8)).toUpperCase());
            return new Notice(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getTimestamp(6).toLocalDateTime(),
                    resultSet.getTimestamp(7).toLocalDateTime(), category);
        }
        return null;
    }

    public static List<Notice> getAllNotices(ResultSet resultSet) throws SQLException {
        List<Notice> list = new ArrayList<>();
        while (resultSet.next()) {
            Category category = Category.valueOf((resultSet.getString(8)).toUpperCase());
            list.add(new Notice(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getTimestamp(6).toLocalDateTime(),
                    resultSet.getTimestamp(7).toLocalDateTime(), category));
        }
        return list;
    }
}
