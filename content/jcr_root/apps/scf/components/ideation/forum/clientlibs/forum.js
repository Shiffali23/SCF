(function($CQ, _, Backbone, SCF) {
    "use strict";
    var SCFTopic = SCF.Topic.extend({
        modelName:"SCFTopic Model",
        addLike:function(){
            alert("i m model")        }
    });

    var SCFTopicView = SCF.TopicView.extend({
        viewName: "Idea",
        addLike:function(){
            alert(this.model.modelName);
            this.model.addLike();}
    });

    SCF.Idea = SCFTopic;
    SCF.IdeaView = SCFTopicView;
    SCF.registerComponent('scf/components/ideation/topic', SCF.Idea, SCF.IdeaView);
    SCF.registerComponent('scf/components/ideation/forum', SCF.Forum, SCF.ForumView);
})($CQ, _, Backbone, SCF);
