package com.ttnd.intellimeet.cqscf;

import com.adobe.cq.social.commons.client.api.SocialComponent;
import com.adobe.cq.social.commons.client.api.SocialComponentFactory;
import com.adobe.cq.social.commons.client.api.SocialComponentFactoryManager;
import com.adobe.cq.social.commons.client.endpoints.AbstractSocialOperation;
import com.adobe.cq.social.commons.client.endpoints.OperationException;
import com.adobe.cq.social.commons.client.endpoints.SocialOperationResult;
import com.adobe.cq.social.forum.client.endpoints.ForumOperations;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.servlets.post.PostOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

@Component(immediate = true)
@Service
@Property(name = PostOperation.PROP_OPERATION_NAME, value = "social:addLike")
public class LikeOperation extends AbstractSocialOperation implements PostOperation{

    Logger logger = LoggerFactory.getLogger(LikeOperation.class);
    @Reference
    private ForumOperations forumService;

    @Reference
    private SocialComponentFactoryManager srf;

    @Override
    protected SocialOperationResult performOperation(SlingHttpServletRequest req) throws OperationException {
        int likes = Integer.parseInt(req.getParameter("likes"));
        Map<String,Object> updates = new HashMap<String,Object>();
        updates.put("likes", ++likes);
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
