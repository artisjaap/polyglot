package be.artisjaap.migrate.model.mongo;

import be.artisjaap.migrate.model.InitScript;

public interface VersionRepositoryCustom {
    void zetVerwerkt(InitScript script);

    String findCurrentVersion();
}
