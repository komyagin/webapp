package io.github.komyagin.dao;

import io.github.komyagin.model.Category;
import io.github.komyagin.model.Notice;
import io.github.komyagin.service.SqlToNotice;
import io.github.komyagin.util.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeSqlRepository implements NoticeRepository {

    private static final Logger logger = LoggerFactory.getLogger(NoticeSqlRepository.class);

    private static final String NOTICE_SELECT_ALL_SQL = "SELECT * FROM lab.notice";
    private static final String NOTICE_SELECT_BY_ID_SQL = "SELECT * FROM lab.notice WHERE id = ?";
    private static final String NOTICE_INSERT_SQL = "INSERT INTO lab.notice " +
            "(person_id, header, body, tel_number, created_date, updated_date, category) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String NOTICE_UPDATE_BY_ID_SQL = "UPDATE lab.notice SET person_id = ?, header = ?, body = ?, " +
            "tel_number = ?, updated_date = ?, category = ? WHERE id = ?";
    private static final String NOTICE_DELETE_SQL = "DELETE FROM lab.notice WHERE id = ?";
    private static final String NOTICE_SELECT_ALL_BY_PERSON_ID_SQL = "SELECT * FROM lab.notice WHERE person_id = ?";

    private static final String PERSONS_NOTICES_DELETE_BY_PERSON_ID_SQL = "DELETE FROM lab.notice WHERE person_id = ?";

    @Override
    public boolean addNotice(Notice notice) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NOTICE_INSERT_SQL)) {
            preparedStatement.setInt(1, notice.getPersonId());
            preparedStatement.setString(2, notice.getHeader());
            preparedStatement.setString(3, notice.getBody());
            preparedStatement.setString(4, notice.getTelNumber());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(notice.getCreatedLocalDateTime()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(notice.getUpdatedLocalDateTime()));
            preparedStatement.setString(7, notice.getCategory().toString());
            if (preparedStatement.executeUpdate() > 0) {
                logger.info("INSERT notice to DB is done");
                return true;
            } else {
                logger.error("INSERT notice to DB is undone");
                return false;
            }
        } catch (SQLException e) {
            logger.error("SQL error {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Notice getNotice(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(NOTICE_SELECT_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            return SqlToNotice.getNotice(preparedStatement.executeQuery());
        } catch (SQLException e) {
            logger.error("Notice not found {}", e);
        }
        return null;
    }

    @Override
    public boolean updateNotice(Notice notice) {
        if (getNotice(notice.getId()) != null) {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(NOTICE_UPDATE_BY_ID_SQL)) {
                preparedStatement.setInt(1, notice.getPersonId());
                preparedStatement.setString(2, notice.getHeader());
                preparedStatement.setString(3, notice.getBody());
                preparedStatement.setString(4, notice.getTelNumber());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(notice.getUpdatedLocalDateTime()));
                preparedStatement.setString(6, notice.getCategory().toString());
                preparedStatement.setInt(7, notice.getId());
                if (preparedStatement.executeUpdate() > 0) {
                    logger.info("UPDATE notice to DB is done");
                    return true;
                } else {
                    logger.warn("UPDATE notice to DB is undone");
                    return false;
                }
            } catch (SQLException e) {
                logger.error("UPDATE SQL error {}", e);
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean removeNotice(int id) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NOTICE_DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                logger.info("Notice is deleted");
                return true;
            } else {
                logger.error("Notice is not deleted");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Removing notice error {}", e);
            return false;
        }
    }

    @Override
    public List<Notice> getAllNotices() {
        List<Notice> list = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(NOTICE_SELECT_ALL_SQL)) {
            list = SqlToNotice.getAllNotices(resultSet);
        } catch (SQLException e) {
            logger.error("Cannot get all notices {}", e.getMessage());
        }
        return list;
    }

    public boolean removeNoticesByPersonId(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PERSONS_NOTICES_DELETE_BY_PERSON_ID_SQL)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                logger.info("Notices by person_id removed");
                return true;
            }
        }catch (SQLException e) {
            logger.error("Notices didn't removed", e);
            return false;
        }
        return false;
    }

    @Override
    public List<Notice> getAllNoticesByPersonId(int personId) {
        List<Notice> list = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NOTICE_SELECT_ALL_BY_PERSON_ID_SQL)) {
            preparedStatement.setInt(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Category category = Category.valueOf((resultSet.getString(8)).toUpperCase());
                    Notice notice = new Notice(resultSet.getInt(1), resultSet.getInt(2),
                            resultSet.getString(3), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getTimestamp(6).toLocalDateTime(),
                            resultSet.getTimestamp(7).toLocalDateTime(), category);
                    list.add(notice);
                }
            }
        } catch (SQLException e) {
            logger.error("SELECT notice BY peson_id error {}", e.getMessage());
        }
        return list;
    }
}
