package com.ttnd.intellimeet.cqscf;

import com.adobe.cq.social.commons.client.api.SocialComponent;
import com.adobe.cq.social.commons.client.api.SocialComponentFactory;
import com.adobe.cq.social.commons.client.api.SocialComponentFactoryManager;
import com.adobe.cq.social.commons.client.endpoints.AbstractSocialOperation;
import com.adobe.cq.social.commons.client.endpoints.OperationException;
import com.adobe.cq.social.commons.client.endpoints.SocialOperationResult;
import com.adobe.cq.social.forum.client.endpoints.ForumOperations;
import com.adobe.cq.social.handlebars.HandlebarsScriptingEngine;
import com.adobe.cq.social.handlebars.HandlebarsScriptingEngineFactory;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.servlets.post.PostOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.*;

@Component(immediate = true)
@Service
@Property(name = PostOperation.PROP_OPERATION_NAME, value = "social:setTechnology")
public class SetTechnologyOperation extends AbstractSocialOperation implements PostOperation{
    private static final Logger LOG = LoggerFactory.getLogger(SetTechnologyOperation.class);
    
    @Reference
    private ForumOperations forumService;
    
    @Reference
    private SocialComponentFactoryManager srf;

    @Override
    protected SocialOperationResult performOperation(SlingHttpServletRequest req) throws OperationException {
        LOG.info("req.getParameter......................>> "+req.getParameter("tech"));
        final String[] techToSet1 = req.getParameter("tech").split("-");
        final Resource idea = req.getResource();

        final ValueMap props = idea.adaptTo(ValueMap.class);
        final String[] tags = props.get("cq:tags", new String[]{});
        final List<String> tagList = new ArrayList<String>();
        for(String tag: tags) {
            if(!tag.startsWith("scfTech:")) {
                tagList.add(tag);
            }
        }
        tagList.addAll(Arrays.asList(techToSet1));
        Map<String,Object> updates = new HashMap<String,Object>();
        updates.put("cq:tags", tagList.toArray(new String[]{}));
        Resource updatedResource = forumService.update(req.getResource(), updates, null, req.getResourceResolver().adaptTo(Session.class));
        return new SocialOperationResult(this.getSocialComponentForResource(updatedResource, req), 200, updatedResource.getPath());
    }

    private SocialComponent getSocialComponentForResource(Resource newProject, SlingHttpServletRequest request) {
        if (newProject == null) {
            return null;
        }
        final SocialComponentFactory factory = this.srf.getSocialComponentFactory(newProject);
        return factory.getSocialComponent(newProject, request);
    }

}
