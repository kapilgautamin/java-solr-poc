$(function () {

   var suggestionsAction = window.location.origin + "/suggestions";

   $("#form-search").autocomplete({
      minLength: 2,
      source: function (request, response) {

         $.post(suggestionsAction, {
            content: $("#form-search").val()
         }, function (data, status) {
            response(data);
         });

      }
   }).autocomplete( "instance" )._renderItem = function( ul, item ) {
      console.log(item);
      return $( "<li>" )
        .append( "<div>" + item.firstName + " "+ item.lastName + "</div>" )
        .appendTo( ul );
    };;
});