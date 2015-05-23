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


$url = 'http://echo.jsontest.com/key/value/one/two';
$content = file_get_contents($url);
$json = json_decode($content, true);
print_r($json['data']);
exit();
foreach($json['data']['weather'] as $item) {
    print $item['date'];
    print ' - ';
    print $item['weatherDesc'][0]['value'];
    print ' - ';
    print '<img src="' . $item['weatherIconUrl'][0]['value'] . '" border="0" alt="" />';
    print '<br>';
}

// Set status message
$tweetMessage = 'This is a tweet to my Twitter account via PHP.';

// Check for 140 characters
if(strlen($tweetMessage) <= 140)
{
    // Post the status message
    $tweet->post('statuses/update', array('status' => $tweetMessage));
}

?>