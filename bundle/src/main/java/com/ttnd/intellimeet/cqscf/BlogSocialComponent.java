package com.ttnd.intellimeet.cqscf;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;

import com.adobe.cq.social.commons.client.api.ClientUtilities;
import com.adobe.cq.social.commons.client.api.QueryRequestInfo;
import com.adobe.cq.social.commons.comments.listing.CommentSocialComponentListProviderManager;
import com.adobe.cq.social.forum.client.api.AbstractPost;
import com.adobe.cq.social.forum.client.api.ForumConfiguration;
import com.adobe.cq.social.forum.client.api.Post;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Priyanku on 1/4/2016.
 */
public class BlogSocialComponent extends AbstractPost<ForumConfiguration> implements Post<ForumConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(BlogSocialComponent.class);
    private Tag techTag;
    private List<Tag> tags;
    private ValueMap properties;
    Logger log = LoggerFactory.getLogger(BlogSocialComponent.class);

    /**
     * Construct a comment for the specified resource and client utilities.
     * @param resource the specified resource
     * @param clientUtils the client utilities instance
     * @param commentListProviderManager list manager to use for listing content
     * @throws RepositoryException if an error occurs
     */
    public BlogSocialComponent(final Resource resource, final ClientUtilities clientUtils,
                               final CommentSocialComponentListProviderManager commentListProviderManager) throws RepositoryException{
        super(resource, clientUtils, commentListProviderManager);
        filterTags();
        this.properties = ResourceUtil.getValueMap(resource);
    }

    /**
     * Constructor of a comment.
     * @param resource the specified {@link com.adobe.cq.social.commons.Comment}
     * @param clientUtils the client utilities instance
     * @param queryInfo the query info.
     * @param commentListProviderManager list manager to use for listing content
     * @throws RepositoryException if an error occurs
     */
    public BlogSocialComponent(final Resource resource, final ClientUtilities clientUtils,
                               final QueryRequestInfo queryInfo, final CommentSocialComponentListProviderManager commentListProviderManager)
            throws RepositoryException {
        super(resource, clientUtils, queryInfo, commentListProviderManager);
        filterTags();
        this.properties = ResourceUtil.getValueMap(resource);
    }

    public BlogSocialComponent(final Resource resource, final ClientUtilities clientUtils, final QueryRequestInfo queryInfo,
                               final Resource latestPost, final int numReplies,
                               final CommentSocialComponentListProviderManager listProviderManager) throws RepositoryException {
        super(resource, clientUtils, queryInfo, latestPost, numReplies, listProviderManager);
        filterTags();
        this.properties = ResourceUtil.getValueMap(resource);
    }


    private void filterTags() {
        this.tags = new ArrayList<Tag>();
        for(Tag tag:super.getTags()) {
            if(tag.getTagId().startsWith("scfTech:")) {
                techTag = tag;
            }
                tags.add(tag);

        }
    }

    public String getTech() {
        return null == this.techTag ? null : this.techTag.getTitle();
    }

    public boolean isSling() {
        if(this.techTag != null && "scfTech:sling".equals(techTag.getTagId())){
            return true;
        }
        return false;
    }

    public boolean isCq() {
        if(this.techTag != null && "scfTech:cq".equals(techTag.getTagId())){
            return true;
        }
        return false;
    }

    public boolean isJava() {
        if(this.techTag != null && "scfTech:java".equals(techTag.getTagId())){
            return true;
        }
        return false;
    }

    public boolean isAem() {
        if(this.techTag != null && "scfTech:Aem".equals(techTag.getTagId())){
            return true;
        }
        return false;
    }



    public String getLikes() {
        String totalLikes = this.properties.get("likes")==null?"0":this.properties.get("likes", String.class);
        LOG.info("current Likes----------------------------------------------------------------------------------"+totalLikes);
        return totalLikes;
    }
}
