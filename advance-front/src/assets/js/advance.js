$(document).ready(function($) {
  $(".clickable-tr").click(function() {
    //alert($(this).data("href"));
    window.document.location = $(this).data("href");
  });
});
