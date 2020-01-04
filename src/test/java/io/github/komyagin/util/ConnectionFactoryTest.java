package io.github.komyagin.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;


class ConnectionFactoryTest {

    @Test
    void getConnection() {
        Connection connection = ConnectionFactory.getConnection();

        //TODO: I have to think...
    }
}