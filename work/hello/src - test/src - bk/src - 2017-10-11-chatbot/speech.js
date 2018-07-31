document.addEventListener('keypress', e => {
			console.log(window.location);
			if(window.location.href === "http://localhost:3000/#/" || window.location.href === "http://localhost:3000/") {
				return;
			}
			if(e.key == '*'){
				var voicetotext = new MozActivity({
					name: "voicetotext",
					data: {
						"mode":"Jio Assist"
					}
				});

				voicetotext.onsuccess = function() {
					console.log("voicetotext.result");
					var result = voicetotext.result;
					var tempresult = result.resultantData.text.split(' ').join('');
					if(!isNaN(tempresult)) {
						result.resultantData.text = tempresult;
						console.log("Inside PNR NUMBER Processing");
					}

					console.log("Result from GVA : "+result.resultantData.text)

					// START :: Handling Immediate Proccessing of REQUESTS

					document.getElementById('footerMsg').value  = result.resultantData.text;
					document.getElementById('footerMsg').blur();
				};

				voicetotext.onerror = function(){
					console.log('error text need to print');
				};
			}
	});
