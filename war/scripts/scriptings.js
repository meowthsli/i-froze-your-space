/**
 * @author meowth
 */
	
 $(document).ready(function() { 
	    $("#inputform").validate({ 
	        rules: { 	          
				email: {// compound rule 
					required: true, 
					email: true 
				}, 
	        
				message: { 
					required: true 
				} 
	        }, 
	        messages: { 
				email: "*",
				message: "*"
	        } 
	    });
		
		$('#thankx').fadeOut( 4000, function() {		
		}); 
});