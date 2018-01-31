/*
package bfh.shorty.shortlinkservice.controller;


import bfh.shorty.shortlinkservice.entities.ShortLink;
import bfh.shorty.shortlinkservice.repositories.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = "application/hal+json")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class ShortLinkController {
    private ShortLinkRepository shortLinkRepository;

    @Autowired
    ShortLinkController(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/hal+json")
    List<ShortLink> findByOwnerId(@Param("ownerId") String ownerId) {
        return shortLinkRepository.findByOwnerId(ownerId);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/hal+json")
    ShortLink add(@RequestBody ShortLink shortLink) {
//        shortLink.setLink("generateShortLinkHere"); //ToDo: genereate short ID for link
        shortLinkRepository.save(shortLink);
        return shortLinkRepository.findOne(shortLink.getId());
    }
}
//*/