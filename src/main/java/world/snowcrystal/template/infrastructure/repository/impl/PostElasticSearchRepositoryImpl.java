package world.snowcrystal.template.infrastructure.repository.impl;

import jakarta.annotation.Resource;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;
import world.snowcrystal.template.domain.aggregationsearch.repository.SupportSearchRepository;
import world.snowcrystal.template.infrastructure.repository.po.PostElkPO;

import java.util.List;

@Repository("postElasticsearchRepository")
public class PostElasticSearchRepositoryImpl implements SupportSearchRepository {


    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<PostElkPO> searchPost(String keyword) {
        throw new UnsupportedOperationException("Not supported yet.");
//        return List.of();
    }



}
