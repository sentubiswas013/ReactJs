import Jconsole from './Jconsole';
/*
* This class will be used to have all utility based functions which can be used
* all throught out the project.
*/

function Utility () {};

/**
* This function helps in fetching the current location of the user.
*/
Utility.getCurrentLocation = function(callback) {
  Jconsole.log("getCurrentLocation");
  if (!(navigator.geolocation == 'undefined')) {
    navigator.geolocation.getCurrentPosition(
    function(position) { // Success
      Jconsole.log("Lat = " + position.coords.latitude
                  + "  Long = " +position.coords.longitude);
      callback(position.coords.latitude, position.coords.longitude);
    },
    function(error) { // Failure
      Jconsole.log("Error in fetching geolocation " + error.code + " message = " + error.message);
      // Sending dummy Location in case of Error.
      callback(19.127510, 73.007608);
    },
    { timeout: 10000} // Setting timeout to decide till when it should try fetching geolocation.
  );
  } else {
    // Device doesn't support geolocation.
    Jconsole.log("geolocation not supported by device.");
    // Sending dummy Location in case of Error.
    callback(19.127510, 73.007608);
  }
}

/**
* This function is used to make a phone call on a given no.
*/
Utility.makePhoneCall = function(phone_number) {
    Jconsole.log("makePhoneCall function");
    let MozActivity= window.MozActivity||null;
    if (MozActivity) {
      var call = new MozActivity({
				name: "dial",
				data: {
					number: phone_number
				}
			});
      call.onsuccess = function() {
        var successResult = this.result;
        Jconsole.log("Call screen invoked " + successResult);
      };

      call.onerror = function() {
        Jconsole.log(this.error);
      };

    } else {
      Jconsole.log("MozActivity not supported");
    }
}

/**
* This function will be used to open the given url in browser.
*/
Utility.openWebUrl = function(web_url) {
  let MozActivity= window.MozActivity||null;
  if (MozActivity) {
    var openURL = new MozActivity({
  			name: "view",
  			data: {
  				type: "url",
  				url: web_url
  			}
  		});

      openURL.onsuccess = function() {
        var successResult = this.result;
        Jconsole.log("Browser screen invoked " + successResult);
      };

      openURL.onerror = function() {
        Jconsole.log(this.error);
      };
  } else {
    Jconsole.log("MozActivity not supported");
  }
}

export default Utility;
