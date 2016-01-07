package com.ttnd.intellimeet.cqscf;

import javax.jcr.RepositoryException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import com.adobe.cq.social.commons.client.api.AbstractSocialComponentFactory;
import com.adobe.cq.social.commons.client.api.ClientUtilities;
import com.adobe.cq.social.commons.client.api.QueryRequestInfo;
import com.adobe.cq.social.commons.client.api.SocialComponent;
import com.adobe.cq.social.commons.client.api.SocialComponentFactory;
import com.adobe.cq.social.commons.comments.api.Comment;
import com.adobe.cq.social.commons.comments.api.CommentSocialComponentFactory;
import com.adobe.cq.social.commons.comments.listing.CommentSocialComponentListProviderManager;
import com.adobe.cq.social.forum.client.api.TopicSocialComponentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Priyanku on 1/4/2016.
 */

/**
 * CustomCommentFactory extends the default CommentSocialComponentFactory to leverage the default comment social
 * component implementation. This makes it possible to only make changes needed for customization without having to
 * implement all the APIs specified by {@link Comment}.
 */
@Component(name = "Intellimeet Social Component Factory")
@Service
public class IdeaSocialComponentFactory extends AbstractSocialComponentFactory implements SocialComponentFactory {


    private static final Logger LOG = LoggerFactory.getLogger(IdeaSocialComponentFactory.class);
    @Reference
    private CommentSocialComponentListProviderManager commentListProviderManager;

    @Override
    public SocialComponent getSocialComponent(final Resource resource) {
        try {
            LOG.info("This Resource ----------------------------------------------------------"+ resource);
            LOG.info("commentListProviderManager ----------------------------------------------------------"+ commentListProviderManager);
            return new IdeaSocialComponent(resource, this.getClientUtilities(resource.getResourceResolver()),commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }

    @Override
    public SocialComponent getSocialComponent(final Resource resource, final SlingHttpServletRequest request) {
        try {
            LOG.info("This Resource ----------------------------------------------------------"+ resource);
            LOG.info("commentListProviderManager ----------------------------------------------------------"+ commentListProviderManager);
            LOG.info("request ----------------------------------------------------------"+ request);
            LOG.info("Component Factory ----------------------------------------------------------"+ this);

            return new IdeaSocialComponent(resource, this.getClientUtilities(request),this.getQueryRequestInfo(request),commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }

    @Override
    public SocialComponent getSocialComponent(Resource resource, ClientUtilities clientUtils, QueryRequestInfo listInfo) {
        try {
            LOG.info("This Resource ----------------------------------------------------------"+ resource);
            LOG.info("commentListProviderManager ----------------------------------------------------------"+ commentListProviderManager);
            return new IdeaSocialComponent(resource, clientUtils, listInfo,commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }


    /*
     * (non-Javadoc)
     * @see com.adobe.cq.social.commons.client.api.AbstractSocialComponentFactory#getPriority() Set the priority to a
     * number greater than 0 to override the default SocialComponentFactory for comments.
     */
    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public String getSupportedResourceType() {
        return "scf/components/ideation/topic";
    }

}
