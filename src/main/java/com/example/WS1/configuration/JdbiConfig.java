package com.example.WS1.configuration;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.argument.ArgumentFactory;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.UUID;

@Configuration
public class JdbiConfig {

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        return Jdbi.create(dataSourceProxy)
                .installPlugins()
                .registerArgument(new UUIDArgumentFactory());
    }
}

class UUIDArgumentFactory extends AbstractArgumentFactory<UUID> {
    UUIDArgumentFactory() {
        super(Types.VARCHAR);
    }

    @Override
    protected Argument build(UUID value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setString(position, value.toString());
    }
}