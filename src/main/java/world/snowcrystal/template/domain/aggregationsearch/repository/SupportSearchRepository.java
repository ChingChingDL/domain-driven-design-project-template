package world.snowcrystal.template.domain.aggregationsearch.repository;

import world.snowcrystal.template.infrastructure.repository.po.PostElkPO;

import java.util.List;

public interface SupportSearchRepository {


    List<PostElkPO> searchPost(String keyword);

}
