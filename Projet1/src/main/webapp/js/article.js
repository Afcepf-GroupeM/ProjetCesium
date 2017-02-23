/**
 * JQuery de la page article
 */
$(document).ready(function(){
	
	view();
	
	$('#allReviewsForm\\:allReviewsLink').on('click', function(event){
		view();
	});
	
	function view (){
		if ($('.tableComment').length > 5){
			var extraTable = $('.tableComment').length - 5;
			
			$('.tableComment').each(function() {
				var tableId = $(this).attr('id');
				var tableNumber = tableId.substr(12);
				
				if (tableNumber <= extraTable){
					$('#' + tableId).toggle();
				}
			});
		}
	}
	
	// 16 possible values (0-15)
	var hex = '0123456789ABCDEF';
	
	var meanRating = $('#meanRatingReview').text();
	color(meanRating, $('#meanRatingReview').attr('id'));
	
	$('.ratingReview').each(function() {
		var ratingId = $(this).attr('id');
		var ratingValue = $('#' + ratingId).text();
		
	    color(ratingValue, ratingId);
	});
	
	function color (ratingValue, ratingId){
		if (ratingValue == 0) {
	        $('#' + ratingId).css('color', "#FF0000");        
	    }
	    else if (ratingValue == 2.5) {
	        $('#' + ratingId).css('color', "#FFFF00");
	    }
	    else if(ratingValue == 5.0) {
	        $('#' + ratingId).css('color', "#00FF00");
	    }
	    else {
	        if (ratingValue < 2.5) {
	            // ratingValue < 2.5 is between red and yellow
	            var perc = ratingValue / 2.5;
	            var hexRatingValue = hex[Math.round(16*perc)];
	            
	            $('#' + ratingId).css('color', "#FF" + hexRatingValue + hexRatingValue + "00");
	        }
	        else if (ratingValue > 2,5) {
	            // ratingValue > 2.5 is between yellow and green
	            var perc = ratingValue / 5 / 0.5 - 1;
	            var hexRatingValue = hex[hex.length - Math.round(16*perc)];
	            
	            $('#' + ratingId).css('color', "#" + hexRatingValue + hexRatingValue + "FF00");
	        }
	    }
	}
	
});