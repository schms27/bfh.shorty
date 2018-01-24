package bfh.shorty.usershortlinkservice.model;

import java.util.List;

public class ShortLink {

    private String id;

    Domain domain;

    List<Target> targets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }
}
