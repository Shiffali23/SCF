Backbone.Model.prototype.idAttribute = '_id';

// Backbone Model

var Blog = Backbone.Model.extend({
	defaults: {
        id:'',
		author: '',
		title: '',
		url: ''
	}
});

// Backbone Collection
//auth for AEM

sendAuthentication = function (xhr) {
  var user = "myusername";// your actual username
  var pass = "mypassword";// your actual password
  var token = user.concat(":", pass);
  xhr.setRequestHeader('Authorization', ("Basic ".concat(btoa(token))));
}


var Blogs = Backbone.Collection.extend({
	url: 'http://localhost:4502/bin/userinfo'
});

// instantiate a Collection

var blogs = new Blogs();

// Backbone View for one blog

var BlogView = Backbone.View.extend({
	model: new Blog(),
	tagName: 'tr',
	initialize: function() {
		this.template = Handlebars.compile( $(".blogs-list-template").html() );
	},
	events: {
		'click .edit-blog': 'edit',
		'click .cancel': 'cancel'
	},
	edit: function() {
		$('.edit-blog').hide();
		$('.delete-blog').hide();
		this.$('.update-blog').show();
		this.$('.cancel').show();

		var author = this.$('.author').html();
		var title = this.$('.title').html();
		var url = this.$('.url').html();

		this.$('.author').html('<input type="text" class="form-control author-update" value="' + author + '">');
		this.$('.title').html('<input type="text" class="form-control title-update" value="' + title + '">');
		this.$('.url').html('<input type="text" class="form-control url-update" value="' + url + '">');
	},
	cancel: function() {
		blogsView.render();
	},
	render: function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}
});

// Backbone View for all blogs

var BlogsView = Backbone.View.extend({
	model: blogs,
	el: $('.blogs-list'),
	initialize: function() {
		var self = this;
		this.model.on('add', this.render, this);
        
         this.model.fetch({
            beforeSend: function (xhr) {
                console.log("beforsend called")
    xhr.setRequestHeader ("Authorization", "Basic " + btoa("admin" + ":" + "admin"));
},
			success: function(response) {
				_.each(response.toJSON(), function(item) {
					console.log('Successfully GOT blog with _id: ' + item.id);
				})
			},
			error: function() {
				console.log('Failed to get blogs!');
			}
		});
	},
   
	render: function() {
		var self = this;
		this.$el.html('');
		_.each(this.model.toArray(), function(blog) {
			self.$el.append((new BlogView({model: blog})).render().$el);
		});
		return this;
	}
});

var blogsView = new BlogsView();

$(document).ready(function() {
	$('.add-blog').on('click', function() {
		var blog = new Blog({
            id: $('.id-input').val(),
			author: $('.author-input').val(),
			title: $('.title-input').val(),
			url: $('.url-input').val()
		});
        $('.id-input').val('');
		$('.author-input').val('');
		$('.title-input').val('');
		$('.url-input').val('');
        console.log(blog.toJSON())
		blogs.add(blog);
        blog.save(null, {
            success: function(response) {
                console.log('Successfully SAVED blog with _id: ' + response.toJSON()._id);
            },
            error: function() {
                console.log('Failed to save blog!');
            }
        });
	});
})