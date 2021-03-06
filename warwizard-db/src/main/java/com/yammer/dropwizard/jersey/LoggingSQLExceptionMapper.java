package com.yammer.dropwizard.jersey;

import com.google.inject.Singleton;
import com.yammer.dropwizard.logging.Log;

import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

/**
 * Iterates through SQLExceptions to log all causes
 */
@Provider
@Singleton
public class LoggingSQLExceptionMapper extends LoggingExceptionMapper<SQLException> {
    private static final Log LOG = Log.forClass(LoggingSQLExceptionMapper.class);

    @Override
    protected void logException(long id, SQLException exception) {
        final String message = formatLogMessage(id, exception);
        for (Throwable throwable : exception) {
            LOG.error(throwable, message);
        }
    }
}
