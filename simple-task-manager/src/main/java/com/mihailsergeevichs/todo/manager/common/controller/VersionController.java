package com.mihailsergeevichs.todo.manager.common.controller;

import com.mihailsergeevichs.todo.manager.common.version.BuildProperties;
import com.mihailsergeevichs.todo.manager.common.version.GitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    private final BuildProperties buildProperties;
    private final GitProperties gitProperties;

    @Autowired
    VersionController(BuildProperties buildProperties,
                         GitProperties gitProperties) {
        this.buildProperties = buildProperties;
        this.gitProperties = gitProperties;
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    BuildProperties getBuildConfiguration() {
        return buildProperties;
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    GitProperties getVersion() {
        return gitProperties;
    }
}
