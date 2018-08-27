   /** 	This library is used to get the voice from the text as input using the microsoft cognitive Voice synthesis request
	*	Usage of this api in other js files is as follows
	*	TextToSpeech.speak(config,textcontent).then(function(data) { //success data }, function(error) { // error data});
	*	parameter config is an object it has following properties
	*	config={"language":"en-US","gender":"Female","voice":"ZiraRUS","textcontent":"You have reached your destination"};
	*/

   var TextToSpeech = (function(parent){
	var isPlayed = false,audio,myAudio;
	var accessToken='',errorTxt='';
	const syntUrl = 'https://speech.platform.bing.com/synthesize';
	const audioFormat = 'riff-16khz-16bit-mono-pcm';
	const tokenUrl = 'https://api.cognitive.microsoft.com/sts/v1.0/issueToken';
	const subscriptionKey = 'de8f111ead85424bba1b39ca308c5f4b';
	const errorStatus = {
		INVALIDACCESSTOKEN: {code: 403, errorText: 'Forbidden'},
		BADREQUEST: {code: 400, errorText: 'Bad Request'},
		UNAUTHORIZED: {code: 401, errorText: 'Unauthorized'},
		REQUESTENTITYTOOLARGE: {code: 413, errorText: 'Request Entity Too Large'},
		BADGATEWAY:{code: 502, errorText: 'Bad Gateway'},
		TOOMANYREQUESTS:{code: 429, errorText: 'Too Many Requests'},
		TEXTCONTENT: {code:400, errorText: 'Bad Request'},
		UNKNOWNERROR: {code: 520, errorText: 'Unknown Error'}
	};
	const success = {code:200, successText:'success'};

	/** Using this function we will get the acessToken from microsoft cognitive services*/
	if(accessToken == ''){
		getToken().then(function(data){
				accessToken = data;
			},
			function(error){
				console.log(error);
			}
		);
	}

  parent.indiantts=function(userText) {
    var data=userText;
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("readystatechange", function () {
     if (this.readyState === 4) {
    console.log(this.response);
    var audio = URL.createObjectURL(this.response);
    var myAudio = new Audio(audio);
    myAudio.play();
     }
    });

    xhr.open("GET", "http://ivrapi.indiantts.co.in/tts?type=indiantts&text="+data+"&api_key=a711e900-33dc-11e7-aee8-57840a2c0ce8&user_id=12167&action=play&amplify=6");
    xhr.responseType = "blob";
            xhr.send(data);
    }


	/** Using this function we will get the voice from text
	 * 	@param config is an object which contains language,gender,voice, textcontent and more
	 * 	Example of config object looks like
	 * 	config={"language":"en-US","gender":"Female","voice":"ZiraRUS","textcontent":"You have reached your destination"};
	 * 	properties of config object like language,gender,voice and textcontent are mandatory parameters
	 * 	need to pass in xml using HTTP POST calls, with out these parameters it will return error
	 */
	parent.speak = function(config,userText){
		var promise=new Promise(function(resolve, reject) {
			if(accessToken == ''){  // check the accessToken Value if it is null it will call the getToken function
				getToken().then(function(data){
					accessToken = data;
          tts(config,userText).then(function(data){
          					resolve(success);
          				},
          				function(error){
                    //alert("error");
          					errorTxt =  errorHandling(403);
                    console.log("Error : " + errorTxt);
          					reject(errorTxt);
          				}
          				);
				},
				function(error){
					errorTxt =  errorHandling(403);
					reject(errorTxt);
				}
				);
			} else {
        tts(config,userText).then(function(accessToken){
                  resolve(success);
                },
                function(error){
                  //alert("error");
                  errorTxt =  errorHandling(403);
                  console.log("Error : " + errorTxt);
                  reject(errorTxt);
                }
                );
			}

		});
		return promise;
	};
	return parent;


	/** Using this function we will get the acessToken from microsoft cognitive services
	 * 	User need to register on microsoft cognitive services and get the subscription-key
	 * 	Pass the subscription-key to the post request headers
	 * 	We will get the response text as access token
	 * 	Use this access token in speak function as post request headers with authorization header
	 */
	 function getToken(){
		var token =new Promise(function(resolve, reject) {
			var data = null;
			var req = new XMLHttpRequest({mozSystem: true});
				req.open('POST', tokenUrl);
				req.setRequestHeader("ocp-apim-subscription-key", subscriptionKey);
				req.setRequestHeader("cache-control", "no-cache");

				req.onload = function() {
				  if (req.status == 200) {
					  accessToken = req.response;
					  resolve(accessToken); // we got success data here, so resolve the Promise

				  } else {
					errorTxt = errorHandling(req.status);
					reject(errorTxt); // if the status is not 200 i.e error then we can reject with appropiate error object
				  }
				};
				req.onerror = function() {
					errorTxt = errorHandling(req.status);
					reject(errorTxt);  // if the status is not 200 i.e error then we can reject with appropiate error object
				};

				req.send(data); //send the request

		});
		return token;
	}

  function tts(config,userText){
  		var tts =new Promise(function(resolve, reject) {
  		config.voice=(config.language=="hi-IN")?"kalpana":"ZiraRUS";
  			var configObjCheck = defaultConfig(config);
      				var data='<?xml version=\"1.0\"?><speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xml:lang=\"'+configObjCheck.language+'\">'+userText+'<voice xml:lang=\"'+configObjCheck.language+'\" xml:gender=\"'+configObjCheck.gender+'\" name=\"Microsoft Server Speech Text to Speech Voice ('+configObjCheck.language+', '+configObjCheck.voice+')\"></voice></speak>';
      				var req = new XMLHttpRequest({mozSystem: true});

      				req.open('POST', syntUrl);
      				req.responseType = "blob";
      				req.setRequestHeader("content-type", "application/ssml+xml");
      				req.setRequestHeader("x-microsoft-outputformat", audioFormat);
      				req.setRequestHeader("authorization", "Bearer "+accessToken+"");  //get access token from microsoft cognitive services
      				req.setRequestHeader("cache-control", "no-cache");

      				req.onload = function() {
      				  if (req.status == 200) {
      					  if(myAudio){ // check the current audio is playing
      						  if(myAudio.ended){
      							audio = URL.createObjectURL(req.response);
      							myAudio = new Audio(audio);
      							myAudio.play();
      							isPlayed = true;
      							resolve(success); // we got success data here, so resolve the Promise
      						}else{ // returning error as because of audio is already playing
      							isPlayed = false;
      							errorTxt = errorHandling(429);
      							reject(errorTxt);
      						}
      					  }
      					  else{ // first time of the audio play
      						audio = URL.createObjectURL(req.response);
      						myAudio = new Audio(audio);
      						myAudio.play();
      						isPlayed = true;
      						resolve(success); // we got success data here, so resolve the Promise
      					  }
      				  } else {
      					if(req.status == 403)
      					{
      						getToken().then(function(data){  // call getoken to get the accessToken value
      								accessToken = data;
      							},
      							function(error){
      								errorTxt =  errorHandling(401);
      								reject(errorTxt);
      							}
      						);
      					}
      					errorTxt = errorHandling(req.status);
      					reject(errorTxt);  // if the status is not 200 i.e error then we can reject with appropiate error object
      				  }
      				};
      				req.onerror = function() {
      					errorTxt = errorHandling(req.status);
      					reject(errorTxt);  // if the status is not 200 i.e error then we can reject with appropiate error object
      				};

      				req.send(data); //send the request
  		});
  		return tts;
  	}
    return parent;








	/** Using this function we will return the corresponding error code to the client
	 * 	@param status is the response status code which gets from server
	 */
	function errorHandling(status){
		var errorStr='';
		switch(status){
			case 403:
				errorStr = errorStatus.INVALIDACCESSTOKEN;
			break;
			case 400:
				errorStr = errorStatus.BADREQUEST;
			break;
			case 401:
				errorStr = errorStatus.UNAUTHORIZED;
			break;
			case 413:
				errorStr = errorStatus.REQUESTENTITYTOOLARGE;
			break;
			case 502:
				errorStr = errorStatus.BADGATEWAY;
			break;
			case 429:
				errorStr = errorStatus.TOOMANYREQUESTS;
			break;
			default:
				errorStr =  errorStatus.UNKNOWNERROR;
			break;
		}
		return errorStr;
	}
	/** Using this function we will set the default config object
	 * @param target is an object
	 */
	function defaultConfig(target) {
		target.language=(target.language=='' ||target.language== undefined)?"en-US":target.language;
		target.gender=(target.gender=='' || target.gender==undefined)?"Female":target.gender;
		target.voice=(target.voice=='' || target.voice== undefined)?"ZiraRUS":target.voice;
		return target;
	}



})(TextToSpeech || {});
export default TextToSpeech;
