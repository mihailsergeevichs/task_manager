package com.mihailsergeevichs.todo.manager.common.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Overlord on 10.12.2015.
 */
@Component
public class GitProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitProperties.class);

    @PostConstruct
    public void writeGitCommitInformationToLog() {
        LOGGER.info("Application was built by using the Git commit: {}", this);
    }

    private String branch;
    private final BuildProperties build;
    private final CommitProperties commit;
    private final boolean dirty;
    private final String remoteOriginUrl;
    private final String tags;

    @Autowired
    public GitProperties(@Value("${git.branch}") String branch,
                         BuildProperties build,
                         CommitProperties commit,
                         @Value("${git.dirty}") boolean dirty,
                         @Value("${git.remote.origin.url}") String remoteOriginUrl,
                         @Value("${git.tags}") String tags) {
        this.branch = branch;
        this.build = build;
        this.commit = commit;
        this.dirty = dirty;
        this.remoteOriginUrl = remoteOriginUrl;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "GitProperties{" +
                "branch='" + branch + '\'' +
                ", build=" + build +
                ", commit=" + commit +
                ", dirty=" + dirty +
                ", remoteOriginUrl='" + remoteOriginUrl + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public BuildProperties getBuild() {
        return build;
    }

    public CommitProperties getCommit() {
        return commit;
    }

    public boolean isDirty() {
        return dirty;
    }

    public String getRemoteOriginUrl() {
        return remoteOriginUrl;
    }

    public String getTags() {
        return tags;
    }

    //Getters are omitted for the sake of clarity
}
