<?php

// Include twitteroauth
require_once('twitteroauth.php');

// Set keys
$consumerKey = 'sKrVtL4ie4j7g9XKEsEEneMAA';
$consumerSecret = 'EAxltB23DPKqtZFF9M5GE4NTCXyYXtrfgofTTNafxpQ0KfzEd5';
$accessToken = '3295475051-sgDj3N0hbb73kr4qAdVpFiPvcLsjKFV6QWl3wv0';
$accessTokenSecret = 'w0J08K3XutY9Qp2ZaIucml1oBnnWn4qcVaC6gwjrPADcE';

// Create object
$tweet = new TwitterOAuth($consumerKey, $consumerSecret, $accessToken, $accessTokenSecret);


// $url = 'http://incidencia.wratix.com/api';
// $content = file_get_contents($url);
// $json = json_decode($content, true);

if(!empty($_POST['descripcion']) and !empty($_POST['imagen'])){
	$descripcion = $_POST['descripcion'];
	$image 		 = $_POST['imagen'];

	if(strlen($descripcion) <= 120){
		$tweetMessage = '#AlertaMunicipal ' . $descripcion  ;
	    $tweet->post('statuses/update', array(
	    	'media'  => "@{$image};type=image/jpeg;filename={$image}",
	    	'status' => $tweetMessage
	    	)
	    );
	    echo 'Se han realizado los Twits <br>';
	}else{
		echo 'El Tweet supera los 140 caracteres<br>';
	}
}else{
	echo 'necesito un post';
}
// foreach($json['results'] as $item) {
	
// 	// if(strlen($tweetMessage) <= 140)
// 	// {
// 	//     // Post the status message
// 	// }

	
    
// }
 
// Set status message
//$tweetMessage = 'This is a tweet to my Twitter account via PHP.';

// Check for 140 characters
/* if(strlen($tweetMessage) <= 140)
{
    // Post the status message
    $tweet->post('statuses/update', array('status' => $tweetMessage));
}
*/
?>