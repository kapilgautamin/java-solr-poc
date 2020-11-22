$(function () {
   var suggestionsAction = window.location.origin + "/api/search/auto-complete";

   $.widget( "custom.catcomplete", $.ui.autocomplete, {
      _create: function() {
        this._super();
        this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
      },
      _renderMenu: function( ul, items ) {
        var that = this, currentCategory = "";

        $.each( items, function( index, item ) {
          var li;
          
          if ( item.header != currentCategory ) {
          	var autoCompleteItem = "<li class='ui-autocomplete-category'>" + item.header + "</li>";
          	
          	$.each(item.results, function (data, value) {
          		var title = value.title;
          		var ariaLabel = "aria-label='" + item.header + " : " + title + "'";
        		autoCompleteItem = autoCompleteItem + "<li " + ariaLabel + ">" + title + "</li>";
        	})
          	
            ul.append(autoCompleteItem);
            currentCategory = item.header;
          }
          
          li = that._renderItemData( ul, item );          
        });
        
      }
    });
   
   $("#search").catcomplete({
      minLength: 2,
      source: function (request, response) {
         $.post(suggestionsAction, {
            content: $("#search").val()
         }, function (data, status) {
            response(data);
         });
      }
   });
});