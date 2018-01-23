package bfh.shorty.shortlinkservice.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShortLink {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Embedded
    Domain domain;

    @OneToMany
    List<Target> targets;

}
